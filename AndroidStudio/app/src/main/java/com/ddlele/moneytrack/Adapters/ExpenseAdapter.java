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

    public ExpenseAdapter(List<Expense> expenses) {
        this.expenses = expenses;
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
        holder.name.setText(expenses.get(position).name);
        holder.amount.setText(expenses.get(position).amount+"");
        holder.currency.setText(expenses.get(position).currency.name);
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView amount;
        TextView currency;

        ViewHolder(View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.tv_name);
            amount = itemView.findViewById(R.id.tv_amount);
            currency = itemView.findViewById(R.id.tv_currency);
        }
    }
}
