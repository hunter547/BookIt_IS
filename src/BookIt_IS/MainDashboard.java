package BookIt_IS;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.text.*;

public class MainDashboard
{
    //Creating ArrayLists
    ArrayList <Book> bookArray= Book.getBookArray();
    ArrayList <Consumable> consumableArray = Consumable.getConsumableArray();
    ArrayList <Customer> customerArray = Customer.getCustArray();
    //ArrayList <Employee> employeeArray = Employee.getEmpArray();
    ArrayList <Store> storeArray = Store.getStoreArray();
    ArrayList <Supplier> supplierArray = Supplier.getSupplierArray();
    
//Creating Panes
    GridPane overallPane = new GridPane();
    
    GridPane salesPane = new GridPane();
    
    GridPane customerPane = new GridPane(); 
    GridPane modCustomerPane = new GridPane();
    
    GridPane employeePane = new GridPane(); 
    
    GridPane bookPane = new GridPane(); 
    GridPane modBookPane = new GridPane();
    
    GridPane profitPane = new GridPane();
    
    GridPane invPane = new GridPane(); 
    GridPane invHeaderPane = new GridPane();
    
    GridPane createPane = new GridPane(); 
    
    //Store Panes
    GridPane storePane = new GridPane();
    GridPane modStorePane = new GridPane();
    
    //Creating Tabs
    TabPane tbPane = new TabPane();
    Tab salesTab = new Tab("Sales");
    Tab customerTab = new Tab("Customers");
    Tab employeeTab = new Tab("Employees");
    Tab bookTab = new Tab("Books");
    Tab profitTab = new Tab("Profits");
    Tab invTab = new Tab("Inventory");
    Tab createTab = new Tab("Create New");
    Tab storeTab = new Tab("Store");
    
    //These panes are used in the Add Entity page. They make up the second 
    //half of the page, and are where a user can enter information for the new 
    //entity.
    GridPane addCustPane = new GridPane();
    GridPane addEmployeePane = new GridPane();
    GridPane addStorePane = new GridPane();
    GridPane addSupplierPane = new GridPane();
    GridPane addBookPane = new GridPane();
    GridPane addConsumablePane = new GridPane();

    //Main Scene
    Scene primaryScene = new Scene(overallPane, 1200, 1000);
    Stage primaryStage = new Stage();
    
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
        invPane.setVgap(5);
        invPane.setMinSize(primaryScene.getWidth(), primaryScene.getHeight());
        
        createPane.setAlignment(Pos.TOP_CENTER);
        createPane.setHgap(20);
        createPane.setVgap(5);
        createPane.setMinSize(primaryScene.getWidth(), primaryScene.getHeight());
        
        //Setting up primaryStage
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("BookIt IS");
        primaryStage.show();
        
        //Formatting tbPane
        tbPane.setMinSize(primaryScene.getWidth(), primaryScene.getHeight());
        
        //Assign tabs to panes in MainDashboard 
        salesTab.setContent(salesPane);
        customerTab.setContent(customerPane);
        employeeTab.setContent(employeePane);
        bookTab.setContent(bookPane);
        profitTab.setContent(profitPane);
        invTab.setContent(invPane);
        createTab.setContent(createPane);
        storeTab.setContent(storePane);
        
        //Add tabs to tab pane in main window
        tbPane.getTabs().addAll(customerTab, employeeTab, storeTab, bookTab,
                                 invTab, salesTab, profitTab, createTab);
        
        overallPane.add(tbPane, 0, 0);
        
        //Making tabs un-closable
        customerTab.setClosable(false);
        employeeTab.setClosable(false);
        storeTab.setClosable(false);
        bookTab.setClosable(false);
        invTab.setClosable(false);
        salesTab.setClosable(false);
        profitTab.setClosable(false);
        createTab.setClosable(false);
        
        //******CUSTOMER TAB INFORMATION*************
        
        //Formatting Stuff
        customerPane.setHgap(20);
        customerPane.setVgap(5);
        modCustomerPane.setHgap(20);
        modCustomerPane.setVgap(5);
        
        // set modCustomerPane invisible
        modCustomerPane.setVisible(false);
        
        // Customer Tab Header
        Label lblCustHeader = new Label ("Customer Report:");
        lblCustHeader.setFont(Font.font("Times New Roman", FontWeight.BOLD, 50));
        GridPane.setHalignment(lblCustHeader, HPos.CENTER);
        
        // Customer Tab Labels
        Label lblCustDesc = new Label("Customer Description: ");
        GridPane.setHalignment(lblCustDesc, HPos.CENTER);
        Label lblCustHist = new Label("Transaction History: ");
        GridPane.setHalignment(lblCustHist, HPos.CENTER); 
        Label lblCustEnroll = new Label("Enrolled in Loyalty Program: ");
        GridPane.setHalignment(lblCustEnroll, HPos.CENTER);
        
        // Text Area outout for customer description
        TextArea txtAreaCustDesc = new TextArea();
        txtAreaCustDesc.setMaxSize(300, 400);

        // Customer Buttons
        Button btnModCust = new Button("Modify Customer ->");
        Button btnRemoveCust = new Button("Remove Customer ->");
        Button btnEnrollCust = new Button("Enroll Customer ->");

        btnModCust.setMaxWidth(165);
        btnRemoveCust.setMaxWidth(165);
        btnEnrollCust.setMaxWidth(165);

        VBox vbCustButtons = new VBox();
        vbCustButtons.setSpacing(10);
        vbCustButtons.setPadding(new Insets(0, 10, 10, 0));
        vbCustButtons.getChildren().addAll(btnModCust, btnRemoveCust, btnEnrollCust);
        
        //modCustPane Controls
        Label lblModCustomerHeader = new Label("Modify Customer:");
        lblModCustomerHeader.setFont(Font.font("Times New Roman", FontWeight.BOLD, 18));
        GridPane.setHalignment(lblModCustomerHeader, HPos.CENTER);
        
        Label lblModCustFname = new Label("First Name: ");
        Label lblModCustLname = new Label("Last Name: ");
        Label lblModCustPhone = new Label("Phone Number: ");
        Label lblModCustAddress = new Label("Adress: ");
        
