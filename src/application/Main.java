package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

import javafx.application.Application;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;

public class Main extends Application {
	public static File file;
	static ComboBox<String> scourseText = new ComboBox<String>();
	static ComboBox<String> targetText = new ComboBox<String>();
	static ToggleButton click = new ToggleButton("Click in map");
	static ToggleButton combo = new ToggleButton("Combo Box");
	static int numOfPointChoice = 0;
	static Pane pane2 = new Pane();
	private Alert error = new Alert(AlertType.ERROR);

	ArrayList<PathTable> tableData = new ArrayList<PathTable>();
	ObservableList<PathTable> data = FXCollections.observableArrayList(tableData);
	static ArrayList<vertex> Countrys = new ArrayList<>();

	@Override
	public void start(Stage primaryStage) {
		Image m = new Image("World-Map.jpg");
		ImageView image = new ImageView(m);
		image.setFitHeight(519);
		image.setFitWidth(1002);
		pane2.getChildren().add(image);

		primaryStage.getIcons().add(new Image("location.png"));
		Label title = new Label("World Map");
		title.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 30));
		title.setPadding(new Insets(15));
		file = new File("cities (1).txt");
		readFile(file);

		BorderPane pane = new BorderPane();
		pane.setPadding(new Insets(10));
		pane.setTop(title);
		BorderPane.setAlignment(title, Pos.CENTER);

		Label choose = new Label("Choose city by :");
		choose.setPadding(new Insets(15));
		ToggleGroup tg = new ToggleGroup();

		click.setToggleGroup(tg);
		combo.setToggleGroup(tg);
		icons(click);
		icons(combo);

		click.setOnAction(e -> {
			click.setStyle("-fx-border-radius: 25 25 25 25;\n" + "-fx-font-size: 14;\n"
					+ "-fx-font-family: Times New Roman;\n" + "-fx-font-weight: Bold;\n" + " -fx-text-fill: #ff6800;\n"
					+ "-fx-background-color: #d8d9e0;\n" + "-fx-border-color: #d8d9e0;\n" + "-fx-border-width:  3.5;"
					+ "-fx-background-radius: 25 25 25 25");
			combo.setStyle("-fx-border-radius: 25 25 25 25;\n" + "-fx-font-size: 14;\n"
					+ "-fx-font-family: Times New Roman;\n" + "-fx-font-weight: Bold;\n"
					+ "-fx-background-color: #f6f6f6;\n" + "-fx-border-color: #d8d9e0;\n" + "-fx-border-width:  3.5;"
					+ "-fx-background-radius: 25 25 25 25");

		});

		combo.setOnAction(e -> {
			combo.setStyle("-fx-border-radius: 25 25 25 25;\n" + "-fx-font-size: 14;\n"
					+ "-fx-font-family: Times New Roman;\n" + "-fx-font-weight: Bold;\n" + " -fx-text-fill: #ff6800;\n"
					+ "-fx-background-color: #d8d9e0;\n" + "-fx-border-color: #d8d9e0;\n" + "-fx-border-width:  3.5;"
					+ "-fx-background-radius: 25 25 25 25");
			click.setStyle("-fx-border-radius: 25 25 25 25;\n" + "-fx-font-size: 14;\n"
					+ "-fx-font-family: Times New Roman;\n" + "-fx-font-weight: Bold;\n"
					+ "-fx-background-color: #f6f6f6;\n" + "-fx-border-color: #d8d9e0;\n" + "-fx-border-width:  3.5;"
					+ "-fx-background-radius: 25 25 25 25");

		});

		scourseText.setOnAction(e -> {
			if (combo.isSelected()) {
				for (int i = 0; i < Countrys.size(); i++) {
					if (Countrys.get(i).getCountry().getName()
							.equals(scourseText.getSelectionModel().getSelectedItem())) {
						ImageView vi0 = new ImageView(new Image("location-pin.png"));
						vi0.setFitHeight(16);
						vi0.setFitWidth(16);
						Countrys.get(i).getCountry().getRadioButton().setGraphic(vi0);
						Countrys.get(i).getCountry().getRadioButton().setSelected(true);
						numOfPointChoice += 1;
						if (numOfPointChoice == 2) {
							lock();
						}
						break;
					}
				}
			}
		});

		targetText.setOnAction(e -> {
			if (combo.isSelected()) {
				for (int i = 0; i < Countrys.size(); i++) {
					if (Countrys.get(i).getCountry().getName()
							.equals(targetText.getSelectionModel().getSelectedItem())) {
						ImageView vi0 = new ImageView(new Image("location-pin (2).png"));
						vi0.setFitHeight(16);
						vi0.setFitWidth(16);
						Countrys.get(i).getCountry().getRadioButton().setGraphic(vi0);
						Countrys.get(i).getCountry().getRadioButton().setSelected(true);
						numOfPointChoice += 1;
						if (numOfPointChoice == 2) {
							lock();
						}
						break;
					}
				}
			}
		});

		HBox hx = new HBox(10, click, combo);
		hx.setAlignment(Pos.CENTER);
		hx.setPadding(new Insets(3));

		IconedTextFieled(choose, hx);
		HBox h = new HBox(choose, hx);
		h.setAlignment(Pos.CENTER);

		Label scourse = new Label("Sourse :");
		scourse.setPadding(new Insets(7));
		scourseText.setMinWidth(150);
		for (int i = 0; i < Countrys.size(); i++) {
			scourseText.getItems().add(Countrys.get(i).getCountry().getName());
		}

		IconedTextFieled(scourse, scourseText);
		HBox h1 = new HBox(scourse, scourseText);
		h1.setAlignment(Pos.CENTER);

		Label target = new Label("Target :");
		target.setPadding(new Insets(7));
		for (int i = 0; i < Countrys.size(); i++) {
			targetText.getItems().add(Countrys.get(i).getCountry().getName());
		}
		targetText.setMinWidth(150);
		IconedTextFieled(target, targetText);

		HBox h2 = new HBox(target, targetText);
		h2.setAlignment(Pos.CENTER);

		Button run = new Button("Run");
		Button reset = new Button("Reset");

		HBox butBox = new HBox(20, run, reset);
		butBox.setAlignment(Pos.CENTER);
		icons(reset);
		icons(run);
		butoonEffect(reset);
		butoonEffect(run);

		Label path = new Label("Shortest Path :");
		path.setPadding(new Insets(7));
		path.setMinHeight(200);
		path.setPadding(new Insets(7));
		TableView<PathTable> pathText = new TableView<PathTable>();

		TableColumn<PathTable, String> scource = new TableColumn<PathTable, String>("Source");
		scource.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getSource()));
		scource.setMaxWidth(80);
		TableColumn<PathTable, String> Target = new TableColumn<PathTable, String>("Target");
		Target.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getTarget()));
		Target.setMaxWidth(80);
		TableColumn<PathTable, Double> Distance = new TableColumn<PathTable, Double>("Distance");
		Distance.setCellValueFactory(p -> new SimpleDoubleProperty(p.getValue().getDistance()).asObject());

		Distance.setMaxWidth(90);

		pathText.getColumns().addAll(scource, Target, Distance);

		pathText.setMaxSize(250, 200);
		pathText.setItems(data);

		IconedTextFieled(path, new Node() {
		});

		HBox h3 = new HBox(path, pathText);
		h3.setAlignment(Pos.CENTER);

		Label distance = new Label("Distance :");
		distance.setPadding(new Insets(7));
		TextField distanceText = new TextField();

		IconedTextFieled(distance, distanceText);
		HBox h4 = new HBox(distance, distanceText);
		h4.setAlignment(Pos.CENTER);

		VBox v = new VBox(30, h, h1, h2, butBox);
		v.setAlignment(Pos.CENTER);
		v.setPadding(new Insets(10));
		icons(v);

		VBox v1 = new VBox(30, h3, h4);
		v1.setAlignment(Pos.CENTER);
		v1.setPadding(new Insets(10));
		icons(v1);

		VBox mix = new VBox(10, v, v1);
		mix.setAlignment(Pos.CENTER);

		VBox Vmap = new VBox(pane2);
		Vmap.setAlignment(Pos.CENTER);

		HBox mainBox = new HBox(20, Vmap, mix);
		mainBox.setAlignment(Pos.CENTER);

		pane.setCenter(mainBox);

		run.setOnAction(e -> {
			vertex vertx1 = null;
			vertex vertx2 = null;
			String s1 = scourseText.getValue();
			System.out.println(s1);
			String s2 = targetText.getValue();
			System.out.println(s2);

			for (int i = 0; i < Countrys.size(); i++) {
				if (Countrys.get(i).getCountry().getName().equals(s1)) {
					vertx1 = Countrys.get(i);
				}
				if (Countrys.get(i).getCountry().getName().equals(s2)) {
					vertx2 = Countrys.get(i);
				}
			}

			if (vertx1 != null && vertx2 != null) {
				int i = drowLine(Dijekstra(vertx1, vertx2));
				if (i == 0)
					distanceText.setText("0");
				else if (i == 1)
					distanceText.setText(String.valueOf(vertx2.distance));
				data = FXCollections.observableArrayList(tableData);
				pathText.setItems(data);

			}

		});

		reset.setOnAction(l -> {
			pane2.getChildren().clear();
			targetText.getSelectionModel().select(null);
			scourseText.getSelectionModel().select(null);
			distanceText.setText("");
			data.clear();
			tableData.clear();
			numOfPointChoice = 0;

			pane2.getChildren().add(image);
			Image Image = new Image("location-pin (1).png");
			for (vertex cou : Countrys) {
				ImageView vi = new ImageView(Image);
				vi.setFitHeight(17);
				vi.setFitWidth(16);
				cou.getCountry().getRadioButton().setGraphic(vi);
				cou.getCountry().getRadioButton().setSelected(false);
				free();

			}

			for (int i = 0; i < Countrys.size(); i++) {
				Countrys.get(i).visited = false;
				Countrys.get(i).previous = null;
			}

			addPoint();

		});

		addPoint();

		Scene scene = new Scene(pane, 1535, 800);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private int drowLine(vertex Destination) {
		if (Destination == null) {
			error.setContentText("No path");
			error.show();
			return 0;
		} else {
			List<vertex> p = new ArrayList<>();
			for (vertex v = Destination; v != null; v = v.previous) {
				System.out.print("-->" + v.country.getName() + " ");
				p.add(v);
			}
			System.out.println();
			// V
			Collections.reverse(p);
			if (p.size() >= 1) {
				for (int i = 1; i < p.size(); i++) {
					double d = Distance(p.get(i - 1), p.get(i));
					tableData.add(
							new PathTable(d, p.get(i - 1).getCountry().getName(), p.get(i).getCountry().getName()));
				}

			}
			if (p.size() <= 1) {
				error.setContentText("No path");
				error.show();
			}

			for (int i = 0; i < p.size() - 1; i++) {
				vertex u = p.get(i);
				vertex v = p.get(i + 1);

				if (i != 0 && i != p.size() - 1) {
					ImageView vi0 = new ImageView(new Image("location-pin (4).png"));
					vi0.setFitHeight(16);
					vi0.setFitWidth(16);
					u.getCountry().getRadioButton().setGraphic(vi0);
				}

				Line line = new Line(u.country.getX(), u.country.getY(), v.country.getX(), v.country.getY());
				pane2.getChildren().add(line);
			}
		}
		return 1;

	}

	private void addPoint() {
		for (int i = 0; i < Countrys.size(); i++) {
			RadioButton r = Countrys.get(i).getCountry().getRadioButton();
			r.setLayoutX(Countrys.get(i).getCountry().getX());
			r.setLayoutY(Countrys.get(i).getCountry().getY());
			pane2.getChildren().add(r);
		}

	}

	public static void main(String[] args) {
		launch(args);
	}

	private void IconedTextFieled(javafx.scene.Node l, javafx.scene.Node t) {
		l.setStyle("-fx-border-color: #d8d9e0;" + "-fx-font-size: 14;\n" + "-fx-border-width: 1;"
				+ "-fx-border-radius: 50;" + "-fx-font-weight: Bold;\n" + "-fx-background-color:#d8d9e0;"
				+ "-fx-background-radius: 50 0 0 50");

		t.setStyle("-fx-border-radius: 0 50 50 0;\n" + "-fx-font-size: 14;\n" + "-fx-font-family: Times New Roman;\n"
				+ "-fx-font-weight: Bold;\n" + "-fx-background-color: #f6f6f6;\n" + "-fx-border-color: #d8d9e0;\n"
				+ "-fx-border-width:  3.5;" + "-fx-text-fill: #ff6800;" + "-fx-background-radius: 0 50 50 0");
	}

	private void icons(Node l) {
		l.setStyle("-fx-border-radius: 25 25 25 25;\n" + "-fx-font-size: 14;\n" + "-fx-font-family: Times New Roman;\n"
				+ "-fx-font-weight: Bold;\n" + "-fx-background-color: transparent;\n" + "-fx-border-color: #d8d9e0;\n"
				+ "-fx-border-width:  3.5;" + "-fx-background-color: #f6f6f6;\n"
				+ "-fx-background-radius: 25 25 25 25");
	}

	private void butoonEffect(Node b) {
		b.setOnMouseMoved(e -> {
			b.setStyle("-fx-border-radius: 25 25 25 25;\n" + "-fx-font-size: 14;\n"
					+ "-fx-font-family: Times New Roman;\n" + "-fx-font-weight: Bold;\n" + " -fx-text-fill: #ff6800;\n"
					+ "-fx-background-color: #d8d9e0;\n" + "-fx-border-color: #d8d9e0;\n" + "-fx-border-width:  3.5;"
					+ "-fx-background-radius: 25 25 25 25");
		});

		b.setOnMouseExited(e -> {
			b.setStyle("-fx-border-radius: 25 25 25 25;\n" + "-fx-font-size: 14;\n"
					+ "-fx-font-family: Times New Roman;\n" + "-fx-font-weight: Bold;\n"
					+ "-fx-background-color: #f6f6f6;\n" + "-fx-border-color: #d8d9e0;\n" + "-fx-border-width:  3.5;"
					+ "-fx-background-radius: 25 25 25 25");
		});
	}

	public static void lock() {
		try {
			for (int i = 0; i < Countrys.size(); i++) {
				Countrys.get(i).getCountry().getRadioButton().setDisable(true);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void free() {
		try {
			for (int i = 0; i < Countrys.size(); i++) {
				Countrys.get(i).getCountry().getRadioButton().setDisable(false);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public vertex Dijekstra(vertex Source, vertex Destination) {// O(n) = (V(logV+E))
		Source.distance = 0;
		if (Source == Destination) {
			return null;
		}

		PriorityQueue<vertex> pq = new PriorityQueue<>(new Comparator<vertex>() { // Log V
			@Override
			public int compare(vertex v1, vertex v2) {
				return Double.compare(v1.distance, v2.distance);
			}
		});

		pq.add(Source);

		while (!pq.isEmpty()) { // V
			vertex u = pq.poll(); //// Log V

			u.visited = true;
			if (u.country.getName().equals(Destination.getCountry().getName())) {
				break;
			}
			for (edges e : u.getE()) { // E
				vertex v = e.desination;
				if (!v.visited) {
					double weight = e.weight;
					double distanceThroughU = u.distance + weight;
					if (distanceThroughU < v.distance) {
						v.distance = distanceThroughU;
						v.previous = u;
						pq.add(v);
					}
				}
			}
		}

		return Destination;
	}

	public void readFile(File file) {
		try {
			Scanner sc = new Scanner(file);
			String[] l = sc.nextLine().split(":");
			int numCounter = Integer.parseInt(l[0]);
			int numEdge = Integer.parseInt(l[1]);
			int count = 0;
			int num = 0;

			while (count < numCounter - 1) {
				String line = sc.nextLine();
				System.out.println(line);
				vertex ver = new vertex(new Country(line), num++);
				Countrys.add(ver);
				count++;

			}

			count = 0;
			while (count < numEdge) {
				String tokens[] = sc.nextLine().split(":");
				for (int i = 0; i < Countrys.size(); i++) {
					if (Countrys.get(i).getCountry().getName().compareToIgnoreCase(tokens[0]) == 0) {
						for (int j = 0; j < Countrys.size(); j++) {

							if (Countrys.get(j).getCountry().getName().compareToIgnoreCase(tokens[1]) == 0) {

								Countrys.get(i).e.add(new edges(Countrys.get(i), Countrys.get(j),
										Distance(Countrys.get(i), Countrys.get(j))));
							}

						}
					}
				}
				count++;
			}
			sc.close();
		} catch (FileNotFoundException t) {
			System.out.println(t);
		}
	}

	public double Distance(vertex a, vertex b) {

		final int EARTH_RADIUS = 6371;
		double lat1Rad = Math.toRadians(a.getCountry().getLatitude());
		double lat2Rad = Math.toRadians(b.getCountry().getLatitude());
		double deltaLat = Math.toRadians(b.getCountry().getLatitude() - a.getCountry().getLatitude());
		double deltaLon = Math.toRadians(b.getCountry().getLongitude() - a.getCountry().getLongitude());

		double dis = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2)
				+ Math.cos(lat1Rad) * Math.cos(lat2Rad) * Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);
		double c = 2 * Math.atan2(Math.sqrt(dis), Math.sqrt(1 - dis));

		return EARTH_RADIUS * c;

	}
}
