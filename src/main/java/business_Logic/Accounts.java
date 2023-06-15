package business_Logic;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Vector;


public class Accounts implements Serializable {
    double Total_Amount_Due;
    double Total_Amount_Collected;
    String collected;
    String due;
    Vector<Payment>payments=new Vector<>(1);


    Payment makePayment(double amount, String date, String comments, String reservation_id)
    {
        Total_Amount_Collected+=amount;
        Total_Amount_Due-=amount;
        payments.add(new Payment(amount, date, comments, reservation_id));

        return payments.elementAt(payments.size()-1);
    }
    ArrayList<Payment> getPayments()
    {
        ArrayList<Payment> payment=new ArrayList<>();
        for(int i=0;i<payments.size();i++)
        {
            payment.add(payments.elementAt(i));
        }
        return payment;
    }
    public String getTotal_Amount_Due() {
        DecimalFormat format=new DecimalFormat("000,000,000,000.00");
        collected=format.format(Total_Amount_Collected); //Double.toString(Total_Amount_Collected);
        due=format.format(Total_Amount_Due); //Double.toString(Total_Amount_Collected);


        StringBuilder sb2=new StringBuilder(due);
        boolean zero=true;
        int ind=0;

        while(zero)
        {
            if(due.charAt(ind)=='0' ||due.charAt(ind)==',')
            {
                sb2.deleteCharAt(0);
                ind++;
            }
            else zero=false;

        }

        due=sb2.toString();

        return due;
    }
    public void setTotal_Amount_Due(double total_Amount_Due) {
        Total_Amount_Due = total_Amount_Due;

        due=Double.toString(Total_Amount_Due);
    }

    public void addTotal_Amount_Due(double total_Amount_Due) {
        Total_Amount_Due+= total_Amount_Due;
        due=Double.toString(Total_Amount_Due);
    }

    public String getTotal_Amount_Collected() {

        DecimalFormat format=new DecimalFormat("000,000,000,000.00");
        collected=format.format(Total_Amount_Collected); //Double.toString(Total_Amount_Collected);
        due=format.format(Total_Amount_Due); //Double.toString(Total_Amount_Collected);

        StringBuilder sb=new StringBuilder(collected);

        boolean zero=true;
        int ind=0;
        while(zero)
        {
            if(collected.charAt(ind)=='0' ||collected.charAt(ind)==',')
            {
                sb.deleteCharAt(0);
                ind++;
            }
            else zero=false;

        }

        collected=sb.toString();



        return collected;
    }
    public void setTotal_Amount_Collected(double total_Amount_Collected) {
        Total_Amount_Collected = total_Amount_Collected;
    }

    public Payment updatePayment(int index, double am, String date, String comments, String reservation_id) {
        Payment obj=payments.get(index);
        obj.setAmount(am);
        obj.setDate(date);
        obj.setComments(comments);
        obj.setReservation_id(reservation_id);

        return obj;
    }

    public Payment getPayment(int index) {
        return payments.get(index);
    }

    public void deletePayment(int index) {
        payments.remove(index);
    }
}
