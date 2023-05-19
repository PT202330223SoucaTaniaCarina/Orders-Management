package Model;

/**
 *  Product Class.
 * @author tania
 */

public class Product {

    private int id;
    private String name;
    private float price;
    private int initQuantity;

    public Product(String name, int initQuantity, float price){
        this.name = name;
        this.initQuantity = initQuantity;
        this.price = price;
    }

    public Product(int id, String name, int initQuantity, float price){
        super();
        this.id = id;
        this.name = name;
        this.initQuantity = initQuantity;
        this.price = price;
    }

    public Product(){

    }

    public Product(String name, int initQuantity) {
        this.name = name;
        this.initQuantity = getInitQuantity();
    }

    public String toString(){
        return "~Product --> ID = " + id + ", name = " + name + ", price = " + price +
                ", InitialQuantity = " + initQuantity + "~";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getInitQuantity() {
        return initQuantity;
    }

    public void setInitQuantity(int initQuantity) {
        this.initQuantity = initQuantity;
    }
}
