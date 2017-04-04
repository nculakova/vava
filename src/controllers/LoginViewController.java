package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;

import first_package.Main;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginViewController implements Initializable {
	public Stage mainStage;

	@FXML
    private AnchorPane login_anchorpane;
	@FXML
    private Label prihlasovacie_meno_label;
	@FXML
    private Label heslo_label;
	@FXML
    public TextField prihlasovacie_meno_textfield;
	@FXML
    public PasswordField heslo_passwordfield;
	@FXML
    public Button prihlasit_sa_button;

	private MyClient client = new MyClient();

	public void setStage(Stage stage) {
        mainStage = stage;
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.client = new MyClient();

		try {
			client.loadpage();
		} catch (FailingHttpStatusCodeException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void on_action_prihlasit_sa_button(ActionEvent event) throws Exception {
		String str_username = prihlasovacie_meno_textfield.getText();
		String str_password = heslo_passwordfield.getText();
		if(str_username.equals("x") && str_password.equals("x")) {
			FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/view/creator_view.fxml"));
			fxmlloader.setController(new CreatorViewController());
		    Parent creator_view_parent = fxmlloader.load();
		    Scene creator_scene = new Scene(creator_view_parent);
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(creator_scene);
			stage.show();
		}
		else if(client.ais_login(str_username, str_password)) {
			FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/view/main_menu_view.fxml"));
			fxmlloader.setController(new MainMenuViewController(client));
		    Parent main_menu_view_parent = fxmlloader.load();
		    Scene main_menu_scene = new Scene(main_menu_view_parent);
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(main_menu_scene);
			stage.show();
		}
		else {
			prihlasovacie_meno_textfield.clear();
			heslo_passwordfield.clear();
		}
	}
}
