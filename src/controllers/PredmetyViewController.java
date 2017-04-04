package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import first_package.MyClient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import managers.PredmetManager;
import model.Predmet;

public class PredmetyViewController implements Initializable {
	public Stage mainStage;

	@FXML
    private AnchorPane predmety_anchorpane;
	@FXML
    private Label predmety_label;
	@FXML
    private ListView<String> predmety_listview;
	@FXML
    private Button ok_button;
	@FXML
    private Button spat_button;

	private MyClient client = new MyClient();

	public void setStage(Stage stage) {
        mainStage = stage;
    }

	public void setClient(MyClient client) {
		this.client = client;
	}

	public PredmetyViewController(MyClient client) {
		setClient(client);
	}

	public void on_action_spat_button(ActionEvent event) throws IOException {
		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/view/main_menu_view.fxml"));
		fxmlloader.setController(new MainMenuViewController(client));
	    Parent main_menu_view_parent = fxmlloader.load();
	    Scene main_menu_scene = new Scene(main_menu_view_parent);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(main_menu_scene);
		stage.show();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			client.get_predmety();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObservableList<String> predmety_nazov = FXCollections.observableArrayList(client.getPredmetyNazov());

		predmety_listview.setItems(predmety_nazov);

		ArrayList<Predmet> predmety = new ArrayList<Predmet>();
		predmety = client.getPredmety();
		PredmetManager predmetmanager = new PredmetManager();
		for(Predmet predmet : predmety) {
			try {
				predmetmanager.insertPredmet(predmet);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void on_key_released_predmety_listview() {
		System.out.println("KEY: " + predmety_listview.getSelectionModel().getSelectedItem());
	}

	public void on_mouse_released_predmety_listview() {
		System.out.println("MOUSE: " + predmety_listview.getSelectionModel().getSelectedItem());
	}
}
