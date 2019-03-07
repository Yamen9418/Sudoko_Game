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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.stage.Stage;

public class SudokoApplicationfinal extends Application {
	private int counter = 0;
	private long count;
	private boolean playing;
//	private Label label = new Label("Count: ");
	TextField mtextfield[][] = new TextField[9][9];
	MediaPlayer mediaPlayer1;
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

		// set the distance between the nodes
		gridpane.setHgap(5);
		gridpane.setVgap(5);
		Scene scene = new Scene(root, 495, 610);

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
			String solvee = "solve.wav";
			Media solvesound = new Media(new File(solvee).toURI().toString());
			MediaPlayer mediaPlayer5 = new MediaPlayer(solvesound);
			mediaPlayer5.play();
			SudokuSolver s = new SudokuSolver();
			Alert c = new Alert(AlertType.INFORMATION);

			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					String t = mtextfield[i][j].getText();
					if (!t.equals("")) {
						s.usersetValue(i, j, Integer.valueOf(t));
					}
				}
			}

			if (s.solve()) {
				for (int i = 0; i < 9; i++) {
					for (int j = 0; j < 9; j++) {
						int v = s.getValue(i, j);
						mtextfield[i][j].setText(String.valueOf(v));

					}
				}
				String cheers = "cheers.wav";
				Media cheerssound = new Media(new File(cheers).toURI().toString());
				MediaPlayer mediaPlayer9= new MediaPlayer(cheerssound);
				mediaPlayer9.play();
				Alert b = new Alert(AlertType.INFORMATION);
				b.setTitle("Solvable!");
				b.setHeaderText("soluation found");
				b.show();
			} else {
				String unsolvable = "unsolvable.wav";
				Media unsolvablesound = new Media(new File(unsolvable).toURI().toString());
				MediaPlayer mediaPlayer3 = new MediaPlayer(unsolvablesound);
				mediaPlayer3.play();

				c.setTitle("Unsolvable!");
				c.setHeaderText("Soluation not found");
				c.setContentText("GIVE IT ANOTHER CHANCE :) ");
				c.show();

			}
		});

		// Clear button: clear the grid and send alert
		Button clear = new Button("Clear");
		clear.setStyle("-fx-background-color: red; -fx-text-fill: white;");
		clear.setDefaultButton(true);
		clear.setOnAction(event -> {
			// clear the digits
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					mtextfield[i][j].setText("");
				}
			}
			String clearr = "clear.wav";
			Media clearsound = new Media(new File(clearr).toURI().toString());
			MediaPlayer mediaPlayer8 = new MediaPlayer(clearsound);
			mediaPlayer8.play();
			Alert a = new Alert(AlertType.INFORMATION);
			a.setTitle("CLEARED!");
			a.setHeaderText("cleared successfully!");
			a.show();

		});

		// generate button : generate a new sudoku scene.
		Button generatehard = new Button("Hard Sudoku");
		generatehard.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
//		generate.setDefaultButton(true);
		generatehard.setOnAction(event -> {
			// generate an empty sudoku
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					mtextfield[i][j].setText("");
				}
			}
			String startsong2 = "start2.wav";
			Media sound2 = new Media(new File(startsong2).toURI().toString());
			MediaPlayer mediaPlayer2 = new MediaPlayer(sound2);
			mediaPlayer2.play();

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

		// generate button : generate a new sudoku scene.
		Button generateeasy = new Button("Easy Sudoku");
		generateeasy.setStyle("-fx-background-color: darkblue; -fx-text-fill: white;");
		generateeasy.setOnAction(event -> {
//			for (int i = 0; i < 9; i++) {
//				for (int j = 0; j < 9; j++) {
//					mtextfield[i][j].setText("");
//				}
//			}
			String easysudoku = "gameon.wav";
			Media easysudokusound = new Media(new File(easysudoku).toURI().toString());
			MediaPlayer mediaPlayer4 = new MediaPlayer(easysudokusound);
			mediaPlayer4.play();
			fillrandomly();
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
			String like = "like.wav";
			Media likesound = new Media(new File(like).toURI().toString());
			MediaPlayer mediaPlayer6 = new MediaPlayer(likesound);
			mediaPlayer6.play();
			thumbsupcounter.add(1);
			int a = thumbsupcounter.size();
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
			String dislike = "dislike.wav";
			Media dislikesound = new Media(new File(dislike).toURI().toString());
			MediaPlayer mediaPlayer7 = new MediaPlayer(dislikesound);
			mediaPlayer7.play();
			thumbsdowncounter.add(1);
			int b = thumbsdowncounter.size();
			down.setText(Integer.toString(b));
		});

