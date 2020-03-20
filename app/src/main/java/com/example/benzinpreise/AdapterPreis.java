package com.example.benzinpreise;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterPreis extends RecyclerView.Adapter<AdapterPreis.ViewHolder> {

    LayoutInflater infalter;
    List<StationPreis> tankstellenListAdapterPreis;

    public AdapterPreis(Context context, List<StationPreis> tankstellenListAdapterPreis) {
        this.infalter = LayoutInflater.from(context);
        this.tankstellenListAdapterPreis = tankstellenListAdapterPreis;
    }

    public void clearList() {
        int size = this.tankstellenListAdapterPreis.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                tankstellenListAdapterPreis.remove(0);
            }

            this.notifyItemRangeRemoved(0, size);
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = infalter.inflate(R.layout.list_row_prices, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tankstellenName.setText(tankstellenListAdapterPreis.get(position).getName());
        holder.tanktsellenDesc.setText(tankstellenListAdapterPreis.get(position).getBrand());
        holder.preis.setText(tankstellenListAdapterPreis.get(position).getPrice() + " €");

        boolean isExpand = tankstellenListAdapterPreis.get(position).isExpand();
        holder.expandLayout.setVisibility(isExpand ? View.VISIBLE : View.GONE);

    }

    @Override
    public int getItemCount() {
        return tankstellenListAdapterPreis.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tankstellenName, tanktsellenDesc;
        ConstraintLayout expandLayout;
        Button expandButton;
        TextView preis;

        public ViewHolder(View itemView) {
            super(itemView);

            tankstellenName = itemView.findViewById(R.id.stationsName_price);
            tanktsellenDesc = itemView.findViewById(R.id.descStationsPrice);
            expandLayout = itemView.findViewById(R.id.expandLayoutPrice);
            expandButton = itemView.findViewById(R.id.expandButtonPrice);
            preis = itemView.findViewById(R.id.preisDisplay);

            expandButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    StationPreis stationPreis = tankstellenListAdapterPreis.get(getAdapterPosition());
                    stationPreis.setExpand(!stationPreis.isExpand());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }
}


