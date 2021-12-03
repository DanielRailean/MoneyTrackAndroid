package com.ddlele.moneytrack.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ddlele.moneytrack.R;
import com.ddlele.moneytrack.Wrappers.Expense;

import java.util.List;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ViewHolder> {

    private List<Expense> expenses;
    final private OnListItemClickListener mOnListItemClickListener;

    public ExpenseAdapter(List<Expense> expenses, OnListItemClickListener mOnListItemClickListener) {
        this.expenses = expenses;
        this.mOnListItemClickListener = mOnListItemClickListener;
    }

    @Override
    public int getItemCount() {
        return expenses.size();
    }


    @NonNull
    @Override
    public ExpenseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.expense, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseAdapter.ViewHolder holder, int position) {
        if(expenses.get(position).name.length()>16){
            holder.name.setText(expenses.get(position).name.substring(0,15));
        }else
            holder.name.setText(expenses.get(position).name);
        holder.amount.setText(expenses.get(position).amount+"");
        holder.currency.setText(expenses.get(position).currency.name);
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
            name = itemView.findViewById(R.id.e_item_name);
            amount = itemView.findViewById(R.id.e_item_amount);
            currency = itemView.findViewById(R.id.e_item_currency);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mOnListItemClickListener.onListItemClick(getAdapterPosition());
        }
    }
}
