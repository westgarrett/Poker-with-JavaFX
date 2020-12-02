
/*
 * Garrett West
 * Homework 4 - Poker 1
 * 10/04/2020
 * COSC 1174
 * This is a program that creates a deck of cards and "deals" five of them to a hand.
 * This program takes no user input, it automatically generates a Deck of cards and outputs them to a window.
 */

// Imports for Java utility
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;
import java.lang.Thread;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
// Imports for JavaFX functionality
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import javafx.scene.shape.Path;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.VLineTo;
import javafx.scene.transform.Rotate;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Bounds;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.RadioButton;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.animation.PauseTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;

// Main class for Poker game
public class PokerMain extends Application {
    // Start boolean to keep track of if the application is starting or already started
    private boolean start;
    // Number of cards in the hand
    public int numCardsInHand = 5;
    // Create a Deck object of Card objects
    public Deck pokerDeck;
    // Deal 5 Card objects to the hand (Deck object) from the pokerDeck object
    public Deck pokerHand;
    // Application Scene object
    public Scene scene;
    // The Application Stage object
    public Stage appStage;
    // Boolean to lock the DRAW button if no cards are held
    public boolean cardsAreHeld = false;
    // Create the FlowPane to hold the start instructions
    public Pane startPane;
    // Create the FlowPane to hold the Poker hand
    public Pane handPane;
    // Create the FlowPane to hold the HOLD buttons
    public Pane holdButtonPane;
    // Create the HBox to hold the BET buttons
    public Pane betButtonPane;
    // Create the HBox to hold the DRAW button
    public Pane drawButtonPane;
    // Create the HBox to hold the DEAL button
    public Pane dealButtonPane;
    // Create the root GridPane for the scene
    public GridPane gridPane;
    // Starting dollar amount
    public int playerMoney = 200;
    // The bet value
    private int betValue;

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        startStage(primaryStage);
    }

    // Start the application
    public void startStage(Stage primaryStage) {
        // Set start to true
        start = true;
        // Initialize a pokerDeck
        pokerDeck = new Deck();
        // Initialize a pokerHand
        pokerHand = new Deck(numCardsInHand, pokerDeck);

        // The Application Stage object
        appStage = primaryStage;
        // Create the FlowPane to show the start instructions
        startPane = createStartPane();
        // Create the HBox to hold the BET RadioButtons
        betButtonPane = createBetButtonPane();
        // Create the FlowPane to hold the HOLD buttons
        holdButtonPane = createHoldButtonPane();
        // Create the HBox to hold the DRAW button
        drawButtonPane = createDrawButtonPane();
        // Create the HBox to hold the DEAL button
        dealButtonPane = createDealButtonPane();

        // Create the root GridPane for the scene
        GridPane gridPane = createGridPane(startPane, holdButtonPane, betButtonPane, drawButtonPane, dealButtonPane);
        // Create the scene
        Scene scene = new Scene(gridPane);

        // Set the stage title
        appStage.setTitle("Poker Hand");
        // Place the scene in the stage
        appStage.setScene(scene);
        // Display the stage. This is what updates the Node positions into the GridPane object.
        appStage.show();
    }

    public void startGame(Stage primaryStage) {
        // The game has started.
        start = false;
        // The Application Stage object
        appStage = primaryStage;
        // Create the FlowPane to hold the Poker hand
        handPane = createHandPane(pokerHand);
        // Create the HBox to hold the BET RadioButtons
        betButtonPane = createBetButtonPane();
        // Create the FlowPane to hold the HOLD buttons
        holdButtonPane = createHoldButtonPane();
        // Create the HBox to hold the DRAW button
        drawButtonPane = createDrawButtonPane();
        // Create the HBox to hold the DEAL button
        dealButtonPane = createDealButtonPane();

        // Create the root GridPane for the scene
        GridPane gridPane = createGridPane(handPane, holdButtonPane, betButtonPane, drawButtonPane, dealButtonPane);
        // Create the scene
        Scene scene = new Scene(gridPane);

        // Set the stage title
        appStage.setTitle("Poker Hand");
        // Place the scene in the stage
        appStage.setScene(scene);
        // Display the stage. This is what updates the Node positions into the GridPane object.
        appStage.show();

        // Animations
        // Sends the first HBox containing the Cards to its animation loop
        Node handHBox = gridPane.getChildren().get(1);
        ArrayList<PathTransition> pathTransitionArrayList = animationCardDownLoop(handHBox);
        for (int i = 0; i < pathTransitionArrayList.size(); i++) {
            pathTransitionArrayList.get(i).play();
        }
    }

    // Starts the stage after the DRAW button is pressed.
     public void startDraw(Stage primaryStage) {
         // Create the FlowPane to hold the HOLD buttons
         holdButtonPane = createHoldButtonPane();
         // Create the HBox to hold the BET RadioButtons
         betButtonPane = createBetButtonPane();
         // Create the HBox to hold the DRAW button
         drawButtonPane = createDrawButtonPane();
         // Create the HBox to hold the DEAL button
         dealButtonPane = createDealButtonPane();
         // Create the root GridPane for the scene
         GridPane gridPane = createGridPane(handPane, holdButtonPane, betButtonPane, drawButtonPane, dealButtonPane);
         // Create the scene
         Scene scene = new Scene(gridPane);

         // Set the stage title
         primaryStage.setTitle("Poker Hand");
         // Place the scene in the stage
         primaryStage.setScene(scene);
         // Display the stage. This is what updates the Node positions into the GridPane object.
         primaryStage.show();

         Node handHBox = gridPane.getChildren().get(1);
         animationFlipCardOver(handHBox);
     };

    // Build the scene GridPane
    public GridPane createGridPane(Pane handPane, Pane holdButtonPane, Pane betButtonPane, Pane drawButtonPane, Pane dealButtonPane) {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.TOP_LEFT);
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(0);
        gridPane.setVgap(0);
        gridPane.setGridLinesVisible(true);
        gridPane.setStyle("-fx-background-color: #248f24");
        gridPane.add(handPane, 0, 0);
        gridPane.add(holdButtonPane, 0, 1);
        gridPane.add(betButtonPane, 0, 2);
        gridPane.add(drawButtonPane, 0, 3);
        gridPane.add(dealButtonPane, 0, 4);
        return gridPane;
    }

    // Create the FlowPane that holds the start instructions
    public Pane createStartPane() {
        // Create a pane to hold the start instructions
        HBox startPane = new HBox(20);
        startPane.setAlignment(Pos.CENTER);
        startPane.setPadding(new Insets(20));
        String startText = "\tPlace your bet, \n then press DEAL to start!";
        Text textBox = new Text();
        textBox.setText(startText);
        textBox.setFont(Font.font("times new roman", FontWeight.BOLD, FontPosture.REGULAR, 25));
        startPane.getChildren().add(textBox);
        return startPane;
    }

    // Create the FlowPane that holds the hand
    public Pane createHandPane(Deck hand) {
        // Create a pane to hold the card hand
        HBox handPane = new HBox(20);
        handPane.setPadding(new Insets(20));

        // Add the cards from the poker Hand to the FlowPane
        for (int i = 0; i < numCardsInHand; i++) {
            // Grab the card from the poker hand
            Card cardView = hand.getCardArray().get(i);
            // Preserve the size ratio of the card
            cardView.setPreserveRatio(true);
            // Make the cards a set size
            cardView.setFitHeight(100);
            // Add the card ImageViews to the FlowPane
            handPane.getChildren().add(cardView);
        }

        return handPane;
    }

    // Create the HBox that holds the HOLD toggle buttons
    public Pane createHoldButtonPane() {
        // Create a pane to hold the toggle buttons
        HBox holdButtonPane = new HBox(40);
        holdButtonPane.setPadding(new Insets(10, 10, 10, 10));
        holdButtonPane.setAlignment(Pos.CENTER);
        // EventHandler for what happens when the HOLD button is pressed.
        for (int i = 0; i < numCardsInHand; i++) {
            ToggleButton tButton = new ToggleButton("HOLD");
            // Initialize the button as disabled if start == true
            if (this.start) {
                tButton.setDisable(true);
            }
            String iString = Integer.toString(i);
            tButton.setUserData(iString);
            tButton.setOnAction(e -> {
                // Indicates the index of the pokerHand that is being saved.
                String userData = tButton.getUserData().toString();
                // Integer value of userData index
                int userDataValue = Integer.valueOf(userData);
                // If the toggle button is selected, move that card from the pokerHand.cardArray to
                //  the pokerHand.saveArray
                pokerHand.getCardArray().get(userDataValue).toggleHeldCondition();

                if (tButton.selectedProperty().get()) {
                    System.out.println("Toggle Button " + tButton.getUserData() + " is selected ");
                    // Replaces the item located at index (userDataValue) in the pokerHand.saveArray with the card drawn
                    //  at value (userDataValue) in the pokerHand.cardArray
                    pokerHand.swapArrayListValues(userDataValue);
                } else {
                    System.out.println("Toggle Button " + tButton.getUserData() + " is selected ");
                    // Replaces the item located at index (userDataValue) in the pokerHand.saveArray with the card drawn
                    //  at value (userDataValue) in the pokerHand.cardArray
                    pokerHand.swapArrayListValues(userDataValue);
                }
            });
            // Add the buttons to the FlowPane
            holdButtonPane.getChildren().add(tButton);
        }

        // return the FlowPane
        return holdButtonPane;
    }

    // Reset the HOLD button selectedProperty() values
    public void resetHoldbuttons() {
        for (Node node : this.holdButtonPane.getChildren()) {
            ToggleButton button = (ToggleButton) node;
            button.setSelected(false);
        }
    }

    // Create the Pane to hold BET RadioButtons
    public Pane createBetButtonPane() {
        HBox betButtonPane = new HBox();
        betButtonPane.setPadding(new Insets(10));
        betButtonPane.setAlignment(Pos.CENTER);
        ToggleGroup betGroup = new ToggleGroup();
        // Bet $1
        RadioButton betButtonOne = new RadioButton("BET $1  ");
        betButtonOne.setToggleGroup(betGroup);
        betButtonOne.setUserData("1");
        betButtonPane.getChildren().add(betButtonOne);
        // Bet $10
        RadioButton betButtonTen = new RadioButton("BET $10 ");
        betButtonTen.setToggleGroup(betGroup);
        betButtonTen.setUserData("10");
        betButtonPane.getChildren().add(betButtonTen);
        // Bet $100
        RadioButton betButtonHundred = new RadioButton("BET $100");
        betButtonHundred.setToggleGroup(betGroup);
        betButtonHundred.setUserData("100");
        betButtonPane.getChildren().add(betButtonHundred);

        // Create an event that enables the drawButton in the drawButtonPane when a bet is placed
        betGroup.selectedToggleProperty().addListener(e -> {
            // Enable the deal button after placing a bet
            for (Node node : dealButtonPane.getChildren()) {
                Button dealButton = (Button) node;
                dealButton.setDisable(false);
            }
        });

        // If start == false, disable the buttons
        if (start) {
        } else {
            betButtonOne.setDisable(true);
            betButtonTen.setDisable(true);
            betButtonHundred.setDisable(true);
        }
        // Return the HBox
        return betButtonPane;
    }

    // Create the HBox that holds the DRAW button
    public Pane createDrawButtonPane() {
        HBox drawButtonPane = new HBox();
        drawButtonPane.setPadding(new Insets(10));
        drawButtonPane.setAlignment(Pos.CENTER);
        Button drawButton = new Button("DRAW");
        // Initialize the button as disabled if start == true
        if (this.start) {
            drawButton.setDisable(true);
        }
        // EventHandler for what happens when the DRAW button is pressed.
        drawButton.setOnAction(e -> {
            System.out.println("Clicking DRAW");
            // Update the Application Stage object after Draw is pressed
            startDraw(appStage);
        });
        drawButtonPane.getChildren().add(drawButton);

        // Return the drawButtonPane
        return drawButtonPane;
    }

    public Pane createDealButtonPane() {
        HBox dealButtonPane = new HBox();
        dealButtonPane.setPadding(new Insets(10));
        dealButtonPane.setAlignment(Pos.CENTER);
        Button dealButton = new Button("DEAL");
        // Initialize the button as disabled. Enabled after bet is placed.
        dealButton.setDisable(true);
        // EventHandler for what happens when the DEAL button is pressed.
        dealButton.setOnAction(e -> {
            // The bet value
            int betValue = 0;
            // Iterate through the BET radio buttons
            for (Node node : betButtonPane.getChildren()) {
                RadioButton button = (RadioButton) node;
                String buttonUserData = button.getUserData().toString();
                // Get the button BET value from the userData property of the RadioButton
                this.betValue = Integer.valueOf(buttonUserData);
                if (button.isSelected()) {
                    playerMoney = playerMoney - this.betValue;
                    break;
                }
            }
            // Enable the draw button after the deal button is pressed
            for (Node node : drawButtonPane.getChildren()) {
                Button drawButton = (Button) node;
                drawButton.setDisable(false);
            }
            System.out.println("The player now has " + playerMoney + " after betting " + this.betValue);

            System.out.println("Clicking DEAL");
            // Update the Application Stage object
            startGame(appStage);
        });
        dealButtonPane.getChildren().add(dealButton);

        // Return the drawButtonPane
        return dealButtonPane;
    }

    // Moves cards into the scene. Conditionally keeps unheld cards from moving.
    public ArrayList<PathTransition> animationCardDownLoop(Node HBoxNode) {
        HBox node = (HBox) HBoxNode;
        ArrayList<PathTransition> pathTransitionArrayList = new ArrayList<PathTransition>();
        for (int i = 0; i < node.getChildren().size(); i++) {
            Card handCard = (Card) node.getChildren().get(i);
            if (handCard.isHeld()) {
                handCard.setHeld(false);
            } else {
                Path pathDown = new Path();
                // Get location of the node in the Scene
                // Has getMinX(), Y(), getMaxX(), Y(), getWidth(), getHeight()
                Bounds layoutBounds = handCard.getBoundsInLocal();
                pathDown.getElements().add(new MoveTo(layoutBounds.getCenterX(), layoutBounds.getCenterY() - 250));
                pathDown.getElements().add(new VLineTo(layoutBounds.getCenterY()));
                PathTransition pathTransition = new PathTransition();
                pathTransition.setDuration(Duration.millis(1000));
                pathTransition.setNode(handCard);
                pathTransition.setPath(pathDown);
                pathTransition.setCycleCount(1);
                pathTransitionArrayList.add(pathTransition);
            }
        }
        return pathTransitionArrayList;
    }

    // Flips unheld cards over onto their "backs"
    public void animationFlipCardOver(Node HBoxNode) {
        HBox node = (HBox) HBoxNode;
        final HBox saveNode = (HBox) HBoxNode;
        for (int i = 0; i < node.getChildren().size(); i++) {
            Card handCard = (Card) node.getChildren().get(i);

            if (handCard.isHeld()) {
            } else {
                // Rotates the front of the card to 90 degrees
                RotateTransition rotateAnimation = new RotateTransition(Duration.millis(750), handCard);
                rotateAnimation.setAxis(Rotate.Y_AXIS);
                rotateAnimation.setFromAngle(0);
                rotateAnimation.setToAngle(90);
                rotateAnimation.setInterpolator(Interpolator.LINEAR);

                Card cardBack = new Card();
                // Preserve the size ratio of the card
                cardBack.setPreserveRatio(true);
                // Make the cards a set size
                cardBack.setFitHeight(100);

                // Rotates to the card "back" by starting when the front is rotated to 90 degrees.
                RotateTransition rotateAnimationBack = new RotateTransition(Duration.millis(750), cardBack);
                rotateAnimationBack.setAxis(Rotate.Y_AXIS);
                rotateAnimationBack.setFromAngle(90);
                rotateAnimationBack.setToAngle(180);
                rotateAnimationBack.setInterpolator(Interpolator.LINEAR);

                final int k = i;
                final HBox n = node;
                rotateAnimation.setOnFinished( e -> {
                    n.getChildren().set(k, cardBack);
                });
                // Runs the rotateAnimation, then runs the rotateAnimationBack to give the illusion of a flip
                SequentialTransition paraT = new SequentialTransition(rotateAnimation, rotateAnimationBack);
                paraT.setOnFinished(e -> {
                    // Slides the cards down out of the scene after being flipped over.
                    animationDiscardCards(n);
                });

                // Completes the card rotating animation
                RotateTransition rotateAnimationComplete = new RotateTransition(Duration.millis(750), handCard);
                rotateAnimationComplete.setAxis(Rotate.Y_AXIS);
                rotateAnimationComplete.setFromAngle(90);
                rotateAnimationComplete.setToAngle(0);
                rotateAnimationComplete.setInterpolator(Interpolator.LINEAR);

                ParallelTransition paraT2 = new ParallelTransition(rotateAnimationComplete);

                SequentialTransition st = new SequentialTransition(paraT, paraT2);
                st.setOnFinished(e -> {
                    // Revert the node value back after the animation
                    n.getChildren().set(k, handCard);
                });
                // Play the SequentialTransition
                st.play();
            }
        }
        PauseTransition pauseTransition = new PauseTransition(Duration.millis(2250));
        pauseTransition.setOnFinished(e -> {
            // Put the cards from the pokerHand back in the pokerDeck. Effectively discards.
            pokerDeck.mergeDecks(pokerHand);
            // Set the pokerHand.cardArray to the pokerHand.saveArray
            pokerHand.setCardArray(pokerHand.getSaveArray());
            // Reinitialize the saveArray to a new ArrayList
            pokerHand.initSaveArray(5);
            // Draw the pokerHand.cardArray to full
            pokerHand.drawToFull(pokerDeck, 5);

            // Determine if the hand is a winner
            Winner winCondition = new Winner(pokerHand.getCardArray());
            if (winCondition.getIsWinner()) {
                // Double the player bet
                doublePlayerBet();
                System.out.println("The hand is a winner! Added 2 * $" + this.betValue + " to the player pocket.");
                start(appStage);

            } else {
                System.out.println("The hand is not a winner.");
                startGame(appStage);
            }
            System.out.println("The player now has $" + this.playerMoney);
        });
        pauseTransition.play();
    }

    // Doubles the betValue variable and adds it to playerMoney when called.
    private void doublePlayerBet() {
        this.playerMoney = this.playerMoney + (2 * this.betValue);
    }
    // Slides unheld cards out of the scene
    public void animationDiscardCards(Node HBoxNode) {
        HBox node = (HBox) HBoxNode;
        for (int i = 0; i < node.getChildren().size(); i++) {
            Card handCard = (Card) node.getChildren().get(i);

            if (handCard.isHeld()) {
            } else {
                Path pathDown = new Path();
                // Get location of the node in the Scene
                // Has getMinX(), Y(), getMaxX(), Y(), getWidth(), getHeight()
                Bounds layoutBounds = handCard.getBoundsInLocal();
                pathDown.getElements().add(new MoveTo(layoutBounds.getCenterX(), layoutBounds.getCenterY()));
                pathDown.getElements().add(new VLineTo(layoutBounds.getCenterY() + 400));
                PathTransition pathTransition = new PathTransition();
                pathTransition.setDuration(Duration.millis(1000));
                pathTransition.setNode(handCard);
                pathTransition.setPath(pathDown);
                pathTransition.setCycleCount(1);
                pathTransition.play();
            }
        }
    }

    // Launch the application
    public static void main(String[] args) {
        Application.launch(args);
    }
}

