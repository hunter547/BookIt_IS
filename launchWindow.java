/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BookIt_IS;



import java.util.Calendar;
import java.util.GregorianCalendar;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;




public class launchWindow extends Application {
    private TextField tfUsername = new TextField();
    private TextField tfPassword = new TextField();
    private Button btLogin = new Button("Login In");
    private Button btClear = new Button("Clear");
    private Label lblError = new Label("Please Enter Both Fields!");
   
    
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        // Create UI
        GridPane errorPane = new GridPane();
        errorPane.setHgap(5);
        errorPane.setVgap(5);
        errorPane.add(new Label("Username:"), 0, 0);
        errorPane.add(tfUsername, 1, 0);
        errorPane.add(new Label("Password:"), 0, 1);
        errorPane.add(tfPassword, 1, 1);
        errorPane.add(btLogin, 1, 3);
        errorPane.add(btClear, 1, 3);
        errorPane.add(lblError, 1, 4);
        
        lblError.setVisible(false);
       
        

        // Set properties for UI
        errorPane.setAlignment(Pos.CENTER);
        tfUsername.setAlignment(Pos.BOTTOM_RIGHT);
        tfPassword.setAlignment(Pos.BOTTOM_RIGHT);

        GridPane.setHalignment(btLogin, HPos.LEFT);
        GridPane.setHalignment(btClear, HPos.RIGHT);
        
        
      int day, month, year;
      int second, minute, hour;
      GregorianCalendar date = new GregorianCalendar();
 
      day = date.get(Calendar.DAY_OF_MONTH);
      month = date.get(Calendar.MONTH);
      year = date.get(Calendar.YEAR);
 
      second = date.get(Calendar.SECOND);
      minute = date.get(Calendar.MINUTE);
      hour = date.get(Calendar.HOUR);
      
      Label lblDateTime = new Label("Current Time and Date is: " + "\n" + (month+1) + "/" +
              day +"/"+year + "         " + hour + " : " + minute + " : " + second);
      
      errorPane.add(lblDateTime,0,5); 
      
  	

        // Process events
        btLogin.setOnAction(e -> {
           if (tfUsername.getText().isEmpty() || tfPassword.getText().isEmpty())
               
           lblError.setVisible(true);
           else {
                          MainDashboard mainDashboard = new MainDashboard();
                         
                          
        }
        });

        btClear.setOnAction(e -> {
            tfUsername.clear();
            tfPassword.clear();
            lblError.setVisible(false);
        }); 
        
        tfUsername.setOnMouseClicked(e->{
            
             lblError.setVisible(false);
        });
        
         tfPassword.setOnMouseClicked(e->{
             
             lblError.setVisible(false);
        });


        // Create a scene and place it in the stage
        Scene scene = new Scene(errorPane, 300, 150);
        primaryStage.setTitle("Login Window"); // Set title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.setResizable(false); //Disables users from changing window size
        primaryStage.show(); // Display the stage
        
    }

   
  
    public static void main(String[] args) {
        launch(args);
    }
}
