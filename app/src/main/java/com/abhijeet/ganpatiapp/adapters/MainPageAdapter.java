package com.abhijeet.ganpatiapp.adapters;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.abhijeet.ganpatiapp.R;
import com.abhijeet.ganpatiapp.activities.Aarti_list;
import com.abhijeet.ganpatiapp.activities.Kundali_entry;
import com.abhijeet.ganpatiapp.activities.Puja_list;
import com.abhijeet.ganpatiapp.modelclass.MainPageModelClass;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class MainPageAdapter extends RecyclerView.Adapter<MainPageAdapter.ViewHolder> {

    List<MainPageModelClass> list;

    public MainPageAdapter(List<MainPageModelClass> list) {
        this.list = list;
    }


    @NonNull
    @Override
    public MainPageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_page_item_design,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainPageAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        int color = list.get(position).getColor();

        holder.setData(color);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position==0){
                    Intent intent = new Intent(v.getContext(), Aarti_list.class);
                    v.getContext().startActivity(intent);
                }
                else if (position==1){
                    Toast.makeText(v.getContext(), "Kundali", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(v.getContext(), Kundali_entry.class);
                    v.getContext().startActivity(intent);
                }
                else if (position==2){
                    Toast.makeText(v.getContext(), "Puja List", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(v.getContext(), Puja_list.class);
                    v.getContext().startActivity(intent);
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        MaterialCardView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
        }

        public void setData(int color){
            imageView.setCardBackgroundColor(color);
        }
    }


}
