package com.example.bt_qt_4;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OfficialsAdapter extends RecyclerView.Adapter<OfficialsAdapter.OfficialsViewHolder>{
    private List<Officials> officialsList;

    public OfficialsAdapter(List<Officials> officialsList) {
        this.officialsList = officialsList;
    }

    @NonNull
    @Override
    public OfficialsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_officials,parent,false);
        return new OfficialsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OfficialsAdapter.OfficialsViewHolder holder, int position) {
        Officials officials = officialsList.get(position);

        if (officials == null){
            return;
        }
        holder.tvPosition.setText(officials.getPosition());
        holder.tvName.setText(officials.getName());
        holder.tvParty.setText(officials.getParty());
    }

    @Override
    public int getItemCount() {
        if (officialsList != null){
            return officialsList.size();
        }
        return 0;
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
}
