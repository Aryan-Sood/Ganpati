package com.abhijeet.ganpatiapp.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.abhijeet.ganpatiapp.R;
import com.abhijeet.ganpatiapp.activities.Aarti_view;
import com.abhijeet.ganpatiapp.modelclass.Aarti_List_Model_Class;
import com.google.android.material.divider.MaterialDivider;

import java.util.List;

public class Aarti_List_Adapter extends RecyclerView.Adapter<Aarti_List_Adapter.ViewHolder> {

    private List<Aarti_List_Model_Class> list;

    public Aarti_List_Adapter(List<Aarti_List_Model_Class> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public Aarti_List_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.aarti_list_item_design, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Aarti_List_Adapter.ViewHolder holder, int position) {

        String name = list.get(position).getName();

        holder.setData(name);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Aarti_view.class);
                intent.putExtra("name", name);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView nameView;
        private MaterialDivider divider;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nameView = itemView.findViewById(R.id.textView21);
            divider = itemView.findViewById(R.id.materialDivider);
        }

        public void setData(String name) {
            nameView.setText(name);
        }
    }
}
