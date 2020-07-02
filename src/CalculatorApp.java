import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CalculatorApp extends Application {
	
	TextField tfQ, tfA;

	@Override
	public void start(Stage primaryStage) {
		// Create the Textfields
		tfQ = new TextField();
        tfQ.setEditable(false);
        
        tfA = new TextField();
        tfA.setEditable(false);
        
        // Create the label
        Label lb = new Label("=");
        
        // Create the Buttons
        Button[] btnN = new Button[10];
        for (int i = 0; i < btnN.length; i++) btnN[i] = new Button(Integer.toString(i));
        Button btn00 = new Button("00");
        Button btnP = new Button(".");
        Button btnAdd = new Button("+");
        Button btnSub = new Button("-");
        Button btnMul = new Button("×");
        Button btnDiv = new Button("÷");
        Button btnEql = new Button("=");

        Button btnCE = new Button("CE");
        Button btnAC = new Button("AC");
        
        //　Set Buttons Actions
        for (int i = 0; i < btnN.length; i++) btnN[i].setOnAction(e -> btnN_Click(e));
        btn00.setOnAction(e -> btnN_Click(e));
        btnP.setOnAction(e -> btnN_Click(e));
        btnAdd.setOnAction(e -> btnN_Click(e));
        btnSub.setOnAction(e -> btnN_Click(e));
        btnMul.setOnAction(e -> btnN_Click(e));
        btnDiv.setOnAction(e -> btnN_Click(e));
        
        btnCE.setOnAction(e -> btnCE_Click());
        btnAC.setOnAction(e -> btnAC_Click());
        
        btnEql.setOnAction(e -> showAns());

        // Create the Layout
        HBox hbox1 = new HBox(5, tfQ, lb, tfA);
        hbox1.setAlignment(Pos.CENTER);
        HBox hbox2 = new HBox(5, btnCE, btnAC, btnEql);
        hbox2.setAlignment(Pos.CENTER);
        HBox hbox3 = new HBox(5, btnN[7], btnN[8], btnN[9], btnAdd);
        hbox3.setAlignment(Pos.CENTER);
        HBox hbox4 = new HBox(5, btnN[4], btnN[5], btnN[6], btnSub);
        hbox4.setAlignment(Pos.CENTER);
        HBox hbox5 = new HBox(5, btnN[1], btnN[2], btnN[3], btnMul);
        hbox5.setAlignment(Pos.CENTER);
        HBox hbox6 = new HBox(5, btnN[0], btn00, btnP, btnDiv);
        hbox6.setAlignment(Pos.CENTER);
        VBox vbox = new VBox(20, hbox1, hbox2, hbox3, hbox4, hbox5, hbox6);
        vbox.setPadding(new Insets(20));

        // Set the stage
        Scene scene = new Scene(vbox);
        primaryStage.setScene(scene);
		
		primaryStage.setTitle("Calculator Application");
		primaryStage.show();
	}
	
	public void btnN_Click(ActionEvent e) {
        Button b = (Button) e.getSource();
        tfQ.appendText(b.getText());
    }

    public void btnCE_Click() {
        int len = tfQ.getLength();
        if (len > 0) {
            tfQ.deleteText(len - 1, len);
        }
    }

    public void btnAC_Click() {
        tfQ.clear();
        tfA.clear();
    }
    
    public void showAns() {
    	String ans = Calculate.calc(tfQ.getText());
    	tfA.setText(ans);
    }
	
	public static void main(String[] args) {
		// アプリケーションを起動する
		Application.launch(args);
		System.out.println("完了--CalculatorApp");
	}
}
