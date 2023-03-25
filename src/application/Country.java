package application;

import javafx.geometry.Insets;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;

public class Country {
	private double WIDTH = 1000;
	private double HEIGHT = 500;
	private String name;
	private double x;
	private double y;
	private double longitude;
	private double latitude;
	private RadioButton radioButton = new RadioButton();
	private ToggleGroup group;

	public Country() {
		// TODO Auto-generated constructor stub
	}

	public Country(String line) {
		String[] arr = line.split(":");

		this.name = arr[0];

		this.latitude = Double.parseDouble(arr[1]);
		this.longitude = Double.parseDouble(arr[2]);

		radioButton.setToggleGroup(group);
		radioButton.setPadding(new Insets(-7.5));
		ImageView vi = new ImageView(new Image("location-pin (1).png"));
		vi.setFitHeight(16);
		vi.setFitWidth(16);
		radioButton.setGraphic(vi);

		Tooltip tip = new Tooltip(this.getName());
		tip.setFont(new Font(16));
		tip.setStyle("-fx-background-color:grey;");
		radioButton.setTooltip(tip);

		radioButton.setOnAction(o -> {
			if (Main.numOfPointChoice == 0) {
				ImageView vi0 = new ImageView(new Image("location-pin.png"));
				vi0.setFitHeight(16);
				vi0.setFitWidth(16);
				radioButton.setGraphic(vi0);
			}
			
			if (Main.numOfPointChoice == 1) {
				ImageView vi0 = new ImageView(new Image("location-pin (2).png"));
				vi0.setFitHeight(16);
				vi0.setFitWidth(16);
				radioButton.setGraphic(vi0);
			}
			radioButton.setSelected(true);
			Main.numOfPointChoice += 1;
			if (Main.numOfPointChoice == 2) {
				Main.lock();
			}
			
			if (Main.click.isSelected()) {
				if (Main.numOfPointChoice == 2) {
					Main.targetText.getSelectionModel().select(getName());
				}
				if (Main.numOfPointChoice == 1) {
					Main.scourseText.getSelectionModel().select(getName());
				}
			}

		});
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public RadioButton getRadioButton() {
		return radioButton;
	}

	public void setRadioButton(RadioButton radioButton) {
		this.radioButton = radioButton;
	}

	public ToggleGroup getGroup() {
		return group;
	} 

	public void setGroup(ToggleGroup group) {
		this.group = group;
	}

	public double getX() {
		return  ((longitude + 180) / 360 * WIDTH);
	} 

	public void setX(double x) {
		this.x = x;
	}

	public double getY() { 
		return (HEIGHT - (latitude + 90) / 180 * HEIGHT);
	}

	public void setY(double y) {
		this.y = y;
	}

}
