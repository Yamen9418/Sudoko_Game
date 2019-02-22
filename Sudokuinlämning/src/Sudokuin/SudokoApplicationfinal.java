package Sudokuin;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
	String a[][] = new String[9][9];
	Map<Map<Integer, Integer>, String> inputsaver = new HashMap<Map<Integer, Integer>, String>();
	private final int textfield_width = 50;
	private final int textfield_height = 50;

	@Override
	public void start(Stage primaryStage) throws Exception {
		HBox hbox = new HBox();
		BorderPane root = new BorderPane();
		root.setStyle("-fx-background-color: #B6C1BC;");
		GridPane gridpane = new GridPane();
		
		//creating a menubar with menu and menu item with a photo
		MenuBar menubar = new MenuBar();
		Menu menugame = new Menu("Game");
		MenuItem menuitem = new MenuItem("new game");
		File pic = new File("/Users/macbook/Desktop/sudoku.png");
		Image image = new Image(pic.toURI().toString());
//		menugame.setGraphic(new ImageView(image));
		menugame.getItems().add(menuitem);
		menubar.getMenus().add(menugame);
		

		// the feedback text
		Label feedback = new Label(" FEEDBACK!");
		feedback.setStyle("-fx-font: normal bold 15px 'serif' ");

		// set the distance between the nodes
		gridpane.setHgap(5);
		gridpane.setVgap(5);
		Scene scene = new Scene(root, 495, 550);
//		gridpane.setPrefHeight(200);
//		gridpane.setPrefWidth(300);

		//fill the grid with random nubers
		
		
		// fill the grid with white cells
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				TextField textfield = new TextField();
				mtextfield[i][j] = textfield;
				Random random = new Random();
				StringBuilder sb = new StringBuilder();
//				for (int k = 0; k < 9; k++) {
					sb.append(random.nextInt(10));
					mtextfield[i][j].setText(sb.toString());
					
//				}
				
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

		// get gridpane
		root.getChildren().add(gridpane);
//		root.getChildren().add(menubar);
		

		// Solve button
		Button solve = new Button("Solve");
		solve.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
		solve.setOnAction(event -> {
//					System.out.println("clicked solve!");
//					TODO
			saver();
		});

		// Clear button: clear the grid and send alert
		Button clear = new Button("Clear");
		clear.setStyle("-fx-background-color: red; -fx-text-fill: white;");
		clear.setOnAction(event -> {
			// clear the digits
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					mtextfield[i][j].setText(null);
				}
			}
			Alert a = new Alert(AlertType.INFORMATION);
			a.setTitle("CLEARED!");
			a.setHeaderText("CLEARED SUCCESSED!");
//			a.setContentText("GIVE IT ANOTHER CHANCE :) ");
			a.show();

		});
		
		

		hbox.getChildren().addAll(solve, clear, feedback);
		hbox.setPadding(new Insets(15, 15, 10, 5));
		hbox.setSpacing(15);
		root.setBottom(hbox);
		
		//Delegate the focus to container so the requestFocus value is null
		final BooleanProperty firstTime = new SimpleBooleanProperty(true);
		mtextfield[0][0].focusedProperty().addListener((observable,  oldValue,  newValue) -> {
            if(newValue && firstTime.get()){
                hbox.requestFocus(); // Delegate the focus to container
                firstTime.setValue(false); // Variable value changed for future references
            }
        });
		

		primaryStage.setTitle("SUDOKU-GAME");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();

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
	public void saver() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				a[i][j] = mtextfield[i][j].getText();
//				inputsaver.put(mtextfield[i][j], a[i][j]);

				if (mtextfield[i][j].getText() == null || mtextfield[i][j].getText().trim().isEmpty()) {
					return;
////					System.out.println("Empty + (i,j)");
				} else {
//					System.out.println(a[i][j] + Integer.valueOf(mtextfield[i][j].getText()));
					System.out.println(a[i][j]);
				}
////					String input = mtextfield[i][j].getText();
//					inputsaver.put(mtextfield[i][j], mtextfield[i][j].getText());
//					System.out.println(inputsaver);
			}

		}
	}

	public static void main(String[] args) {
		Application.launch(args);

	}

}
