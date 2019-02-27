package com.wictorsundstrom.mypackage;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.ArrayList;

/**
 * Program where you add what tickets you want to buy.
 *
 * @author  Wictor Sundström
 * @version 1.0
 * @since   2018-12-12
 */

public class Main extends Application {
    /**
     * ArrayList that accepts HBox which are each game on their own
     */
    private static ArrayList<HBox> listOfGames = new ArrayList<>();

    /**
     * Int that is a text but it need to be cleared from multiple methods
     */
    private static int amountOfGames;

    /**
     * Int that is a text but it need to be cleared from multiple methods
     */
    private static int sumOfGames;

    /**
     * Start where panes start building
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Java FX");
        primaryStage.setResizable(false);

        BorderPane groundPane = new BorderPane();
        groundPane.setPrefSize(800, 600);

        groundPane.setTop(topMargin());
        groundPane.setLeft(sideMargin());
        groundPane.setRight(sideMargin());
        groundPane.setCenter(gameSection());
        groundPane.setBottom(topMargin());

        Scene scene = new Scene(groundPane);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());


        primaryStage.setScene(scene);
        primaryStage.show();

    }

    /**
     * Main - Launches the program
     * @param args parameter you have to use ,
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Side margin for borderPane (Since there is no margin in javafx)
     * @return returns the HBox with width 20
     */
    private static HBox sideMargin() {
        HBox left = new HBox();
        left.setPrefWidth(20);

        return left;
    }

    /**
     * Top margin for borderPane (Since there is no margin in javafx)
     * @return returns the VBox with height 20
     */
    private static VBox topMargin() {
        VBox top = new VBox();
        top.setPrefHeight(20);

        return top;
    }


    /**
     * Core of the program where games are added and button controls are checked.
     * @return returns a ScrollPane that is then placed on top of the Center part of BorderPane
     */
    private static ScrollPane gameSection() {
        ScrollPane gameSelection = new ScrollPane();

        gameSelection.getStyleClass().add("mainContainer");

        GridPane startWindow = new GridPane();
        startWindow.getStyleClass().add("mainContent");

        gameSelection.setFitToWidth(true);
        gameSelection.setContent(startWindow);




      ArrayList<String> teams = new ArrayList<>();
            teams.add("Liverpool");
            teams.add("Arsenal");
            teams.add("Chelsea");
            teams.add("Man City");

        Text items = new Text();
        Text sumofItems = new Text();

        for (int x = 1; x < teams.size(); x++) {
            for (int y = x - 1; y >= 0; y--) {
                HBox game = new HBox();
                game.getStyleClass().add("gameTable");
                game.setPrefWidth(800);
                game.setPrefHeight(80);

                Text games = new Text(teams.get(x) + " vs " + teams.get(y));
                games.setId("teams");

                Text amountText = new Text("Antal biljetter");
                amountText.setId("amountText");

                Text price = new Text();
                price.setId("price");

                int randomPrice = (int) (Math.floor(Math.random() * 4000) + 1000);
                price.setText(String.valueOf(randomPrice) + " kr/ biljett");

                ComboBox<Integer> amount = new ComboBox<>();
                amount.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
                amount.getSelectionModel().selectFirst();
                amount.setId("amountCombo");

                Button addMatch = new Button("Lägg till");
                addMatch.setId("addToCart");

                game.getChildren().addAll(games, amountText, amount, price, addMatch);
                listOfGames.add(game);

                addMatch.setOnAction(ae -> {
                    sumOfGames += amount.getValue() * randomPrice;
                    amountOfGames += amount.getValue();

                   items.setText(amountOfGames + "x ");
                   sumofItems.setText(Integer.toString(sumOfGames) + " kr/ biljett");

                    });
                }
            }

            for(int i = 0; i < listOfGames.size(); i++) {
            startWindow.add(listOfGames.get(i), 0, i);
            }


        GridPane grid = new GridPane();
        Text title = new Text("Kassa:");
        title.setId("title");
        items.setId("orderStatus");


        Button clearCart = new Button("Töm");
        clearCart.getStyleClass().add("clearCart");

        clearCart.setOnAction(ae -> {
            sumOfGames = 0;
            amountOfGames = 0;
            items.setText("");
            sumofItems.setText("");
                });

        Button sendOrder = new Button("Beställ");
        sendOrder.getStyleClass().add("sendOrder");

        sendOrder.setOnAction(ae -> {
            sumOfGames = 0;
            amountOfGames = 0;
            sumofItems.setText("");
            items.setText("Din order är beställd");
        });

        grid.setId("grid");

        grid.add(title,0,0);

        grid.add(items,0,1);
        grid.add(sumofItems,1, 1);

        grid.add(clearCart,3,0);
        grid.add(sendOrder,4,0);

        startWindow.add(grid, 0, listOfGames.size()+1);



            for(int i = 0; i < listOfGames.size(); i++) {

                if (i % 2 == 0) {
                    listOfGames.get(i).setId("gameEven");
                }
                else
                {
                    listOfGames.get(i).setId("gameOdd");
                }
            }


        return gameSelection;
    }
}

