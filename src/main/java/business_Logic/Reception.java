package business_Logic;



import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.databinding.ObservableList;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Vector;



public class Reception implements Serializable {
    public Vector<Customer> customers=new Vector<Customer>(1);
    public Vector<Reservation> reservations=new Vector<Reservation>(1);
    public Vector<Check_in> checkins=new Vector<>(1);
    public Vector<Check_out> checkouts=new Vector<>(1);

    public Reception()
    {

    }
    public void addCustomer(String name, String city, String country, String address, int postal_code,String cnic,String phone,String dob)
    {
        customers.add(new Customer(name,new Address(city,country,address,postal_code),cnic,phone,dob));

    }
    public void addCustomerDB(String name, String city, String country, String address, int postal_code,String cnic,String phone,String dob)
    {
        customers.add(new Customer(name,new Address(city,country,address,postal_code),cnic,phone,dob));
    }



    @RequiresApi(api = Build.VERSION_CODES.O)
    public Reservation addReservation(String room_No, String from_date, String to_date, String customer_CNIC,
                                      String amount)
    {
        DateTimeFormatter f=DateTimeFormatter.ofPattern("yyyy-mm-dd");
    //    LocalDate to=LocalDate.parse(to_date,f);

     //   LocalDate from=LocalDate.parse(from_date,f);

      //  long days=ChronoUnit.DAYS.between(from,to);
        //	System.out.println("Days for reservation are:"+days);
       // double am=Double.parseDouble(amount)*days;
       // amount=Double.toString(am);
        reservations.add(new Reservation("R-"+Integer.toString(reservations.size()+1), room_No, from_date, to_date, customer_CNIC, amount));


        return reservations.elementAt(reservations.size()-1);
    }


    public Reservation addReservationDB(String room_No, String from_date, String to_date, String customer_CNIC,
                                        String amount,Accounts acc)
    {


        reservations.add(new Reservation("R-"+Integer.toString(reservations.size()+1), room_No, from_date, to_date, customer_CNIC, amount));
        checkins.add(new Check_in(to_date, null,reservations.elementAt(reservations.size()-1)));
        reservations.elementAt(reservations.size()-1).setCheck_In(checkins.elementAt(checkins.size()-1));

        acc.addTotal_Amount_Due(Double.parseDouble(amount));
        if(reservations.size()<81)
        {
            acc.makePayment(Double.parseDouble(amount), to_date, customer_CNIC, reservations.elementAt(reservations.size()-1).getReservation_ID());
            reservations.elementAt(reservations.size()-1).setPayment(acc.getPayments().get(acc.getPayments().size()-1));
            if(reservations.size()<41)
            {
                checkouts.add(new Check_out(to_date, null,reservations.elementAt(reservations.size()-1)));
                reservations.elementAt(reservations.size()-1).setCheck_out(checkouts.elementAt(checkouts.size()-1));
            }
        }
        return reservations.elementAt(reservations.size()-1);
    }


    public boolean updateReservation(int index,String room_No, String from_date, String to_date, String customer_CNIC,
                                     String amount)
    {
        boolean same=false;
        if(reservations.elementAt(index).getRoom_No().equals(room_No))
            same=true;
        reservations.elementAt(index).setRoom_No(room_No);
        reservations.elementAt(index).setFrom_date(from_date);
        reservations.elementAt(index).setTo_date(to_date);
        reservations.elementAt(index).setCustomer_CNIC(customer_CNIC);
        reservations.elementAt(index).setAmount(amount);
        return same;
    }
    public void addCheckin(String res_id,String date, String comments)
    {
        int j=0;
        for(int i=0;i<reservations.size();i++)
        {
            if(reservations.elementAt(i).getReservation_ID().equals(res_id))
            {
                System.out.println("Comparing "+reservations.elementAt(i).getReservation_ID()+" with "+res_id+"  "+j+" times");
                checkins.add(new Check_in(date, comments, reservations.elementAt(i)));
                System.out.println("Called "+j+" times");
                j++;
                System.out.println("Size of Checkin :"+checkins.size());
                reservations.elementAt(i).setCheck_In(checkins.elementAt(checkins.size()-1));
            }

        }
    }