        TextField txtModCustFname = new TextField();
        TextField txtModCustLname = new TextField();
        TextField txtModCustPhone = new TextField();
        TextField txtModCustAddress = new TextField();
        
        Button btnModCustSubmit = new Button("Submit");
        Button btnModCustClear = new Button("Clear");
        btnModCustSubmit.setMaxWidth(150);
        btnModCustClear.setMaxWidth(150);

        VBox vbModCustButtons = new VBox();
        vbModCustButtons.setSpacing(10);
        vbModCustButtons.setPadding(new Insets(0, 10, 10, 0));
        vbModCustButtons.getChildren().addAll(btnModCustSubmit, btnModCustClear);
        
        //Add Controls to modCustPane
        modCustomerPane.add(lblModCustomerHeader,0,0);
        modCustomerPane.add(lblModCustFname, 0, 1);
        modCustomerPane.add(lblModCustLname,0,2);
        modCustomerPane.add(lblModCustPhone,0,3);
        modCustomerPane.add(lblModCustAddress,0,4);
        modCustomerPane.add(txtModCustFname, 1, 1);
        modCustomerPane.add(txtModCustLname,1,2);
        modCustomerPane.add(txtModCustPhone,1,3);
        modCustomerPane.add(txtModCustAddress,1,4);
        modCustomerPane.add(vbModCustButtons,0,5);
        
        // Setting up TableViews
        TableView<Customer> custView = new TableView<>();
        ObservableList<Customer> custTableData = 
                FXCollections.observableArrayList(Customer.getCustArray());
        custView.setItems(custTableData);
        
        TableView<Customer> custLoyaltyView = new TableView<>();
        ObservableList<Customer> custLoyaltyTableData = 
                FXCollections.observableArrayList(Customer.getCustArray());
        custView.setItems(custLoyaltyTableData);
        
        TableView<Customer> custTransView = new TableView<>();
        ObservableList<Customer> custTransTableData = 
                FXCollections.observableArrayList(Customer.getCustArray());
        custTransView.setItems(custTransTableData);
        
        //Create Table Columns For TableViews   
        TableColumn tblcCustID = new TableColumn("Customer ID");
        TableColumn tblcCustFirstName = new TableColumn("First");
        TableColumn tblcCustLastName = new TableColumn("Last");
        
        // Table Columns for report
        TableColumn tblcCustOrderID = new TableColumn("Order ID");
        TableColumn tblcCustOrderQty = new TableColumn("Order Qty");
        TableColumn tblcCustStore = new TableColumn("Store ID");
        TableColumn tblcCustOrderDate = new TableColumn("Order Date");
         
        //Formatting and Adding Columns to TableViews
        custView.setMinWidth(300);
        custView.setMaxHeight(400);
        custView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        custView.getColumns().addAll(tblcCustID, tblcCustFirstName, tblcCustLastName);

        custLoyaltyView.setMinWidth(300);
        custLoyaltyView.setMaxHeight(400);
        custLoyaltyView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        custLoyaltyView.getColumns().addAll(tblcCustID, tblcCustFirstName, tblcCustLastName);

        custTransView.setMinWidth(500);
        custTransView.setMaxHeight(400);
        custTransView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        custTransView.getColumns().addAll(tblcCustID, tblcCustOrderID, tblcCustOrderQty, tblcCustStore, tblcCustOrderDate);
 
        //Adding Controls to custPane
        customerPane.add(lblCustHeader,0,0,3,1);
        customerPane.add(lblCustEnroll,1,1);
        customerPane.add(lblCustHist,2,1);
        customerPane.add(custView,0,2);
        customerPane.add(custLoyaltyView,1,2);
        customerPane.add(modCustomerPane,1,6);
        customerPane.add(custTransView,2,2);
        customerPane.add(lblCustDesc,0,5);
        customerPane.add(txtAreaCustDesc,0,6);
        customerPane.add(vbCustButtons,0,7);
        
        //Modify Button Functions
        btnModCust.setOnAction(e ->
        {
            modCustomerPane.setVisible(true); 
        });
        
        //Submit Customer Modification Functions
        btnModCustSubmit.setOnAction(e ->
        {
            modCustomerPane.setVisible(false);
            
        });
        
        //Clear Customer Modification Functions
        btnModCustClear.setOnAction(e ->
        {
            txtModCustFname.clear();
            txtModCustLname.clear();
            txtModCustPhone.clear();
            txtModCustAddress.clear();  
        });

        //******BOOK TAB INFORMATION*********************************************
        
        //Formatting Stuff
        bookPane.setHgap(20);
        bookPane.setVgap(5);
        modBookPane.setHgap(20);
        modBookPane.setVgap(5);
        modBookPane.setVisible(false);
        
        // Book Tab Header
        Label lblBookHeader = new Label ("Book Report");
        lblBookHeader.setFont(Font.font("Times New Roman", FontWeight.BOLD, 50));
        GridPane.setHalignment(lblBookHeader, HPos.CENTER);
        
        //Book Tab Labels
        Label lblTopFive = new Label("Top 5 Sellers: ");
        GridPane.setHalignment(lblTopFive, HPos.CENTER);
        Label lblBookDesc = new Label("Product Description: ");
        GridPane.setHalignment(lblBookDesc, HPos.CENTER);   
        Label lblBookHist = new Label("Transaction History ");
        GridPane.setHalignment(lblBookHist, HPos.CENTER);
        
        //Text Area outout for product description
        TextArea txtAreaDesc = new TextArea();
        txtAreaDesc.setMaxSize(300, 400);

        //Book Tab Buttons      
        Button btnModBook = new Button("Modify Book ->");
        Button btnRemoveBook = new Button("Remove Book ->");
        btnModBook.setMaxWidth(150);
        btnRemoveBook.setMaxWidth(150);

        VBox vbBookButtons = new VBox();
        vbBookButtons.setSpacing(10);
        vbBookButtons.setPadding(new Insets(0, 10, 10, 0));
        vbBookButtons.getChildren().addAll(btnModBook, btnRemoveBook);
        
        //modBookPane Controls
        Label lblModBookHeader = new Label("Modify Book:");
        lblModBookHeader.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
        GridPane.setHalignment(lblModBookHeader, HPos.CENTER);
        
