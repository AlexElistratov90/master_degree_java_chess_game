module com.chessgame.mod2_oop_final_task_chess_game_elistratovaa {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.chessgame.mod2_oop_final_task_chess_game_elistratovaa to javafx.fxml;
    exports com.chessgame.mod2_oop_final_task_chess_game_elistratovaa;
}