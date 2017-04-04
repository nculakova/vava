package controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;

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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import managers.VolitelnyPredmetManager;
import model.Predmet;

public class PlanyVyberuPredmetovViewController implements Initializable {
	public Stage mainStage;

	@FXML
    private AnchorPane plany_vyberu_predmetov_anchorpane;
	@FXML
    private Label plany_vyberu_predmetov_label;
	@FXML
    private Label volitelne_predmety_z_db_label;
	@FXML
    public Button aktualizacia_udajov_z_aisu_button;
	@FXML
    private ListView<String> volitelne_predmety_z_db_listview;
	@FXML
    public Button spat_button;

	private MyClient client = new MyClient();

	public void setClient(MyClient client) {
		this.client = client;
	}

	public PlanyVyberuPredmetovViewController(MyClient client) {
		setClient(client);
	}

	public void setStage(Stage stage) {
        mainStage = stage;
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		VolitelnyPredmetManager volitelny_predmet_manager = new VolitelnyPredmetManager();
		List<Predmet> predmety = new ArrayList<>();
		try {
			predmety = volitelny_predmet_manager.getVolitelne();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		List<String> str_predmety = new ArrayList<>();
		for(int i = 1; i < predmety.size(); i++) {
			str_predmety.add(predmety.get(i).getNazov());
		}

		ObservableList<String> predmety_nazov = FXCollections.observableArrayList(str_predmety);

		volitelne_predmety_z_db_listview.setItems(predmety_nazov);
	}

	public void on_action_aktualizacia_udajov_z_aisu_button(ActionEvent event) throws FailingHttpStatusCodeException, MalformedURLException, IOException, SQLException {
		client.get_volitelne_predmety();
		initialize(null, null);
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
