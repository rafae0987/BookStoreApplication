
package Project;

import java.util.ArrayList;

public class Library {
    private static ArrayList<Book> books = new ArrayList<Book>();
    private static Library instance;
    Library(){
        
    }

    public static Library getInstance(){
        if(instance == null) instance = new Library();
        return instance;
    }

    public void addBook(Book s){
        books.add(s);
    }

    public void removeBook(Book s){
        books.remove(s);
    }

    public  ArrayList<Book> getBookItems(){
        return books; 
     
    }
}
