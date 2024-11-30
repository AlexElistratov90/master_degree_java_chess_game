package com.chessgame.mod2_oop_final_task_chess_game_elistratovaa;

public class King extends ChessPiece {

    // Конструктор принимает цвет фигуры
    public King(String color) {
        super(color);
    }

    // Метод для получения символа фигуры
    @Override
    public String getSymbol() {
        return "K";
    }

    // Метод для проверки возможности хода короля
    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // Проверяем, находятся ли начальная и конечная позиции на доске
        if (!isOnBoard(line, column) || !isOnBoard(toLine, toColumn)) {
            return false;
        }

        // Король не может остаться на месте
        if (line == toLine && column == toColumn) {
            return false;
        }

        // Король может ходить на одну клетку в любом направлении
        int rowDiff = Math.abs(line - toLine);
        int colDiff = Math.abs(column - toColumn);

        if (rowDiff > 1 || colDiff > 1) {
            return false;
        }

        // Проверяем, не находится ли конечная позиция под атакой
        if (isUnderAttack(chessBoard, toLine, toColumn)) {
            return false;
        }

        // Проверяем целевую клетку: она может быть пустой или занята фигурой другого цвета
        ChessPiece target = chessBoard.board[toLine][toColumn];
        return target == null || !target.getColor().equals(this.getColor());
    }

    // Метод для проверки, находится ли поле под атакой
    public boolean isUnderAttack(ChessBoard chessBoard, int line, int column) {
        if (!isOnBoard(line, column)) {
            return false;
        }

        // Проверяем все клетки доски
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ChessPiece piece = chessBoard.board[i][j];
                if (piece != null && !piece.getColor().equals(this.getColor())) {
                    // Если фигура противоположного цвета может атаковать эту клетку
                    if (piece.canMoveToPosition(chessBoard, i, j, line, column)) {
                        return true;
                    }
                }
            }
        }

        return false; // Поле не под атакой
    }

    // Метод для проверки, находится ли позиция на доске
    private boolean isOnBoard(int line, int column) {
        return line >= 0 && line < 8 && column >= 0 && column < 8;
    }
}
