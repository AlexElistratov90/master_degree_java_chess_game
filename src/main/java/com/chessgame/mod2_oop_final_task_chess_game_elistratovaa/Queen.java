package com.chessgame.mod2_oop_final_task_chess_game_elistratovaa;

public class Queen extends ChessPiece {

    // Конструктор принимает цвет фигуры
    public Queen(String color) {
        super(color);
    }

    // Метод для получения символа фигуры
    @Override
    public String getSymbol() {
        return "Q";
    }

    // Метод для проверки возможности хода ферзя
    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // Проверяем, находятся ли начальная и конечная позиции на доске
        if (!isOnBoard(line, column) || !isOnBoard(toLine, toColumn)) {
            return false;
        }

        // Ферзь не может остаться на месте
        if (line == toLine && column == toColumn) {
            return false;
        }

        // Ферзь ходит как ладья или слон
        if (line == toLine || column == toColumn) {
            // Движение как у ладьи
            return canMoveAsRook(chessBoard, line, column, toLine, toColumn);
        } else if (Math.abs(line - toLine) == Math.abs(column - toColumn)) {
            // Движение как у слона
            return canMoveAsBishop(chessBoard, line, column, toLine, toColumn);
        }

        return false;
    }

    // Вспомогательный метод для движения как ладья
    private boolean canMoveAsRook(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (line == toLine) { // Движение по горизонтали
            int direction = (toColumn - column) > 0 ? 1 : -1;
            for (int col = column + direction; col != toColumn; col += direction) {
                if (chessBoard.board[line][col] != null) {
                    return false; // На пути есть фигура
                }
            }
        } else { // Движение по вертикали
            int direction = (toLine - line) > 0 ? 1 : -1;
            for (int row = line + direction; row != toLine; row += direction) {
                if (chessBoard.board[row][column] != null) {
                    return false; // На пути есть фигура
                }
            }
        }

        // Проверяем целевую клетку: она может быть пустой или занята фигурой другого цвета
        ChessPiece target = chessBoard.board[toLine][toColumn];
        return target == null || !target.getColor().equals(this.getColor());
    }

    // Вспомогательный метод для движения как слон
    private boolean canMoveAsBishop(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        int rowDirection = (toLine - line) > 0 ? 1 : -1;
        int colDirection = (toColumn - column) > 0 ? 1 : -1;

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