    ArrayList<Customer> getCustomers()
    {
        System.out.println("Called size is : "+customers.size());
        ArrayList<Customer> customer=new ArrayList<Customer>();
        for(int i=0;i<customers.size();i++)
        {
            customer.add(customers.elementAt(i));
            System.out.println(customers.elementAt(i).getName());
        }
        return customer;

    }

    ArrayList<Check_in> getCheckins()
    {
        //	System.out.println("Called size is : "+customers.size());
        ArrayList<Check_in> checkin=new ArrayList<>();
        for(int i=0;i<checkins.size();i++)
        {
            checkin.add(checkins.elementAt(i));
//			System.out.println("checkin "+checkins.elementAt(i).());
        }
        return checkin;

    }


    ArrayList<Check_out> getCheckouts()
    {
        //	System.out.println("Called size is : "+customers.size());
        ArrayList<Check_out> checkout=new ArrayList<>();
        for(int i=0;i<checkouts.size();i++)
        {
            checkout.add(checkouts.elementAt(i));
//			System.out.println("checkin "+checkins.elementAt(i).());
        }
        return checkout;

    }

    ArrayList<Reservation> getReservations()
    {
        System.out.println("Called size is : "+customers.size());
        ArrayList<Reservation> reservation=new ArrayList<>();
        for(int i=0;i<reservations.size();i++)
        {
            reservation.add(reservations.elementAt(i));
            System.out.println(reservations.elementAt(i).getReservation_ID());
        }
        return reservation;

    }

    Reservation getReservation(int index)
    {
        return reservations.elementAt(index);

    }

    ArrayList<String> getReservationIDs()
    {
        ArrayList<String> reservation=new ArrayList<>();
        for(int i=0;i<reservations.size();i++)
        {
            if(reservations.elementAt(i).getCheck_In()==null)
                reservation.add(reservations.elementAt(i).getReservation_ID());
            System.out.println(reservations.elementAt(i).getReservation_ID());
        }
        return reservation;

    }

    ArrayList<String> getReservationIDforCheckOuts()
    {
        ArrayList<String> reservation=new ArrayList<>();
        for(int i=0;i<reservations.size();i++)
        {
            if(reservations.elementAt(i).getCheck_out()==null && reservations.elementAt(i).getCheck_In()!=null)
                reservation.add(reservations.elementAt(i).getReservation_ID());
            System.out.println(reservations.elementAt(i).getReservation_ID());
        }
        return reservation;

    }

    ArrayList<String> getReservationIDforPayments()
    {
        ArrayList<String> reservation=new ArrayList<>();
        for(int i=0;i<reservations.size();i++)
        {
            if(reservations.elementAt(i).getPayment()==null)
                reservation.add(reservations.elementAt(i).getReservation_ID());
            System.out.println(reservations.elementAt(i).getReservation_ID());
        }
        return reservation;

    }


    ArrayList<Reservation> getReservationsforPayments()
    {
        ArrayList<Reservation> reservation=new ArrayList<>();
        for(int i=0;i<reservations.size();i++)
        {
            if(reservations.elementAt(i).getPayment()==null)
                reservation.add(reservations.elementAt(i));
            System.out.println(reservations.elementAt(i).getReservation_ID());
        }
        return reservation;

    }

    public boolean addCheckout(String res_id,String date, String comments)
    {
        int j=0;
        for(int i=0;i<reservations.size();i++)
        {
            if(reservations.elementAt(i).getReservation_ID().equals(res_id))
            {
                if(reservations.elementAt(i).getPayment()!=null)
                {
                    System.out.println("Comparing "+reservations.elementAt(i).getReservation_ID()+" with "+res_id+"  "+j+" times");
                    checkouts.add(new Check_out(date, comments, reservations.elementAt(i)));
                    System.out.println("Called "+j+" times");
                    j++;
                    System.out.println("Size of Checkin :"+checkins.size());
                    reservations.elementAt(i).setCheck_out(checkouts.elementAt(checkouts.size()-1));
                    return true;
                }

            }

        }
        return false;
    }



    boolean customer_exists(String cnic)
    {

        for(int i=0;i<customers.size();i++)
        {
            if(customers.elementAt(i).getCNIC().equals(cnic))
                return true;
        }
        return false;
    }


    public void deleteReservation(int index) {

        reservations.remove(index);
    }
}
