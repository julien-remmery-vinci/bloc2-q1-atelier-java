package be.vinci.classes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class User {

    private int id;
    public String firstName;
    public String lastName;
    //private String Visa;
    //private List<Order> orders = new ArrayList<Order>();
    private Order order = new Order(LocalDateTime.now());

    public User(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        //orders.add(new Order(LocalDateTime.now()));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /*public List<Order> getOrders() {
        return orders;
    }

    public boolean addOrder(Order order) {
        return this.orders.add(order);
    }*/

}
