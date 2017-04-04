package first_package;

import controllers.LoginViewController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	//WITH GUI
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		/*FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/view/login_view.fxml"));
	    Parent parent = fxmlloader.load();
	    LoginViewController loginviewcontroller = fxmlloader.getController();
	    loginviewcontroller.setStage(stage);
	    Scene scene = new Scene(parent);
	    stage.setTitle("Login window");
	    stage.setScene(scene);
	    stage.show();*/

		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/view/login_view.fxml"));
		fxmlloader.setController(new LoginViewController());
	    Parent login_view_parent = fxmlloader.load();
	    Scene login_scene = new Scene(login_view_parent);
	    stage.setTitle("Login window");
		stage.setScene(login_scene);
		stage.show();


	    stage.setOnCloseRequest(t -> {
	        Platform.exit();
	        System.exit(0);
	    });
	}

	//WITHOUT GUI
	/*public static void main(String[] args) throws Exception {
		WebClient webClient = new WebClient(); //Initiate a WebClient variable.
		webClient.setThrowExceptionOnFailingStatusCode(false);
		webClient = ais_login(webClient);
		//HtmlPage currentPage = webClient.getPage("https://is.stuba.sk/auth/?lang=sk");
		//String pageSource = currentPage.asXml();


		get_predmety(webClient);
		System.out.println("ahoj");
	}*/
}
