package Project;

/**
 *
 * @author feroz
 */
public class Book {
  
    String name;
    double price;

    public Book(String name, double price){
        this.name =name;
        this.price = price; 
    }
    
    public String getBookName() {
        return name;
    }

    public double getBookPrice() {
        return price;
    }
    @Override
    public String toString(){
        String list;
        list = getBookName()+","+getBookPrice()+"\n";
        return list;
    }
    
}
