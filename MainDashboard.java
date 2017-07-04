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
        
        createPane.setAlignment(Pos.TOP_CENTER);
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
        
        //BOOK TAB INFORMATION
        
        // Book Header
        Label lblBookHeader = new Label ("Book Report:");
        lblBookHeader.setFont(Font.font("Times New Roman", FontWeight.BOLD, 50));
        GridPane.setHalignment(lblBookHeader, HPos.CENTER);
        
        // book tab labels
        Label lblTopFive = new Label("Top 5 Sellers: ");
        GridPane.setHalignment(lblTopFive, HPos.CENTER);
        
        Label lblBookDesc = new Label("Product Description: ");
        GridPane.setHalignment(lblBookDesc, HPos.CENTER);
        
        Label lblBookHist = new Label("Transaction History ");
        GridPane.setHalignment(lblBookHist, HPos.CENTER);
        
        // Text Area outout for product description
        TextArea txtAreaDesc = new TextArea();
        txtAreaDesc.setMaxSize(300, 400);

        
        // Book Buttons
        Button btnModBook = new Button("Modify Book ->");
        btnModBook.setScaleX(1);
        Button btnRemoveBook = new Button("Remove Book ->");
        btnRemoveBook.setScaleX(1);
        
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
        
        
        // format and add columns to bookView
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
        bookPane.add(lblBookDesc,0,5);
        bookPane.add(txtAreaDesc,0,6);
        bookPane.add(btnModBook,0,7);         
        bookPane.add(btnRemoveBook,0,9);
        
        //CUSTOMER TAB INFORMATION
        
        // Cust Header
        Label lblCustHeader = new Label ("Customer Report:");
        lblCustHeader.setFont(Font.font("Times New Roman", FontWeight.BOLD, 50));
        GridPane.setHalignment(lblCustHeader, HPos.CENTER);
        
        // cust tab labels
        Label lblCustDesc = new Label("Customer Description: ");
        GridPane.setHalignment(lblCustDesc, HPos.CENTER);
        
        Label lblCustHist = new Label("Transaction History: ");
        GridPane.setHalignment(lblCustHist, HPos.CENTER);
        
        Label lblCustEnroll = new Label("Enrolled in Loyalty Program: ");
        GridPane.setHalignment(lblCustEnroll, HPos.CENTER);
        
        // Text Area outout for product description
        TextArea txtAreaCustDesc = new TextArea();
        txtAreaCustDesc.setMaxSize(300, 400);
        
        // Label Blank

        
        // Cust Buttons
        Button btnModCust = new Button("Modify Customer ->");
        btnModCust.setScaleX(1);
        Button btnRemoveCust = new Button("Remove Customer ->");
        btnRemoveCust.setScaleX(1);
        Button btnEnrollCust = new Button("Enroll Customer ->");
        btnEnrollCust.setScaleX(1);
        
        // create and add to cust tableview for initial
        TableView<Customer> custView = new TableView<>();
        ObservableList<Customer> custTableData = 
                FXCollections.observableArrayList(Customer.getCustArray());
        custView.setItems(custTableData);
        
        // create and add to cust loyalaty
        TableView<Customer> custLoyaltyView = new TableView<>();
        ObservableList<Customer> custLoyaltyTableData = 
                FXCollections.observableArrayList(Customer.getCustArray());
        custView.setItems(custLoyaltyTableData);
        
        
        // create and add to cust trans history
        TableView<Customer> custTransView = new TableView<>();
        ObservableList<Customer> custTransTableData = 
                FXCollections.observableArrayList(Customer.getCustArray());
        custTransView.setItems(custTransTableData);
        
        
        //Create Table Columns For tableView(s)    
        TableColumn tblcCustID = new TableColumn("Customer ID");
        TableColumn tblcCustFirstName = new TableColumn("First");
        TableColumn tblcCustLastName = new TableColumn("Last");
        
        // Table Columns for report
        TableColumn tblcCustOrderID = new TableColumn("Order ID");
        TableColumn tblcCustOrderQty = new TableColumn("Order Qty");
        TableColumn tblcCustStore = new TableColumn("Store ID");
        TableColumn tblcCustOrderDate = new TableColumn("Order Date");
        
        
        // format and add columns to custView
        custView.setMinWidth(300);
        custView.setMaxHeight(400);
        custView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        custView.getColumns().addAll(tblcCustID, tblcCustFirstName, tblcCustLastName);
        
        // Format and add columns to custloyaltyView
        custLoyaltyView.setMinWidth(300);
        custLoyaltyView.setMaxHeight(400);
        custLoyaltyView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        custLoyaltyView.getColumns().addAll(tblcCustID, tblcCustFirstName, tblcCustLastName);
        
        // Format and add columns to custTransView
        custTransView.setMinWidth(500);
        custTransView.setMaxHeight(400);
        custTransView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        custTransView.getColumns().addAll(tblcCustID, tblcCustOrderID, tblcCustOrderQty, tblcCustStore, tblcCustOrderDate);
 
        // add labels to custPane
        customerPane.add(lblCustHeader,0,0,3,1);
        customerPane.add(lblCustEnroll,1,1);
        customerPane.add(lblCustHist,2,1);
        customerPane.add(custView,0,2);
        customerPane.add(custLoyaltyView,1,2);
        customerPane.add(custTransView,2,2);
        customerPane.add(lblCustDesc,0,5);
        customerPane.add(txtAreaCustDesc,0,6);
        customerPane.add(btnModCust,0,7);
        customerPane.add(btnRemoveCust,0,8);
        customerPane.add(btnEnrollCust,0,9);
        
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
        invPane.add(blankSpace1, 1, 1);
        invPane.add(lblInvChooseStore, 1, 2);
        invPane.add(cmboInvChooseStore, 1, 3);
        invPane.add(lblBookInvHeader, 1, 4);
        invPane.add(bookInventoryView, 1, 5);
        invPane.add(blankSpace2, 1, 6);
        invPane.add(lblConsInvHeader, 1, 7);
        invPane.add(consumableInventoryView, 1,8);
        
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
        
        Label lblCreateSubHeader4 = new Label ("Orders");
        lblCreateSubHeader4.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
        GridPane.setHalignment(lblCreateSubHeader4, HPos.CENTER);
        
        //Adding buttons and labels for choosing which entity to create
        RadioButton rdoCreateCustomer = new RadioButton();
        RadioButton rdoCreateEmployee = new RadioButton();
        RadioButton rdoCreateStore = new RadioButton();
        RadioButton rdoCreateSupplier = new RadioButton();
        RadioButton rdoCreateCustomerOrder = new RadioButton();
        RadioButton rdoCreateAquisitionOrder = new RadioButton();
        
        Label lblCreateCustomer = new Label ("Customer");
        Label lblCreateEmployee = new Label ("Employee");
        Label lblCreateStore = new Label ("Store");
        Label lblCreateSupplier = new Label ("Supplier");
        Label lblCreateCustomerOrder = new Label ("Customer Order");
        Label lblCreateAquisitionOrder = new Label ("Aquisition Order");
        
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
        
        createPane1.add(lblCreateCustomerOrder, 4, 5);
        createPane1.add(rdoCreateCustomerOrder, 5, 5);
        createPane1.add(lblCreateAquisitionOrder, 4, 6);
        createPane1.add(rdoCreateAquisitionOrder, 5, 6);
        
        GridPane.setHalignment(lblCreateCustomerOrder, HPos.RIGHT);
        GridPane.setHalignment(rdoCreateCustomerOrder, HPos.LEFT);
        GridPane.setHalignment(lblCreateAquisitionOrder, HPos.RIGHT);
        GridPane.setHalignment(rdoCreateAquisitionOrder, HPos.LEFT);
        
        //These GridPanes will make up the second half of the page, where
        //the user will be able to add an entity
        GridPane addCustPane = new GridPane();
        GridPane addEmployeePane = new GridPane();
        GridPane addStorePane = new GridPane();
        GridPane addSupplierPane = new GridPane();
        GridPane addCustomerOrderPane = new GridPane();
        GridPane addAqOrderPane = new GridPane();
        
        addCustPane.setVisible(true);
        addEmployeePane.setVisible(true);
        addStorePane.setVisible(false);
        addSupplierPane.setVisible(false);
        addCustomerOrderPane.setVisible(false);
        addAqOrderPane.setVisible(false);
          
        //Never-ending formatting
        addCustPane.setAlignment(Pos.TOP_CENTER);
        addCustPane.setHgap(20);
        addCustPane.setVgap(20);
        
        addEmployeePane.setAlignment(Pos.TOP_CENTER);
        addEmployeePane.setHgap(20);
        addStorePane.setAlignment(Pos.TOP_CENTER);
        addStorePane.setHgap(20);
        addSupplierPane.setAlignment(Pos.TOP_CENTER);
        addSupplierPane.setHgap(20);
        addCustomerOrderPane.setAlignment(Pos.TOP_CENTER);
        addCustomerOrderPane.setHgap(20);
        addAqOrderPane.setAlignment(Pos.TOP_CENTER);
        addAqOrderPane.setHgap(20);
        
        createPane.add(blankSpace3, 0, 1);
        
        //Adding the lower panes to the main createPane
        createPane.add(addCustPane, 0, 2);
        createPane.add(addEmployeePane, 0, 3);
        
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
        
        addCustPane.add(lblAddCust, 0, 0);
        addCustPane.add(lblAddCustFN, 0, 1);
        addCustPane.add(lblAddCustLN, 0, 2);
        addCustPane.add(lblAddCustPhone, 0, 3);
        addCustPane.add(lblAddCustAddress, 0, 4);
        
        addCustPane.add(txtAddCustFN, 1, 1);
        addCustPane.add(txtAddCustLN, 1, 2);
        addCustPane.add(txtAddCustPhone, 1, 3);
        addCustPane.add(txtAddCustAddress, 1, 4);
        
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
        
        addEmployeePane.add(lblAddEmployee, 0, 0);
        addEmployeePane.add(lblAddEmployeeFN, 0, 1);
        addEmployeePane.add(lblAddEmployeeLN, 0, 2);
        addEmployeePane.add(lblAddEmployeePhone, 0, 3);
        addEmployeePane.add(lblAddEmployeePay, 0, 4);
        
        addEmployeePane.add(txtAddEmployeeFN, 1, 1);
        addEmployeePane.add(txtAddEmployeeLN, 1, 2);
        addEmployeePane.add(txtAddEmployeePhone, 1, 3);
        addEmployeePane.add(txtAddEmployeePay, 1, 4);
        
        
        
    }
}
