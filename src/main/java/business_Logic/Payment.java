package business_Logic;

import java.io.Serializable;

public class Payment implements Serializable {
    public Payment(double amount, String date, String comments, String reservation_id) {
        super();
        this.amount = amount;
        this.date = date;
        this.comments = comments;
        this.reservation_id = reservation_id;
    }
    double amount;
    String date;
    String comments;
    String reservation_id;
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }
    public String getReservation_id() {
        return reservation_id;
    }
    public void setReservation_id(String reservation_id) {
        this.reservation_id = reservation_id;
    }

}
