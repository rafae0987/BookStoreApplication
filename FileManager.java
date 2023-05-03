
package Project;
import java.util.*;

import javafx.collections.FXCollections;
import java.util.ArrayList;

// import java.nio.file.Path;
// import java.util.List;
import java.io.*;
import java.nio.file.Files;
/**
 *
 * 
 */
public class FileManager {
  private ArrayList<Customer> customers = new ArrayList<>();
  private ArrayList<Book> books = new ArrayList<>();
  private static final String FILE_NAME = "Customer.txt";
  private static final String File_name = "Book.txt";
   
   public void ReadContentsCust() throws  IOException{
    customers.clear();
    try(BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))){
        String read = "";
        while((read = reader.readLine())!=null){
            String[] splits = read.split(",");
            // if(){
                String username = splits[0];
                String psswrd = splits[1];
                Customer CC = new Customer(username, psswrd);

                customers.add(CC);
        //}
        }

    }
   }
   public void ReadContentsBook() throws  IOException{
    books.clear();
    try(BufferedReader reader = new BufferedReader(new FileReader(File_name))){
        String read = "";
        while((read = reader.readLine())!=null){
            String[] splits = read.split(",");
            // if(){
                String Book = splits[0];
                String Bookprice = splits[1];
                Book BB = new Book(Book, Integer.parseInt(Bookprice));
                books.add(BB);
               
        //}
        }

    }
   }
   public ArrayList<Customer> getCustomersList(){
    return customers;
   }
   public ArrayList<Book> getBookList(){
        return books; 
   }
   
   
   
     boolean check2 = false;
        //   String text ="";
    //     try{
            
    //         Scanner input = new Scanner(new File("Customer.txt"));
    //         while(input.hasNextLine()) text  = text +input.nextLine()+"\n";
            // if(check2 == false){
            //     createtxtFile("Customer.txt");
            //     System.out.println("File has been created");
               
        
            // }
            // else if(check2 == true){
            //     System.out.println("File already exists");
        
            
            // }
            
        //}   
        // catch(Exception e){
        //     System.out.println("An error occured");
        //     e.printStackTrace();

        //}
   // }
    public void createtxtFile(String name){
        
        File newFile = new File(name);
        try{
            FileWriter fileWrite = new FileWriter(newFile, true);
           
            PrintWriter PrintWriter = new PrintWriter(fileWrite);
            
            check2 = true; 
           
        }
       catch(Exception e){

       }
    }

   
    
    // get all the customer data from the book arraylist and copy it to the customer.txt file
    public void CopyToCustomerFile(ArrayList<Customer> customers) throws IOException{
        //create new customer.txt file
        FileWriter customerFile = new FileWriter("Customer.txt", true);
        //iterate through all the customers in the arraylist
        //copy all the customer info into the file
        for(Customer customer: customers){
          String custInfo = customer.toString();
            customerFile.write(custInfo);
            
        }
       
        
        //close the file
        customerFile.close();
    }
    
    // get all the data from the book arraylist and copy it to the book.txt file
    public void CopyToBookFile(ArrayList<Book> books) throws IOException{
        // create new book.txt file
        FileWriter bookFile = new FileWriter("Book.txt", true);
        //iterate through all the books in the arraylist
        //copy all the elements of the books into the file
        for(Book book: books){
          String bookElements = book.toString();
            bookFile.write(bookElements);
        }
        //close the file
        bookFile.close();
    }    
    
    
    // empty the customer file
    // method will be used when we want to overwrite the existing file with then updated list of customers
    public void EmptyCustomerFile() throws IOException{
        FileWriter customerFile = new FileWriter("Customer.txt", false);
        customerFile.close();
    }
    
    
    // empty the book file
    // method will be used when we want to overwrite the existing file with then updated list of customers
    public void EmptyBookFile() throws IOException{
        FileWriter bookFile = new FileWriter("Book.txt", false);
        bookFile.close();
    }


   
}
