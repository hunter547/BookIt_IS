package BookIt_IS;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class launchWindow extends Application 
{
    private TextField tfUsername = new TextField();
    private TextField tfPassword = new TextField();
    Button btnLogin = new Button("Login");
    Button btnClear = new Button("Clear"); 
    Button btnCheckIn = new Button("Clock-In"); 
    Button btnCheckOut = new Button("Clock-Out");
    private Label lblError = new Label("Incorrect Login");
    
     
   
    
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) 
    {
        // Fill arrays specific to each class, pulling from the database. 
        // Each fill array method bridges the gap between database storage and 
        // useable local application data. 
        
        Account_Transaction.fillAcctTransactionArray();
        Book.fillBookArray(); 
        Book_Order_Line_Item.fillBookOrderLineItemArray(); 
        Consumable.fillConsumableArray(); 
        Consumable_Order_Line_Item.fillConsumableOrderLineItemArray(); 
        Customer.fillCustomerArray(); 
        Employee.fillEmployeeArray();
        POS.fillPosArray(); 
        POS_Line_Item.fillPosLineItemArray(); 
        Payroll.fillPayrollArray(); 
        Product_Order.fillProductOrderArray(); 
        Salesperson.fillSalespersonArray(); 
        Store.fillStoreArray(); 
        Supplier.fillSupplierArray();
        Time_Management.fillTimeManagementArray();
        
        //create Hboxes for buttons
        btnLogin.setMinWidth(100);
        btnClear.setMinWidth(100);
        HBox hbLoginButtons = new HBox();
        hbLoginButtons.setSpacing(10);
        hbLoginButtons.setPadding(new Insets(0, 10, 10, 0));
        hbLoginButtons.getChildren().addAll(btnLogin,btnClear);
        
        btnCheckIn.setMinWidth(100);
        btnCheckOut.setMinWidth(100);
        HBox hbCheckInButtons = new HBox();
        hbCheckInButtons.setSpacing(10);
        hbCheckInButtons.setPadding(new Insets(0, 10, 10, 0));
        hbCheckInButtons.getChildren().addAll(btnCheckIn,btnCheckOut);
        
        //Create UI
        GridPane errorPane = new GridPane();
        errorPane.setHgap(10);
        errorPane.setVgap(5);
        errorPane.add(new Label("Username:"), 0, 0);
        errorPane.add(tfUsername, 1, 0,2,1);
        errorPane.add(new Label("Password:"), 0, 1);
        errorPane.add(tfPassword, 1, 1,2,1);
        errorPane.add(hbLoginButtons, 1, 3);
        errorPane.add(hbCheckInButtons, 1, 4); 
        errorPane.add(lblError, 1, 6);
        lblError.setTextFill(Color.RED);
        lblError.setVisible(false);
       
        // Set properties for UI
        errorPane.setAlignment(Pos.CENTER);
        tfUsername.setAlignment(Pos.BOTTOM_RIGHT);
        tfUsername.setMaxWidth(210);
        tfPassword.setAlignment(Pos.BOTTOM_RIGHT);
        tfPassword.setMaxWidth(210);

        GridPane.setHalignment(btnLogin, HPos.LEFT);
        GridPane.setHalignment(btnClear, HPos.RIGHT);
        
        
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

        errorPane.add(lblDateTime,1,5,2,1); 
      
        // Process events
        btnLogin.setOnAction(e -> 
        {
            if (!Employee.checkCredentials(tfUsername.getText(), tfPassword.getText())) 
                lblError.setVisible(true);
            
            else 
            { 
                MainDashboard mainDashboard = new MainDashboard();
            }
        }); 
        
        btnCheckIn.setOnAction(e -> 
        { 
            boolean valid = Employee.checkCredentials(tfUsername.getText(), tfPassword.getText()); 
            if(valid)
            { 
                int employID = Employee.getEmployID(tfUsername.getText()); 
                int storeID = Employee.getStoreID(tfUsername.getText()); 
                Date currentTime = new Date(); 
                Time_Management.timeIn(employID, storeID, currentTime.getTime()); 
            }
        });
        
        btnCheckOut.setOnAction(e -> 
        { 
            boolean valid = Employee.checkCredentials(tfUsername.getText(), tfPassword.getText()); 
            if(valid)
            { 
                int employID = Employee.getEmployID(tfUsername.getText()); 
                int storeID = Employee.getStoreID(tfUsername.getText()); 
                Date currentTime = new Date();  
                Time_Management.timeOut(employID, storeID, currentTime.getTime()); 
            }
        });
        
        btnClear.setOnAction(e -> 
        {
            tfUsername.clear();
            tfPassword.clear();
            lblError.setVisible(false);
        }); 
        
        tfUsername.setOnMouseClicked(e->
        {   
            lblError.setVisible(false);
        });
        
        tfPassword.setOnMouseClicked(e->
        {
            lblError.setVisible(false);
        });

        // Create a scene and place it in the stage
        Scene scene = new Scene(errorPane, 350, 250);
        primaryStage.setTitle("BookBurg Login"); // Set title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.setResizable(false); //Disables users from changing window size
        primaryStage.show(); // Display the stage
    }

    public static void main(String[] args) 
    {
        launch(args);
    } 
    
    public void stop()
    { 
        Account_Transaction.clearAcctTransactionTable(); 
        Account_Transaction.insertAllAcctTransactions();
        Book.clearBookTable(); 
        Book.insertAllBooks();
        Book_Order_Line_Item.clearBookOrderLineItemTable(); 
        Book_Order_Line_Item.insertAllBookOrderLineItems();
        Consumable.clearConsumableTable(); 
        Consumable.insertAllConsumables();
        Consumable_Order_Line_Item.clearConsumableOrderLineItemTable(); 
        Consumable_Order_Line_Item.insertAllConsumableOrderLineItems();
        Customer.clearCustomerTable(); 
        Customer.insertAllCustomers();
        Employee.clearEmployeeTable(); 
        Employee.insertAllEmployees(); 
        POS.clearPosTable(); 
        POS.insertAllPos();
        POS_Line_Item.clearPosLineItemTable(); 
        POS_Line_Item.insertAllPosLineItems();
        Payroll.clearPayrollTable(); 
        Payroll.insertAllPayrolls();
        Product_Order.clearProductOrderTable(); 
        Product_Order.insertAllProductOrders();
        Salesperson.clearSalespersonTable(); 
        Salesperson.insertAllSalespersons();
        Store.clearStoreTable(); 
        Store.insertAllStores();
        Supplier.clearSupplierTable(); 
        Supplier.insertAllSuppliers();
        Time_Management.clearTimeManagementTable(); 
        Time_Management.insertAllTimeManagements();
    }
}
