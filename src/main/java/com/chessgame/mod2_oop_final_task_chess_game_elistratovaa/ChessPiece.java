package com.chessgame.mod2_oop_final_task_chess_game_elistratovaa;

public abstract class ChessPiece {
    private String color; // Цвет фигуры
    private boolean check = true; // По умолчанию true, понадобится позже

    // Конструктор
    public ChessPiece(String color) {
        this.color = color;
    }

    // Метод для получения цвета фигуры
    public String getColor() {
        return color;
    }

    // Метод для получения состояния check
    public boolean isFirstMove() {
        return check;
    }

    // Метод для обновления состояния check
    public void setMoved() {
        this.check = false;
    }

    // Абстрактный метод для проверки возможности хода
    public abstract boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn);

    // Абстрактный метод для получения символа фигуры
    public abstract String getSymbol();
}
