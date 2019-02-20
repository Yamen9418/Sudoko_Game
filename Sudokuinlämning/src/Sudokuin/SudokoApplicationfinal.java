package Sudokuin;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class SudokoApplicationfinal extends Application {

	TextField mtextfield[][] = new TextField[9][9];
	private final int textfield_width = 50;
	private final int textfield_height = 50;

	@Override
	public void start(Stage primaryStage) throws Exception {
		HBox hbox = new HBox();
		BorderPane root = new BorderPane();
		GridPane gridpane = new GridPane();
		// the feedback text
		Label feedback = new Label(" FEEDBACK!");
		feedback.setStyle("-fx-font: normal bold 15px 'serif' ");

		// set the distance between the nodes
		gridpane.setHgap(5);
		gridpane.setVgap(5);
//		gridpane.setStyle("-fx-background-color: #C0C0C0;");
//		gridpane.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
//		gridpane.setBackground(new Background(new BackgroundFill(Color.rgb(90, 90, 90), CornerRadii.EMPTY, Insets.EMPTY)));
		Scene scene = new Scene(root, 495, 600);
		root.setStyle("-fx-background-color: #B6C1BC;");

//		gridpane.setBackground(new Background(new BackgroundImage(image,BackgroundRepeat.REPEAT,
//                BackgroundRepeat.REPEAT,
//                BackgroundPosition.DEFAULT,
//                BackgroundSize.DEFAULT)));

		// Solve button
		Button solve = new Button("Solve");
		solve.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
		solve.setOnAction(event -> {
//			System.out.println("clicked solve!");
//			TODO
		});

		// Clear button
		Button clear = new Button("Clear");
		clear.setStyle("-fx-background-color: red; -fx-text-fill: white;");
		clear.setOnAction(event -> {
			// clear the digits

		});

		hbox.getChildren().addAll(solve, clear, feedback);
		hbox.setPadding(new Insets(15, 15, 10, 5));
		hbox.setSpacing(15);
		root.setBottom(hbox);

		// fill the grid
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {

				TextField textfield = new TextField();
				mtextfield[i][j] = textfield;
				textfield.setAlignment(Pos.CENTER);
				textfield.setMinHeight(textfield_height);
				textfield.setMaxHeight(textfield_height);
				textfield.setMinWidth(textfield_width);
				textfield.setMaxWidth(textfield_width);
				gridpane.add(textfield, j, i);

				// check the input // take only numbers 1-9
				textfield.setTextFormatter(new TextFormatter<String>((Change change) -> {
					String newText = change.getControlNewText();
					if (newText.length() > 1) {
						return null;
					} else if (newText.matches("[^1-9]")) {
						return null;
					} else {
						return change;
					}
				}));

			}
		}

		// set the colors for the first box
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				mtextfield[i][j].setStyle("-fx-background-radius:10; -fx-background-color:#FF4500;");
			}
		}

		// set the colors for the second box
		for (int i = 0; i < 3; i++) {
			for (int j = 6; j < 9; j++) {
				mtextfield[i][j].setStyle("-fx-background-radius:10; -fx-background-color:#FF4500;");

			}
		}

		// set the colors for the third box
		for (int i = 3; i < 6; i++) {
			for (int j = 3; j < 6; j++) {
				mtextfield[i][j].setStyle("-fx-background-radius:10; -fx-background-color:#FF4500;");
			}
		}

		// set the colors for the forth box
		for (int i = 6; i < 9; i++) {
			for (int j = 0; j < 3; j++) {
				mtextfield[i][j].setStyle("-fx-background-radius:10; -fx-background-color:#FF4500;");

			}
		}

		// set the colors for the fifth box
		for (int i = 6; i < 9; i++) {
			for (int j = 6; j < 9; j++) {
				mtextfield[i][j].setStyle("-fx-background-radius:10; -fx-background-color:#FF4500;");
			}
		}

		// get gridpane
		root.getChildren().add(gridpane);

		primaryStage.setTitle("SUDOKU-GAME");
		primaryStage.setScene(scene);
//		primaryStage.setResizable(false);
		primaryStage.show();

	}

	public static void main(String[] args) {
		Application.launch(args);

	}

}
