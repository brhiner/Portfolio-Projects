package userinterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import businesslayer.AppData;
import businesslayer.Fighter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;

public class MainMenu {

	private static MainMenu mainMenu = null;
	private static MenuBar bar = null;
	
	private MainMenu(){
		
		bar = new MenuBar();
		
		//Create 'file' menu containing 'exit'
		Menu fileMenu = new Menu("File");
		MenuItem exit = new MenuItem("Exit");
		exit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.exit(0);
			}
		});
		
		fileMenu.getItems().add(exit);
		
		//create 'fighter' menu containing 'add', 'edit', 'remove' fighter
		Menu fighterMenu = new Menu("Fighter");
		
		//menu item 'add'
		MenuItem add = new MenuItem("Add Fighter");
		
		add.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				NewFighterWindow.displayNewFighterWindow();
			}
		});
		
		//menu item 'edit'
		MenuItem edit = new MenuItem("Edit A Fighter");
		
		edit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				EditFighterWindow.displayEditFighterWindow();
			}
		});
		
		//menu item 'remove'
		MenuItem remove = new MenuItem("Remove a Fighter");
		
		remove.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				List<String> options = new ArrayList<>();
				for(Fighter f : AppData.getAppData().getAllFighters()){
					options.add(f.getName());
				}
				
				if(options.isEmpty()) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error");
					alert.setHeaderText("No Fighters to Remove");
					alert.setContentText("You must first add a fighter!");

					alert.showAndWait();
				} else {
					ChoiceDialog<String> dialog = new ChoiceDialog<>(
							options.get(0), options);
					dialog.setTitle("Remove a Fighter");
					dialog.setHeaderText("Select a Fighter to Remove:");
					dialog.setContentText("Select Fighter: ");
				
					Optional<String> result = dialog.showAndWait();
					if(result.isPresent()) {
						String name = result.get();
						AppData.getAppData().removeFighter(name);
						SidePanelData.getSidePanelData().removeFighter(name);
						ContentPanel.updateContentPanel();
					}
				}
			}
		});
		
		fighterMenu.getItems().addAll(add, edit, remove);
		
		bar.getMenus().addAll(fileMenu, fighterMenu);
	} //end constructor
	
	//SINGLETON GET METHOD
	public static MenuBar getMainMenu() {
		if (mainMenu == null) {
			mainMenu = new MainMenu();
		}
		
		return bar;
	}
}
