package userinterface;
	
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("Street Fighter V: Character Database");
			BorderPane root = new BorderPane();			
			
			root.setTop(MainMenu.getMainMenu());
			
			VBox selectPanel = new VBox();
			selectPanel.setPadding(new Insets(10));
			
			selectPanel.getChildren().addAll(new Label("Fighters: "),
					SidePanelData.getSidePanelData().getFighterListView());
			
			GridPane contentPanel = new ContentPanel().getContentPanel();
			
			SidePanelData.getSidePanelData().getFighterListView().
			setOnMouseClicked(new EventHandler<MouseEvent>(){
				
				@Override
				public void handle(MouseEvent arg0) {
					ContentPanel.updateContentPanel();
				}

			});
			
			root.setCenter(contentPanel);
			root.setLeft(selectPanel);
			
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
