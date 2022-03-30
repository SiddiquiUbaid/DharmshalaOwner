package com.example.dharmshalaowner.dhaaramshalaown.Adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.dharmshalaowner.Domain.BookingOrders;
import com.example.dharmshalaowner.R;
import com.example.dharmshalaowner.dhaaramshalaown.Activities.PaymentStatusCheckActivity;

import java.util.ArrayList;

public class OrdersListAdapter extends RecyclerView.Adapter<OrdersListAdapter.ViewHolder> {

    private ArrayList<BookingOrders> localDataSet;
    private OnOrderLitener mOnOrderListener;
    Context mContext ;


    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView userName;
        TextView phone;
        TextView rooms, nights;
        TextView amount;
        OnOrderLitener onOrderLitener;
        LinearLayout orderGlimpseLinear;
        ImageView imgPaymentStatus;

        //private final TextView textViewDescription;

        public ViewHolder(View view, OnOrderLitener mOnOrderListener) {
            super(view);


            userName = (TextView) view.findViewById(R.id.userName);
            phone = (TextView) view.findViewById(R.id.phone);
            rooms = (TextView) view.findViewById(R.id.rooms);
            nights = (TextView) view.findViewById(R.id.nights);
            amount = (TextView) view.findViewById(R.id.amount);
            imgPaymentStatus = (ImageView) view.findViewById(R.id.imgPaymentSatus);

            orderGlimpseLinear = (LinearLayout) view.findViewById(R.id.orderGlimpseLinear);


        }




        public TextView getUserName() {
            return userName;
        }
        public TextView getPhone() {
            return phone;
        }
        public TextView getRooms() {
            return rooms;
        }
        public TextView getNights() {
            return nights;
        }
        public TextView getAmount() {
            return amount;
        }
        public ImageView getImgPaymentStatus() {
            return imgPaymentStatus;
        }



        @Override
        public void onClick(View v) {

        }

        public  void setPaymentStatus(String status){



        }



    }


    public OrdersListAdapter(Context mContext, ArrayList<BookingOrders> dataSet, OnOrderLitener onOrderLitener) {
        localDataSet = dataSet;
        this.mOnOrderListener = onOrderLitener;
        this.mContext = mContext;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.get_placed_orders_list_items, viewGroup, false);


            return new ViewHolder(view, mOnOrderListener);




    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        viewHolder.getUserName().setText(localDataSet.get(position).getUserName());
        viewHolder.getPhone().setText(localDataSet.get(position).getUserPhone());
        viewHolder.getRooms().setText("Rooms: "+localDataSet.get(position).getRooms());
        viewHolder.getNights().setText("Nights: "+localDataSet.get(position).getTotalNights());
        viewHolder.getAmount().setText("₹"+localDataSet.get(position).getPaymentAmount());

        //viewHolder.getAmount().setBackground(mContext.getResources().getDrawable(R.drawable.add));



        if(localDataSet.get(position).getPaymentType().equals("cancelled")){
                viewHolder.getImgPaymentStatus().setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_baseline_paid_24));
                viewHolder.getImgPaymentStatus().setColorFilter(mContext.getResources().getColor(R.color.pinkred));
        }
        else if(localDataSet.get(position).getPaymentType().equals("atHotel")){
            viewHolder.getImgPaymentStatus().setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_baseline_paid_24));
            viewHolder.getImgPaymentStatus().setColorFilter(mContext.getResources().getColor(R.color.green));

        }
        else{
            viewHolder.getImgPaymentStatus().setImageDrawable(mContext.getResources().getDrawable(R.drawable.add));

        }







        viewHolder.orderGlimpseLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnOrderListener.onOrderClick(position);

            }
        });
    }



    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }

    public interface OnOrderLitener{

        void onOrderClick(int position);
    }
}
