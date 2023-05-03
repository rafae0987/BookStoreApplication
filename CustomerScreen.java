/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project;

import java.util.Set;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CustomerScreen {
    
    private String getUser;
    private final Stage stage;
    
    GridPane root = new GridPane();
    GridPane root1 = new GridPane();
    GridPane root2 = new GridPane();
    
    Label welcome = new Label("WELCOME " + getUser + ", YOU HAVE " + getUser +" POINTS ," + " YOUR STATUS IS " + getUser);
    
    Button btn = new Button("Buy");
    Button btn1 = new Button("Reedeem Points & Buy");
    Button btn2 = new Button("Logout");
 
   
    
    public CustomerScreen(Stage stage) {
        this.stage = stage;
        Scene scene3 = new Scene(createScene3(), 800, 800);
        
        // Create the third scene
        btn2.setOnAction(e -> switchScene1());

         // Set the second scene to the stage
        stage.setScene(scene3);
    }
    
    //GUI for scene3     
    private BorderPane createScene3() {
        
         TableView<Customer> table; 
        //name column 
        TableColumn<Customer,String> bookName = new TableColumn<>("Book Name");

        bookName.setCellValueFactory(new PropertyValueFactory<>("Book Name"));
        
        //Password Column
        TableColumn<Customer,String> bookPrice = new TableColumn<>("Book Price");
        
        bookPrice.setCellValueFactory(new PropertyValueFactory<>("Book Price"));
        
        //Password Column
        TableColumn<Customer,String> select = new TableColumn<>("Select");
        
        select.setCellValueFactory(new PropertyValueFactory<>("Select"));
        
        table = new TableView<>();
        bookName.setMinWidth(270);
        bookPrice.setMinWidth(270);
        select.setMinWidth(270);
        table.getColumns().addAll(bookName,bookPrice, select);
        
        
        HBox Label = new HBox(welcome);
        HBox buttons = new HBox(btn, btn1, btn2);
        
        Label.setAlignment(Pos.CENTER);
        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(10);
        root.setHgap(10);
        root.setVgap(10);

        BorderPane root = new BorderPane();
        root.setTop(Label);
        root.setCenter(table);
        root.setBottom(buttons);    

        return root;
    }
   
     
       
         // Method to switch to the first scene
    private void switchScene1() {
        LoginPage scene1 = new LoginPage(stage);
    }
    }
    
   
 

