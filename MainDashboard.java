package BookIt_IS;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.*;
import static javafx.geometry.Pos.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.text.*;

public class MainDashboard
{
    //Creating Panes
    GridPane overallPane = new GridPane();
    
    GridPane salesPane = new GridPane();
    GridPane customerPane = new GridPane(); 
    GridPane employeePane = new GridPane(); 
    GridPane bookPane = new GridPane(); 
    GridPane profitPane = new GridPane();
    GridPane invPane = new GridPane(); 
    GridPane invHeaderPane = new GridPane();
    GridPane createPane = new GridPane(); 
    
    //Creating Tabs
    TabPane tbPane = new TabPane();
    Tab salesTab = new Tab("Sales");
    Tab customerTab = new Tab("Customers");
    Tab employeeTab = new Tab("Employees");
    Tab bookTab = new Tab("Books");
    Tab profitTab = new Tab("Profits");
    Tab invTab = new Tab("Inventory");
    Tab createTab = new Tab("Create New");
    
    //Main Scene
    Scene primaryScene = new Scene(overallPane, 1200, 1000);
    
    Stage primaryStage = new Stage();
    
    //Adding tabs to tbPane, and tbPane primaryStage
    
    public MainDashboard() 
    {
        //Formatting Panes
        overallPane.setAlignment(Pos.CENTER);
        overallPane.setHgap(20);
        overallPane.setMinSize(primaryScene.getWidth(), primaryScene.getHeight());
        
        salesPane.setAlignment(Pos.CENTER);
        salesPane.setHgap(20);
        salesPane.setMinSize(primaryScene.getWidth(), primaryScene.getHeight());
        
        customerPane.setAlignment(Pos.CENTER);
        customerPane.setHgap(20);
        customerPane.setMinSize(primaryScene.getWidth(), primaryScene.getHeight());
        
        employeePane.setAlignment(Pos.CENTER);
        employeePane.setHgap(20);
        employeePane.setMinSize(primaryScene.getWidth(), primaryScene.getHeight());
        
        bookPane.setAlignment(Pos.CENTER);
        bookPane.setHgap(20);
        bookPane.setMinSize(primaryScene.getWidth(), primaryScene.getHeight());
        
        profitPane.setAlignment(Pos.CENTER);
        profitPane.setHgap(20);
        profitPane.setMinSize(primaryScene.getWidth(), primaryScene.getHeight());
        
        invPane.setAlignment(Pos.TOP_CENTER);
        invPane.setHgap(20);
        invPane.setMinSize(primaryScene.getWidth(), primaryScene.getHeight());
        
        createPane.setAlignment(Pos.CENTER);
        createPane.setHgap(20);
        createPane.setMinSize(primaryScene.getWidth(), primaryScene.getHeight());
        
        //Setting up primaryStage
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("BookIt IS");
        primaryStage.show();
        
        //Formatting tbPane
        tbPane.setMinSize(primaryScene.getWidth(), primaryScene.getHeight());
        
        //Assign tabs to panes in main 
        salesTab.setContent(salesPane);
        customerTab.setContent(customerPane);
        employeeTab.setContent(employeePane);
        bookTab.setContent(bookPane);
        profitTab.setContent(profitPane);
        invTab.setContent(invPane);
        createTab.setContent(createPane);

        //Add tabs to tab pane in main window
        tbPane.getTabs().addAll(salesTab, customerTab, employeeTab, bookTab,
                                profitTab, invTab, createTab);

        salesTab.setClosable(false);
        customerTab.setClosable(false);
        employeeTab.setClosable(false);
        bookTab.setClosable(false);
        profitTab.setClosable(false);
        invTab.setClosable(false);
        createTab.setClosable(false);

        overallPane.add(tbPane, 0, 0);
        
        //INVENTORY TAB INFORMATION
        
        Label lblInventoryHeader = new Label ("Inventory");
        lblInventoryHeader.setFont(Font.font("Times New Roman", FontWeight.BOLD, 50));
        GridPane.setHalignment(lblInventoryHeader, HPos.CENTER);
        
        Label lblBookInvHeader = new Label ("Book Inventory");
        lblBookInvHeader.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
        GridPane.setHalignment(lblBookInvHeader, HPos.CENTER);
        
        Label lblConsInvHeader = new Label ("Consumable Inventory");
        lblConsInvHeader.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
        GridPane.setHalignment(lblConsInvHeader, HPos.CENTER);
        
        Label lblInvChooseStore = new Label ("Choose a Store: ");
        GridPane.setHalignment(lblInvChooseStore, HPos.CENTER);
        
        ComboBox cmboInvChooseStore = new ComboBox();
        GridPane.setHalignment(cmboInvChooseStore, HPos.CENTER);
        
        //Filler Panes
        Pane invBlankSpace1 = new Pane();
        invBlankSpace1.setMinHeight(50);
        Pane invBlankSpace2 = new Pane();
        invBlankSpace2.setMinHeight(50);
        
        //TableView Setups
        TableView<Book> bookInventoryView = new TableView<>();
        ObservableList<Book> bookInventoryTableData = 
                FXCollections.observableArrayList(Book.getBookArray());
        bookInventoryView.setItems(bookInventoryTableData);
        
        TableView<Consumable> consumableInventoryView = new TableView<>();
        ObservableList<Consumable> consumableInventoryTableData = 
                FXCollections.observableArrayList(Consumable.getConsumableArray());
        consumableInventoryView.setItems(consumableInventoryTableData);
        
        //Create Table Columns For bookInventoryView    
        TableColumn tblcBookInvID = new TableColumn("Book ID");
        TableColumn tblcBookInvTitle = new TableColumn("Title");
        TableColumn tblcBookQuantity = new TableColumn("Quantity");
        TableColumn tblcBookPrice = new TableColumn("Sale Price");
        TableColumn tblcBookCost = new TableColumn("Aquisition Cost");
        TableColumn tblcBookTotalCost = new TableColumn("Total Inventory Value");
        
        //Create Table Columns for consumableInventoryView
        TableColumn tblcConsInvID = new TableColumn("Consumable ID");
        TableColumn tblcConsInvName = new TableColumn("Name");
        TableColumn tblcConsQuantity = new TableColumn("Quantity");
        TableColumn tblcConsPrice = new TableColumn("Sale Price");
        TableColumn tblcConsCost = new TableColumn("Aquisition Cost");
        TableColumn tblcConsTotalCost = new TableColumn("Total Inventory Value");

        //Formatting TableViews
        bookInventoryView.setMinWidth(1000);
        bookInventoryView.setMaxHeight(320);
        bookInventoryView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        consumableInventoryView.setMinWidth(1000);
        consumableInventoryView.setMaxHeight(320);
        consumableInventoryView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
               
//        tblcBookInvID.setCellValueFactory(new PropertyValueFactory<Customer, String>("custIDString"));
//        tblcBookInvTitle.setCellValueFactory(new PropertyValueFactory<Customer, String>("custName"));
//        tblcBookQuantity.setCellValueFactory(new PropertyValueFactory<Customer, String>("custPhone"));
//        tblcBookPrice.setCellValueFactory(new PropertyValueFactory<Customer, String>("custAddress"));
//        tblcBookCost.setCellValueFactory(new PropertyValueFactory<Customer, String>("custPhone"));
//        tblcBookTotalCost.setCellValueFactory(new PropertyValueFactory<Customer, String>("custAddress"));

//        tblcConsInvID.setCellValueFactory(new PropertyValueFactory<Customer, String>("custIDString"));
//        tblcConsInvTitle.setCellValueFactory(new PropertyValueFactory<Customer, String>("custName"));
//        tblcConsQuantity.setCellValueFactory(new PropertyValueFactory<Customer, String>("custPhone"));
//        tblcConsPrice.setCellValueFactory(new PropertyValueFactory<Customer, String>("custAddress"));
//        tblcConsCost.setCellValueFactory(new PropertyValueFactory<Customer, String>("custPhone"));
//        tblcConsTotalCost.setCellValueFactory(new PropertyValueFactory<Customer, String>("custAddress"));
        
        //Adding Columns to TableViews
        bookInventoryView.getColumns().addAll(tblcBookInvID, tblcBookInvTitle, tblcBookQuantity, 
                tblcBookPrice, tblcBookCost, tblcBookTotalCost);
        
        consumableInventoryView.getColumns().addAll(tblcConsInvID, tblcConsInvName, tblcConsQuantity, 
                tblcConsPrice, tblcConsCost, tblcConsTotalCost);
        
        //Adding Items to invPane
        invPane.add(lblInventoryHeader, 1, 0);
        invPane.add(invBlankSpace1, 1, 1);
        invPane.add(lblInvChooseStore, 1, 2);
        invPane.add(cmboInvChooseStore, 1, 3);
        invPane.add(lblBookInvHeader, 1, 4);
        invPane.add(bookInventoryView, 1, 5);
        invPane.add(invBlankSpace2, 1, 6);
        invPane.add(lblConsInvHeader, 1, 7);
        invPane.add(consumableInventoryView, 1,8);
          
    }
}
