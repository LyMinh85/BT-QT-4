package com.example.bt_qt_4;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OfficialsAdapter extends RecyclerView.Adapter<OfficialsAdapter.OfficialsViewHolder>{
    private List<Office> offices;
    private List<Official> officials;
    private Context context;

    public OfficialsAdapter(Context context, List<Office> offices, List<Official> officials) {
        this.offices = offices;
        this.officials = officials;
        this.context = context;
    }

    @NonNull
    @Override
    public OfficialsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_office,parent,false);
        return new OfficialsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OfficialsAdapter.OfficialsViewHolder holder, int position) {
        Office office = offices.get(position);
        Official official = officials.get(office.getOfficialIndices().get(0));

        holder.tvPosition.setText(official.getPosition());
        holder.tvName.setText(official.getName());
        holder.tvParty.setText(official.getParty());

        holder.tvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, OfficialActivity.class);
                intent.putExtra("official", official);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return offices.size();
    }

    static class OfficialsViewHolder extends RecyclerView.ViewHolder {
        private TextView tvPosition;
        private TextView tvName;
        private TextView tvParty;

        public OfficialsViewHolder(@NonNull View itemView) {
            super(itemView);

            tvPosition = itemView.findViewById(R.id.tv_position);
            tvName = itemView.findViewById(R.id.tv_name);
            tvParty = itemView.findViewById(R.id.tv_party);
        }

    }

    public interface ItemClickListener {
        void onClick(View view, int position,boolean isLongClick);
    }

}
