package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import managers.VolitelnyPredmetManager;
import model.Predmet;

public class VolitelnePredmetyNovyViewController implements Initializable {
	public Stage mainStage;

	@FXML
    private AnchorPane volitelne_predmety_novy_anchorpane;
	@FXML
    private Label nazov_label;
	@FXML
    public TextField nazov_textfield;
	@FXML
    private Label semester_label;
	@FXML
    private ChoiceBox<String> semester_choicebox;
	@FXML
    public Button zvolit_button;
	@FXML
    private Label volitelne_predmety_label;
	@FXML
    private ChoiceBox<String> volitelne_predmety_choicebox;
	@FXML
    public Button pridat_button;
	@FXML
    private ListView<String> volitelne_predmety_listview;
	@FXML
    public Button spat_button;
	@FXML
    public Button ulozit_button;

	private ArrayList<String> zvolene_predmety_str = new ArrayList<String>();

	public void setStage(Stage stage) {
        mainStage = stage;
    }

	public void on_action_pridat_button(ActionEvent event) {

		zvolene_predmety_str.add(volitelne_predmety_choicebox.getValue());

		ObservableList<String> zvolene_predmety = FXCollections.observableArrayList(zvolene_predmety_str);

		volitelne_predmety_listview.setItems(zvolene_predmety);
	}

	public void on_action_spat_button(ActionEvent event) throws IOException {
		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/view/creator_view.fxml"));
		fxmlloader.setController(new CreatorViewController());
	    Parent creator_view_parent = fxmlloader.load();
	    Scene creator_scene = new Scene(creator_view_parent);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(creator_scene);
		stage.show();
	}

	public void on_action_ulozit_button(ActionEvent event) {
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		semester_choicebox.getItems().clear();
		List<String> semestre = new ArrayList<>();
		for(int i = 1; i <= 8; i++) {
			semestre.add(i + ". semester");
		}
		ObservableList<String> ob = FXCollections.observableArrayList(semestre);
		semester_choicebox.setItems(ob);


		VolitelnyPredmetManager volitelny_predmet_manager = new VolitelnyPredmetManager();
		List<Predmet> predmety = new ArrayList<>();
		try {
			predmety = volitelny_predmet_manager.getVolitelne();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		volitelne_predmety_choicebox.getItems().clear();
		List<String> str_predmety = new ArrayList<>();
		for(int i = 1; i < predmety.size(); i++) {
			str_predmety.add(predmety.get(i).getNazov());
		}
		ObservableList<String> ob1 = FXCollections.observableArrayList(str_predmety);
		volitelne_predmety_choicebox.setItems(ob1);
	}

	public void on_action_zvolit_button(ActionEvent event) {

	}

}
