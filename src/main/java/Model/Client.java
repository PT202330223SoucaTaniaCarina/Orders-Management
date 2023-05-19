package Model;

/**
 *  Client Class.
 * @author tania
 */

public class Client {
    private int id;
    private String name;
    private String email;
    private String town;


    public Client(String name, String email, String town){
        this.name = name;
        this.email = email;
        this.town = town;
    }

    public Client(String name, String town){
        this.name = name;
        this.town = town;
    }

    public Client(){

    }

    public String toString(){
        return "~Client --> ID = " + id + ", Name = '" + name + "', E-mail = '" + email + "', town = '" + town + "'~";
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }
}