//		HBox hbox2 = new HBox();
//		hbox2.getChildren().add(label);
		hbox.getChildren().addAll(solve, clear, generateeasy, generatehard, thumbsup, up, thumbsdown, down);
//		label.relocate(0, 0);
		hbox.setPadding(new Insets(20, 20, 20, 5));
		hbox.setSpacing(3);
		hbox.setStyle("-fx-background-color: #808080;");
		root.setBottom(hbox);
//		root.setTop(hbox2);

		// Delegate the focus to container so the requestFocus value is null
		final BooleanProperty firstTime = new SimpleBooleanProperty(true);
		mtextfield[0][0].focusedProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue && firstTime.get()) {
				hbox.requestFocus(); // Delegate the focus to container
				firstTime.setValue(false); // Variable value changed for future references
			}
		});

		String startsong1 = "countdown.wav";
		Media sound1 = new Media(new File(startsong1).toURI().toString());
		mediaPlayer1 = new MediaPlayer(sound1);

		primaryStage.setTitle("SUDOKU-GAME");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
		if (primaryStage.isShowing()) {
			Alert d = new Alert(AlertType.WARNING);
			d.setTitle("Welcome to Sudoku- Game!");
			d.setContentText("You have 10 seconds to solve sudoku.The countdown will start after pressing ok!");
			Optional<ButtonType> option = d.showAndWait();
			if (option.get() == ButtonType.OK) {
				mediaPlayer1.play();
//				if(System.currentTimeMillis() - count >= 10000) {
//					Alert f = new Alert(AlertType.WARNING);
//					f.setTitle("You ");
//					f.setContentText("You have 10 seconds to solve sudoku.The countdown will start after pressing ok!");
//					f.show();
//				}
			}

//				Status status = mediaPlayer1.getStatus();
//				if(status == Status.PLAYING) {
//					Alert f = new Alert(AlertType.WARNING);
//					f.setTitle("You ");
//					f.setContentText("You have 10 seconds to solve sudoku.The countdown will start after pressing ok!");
//					f.show();
//				}
//				if(!isplaying()) {
//					Alert f = new Alert(AlertType.WARNING);
//					f.setTitle("You ");
//					f.setContentText("You have 10 seconds to solve sudoku.The countdown will start after pressing ok!");
//					f.show();
////					Optional<ButtonType> option1 = d.showAndWait();
//				} 
//				mediaPlayer1.getStopTime();
//				if(mediaPlayer1.getStatus().equals(Status.PLAYING)) {
//					playing = true;
//				}
//				playing = false;

//			Thread thread = new Thread();
//			for (int i = 0; i<= 11; i++) {
////				thread.sleep(12000);
//				counter ++;
//				if(counter == 10) {
//					Alert f = new Alert(AlertType.WARNING);
//					f.setTitle("You ");
//					f.show();
//				}
//			}

		}

	}

	public boolean isplaying() {
		Status status = mediaPlayer1.getStatus();
		if (status == Status.STOPPED) {
			return false;
		}
		return true;
	}

	// method to fill textfields randomly
	public void fillrandomly() {
		Random random2 = new Random();
		StringBuilder sb2 = new StringBuilder();
		sb2.append(random2.nextInt(9) + 1);
		int randomrow = random2.nextInt(9) + 1;
		int randomcol = random2.nextInt(9) + 1;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				mtextfield[i][j].setText("");
			}
		}

		for (int i = 1; i < 10; i++) {
			mtextfield[randomrow][randomcol].setText(sb2.toString());
		}
	}

	public static void main(String[] args) {
		Application.launch(args);

	}

}