        Label lblModBookTitle = new Label("Title: ");
        Label lblModBookAuthor = new Label("Author: ");
        Label lblModBookDesc = new Label("Description: ");
        Label lblModAcCost = new Label("Acquistion Cost: ");
        Label lblModSalePrice = new Label("Retail Price: ");
        
        TextField txtModBookTitle = new TextField();
        TextField txtModBookAuthor = new TextField();
        TextField txtModBookDesc = new TextField();
        TextField txtModAcCost = new TextField();
        TextField txtModSalePrice = new TextField();
        
        Button btnModBookSubmit = new Button("Submit");
        Button btnModBookClear = new Button("Clear");
        btnModBookSubmit.setMaxWidth(150);
        btnModBookClear.setMaxWidth(150);

        VBox vbModBookButtons = new VBox();
        vbModBookButtons.setSpacing(10);
        vbModBookButtons.setPadding(new Insets(0, 10, 10, 0));
        vbModBookButtons.getChildren().addAll(btnModBookSubmit, btnModBookClear);
        
        //adding controls to modBookPane
        modBookPane.add(lblModBookHeader,0,0);
        modBookPane.add(lblModBookTitle, 0, 1);
        modBookPane.add(lblModBookAuthor,0,2);
        modBookPane.add(lblModBookDesc,0,3);
        modBookPane.add(lblModAcCost,0,4);
        modBookPane.add(lblModSalePrice, 0, 5);
        
        modBookPane.add(txtModBookTitle, 1, 1);
        modBookPane.add(txtModBookAuthor,1,2);
        modBookPane.add(txtModBookDesc,1,3);
        modBookPane.add(txtModAcCost,1,4);
        modBookPane.add(txtModSalePrice, 1, 5);
        modBookPane.add(vbModBookButtons,0, 6);
         
        // create and add to book tableview for initial
        TableView<Book> bookView = new TableView<>();
        ObservableList<Book> bookTableData = 
                FXCollections.observableArrayList(Book.getBookArray());
        bookView.setItems(bookTableData);
        
        // create and add to book top 5 sellers
        TableView<Book> topSellerView = new TableView<>();
        ObservableList<Book> topSellerTableData = 
                FXCollections.observableArrayList(Book.getBookArray());
        bookView.setItems(topSellerTableData);
        
        // create and add to book Transactions
        TableView<Book> bookTransView = new TableView<>();
        ObservableList<Book> bookTransTableData = 
                FXCollections.observableArrayList(Book.getBookArray());
        bookView.setItems(bookTransTableData);

        //Create Table Columns For tableView(s)    
        TableColumn tblcBookID = new TableColumn("Book ID");
        TableColumn tblcBookTitle = new TableColumn("Title");
        TableColumn tblcBookAuthor = new TableColumn("Author");
        TableColumn tblcBookSold = new TableColumn("Units Sold");
        
        // Table Columns for report
        TableColumn tblcBookAcOrder = new TableColumn("Acq. Order(s)");
        TableColumn tblcBookCustOrder = new TableColumn("Cust. Order(s)");
        TableColumn tblcBookStore = new TableColumn("Store ID");
        TableColumn tblcBookInStock = new TableColumn("Store Inventory");
        
        //Format and add columns to bookView
        bookView.setMinWidth(200);
        bookView.setMaxHeight(400);
        bookView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        bookView.getColumns().addAll(tblcBookID, tblcBookTitle, tblcBookAuthor);
        
        // Format and add columns to topSellerView
        topSellerView.setMinWidth(300);
        topSellerView.setMaxHeight(400);
        topSellerView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        topSellerView.getColumns().addAll(tblcBookID, tblcBookTitle, tblcBookAuthor, tblcBookSold);
        
        // Format and add columns to bookTransView
        bookTransView.setMinWidth(500);
        bookTransView.setMaxHeight(400);
        bookTransView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        bookTransView.getColumns().addAll(tblcBookAcOrder, tblcBookCustOrder, tblcBookStore, tblcBookInStock);
 
        // add labels to bookPane
        bookPane.add(lblBookHeader,0,0,3,1);
        bookPane.add(lblTopFive,1,2);
        bookPane.add(bookView,0,3);
        bookPane.add(topSellerView,1,3);
        bookPane.add(lblBookHist,2,2);
        bookPane.add(bookTransView,2,3);
        bookPane.add(modBookPane,1,6);
        bookPane.add(lblBookDesc,0,5);
        bookPane.add(txtAreaDesc,0,6);
        bookPane.add(vbBookButtons,0,7);       
        
        //Modify Book Button Functions
        btnModBook.setOnAction(e ->
        {
            modBookPane.setVisible(true); 
        });
        
        //Modify Book Submit Button Functions
        btnModBookSubmit.setOnAction(e ->
        {
            modBookPane.setVisible(false);      
        });
        
        btnModBookClear.setOnAction(e ->
        {
            txtModBookTitle.clear();
            txtModBookAuthor.clear();
            txtModBookDesc.clear();
            txtModAcCost.clear();
            txtModSalePrice.clear();   
        });
        
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
        Pane blankSpace1 = new Pane();
        blankSpace1.setMinHeight(50);
        Pane blankSpace2 = new Pane();
        blankSpace2.setMinHeight(50);
        Pane blankSpace3 = new Pane();
        blankSpace3.setMinHeight(50);
                
        //TableView Setups
        TableView<Book> bookInventoryView = new TableView<>();
        ObservableList<Book> bookInventoryTableData = 
                FXCollections.observableArrayList(bookArray);
        bookInventoryView.setItems(bookInventoryTableData);
        
        TableView<Consumable> consumableInventoryView = new TableView<>();
        ObservableList<Consumable> consumableInventoryTableData = 
                FXCollections.observableArrayList(Consumable.getConsumableArray());
        consumableInventoryView.setItems(consumableInventoryTableData);
        
        //Create Table Columns For bookInventoryView    
        TableColumn tblcBookInvID = new TableColumn("Book ID");
        TableColumn tblcBookInvTitle = new TableColumn("Title");
        TableColumn tblcBookInvAuthor = new TableColumn("Author");
        TableColumn tblcBookQuantity = new TableColumn("Quantity");
        TableColumn tblcBookPrice = new TableColumn("Retail Price");
        TableColumn tblcBookCost = new TableColumn("Aquisition Cost");
        
