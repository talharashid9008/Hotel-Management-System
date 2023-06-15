package Utility;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hotelmanagementsystem.R;

import java.util.List;

import business_Logic.Reservation;

public class MyAdapterReservations extends BaseAdapter {
    Context applicationContext;
    List<Reservation> reservationList;
    public MyAdapterReservations(Context applicationContext, List<Reservation> reservationList) {
        this.applicationContext=applicationContext;
        this.reservationList=reservationList;
    }

    @Override
    public int getCount() {
        return reservationList.size();
    }

    @Override
    public Object getItem(int i) {
        return reservationList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    private class MyViewHolder{
        TextView customer_cnic;
        TextView from_date;
        TextView to_date;
        TextView status;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MyViewHolder myViewHolder=null;
        if(view==null)
        {
            LayoutInflater layoutInflater=(LayoutInflater)applicationContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=layoutInflater.inflate(R.layout.row_data_reservation,null);
            myViewHolder=new MyViewHolder();

            myViewHolder.customer_cnic=view.findViewById(R.id.customer_cnic_in_view_res);
            myViewHolder.from_date=view.findViewById(R.id.date_from_in_view_res);
            myViewHolder.to_date=view.findViewById(R.id.date_to_in_view_res);
            myViewHolder.status=view.findViewById(R.id.status_in_view_res);

            Reservation obj=reservationList.get(i);
            myViewHolder.customer_cnic.setText(obj.getCustomer_CNIC());
            myViewHolder.from_date.setText(obj.getFrom_date());
            myViewHolder.to_date.setText(obj.getTo_date());
            if(obj.getCheck_In()==null)
                myViewHolder.status.setText("Confirmed");
            else if(obj.getCheck_out()==null)
                myViewHolder.status.setText("Checked In");
            else if(obj.getPayment()==null)
                myViewHolder.status.setText("Not Payed");
            else myViewHolder.status.setText("Checked Out");
            view.setTag(myViewHolder);
        }
        else{
            myViewHolder=(MyViewHolder)view.getTag();
        }


        return view;
    }
}
