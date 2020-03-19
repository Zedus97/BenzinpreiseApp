package com.example.benzinpreise;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    LayoutInflater infalter;
    List<Station> tankstellenListAdapter;

    public Adapter(Context context, List<Station> tankstellenListAdapter){
        this.infalter = LayoutInflater.from(context);
        this.tankstellenListAdapter = tankstellenListAdapter;
    }

    public void clearList() {
        int size = this.tankstellenListAdapter.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                tankstellenListAdapter.remove(0);
            }

            this.notifyItemRangeRemoved(0, size);
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = infalter.inflate(R.layout.list_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tankstellenName.setText(tankstellenListAdapter.get(position).getName());
        holder.tanktsellenDesc.setText(tankstellenListAdapter.get(position).getBrand());
        holder.dieselPreisNumber.setText(tankstellenListAdapter.get(position).getDiesel()+" €");
        holder.e10PreisNumber.setText(tankstellenListAdapter.get(position).getE10()+" €");
        holder.superPreisNumber.setText(tankstellenListAdapter.get(position).getE5()+" €");

        boolean isExpand = tankstellenListAdapter.get(position).isExpand();
        holder.expandLayout.setVisibility(isExpand ? View.VISIBLE : View.GONE);

    }

    @Override
    public int getItemCount() {
        return tankstellenListAdapter.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tankstellenName, tanktsellenDesc;
        ConstraintLayout expandLayout;
        Button expandButton;
        TextView dieselPreisNumber, e10PreisNumber, superPreisNumber;

        public ViewHolder(View itemView) {
            super(itemView);

            tankstellenName = itemView.findViewById(R.id.stationsName);
            tanktsellenDesc = itemView.findViewById(R.id.descStations);
            expandLayout = itemView.findViewById(R.id.expandLayout);
            expandButton = itemView.findViewById(R.id.expandButton);
            dieselPreisNumber = itemView.findViewById(R.id.dieselPreisNumber);
            e10PreisNumber = itemView.findViewById(R.id.e10PreisNumber);
            superPreisNumber = itemView.findViewById(R.id.superPreisNumber);

            expandButton.setOnClickListener(new View.OnClickListener(){
                public void onClick(View view){
                    Station station = tankstellenListAdapter.get(getAdapterPosition());
                    station.setExpand(!station.isExpand());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }
}


