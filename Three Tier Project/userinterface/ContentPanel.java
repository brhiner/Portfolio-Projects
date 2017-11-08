package userinterface;

import businesslayer.Fighter;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class ContentPanel {

	private static GridPane contentPanel = new GridPane();
	
	private static Label name = new Label();
	private static Label nickname = new Label();
	private static Label power = new Label("Power");
	private static Label powerRating = new Label();
	private static Label health = new Label("Health");
	private static Label healthRating = new Label();
	private static Label mobility = new Label("Mobility");
	private static Label mobilityRating = new Label();
	private static Label techniques = new Label("Techniques");
	private static Label techniquesRating = new Label();
	private static Label range = new Label("Range");
	private static Label rangeRating = new Label();
	
	public ContentPanel() {
		
		contentPanel.setPrefSize(400, 400);;
		contentPanel.setPadding(new Insets(5));
		contentPanel.setHgap(5);
		contentPanel.setVgap(5);
		
		ColumnConstraints col1 = new ColumnConstraints();
		col1.setPercentWidth(20);
		ColumnConstraints col2 = new ColumnConstraints();
		col2.setPercentWidth(20);
		ColumnConstraints col3 = new ColumnConstraints();
		col3.setPercentWidth(20);
		ColumnConstraints col4 = new ColumnConstraints();
		col4.setPercentWidth(20);
		ColumnConstraints col5 = new ColumnConstraints();
		col5.setPercentWidth(20);
		contentPanel.getColumnConstraints()
			.addAll(col1, col2, col3, col4, col5);
		
		updateContentPanel();
		
	}
	
	public static void updateContentPanel() {
		
		contentPanel.getChildren().remove(name);
		contentPanel.getChildren().remove(nickname);
		contentPanel.getChildren().remove(power);
		contentPanel.getChildren().remove(powerRating);
		contentPanel.getChildren().remove(health);
		contentPanel.getChildren().remove(healthRating);
		contentPanel.getChildren().remove(mobility);
		contentPanel.getChildren().remove(mobilityRating);
		contentPanel.getChildren().remove(techniques);
		contentPanel.getChildren().remove(techniquesRating);
		contentPanel.getChildren().remove(range);
		contentPanel.getChildren().remove(rangeRating);
		
		Fighter currentFighter = SidePanelData.getSidePanelData().getCurrent();
		
		if(!(currentFighter == null)) {
			name.setText(currentFighter.getName());
			name.setFont(new Font(name.getFont().getName(), 18));
			nickname.setText("\"" + currentFighter.getNickname() + "\"");
			nickname.setFont(new Font(nickname.getFont().getName(), 15));
			powerRating.setText("" + currentFighter.getPower());
			healthRating.setText("" + currentFighter.getHealth());
			mobilityRating.setText("" + currentFighter.getMobility());
			techniquesRating.setText("" + currentFighter.getTechniques());
			rangeRating.setText("" + currentFighter.getRange());
			
			contentPanel.add(name, 0, 0);
			GridPane.setColumnSpan(name, 5);
			contentPanel.add(nickname, 0, 1);
			GridPane.setColumnSpan(nickname, 5);
			contentPanel.add(power, 0, 2);
			contentPanel.add(powerRating, 0, 3);
			contentPanel.add(health, 1, 2);
			contentPanel.add(healthRating, 1, 3);
			contentPanel.add(mobility, 2, 2);
			contentPanel.add(mobilityRating, 2, 3);
			contentPanel.add(techniques, 3, 2);
			contentPanel.add(techniquesRating, 3, 3);
			contentPanel.add(range, 4, 2);
			contentPanel.add(rangeRating, 4, 3);
		}
	}
	
	public GridPane getContentPanel(){
		return contentPanel;
	}
}
