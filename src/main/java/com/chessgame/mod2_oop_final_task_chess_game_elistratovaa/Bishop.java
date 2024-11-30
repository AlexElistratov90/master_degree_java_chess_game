package com.chessgame.mod2_oop_final_task_chess_game_elistratovaa;

public class Bishop extends ChessPiece {

    // Конструктор принимает цвет фигуры
    public Bishop(String color) {
        super(color);
    }

    // Метод для получения символа фигуры
    @Override
    public String getSymbol() {
        return "B";
    }

    // Метод для проверки возможности хода слона
    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // Проверяем, находятся ли начальная и конечная позиции на доске
        if (!isOnBoard(line, column) || !isOnBoard(toLine, toColumn)) {
            return false;
        }

        // Слон не может остаться на месте
        if (line == toLine && column == toColumn) {
            return false;
        }

        // Проверяем, ходит ли слон по диагонали
        if (Math.abs(line - toLine) != Math.abs(column - toColumn)) {
            return false;
        }

        // Проверяем, свободен ли путь между начальной и конечной точками
        int rowDirection = (toLine - line) > 0 ? 1 : -1; // Направление движения по строкам
        int colDirection = (toColumn - column) > 0 ? 1 : -1; // Направление движения по столбцам

        int currentRow = line + rowDirection;
        int currentCol = column + colDirection;

        while (currentRow != toLine && currentCol != toColumn) {
            if (chessBoard.board[currentRow][currentCol] != null) {
                return false; // На пути есть фигура
            }
            currentRow += rowDirection;
            currentCol += colDirection;
        }

        // Проверяем целевую клетку: она может быть пустой или занята фигурой другого цвета
        ChessPiece target = chessBoard.board[toLine][toColumn];
        return target == null || !target.getColor().equals(this.getColor());
    }

    // Метод для проверки, находится ли позиция на доске
    private boolean isOnBoard(int line, int column) {
        return line >= 0 && line < 8 && column >= 0 && column < 8;
    }
}
