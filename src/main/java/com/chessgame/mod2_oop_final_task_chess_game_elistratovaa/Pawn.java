package com.chessgame.mod2_oop_final_task_chess_game_elistratovaa;

public class Pawn extends ChessPiece {

    // Конструктор принимает цвет фигуры
    public Pawn(String color) {
        super(color);
    }

    // Метод для получения символа фигуры
    @Override
    public String getSymbol() {
        return "P";
    }

    // Метод для проверки возможности хода пешки
    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // Проверяем, находятся ли начальная и конечная позиции на доске
        if (!isOnBoard(line, column) || !isOnBoard(toLine, toColumn)) {
            return false;
        }

        // Пешка не может остаться на месте
        if (line == toLine && column == toColumn) {
            return false;
        }

        int direction = getColor().equals("White") ? 1 : -1;

        // Движение вперёд
        if (column == toColumn) {
            if (toLine - line == direction && chessBoard.board[toLine][toColumn] == null) {
                return true; // Пешка движется на одну клетку вперёд
            }
            if (toLine - line == 2 * direction && chessBoard.board[toLine][toColumn] == null && chessBoard.board[line + direction][column] == null) {
                return line == (getColor().equals("White") ? 1 : 6); // Пешка на стартовой позиции
            }
        }

        // Съедание фигуры по диагонали
        if (Math.abs(column - toColumn) == 1 && toLine - line == direction) {
            ChessPiece target = chessBoard.board[toLine][toColumn];
            return target != null && !target.getColor().equals(this.getColor());
        }

        return false; // Все остальные случаи недопустимы
    }

    // Метод для проверки, находится ли позиция на доске
    private boolean isOnBoard(int line, int column) {
        return line >= 0 && line < 8 && column >= 0 && column < 8;
    }
}