// Determine if a win condition is met. Pass in an ArrayList<Card>
class Winner {
    // ArrayList of card objects
    ArrayList<Card> cardArray;
    // ArrayList of cardRank
    ArrayList<Integer> cardRankArray = new ArrayList<Integer>();
    // ArrayList of cardSuit
    ArrayList<String> cardSuitArray = new ArrayList<String>();
    // Hashset to determine duplicates
    Set<String> rankSet;
    // HashMap to determine duplicates for cardRank
    Map<Integer, Integer> cardRankMap;
    // HashMap to determine duplicates for cardSuit
    Map<String, Integer> CardSuitMap;

    // Winner condition
    private boolean isWinner = false;
    // Possible ranks

    // Constructor
    Winner(ArrayList<Card> cardArrayList) {
        this.cardArray = cardArrayList;
        Iterator<Card> cardIterator = cardArrayList.iterator();
        // Populate the cardRankArray and cardSuitArray from the cardArray
        while (cardIterator.hasNext()) {
            Card card = cardIterator.next();
            cardRankArray.add(card.getCardRank());
            cardSuitArray.add(card.getCardSuit());
        }
        int numberOfSameCardsResult = numberOfSameCards(cardRankArray);

        if (royalFlush(cardRankArray)) {
            System.out.print("Royal flush");
            isWinner = true;
        } else if (straight(cardRankArray) && flush()) {
            System.out.print("Straight flush");
            isWinner = true;
        } else if (numberOfSameCardsResult == 4) {
            System.out.println("Four of a Kind");
            isWinner = true;
        } else if (numberOfSameCardsResult == 6) {
            System.out.println("Full House");
            isWinner = true;
        } else if (flush()) {
            System.out.println("Flush");
            isWinner = true;
        } else if (straight(cardRankArray)) {
            System.out.println("Straight");
            isWinner = true;
        } else if (numberOfSameCardsResult == 3) {
            System.out.println("Three of a Kind");
            isWinner = true;
        } else if (numberOfSameCardsResult == 5) {
            System.out.println("Two Pair");
            isWinner = true;
        } else if (numberOfSameCardsResult == 1) {
            System.out.println("One Pair");
            isWinner = true;
        }
        // Determines pair, two pair, full house, three of a kind, and four of a kind. Useful as a reference
//        switch (numberOfSameCards(cardRankArray)) {
//            case 0:
//                System.out.println("No matching cards");
//                break;
//            case 1:
//                System.out.println("One Pair");
//                isWinner = true;
//                break;
//            case 2:
//                System.out.println("Error");
//                break;
//            case 3:
//                System.out.println("Three of a Kind");
//                isWinner = true;
//                break;
//            case 4:
//                System.out.println("Four of a Kind");
//                isWinner = true;
//                break;
//            case 5:
//                System.out.println("Two Pair");
//                isWinner = true;
//                break;
//            case 6:
//                System.out.println("Full House");
//                isWinner = true;
//                break;
//        }
    }

