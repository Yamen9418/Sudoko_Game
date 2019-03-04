package Sudokuin;

import java.io.File;
//import java.util.HashMap;
//import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Vector;

import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class SudokoApplicationfinal extends Application {

	TextField mtextfield[][] = new TextField[9][9];
	StringBuilder sb = new StringBuilder();
//	String a[][] = new String[9][9];
//	Map<Map<Integer, Integer>, String> inputsaver = new HashMap<Map<Integer, Integer>, String>();
	private final int textfield_width = 50;
	private final int textfield_height = 50;
	Vector<Integer> thumbsupcounter = new Vector<Integer>();
	Vector<Integer> thumbsdowncounter = new Vector<Integer>();

	@Override
	public void start(Stage primaryStage) throws Exception {
		HBox hbox = new HBox();
		BorderPane root = new BorderPane();
		root.setStyle("-fx-background-color: #B6C1BC;");
		GridPane gridpane = new GridPane();

		// creating a menubar with menu and menu item with a photo
		MenuBar menubar = new MenuBar();
		Menu menugame = new Menu("Game");
		MenuItem menuitem1 = new MenuItem("Info");
		MenuItem menuitem2 = new MenuItem("Exit");
		File pic = new File("/Users/macbook/Desktop/sudoku.png");
		Image image = new Image(pic.toURI().toString());
		menugame.setGraphic(new ImageView(image));
		menugame.getItems().addAll(menuitem1, menuitem2);
		menubar.getMenus().add(menugame);
		// info will show an alert box with info about the game
		menuitem1.setOnAction(event -> {
			Alert info = new Alert(AlertType.INFORMATION);
			info.setTitle("about the game");
			info.setHeaderText("This game is done by Yamen & Filip");
			info.setContentText("Sudoku-Game version 1.0 \nHope you enjoy it :)");
			info.show();
//			fillrandomly();
		});
		// exit will close the stage after confirmation
		menuitem2.setOnAction(event -> {
			Alert exit = new Alert(AlertType.CONFIRMATION);
			exit.setTitle("Exit the game!");
			exit.setHeaderText("Are you sure you want to exit the game?");
			Optional<ButtonType> option = exit.showAndWait();
			if (option.get() == ButtonType.OK) {
				primaryStage.close();
			} else if (option.get() == ButtonType.CANCEL) {
				exit.close();
			}

		});

		// the feedback text
		Label feedback = new Label(" FEEDBACK!");
		feedback.setStyle("-fx-font: normal bold 15px 'serif' ");

		// set the distance between the nodes
		gridpane.setHgap(5);
		gridpane.setVgap(5);
		Scene scene = new Scene(root, 495, 610);
//		gridpane.setPrefHeight(200);
//		gridpane.setPrefWidth(300);

		// fill the grid with random numbers

		// fill the grid with white cells
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				TextField textfield = new TextField();
				mtextfield[i][j] = textfield;
				Random random = new Random();
				StringBuilder sb = new StringBuilder();
				sb.append(random.nextInt(9) + 1);
				mtextfield[i][j].setText(sb.toString());
				textfield.setStyle("-fx-background-radius:10;");
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

		// get gridpane & menubar
		root.setCenter(gridpane);
		root.setTop(menubar);

		// Solve button
		Button solve = new Button("Solve");
		solve.setStyle("-fx-background-color: green; -fx-text-fill: white;");
		solve.setOnAction(event -> {
			SudokuSolver s = new SudokuSolver();
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					String t = mtextfield[i][j].getText();
//					int tint;
					if (!t.equals("")) {
//						tint = 0;
						s.usersetValue(i, j, Integer.valueOf(t));
					}
//					} else tint = Integer.valueOf(t);

//					SudokuSolver s = new SudokuSolver();
//					s.setValue(i, j, tint);
				}
			}
//			SudokuSolver sudoku = new SudokuSolver();
			if (s.solve(0, 0)) {
				for (int i = 0; i < 9; i++) {
					for (int j = 0; j < 9; j++) {
						int v = s.getValue(i, j);
						mtextfield[i][j].setText(String.valueOf(v));

					}
				}
				Alert b = new Alert(AlertType.INFORMATION);
				b.setTitle("Solvable!");
				b.setHeaderText("soluation found");
//				b.setContentText("GIVE IT ANOTHER CHANCE :) ");
				b.show();
			} else {
				Alert c = new Alert(AlertType.INFORMATION);
				c.setTitle("Unsolvable!");
				c.setHeaderText("soluation not found");
				c.setContentText("GIVE IT ANOTHER CHANCE :) ");
				c.show();

			}
//			saver();
		});

		// Clear button: clear the grid and send alert
		Button clear = new Button("Clear");
		clear.setStyle("-fx-background-color: red; -fx-text-fill: white;");
		clear.setOnAction(event -> {
			// clear the digits
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					mtextfield[i][j].setText("");
				}
			}
			Alert a = new Alert(AlertType.INFORMATION);
			a.setTitle("CLEARED!");
			a.setHeaderText("cleared successfully!");
//			a.setContentText("GIVE IT ANOTHER CHANCE :) ");
			a.show();

		});

		// generate button : generate a new sudoku scene.
		Button generate = new Button("generate");
		generate.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
