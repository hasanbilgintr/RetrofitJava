package com.hasanbilgin.retrofitjava.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hasanbilgin.retrofitjava.R;
import com.hasanbilgin.retrofitjava.model.CryptoModel;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RowHolder> {

    public RecyclerViewAdapter(ArrayList<CryptoModel> arrayList) {
        this.arrayList = arrayList;
    }

    private ArrayList<CryptoModel> arrayList;
    private String[] colors = {"#F4D03F", "#AED6F1", "#E59866", "#CCD1D1", "#4A235A", "#2ECC71"};

    @NonNull
    @Override
    public RecyclerViewAdapter.RowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row, parent, false);

        return new RowHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.RowHolder holder, int position) {
        holder.bind(arrayList.get(position), colors, position);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class RowHolder extends RecyclerView.ViewHolder {

        TextView currencyTextView, priceTextView;

        public RowHolder(@NonNull View itemView) {

            super(itemView);
        }

        public void bind(CryptoModel cryptoModel, String[] colors, int position) {
            itemView.setBackgroundColor(Color.parseColor(colors[position % 6]));
            currencyTextView = itemView.findViewById(R.id.currencyTextView);
            priceTextView = itemView.findViewById(R.id.priceTextView);
            currencyTextView.setText(cryptoModel.paraBirimi);
            priceTextView.setText(cryptoModel.para);

        }
    }
}
