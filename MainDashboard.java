package BookIt_IS;

import java.time.format.DateTimeFormatter;
import java.util.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

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
        invPane.setAlignment(Pos.CENTER);
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
    
    }
}
