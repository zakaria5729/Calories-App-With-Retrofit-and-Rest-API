package com.hossain.zakaria.caloriesappwithretrofit.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hossain.zakaria.caloriesappwithretrofit.R;
import com.hossain.zakaria.caloriesappwithretrofit.models.Calorie;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private List<Calorie> calorieList;
    private Context context;
    private OnItemMyClickListener myClickListener;

    public interface OnItemMyClickListener {
        void onItemMyClick(int position, View view);
    }

    public void setOnItemMyClickListener(OnItemMyClickListener listener) {
        myClickListener = listener;
    }

    public RecyclerViewAdapter(List<Calorie> calorieList, Context context) {
        this.calorieList = calorieList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        myViewHolder.name.setText(calorieList.get(position).getName());
        myViewHolder.calorie.setText(String.valueOf(calorieList.get(position).getCalorie()));

        Glide.with(context)
                .load(calorieList.get(position).getImagePath())
                .into(myViewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return calorieList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name, calorie;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            name = itemView.findViewById(R.id.nameTv);
            calorie = itemView.findViewById(R.id.calorieTv);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (myClickListener != null) {
                        int position = getAdapterPosition();

                        if (position != RecyclerView.NO_POSITION) {
                            myClickListener.onItemMyClick(position, imageView);
                        }
                    }
                }
            });
        }
    }
}
