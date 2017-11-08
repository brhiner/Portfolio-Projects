package userinterface;

import java.util.ArrayList;
import java.util.List;

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

public class EditFighterWindow {
	
	private static Stage editFighter = null;
	private static EditFighterWindow editFighterWindow = null;
	
	private EditFighterWindow(){
		
		List<String> fighterNames = new ArrayList<>();
		for (Fighter f : AppData.getAppData().getAllFighters()) {
			fighterNames.add(f.getName());
		}
		
		if(fighterNames.isEmpty()) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("No Fighters to Edit");
			alert.setContentText("You must first add a fighter!");

			alert.showAndWait();
		} else {
			// CREATE A NEW CUSTOM DIALOG BOX
			editFighter = new Stage();
			editFighter.setOnCloseRequest(new EventHandler<WindowEvent>(){

				@Override
				public void handle(WindowEvent arg0) {
					editFighter.close();
					editFighter = null;
					editFighterWindow = null;
				}
				
			});
			editFighter.setTitle("Update Fighter Info");

			BorderPane root = new BorderPane();

			Label header = new Label("Please enter the fighter's information:");
			header.setFont(new Font(header.getFont().getName(), 18));
			header.setPadding(new Insets(5, 5, 0, 5));

			root.setTop(header);

			// SELECT WHICH FIGHTER TO EDIT
			ChoiceBox<String> fighterToEdit = new ChoiceBox<>(
					FXCollections.observableArrayList(fighterNames));

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

			userInfo.add(new Label("Choose fighter to edit: "), 0, 0);
			userInfo.add(fighterToEdit, 1, 0);
			userInfo.add(new Label("Name: "), 0, 1);
			userInfo.add(fighterName, 0, 2);
			userInfo.add(new Label("Nickname: "), 1, 1);
			userInfo.add(fighterNickname, 1, 2);
			userInfo.add(new Label("Power: "), 2, 1);
			userInfo.add(powerCb, 2, 2);
			userInfo.add(new Label("Health: "), 3, 1);
			userInfo.add(healthCb, 3, 2);
			userInfo.add(new Label("Mobility: "), 4, 1);
			userInfo.add(mobilityCb, 4, 2);
			userInfo.add(new Label("Techniques: "), 5, 1);
			userInfo.add(techniquesCb, 5, 2);
			userInfo.add(new Label("Range: "), 6, 1);
			userInfo.add(rangeCb, 6, 2);

			// CREATE BUTTON TYPES
			Button edit = new Button("Save");
			edit.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					if (fighterName.getText().isEmpty()
							|| fighterNickname.getText().isEmpty()
							|| powerCb.getSelectionModel()
									.getSelectedItem() == null
							|| healthCb.getSelectionModel()
									.getSelectedItem() == null
							|| mobilityCb.getSelectionModel()
									.getSelectedItem() == null
							|| techniquesCb.getSelectionModel()
									.getSelectedItem() == null
							|| rangeCb.getSelectionModel()
									.getSelectedItem() == null
							|| fighterToEdit.getSelectionModel()
									.getSelectedItem() == null) {
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Error");
						alert.setHeaderText("Missing Information");
						alert.setContentText("All fields must be completed!");

						alert.showAndWait();
					} else {

						AppData.getAppData().removeFighter(fighterToEdit
								.getSelectionModel().getSelectedItem());
						SidePanelData.getSidePanelData()
								.removeFighter(fighterToEdit.getSelectionModel()
										.getSelectedItem());

						int power = Integer.parseInt(
								powerCb.getSelectionModel().getSelectedItem());
						int health = Integer.parseInt(
								healthCb.getSelectionModel().getSelectedItem());
						int mobility = Integer.parseInt(mobilityCb
								.getSelectionModel().getSelectedItem());
						int techniques = Integer.parseInt(techniquesCb
								.getSelectionModel().getSelectedItem());
						int range = Integer.parseInt(
								healthCb.getSelectionModel().getSelectedItem());

						Fighter f = new Fighter(fighterName.getText(),
								fighterNickname.getText(), power, health,
								mobility, techniques, range);
						
						AppData.getAppData().addFighter(f);
						SidePanelData.getSidePanelData().addFighter(f);
						ContentPanel.updateContentPanel();
						
						editFighter.close();
						editFighter = null;
						editFighterWindow = null;
					}

				}

			});

			Button cancel = new Button("Cancel");
			cancel.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					editFighter.close();
					editFighter = null;
					editFighterWindow = null;
				}

			});

			HBox buttonBar = new HBox();
			buttonBar.setAlignment(Pos.CENTER);
			buttonBar.setPadding(new Insets(0, 0, 5, 0));
			buttonBar.getChildren().addAll(edit, cancel);
			buttonBar.setSpacing(5);

			root.setCenter(userInfo);
			root.setBottom(buttonBar);

			Scene scene = new Scene(root);
			editFighter.setScene(scene);
			editFighter.show();
		}
	}
	
	public static void displayEditFighterWindow(){
		if(editFighterWindow == null){
			editFighterWindow = new EditFighterWindow();
		} else {
			editFighter.show();
		}
	}
}
