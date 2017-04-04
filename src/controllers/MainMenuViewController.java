package controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;

import first_package.MyClient;
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

public class MainMenuViewController implements Initializable {
	public Stage mainStage;

	@FXML
    private AnchorPane main_menu_anchorpane;
	@FXML
    private Label main_menu_label;
	@FXML
    private Button plany_vyberu_predmetov_button;
	@FXML
    private Button testy_button;
	@FXML
    private Button odhlasit_sa_button;

	private MyClient client = new MyClient();

	public void setStage(Stage stage) {
        mainStage = stage;
    }

	public void setClient(MyClient client) {
		this.client = client;
	}

	public MainMenuViewController(MyClient client) {
		setClient(client);
	}

	public void on_action_testy_button(ActionEvent event) throws IOException {
		/*FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/view/predmety_view.fxml"));
		fxmlloader.setController(new PredmetyViewController(client));
	    Parent predmety_view_parent = fxmlloader.load();
	    Scene predmety_scene = new Scene(predmety_view_parent);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(predmety_scene);
		stage.show();*/

		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/view/testy_view.fxml"));
		fxmlloader.setController(new TestyViewController(client));
	    Parent testy_view_parent = fxmlloader.load();
	    Scene testy_scene = new Scene(testy_view_parent);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(testy_scene);
		stage.show();
	}

	public void on_action_plany_vyberu_predmetov_button(ActionEvent event) throws FailingHttpStatusCodeException, MalformedURLException, IOException, SQLException {
		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/view/plany_vyberu_predmetov_view.fxml"));
		fxmlloader.setController(new PlanyVyberuPredmetovViewController(client));
	    Parent plany_vyberu_predmetov_view_parent = fxmlloader.load();
	    Scene plany_vyberu_predmetov_scene = new Scene(plany_vyberu_predmetov_view_parent);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(plany_vyberu_predmetov_scene);
		stage.show();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
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
