package Sudokuin;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SudokuApplication extends Application{
	
	private final int textfield_width = 55;
	private final int textfield_height = 55;

	public static void main(String[] args) {
		Application.launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		HBox hb = new HBox();
	//	BorderPane root = new BorderPane();
		TilePane tilepane = new TilePane();
		tilepane.setAlignment(Pos.TOP_LEFT);
//		GridPane gridpane = new GridPane();
		VBox root = new VBox();
	
//		TextField textfield2 = new TextField();
//		TextField textfield3 = new TextField();
//		


		Scene scene = new Scene(root, 500, 550);
	
		
		//Solve button
		Button solve = new Button("Solve");
		
		//Clear button
		Button clear = new Button("Clear");
		
		//Graphics 
		root.getChildren().addAll(solve, clear);
		root.setPadding(new Insets(15, 15, 10, 5));
		root.setSpacing(15);
//		hb.getChildren().add(tilepane);
//		gridpane.addColumn(0, textfield2,textfield3);
//		gridpane.addRow(1, textfield1);
//		gridpane.setVgap(5);
//		gridpane.setHgap(5);
//		root.setBottom(hb);
		root.getChildren().add(tilepane);
		root.setFillWidth(false);
		
		
		
		
//		tilepane.setPrefColumns(9);
//		tilepane.setPrefRows(9);
		
		for(int i =0; i < 81; i++) {
			TextField textfield1 = new TextField();
			textfield1.setMinHeight(textfield_height);
			textfield1.setMaxHeight(textfield_height);
			textfield1.setMinWidth(textfield_width);
			textfield1.setMaxWidth(textfield_width);
			
			textfield1.setTextFormatter(new TextFormatter <String>((Change change) ->  {
				 String newText = change.getControlNewText();
	                if (newText.length() > 1) 
	                {
	                    return null ;
	                }
	                else if (newText.matches("[^1-9]"))
	                {
	                    return null;
	                }
	                else 
	                {
	                    return change ;
	                }
			}));
			
			tilepane.getChildren().add(textfield1);
		}
		
		
		
		primaryStage.setTitle("SUDOKU-GAME");
		primaryStage.setScene(scene);
//		primaryStage.setResizable(false);
		primaryStage.show();
		
		
		
		
		
	}

}
