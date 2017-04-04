package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import managers.PredmetManager;
import managers.RokManager;
import model.Predmet;
import model.Rok;

public class TestyViewController implements Initializable {
	public Stage mainStage;

	@FXML
    private AnchorPane testy_anchorpane;
	@FXML
    private Label testy_label;
	@FXML
    private Label predmet_label;
	@FXML
    private Label rok_label;
	@FXML
    private Label test_label;
	@FXML
    private ChoiceBox<String> predmet_choicebox;
	@FXML
    private ChoiceBox<String> rok_choicebox;
	@FXML
    private ChoiceBox<String> test_choicebox;
	@FXML
    private Button pridat_novy_test_button;
	@FXML
    private Button zvolit_predmet_button;
	@FXML
    private Button zvolit_rok_button;
	@FXML
    private Button zvolit_test_button;
	@FXML
    private Label otazky_label;
	@FXML
    private Label odpoved_label;
	@FXML
    private ListView<String> otazky_listview;
	@FXML
    private TextArea odpoved_textarea;
	@FXML
    private Label nova_otazka_label;
	@FXML
    private Label otazka_label;
	@FXML
    private Label nova_odpoved_label;
	@FXML
    private TextArea otazka_textarea;
	@FXML
    private TextArea nova_odpoved_textarea;
	@FXML
    private Button pridat_button;
	@FXML
    private Button spat_button;

	private MyClient client = new MyClient();

	private String selected_predmet;
	private String selected_rok;
	private int selected_test;

	public void setStage(Stage stage) {
        mainStage = stage;
    }

	public void setClient(MyClient client) {
		this.client = client;
	}

	public TestyViewController(MyClient client) {
		setClient(client);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			client.get_predmety();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObservableList<String> predmety_nazov = FXCollections.observableArrayList(client.getPredmetyNazov());
		predmet_choicebox.getItems().clear();
		predmet_choicebox.setItems(predmety_nazov);

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


		ArrayList<String> roky_str = new ArrayList<String>();;
		RokManager rok_manager = new RokManager();
		List<Rok> roky = new ArrayList<Rok>();
		try {
			roky = rok_manager.getAllRok();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		for(Rok rok: roky) {
			roky_str.add(rok.toString());
		}
		ObservableList<String> ob = FXCollections.observableArrayList(roky_str);
		rok_choicebox.getItems().clear();
		rok_choicebox.setItems(ob);
	}

	public void on_action_pridat_novy_test_button() {

	}

	public void on_action_zvolit_predmet_button() {
		this.selected_predmet = predmet_choicebox.getSelectionModel().getSelectedItem();
	}

	public void on_action_zvolit_rok_button() {
		this.selected_rok = rok_choicebox.getSelectionModel().getSelectedItem();
	}

	public void on_action_zvolit_test_button() {
		this.selected_test = test_choicebox.getSelectionModel().getSelectedIndex();
	}

	public void on_action_pridat_button() {

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

}
