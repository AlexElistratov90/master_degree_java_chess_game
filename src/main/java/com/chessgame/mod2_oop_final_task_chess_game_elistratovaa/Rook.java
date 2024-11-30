package com.chessgame.mod2_oop_final_task_chess_game_elistratovaa;

public class Rook extends ChessPiece {

    // Конструктор принимает цвет фигуры
    public Rook(String color) {
        super(color);
    }

    // Метод для получения символа фигуры
    @Override
    public String getSymbol() {
        return "R";
    }

    // Метод для проверки возможности хода ладьи
    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // Проверяем, находятся ли начальная и конечная позиции на доске
        if (!isOnBoard(line, column) || !isOnBoard(toLine, toColumn)) {
            return false;
        }

        // Ладья не может остаться на месте
        if (line == toLine && column == toColumn) {
            return false;
        }

        // Ладья может ходить только по прямой линии
        if (line != toLine && column != toColumn) {
            return false;
        }

        // Проверяем путь между начальной и конечной точками
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

    // Метод для проверки, находится ли позиция на доске
    private boolean isOnBoard(int line, int column) {
        return line >= 0 && line < 8 && column >= 0 && column < 8;
    }
}