        //Create Table Columns for consumableInventoryView
        TableColumn tblcConsInvID = new TableColumn("Consumable ID");
        TableColumn tblcConsInvName = new TableColumn("Name");
        TableColumn tblcConsQuantity = new TableColumn("Quantity");
        TableColumn tblcConsPrice = new TableColumn("Retail Price");
        TableColumn tblcConsCost = new TableColumn("Aquisition Cost");

        //Formatting TableViews
        bookInventoryView.setMinWidth(1000);
        bookInventoryView.setMaxHeight(320);
        bookInventoryView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        consumableInventoryView.setMinWidth(1000);
        consumableInventoryView.setMaxHeight(320);
        consumableInventoryView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
               
        tblcBookInvID.setCellValueFactory(new PropertyValueFactory<Book, Integer>("bookID"));
        tblcBookInvTitle.setCellValueFactory(new PropertyValueFactory<Book, String>("bookTitle"));
        tblcBookAuthor.setCellValueFactory(new PropertyValueFactory<Book, String>("bookAuthor"));
        tblcBookCost.setCellValueFactory(new PropertyValueFactory<Book, String>("bookCost"));
        tblcBookPrice.setCellValueFactory(new PropertyValueFactory<Book, Double>("bookSalePrice"));
        tblcBookQuantity.setCellValueFactory(new PropertyValueFactory<Book, Integer>("bookQuantity"));
       
        tblcConsInvID.setCellValueFactory(new PropertyValueFactory<Consumable, Integer>("conID"));
        tblcConsInvName.setCellValueFactory(new PropertyValueFactory<Consumable, String>("conName"));
        tblcConsQuantity.setCellValueFactory(new PropertyValueFactory<Consumable, Integer>("conQuantity"));
        tblcConsPrice.setCellValueFactory(new PropertyValueFactory<Consumable, Double>("conSalePrice"));
        tblcConsCost.setCellValueFactory(new PropertyValueFactory<Consumable, Double>("conCost"));
        
        //Adding Columns to TableViews
        bookInventoryView.getColumns().addAll(tblcBookInvID, tblcBookInvTitle, tblcBookAuthor,
                tblcBookQuantity, tblcBookCost, tblcBookPrice);
        
        consumableInventoryView.getColumns().addAll(tblcConsInvID, tblcConsInvName, tblcConsQuantity, 
                tblcConsCost, tblcConsPrice);
        
        //Adding Items to invPane
        invPane.add(lblInventoryHeader, 1, 0);
        invPane.add(blankSpace1, 1, 1);
        invPane.add(lblInvChooseStore, 1, 2);
        invPane.add(cmboInvChooseStore, 1, 3);
        invPane.add(lblBookInvHeader, 1, 4);
        invPane.add(bookInventoryView, 1, 5);
        invPane.add(blankSpace2, 1, 6);
        invPane.add(lblConsInvHeader, 1, 7);
        invPane.add(consumableInventoryView, 1,8);
        
        //******STORE TAB INFORMATION*******************************************************************************************
        
        // Store Tab Header
        Label lblStoreHeader = new Label ("Store Report");
        lblStoreHeader.setFont(Font.font("Times New Roman", FontWeight.BOLD, 50));
        GridPane.setHalignment(lblStoreHeader, HPos.CENTER);
        
        //Formatting
        storePane.setHgap(20);
        storePane.setVgap(5);
        modStorePane.setHgap(20);
        modStorePane.setVgap(5);
        modStorePane.setVisible(false);

        //Store Tab Labels
        Label lblEmployeeStore = new Label("Current Employees of Specified Store: ");
        GridPane.setHalignment(lblEmployeeStore, HPos.CENTER);
        Label lblStoreDesc = new Label("Store Description: ");
        GridPane.setHalignment(lblStoreDesc, HPos.CENTER);
        
        //Store Tab Controls
        TextArea txtAreaStoreDesc = new TextArea();
        txtAreaStoreDesc.setMaxSize(300, 400);
        
        Button btnModStore = new Button("Modify Store ->");
        Button btnRemoveStore = new Button("Remove Store ->");
        btnModStore.setMaxWidth(150);
        btnRemoveStore.setMaxWidth(150);

        VBox vbStoreButtons = new VBox();
        vbStoreButtons.setSpacing(10);
        vbStoreButtons.setPadding(new Insets(0, 10, 10, 0));
        vbStoreButtons.getChildren().addAll(btnModStore, btnRemoveStore);
        
        //modCustPane Controls
        Label lblModStoreHeader = new Label("Modify Store");
        lblModStoreHeader.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
        GridPane.setHalignment(lblModStoreHeader, HPos.CENTER);
        
        Label lblModStoreName = new Label("Store Name: ");
        Label lblModStoreAddress = new Label("Store Address: ");
        Label lblModStorePhone = new Label("Store phone: ");
        Label lblModStoreAreaID = new Label("Store Area ID: ");
        
        TextField txtModStoreName = new TextField();
        TextField txtModStoreAddress = new TextField();
        TextField txtModStorePhone = new TextField();
        TextField txtModStoreAreaID = new TextField();

        Button btnModStoreSubmit = new Button("Submit");
        Button btnModStoreClear = new Button("Clear");
        btnModStoreSubmit.setMaxWidth(150);
        btnModStoreClear.setMaxWidth(150);

        VBox vbModStoreButtons = new VBox();
        vbModStoreButtons.setSpacing(10);
        vbModStoreButtons.setPadding(new Insets(0, 10, 10, 0));
        vbModStoreButtons.getChildren().addAll(btnModStoreSubmit, btnModStoreClear);
        
        //Add Controls to modCustPane
        modStorePane.add(lblModStoreHeader,0,0);
        modStorePane.add(lblModStoreName, 0, 1);
        modStorePane.add(lblModStoreAddress,0,2);
        modStorePane.add(lblModStorePhone,0,3);
        modStorePane.add(lblModStoreAreaID,0,4);
        
        modStorePane.add(txtModStoreName, 1, 1);
        modStorePane.add(txtModStoreAddress,1,2);
        modStorePane.add(txtModStorePhone,1,3);
        modStorePane.add(txtModStoreAreaID,1,4);
        
        modStorePane.add(vbModStoreButtons,0,5);

