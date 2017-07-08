package BookIt_IS;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ConcurrentModificationException; 
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.text.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent; 
import javafx.scene.paint.Color; 
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

public class MainDashboard {

    //Creating ArrayLists
    private static ArrayList<Book> bookArray = Book.getBookArray();
    private static ArrayList<Consumable> consumableArray = Consumable.getConsumableArray();
    private static ArrayList<Customer> customerArray = Customer.getCustArray();
    private static ArrayList <Employee> employeeArray = Employee.getEmployArray();
    private static ArrayList<Store> storeArray = Store.getStoreArray();
    private static ArrayList<Supplier> supplierArray = Supplier.getSupplierArray(); 
    private static ArrayList<Time_Management> timeArray = Time_Management.getTimeArray();
    private static ArrayList<Book> bookInvArray = new ArrayList<>();
    private static ArrayList<Consumable> consInvArray = new ArrayList<>(); 
    private static ArrayList<Customer> custLoyaltyArray = new ArrayList<>(); 
    private static ArrayList<Spinner> bookSpinArray = new ArrayList<>(); 
    private static ArrayList<Spinner> conSpinArray = new ArrayList<>();
    ObservableList<String> invTabStoreCombo = FXCollections.observableArrayList();
    
    
    
    
    //Creating Panes
    GridPane overallPane = new GridPane();

    GridPane salesPane = new GridPane();

    GridPane customerPane = new GridPane();
    GridPane modCustomerPane = new GridPane();

    GridPane employeePane = new GridPane(); 
    GridPane modEmployeePane = new GridPane();
    

    GridPane bookPane = new GridPane();
    GridPane modBookPane = new GridPane();
    

    GridPane profitPane = new GridPane();

    GridPane invPane = new GridPane();
    GridPane invHeaderPane = new GridPane();

    GridPane createPane = new GridPane();

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
    GridPane addPOSPane = new GridPane(); 
    GridPane addProductOrderPane = new GridPane();
    //Global controls for ease of updates
    ComboBox cmboInvChooseStore = new ComboBox();
    ComboBox cmboAddChooseStore = new ComboBox();
    ComboBox cmboAddSupplierRep = new ComboBox();

    ComboBox cmboAddChooseStore2 = new ComboBox(); 
    ComboBox cmboChooseEmployeeStore = new ComboBox();
    ComboBox cmboAddPOSCustomer = new ComboBox(); 
    ComboBox cmboAddPOSStore = new ComboBox(); 
    ComboBox cmboAddProductOrderStore = new ComboBox(); 
    ComboBox cmboAddProductOrderSupplier = new ComboBox();  
    TextArea txtAreaCustDesc = new TextArea(); 
    Menu mnuCategories = new Menu("Product Type...."); 
    MenuBar mnuBar = new MenuBar();  
    Menu mnuBook = new Menu("Book..."); 
    Menu mnuConsumable = new Menu("Consumable..."); 
    Label lblAddPOSTax = new Label("Tax (5.30%):"); 
    Label lblAddPOSTotal = new Label("Total:"); 
    Label lblAddPOSTaxAmount = new Label(); 
    Label lblAddPOSTotalAmount = new Label(); 
    Label lblAddPOSSub = new Label("Sub Total:"); 
    Label lblAddPOSSubAmount = new Label();
    int posPaneControl = 4;  
    int productOrderPaneControl = 4;
    Button btnAddPOS = new Button("Add POS"); 
    Button btnAddProductOrder = new Button("Add Product Order");

    //Main Scene
    Scene primaryScene = new Scene(overallPane, 1200, 1000);
    Stage primaryStage = new Stage();

    // Setting up CustomerTableViews
    TableView<Customer> custView = new TableView<>();
    ObservableList<Customer> custTableData
            = FXCollections.observableArrayList(Customer.getCustArray());

    TableView<Customer> custLoyaltyView = new TableView<>();
    ObservableList<Customer> custLoyaltyTableData
            = FXCollections.observableArrayList(Customer.getCustArray());

    TableView<Customer> custTransView = new TableView<>();
    ObservableList<Customer> custTransTableData
            = FXCollections.observableArrayList(Customer.getCustArray()); 
    
    // Setting up employee tables
    TableView<Employee> employView = new TableView<>(); 
     ObservableList<Employee> employTableData 
             = FXCollections.observableArrayList(employeeArray); 
    private static TableView<Time_Management> timeView = new TableView<>(); 
        private static ObservableList<Time_Management> timeTableData 
            = FXCollections.observableArrayList(Time_Management.getTimeArray()); 

    //Setting Up StoreTableViews
    TableView<Store> storeView = new TableView<>();
    ObservableList<Store> storeTableData
            = FXCollections.observableArrayList(Store.getStoreArray());

    TableView<Store> storeEmployeeView = new TableView<>();
    ObservableList<Store> storeEmployeeTableData
            = FXCollections.observableArrayList(Store.getStoreArray()); 
    

    public MainDashboard() {
        //Formatting Panes
        overallPane.setAlignment(Pos.TOP_CENTER);
        overallPane.setHgap(20);
        overallPane.setVgap(5);
        overallPane.setMinSize(primaryScene.getWidth(), primaryScene.getHeight());

        salesPane.setAlignment(Pos.TOP_CENTER);
        salesPane.setHgap(20);
        salesPane.setVgap(5);
        salesPane.setMinSize(primaryScene.getWidth(), primaryScene.getHeight());

        customerPane.setAlignment(Pos.TOP_CENTER);
        customerPane.setHgap(20);
        customerPane.setVgap(5);
        customerPane.setMinSize(primaryScene.getWidth(), primaryScene.getHeight());

        employeePane.setAlignment(Pos.TOP_CENTER);
        employeePane.setHgap(20);
        employeePane.setHgap(5);
        employeePane.setMinSize(primaryScene.getWidth(), primaryScene.getHeight());

        bookPane.setAlignment(Pos.TOP_CENTER);
        bookPane.setHgap(20);
        bookPane.setVgap(5);
        bookPane.setMinSize(primaryScene.getWidth(), primaryScene.getHeight());

        profitPane.setAlignment(Pos.TOP_CENTER);
        profitPane.setHgap(20);
        profitPane.setVgap(5);
        profitPane.setMinSize(primaryScene.getWidth(), primaryScene.getHeight());

        invPane.setAlignment(Pos.TOP_CENTER);
        invPane.setHgap(20);
        invPane.setVgap(10);
        invPane.setMinSize(primaryScene.getWidth(), primaryScene.getHeight());

        createPane.setAlignment(Pos.TOP_CENTER);
        createPane.setHgap(20);
        createPane.setVgap(5);
        createPane.setMinSize(primaryScene.getWidth(), primaryScene.getHeight());

        storePane.setAlignment(Pos.TOP_CENTER);
        storePane.setHgap(20);
        storePane.setHgap(5);
        storePane.setMinSize(primaryScene.getWidth(), primaryScene.getHeight());

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
        Label lblCustHeader = new Label("Customer Report:");
        lblCustHeader.setFont(Font.font("Times New Roman", FontWeight.BOLD, 50));
        GridPane.setHalignment(lblCustHeader, HPos.CENTER);

        // Customer Tab Labels
        Label lblCustDesc = new Label("Customer Description: ");
        GridPane.setHalignment(lblCustDesc, HPos.CENTER);
        Label lblCustHist = new Label("Transaction History: ");
        GridPane.setHalignment(lblCustHist, HPos.CENTER);
        Label lblCustEnroll = new Label("Enrolled in Loyalty Program: ");
        GridPane.setHalignment(lblCustEnroll, HPos.CENTER);

        // Text Area output for customer description
        txtAreaCustDesc.setMaxSize(300, 400);

        // Customer Buttons
        Button btnRemoveCust = new Button("Remove Customer ->");
        Button btnEnrollCust = new Button("Enroll Customer ->");

        // disable buttons initially
        btnRemoveCust.setDisable(true);
        btnEnrollCust.setDisable(true);

        btnRemoveCust.setMaxWidth(165);
        btnEnrollCust.setMaxWidth(165);

        VBox vbCustButtons = new VBox();
        vbCustButtons.setSpacing(10);
        vbCustButtons.setPadding(new Insets(0, 10, 10, 0));
        vbCustButtons.getChildren().addAll(btnRemoveCust, btnEnrollCust);

        //modCustPane Controls
        Label lblModCustomerHeader = new Label("Modify Customer:");
        lblModCustomerHeader.setFont(Font.font("Times New Roman", FontWeight.BOLD, 18));
        GridPane.setHalignment(lblModCustomerHeader, HPos.CENTER);

        Label lblModCustFname = new Label("First Name: ");
        Label lblModCustLname = new Label("Last Name: ");
        Label lblModCustPhone = new Label("Phone Number: ");
        Label lblModCustAddress = new Label("Address: ");

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
        modCustomerPane.add(lblModCustomerHeader, 0, 0);
        modCustomerPane.add(lblModCustFname, 0, 1);
        modCustomerPane.add(lblModCustLname, 0, 2);
        modCustomerPane.add(lblModCustPhone, 0, 3);
        modCustomerPane.add(lblModCustAddress, 0, 4);
        modCustomerPane.add(txtModCustFname, 1, 1);
        modCustomerPane.add(txtModCustLname, 1, 2);
        modCustomerPane.add(txtModCustPhone, 1, 3);
        modCustomerPane.add(txtModCustAddress, 1, 4);
        modCustomerPane.add(vbModCustButtons, 0, 5);

        // initialize tableviews
        custView.setItems(custTableData);
        custLoyaltyView.setItems(custLoyaltyTableData);
        custTransView.setItems(custTransTableData);

        //Create Table Columns For TableViews   
        TableColumn tblcCustID = new TableColumn("Customer ID");
        TableColumn tblcCustFirstName = new TableColumn("First");
        TableColumn tblcCustLastName = new TableColumn("Last");

        // Create Table Columns for enroll Customer
        TableColumn tblcEnrollCustID = new TableColumn("Customer ID");
        TableColumn tblcEnrollCustFirstName = new TableColumn("First");
        TableColumn tblcEnrollCustLastName = new TableColumn("Last");

        // Table Columns for report
        TableColumn tblcTransCustID = new TableColumn("Customer ID");
        TableColumn tblcCustOrderID = new TableColumn("Order ID");
        TableColumn tblcCustOrderQty = new TableColumn("Order Qty");
        TableColumn tblcCustStore = new TableColumn("Store ID");
        TableColumn tblcCustOrderDate = new TableColumn("Order Date");

        // custview table items
        tblcCustID.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("custID"));
        tblcCustFirstName.setCellValueFactory(new PropertyValueFactory<Customer, String>("custFirstName"));
        tblcCustLastName.setCellValueFactory(new PropertyValueFactory<Customer, String>("custLastName"));

