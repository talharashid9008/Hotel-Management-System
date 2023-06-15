package Utility;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hotelmanagementsystem.R;

import java.util.List;

import business_Logic.Payment;
import business_Logic.Reservation;

public class MyAdapterPayment extends BaseAdapter {
    Context applicationContext;
    List<Payment> paymentList;
    public MyAdapterPayment(Context applicationContext, List<Payment> paymentList) {
        this.applicationContext=applicationContext;
        this.paymentList=paymentList;
    }

    @Override
    public int getCount() {
        return paymentList.size();
    }

    @Override
    public Object getItem(int i) {
        return paymentList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    private class MyViewHolder{
        TextView res_id;
        TextView date;
        TextView amount;
        TextView comments;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MyViewHolder myViewHolder=null;
        if(view==null)
        {
            LayoutInflater layoutInflater=(LayoutInflater)applicationContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=layoutInflater.inflate(R.layout.row_data_reservation,null);
            myViewHolder=new MyViewHolder();

            myViewHolder.res_id=view.findViewById(R.id.customer_cnic_in_view_res);
            myViewHolder.date=view.findViewById(R.id.date_from_in_view_res);
            myViewHolder.comments=view.findViewById(R.id.date_to_in_view_res);

            myViewHolder.amount=view.findViewById(R.id.status_in_view_res);

            Payment obj=paymentList.get(i);
            myViewHolder.res_id.setText(obj.getReservation_id());
            myViewHolder.date.setText(obj.getDate());
            myViewHolder.amount.setText(Double.toString(obj.getAmount()));
            myViewHolder.comments.setText(obj.getComments());
            view.setTag(myViewHolder);
        }
        else{
            myViewHolder=(MyAdapterPayment.MyViewHolder)view.getTag();
        }


        return view;
    }
}
