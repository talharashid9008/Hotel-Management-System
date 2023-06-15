package business_Logic;



//import javafx.scene.control.Alert;


import static java.security.AccessController.getContext;

import android.os.Build;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import java.io.Serializable;
import java.util.ArrayList;

public class Hotel implements Serializable {
    private Reception reception;
    private Room_Catalog room_catalog;
    private Accounts accounts;
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Hotel() {
        reception=new Reception();
        room_catalog=new Room_Catalog();
        accounts=new Accounts();
    }
    public void addCustomer(String name, String city, String country, String address, int postal_code,String cnic,String phone,String dob)
    {
        System.out.println("Size Before Addition in Hotel:"+reception.customers.size());
        reception.addCustomer(name, city, country, address, postal_code, cnic, phone, dob);
        System.out.println("Size After Addition in Hotel:"+reception.customers.size());

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Boolean makeReservation(String room_No, String from_date, String to_date, String customer_CNIC,
                                String amount)
    {
        if(room_catalog.bookRoom(room_No))
        {
            reception.addReservation( room_No, from_date, to_date, customer_CNIC, amount);
            return true;
        }
        return false;
    }

    public boolean updateReservation(int index,String room_No, String from_date, String to_date, String customer_CNIC,
                                  String amount)
    {
        String old_room=reception.getReservation(index).getRoom_No();
        if(!reception.updateReservation(index, room_No, from_date, to_date, customer_CNIC, amount))
        {
            room_catalog.ChangeRooms(room_catalog.getRoom(old_room), room_catalog.getRoom(room_No));
        }
        return true;
    }

    public boolean deleteReservation(int index)
    {
        String old_room=reception.getReservation(index).getRoom_No();
        reception.deleteReservation(index);
        room_catalog.deleteRoom(room_catalog.getRoom(old_room));
        return true;
    }


    public void addCheckin(String res_id,String date, String comments)
    {
        reception.addCheckin(res_id, date, comments);

        int index=Integer.parseInt(res_id.split("-")[1])-1;

        accounts.addTotal_Amount_Due(Double.parseDouble(reception.getReservation(index).getAmount()));
    }
    public boolean addCheckout(String res_id,String date, String comments)
    {
        return reception.addCheckout(res_id, date, comments);
    }
    public ArrayList<Customer> getCustomers()
    {

        return reception.getCustomers();

    }
    public ArrayList<String> getReservationIDforPayments()
    {
        return reception.getReservationIDforPayments();
    }

    public ArrayList<Reservation> getReservationsforPayments()
    {
        return reception.getReservationsforPayments();
    }

    public ArrayList<String> getAvailableRooms(int cap,double price)
    {
       // Toast.makeText(getApplicationContext(),Double.toString(price),Toast.LENGTH_LONG).show();
        return room_catalog.getAvailableRooms(cap,price);

    }

    public ArrayList<String> getPrices(int cap)
    {

        return room_catalog.getPrices(cap);

    }

    public ArrayList<Reservation> getReservations()
    {

        return reception.getReservations();

    }
    public Reservation getReservation(int index)
    {

        return reception.getReservation(index);

    }
    public Room getRoom(String room_no)
    {
        return room_catalog.getRoom(room_no);
    }
    public ArrayList<String> getReservationIDs()
    {

        return reception.getReservationIDs();

    }
    public ArrayList<String> getReservationIDforCheckouts()
    {

        return reception.getReservationIDforCheckOuts();

    }

    public boolean customer_exists(String cnic)
    {
        return reception.customer_exists(cnic);
    }

    public ArrayList<Check_in> getCheckins()
    {
        return reception.getCheckins();
    }
    public ArrayList<Check_out> getCheckouts()
    {
        return reception.getCheckouts();
    }
    public 	ArrayList<Room> getRooms()
    {
        return room_catalog.getRooms();
    }

    public boolean makePayment(String amount, String date, String comments, String reservation_id)
    {
        double am=Double.parseDouble(amount);
        int index=Integer.parseInt(reservation_id.split("-")[1])-1;
        reception.getReservation(index).setPayment(accounts.makePayment(am, date, comments, reservation_id));
        return true;
    }

    public ArrayList<Payment>getPayments()
    {
        return accounts.getPayments();
    }

    public String getDueAmount()
    {
        return accounts.getTotal_Amount_Due();
    }
    public String getCollectedAmount()
    {
        return accounts.getTotal_Amount_Collected();
    }
    public Reception getReception() {
        return reception;
    }
    public void setReception(Reception reception) {
        this.reception = reception;
    }
    public Room_Catalog getRoom_catalog() {
        return room_catalog;
    }
    public void setRoom_catalog(Room_Catalog room_catalog) {
        this.room_catalog = room_catalog;
    }
    public Accounts getAccounts() {
        return accounts;
    }
    public void setAccounts(Accounts accounts) {
        this.accounts = accounts;
    }

    public Boolean updatePayment(int index, String amount, String date, String comments, String reservation_id) {
        double am=Double.parseDouble(amount);
        int ind=Integer.parseInt(reservation_id.split("-")[1])-1;
        reception.getReservation(ind).setPayment(accounts.updatePayment(index,am, date, comments, reservation_id));
        return true;

    }

    public Payment get_payment(int index){
        return accounts.getPayment( index);
    }

    public void deletePayment(int index) {
        accounts.deletePayment(index);
    }
}

