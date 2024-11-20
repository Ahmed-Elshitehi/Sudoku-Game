package sudoku.sudoku_game;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Controller implements Initializable {
    @FXML Button button_one;
    @FXML Button button_two;
    @FXML Button button_three;
    @FXML Button button_four;
    @FXML Button button_five;
    @FXML Button button_six;
    @FXML Button button_seven;
    @FXML Button button_eight;
    @FXML Button button_nine;
    @FXML Canvas canvas;
    GameBoard gameboard;
    private int player_selected_row = -1;
    private int player_selected_col = -1;
    Color line_color = Color.WHITE;
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        System.out.println("Start");
        gameboard = new GameBoard(30);
        GraphicsContext context = canvas.getGraphicsContext2D();
        drawOnCanvas(context);
    }
    private void newGame() {
        System.out.println("New Game");
        gameboard.newValues();
        player_selected_row = player_selected_col = -1;
        drawOnCanvas(canvas.getGraphicsContext2D());
    }

    private void check(){
        System.out.println("Check");
        if (gameboard.check()) {
            System.out.println("True");
            line_color = Color.GREEN;
        } else {
            System.out.println("False");
            line_color = Color.RED;
        }
        player_selected_row = player_selected_col = -1;
        drawOnCanvas(canvas.getGraphicsContext2D());
        line_color = Color.WHITE;
    }
    private void drawOnCanvas(GraphicsContext context) {
        int initial[][] = gameboard.getInitial();
        int[][] player = gameboard.getPlayer();
        context.clearRect(0, 0, 450, 450);
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                int position_y = row * 50 + 2;
                int position_x = col * 50 + 2;
                int width = 46;
                if (initial[row][col] != 0) {
                    context.setFill(Color.rgb(175, 175, 175));
                } else if (player[row][col] != 0) {
                    context.setFill(Color.rgb(225, 225, 125));
                } else {
                    context.setFill(Color.rgb(225, 225, 225));
                }
                context.fillRoundRect(position_x, position_y, width, width, 10, 10);
            }
        }

        context.setStroke(Color.rgb(193, 87, 87));
        context.setLineWidth(3);
        if (player_selected_col > -1) {
            context.strokeRoundRect(player_selected_col * 50 + 2, player_selected_row * 50 + 2, 46, 46, 10, 10);
        }

        // for loop is the same as before
        for(int row = 0; row<9; row++) {
            for(int col = 0; col<9; col++) {

                int position_y = row * 50 + 35;
                int position_x = col * 50 + 15;

                context.setFill(Color.BLACK);
                context.setFont(new Font(30));

                if(player[row][col]!=0) {
                    String txt = Integer.toString(player[row][col]);
                    context.fillText(txt, position_x, position_y);
                }
            }
        }
        context.setStroke(line_color);
        for (int i = 1; i <= 2; i++) {
            context.strokeLine(i * 150, 0, i * 150, 450);
            context.strokeLine(0, i * 150, 450, i * 150);
        }
    }

    private void canvasMouseClicked() {
        canvas.setOnMouseClicked(e->{
            int mouse_x = (int) e.getX();
            int mouse_y = (int) e.getY();
            player_selected_row = (int) (mouse_y / 50); // update player selected row
            player_selected_col = (int) (mouse_x / 50); // update player selected column
            drawOnCanvas(canvas.getGraphicsContext2D());
        });
    }

    private void reset() {
        System.out.println("Reset");
        gameboard.resetPlayer();
        player_selected_row = player_selected_col = -1;
        drawOnCanvas(canvas.getGraphicsContext2D());
    }

    private void buttonOnePressed() {
        System.out.println(1);
        if (gameboard.getInitial()[player_selected_row][player_selected_col] != 0) {
            return;
        }
        gameboard.modifyPlayer(1, player_selected_row, player_selected_col);
        drawOnCanvas(canvas.getGraphicsContext2D());
    }

    private void buttonTwoPressed() {
        System.out.println(2);
        if (gameboard.getInitial()[player_selected_row][player_selected_col] != 0) {
            return;
        }
        gameboard.modifyPlayer(2, player_selected_row, player_selected_col);
        drawOnCanvas(canvas.getGraphicsContext2D());
    }

    private void buttonThreePressed() {
        System.out.println(3);
        if (gameboard.getInitial()[player_selected_row][player_selected_col] != 0) {
            return;
        }
        gameboard.modifyPlayer(3, player_selected_row, player_selected_col);
        drawOnCanvas(canvas.getGraphicsContext2D());
    }

    private void buttonFourPressed() {
        System.out.println(4);
        if (gameboard.getInitial()[player_selected_row][player_selected_col] != 0) {
            return;
        }
        gameboard.modifyPlayer(4, player_selected_row, player_selected_col);
        drawOnCanvas(canvas.getGraphicsContext2D());
    }

    private void buttonFivePressed() {
        System.out.println(5);
        if (gameboard.getInitial()[player_selected_row][player_selected_col] != 0) {
            return;
        }
        gameboard.modifyPlayer(5, player_selected_row, player_selected_col);
        drawOnCanvas(canvas.getGraphicsContext2D());
    }

    private void buttonSixPressed() {
        System.out.println(6);
        if (gameboard.getInitial()[player_selected_row][player_selected_col] != 0) {
            return;
        }
        gameboard.modifyPlayer(6, player_selected_row, player_selected_col);
        drawOnCanvas(canvas.getGraphicsContext2D());
    }

    private void buttonSevenPressed() {
        System.out.println(7);
        if (gameboard.getInitial()[player_selected_row][player_selected_col] != 0) {
            return;
        }
        gameboard.modifyPlayer(7, player_selected_row, player_selected_col);
        drawOnCanvas(canvas.getGraphicsContext2D());
    }

    private void buttonEightPressed() {
        System.out.println(8);
        if (gameboard.getInitial()[player_selected_row][player_selected_col] != 0) {
            return;
        }
        gameboard.modifyPlayer(8, player_selected_row, player_selected_col);
        drawOnCanvas(canvas.getGraphicsContext2D());
    }

    private void buttonNinePressed() {
        System.out.println(9);
        if (gameboard.getInitial()[player_selected_row][player_selected_col] != 0) {
            return;
        }
        gameboard.modifyPlayer(9, player_selected_row, player_selected_col);
        drawOnCanvas(canvas.getGraphicsContext2D());
    }
}