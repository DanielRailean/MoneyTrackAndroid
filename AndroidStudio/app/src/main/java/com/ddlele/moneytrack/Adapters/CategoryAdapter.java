package com.ddlele.moneytrack.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ddlele.moneytrack.R;
import com.ddlele.moneytrack.Wrappers.Category;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private List<Category> categories;
    final private OnListItemClickListener mOnListItemClickListener;

    public CategoryAdapter(List<Category> categories, OnListItemClickListener mOnListItemClickListener) {
        this.categories = categories;
        this.mOnListItemClickListener = mOnListItemClickListener;
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }


    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.category, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {
        if(categories.get(position).name.length()>16){
            holder.name.setText(categories.get(position).name.substring(0,15));
        }else
            holder.name.setText(categories.get(position).name);
        holder.spent.setText(categories.get(position).currentSpent+"");
        holder.currency.setText(categories.get(position).currency.name);
    }

    public interface OnListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView name;
        TextView spent;
        TextView remains;
        TextView currency;

        ViewHolder(View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.category_item_name);
            spent = itemView.findViewById(R.id.category_item_spent);
            remains = itemView.findViewById(R.id.category_item_remains);
            currency = itemView.findViewById(R.id.category_item_currency);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mOnListItemClickListener.onListItemClick(getAdapterPosition());
        }
    }
}
