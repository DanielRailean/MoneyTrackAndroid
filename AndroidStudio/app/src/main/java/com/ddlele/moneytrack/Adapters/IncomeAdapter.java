package com.ddlele.moneytrack.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ddlele.moneytrack.R;
import com.ddlele.moneytrack.Wrappers.Income;

import java.util.List;

public class IncomeAdapter extends RecyclerView.Adapter<IncomeAdapter.ViewHolder> {

    private List<Income> incomes;
    final private OnListItemClickListener mOnListItemClickListener;

    public IncomeAdapter(List<Income> incomes, OnListItemClickListener mOnListItemClickListener) {
        this.incomes = incomes;
        this.mOnListItemClickListener = mOnListItemClickListener;
    }

    @Override
    public int getItemCount() {
        return incomes.size();
    }


    @NonNull
    @Override
    public IncomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.income, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull IncomeAdapter.ViewHolder holder, int position) {
        if(incomes.get(position).name.length()>16){
            holder.name.setText(incomes.get(position).name.substring(0,15));
        }else
            holder.name.setText(incomes.get(position).name);
        holder.amount.setText(incomes.get(position).amount+"");
        holder.currency.setText(incomes.get(position).currency.name);
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
            name = itemView.findViewById(R.id.i_item_name);
            amount = itemView.findViewById(R.id.i_item_amount);
            currency = itemView.findViewById(R.id.i_item_currency);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mOnListItemClickListener.onListItemClick(getAdapterPosition());
        }
    }
}