        // create and add to store tableview for initial
        TableView<Store> storeView = new TableView<>();
        ObservableList<Store> storeTableData = 
                FXCollections.observableArrayList(Store.getStoreArray());
        storeView.setItems(storeTableData);
        
        TableView<Store> storeEmployeeView = new TableView<>();
        ObservableList<Store> storeEmployeeTableData = 
                FXCollections.observableArrayList(Store.getStoreArray());
        storeEmployeeView.setItems(storeEmployeeTableData);

        //Create Table Columns For intital tableView 
        TableColumn tblcStoreID = new TableColumn("Store ID");
        TableColumn tblcStoreName = new TableColumn("Store Name");
        TableColumn tblcStoreLocation = new TableColumn("Location");
        
        // Table Columns for employees at store tableview
        TableColumn tblcStoreEmployID = new TableColumn("EmployeeID");
        TableColumn tblcStoreEmployFname = new TableColumn("First Name");
        TableColumn tblcStoreEmployLname = new TableColumn("Last Name");
        TableColumn tblcStoreEmployEmail = new TableColumn("Emp Email");
        TableColumn tblcStoreEmployPhone = new TableColumn("Emp Phone");

        //Format and add Columns to StoreView
        storeView.setMinWidth(200);
        storeView.setMaxHeight(400);
        storeView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        storeView.getColumns().addAll(tblcStoreID, tblcStoreName, tblcStoreLocation);
        
        // Format and add columns to storeEmployeeView
        storeEmployeeView.setMinWidth(700);
        storeEmployeeView.setMaxHeight(400);
        storeEmployeeView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        storeEmployeeView.getColumns().addAll(tblcStoreID, tblcStoreEmployID, 
                                              tblcStoreEmployFname,tblcStoreEmployLname, 
                                              tblcStoreEmployEmail, tblcStoreEmployPhone);
 
        // add labels and tableviews to storePane
        storePane.add(lblStoreHeader,0,0,3,1);
        storePane.add(lblEmployeeStore,1,2);
        storePane.add(storeView,0,3);
        storePane.add(storeEmployeeView,1,3);
        storePane.add(modStorePane,1,6);
        storePane.add(lblStoreDesc,0,5);
        storePane.add(txtAreaStoreDesc,0,6);
        storePane.add(vbStoreButtons,0,7);
        
        //Modify Store Button Functions
        btnModStore.setOnAction(e ->
        {
            modStorePane.setVisible(true); 
        });
        
        //Modify Store Submit Button Functions
        btnModStoreSubmit.setOnAction(e ->
        {
            modStorePane.setVisible(false);
        });
        
        btnModStoreClear.setOnAction(e ->
        {
            txtModStoreName.clear();
            txtModStoreAddress.clear();
            txtModStorePhone.clear();
            txtModStoreAreaID.clear();   
        });
        
        //CREATE TAB INFORMATION

        Label lblCreateHeader = new Label ("Create New Object");
        lblCreateHeader.setFont(Font.font("Times New Roman", FontWeight.BOLD, 50));
        GridPane.setHalignment(lblInventoryHeader, HPos.CENTER);
        
        Label lblCreateSubHeader2 = new Label ("Individual");
        lblCreateSubHeader2.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
        GridPane.setHalignment(lblCreateSubHeader2, HPos.CENTER);
        
        Label lblCreateSubHeader3 = new Label ("Business Entities");
        lblCreateSubHeader3.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
        GridPane.setHalignment(lblCreateSubHeader3, HPos.CENTER);
        
        Label lblCreateSubHeader4 = new Label ("Items");
        lblCreateSubHeader4.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
        GridPane.setHalignment(lblCreateSubHeader4, HPos.CENTER);
        
        //Adding buttons and labels for choosing which entity to create
        ToggleGroup createGroup = new ToggleGroup();
        
        RadioButton rdoCreateCustomer = new RadioButton();
        RadioButton rdoCreateEmployee = new RadioButton();
        RadioButton rdoCreateStore = new RadioButton();
        RadioButton rdoCreateSupplier = new RadioButton();
        RadioButton rdoCreateBook = new RadioButton();
        RadioButton rdoCreateConsumable = new RadioButton();

        rdoCreateCustomer.setToggleGroup(createGroup);
        rdoCreateEmployee.setToggleGroup(createGroup);
        rdoCreateStore.setToggleGroup(createGroup);
        rdoCreateSupplier.setToggleGroup(createGroup);
        rdoCreateBook.setToggleGroup(createGroup);
        rdoCreateConsumable.setToggleGroup(createGroup);
        
        Label lblCreateCustomer = new Label ("Customer");
        Label lblCreateEmployee = new Label ("Employee");
        Label lblCreateStore = new Label ("Store");
        Label lblCreateSupplier = new Label ("Supplier");
        Label lblCreateBook = new Label ("Book");
        Label lblCreateConsumable = new Label ("Consumable");
        
        //This will be the GridPane that contains the first half of the page,
        //the radio buttons and labels.
        GridPane createPane1 = new GridPane();
       
        //Formatting columns for createPane1
        ColumnConstraints clmn1 = new ColumnConstraints();
        ColumnConstraints clmn2 = new ColumnConstraints();
        ColumnConstraints clmn3 = new ColumnConstraints();
        ColumnConstraints clmn4 = new ColumnConstraints();
        ColumnConstraints clmn5 = new ColumnConstraints();
        ColumnConstraints clmn6 = new ColumnConstraints();
        ColumnConstraints clmn7 = new ColumnConstraints();
        
        createPane1.getColumnConstraints().addAll(clmn1, clmn2, clmn3, clmn4, clmn5, clmn6);
        
        clmn1.setPercentWidth(16.67);
        clmn2.setPercentWidth(16.67);
        clmn3.setPercentWidth(16.67);
        clmn4.setPercentWidth(16.67);
        clmn5.setPercentWidth(16.67);
        clmn6.setPercentWidth(16.67);
        clmn7.setPercentWidth(100);
        
        createPane.getColumnConstraints().addAll(clmn7);
 
        //Adding the first half of the page to the main createPane
        createPane.add(createPane1, 0, 0);
        createPane1.setHgap(20);
        
        createPane1.add(lblCreateHeader, 2, 0, 3, 1);
        createPane1.add(blankSpace1, 2, 1);
        
