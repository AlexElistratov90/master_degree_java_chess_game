package com.chessgame.mod2_oop_final_task_chess_game_elistratovaa;

public class ChessBoard {
    public ChessPiece[][] board = new ChessPiece[8][8]; // creating a field for game
    String nowPlayer;

    public ChessBoard(String nowPlayer) {
        this.nowPlayer = nowPlayer;
    }

    public String nowPlayerColor() {
        return this.nowPlayer;
    }

    public boolean moveToPosition(int startLine, int startColumn, int endLine, int endColumn) {
        if (checkPos(startLine) && checkPos(startColumn)) {
            ChessPiece piece = board[startLine][startColumn];

            if (piece == null || !nowPlayer.equals(piece.getColor())) {
                return false; // Некорректный ход
            }

            if (piece.canMoveToPosition(this, startLine, startColumn, endLine, endColumn)) {
                // Передвигаем фигуру
                board[endLine][endColumn] = piece;
                board[startLine][startColumn] = null;

                // Обновляем состояние check для королей и ладей
                if (piece instanceof King || piece instanceof Rook) {
                    piece.setMoved();
                }

                // Меняем игрока
                nowPlayer = nowPlayer.equals("White") ? "Black" : "White";
                return true;
            }
        }
        return false;
    }

    public boolean castling(int direction) {
        int line = nowPlayer.equals("White") ? 0 : 7; // Белые на 0 линии, чёрные на 7 линии

        // Рокировка влево (к столбцу 0)
        if (direction == 0 && board[line][4] instanceof King && board[line][0] instanceof Rook) {
            King king = (King) board[line][4];
            Rook rook = (Rook) board[line][0];

            if (king.isFirstMove() && rook.isFirstMove()) {
                if (board[line][1] == null && board[line][2] == null && board[line][3] == null) {
                    if (!king.isUnderAttack(this, line, 4) &&
                            !king.isUnderAttack(this, line, 3) &&
                            !king.isUnderAttack(this, line, 2)) {

                        // Выполняем рокировку
                        board[line][2] = king;
                        board[line][4] = null;
                        board[line][3] = rook;
                        board[line][0] = null;

                        // Обновляем состояние check
                        king.setMoved();
                        rook.setMoved();

                        nowPlayer = nowPlayer.equals("White") ? "Black" : "White";
                        return true;
                    }
                }
            }
        }

        // Рокировка вправо (к столбцу 7)
        if (direction == 7 && board[line][4] instanceof King && board[line][7] instanceof Rook) {
            King king = (King) board[line][4];
            Rook rook = (Rook) board[line][7];

            if (king.isFirstMove() && rook.isFirstMove()) {
                if (board[line][5] == null && board[line][6] == null) {
                    if (!king.isUnderAttack(this, line, 4) &&
                            !king.isUnderAttack(this, line, 5) &&
                            !king.isUnderAttack(this, line, 6)) {

                        // Выполняем рокировку
                        board[line][6] = king;
                        board[line][4] = null;
                        board[line][5] = rook;
                        board[line][7] = null;

                        // Обновляем состояние check
                        king.setMoved();
                        rook.setMoved();

                        nowPlayer = nowPlayer.equals("White") ? "Black" : "White";
                        return true;
                    }
                }
            }
        }

        return false; // Рокировка невозможна
    }

    public void printBoard() {  //print board in console
        System.out.println("Turn " + nowPlayer);
        System.out.println();
        System.out.println("Player 2(Black)");
        System.out.println();
        System.out.println("\t0\t1\t2\t3\t4\t5\t6\t7");

        for (int i = 7; i > -1; i--) {
            System.out.print(i + "\t");
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    System.out.print(".." + "\t");
                } else {
                    System.out.print(board[i][j].getSymbol() + board[i][j].getColor().substring(0, 1).toLowerCase() + "\t");
                }
            }
            System.out.println();
            System.out.println();
        }
        System.out.println("Player 1(White)");
    }

    public boolean checkPos(int pos) {
        return pos >= 0 && pos <= 7;
    }
}