        //enroll view table items
        tblcEnrollCustID.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("custID"));
        tblcEnrollCustFirstName.setCellValueFactory(new PropertyValueFactory<Customer, String>("custFirstName"));
        tblcEnrollCustLastName.setCellValueFactory(new PropertyValueFactory<Customer, String>("custLastName"));

        //Formatting and Adding Columns to TableViews
        custView.setMinWidth(300);
        custView.setMaxHeight(400);
        custView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        custView.getColumns().addAll(tblcCustID, tblcCustFirstName, tblcCustLastName);

        custLoyaltyView.setMinWidth(300);
        custLoyaltyView.setMaxHeight(400);
        custLoyaltyView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        custLoyaltyView.getColumns().addAll(tblcEnrollCustID, tblcEnrollCustFirstName, tblcEnrollCustLastName);

        custTransView.setMinWidth(500);
        custTransView.setMaxHeight(400);
        custTransView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        custTransView.getColumns().addAll(tblcTransCustID, tblcCustOrderID, tblcCustOrderQty, tblcCustStore, tblcCustOrderDate);

        //Adding Controls to custPane
        customerPane.add(lblCustHeader, 0, 0, 3, 1);
        customerPane.add(lblCustEnroll, 1, 1);
        customerPane.add(lblCustHist, 2, 1);
        customerPane.add(custView, 0, 2);
        customerPane.add(custLoyaltyView, 1, 2);
        customerPane.add(modCustomerPane, 1, 6);
        customerPane.add(custTransView, 2, 2);
        customerPane.add(lblCustDesc, 0, 5);
        customerPane.add(txtAreaCustDesc, 0, 6);
        customerPane.add(vbCustButtons, 0, 7);

        //Submit Customer Modification Functions
        //here
        btnModCustSubmit.setOnAction(e
                -> {
            // store selected Customer Information
            Customer selectedCust = custView.getSelectionModel().getSelectedItem();
            // remove old customer information from array and listview

            // create new object containing new information
            Customer modCust = new Customer(selectedCust.getCustID(),
                    txtModCustFname.getText(),
                    txtModCustLname.getText(),
                    txtModCustPhone.getText(),
                    txtModCustAddress.getText());
            // update customer array and listview
            customerArray.add(modCust);
            custTableData.add(modCust);
            custView.setItems(custTableData);

            // check to see if customer is loyalty member
            // if so, modify loyalty information
            if (custLoyaltyView.getItems().contains(selectedCust)) {
                custLoyaltyArray.add(modCust);
                custLoyaltyTableData.add(modCust);
                custLoyaltyView.setItems(custLoyaltyTableData);
            }

            removeCustomer();

        });

        //Clear Customer Modification Functions
        btnModCustClear.setOnAction(e
                -> {
            txtModCustFname.clear();
            txtModCustLname.clear();
            txtModCustPhone.clear();
            txtModCustAddress.clear();
        });

        //Button to Enroll Customer
        btnEnrollCust.setOnAction(e
                -> {
            custLoyaltyTableData.clear();
            enrollCustomer();

            for (Customer c : custLoyaltyArray) {
                custLoyaltyTableData.add(c);
                custLoyaltyView.setItems(custLoyaltyTableData);
            }
        });

        btnRemoveCust.setOnAction(e
                -> {
            removeCustomer();
        });

        //populate text area upon customer selection from tableview
        custView.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                txtModCustFname.clear();
                txtModCustLname.clear();
                txtModCustPhone.clear();
                txtModCustAddress.clear();
                btnRemoveCust.setDisable(false);
                btnEnrollCust.setDisable(false);

                txtAreaCustDesc.clear();
                Customer cust = custView.getSelectionModel().getSelectedItem();
                txtAreaCustDesc.appendText("Customer ID: ".toUpperCase() + cust.getCustID() + "\n"
                        + "First Name: ".toUpperCase() + cust.getCustFirstName() + "\n"
                        + "Last Name: ".toUpperCase() + cust.getCustLastName() + "\n"
                        + "Phone: ".toUpperCase() + cust.getCustPhone() + "\n"
                        + "Address: ".toUpperCase() + cust.getCustAddress());

                modCustomerPane.setVisible(true);
                Customer modCustomer = custView.getSelectionModel().getSelectedItem();
                txtModCustFname.appendText(modCustomer.getCustFirstName());
                txtModCustLname.appendText(modCustomer.getCustLastName());
                txtModCustPhone.appendText(modCustomer.getCustPhone());
                txtModCustAddress.appendText(modCustomer.getCustAddress());
            }

        });
        // populate text area upon customer loyalty selection from tableview
        custLoyaltyView.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                txtAreaCustDesc.clear();
                Customer cust = custLoyaltyView.getSelectionModel().getSelectedItem();
                txtAreaCustDesc.appendText("Customer ID: ".toUpperCase() + cust.getCustID() + "\n"
                        + "First Name: ".toUpperCase() + cust.getCustFirstName() + "\n"
                        + "Last Name: ".toUpperCase() + cust.getCustLastName() + "\n"
                        + "Phone: ".toUpperCase() + cust.getCustPhone() + "\n"
                        + "Address: ".toUpperCase() + cust.getCustAddress());

                // populatate textfields based on selection from custLoyaltyView
                txtModCustFname.clear();
                txtModCustLname.clear();
                txtModCustPhone.clear();
                txtModCustAddress.clear();
                modCustomerPane.setVisible(true);
                Customer modLoyaltyCustomer = custLoyaltyView.getSelectionModel().getSelectedItem();
                txtModCustFname.appendText(modLoyaltyCustomer.getCustFirstName());
                txtModCustLname.appendText(modLoyaltyCustomer.getCustLastName());
                txtModCustPhone.appendText(modLoyaltyCustomer.getCustPhone());
                txtModCustAddress.appendText(modLoyaltyCustomer.getCustAddress());
            }
        });

        //******BOOK TAB INFORMATION*********************************************
        
        //Formatting Stuff
        bookPane.setHgap(20);
        bookPane.setVgap(5);
        modBookPane.setHgap(20);
        modBookPane.setVgap(5);
        modBookPane.setVisible(false);

        // Book Tab Header
        Label lblBookHeader = new Label("Book Report");
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
        modBookPane.add(lblModBookHeader, 0, 0);
        modBookPane.add(lblModBookTitle, 0, 1);
        modBookPane.add(lblModBookAuthor, 0, 2);
        modBookPane.add(lblModBookDesc, 0, 3);
        modBookPane.add(lblModAcCost, 0, 4);
        modBookPane.add(lblModSalePrice, 0, 5);

        modBookPane.add(txtModBookTitle, 1, 1);
        modBookPane.add(txtModBookAuthor, 1, 2);
        modBookPane.add(txtModBookDesc, 1, 3);
        modBookPane.add(txtModAcCost, 1, 4);
        modBookPane.add(txtModSalePrice, 1, 5);
        modBookPane.add(vbModBookButtons, 0, 6);

        // create and add to book tableview for initial
        TableView<Book> bookView = new TableView<>();
        ObservableList<Book> bookTableData
                = FXCollections.observableArrayList(Book.getBookArray());
        bookView.setItems(bookTableData);

        // create and add to book top 5 sellers
        TableView<Book> topSellerView = new TableView<>();
        ObservableList<Book> topSellerTableData
                = FXCollections.observableArrayList(Book.getBookArray());
        bookView.setItems(topSellerTableData);

        // create and add to book Transactions
        TableView<Book> bookTransView = new TableView<>();
        ObservableList<Book> bookTransTableData
                = FXCollections.observableArrayList(Book.getBookArray());
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
        bookPane.add(lblBookHeader, 0, 0, 3, 1);
        bookPane.add(lblTopFive, 1, 2);
        bookPane.add(bookView, 0, 3);
        bookPane.add(topSellerView, 1, 3);
        bookPane.add(lblBookHist, 2, 2);
        bookPane.add(bookTransView, 2, 3);
        bookPane.add(modBookPane, 1, 6);
        bookPane.add(lblBookDesc, 0, 5);
        bookPane.add(txtAreaDesc, 0, 6);
        bookPane.add(vbBookButtons, 0, 7);

        //Modify Book Button Functions
        btnModBook.setOnAction(e
                -> {
            modBookPane.setVisible(true);
        });

        //Modify Book Submit Button Functions
        btnModBookSubmit.setOnAction(e
                -> {
            modBookPane.setVisible(false);
        });

        btnModBookClear.setOnAction(e
                -> {
            txtModBookTitle.clear();
            txtModBookAuthor.clear();
            txtModBookDesc.clear();
            txtModAcCost.clear();
            txtModSalePrice.clear();
        });

        //INVENTORY TAB INFORMATION
        Label lblInventoryHeader = new Label("Inventory");
        lblInventoryHeader.setFont(Font.font("Times New Roman", FontWeight.BOLD, 50));
        GridPane.setHalignment(lblInventoryHeader, HPos.CENTER);

        Label lblBookInvHeader = new Label("Book Inventory");
        lblBookInvHeader.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
        GridPane.setHalignment(lblBookInvHeader, HPos.CENTER);

        Label lblConsInvHeader = new Label("Consumable Inventory");
        lblConsInvHeader.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
        GridPane.setHalignment(lblConsInvHeader, HPos.CENTER);

        Label lblInvChooseStore = new Label("Choose a Store: ");
        GridPane.setHalignment(lblInvChooseStore, HPos.CENTER);

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
        ObservableList<Book> bookInventoryTableData
                = FXCollections.observableArrayList(bookInvArray);
        bookInventoryView.setItems(bookInventoryTableData);

        TableView<Consumable> consumableInventoryView = new TableView<>();
        ObservableList<Consumable> consumableInventoryTableData
                = FXCollections.observableArrayList(consInvArray);
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
        invPane.add(consumableInventoryView, 1, 8);

        cmboInvChooseStore.setOnAction((event) -> {

            String storeStr;
            char toChar;
            int storeInt;

            if (cmboInvChooseStore.getSelectionModel().getSelectedIndex() != -1) {
                storeStr = cmboInvChooseStore.getSelectionModel().getSelectedItem().toString();
                toChar = storeStr.charAt(0);
                storeInt = Character.getNumericValue(toChar);

                bookInventoryTableData.clear();
                consumableInventoryTableData.clear();

                for (Book b : bookArray) {
                    if (b.getBookStore() == storeInt) {
                        bookInventoryTableData.add(b);
                    }

                    bookInventoryView.setItems(bookInventoryTableData);
                }
                for (Consumable c : consumableArray) {
                    if (c.getConStore() == storeInt) {
                        consumableInventoryTableData.add(c);
                    }

                    consumableInventoryView.setItems(consumableInventoryTableData);
                }

            }
        });

        //******STORE TAB INFORMATION*******************************************************************************************
        // Store Tab Header
        Label lblStoreHeader = new Label("Store Report");
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

        Button btnRemoveStore = new Button("Remove Store ->");
        btnRemoveStore.setMaxWidth(150);

        VBox vbStoreButtons = new VBox();
        vbStoreButtons.setSpacing(10);
        vbStoreButtons.setPadding(new Insets(0, 10, 10, 0));
        vbStoreButtons.getChildren().addAll(btnRemoveStore);

        //Initialize TableViews
        storeView.setItems(storeTableData);
        storeEmployeeView.setItems(storeEmployeeTableData);

        //modStorePane Controls
        Label lblModStoreHeader = new Label("Modify Store");
        lblModStoreHeader.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
        GridPane.setHalignment(lblModStoreHeader, HPos.CENTER);

        Label lblModStoreName = new Label("Store Name: ");
        Label lblModStoreAddress = new Label("Store Address: ");
        Label lblModStorePhone = new Label("Store phone: ");


        TextField txtModStoreName = new TextField();
        TextField txtModStoreAddress = new TextField();
        TextField txtModStorePhone = new TextField();


        Button btnModStoreSubmit = new Button("Submit");
        Button btnModStoreClear = new Button("Clear");
        btnModStoreSubmit.setMaxWidth(150);
        btnModStoreClear.setMaxWidth(150);

        VBox vbModStoreButtons = new VBox();
        vbModStoreButtons.setSpacing(10);
        vbModStoreButtons.setPadding(new Insets(0, 10, 10, 0));
        vbModStoreButtons.getChildren().addAll(btnModStoreSubmit, btnModStoreClear);

        //Add Controls to modCustPane
        modStorePane.add(lblModStoreHeader, 0, 0);
        modStorePane.add(lblModStoreName, 0, 1);
        modStorePane.add(lblModStoreAddress, 0, 2);
        modStorePane.add(lblModStorePhone, 0, 3);


        modStorePane.add(txtModStoreName, 1, 1);
        modStorePane.add(txtModStoreAddress, 1, 2);
        modStorePane.add(txtModStorePhone, 1, 3);


        modStorePane.add(vbModStoreButtons, 0, 5);

        //Create Table Columns For initial tableView 
        TableColumn tblcStoreID = new TableColumn("Store ID");
        TableColumn tblcStoreName = new TableColumn("Store Name");


        // Table Columns for employees at store tableview
        TableColumn tblcStoreID2 = new TableColumn("Store ID");
        TableColumn tblcStoreEmployID = new TableColumn("EmployeeID");
        TableColumn tblcStoreEmployFname = new TableColumn("First Name");
        TableColumn tblcStoreEmployLname = new TableColumn("Last Name");
        TableColumn tblcStoreEmployEmail = new TableColumn("Emp Email");
        TableColumn tblcStoreEmployPhone = new TableColumn("Emp Phone");

        // storeView table items
        tblcStoreID.setCellValueFactory(new PropertyValueFactory<Store, Integer>("storeID"));
        tblcStoreName.setCellValueFactory(new PropertyValueFactory<Store, String>("storeName"));


        //Format and add Columns to StoreView
        storeView.setMinWidth(200);
        storeView.setMaxHeight(400);
        storeView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        storeView.getColumns().addAll(tblcStoreID, tblcStoreName);

        // Format and add columns to storeEmployeeView
        storeEmployeeView.setMinWidth(700);
        storeEmployeeView.setMaxHeight(400);
        storeEmployeeView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        storeEmployeeView.getColumns().addAll(tblcStoreID2, tblcStoreEmployID,
                tblcStoreEmployFname, tblcStoreEmployLname,
                tblcStoreEmployEmail, tblcStoreEmployPhone);

        // add labels and tableviews to storePane
        storePane.add(lblStoreHeader, 0, 0, 3, 1);
        storePane.add(lblEmployeeStore, 1, 2);
        storePane.add(storeView, 0, 3);
        storePane.add(storeEmployeeView, 1, 3);
        storePane.add(modStorePane, 1, 6);
        storePane.add(lblStoreDesc, 0, 5);
        storePane.add(txtAreaStoreDesc, 0, 6);
        storePane.add(vbStoreButtons, 0, 7);

        //Select Store from storeView
        storeView.setOnMouseClicked(e -> {

            txtAreaStoreDesc.clear();

            //Filling the store description box
            txtAreaStoreDesc.appendText(
                    "STORE ID: " + storeArray.get(storeView.getSelectionModel()
                            .getSelectedIndex()).getStoreID()
                    + "\nSTORE NAME: " + storeArray.get(storeView.getSelectionModel()
                            .getSelectedIndex()).getStoreName()
                    + "\nSTORE ADDRESS: " + storeArray.get(storeView.getSelectionModel()
                            .getSelectedIndex()).getStoreAddress()
                    + "\nSTORE PHONE NUMBER: " + storeArray.get(storeView.getSelectionModel()
                            .getSelectedIndex()).getStorePhone());

            //Filling modify store fields
            txtModStoreName.setText(storeArray.get(storeView.getSelectionModel()
                    .getSelectedIndex()).getStoreName());
            txtModStoreAddress.setText(storeArray.get(storeView.getSelectionModel()
                    .getSelectedIndex()).getStoreAddress());
            txtModStorePhone.setText(storeArray.get(storeView.getSelectionModel()
                    .getSelectedIndex()).getStorePhone());
            

            modStorePane.setVisible(true);
        });

        //Modify Store Submit Button Functions
        btnModStoreSubmit.setOnAction(e
                -> {
            modStorePane.setVisible(false);
        });

        btnModStoreClear.setOnAction(e
                -> {
            txtModStoreName.clear();
            txtModStoreAddress.clear();
            txtModStorePhone.clear();
        });

        //Remove Store Button Functions
        btnRemoveStore.setOnAction(e
                -> {
            Store selectedStore = storeView.getSelectionModel().getSelectedItem();
            storeView.getItems().remove(selectedStore);
            storeArray.remove(selectedStore);
        }); 
        
         //****EMPLOYEE TAB INFORMATION*********** 
        Label lblEmployeeHeader = new Label("Employee Report"); 
        lblEmployeeHeader.setFont(Font.font("Times New Roman", FontWeight.BOLD,50)); 
        Label lblTimeLog = new Label("Time Log"); 
        lblTimeLog.setFont(Font.font("Times New Roman",FontWeight.BOLD,15)); 
        Label lblChooseEmployee = new Label("Choose an Employee"); 
        lblChooseEmployee.setFont(Font.font("Times New Roman",FontWeight.BOLD,15)); 
        GridPane.setHalignment(lblEmployeeHeader, HPos.CENTER); 
        Pane blankSpace4 = new Pane();
        blankSpace4.setMinHeight(20);
        
        //Formatting 
        employeePane.setVgap(5); 
        employeePane.setHgap(20); 
        modEmployeePane.setHgap(20); 
        modEmployeePane.setVgap(5); 
        
        //Employee columns 
        employView.setItems(employTableData); 
        TableColumn tblcEmployID = new TableColumn("Employee ID");
        TableColumn tblcEmployFirstName = new TableColumn("First");
        TableColumn tblcEmployLastName = new TableColumn("Last"); 
        
        tblcEmployID.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("employID"));
        tblcEmployFirstName.setCellValueFactory(new PropertyValueFactory<Employee, String>("employFirstName"));
        tblcEmployLastName.setCellValueFactory(new PropertyValueFactory<Employee, String>("employLastName")); 
        
        
        
        
        timeView.setItems(timeTableData); 
        TableColumn tblcTimeID = new TableColumn("Time ID");
        TableColumn tblcTimeIn = new TableColumn("Time-In");
        TableColumn tblcTimeOut = new TableColumn("Time-Out"); 
        
        
        tblcTimeID.setCellValueFactory(new PropertyValueFactory<Time_Management, Integer>("timeID"));
        tblcTimeIn.setCellValueFactory(new PropertyValueFactory<Time_Management, Date>("timeInFormat"));
        tblcTimeOut.setCellValueFactory(new PropertyValueFactory<Time_Management, Date>("timeOutFormat")); 
        tblcTimeID.setSortType(TableColumn.SortType.ASCENDING);
        timeView.getColumns().addAll(tblcTimeID,tblcTimeIn,tblcTimeOut); 
        timeView.getSortOrder().add(tblcTimeID);
        timeView.setMinWidth(400);
        employView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        timeView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        employView.getColumns().addAll(tblcEmployID,tblcEmployFirstName,tblcEmployLastName); 
        timeTableData.clear();
        employeePane.add(lblEmployeeHeader, 0, 0); 
        employeePane.add(blankSpace4, 0, 1); 
        employeePane.add(lblTimeLog, 1, 2); 
        employeePane.add(lblChooseEmployee, 0, 2);
        employeePane.add(employView, 0, 3); 
        employeePane.add(timeView,1,3); 
        tblcEmployID.setSortType(TableColumn.SortType.ASCENDING); 
        employView.getSortOrder().add(tblcEmployID);
        employTableData.clear(); 
        employeeArray = Employee.getEmployArray(); 
        
        tblcEmployFirstName.setSortable(false); 
        tblcEmployLastName.setSortable(false);
        
        for(Employee e: employeeArray){ 
            employTableData.add(e);
        } 
        employView.sort(); 
        
        employeeArray.clear(); 
        for(Employee e: employTableData){ 
            employeeArray.add(e);
        } 
        
        Employee.setEmployArray(employeeArray);
        tblcEmployID.setSortable(false);  
        tblcTimeID.setSortable(false);
        tblcTimeIn.setSortable(false); 
        tblcTimeOut.setSortable(false);
        
        employView.setOnMouseClicked(e -> { 
            int selection = employView.getSelectionModel().getSelectedIndex(); 
            if(selection != -1){ 
                int employID = employeeArray.get(selection).getEmployID(); 
                timeArray = Time_Management.timeLogForEmployee(employID); 
                timeTableData.clear(); 
                if(timeArray.size()>0){
                for(Time_Management tm: timeArray){ 
                    timeTableData.add(tm);
                }  
                timeView.refresh(); 
                tblcTimeID.setSortable(true);
                timeView.sort(); 
                tblcTimeID.setSortable(false);
                }    
            }
        });

        //Profit Vs Expense Header
        //Title header
        Label lblPVE = new Label("Profit/Expense Report");
        lblPVE.setFont(Font.font("Times New Roman", FontWeight.BOLD, 50));
        GridPane.setHalignment(lblPVE, HPos.CENTER);

        //Time period header
        Label lblTime = new Label("Choose Time Period:");
        lblTime.setFont(Font.font("Times New Roman", FontWeight.BOLD, 25));
        GridPane.setHalignment(lblTime, HPos.CENTER);

        //Radio Buttons 
        RadioButton rdoDay = new RadioButton();
        RadioButton rdoMonth = new RadioButton();
        RadioButton rdoYear = new RadioButton();

        Label lblDay = new Label("Day");
        Label lblMonth = new Label("Month");
        Label lblYear = new Label("Year");

        Label lblRev = new Label("Revenues:");
        Label lblExp = new Label("Expenses:");

        Label lblTotal = new Label("Total Profit: ");
        Label lblIncome = new Label("Income Before Taxes: ");

        TextField txtProfit = new TextField();
        TextField txtIncome = new TextField();

        //Location of labels and buttons
        profitPane.add(lblPVE, 2, 0, 3, 1);

        profitPane.setHalignment(lblTime, HPos.CENTER);
        profitPane.add(lblTime, 0, 1);

        //Revenues and expenses
        TableView<Book> profitView = new TableView<>();

        //create table columns 
        TableColumn tblcRevenueID = new TableColumn("ID");
        TableColumn tblcAmount = new TableColumn("Amount");
        TableColumn tblcDate = new TableColumn("Date");

        profitView.setMinWidth(300);
        profitView.setMaxHeight(400);
        profitView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        profitView.getColumns().addAll(tblcRevenueID, tblcAmount, tblcDate);

        TableView<Book> expenseView = new TableView<>();

        //create table columns 
        TableColumn tblcExpenseID = new TableColumn("ID");
        TableColumn tblcAmountExp = new TableColumn("Amount");
        TableColumn tblcDateExp = new TableColumn("Date");

        expenseView.setMinWidth(300);
        expenseView.setMaxHeight(400);
        expenseView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        expenseView.getColumns().addAll(tblcExpenseID, tblcAmountExp, tblcDateExp);

        GridPane.setHalignment(lblDay, HPos.LEFT);
        GridPane.setHalignment(rdoDay, HPos.RIGHT);
        GridPane.setHalignment(lblMonth, HPos.LEFT);
        GridPane.setHalignment(rdoMonth, HPos.RIGHT);
        GridPane.setHalignment(lblYear, HPos.LEFT);
        GridPane.setHalignment(rdoYear, HPos.RIGHT);

        profitPane.add(lblDay, 3, 1);
        profitPane.add(rdoDay, 4, 1);
        profitPane.add(lblMonth, 6, 1);
        profitPane.add(rdoMonth, 7, 1);
        profitPane.add(lblYear, 8, 1);
        profitPane.add(rdoYear, 9, 1);
        profitPane.add(lblRev, 0, 5);
        profitPane.add(lblExp, 4, 5);
        profitPane.add(profitView, 0, 7);
        profitPane.add(expenseView, 4, 7);
        profitPane.add(lblTotal, 0, 11);
        profitPane.add(lblIncome, 0, 13);
        profitPane.add(txtProfit, 0, 12);
        profitPane.add(txtIncome, 0, 14);
        //CREATE TAB INFORMATION
        Label lblCreateHeader = new Label("Create New Object");
        lblCreateHeader.setFont(Font.font("Times New Roman", FontWeight.BOLD, 50));
        GridPane.setHalignment(lblInventoryHeader, HPos.CENTER);

        Label lblCreateSubHeader2 = new Label("Individual");
        lblCreateSubHeader2.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
        GridPane.setHalignment(lblCreateSubHeader2, HPos.CENTER);

        Label lblCreateSubHeader3 = new Label("Business Entities");
        lblCreateSubHeader3.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
        GridPane.setHalignment(lblCreateSubHeader3, HPos.CENTER);

        Label lblCreateSubHeader4 = new Label("Items");
        lblCreateSubHeader4.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
        GridPane.setHalignment(lblCreateSubHeader4, HPos.CENTER); 
        
        Label lblCreateSubHeader5 = new Label("Operations");
        lblCreateSubHeader5.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
        GridPane.setHalignment(lblCreateSubHeader5, HPos.CENTER);

        //Adding buttons and labels for choosing which entity to create
        ToggleGroup createGroup = new ToggleGroup();

        RadioButton rdoCreateCustomer = new RadioButton();
        RadioButton rdoCreateEmployee = new RadioButton();
        RadioButton rdoCreateStore = new RadioButton();
        RadioButton rdoCreateSupplier = new RadioButton();
        RadioButton rdoCreateBook = new RadioButton();
        RadioButton rdoCreateConsumable = new RadioButton(); 
        RadioButton rdoCreatePOS = new RadioButton(); 
        RadioButton rdoCreateProductOrder = new RadioButton();

        rdoCreateCustomer.setToggleGroup(createGroup);
        rdoCreateEmployee.setToggleGroup(createGroup);
        rdoCreateStore.setToggleGroup(createGroup);
        rdoCreateSupplier.setToggleGroup(createGroup);
        rdoCreateBook.setToggleGroup(createGroup);
        rdoCreateConsumable.setToggleGroup(createGroup); 
        rdoCreatePOS.setToggleGroup(createGroup);
        rdoCreateProductOrder.setToggleGroup(createGroup);

        Label lblCreateCustomer = new Label("Customer");
        Label lblCreateEmployee = new Label("Employee");
        Label lblCreateStore = new Label("Store");
        Label lblCreateSupplier = new Label("Supplier");
        Label lblCreateBook = new Label("Book");
        Label lblCreateConsumable = new Label("Consumable"); 
        Label lblCreatePOS = new Label("Point-Of-Sale"); 
        Label lblCreateProductOrder = new Label("Product Order"); 

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
        ColumnConstraints clmn8 = new ColumnConstraints(); 
        ColumnConstraints clmn9 = new ColumnConstraints();

        createPane1.getColumnConstraints().addAll(clmn1, clmn2, clmn3, clmn4, 
                clmn5, clmn6,clmn7,clmn8);

        clmn1.setPercentWidth(12.5);
        clmn2.setPercentWidth(12.5);
        clmn3.setPercentWidth(12.5);
        clmn4.setPercentWidth(12.5);
        clmn5.setPercentWidth(12.5);
        clmn6.setPercentWidth(12.5); 
        clmn7.setPercentWidth(12.5); 
        clmn8.setPercentWidth(12.5);
        clmn9.setPercentWidth(100);

        createPane.getColumnConstraints().add(clmn9);

        //Adding the first half of the page to the main createPane
        createPane.add(createPane1, 0, 0);
        createPane1.setHgap(20);

        createPane1.add(lblCreateHeader, 2, 0, 3, 1);
        createPane1.add(blankSpace1, 2, 1);

        createPane1.add(lblCreateSubHeader2, 0, 4, 2, 1);
        createPane1.add(lblCreateSubHeader3, 2, 4, 2, 1);
        createPane1.add(lblCreateSubHeader4, 4, 4, 2, 1); 
        createPane1.add(lblCreateSubHeader5, 6, 4, 2, 1);

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
        
        createPane1.add(lblCreatePOS, 6, 5); 
        createPane1.add(lblCreateProductOrder, 6, 6); 
        createPane1.add(rdoCreatePOS, 7, 5); 
        createPane1.add(rdoCreateProductOrder, 7, 6);
        
        GridPane.setHalignment(lblCreatePOS, HPos.RIGHT);
        GridPane.setHalignment(rdoCreatePOS, HPos.LEFT);
        GridPane.setHalignment(lblCreateProductOrder, HPos.RIGHT);
        GridPane.setHalignment(rdoCreateProductOrder, HPos.LEFT);
        
        //These GridPanes will make up the second half of the page, where
        //the user will be able to add an entity
        addCustPane.setVisible(false);
        addEmployeePane.setVisible(false);
        addStorePane.setVisible(false);
        addSupplierPane.setVisible(false);
        addBookPane.setVisible(false);
        addConsumablePane.setVisible(false); 
        addPOSPane.setVisible(false); 
        addProductOrderPane.setVisible(false);

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
        addPOSPane.setAlignment(Pos.TOP_CENTER); 
        addPOSPane.setHgap(20);
        addPOSPane.setVgap(5); 
        addProductOrderPane.setAlignment(Pos.TOP_CENTER);
        addProductOrderPane.setHgap(20);
        addProductOrderPane.setVgap(5);        

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
        createPane.add(addPOSPane, 0, 2); 
        createPane.add(addProductOrderPane, 0, 2);        

        createPane.add(blankSpace1, 0, 3);
        createPane.add(addOutput, 0, 4);

        //Setting up add customer pane for second half of page
        Label lblAddCust = new Label("Add New Customer");
        lblAddCust.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
        GridPane.setHalignment(lblAddCust, HPos.RIGHT);

        Label lblAddCustFN = new Label("First Name: ");
        Label lblAddCustLN = new Label("Last Name: ");
        Label lblAddCustPhone = new Label("Phone Number: ");
        Label lblAddCustAddress = new Label("Address: ");

        TextField txtAddCustFN = new TextField();
        TextField txtAddCustLN = new TextField();
        TextField txtAddCustPhone = new TextField();
        TextField txtAddCustAddress = new TextField();
 
        Button btnAddCust = new Button("Add Customer");

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

        btnAddCust.setOnAction(e -> {

            addOutput.setVisible(true);
            Customer.newCustomer(txtAddCustFN.getText(), txtAddCustLN.getText(),
                    txtAddCustPhone.getText(), txtAddCustAddress.getText());

            txtAddCustFN.clear();
            txtAddCustLN.clear();
            txtAddCustPhone.clear();
            txtAddCustAddress.clear();

            custTableData.clear();
            for (Customer c : customerArray) {
                custTableData.add(c);
            }

            addOutput.setText("Customer Added!");
        });

        //Setting up add employee pane for second half of page
        Label lblAddEmployee = new Label("Add New Employee");
        lblAddEmployee.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
        GridPane.setHalignment(lblAddEmployee, HPos.RIGHT);

        Label lblAddEmployeeFN = new Label("First Name: ");
        Label lblAddEmployeeLN = new Label("Last Name: ");
        Label lblAddEmployeePhone = new Label("Phone Number: ");
        Label lblAddEmployeePay = new Label("Pay Rate: ");
        Label lblAddEmployeeUsername = new Label("Username"); 
        Label lblAddEmployeePassword = new Label("Password"); 
        CheckBox cbxManagementStatus = new CheckBox("Assign management privileges?"); 
        Label lblStore = new Label("Employee of Store: "); 
        Label lblErrorUsername = new Label("Username already taken!"); 
        lblErrorUsername.setTextFill(Color.RED); 
        lblErrorUsername.setVisible(false);
        cbxManagementStatus.setTextFill(Color.RED);

        TextField txtAddEmployeeFN = new TextField();
        TextField txtAddEmployeeLN = new TextField();
        TextField txtAddEmployeePhone = new TextField();
        TextField txtAddEmployeePay = new TextField(); 
        TextField txtAddEmployeeUsername = new TextField(); 
        TextField txtAddEmployeePassword = new TextField(); 

        Button btnAddEmployee = new Button("Add Employee"); 
        

        addEmployeePane.add(lblAddEmployee, 0, 0);
        addEmployeePane.add(lblStore, 0, 1);
        addEmployeePane.add(lblAddEmployeeFN, 0, 2);
        addEmployeePane.add(lblAddEmployeeLN, 0, 3);
        addEmployeePane.add(lblAddEmployeePhone, 0, 4);
        addEmployeePane.add(lblAddEmployeePay, 0, 5); 
        addEmployeePane.add(lblAddEmployeeUsername, 0, 6); 
        addEmployeePane.add(lblAddEmployeePassword, 0, 7); 
        addEmployeePane.add(cbxManagementStatus,0,8); 
        populateChooseStoreCombos();
        addEmployeePane.add(cmboChooseEmployeeStore, 1, 1);
        addEmployeePane.add(txtAddEmployeeFN, 1, 2);
        addEmployeePane.add(txtAddEmployeeLN, 1, 3);
        addEmployeePane.add(txtAddEmployeePhone, 1, 4);
        addEmployeePane.add(txtAddEmployeePay, 1, 5); 
        addEmployeePane.add(txtAddEmployeeUsername, 1, 6); 
        addEmployeePane.add(txtAddEmployeePassword, 1, 7);

        addEmployeePane.add(btnAddEmployee, 1, 9); 
        addEmployeePane.add(lblErrorUsername, 0, 10);
        
        btnAddEmployee.setOnAction(e -> { 
            boolean valid = true;
            int status = 0; 
            double payRate = Double.valueOf(txtAddEmployeePay.getText()); 
            String attemptedUsername = txtAddEmployeeUsername.getText(); 
            valid = Employee.checkUsernameAvailability(attemptedUsername);
            int storeID = storeArray.get(
                    cmboChooseEmployeeStore.getSelectionModel().getSelectedIndex())
                    .getStoreID();
            if(cbxManagementStatus.isSelected()){ 
                status = 1;
            } 
            
            if(valid){
            lblErrorUsername.setVisible(false);
                Employee.newEmployee(txtAddEmployeeFN.getText(), 
                    txtAddEmployeeLN.getText(), txtAddEmployeePhone.getText(), 
                    payRate,txtAddEmployeeUsername.getText(), 
                    txtAddEmployeePassword.getText(), status, storeID); 
            employTableData.clear(); 
            employeeArray = Employee.getEmployArray(); 
            for(Employee em: employeeArray){ 
                employTableData.add(em);
            }
            } 
            else { 
                lblErrorUsername.setVisible(true);
            }
        });

        //Setting up add Store pane for second half of page
        Label lblAddStore = new Label("Add New Store");
        lblAddStore.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
        GridPane.setHalignment(lblAddStore, HPos.RIGHT);

        Label lblAddStoreName = new Label("Store Name: ");
        Label lblAddStoreAddress = new Label("Store Address: ");
        Label lblAddStorePhone = new Label("Store Phone Number: ");


        TextField txtAddStoreName = new TextField();
        TextField txtAddStoreAddress = new TextField();
        TextField txtAddStorePhone = new TextField();


        Button btnAddStore = new Button("Add Store");

        addStorePane.add(lblAddStore, 0, 0);

        addStorePane.add(lblAddStoreName, 0, 1);
        addStorePane.add(lblAddStoreAddress, 0, 2);
        addStorePane.add(lblAddStorePhone, 0, 3);


        addStorePane.add(txtAddStoreName, 1, 1);
        addStorePane.add(txtAddStoreAddress, 1, 2);
        addStorePane.add(txtAddStorePhone, 1, 3);


        addStorePane.add(btnAddStore, 1, 5);

        btnAddStore.setOnAction(e -> {

            boolean toAddFlag = true;
            addOutput.setVisible(true);

            for (Store s : storeArray) {
                if (txtAddStoreName.getText().matches(s.getStoreName())
                        || txtAddStoreAddress.getText().matches(s.getStoreAddress())
                        ) {
                    addOutput.setText("Store already exists!");
                    toAddFlag = false;
                }
            }

            if (toAddFlag == true) {
                Store.newStore(txtAddStoreName.getText(), txtAddStoreAddress.getText(),
                        txtAddStorePhone.getText());

                addOutput.setText("Store successfully added.");
            }

            txtAddStoreName.clear();
            txtAddStoreAddress.clear();
            txtAddStorePhone.clear();


            bookInventoryTableData.clear();

            storeTableData.clear();

            for (Store s : storeArray) {
                storeTableData.add(s);
            }

            populateChooseStoreCombos();
        });

        //Setting up add Supplier pane for second half of page
        Label lblAddSupplier = new Label("Add New Supplier");
        lblAddSupplier.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
        GridPane.setHalignment(lblAddSupplier, HPos.RIGHT);

        Label lblAddSupplierName = new Label("Supplier Name: ");
        Label lblAddSupplierAddress = new Label("Supplier Address: ");
        //Label lblAddSupplierRep = new Label("Supplier Contact: ");

        TextField txtAddSupplierName = new TextField();
        TextField txtAddSupplierAddress = new TextField();

        Button btnAddSupplier = new Button("Add Supplier");

        addSupplierPane.add(lblAddSupplier, 0, 0);

        addSupplierPane.add(lblAddSupplierName, 0, 1);
        addSupplierPane.add(lblAddSupplierAddress, 0, 2);
        //addSupplierPane.add(lblAddSupplierRep, 0, 3);

        addSupplierPane.add(txtAddSupplierName, 1, 1);
        addSupplierPane.add(txtAddSupplierAddress, 1, 2);
        //addSupplierPane.add(cmboAddSupplierRep, 1, 3);

        addSupplierPane.add(btnAddSupplier, 1, 4);
        
        btnAddSupplier.setOnAction(e -> {

            boolean toAddFlag = true;
            addOutput.setVisible(true);

            for (Supplier s : supplierArray) {
                if (txtAddSupplierName.getText().matches(s.getSuppName())
                        || txtAddSupplierAddress.getText().matches(s.getSuppAddress())) 
                {
                    addOutput.setText("Supplier already exists!");
                    toAddFlag = false;
                }
            }

            if (toAddFlag == true) 
            {
                Supplier.newSupplier(txtAddSupplierName.getText(), txtAddSupplierAddress.getText());
                addOutput.setText("Store successfully added.");
            }

            txtAddSupplierName.clear();
            txtAddSupplierAddress.clear();
        });

        //Setting up add Book pane for second half of page
        Label lblAddBook = new Label("Add New Book");
        lblAddBook.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
        GridPane.setHalignment(lblAddBook, HPos.RIGHT);

        Label lblAddBookTitle = new Label("Title: ");
        Label lblAddBookAuthor = new Label("Author: ");
        Label lblAddBookDesc = new Label("Description ");
        Label lblAddBookCost = new Label("Aquisition Cost: ");
        Label lblAddBookSalePrice = new Label("Retail Price: ");
        Label lblAddBookQuantity = new Label("Quantity to add: ");
        Label lblAddBookStore = new Label("Adding to Store: ");

        TextField txtAddBookTitle = new TextField();
        TextField txtAddBookAuthor = new TextField();
        TextField txtAddBookDesc = new TextField();
        TextField txtAddBookCost = new TextField();
        TextField txtAddBookSalePrice = new TextField();
        TextField txtAddBookQuantity = new TextField();

        Button btnAddBook = new Button("Add Book");

        addBookPane.add(lblAddBook, 0, 0);

        addBookPane.add(lblAddBookTitle, 0, 1);
        addBookPane.add(lblAddBookAuthor, 0, 2);
        addBookPane.add(lblAddBookDesc, 0, 3);
        addBookPane.add(lblAddBookCost, 0, 4);
        addBookPane.add(lblAddBookSalePrice, 0, 5);
        addBookPane.add(lblAddBookQuantity, 0, 6);
        addBookPane.add(lblAddBookStore, 0, 7);

        addBookPane.add(txtAddBookTitle, 1, 1);
        addBookPane.add(txtAddBookAuthor, 1, 2);
        addBookPane.add(txtAddBookDesc, 1, 3);
        addBookPane.add(txtAddBookCost, 1, 4);
        addBookPane.add(txtAddBookSalePrice, 1, 5);
        addBookPane.add(txtAddBookQuantity, 1, 6);
        addBookPane.add(cmboAddChooseStore, 1, 7);

        addBookPane.add(btnAddBook, 1, 8);

        btnAddBook.setOnAction(e -> {
            try {
                boolean toAddFlag = true;
                addOutput.setVisible(true);

                String storeNumStr = cmboAddChooseStore.getSelectionModel().getSelectedItem().toString();
                char storeNumChar = storeNumStr.charAt(0);
                int storeNumInt = Character.getNumericValue(storeNumChar);

                for (Book b : bookArray) {
                    if (txtAddBookTitle.getText().matches(b.getBookTitle())
                            && txtAddBookAuthor.getText().matches(b.getBookAuthor())
                            && storeNumInt == b.getBookStore()) {
                        int newQuantity = b.getBookQuantity() + Integer.parseInt(txtAddBookQuantity.getText());

                        b.setBookQuantity(newQuantity);

                        addOutput.setText("Book is already in inventory for store. Updating quantity."
                                + "\nNew Quantity is: " + b.getBookQuantity());

                        toAddFlag = false;
                    }
                }

                if (toAddFlag == true) {
                    Book.newBook(txtAddBookTitle.getText(), txtAddBookAuthor.getText(),
                            Double.parseDouble(txtAddBookCost.getText()),
                            Double.parseDouble(txtAddBookSalePrice.getText()),
                            txtAddBookDesc.getText(), Integer.parseInt(txtAddBookQuantity.getText()),
                            storeNumInt);

                    addOutput.setText("Book successfully added to store " + storeNumInt);
                }

                txtAddBookTitle.clear();
                txtAddBookAuthor.clear();
                txtAddBookCost.clear();
                txtAddBookSalePrice.clear();
                txtAddBookDesc.clear();
                txtAddBookQuantity.clear();

                if (invTabStoreCombo.size() < 2) {
                    bookInventoryTableData.clear();

                    for (Book b : bookArray) {
                        bookInventoryTableData.add(b);
                    }

                    bookInventoryView.setItems(bookInventoryTableData);
                }
            } catch (NumberFormatException n) {
                addOutput.setVisible(true);
                addOutput.setText("Please ensure that Aquisition Cost, Retail Price"
                        + ", \nand quantity are numbers and that you have selected a store.");
            } catch (NullPointerException npe) {
                addOutput.setVisible(true);
                addOutput.setText("Please ensure that Aquisition Cost, Retail Price"
                        + ", and quantity are numbers and that you have selected a store.");
            }
        }); 
        
         //Setting up POS pane for second half of page 
        Label lblAddPOS = new Label("Add New POS");
        lblAddPOS.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
        GridPane.setHalignment(lblAddPOS, HPos.RIGHT); 
        
        Label lblAddPOSCustomer = new Label ("Select a Customer:"); 
        Label lblAddPOSDate = new Label ("Select a Date:"); 
        Label lblAddPOSStore = new Label ("Select a Store:"); 
        Label lblAddPOSProducts = new Label("Items Bought:");  

        mnuBar.getMenus().add(mnuCategories); 
        mnuCategories.getItems().addAll(mnuBook,mnuConsumable);
        
        
        DatePicker datePick = new DatePicker(); 
        datePick.setEditable(false);
        // Technique retrieved from 
        // https://stackoverflow.com/a/665463
        JSpinner spinTimePick = new JSpinner( new SpinnerDateModel() ); 
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(spinTimePick, "hh:mm:ss a");
        spinTimePick.setEditor(timeEditor);
        spinTimePick.setValue(new Date());  
        
        SwingNode timePick = new SwingNode(); 
        timePick.setContent(spinTimePick); 
        
        
        addPOSPane.add(lblAddPOS, 0, 0); 
        addPOSPane.add(lblAddPOSCustomer, 0, 1); 
        addPOSPane.add(lblAddPOSStore, 0, 2); 
        addPOSPane.add(lblAddPOSDate, 0, 3); 
        addPOSPane.add(lblAddPOSProducts, 0, 4); 


        addPOSPane.add(cmboAddPOSCustomer,1,1); 
        addPOSPane.add(cmboAddPOSStore, 1, 2); 
        addPOSPane.add(datePick, 1, 3); 
        addPOSPane.add(timePick, 2, 3); 
        addPOSPane.add(mnuBar, 1, 4);  
        

        
        
        
        cmboAddPOSStore.setOnAction(e -> { 
            int storeSelection = cmboAddPOSStore.getSelectionModel().getSelectedIndex();
            if(storeSelection>-1){ 
                mnuBook.getItems().clear(); 
                mnuConsumable.getItems().clear();
                int storeID = storeArray.get(storeSelection).getStoreID(); 
                EventHandler<ActionEvent> action = changeCustPOSPlacement(storeID);
                bookArray = Book.getBookStoreInventory(storeID);
                consumableArray = Consumable.getConsumableStoreInventory(storeID); 
                if(!bookArray.isEmpty()){ 
                    for(Book b: bookArray){ 
                        MenuItem mnuItem = new MenuItem(b.getBookTitle()); 
                        mnuBook.getItems().add(mnuItem); 
                        mnuItem.setUserData("b"+b.getBookID()); 
                        mnuItem.setOnAction(action);
                    }
                } 
                if(!consumableArray.isEmpty()){ 
                    for(Consumable c: consumableArray){ 
                        MenuItem mnuItem = new MenuItem(c.getConName()); 
                        mnuConsumable.getItems().add(mnuItem); 
                        mnuItem.setUserData("c"+c.getConID()); 
                        mnuItem.setOnAction(action);
                    }
                }
            }
        });
        
        
        btnAddPOS.setOnAction(e -> { 
            Date timePickDate = (Date) spinTimePick.getValue(); 
            SimpleDateFormat formatDate = new SimpleDateFormat("h:mm:ss a"); 
            String sPosDate = ""; 
            sPosDate = datePick.getValue().format(DateTimeFormatter.ofPattern("MMMM dd, yyyy")); 
            sPosDate = sPosDate + " " + formatDate.format(timePickDate); 
            try {
                Date posDate = new SimpleDateFormat("MMMM dd, yyyy hh:mm:ss a").parse(sPosDate); 
                double total = 0;
                if(!bookSpinArray.isEmpty()){ 
                    for(Spinner s : bookSpinArray){ 
                        total += (Book.getSalePrice((Integer) s.getUserData())) 
                                * ((Integer) s.getValue());
                    }
                } 
                if(!conSpinArray.isEmpty()){ 
                    for(Spinner s : conSpinArray){ 
                        total += (Consumable.getSalePrice((Integer) s.getUserData())) 
                                * ((Integer) s.getValue());
                    }
                }
                int custID = customerArray.get(
                        cmboAddPOSCustomer.getSelectionModel().getSelectedIndex())
                        .getCustID(); 
                int storeID = storeArray.get(
                        cmboAddPOSStore.getSelectionModel().getSelectedIndex())
                        .getStoreID(); 
                POS.newPos(storeID, custID, posDate.getTime(),total); 
                addOutput.setVisible(true);
                addOutput.setText("POS added!");
                Account_Transaction.newAcctTransation(posDate.getTime(), total, 0);
                posPaneControl = 4;
                if(!bookSpinArray.isEmpty()){ 
                    for(Spinner s : bookSpinArray){ 
                        POS_Line_Item.newPosLineItem(POS.getNextID(), 
                                (Integer) s.getUserData(), 1, 
                                (Integer) s.getValue()); 
                        Book.setQuantity(storeID, (Integer) s.getUserData(), 
                                ((Integer) s.getValue())*-1);
                    } 
                    
                } 
                
                if(!conSpinArray.isEmpty()){ 
                    for(Spinner s: conSpinArray){ 
                        POS_Line_Item.newPosLineItem(POS.getNextID(), 
                                (Integer) s.getUserData(), 0, 
                                (Integer) s.getValue()); 
                        Consumable.setQuantity(storeID, (Integer) s.getUserData(), 
                                ((Integer) s.getValue())*-1);
                    } 
                    
                }
                bookSpinArray.clear(); 
                mnuBook.getItems().clear();
                conSpinArray.clear(); 
                mnuConsumable.getItems().clear(); 
                
            } catch (ParseException ex) {
                addOutput.setVisible(true);
                addOutput.setText("Please enter a valid date");
            }
        }); 
        
        //Setting up add Product Order for second half of page 
        Label lblAddProductOrder = new Label("Add New Product Order");
        lblAddProductOrder.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
        GridPane.setHalignment(lblAddProductOrder, HPos.RIGHT); 

        Label lblAddProductOrderDate = new Label ("Select a Date:"); 
        Label lblAddProductOrderStore = new Label ("Select a Store:"); 
        Label lblAddProductOrderProducts = new Label("Items Ordered:");
        Label lblAddProductOrderSupplier = new Label ("Select a Supplier:");  
        
        DatePicker datePick2 = new DatePicker(); 
        datePick2.setEditable(false);
        // Technique retrieved from 
        // https://stackoverflow.com/a/665463
        JSpinner spinTimePick2 = new JSpinner( new SpinnerDateModel() ); 
        JSpinner.DateEditor timeEditor2 = new JSpinner.DateEditor(spinTimePick, "hh:mm:ss a");
        spinTimePick2.setEditor(timeEditor2);
        spinTimePick2.setValue(new Date());  
        
        SwingNode timePick2 = new SwingNode(); 
        timePick2.setContent(spinTimePick2); 
        
        addProductOrderPane.add(lblAddProductOrder, 0, 0); 
        addProductOrderPane.add(lblAddProductOrderStore, 0, 1); 
        addProductOrderPane.add(lblAddProductOrderSupplier, 0, 2); 
        addProductOrderPane.add(lblAddProductOrderDate, 0, 3); 
        addProductOrderPane.add(lblAddProductOrderProducts, 0, 4); 


        addProductOrderPane.add(cmboAddProductOrderStore,1,1); 
        addProductOrderPane.add(cmboAddProductOrderSupplier, 1, 2); 
        addProductOrderPane.add(datePick2, 1, 3); 
        addProductOrderPane.add(timePick2, 2, 3); 
        addProductOrderPane.add(mnuBar, 1, 4);  
        
        cmboAddProductOrderStore.setOnAction(e -> { 
            int storeSelection = cmboAddProductOrderStore.getSelectionModel().getSelectedIndex();
            if(storeSelection>-1){ 
                mnuBook.getItems().clear(); 
                mnuConsumable.getItems().clear();
                int storeID = storeArray.get(storeSelection).getStoreID(); 
                EventHandler<ActionEvent> action = changeProductOrderPlacement();
                bookArray = Book.getBookStoreInventory(storeID);
                consumableArray = Consumable.getConsumableStoreInventory(storeID); 
                if(!bookArray.isEmpty()){ 
                    for(Book b: bookArray){ 
                        MenuItem mnuItem = new MenuItem(b.getBookTitle()); 
                        mnuBook.getItems().add(mnuItem); 
                        mnuItem.setUserData("b"+b.getBookID()); 
                        mnuItem.setOnAction(action);
                    }
                } 
                if(!consumableArray.isEmpty()){ 
                    for(Consumable c: consumableArray){ 
                        MenuItem mnuItem = new MenuItem(c.getConName()); 
                        mnuConsumable.getItems().add(mnuItem); 
                        mnuItem.setUserData("c"+c.getConID()); 
                        mnuItem.setOnAction(action);
                    }
                }
            }
        }); 
        
        
        btnAddProductOrder.setOnAction(e -> {
            Date timePickDate = (Date) spinTimePick2.getValue(); 
            SimpleDateFormat formatDate = new SimpleDateFormat("h:mm:ss a"); 
            String sPosDate = ""; 
            sPosDate = datePick2.getValue().format(DateTimeFormatter.ofPattern("MMMM dd, yyyy")); 
            sPosDate = sPosDate + " " + formatDate.format(timePickDate); 
            try {
                Date posDate = new SimpleDateFormat("MMMM dd, yyyy hh:mm:ss a").parse(sPosDate); 
                double total = 0;
                if(!bookSpinArray.isEmpty()){ 
                    for(Spinner s : bookSpinArray){ 
                        total += (Book.getSalePrice((Integer) s.getUserData())) 
                                * ((Integer) s.getValue());
                    }
                } 
                if(!conSpinArray.isEmpty()){ 
                    for(Spinner s : conSpinArray){ 
                        total += (Consumable.getSalePrice((Integer) s.getUserData())) 
                                * ((Integer) s.getValue());
                    }
                }
                int suppID = supplierArray.get(
                        cmboAddProductOrderSupplier.getSelectionModel().getSelectedIndex())
                        .getSuppID(); 
                int storeID = storeArray.get(
                        cmboAddProductOrderStore.getSelectionModel().getSelectedIndex())
                        .getStoreID(); 
                Product_Order.newProductOrder(posDate.getTime(), suppID, storeID, total); 
                addOutput.setVisible(true);
                addOutput.setText("Product Order added!");
                Account_Transaction.newAcctTransation(posDate.getTime(), total, 1);

                if(!bookSpinArray.isEmpty()){ 
                    for(Spinner s : bookSpinArray){ 
                        Book_Order_Line_Item.newBoli(Product_Order.getNextID(), 
                                (Integer) s.getUserData(), 
                                (Integer) s.getValue()); 
                        Book.setQuantity(storeID, (Integer) s.getUserData(), 
                                (Integer) s.getValue());
                    } 
                    
                } 
                
                if(!conSpinArray.isEmpty()){ 
                    for(Spinner s: conSpinArray){ 
                        Consumable_Order_Line_Item.newColi(POS.getNextID(), 
                                (Integer) s.getUserData(), 
                                (Integer) s.getValue()); 
                        Consumable.setQuantity(storeID, (Integer) s.getUserData(),
                                (Integer) s.getValue());
                    } 
                    
                }
                bookSpinArray.clear(); 
                mnuBook.getItems().clear();
                conSpinArray.clear(); 
                mnuConsumable.getItems().clear(); 
                productOrderPaneControl = 4;
                
            } catch (ParseException ex) {
                addOutput.setVisible(true);
                addOutput.setText("Please enter a valid date");
            }
        });
        

        //Setting up add Consumable pane for second half of page
        Label lblAddConsumable = new Label("Add New Consumable");
        lblAddConsumable.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
        GridPane.setHalignment(lblAddConsumable, HPos.RIGHT);

        Label lblAddConsName = new Label("Name: ");
        Label lblAddConsDesc = new Label("Description: ");
        Label lblAddConsCost = new Label("Aquisition Cost: ");
        Label lblAddConsSalePrice = new Label("Retail Price: ");
        Label lblAddConsQuantity = new Label("Quantity to add: ");
        Label lblAddConsStore = new Label("Adding to Store: ");

        TextField txtAddConsName = new TextField();
        TextField txtAddConsDesc = new TextField();
        TextField txtAddConsCost = new TextField();
        TextField txtAddConsSalePrice = new TextField();
        TextField txtAddConsQuantity = new TextField();

        Button btnAddConsumable = new Button("Add Consumable");

        addConsumablePane.add(lblAddConsumable, 0, 0);

        addConsumablePane.add(lblAddConsName, 0, 1);
        addConsumablePane.add(lblAddConsDesc, 0, 2);
        addConsumablePane.add(lblAddConsCost, 0, 3);
        addConsumablePane.add(lblAddConsSalePrice, 0, 4);
        addConsumablePane.add(lblAddConsQuantity, 0, 5);
        addConsumablePane.add(lblAddConsStore, 0, 6);

        addConsumablePane.add(txtAddConsName, 1, 1);
        addConsumablePane.add(txtAddConsDesc, 1, 2);
        addConsumablePane.add(txtAddConsCost, 1, 3);
        addConsumablePane.add(txtAddConsSalePrice, 1, 4);
        addConsumablePane.add(txtAddConsQuantity, 1, 5);
        addConsumablePane.add(cmboAddChooseStore2, 1, 6);

        addConsumablePane.add(btnAddConsumable, 1, 7);

        btnAddConsumable.setOnAction(e -> {
            try {
                addOutput.setVisible(true);
                boolean toAddFlag = true;

                String storeNumStr = cmboAddChooseStore2.getSelectionModel().getSelectedItem().toString();
                char storeNumChar = storeNumStr.charAt(0);
                int storeNumInt = Character.getNumericValue(storeNumChar);

                for (Consumable c : consumableArray) {
                    if (txtAddConsName.getText().matches(c.getConName())
                            && txtAddConsDesc.getText().matches(c.getConDesc())
                            && storeNumInt == c.getConStore()) {

                        int newQuantity = c.getConQuantity();
                        newQuantity = newQuantity + Integer.parseInt(txtAddConsQuantity.getText());
                        c.setConQuantity(newQuantity);

                        addOutput.setText("Consumable is already in inventory for store. Updating quantity."
                                + "\nNew Quantity is: " + c.getConQuantity());

                        toAddFlag = false;
                    }
                }

                if (toAddFlag == true) {
                    Consumable.newCon(txtAddConsName.getText(), Double.parseDouble(txtAddConsCost.getText()),
                            Double.parseDouble(txtAddConsSalePrice.getText()),
                            txtAddConsDesc.getText(), Integer.parseInt(txtAddConsQuantity.getText()), storeNumInt);

                    addOutput.setText("Consumable successfully added.");
                }

                txtAddConsName.clear();
                txtAddConsCost.clear();
                txtAddConsSalePrice.clear();
                txtAddConsDesc.clear();
                txtAddConsQuantity.clear();

                if (invTabStoreCombo.size() < 2) {
                    consumableInventoryTableData.clear();

                    for (Consumable c : consumableArray) {
                        consumableInventoryTableData.add(c);
                    }

                    consumableInventoryView.setItems(consumableInventoryTableData);
                }
            } catch (NumberFormatException n) {
                addOutput.setText("Please ensure that Aquisition Cost, Retail Price"
                        + ", and Quantity are numbers and that you have selected a store.");
            } catch (NullPointerException npe) {
                addOutput.setVisible(true);
                addOutput.setText("Please ensure that Aquisition Cost, Retail Price"
                        + ", and Quantity are numbers and that you have selected a store.");
            }

        });

        //Radio Button Handlers to hide or display the desired pane to add objects
        rdoCreateCustomer.setOnAction(e -> {
            if (rdoCreateCustomer.isSelected()) {
                hideAddPanes();
                addCustPane.setVisible(true);
                addOutput.setVisible(false);
            }
        });

        rdoCreateEmployee.setOnAction(e -> {
            if (rdoCreateEmployee.isSelected()) {
                hideAddPanes();
                addEmployeePane.setVisible(true);
                addOutput.setVisible(false);
            }
        });

        rdoCreateStore.setOnAction(e -> {
            if (rdoCreateStore.isSelected()) {
                hideAddPanes();
                addStorePane.setVisible(true);
                addOutput.setVisible(false);
            }
        });

        rdoCreateSupplier.setOnAction(e -> {
            if (rdoCreateSupplier.isSelected()) {
                hideAddPanes();
                addSupplierPane.setVisible(true);
                addOutput.setVisible(false);
            }
        });

        rdoCreateBook.setOnAction(e -> {
            if (rdoCreateBook.isSelected()) {
                hideAddPanes();
                addBookPane.setVisible(true);
                addOutput.setVisible(false);
            }
        });

        rdoCreateConsumable.setOnAction(e -> {
            if (rdoCreateConsumable.isSelected()) {
                hideAddPanes();
                addConsumablePane.setVisible(true);
                addOutput.setVisible(false);
            }
        }); 
        
        rdoCreatePOS.setOnAction(e -> { 
            if (rdoCreatePOS.isSelected()) {
                hideAddPanes(); 
                populateChooseStoreCombos(); 
                populateChooseCustomerCombos();
                addPOSPane.setVisible(true);
                addOutput.setVisible(false);
            }
        }); 
        
        rdoCreateProductOrder.setOnAction(e -> { 
            if (rdoCreateProductOrder.isSelected()) {
                hideAddPanes(); 
                populateChooseStoreCombos(); 
                populateChooseSupplierCombos();
                addProductOrderPane.setVisible(true);
                addOutput.setVisible(false);
            }
        });
    }

    public void hideAddPanes() {
        addCustPane.setVisible(false);
        addEmployeePane.setVisible(false);
        addStorePane.setVisible(false);
        addSupplierPane.setVisible(false);
        addBookPane.setVisible(false);
        addConsumablePane.setVisible(false); 
        addPOSPane.setVisible(false); 
        addProductOrderPane.setVisible(false);
    }

    public ArrayList enrollCustomer() {
        // ArrayList<Customer> enrollCustArray = new ArrayList<>();
        Customer selectedCust = custView.getSelectionModel().getSelectedItem();

        custLoyaltyArray.add(selectedCust);
        return custLoyaltyArray;
    }

    public void removeCustomer() {
        int custID = 0;
        int custLoyaltyID = 0;
        Customer selectedCust = custView.getSelectionModel().getSelectedItem();

        try {
            for (Customer c : customerArray) {
                custID = c.getCustID();
                for (Customer l : custLoyaltyArray) {
                    custLoyaltyID = l.getCustID();

                    if (custID == custLoyaltyID) {
                        custLoyaltyView.getSelectionModel().select(selectedCust);
                        custLoyaltyView.getItems().remove(selectedCust);
                        custLoyaltyArray.remove(selectedCust);
                        custView.getItems().remove(selectedCust);
                        customerArray.remove(selectedCust);
                    }
                }
            }

        } catch (ConcurrentModificationException e) {
            System.out.println("");
        }
        custView.getItems().remove(selectedCust);
        customerArray.remove(selectedCust);
        txtAreaCustDesc.clear();
        modCustomerPane.setVisible(false);
    }

    public void populateChooseStoreCombos() {
        cmboInvChooseStore.getItems().clear();
        cmboAddChooseStore.getItems().clear();
        cmboAddChooseStore2.getItems().clear();
        cmboChooseEmployeeStore.getItems().clear(); 
        cmboAddPOSStore.getItems().clear(); 
        cmboAddProductOrderStore.getItems().clear();
        for (Store s : storeArray) {
            invTabStoreCombo.add(s.getStoreID() + " : " + s.getStoreName());
        }
        cmboInvChooseStore.setItems(invTabStoreCombo);
        cmboAddChooseStore.setItems(invTabStoreCombo);
        cmboAddChooseStore2.setItems(invTabStoreCombo); 
        cmboChooseEmployeeStore.setItems(invTabStoreCombo); 
        cmboAddPOSStore.setItems(invTabStoreCombo); 
        cmboAddProductOrderStore.setItems(invTabStoreCombo);        
    }  
    
    public void populateChooseCustomerCombos(){ 
        cmboAddPOSCustomer.getItems().clear(); 
        customerArray = Customer.getCustArray();
        for (Customer c: customerArray){ 
            cmboAddPOSCustomer.getItems().add(c.getCustFirstName()+" "+c.getCustLastName());
        }
    } 
    
    public void populateChooseSupplierCombos(){ 
        cmboAddProductOrderSupplier.getItems().clear(); 
        supplierArray = Supplier.getSupplierArray(); 
        for(Supplier s: supplierArray){ 
            cmboAddProductOrderSupplier.getItems().add(s.getSuppName());
        }
    }
    
        
    
    private EventHandler<ActionEvent> changeCustPOSPlacement(int storeID) {
        return new EventHandler<ActionEvent>() {
            
            public void handle(ActionEvent event) {
                
                addPOSPane.getChildren().removeAll(lblAddPOSTax,lblAddPOSTotal,
                        lblAddPOSTaxAmount, lblAddPOSTotalAmount, lblAddPOSSub,
                        lblAddPOSSubAmount,btnAddPOS);
                MenuItem mnuItem = (MenuItem) event.getSource();
                Label lblProductName = new Label(mnuItem.getText()); 
                addPOSPane.add(lblProductName, 0, ++posPaneControl);
                if(mnuItem.getUserData().toString().substring(0, 1).matches("b"))
                { 
                    int bookID = Integer.valueOf(mnuItem.getUserData().toString().substring(1)); 
                    int limit = Book.getQuantity(storeID, bookID); 
                    // From SpinnerDemo: 
                    // http://o7planning.org/en/11185/javafx-spinner-tutorial 
                    Spinner<Integer> spinQuantity = new Spinner<>();
                    SpinnerValueFactory<Integer> valueFactory = 
                    new SpinnerValueFactory.IntegerSpinnerValueFactory(1, limit, 0);
                    spinQuantity.setValueFactory(valueFactory); 
                    spinQuantity.setUserData(bookID); 
                    bookSpinArray.add(spinQuantity); 
                    addPOSPane.add(spinQuantity, 1, posPaneControl);
                }  
                else
                { 
                    int conID = Integer.valueOf(mnuItem.getUserData().toString().substring(1));
                    int limit = Consumable.getQuantity(storeID,conID); 
                    Spinner<Integer> spinQuantity = new Spinner<>();
                    SpinnerValueFactory<Integer> valueFactory = 
                    new SpinnerValueFactory.IntegerSpinnerValueFactory(1, limit, 0);
                    spinQuantity.setValueFactory(valueFactory);  
                    spinQuantity.setUserData(conID); 
                    conSpinArray.add(spinQuantity); 
                    addPOSPane.add(spinQuantity, 1, posPaneControl);
                }
                
                addPOSPane.add(btnAddPOS, 0, posPaneControl+1);
                                
                Menu parentMenu = mnuItem.getParentMenu();
                mnuItem.getParentMenu().getItems().remove(mnuItem); 
                if(parentMenu.getItems().isEmpty()){ 
                    parentMenu.getParentMenu().getItems().remove(parentMenu);
                }
                
                
                
            }
        };

    } 
    
    private EventHandler<ActionEvent> changeProductOrderPlacement() {
        return new EventHandler<ActionEvent>() {
            
            public void handle(ActionEvent event) {
                
                addProductOrderPane.getChildren().removeAll(btnAddProductOrder);
                MenuItem mnuItem = (MenuItem) event.getSource();
                Label lblProductName = new Label(mnuItem.getText()); 
                addProductOrderPane.add(lblProductName, 0, ++productOrderPaneControl);
                if(mnuItem.getUserData().toString().substring(0, 1).matches("b"))
                { 
                    int bookID = Integer.valueOf(mnuItem.getUserData().toString().substring(1)); 
                    // From SpinnerDemo: 
                    // http://o7planning.org/en/11185/javafx-spinner-tutorial 
                    Spinner<Integer> spinQuantity = new Spinner<>();
                    SpinnerValueFactory<Integer> valueFactory = 
                    new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 500, 0);
                    spinQuantity.setValueFactory(valueFactory); 
                    spinQuantity.setUserData(bookID); 
                    bookSpinArray.add(spinQuantity); 
                    addProductOrderPane.add(spinQuantity, 1, productOrderPaneControl);
                }  
                else
                { 
                    int conID = Integer.valueOf(mnuItem.getUserData().toString().substring(1));
                    Spinner<Integer> spinQuantity = new Spinner<>();
                    SpinnerValueFactory<Integer> valueFactory = 
                    new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 500, 0);
                    spinQuantity.setValueFactory(valueFactory);  
                    spinQuantity.setUserData(conID); 
                    conSpinArray.add(spinQuantity); 
                    addProductOrderPane.add(spinQuantity, 1, productOrderPaneControl);
                }
                
                addProductOrderPane.add(btnAddProductOrder, 0, productOrderPaneControl+1);
                                
                Menu parentMenu = mnuItem.getParentMenu();
                mnuItem.getParentMenu().getItems().remove(mnuItem); 
                if(parentMenu.getItems().isEmpty()){ 
                    parentMenu.getParentMenu().getItems().remove(parentMenu);
                }
                
                
                
            }
        };

    }
    

    
}
