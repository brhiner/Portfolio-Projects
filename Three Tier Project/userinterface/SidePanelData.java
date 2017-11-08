package userinterface;

import businesslayer.AppData;
import businesslayer.Fighter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

public class SidePanelData {

	private static ListView<Fighter> fighterListView = new ListView<>();
	private static ObservableList<Fighter> fighterObservableList = FXCollections.observableArrayList();
	private static SidePanelData sidePanelData = null;
	
	private SidePanelData() {
		
	}
	
	public static SidePanelData getSidePanelData(){
		if(sidePanelData == null) {
			sidePanelData = new SidePanelData();
		}
		return sidePanelData;
	}
	
	public ListView<Fighter> getFighterListView() {
		if (fighterObservableList.isEmpty()) {
			for (Fighter f : AppData.getAppData().getAllFighters()){
				this.addFighter(f);
			}
		}
		return fighterListView;
	}
	
	public void addFighter(Fighter f){
		fighterObservableList.add(f);
		fighterListView.setItems(fighterObservableList);
	}
	
	public boolean removeFighter(String name){
		for(Fighter f : fighterObservableList) {
			if (f.getName() == name) {
				boolean success = fighterObservableList.remove(f);
				fighterListView.setItems(fighterObservableList);
				return success;
			} //end if
		} //end for-each loop
		return false;
	} // end removeCharacter
	
	public Fighter getCurrent() {
		return fighterListView.getSelectionModel().getSelectedItem();
	}
	
}
