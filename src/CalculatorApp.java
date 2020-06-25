import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CalculatorApp extends Application {

	@Override
	public void start(Stage primaryStage) {
		// Create the Textfield
		TextField tf = new TextField();
        tf.setAlignment(Pos.BASELINE_RIGHT);
        tf.setEditable(false);
        
     // Create Number Buttons
        Button[] btnN = new Button[10];
        for (int i = 0; i < btnN.length; i++) {
            btnN[i] = new Button(Integer.toString(i));
        }
        Button btnP = new Button(".");
        Button btnAdd = new Button("+");
        Button btnSub = new Button("-");
        Button btnMul = new Button("×");
        Button btnDiv = new Button("÷");
        Button btnEql = new Button("=");

        Button btnCE = new Button("CE");
        Button btnAC = new Button("AC");

        // Create the Layout
        HBox hbox1 = new HBox(5, tf);
        hbox1.setAlignment(Pos.CENTER);
        HBox hbox2 = new HBox(5, btnCE, btnAC);
        hbox2.setAlignment(Pos.CENTER);
        HBox hbox3 = new HBox(5, btnN[7], btnN[8], btnN[9], btnAdd);
        hbox3.setAlignment(Pos.CENTER);
        HBox hbox4 = new HBox(5, btnN[4], btnN[5], btnN[6], btnSub);
        hbox4.setAlignment(Pos.CENTER);
        HBox hbox5 = new HBox(5, btnN[1], btnN[2], btnN[3], btnMul);
        hbox5.setAlignment(Pos.CENTER);
        HBox hbox6 = new HBox(5, btnN[0], btnP, btnEql, btnDiv);
        hbox6.setAlignment(Pos.CENTER);
        VBox vbox = new VBox(20, hbox1, hbox2, hbox3, hbox4, hbox5, hbox6);
        vbox.setPadding(new Insets(20));

        // Set the stage
        Scene scene = new Scene(vbox);
        primaryStage.setScene(scene);
		
		primaryStage.setTitle("Calculator Application");
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		// アプリケーションを起動する
		Application.launch(args);
		System.out.println("完了--CalculatorApp");
	}
}
