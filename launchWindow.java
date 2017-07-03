/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BookIt_IS;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author lxpee
 */
public class launchWindow extends Application {
    
    @Override
    public void start(Stage primaryStage) 
    {
        MainDashboard mainDashboard = new MainDashboard();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
