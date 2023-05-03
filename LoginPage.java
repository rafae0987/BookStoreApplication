/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class LoginPage {

    private FileManager fileManager = new FileManager();
    private final Stage stage;
    private Customer customer;
    private TableView<Book> table;
    GridPane root = new GridPane();


    Label labels = new Label();
    Label userID = new Label("User ID");
    Label passID = new Label("Password");

    TextField user = new TextField("admin");
    TextField password = new TextField("admin");

    Button btn = new Button("Login");
    Button btn1 = new Button("Exit");


    public LoginPage(Stage stage) {
        this.stage = stage;

        // Create the first scene
        btn1.setOnAction(e -> System.exit(0));
        btn.setOnAction(e -> {
            User authenticatedUser = authenticate(user.getText(), password.getText());
            if (authenticatedUser instanceof Owner) {
                OwnerScreen scene2 = new OwnerScreen(stage);
            } else if (authenticatedUser instanceof Customer) {
                CustomerScreen scene3 = new CustomerScreen(stage);
            } else {
                labels.setText("Error: Invalid username or password.");
                root.add(labels, 0, 5);
            }
        });

        root.setAlignment(Pos.CENTER);
        root.setHgap(10);
        root.setVgap(10);
        root.setPadding(new Insets(25, 25, 25, 25));

        root.add(userID, 0, 0);
        root.add(user, 1, 0);
        root.add(passID, 0, 1);
        root.add(password, 1, 1);

        root.add(btn, 0, 3);
        root.add(btn1, 1, 3);

        Scene scene1 = new Scene(root, 800, 800);

        // Set the first scene as the primary scene
        stage.setScene(scene1);
    }



    // Authenticates the user based on the provided username and password
    private User authenticate(String username, String password) {
        // Authenticate as an owner
        if (username.equals(Owner.getInstance().getUsername()) && password.equals(Owner.getInstance().getPassword())) {
            return Owner.getInstance();
        }
        
        // Authenticate as a customer
        for (Customer customer : fileManager.getCustomersList()) {
            if (customer.getUsername().equals("customer") && customer.getPassword().equals("customer")) {
                return customer;
            }
        }
        // Authentication failed 
        return null;
    }
}