        createPane1.add(lblCreateSubHeader2, 0, 4, 2, 1);
        createPane1.add(lblCreateSubHeader3, 2, 4, 2, 1);
        createPane1.add(lblCreateSubHeader4, 4, 4, 2, 1);
        
        createPane1.add(blankSpace2, 1, 5);
        
        createPane1.add(lblCreateCustomer, 0, 5);
        createPane1.add(rdoCreateCustomer, 1, 5);
        createPane1.add(lblCreateEmployee, 0, 6);
        createPane1.add(rdoCreateEmployee, 1, 6);
        
        //Adding and formatting Labels and Radio Buttons for first half of page
        GridPane.setHalignment(lblCreateCustomer, HPos.RIGHT);
        GridPane.setHalignment(rdoCreateCustomer, HPos.LEFT);
        GridPane.setHalignment(lblCreateEmployee, HPos.RIGHT);
        GridPane.setHalignment(rdoCreateEmployee, HPos.LEFT);
        
        createPane1.add(lblCreateStore, 2, 5);
        createPane1.add(rdoCreateStore, 3, 5);
        createPane1.add(lblCreateSupplier, 2, 6);
        createPane1.add(rdoCreateSupplier, 3, 6);
        
        GridPane.setHalignment(lblCreateStore, HPos.RIGHT);
        GridPane.setHalignment(rdoCreateStore, HPos.LEFT);
        GridPane.setHalignment(lblCreateSupplier, HPos.RIGHT);
        GridPane.setHalignment(rdoCreateSupplier, HPos.LEFT);
        
        createPane1.add(lblCreateBook, 4, 5);
        createPane1.add(rdoCreateBook, 5, 5);
        createPane1.add(lblCreateConsumable, 4, 6);
        createPane1.add(rdoCreateConsumable, 5, 6);
        
        GridPane.setHalignment(lblCreateBook, HPos.RIGHT);
        GridPane.setHalignment(rdoCreateBook, HPos.LEFT);
        GridPane.setHalignment(lblCreateConsumable, HPos.RIGHT);
        GridPane.setHalignment(rdoCreateConsumable, HPos.LEFT);
        
        //These GridPanes will make up the second half of the page, where
        //the user will be able to add an entity
        
        addCustPane.setVisible(false);
        addEmployeePane.setVisible(false);
        addStorePane.setVisible(false);
        addSupplierPane.setVisible(false);
        addBookPane.setVisible(false);
        addConsumablePane.setVisible(false);
        
        //Never-ending formatting
        addCustPane.setAlignment(Pos.TOP_CENTER);
        addCustPane.setHgap(20);
        addCustPane.setVgap(5);
        addEmployeePane.setAlignment(Pos.TOP_CENTER);
        addEmployeePane.setHgap(20);
        addEmployeePane.setVgap(5);
        addStorePane.setAlignment(Pos.TOP_CENTER);
        addStorePane.setHgap(20);
        addStorePane.setVgap(5);
        addSupplierPane.setAlignment(Pos.TOP_CENTER);
        addSupplierPane.setHgap(20);
        addSupplierPane.setVgap(5);
        addBookPane.setAlignment(Pos.TOP_CENTER);
        addBookPane.setHgap(20);
        addBookPane.setVgap(5);
        addConsumablePane.setAlignment(Pos.TOP_CENTER);
        addConsumablePane.setHgap(20);
        addConsumablePane.setVgap(5);
        
        createPane.add(blankSpace3, 0, 1);
        
        //TextArea Field for error notification       
        TextArea addOutput = new TextArea();
        addOutput.setMaxSize(500, 50);
        GridPane.setHalignment(addOutput, HPos.CENTER);
        addOutput.setVisible(false);
        addOutput.setEditable(false);
             
        //Adding the lower panes to the main createPane
        createPane.add(addCustPane, 0, 2);
        createPane.add(addEmployeePane, 0, 2);
        createPane.add(addStorePane, 0, 2);
        createPane.add(addSupplierPane, 0, 2);
        createPane.add(addBookPane, 0, 2);
        createPane.add(addConsumablePane, 0, 2);
        
        createPane.add(blankSpace1, 0, 3);
        createPane.add(addOutput, 0, 4);
        
        //Setting up add customer pane for second half of page
        Label lblAddCust = new Label ("Add New Customer");
        lblAddCust.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
        GridPane.setHalignment(lblAddCust, HPos.RIGHT);
        
        Label lblAddCustFN = new Label ("First Name: ");
        Label lblAddCustLN = new Label ("Last Name: ");
        Label lblAddCustPhone = new Label ("Phone Number: ");
        Label lblAddCustAddress = new Label ("Address: ");
        
        TextField txtAddCustFN = new TextField();
        TextField txtAddCustLN = new TextField();
        TextField txtAddCustPhone = new TextField();
        TextField txtAddCustAddress = new TextField();
        
        Button btnAddCust = new Button ("Add Customer");
        
        addCustPane.add(lblAddCust, 0, 0);
        addCustPane.add(lblAddCustFN, 0, 1);
        addCustPane.add(lblAddCustLN, 0, 2);
        addCustPane.add(lblAddCustPhone, 0, 3);
        addCustPane.add(lblAddCustAddress, 0, 4);
        
        addCustPane.add(txtAddCustFN, 1, 1);
        addCustPane.add(txtAddCustLN, 1, 2);
        addCustPane.add(txtAddCustPhone, 1, 3);
        addCustPane.add(txtAddCustAddress, 1, 4);
        
        addCustPane.add(btnAddCust, 1, 5);
        
        //Setting up add employee pane for second half of page
        Label lblAddEmployee = new Label ("Add New Employee");
        lblAddEmployee.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
        GridPane.setHalignment(lblAddEmployee, HPos.RIGHT);
        
        Label lblAddEmployeeFN = new Label ("First Name: ");
        Label lblAddEmployeeLN = new Label ("Last Name: ");
        Label lblAddEmployeePhone = new Label ("Phone Number: ");
        Label lblAddEmployeePay = new Label ("Pay Rate: ");
        
        TextField txtAddEmployeeFN = new TextField();
        TextField txtAddEmployeeLN = new TextField();
        TextField txtAddEmployeePhone = new TextField();
        TextField txtAddEmployeePay = new TextField();
        
