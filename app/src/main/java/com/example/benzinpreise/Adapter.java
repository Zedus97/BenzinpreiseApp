package com.example.benzinpreise;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    LayoutInflater infalter;
    List<Station> tankstellenListAdapter;

    public Adapter(Context context, List<Station> tankstellenListAdapter){
        this.infalter = LayoutInflater.from(context);
        this.tankstellenListAdapter = tankstellenListAdapter;
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
    }

    @Override
    public int getItemCount() {
        return tankstellenListAdapter.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tankstellenName, tanktsellenDesc;

        public ViewHolder(View itemView) {
            super(itemView);

            tankstellenName = itemView.findViewById(R.id.stationsName);
            tanktsellenDesc = itemView.findViewById(R.id.descStations);
        }
    }
}


