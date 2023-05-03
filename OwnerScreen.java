
package Project;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class OwnerScreen {
    
    String name;
    FileWriter fw;
    PrintWriter pw;
    String text;
    Owner admin;
    Library lib = null;
        

    
    FileManager FileManager = new FileManager();
    public ObservableList<Customer> OwnergetCust(){
        ObservableList<Customer> ownerCustList = FXCollections.observableArrayList();
        ArrayList<Customer> customers = FileManager.getCustomersList();
        for(Customer s:customers){
            ownerCustList.add(s);
        }
        return ownerCustList;
    }
    public ObservableList<Book> LibgetBook(){
        ObservableList<Book> Librarylist = FXCollections.observableArrayList();
        ArrayList<Book> books = FileManager.getBookList();
        for(Book b:books){
           Librarylist.add(b);
        }
        return Librarylist;
    }
    
    private final Stage stage;

    GridPane root = new GridPane();
    GridPane root1 = new GridPane();
    GridPane root2 = new GridPane();
     
    Button btn = new Button("Books");
    Button btn1 = new Button("Customers");
    Button btn2 = new Button("Logout");
    
    Button btnDel1 = new Button("Delete");//cust
    Button btnBack1 = new Button("Back");
    Button btnAdd1 = new Button("Add");
    
    Button btnDel = new Button("Delete");//book
    Button btnBack = new Button("Back");
    Button btnAdd = new Button("Add");
   
    
    public OwnerScreen(Stage stage) {
        this.stage = stage;
        
        Scene scene2 = new Scene(createScene2(), 800, 800);
        Scene ownCust = new Scene(createOwnCust(), 800, 800);
        Scene ownBook = new Scene(createOwnBook(), 800, 800);
        
        // Create the second scene
        btn.setOnAction(e->stage.setScene(ownBook));
        btn1.setOnAction(e -> stage.setScene(ownCust));
        btn2.setOnAction(e -> switchScene1());
        btnBack.setOnAction(e -> goBack());
        btnBack1.setOnAction(e -> goBack());

        
         // Set the second scene to the stage
        stage.setScene(scene2);
    }
    
    private void goBack(){
        OwnerScreen scene2 = new OwnerScreen(stage);
    }
    
    //GUI for scene2     
    private VBox createScene2() {
        
        VBox root = new VBox(btn, btn1, btn2);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);
        
    return root;
    }
   
       
   
     //GUI for ownCust
    private BorderPane createOwnCust() {
        
        TableView<Customer> table; 
        //name column 
        TableColumn<Customer,String> nameColumn = new TableColumn<>("Username");

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("Username"));
        //Password Column
        TableColumn<Customer,String> passColumn = new TableColumn<>("Password");
        
        passColumn.setCellValueFactory(new PropertyValueFactory<>("Password"));
        //points
         TableColumn<Customer,String> pointsColumn = new TableColumn<>("points");
        
         pointsColumn.setCellValueFactory(new PropertyValueFactory<>("points"));
        table = new TableView<>();
        
        nameColumn.setMinWidth(250);
        passColumn.setMinWidth(250);
        pointsColumn.setMinWidth(250);
        
        table.getColumns().addAll(nameColumn,passColumn,pointsColumn);
        
        admin = Owner.getInstance();
        
        TextField textF = new TextField("username");
        TextField textF2 = new TextField("password");
        Text text = new Text();
        text.setFill(Color.RED);
        Text text2 = new Text();
        textF.clear();
        textF2.clear();
        // String uname = textF.getText();
        // String passwrd = textF2.getText();
   

        
        btnAdd1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent s){
                System.out.println("Starting Add operation...");
                Boolean Ccheck = false; 
                Customer ss = new Customer(textF.getText(), textF2.getText());
                for(Customer Ilist:admin.getCustomers()){
                    if((ss.getUsername().equals(Ilist.getUsername())) && (ss.getPassword().equals(Ilist.getPassword()))){
                        System.out.println("Customer Already exits check list again");
                        text.setText("Customer "+Ilist.getUsername()+" Already exits check list again");
                        
                        Ccheck = true;
                    }    
                    }
                if(Ccheck == false){
                    System.out.println("Adding Customer "+ss.getUsername());
                     if(Ccheck == false){
                        admin.AddCustomer(ss);
                        table.getItems().add(ss);
                        FileManager.createtxtFile("Customer.txt");
                        System.out.println("Customer "+ss.getUsername()+" was added");
                        System.out.println("List of all Customers"+"\n");
                        System.out.println("-----------------------");
                        text.setText("");
                        try {
                            FileManager.CopyToCustomerFile(admin.getCustomers());
                            } catch (IOException e) {
                                throw new IllegalAccessError("Something went wrong");
                            }
                        }
                        
                        // table.setItems(OwnergetCust());
                        
                        // table.getItems().addAll(ownercustomerlist);
                    }
            }
        });

        Button btnup = new Button();
        Text text3 = new Text();
        text3.setFill(Color.ALICEBLUE);
        btnup.setText("update");
        btnup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e){
                System.out.println("updating list");
                try {
                    FileManager.ReadContentsCust();
                }  catch (IOException k) {
                }
                admin.getCustomers().clear();
                table.setItems(OwnergetCust());
                text3.setText("List was updated");


            }
        });
        btnDel1.setOnAction(new EventHandler<ActionEvent>() {
            @Override 
            public void handle(ActionEvent e){
                
            }
        });
        
        
        
        HBox storing = new HBox(btnAdd1, textF, textF2,text,btnup,text3);
        HBox buttons = new HBox(btnDel1, btnBack1);
        storing.setAlignment(Pos.CENTER);
        storing.setSpacing(10);
        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(10);

        BorderPane root = new BorderPane();
        root.setCenter(storing);
        root.setTop(table);
        root.setBottom(buttons);
        

        return root;
    }
    
    private BorderPane createOwnBook() {
        lib = Library.getInstance();
    
        TableView<Book> table; 
        //name column 
        TableColumn<Book,String> bookName = new TableColumn<>("Book Name");

        bookName.setCellValueFactory(new PropertyValueFactory<>("Book Name"));
        //Password Column
        TableColumn<Book,Double> bookPrice = new TableColumn<>("Book Price");
        
        bookPrice.setCellValueFactory(new PropertyValueFactory<>("Book Price"));
        TextField textF = new TextField();
        TextField textF2 = new TextField();
        Text text = new Text();
        table = new TableView<>();
        bookName.setMinWidth(350);
        bookPrice.setMinWidth(350);
        table.getColumns().addAll(bookName,bookPrice);
        textF.clear();
        textF2.clear();
        btnAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent s){
                System.out.println("Starting Add operation...");
                Boolean Ccheck = false; 
                Book bb = new Book(textF.getText(), Double.parseDouble(textF2.getText()));
                for(Book Ilist:lib.getBookItems()){
                    if((bb.getBookName().equals(Ilist.getBookName())) && (bb.getBookPrice() == Ilist.getBookPrice())){
                        System.out.println("Book Already exits check list again");
                        text.setText("Book: "+Ilist.getBookName()+" Already exits check list again");
                        Ccheck = true;
                    }    
                    }
                if(Ccheck == false){
                    System.out.println("Adding book "+bb.getBookName());
                     if(Ccheck == false){
                        lib.addBook(bb);
                        table.getItems().add(bb);
                        FileManager.createtxtFile("Book.txt");
                        System.out.println("Book "+bb.getBookName()+" was added");
                        System.out.println("List of all Books"+"\n");
                        System.out.println("-----------------------");
                        text.setText("");
                        try {
                            FileManager.CopyToBookFile(lib.getBookItems());;
                            } catch (IOException e) {
                                throw new IllegalAccessError("Something went wrong");
                            }
                        }
                        
                        // table.setItems(OwnergetCust());
                        
                        // table.getItems().addAll(ownercustomerlist);
                    }
            }
        });
        
        
        HBox storing = new HBox(btnAdd, textF, textF2,text);
        HBox buttons = new HBox(btnDel, btnBack);
        storing.setAlignment(Pos.CENTER);
        storing.setSpacing(10);
        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(10);

        BorderPane root = new BorderPane();
        root.setCenter(storing);
        root.setTop(table);
        root.setBottom(buttons);

        return root;
       
}

       
         // Method to switch to the first scene
    private void switchScene1() {
        LoginPage scene1 = new LoginPage(stage);
    }
    
    }
    
   
 