        Button btnAddEmployee = new Button ("Add Employee");
        
        addEmployeePane.add(lblAddEmployee, 0, 0);
        addEmployeePane.add(lblAddEmployeeFN, 0, 1);
        addEmployeePane.add(lblAddEmployeeLN, 0, 2);
        addEmployeePane.add(lblAddEmployeePhone, 0, 3);
        addEmployeePane.add(lblAddEmployeePay, 0, 4);
        
        addEmployeePane.add(txtAddEmployeeFN, 1, 1);
        addEmployeePane.add(txtAddEmployeeLN, 1, 2);
        addEmployeePane.add(txtAddEmployeePhone, 1, 3);
        addEmployeePane.add(txtAddEmployeePay, 1, 4);
        
        addEmployeePane.add(btnAddEmployee, 1, 5);
        
        //Setting up add Store pane for second half of page
        Label lblAddStore = new Label ("Add New Store");
        lblAddStore.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
        GridPane.setHalignment(lblAddStore, HPos.RIGHT);
        
        Label lblAddStoreName = new Label("Store Name: ");
        Label lblAddStoreAddress = new Label("Store Address: ");
        Label lblAddStorePhone = new Label("Store Phone Number: ");
        Label lblAddStoreAreaID = new Label("Store Area ID: ");
        
        TextField txtAddStoreName = new TextField();
        TextField txtAddStoreAddress = new TextField();
        TextField txtAddStorePhone = new TextField();
        TextField txtAddStoreAreaID = new TextField();
        
        Button btnAddStore = new Button("Add Store");
        
        addStorePane.add(lblAddStore, 0, 0);
        
        addStorePane.add(lblAddStoreName, 0, 1);
        addStorePane.add(lblAddStoreAddress, 0, 2);
        addStorePane.add(lblAddStorePhone, 0, 3);
        addStorePane.add(lblAddStoreAreaID, 0, 4);
        
        addStorePane.add(txtAddStoreName, 1, 1);
        addStorePane.add(txtAddStoreAddress, 1, 2);
        addStorePane.add(txtAddStorePhone, 1, 3);
        addStorePane.add(txtAddStoreAreaID, 1, 4);
        
        addStorePane.add(btnAddStore, 1, 5);
        
        //Setting up add Supplier pane for second half of page
        Label lblAddSupplier = new Label ("Add New Supplier");
        lblAddSupplier.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
        GridPane.setHalignment(lblAddSupplier, HPos.RIGHT);
        
        Label lblAddSupplierName = new Label("Supplier Name: ");
        Label lblAddSupplierAddress = new Label("Supplier Address: ");
        Label lblAddSupplierRep = new Label ("Supplier Contact: ");
        
        TextField txtAddSupplierName = new TextField ();
        TextField txtAddSupplierAddress = new TextField ();
        
        ComboBox cmboAddSupplierRep = new ComboBox();
        
        Button btnAddSupplier = new Button ("Add Supplier");
        
        addSupplierPane.add(lblAddSupplier, 0, 0);
        
        addSupplierPane.add(lblAddSupplierName, 0, 1);
        addSupplierPane.add(lblAddSupplierAddress, 0, 2);
        addSupplierPane.add(lblAddSupplierRep, 0, 3);
        
        addSupplierPane.add(txtAddSupplierName, 1, 1);
        addSupplierPane.add(txtAddSupplierAddress, 1, 2);
        addSupplierPane.add(cmboAddSupplierRep, 1, 3);
        
        addSupplierPane.add(btnAddSupplier, 1, 4);
        
        //Setting up add Book pane for second half of page
        Label lblAddBook = new Label ("Add New Book");
        lblAddBook.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
        GridPane.setHalignment(lblAddBook, HPos.RIGHT);
        
        Label lblAddBookTitle = new Label ("Title: ");
        Label lblAddBookAuthor = new Label ("Author: ");
        Label lblAddBookDesc = new Label ("Description ");
        Label lblAddBookCost = new Label ("Aquisition Cost: ");
        Label lblAddBookSalePrice = new Label ("Retail Price: ");
        Label lblAddBookQuantity = new Label ("Quantity to add: ");
        
        TextField txtAddBookTitle = new TextField();
        TextField txtAddBookAuthor = new TextField();
        TextField txtAddBookDesc = new TextField();
        TextField txtAddBookCost = new TextField();
        TextField txtAddBookSalePrice = new TextField();
        TextField txtAddBookQuantity = new TextField();
        
        Button btnAddBook = new Button ("Add Book");
        
        addBookPane.add(lblAddBook, 0, 0);
        
        addBookPane.add(lblAddBookTitle, 0, 1);
        addBookPane.add(lblAddBookAuthor, 0, 2);
        addBookPane.add(lblAddBookDesc, 0, 3);
        addBookPane.add(lblAddBookCost, 0, 4);
        addBookPane.add(lblAddBookSalePrice, 0, 5);
        addBookPane.add(lblAddBookQuantity, 0, 6);
        
        addBookPane.add(txtAddBookTitle, 1, 1);
        addBookPane.add(txtAddBookAuthor, 1, 2);
        addBookPane.add(txtAddBookDesc, 1, 3);
        addBookPane.add(txtAddBookCost, 1, 4);
        addBookPane.add(txtAddBookSalePrice, 1, 5);
        addBookPane.add(txtAddBookQuantity, 1, 6);
        
        addBookPane.add(btnAddBook, 1, 7);
        