//		generate.setDefaultButton(true);
		generate.setOnAction(event -> {
			// generate sudoku
			mtextfield[0][2].setText("8");
			mtextfield[0][5].setText("9");
			mtextfield[0][7].setText("6");
			mtextfield[0][8].setText("2");
			mtextfield[1][8].setText("5");
			mtextfield[2][0].setText("1");
			mtextfield[2][2].setText("2");
			mtextfield[2][3].setText("5");
			mtextfield[3][3].setText("2");
			mtextfield[3][4].setText("1");
			mtextfield[3][7].setText("9");
			mtextfield[4][1].setText("5");
			mtextfield[4][6].setText("6");
			mtextfield[5][0].setText("6");
			mtextfield[5][7].setText("2");
			mtextfield[5][8].setText("8");
			mtextfield[6][0].setText("4");
			mtextfield[6][1].setText("1");
			mtextfield[6][3].setText("6");
			mtextfield[6][5].setText("8");
			mtextfield[7][0].setText("8");
			mtextfield[7][1].setText("6");
			mtextfield[7][4].setText("3");
			mtextfield[7][6].setText("1");
			mtextfield[8][6].setText("4");
		});

		// button thumbs up to give feedback
		Button thumbsup = new Button("");
		TextField up = new TextField("0");
		up.setStyle("-fx-background-radius:10;");
		up.setAlignment(Pos.CENTER);
		up.setMinHeight(30);
		up.setMaxHeight(30);
		up.setMinWidth(30);
		up.setMaxWidth(30);
		File pic2 = new File("/Users/macbook/Desktop/thumbsup.png");
		Image image2 = new Image(pic2.toURI().toString());
		thumbsup.setGraphic(new ImageView(image2));
		thumbsup.setOnAction(event -> {
			thumbsupcounter.add(1);
			int a = thumbsupcounter.size();
//			System.out.println(thumbsupcounter.size());
			up.setText(Integer.toString(a));
		});

		
		
		// button thumbs down to give feedback
		Button thumbsdown = new Button("");
		TextField down = new TextField("0");
		down.setStyle("-fx-background-radius:10;");
		down.setAlignment(Pos.CENTER);
		down.setMinHeight(30);
		down.setMaxHeight(30);
		down.setMinWidth(30);
		down.setMaxWidth(30);
		File pic3 = new File("/Users/macbook/Desktop/thumbsdown.png");
		Image image3 = new Image(pic3.toURI().toString());
		thumbsdown.setGraphic(new ImageView(image3));
		thumbsdown.setOnAction(event -> {
			thumbsdowncounter.add(1);
			int b = thumbsdowncounter.size();
//			System.out.println(thumbsdowncounter.size());
			down.setText(Integer.toString(b));
		});

		
		
		hbox.getChildren().addAll(solve, clear, generate, thumbsup, up, thumbsdown, down);
		hbox.setPadding(new Insets(20, 20, 20, 5));
		hbox.setSpacing(20);
		hbox.setStyle("-fx-background-color: #808080;");
		root.setBottom(hbox);

		// Delegate the focus to container so the requestFocus value is null
		final BooleanProperty firstTime = new SimpleBooleanProperty(true);
		mtextfield[0][0].focusedProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue && firstTime.get()) {
				hbox.requestFocus(); // Delegate the focus to container
				firstTime.setValue(false); // Variable value changed for future references
			}
		});

		primaryStage.setTitle("SUDOKU-GAME");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();

	}

	// method to fill textfields randomly
	public void fillrandomly() {
		Random random2 = new Random();
		StringBuilder sb2 = new StringBuilder();
		sb2.append(random2.nextInt(9) + 1);
		int sb2int = Integer.valueOf(sb2.toString());
		GridPane gridpane = new GridPane();

		for (int i = 0; i < sb2int; i++) {
			for (int j = 0; j < sb2int; j++) {
				TextField textfield = new TextField();
				mtextfield[i][j] = textfield;
				mtextfield[i][j].setText(sb.toString());
				gridpane.add(textfield, j, i);
			}
		}
	}

//	// method to fill the grid with random nubers
//	public void fill() {
//		Random random = new Random();
//		StringBuilder sb = new StringBuilder();
//		for (int k = 0; k < 12; k++) {
//			sb.append(random.nextInt(10));
//		}
//		for(int i =0; i <9; i++) {
//			for(int j = 0; j< 9; j++) {
//				mtextfield[i][j].setText(sb.toString());
//			}
//		}
//	}

	// read the input from the textfields using input-mapen
//	public void saver() {
//		for (int i = 0; i < 9; i++) {
//			for (int j = 0; j < 9; j++) {
//				a[i][j] = mtextfield[i][j].getText();
////				inputsaver.put(mtextfield[i][j], a[i][j]);
//
//				if (mtextfield[i][j].getText() == null || mtextfield[i][j].getText().trim().isEmpty()) {
//					return;
//////					System.out.println("Empty + (i,j)");
//				} else {
////					System.out.println(a[i][j] + Integer.valueOf(mtextfield[i][j].getText()));
//					System.out.println(a[i][j]);
//				}
//////					String input = mtextfield[i][j].getText();
////					inputsaver.put(mtextfield[i][j], mtextfield[i][j].getText());
////					System.out.println(inputsaver);
//			}
//
//		}
//	}

	public static void main(String[] args) {
		Application.launch(args);

	}

}