    // Determines if the cardRankArray contains a pair, three of a kind, or four of a kind, or if it is a full house
    private int numberOfSameCards(ArrayList<Integer> rankArray) {
        // Hashmap for rank frequencyse
        Map<Integer, Integer> freq = new HashMap<>();
        for (Integer rank : rankArray) {
            freq.compute(rank, (key, value) -> {
                // If there is not a frequency set, set it to 1
                if (value == null) {
                    value = 1;
                } else {
                    // Increment the frequency
                    value++;
                }
                return value;
            });
        }

        // Determine what kind of hand the player has
        switch (freq.size()) {
            case 5:
                return 0; // No same cards
            case 4:
                return 1; // Pair
            case 3:
                for (int val : freq.values()) {
                    if (val == 3) {
                        return 3; // Three of a Kind
                    } else if (val == 2) {
                        return 5; // Two pair
                    }
                }
            case 2:
                for (int val : freq.values()) {
                    if (val == 3) {
                        return 6; // Full House
                    } else if (val == 4) {
                        return 4; // Four of a Kind
                    }
                }
        }
        return 2; // Error of some sort
    }

    // Determines if a hand is a straight
    private boolean straight(ArrayList<Integer> rankArray) {
        // Sort the given array into sequential order
        Collections.sort(rankArray);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                // if the next card value is one more than the current card value, continue
                if (rankArray.get(i) == rankArray.get(j) - 1) {
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    // Determines if a hand is a flush
    private boolean flush() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (cardSuitArray.get(i) == cardSuitArray.get(j)) {
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    // Determines if a hand is a straight and a flush
    private boolean straightFlush(ArrayList<Integer> rankArray) {
        if (this.flush() && this.straight(rankArray)) {
            return true;
        }
        return false;
    }

    // Determines if a hand is a straight, a flush, and contains 10, J, Q, K, A
    private boolean royalFlush(ArrayList<Integer> rankArray) {
        Collections.sort(rankArray);
        ArrayList<Integer> royalFlushArrayList = new ArrayList<Integer> () {{
            add(10);
            add(11);
            add(12);
            add(13);
            add(14);
        }};

        // If the hand is a flush (all same suit) and the ranks equal the flush ranks, return true
        if (this.flush() && royalFlushArrayList.equals(rankArray)) {
            return true;
        }
        return false;
    }

    // Returns if the hand is a winner
    public boolean getIsWinner() {
        return this.isWinner;
    }
}

// Creates a Deck object that consists of 52 distinct Card objects
class Deck {
    // private ArrayList to hold the Deck of Card objects
    private ArrayList<Card> cardArray = new ArrayList<Card>();
    // private ArrayList to save what cards in the Deck object need to be held
    private ArrayList<Card> saveArray;
    // Number of cards in a standard deck
    private int numCards = 5;

    // Default class constructor that creates a standard Deck size
    public Deck() {
        // Set the Deck to 54 cards (standard)
        this.setNumCards(54);
        // Set the Deck to an ArrayList of Card objects
        this.setDeck(this.numCards);
        // Shuffle the Deck
        this.shuffleCards();
    }

    // Optional constructor to set the size of the Deck()
    public Deck(int numCardsInDeck) {
        // Set the Deck to given number of cards into an ArrayList of Card objects
        this.setNumCards(numCardsInDeck);
        // Set the Deck to an ArrayList of Card objects
        this.setDeck(this.numCards);
        // Initialize the saveArray
        this.initSaveArray(5);
    }

    // Constructor to create another Deck of Cards of size num from a Deck existingDeck (drawn randomly)
    // Makes a "Hand"
    public Deck(int numCardsInDeck, Deck existingDeck) {
        this.setNumCards(numCardsInDeck);
        for (int i = 0; i < this.numCards; i++) {
            Card card = existingDeck.draw();
            this.cardArray.add(card);
        }
        // Initialize the saveArray
        this.initSaveArray(5);
    }

    // Builds an Arraylist of Card objects
    public void setDeck(int num) {
        for (int i = 1; i <= num; i++) {
            // Path to card in ./img/
            String cardPath = "./img/" + i + ".png";
            // New card object
            Card card = new Card(i, num, cardPath);
            // Add card object to ArrayList cards
            this.cardArray.add(card);
        }
    }

    // A private method to set the number of cards in the deck
    public void setNumCards(int num) {
        this.numCards = num;
    }

    // Shuffles an ArrayList object
    public void shuffleCards() {
        // Randomly shuffle the Deck of Card objects
        Collections.shuffle(this.cardArray);
    }

    // Draws the first Card object from the Deck object, then removes it from the Deck
    public Card draw() {
        // Save the card you're taking from the hand and remove it from the list.
        Card drawnCard = this.cardArray.remove(0);
        return drawnCard;
    }

    // Draws a specific card object from the Deck object, then removes it from the Deck
    public Card draw(int index) {
        // Save the card you're taking from the hand and remove it from the list.
        Card drawnCard = this.cardArray.remove(index);
        return drawnCard;
    }

    // Draw the Deck to a given length
    public void drawToFull(Deck deckOfCards, int numCards) {
        // Length of the Deck.cardArray
        int lengthOfDeck = this.cardArray.size();
        for (int i = 0; i < (numCards - lengthOfDeck); i++) {
            // Draw a card from the deckOfCards
            Card drawnCard = deckOfCards.draw();
            // Add the card to this.cardArray;
            this.addCard(drawnCard);
        }
    }

    // Return a card to the ArrayList<Card>
    public void addCard(Card returnCard) {
        // Add the card back to the ArrayList<Card> that makes up the Deck
        this.cardArray.add(returnCard);
    }

    // Merge two Deck object cardArrays. Pass the Deck to absorb.
    public void mergeDecks(Deck absorbDeck) {
        // Iterator to pass through the merged deck to check for "null" cards
        Iterator<Card> cardIterator = absorbDeck.getCardArray().iterator();
        // Check to see what Card objects are null cards
        while (cardIterator.hasNext()) {
            // Compare each card in the merged array to determine if the card is a "null" card
            if (cardIterator.next().getCardValue().compareTo("null") == 0) {
                // If the card is a "null" card, remove it
                cardIterator.remove();
            }
        }
        // Add the cards back to the end of the pokerDeck
        this.getCardArray().addAll(absorbDeck.cardArray);

        // Iterator to pass through the saveArray of a Deck (pokerHand)
        Iterator<Card> saveCardIterator = absorbDeck.getSaveArray().iterator();
        // Count the index in the saveArray
        int index = 0;
        // Check to see what Card objects in the saveArray are null cards
        while (saveCardIterator.hasNext()) {
            String cardValue = saveCardIterator.next().getCardValue();
            // compare each card in the saveArray to determine if the card is a "null" card
            if (cardValue.compareTo("null") == 0) {
                // Draw a new card from the Deck
                Card drawnCard = this.draw();
                // Swap the null card with the new drawnCard
                absorbDeck.getSaveArray().set(index, drawnCard);
            }
            // Increment the index value
            index++;
        }
    }

    // Setter for cardArray
    public void setCardArray(ArrayList<Card> cardArray) {
        this.cardArray = cardArray;
    }

    // Getter for cardArray
    public ArrayList<Card> getCardArray() {
        return cardArray;
    }

    // Initializes saveArray with a given number of null Cards
    public void initSaveArray(int length) {
        ArrayList<Card> interim = new ArrayList<Card>();
        for (int i = 0; i < length; i++) {
            interim.add(new Card());
        }
        // Set the saveArray to the interim ArrayList<Card>
        this.saveArray = interim;
    }

    // Save Card card to the saveArray
    public void appendSaveArray(Card card) {
        this.saveArray.add(card);
    }

    // Replace the card at a given index with a null card.
    public Card replaceSaveArray(int index) {
        Card card = this.saveArray.set(index, new Card());
        return card;
    }

    public void swapArrayListValues(int index) {
        // Null card used to swap values around
        Card nullCard = new Card();
        // Sets the nullCard to the cardArray while saving the card it took
        Card cardArrayCard = this.cardArray.set(index, nullCard);
        // Sets the cardArray card taken in the saveArray at the same index
        Card saveArrayCard = this.saveArray.set(index, cardArrayCard);
        // Sets the saveArray card taken into the cardArray without saving the nullCard object created in the beginning
        //  of the method
        this.cardArray.set(index, saveArrayCard);
    }

    // Remove hold conditions
    public void removeHoldCondition() {
        for (Card card : this.cardArray) {
            if (card.isHeld()) {
                card.setHeld(false);
            }
            else {
                card.setHeld(true);
            }
        }
    }

    // Getter for saveArray
    public ArrayList<Card> getSaveArray() {
        return saveArray;
    }

    // Re-initialize the saveArray
    public void dumpSaveArray() {
        this.saveArray = new ArrayList<Card>();
    }

    // Returns the length of the Deck object (Number of cards present in the deck)
    public int length() {
        return this.cardArray.size();
    }
}

// Card parent class
class Card extends ImageView {
    // Picture the Card is associated with
    private Image cardPic;
    // Value of a card. This is the same as the path value
    private String cardValue;
    // Indicates if the Card is being held
    private boolean isHeld = false;
    // Rank of the card: 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K, A, Joker
    private int cardRank;
    // Suit of the card: Spades, Hearts, Diamonds, Spades, Joker
    private String cardSuit;

    // Picture of the back of a Card object (vertically). Set to Blue for Poker1
    private Image cardBackBlue = new Image("./img/b1fv.png");
    private Image cardBackRed = new Image("./img/b2fv.png");

    // Default card class constructor. Represents an empty card slot OR the back of a card
    public Card() {
        super();
        // Call the ImageView super constructor to create the ImageView object.
        this.cardPic = cardBackBlue;
        this.cardValue = "null";
        this.setImage(this.cardPic);
    }

    // Card class constructor
    public Card(int index, int value, String path) {
        // Call the ImageView super constructor to create the ImageView object.
        super();
        // set cardPic to a specific card
        this.cardPic = setCardPic(path);
        // Set the value of a card
        this.cardValue = String.valueOf(index);
        // Set the ImageView.image property to the cardPic
        this.setImage(this.cardPic);
        determineCardRank();
        determineCardSuit();
    }

    // Draws the specified card
    private Image setCardPic(String path) {
        // new Image object to hold a card image
        Image card = new Image(path);
        // return the card object
        return card;
    }

    // Set the cardRank
    private void determineCardRank() {
        int cardValueInt = Integer.valueOf(cardValue);
        if (cardValueInt == 53 || cardValueInt == 54) {
            cardRank = 15; // Joker
        } else {
            switch (cardValueInt % 13) {
                case 0:
                    cardRank = 13; // K
                    break;
                case 1:
                    cardRank = 14; // A
                    break;
                case 2:
                    cardRank = 2;
                    break;
                case 3:
                    cardRank = 3;
                    break;
                case 4:
                    cardRank = 4;
                    break;
                case 5:
                    cardRank = 5;
                    break;
                case 6:
                    cardRank = 6;
                    break;
                case 7:
                    cardRank = 7;
                    break;
                case 8:
                    cardRank = 8;
                    break;
                case 9:
                    cardRank = 9;
                    break;
                case 10:
                    cardRank = 10;
                    break;
                case 11:
                    cardRank = 11; // J
                    break;
                case 12:
                    cardRank = 12; // Q
                    break;
            }
        }
    }

    // Set the cardSuit
    private void determineCardSuit() {
        int cardValueInt = Integer.valueOf(cardValue);
        if (cardValueInt <= 13) {
            cardSuit = "SPADES";
        } else if (cardValueInt <= 26) {
            cardSuit = "HEARTS";
        } else if (cardValueInt <= 39) {
            cardSuit = "DIAMONDS";
        } else if (cardValueInt <= 52) {
            cardSuit = "CLUBS";
        } else {
            cardSuit = "JOKER";
        }
    }

    // getter for cardPic
    public Image getCardPic() {
        return cardPic;
    }

    // Getter for cardValue
    public String getCardValue() {
        return cardValue;
    }

    // Setter for isheld
    public void setHeld(boolean bool) {
        this.isHeld = bool;
    }

    // Getter for isHeld
    public boolean isHeld() {
        return isHeld;
    }

    // Getter for cardRank
    public int getCardRank() {
        return cardRank;
    }

    // Setter for cardRank
    public void setCardRank(int cardRank) {
        this.cardRank = cardRank;
    }

    // Getter for cardSuit
    public String getCardSuit() {
        return cardSuit;
    }

    // Setter for cardSuit
    public void setCardSuit(String cardSuit) {
        this.cardSuit = cardSuit;
    }

    public void toggleHeldCondition() {
        if (isHeld) {
            isHeld = false;
        }
        else {
            isHeld = true;
        }
    }
}