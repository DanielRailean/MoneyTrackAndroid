package com.ddlele.moneytrack.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ddlele.moneytrack.R;
import com.ddlele.moneytrack.Wrappers.Account;

import java.util.List;

public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.ViewHolder> {

    private List<Account> accounts;
    final private OnListItemClickListener mOnListItemClickListener;

    public AccountAdapter(List<Account> accounts, OnListItemClickListener mOnListItemClickListener) {
        this.accounts = accounts;
        this.mOnListItemClickListener = mOnListItemClickListener;
    }

    @Override
    public int getItemCount() {
        return accounts.size();
    }


    @NonNull
    @Override
    public AccountAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.account, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull AccountAdapter.ViewHolder holder, int position) {
        if(accounts.get(position).name.length()>16){
            holder.name.setText(accounts.get(position).name.substring(0,15));
        }else
            holder.name.setText(accounts.get(position).name);
        holder.balance.setText(accounts.get(position).balance+"");
        holder.currency.setText(accounts.get(position).currency.name);
    }

    public interface OnListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView name;
        TextView balance;
        TextView currency;

        ViewHolder(View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.a_item_name);
            balance = itemView.findViewById(R.id.a_item_balance);
            currency = itemView.findViewById(R.id.a_item_currency);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mOnListItemClickListener.onListItemClick(getAdapterPosition());
        }
    }
}
