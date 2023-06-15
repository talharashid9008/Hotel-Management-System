package business_Logic;

import java.io.Serializable;

public class Reservation implements Serializable {

    private String reservation_ID;
    private String room_No;
    private String from_date;
    private String to_date;
    private String Customer_CNIC;
    private String amount;
    private Check_in check_in;
    private Check_out check_out;
    private Payment payment;

    public Reservation(String reservation_ID, String room_No, String from_date, String to_date, String customer_CNIC,String amount) {
        super();
        this.reservation_ID = reservation_ID;
        this.room_No = room_No;
        this.from_date = from_date;
        this.to_date = to_date;
        Customer_CNIC = customer_CNIC;
        this.amount = amount;
        this.check_in=null;
        this.check_out=null;
        this.payment=null;
    }
    public String getReservation_ID() {
        return reservation_ID;
    }
    public Check_in getCheck_In() {
        return check_in;
    }
    public void setCheck_In(Check_in check) {
        check_in=check;
    }

    public void setReservation_ID(String reservation_ID) {
        this.reservation_ID = reservation_ID;
    }
    public String getRoom_No() {
        return room_No;
    }
    public void setRoom_No(String room_No) {
        this.room_No = room_No;
    }
    public String getFrom_date() {
        return from_date;
    }
    public void setFrom_date(String from_date) {
        this.from_date = from_date;
    }
    public String getTo_date() {
        return to_date;
    }
    public void setTo_date(String to_date) {
        this.to_date = to_date;
    }
    public String getCustomer_CNIC() {
        return Customer_CNIC;
    }
    public void setCustomer_CNIC(String customer_CNIC) {
        Customer_CNIC = customer_CNIC;
    }
    public String getAmount() {
        return amount;
    }
    public void setAmount(String amount) {
        this.amount = amount;
    }
    public Check_out getCheck_out() {
        return check_out;
    }
    public void setCheck_out(Check_out check_out) {
        this.check_out = check_out;
    }
    public Payment getPayment() {
        return payment;
    }
    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
