package business_Logic;

import java.io.Serializable;

public class Room implements Serializable {
    public Room(double price, int room_no, int capacity) {
        super();
        this.price = price;
        this.room_no = room_no;
        this.capacity = capacity;
    }
    private double price;
    int room_no;
    int capacity;
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public int getRoom_no() {
        return room_no;
    }
    public void setRoom_no(int room_no) {
        this.room_no = room_no;
    }
    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    public String toString()
    {
        return Integer.toString(room_no);
    }
}
