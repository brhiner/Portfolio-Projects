package userinterface;

import businesslayer.AppData;
import businesslayer.Fighter;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class NewFighterWindow {

	private static Stage newFighter = null;
	private static NewFighterWindow newFighterWindow = null;

	private NewFighterWindow() {
		
		// CREATE A NEW CUSTOM DIALOG BOX
		newFighter = new Stage();
		
		newFighter.setOnCloseRequest(new EventHandler<WindowEvent>(){

			@Override
			public void handle(WindowEvent arg0) {
				newFighter.close();
				newFighter = null;
				newFighterWindow = null;
			}
			
		});
		
		newFighter.setTitle("Add A New Character");

		BorderPane root = new BorderPane();

		Label header = new Label("Please enter the character's information:");
		header.setFont(new Font(header.getFont().getName(), 18));
		header.setPadding(new Insets(5, 5, 0, 5));

		root.setTop(header);

		// USER INFO PANE
		TextField fighterName = new TextField();
		fighterName.setPromptText("Name");
		TextField fighterNickname = new TextField();
		fighterNickname.setPromptText("Nickname");

		ChoiceBox<String> powerCb = new ChoiceBox<>(
				FXCollections.observableArrayList("1", "2", "3", "4", "5"));
		ChoiceBox<String> healthCb = new ChoiceBox<>(
				FXCollections.observableArrayList("1", "2", "3", "4", "5"));
		ChoiceBox<String> mobilityCb = new ChoiceBox<>(
				FXCollections.observableArrayList("1", "2", "3", "4", "5"));
		ChoiceBox<String> techniquesCb = new ChoiceBox<>(
				FXCollections.observableArrayList("1", "2", "3", "4", "5"));
		ChoiceBox<String> rangeCb = new ChoiceBox<>(
				FXCollections.observableArrayList("1", "2", "3", "4", "5"));

		GridPane userInfo = new GridPane();
		userInfo.setHgap(10);
		userInfo.setVgap(10);
		userInfo.setPadding(new Insets(10, 5, 10, 5));

		userInfo.add(new Label("Name: "), 0, 0);
		userInfo.add(fighterName, 0, 1);
		userInfo.add(new Label("Nickname: "), 1, 0);
		userInfo.add(fighterNickname, 1, 1);
		userInfo.add(new Label("Power: "), 2, 0);
		userInfo.add(powerCb, 2, 1);
		userInfo.add(new Label("Health: "), 3, 0);
		userInfo.add(healthCb, 3, 1);
		userInfo.add(new Label("Mobility: "), 4, 0);
		userInfo.add(mobilityCb, 4, 1);
		userInfo.add(new Label("Techniques: "), 5, 0);
		userInfo.add(techniquesCb, 5, 1);
		userInfo.add(new Label("Range: "), 6, 0);
		userInfo.add(rangeCb, 6, 1);

		// CREATE BUTTON TYPES
		Button add = new Button("Add");
		add.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (fighterName.getText().isEmpty()
						|| fighterNickname.getText().isEmpty()
						|| powerCb.getSelectionModel().getSelectedItem() == null
						|| healthCb.getSelectionModel()
								.getSelectedItem() == null
						|| mobilityCb.getSelectionModel()
								.getSelectedItem() == null
						|| techniquesCb.getSelectionModel()
								.getSelectedItem() == null
						|| rangeCb.getSelectionModel()
								.getSelectedItem() == null) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error");
					alert.setHeaderText("Missing Information");
					alert.setContentText("All fields must be completed!");

					alert.showAndWait();
				} else {

					int power = Integer.parseInt(
							powerCb.getSelectionModel().getSelectedItem());
					int health = Integer.parseInt(
							healthCb.getSelectionModel().getSelectedItem());
					int mobility = Integer.parseInt(
							mobilityCb.getSelectionModel().getSelectedItem());
					int techniques = Integer.parseInt(
							techniquesCb.getSelectionModel().getSelectedItem());
					int range = Integer.parseInt(
							healthCb.getSelectionModel().getSelectedItem());

					Fighter f = new Fighter(fighterName.getText(),
							fighterNickname.getText(), power, health, mobility,
							techniques, range);

					AppData.getAppData().addFighter(f);
					SidePanelData.getSidePanelData().addFighter(f);

					newFighter.close();
					newFighter = null;
					newFighterWindow = null;
				}

			}

		});

		Button cancel = new Button("Cancel");
		cancel.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				newFighter.close();
				newFighter = null;
				newFighterWindow = null;
			}

		});

		HBox buttonBar = new HBox();
		buttonBar.setAlignment(Pos.CENTER);
		buttonBar.setPadding(new Insets(0, 0, 5, 0));
		buttonBar.getChildren().addAll(add, cancel);
		buttonBar.setSpacing(5);

		root.setCenter(userInfo);
		root.setBottom(buttonBar);

		Scene scene = new Scene(root);
		newFighter.setScene(scene);
		newFighter.show();
	}

	public static void displayNewFighterWindow(){
		if(newFighterWindow == null){
			newFighterWindow = new NewFighterWindow();
		} else {
			newFighter.show();
		}
	}
}
