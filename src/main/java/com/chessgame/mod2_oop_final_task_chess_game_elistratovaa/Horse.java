package com.chessgame.mod2_oop_final_task_chess_game_elistratovaa;

public class Horse extends ChessPiece {

    // Конструктор принимает цвет фигуры
    public Horse(String color) {
        super(color);
    }

    // Метод для получения символа фигуры
    @Override
    public String getSymbol() {
        return "H";
    }

    // Метод для проверки возможности хода коня
    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // Проверяем, находятся ли начальная и конечная позиции на доске
        if (!isOnBoard(line, column) || !isOnBoard(toLine, toColumn)) {
            return false;
        }

        // Конь не может остаться на месте
        if (line == toLine && column == toColumn) {
            return false;
        }

        // Проверяем, делает ли конь ход буквой "Г"
        int rowDiff = Math.abs(line - toLine);
        int colDiff = Math.abs(column - toColumn);
        if (!((rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2))) {
            return false;
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