        btnAddBook.setOnAction (e -> {
            try
            { 
                boolean toAddFlag = true;
                addOutput.setVisible(true);

                for (Book b: bookArray)
                {
                    if (txtAddBookTitle.getText().matches(b.getBookTitle()) 
                            && txtAddBookAuthor.getText().matches(b.getBookAuthor()))
                    {
                        addOutput.setText("Book is already in inventory. Updating quantity.");
                        int newQuantity = b.getBookQuantity();
                        newQuantity = newQuantity + Integer.parseInt(txtAddBookQuantity.getText());
                        b.setBookQuantity(newQuantity);
                        
                        toAddFlag = false;
                    }
                }
                    
                if (toAddFlag == true)
                {
                    Book.newBook(txtAddBookTitle.getText(), txtAddBookAuthor.getText(),
                    Double.parseDouble(txtAddBookCost.getText()),
                    Double.parseDouble(txtAddBookSalePrice.getText()), 
                    txtAddBookDesc.getText(), Integer.parseInt(txtAddBookQuantity.getText()));
                   
                    addOutput.setText("Book successfully added.");
                    
                }

                txtAddBookTitle.clear();
                txtAddBookAuthor.clear();
                txtAddBookCost.clear();
                txtAddBookSalePrice.clear();
                txtAddBookDesc.clear();
                txtAddBookQuantity.clear();
                
                bookInventoryTableData.clear();
                
                for (Book b: bookArray)
                    bookInventoryTableData.add(b);
                
                bookInventoryView.setItems(bookInventoryTableData);
            }
            
            catch(NumberFormatException n)
            {
                addOutput.setVisible(true);
                addOutput.setText("Please ensure that Aquisition Cost, Retail Price"
                        + ", and quantity are numbers.");
            }
        });
        
        //Setting up add Consumable pane for second half of page
        Label lblAddConsumable = new Label ("Add New Consumable");
        lblAddConsumable.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
        GridPane.setHalignment(lblAddConsumable, HPos.RIGHT);
        
        Label lblAddConsName = new Label ("Name: ");
        Label lblAddConsDesc = new Label ("Description: ");
        Label lblAddConsCost = new Label ("Aquisition Cost: ");
        Label lblAddConsSalePrice= new Label ("Retail Price: ");
        Label lblAddConsQuantity = new Label ("Quantity to add: ");
        
        TextField txtAddConsName = new TextField ();
        TextField txtAddConsDesc = new TextField ();
        TextField txtAddConsCost = new TextField ();
        TextField txtAddConsSalePrice = new TextField ();
        TextField txtAddConsQuantity = new TextField ();
        
        Button btnAddConsumable = new Button("Add Consumable");
        
        addConsumablePane.add(lblAddConsumable, 0, 0);
        
        addConsumablePane.add(lblAddConsName, 0, 1);
        addConsumablePane.add(lblAddConsDesc, 0, 2);
        addConsumablePane.add(lblAddConsCost, 0, 3);
        addConsumablePane.add(lblAddConsSalePrice, 0, 4);
        addConsumablePane.add(lblAddConsQuantity, 0, 5);
        
        addConsumablePane.add(txtAddConsName, 1, 1);
        addConsumablePane.add(txtAddConsDesc, 1, 2);
        addConsumablePane.add(txtAddConsCost, 1, 3);
        addConsumablePane.add(txtAddConsSalePrice, 1, 4);
        addConsumablePane.add(txtAddConsQuantity, 1, 5);
        
        addConsumablePane.add(btnAddConsumable, 1, 6);
        
        btnAddConsumable.setOnAction (e -> {
            try
            { 
                addOutput.setVisible(true);
                boolean toAddFlag = true;

                for (Consumable c: consumableArray)
                {
                    if (txtAddConsName.getText().matches(c.getConName()) 
                            && txtAddConsDesc.getText().matches(c.getConDesc()))
                    {
                        addOutput.setText("Consumable is already in inventory. Updating quantity.");
                        int newQuantity = c.getConQuantity();
                        newQuantity = newQuantity + Integer.parseInt(txtAddConsQuantity.getText());
                        c.setConQuantity(newQuantity);
                        
                        toAddFlag = false;
                    }
                }
                    
                if (toAddFlag == true)
                {
                    Consumable.newCon(txtAddConsName.getText(), Double.parseDouble(txtAddConsCost.getText()),
                    Double.parseDouble(txtAddConsSalePrice.getText()), 
                    txtAddConsDesc.getText(), Integer.parseInt(txtAddConsQuantity.getText()));
                    
                    addOutput.setText("Consumable successfully added.");
                }

                txtAddConsName.clear();
                txtAddConsCost.clear();
                txtAddConsSalePrice.clear();
                txtAddConsDesc.clear();
                txtAddConsQuantity.clear();
                
                consumableInventoryTableData.clear();
                
                for (Consumable c: consumableArray)
                {
                    consumableInventoryTableData.add(c);
                    System.out.println(consumableInventoryTableData);
                }
                
                consumableInventoryView.setItems(consumableInventoryTableData);
            }
            
            catch(NumberFormatException n)
            {
                addOutput.setText("Please ensure that Aquisition Cost, Retail Price"
                        + ", and quantity are numbers.");
            }
        
        });
              
        //Radio Button Handlers to hide or display the desired pane to add objects
        rdoCreateCustomer.setOnAction (e -> {
            if (rdoCreateCustomer.isSelected())
            {   
                hideAddPanes();
                addCustPane.setVisible(true);
                addOutput.setVisible(false);
            }
        });
        
        rdoCreateEmployee.setOnAction (e -> {
            if (rdoCreateEmployee.isSelected())
            {       
                hideAddPanes();
                addEmployeePane.setVisible(true);
                addOutput.setVisible(false);
            }
        }); 
        
        rdoCreateStore.setOnAction (e -> {
            if (rdoCreateStore.isSelected())
            {       
                hideAddPanes();
                addStorePane.setVisible(true);
                addOutput.setVisible(false);
            }
        });   
        
        rdoCreateSupplier.setOnAction (e -> {
            if (rdoCreateSupplier.isSelected())
            {       
                hideAddPanes();
                addSupplierPane.setVisible(true);
                addOutput.setVisible(false);
            }
        });  
        
        rdoCreateBook.setOnAction (e -> {
            if (rdoCreateBook.isSelected())
            {       
                hideAddPanes();
                addBookPane.setVisible(true);
                addOutput.setVisible(false);
            }
        });
        
        rdoCreateConsumable.setOnAction (e -> {
            if (rdoCreateConsumable.isSelected())
            {       
                hideAddPanes();
                addConsumablePane.setVisible(true);
                addOutput.setVisible(false);
            }
        });  
    } 
    
    public void hideAddPanes()
    {
        addCustPane.setVisible(false);
        addEmployeePane.setVisible(false);
        addStorePane.setVisible(false);
        addSupplierPane.setVisible(false);
        addBookPane.setVisible(false);
        addConsumablePane.setVisible(false);
    }
}

