package Project;
import java.util.Scanner;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
public class Customer extends User {
    private int Cpoints; 
    private State myState; 
    private String intial = null; 
    public Customer(String username,String password){
        super(username, password);
        
       myState = new Silver();
       Cpoints = 0;
    }
    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
    public int getPoints(){
        return Cpoints;
    }
    public void setPoints(int s ){
        Cpoints =s;
    }
    public void SetUsername(String s){
        username = s;
    }
    public void SetPassword(String s){
        
        if(intial == null){ 
            password = s;
            intial = "LockedPassword";
        }
        
    }
    public void ChangePassword(Customer s, String k, String p) throws IllegalAccessError{
        Scanner scan = new Scanner(System.in);
        int counter = 0;
        if(s.getPassword() ==k){
            intial = null;
            s.SetPassword(p);
        }
        else{
            while(counter<3){
                    System.out.println("Im sorry incorrect password--> enter password again");
                     k = scan.next();
                     if(s.getPassword() ==k){
                        intial = null;
                        s.SetPassword(p);
                        counter = 2;
                    }
                    counter++;
            }
            if(s.getPassword()!=p) throw new IllegalAccessError("Surpassed Allowed Attempts: Try again later");
        }
        System.out.println("Password was Successfully changed");

    }
    // need to fix this*
    public void setStatus(){
        if(Cpoints>=1000) myState = new Gold();
        else myState = new Silver();
    }
   
    @Override
    public String toString(){
        String clist =getUsername()+","+getPassword()+","+getPoints()+"\n";
        //System.out.println(clist);
        return clist;
    }
    //testing methods//
   
    

    public static void main(String args[]){
        
    }
    
}
