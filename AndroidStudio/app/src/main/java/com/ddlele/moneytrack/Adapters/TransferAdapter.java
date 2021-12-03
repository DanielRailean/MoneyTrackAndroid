package com.ddlele.moneytrack.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ddlele.moneytrack.R;
import com.ddlele.moneytrack.Wrappers.Transfer;

import java.util.List;

public class TransferAdapter extends RecyclerView.Adapter<TransferAdapter.ViewHolder> {

    private List<Transfer> transfers;
    final private OnListItemClickListener mOnListItemClickListener;

    public TransferAdapter(List<Transfer> transfers, OnListItemClickListener mOnListItemClickListener) {
        this.transfers = transfers;
        this.mOnListItemClickListener = mOnListItemClickListener;
    }

    @Override
    public int getItemCount() {
        return transfers.size();
    }


    @NonNull
    @Override
    public TransferAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.transfer, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull TransferAdapter.ViewHolder holder, int position) {
        if(transfers.get(position).description.length()>16){
            holder.name.setText(transfers.get(position).description.substring(0,15));
        }else
            holder.name.setText(transfers.get(position).description);
        holder.amount.setText(transfers.get(position).amount+"");
        holder.currency.setText(transfers.get(position).currency.name);
    }

    public interface OnListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView name;
        TextView amount;
        TextView currency;

        ViewHolder(View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.t_item_name);
            amount = itemView.findViewById(R.id.t_item_amount);
            currency = itemView.findViewById(R.id.t_item_currency);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mOnListItemClickListener.onListItemClick(getAdapterPosition());
        }
    }
}
