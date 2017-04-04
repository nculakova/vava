package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CreatorViewController implements Initializable {
	public Stage mainStage;

	@FXML
    private AnchorPane creator_anchorpane;
	@FXML
    private Label plan_creator_mode_label;
	@FXML
    public Button vytvorit_novy_plan_button;
	@FXML
    public Button odhlasit_sa_button;

	public void setStage(Stage stage) {
        mainStage = stage;
    }

	public void on_action_vytvorit_novy_plan(ActionEvent event) throws IOException {
		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/view/volitelne_predmety_novy_view.fxml"));
		fxmlloader.setController(new VolitelnePredmetyNovyViewController());
	    Parent volitelne_predmety_novy_parent = fxmlloader.load();
	    Scene volitelne_predmety_novy_scene = new Scene(volitelne_predmety_novy_parent);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(volitelne_predmety_novy_scene);
		stage.show();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}

	public void on_action_odhlasit_sa_button(ActionEvent event) throws IOException {
		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/view/login_view.fxml"));
		fxmlloader.setController(new LoginViewController());
	    Parent login_view_parent = fxmlloader.load();
	    Scene login_scene = new Scene(login_view_parent);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(login_scene);
		stage.show();
	}
}
