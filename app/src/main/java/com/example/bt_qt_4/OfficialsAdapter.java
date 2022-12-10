package com.example.bt_qt_4;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OfficialsAdapter extends RecyclerView.Adapter<OfficialsAdapter.OfficialsViewHolder>{
    private List<Officials> officialsList;
    private Context context;

    public OfficialsAdapter(Context context, List<Officials> officialsList) {
        this.officialsList = officialsList;
        this.context = context;
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

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                Intent intent = new Intent(context, OfficialActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (officialsList != null){
            return officialsList.size();
        }
        return 0;
    }

    static class OfficialsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvPosition;
        private TextView tvName;
        private TextView tvParty;

        private ItemClickListener itemClickListener;

        public OfficialsViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            tvPosition = itemView.findViewById(R.id.tv_position);
            tvName = itemView.findViewById(R.id.tv_name);
            tvParty = itemView.findViewById(R.id.tv_party);
        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {
            itemClickListener.onClick(view, getAdapterPosition(),false);
        }
    }

    public interface ItemClickListener {
        void onClick(View view, int position,boolean isLongClick);
    }

}
