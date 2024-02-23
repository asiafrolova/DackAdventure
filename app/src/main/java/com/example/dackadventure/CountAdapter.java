package com.example.dackadventure;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dackadventure.databinding.ItemScoreBinding;

import java.util.ArrayList;
import java.util.List;

public class CountAdapter extends RecyclerView.Adapter<CountAdapter.CountViewHolder>{


    private List<Count> counts=new ArrayList<>();
    public void setCountList(List<Count> counts){
        this.counts = counts;
        notifyDataSetChanged();
    }
    static class CountViewHolder extends RecyclerView.ViewHolder {

        ItemScoreBinding binding;

        public CountViewHolder(ItemScoreBinding b) {
            super(b.getRoot());
            binding = b;
        }
    }


    @NonNull
    @Override
    public CountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_score
                ,parent
                ,false);
        return new CountViewHolder(ItemScoreBinding.bind(view));
    }

    @Override
    public void onBindViewHolder(@NonNull CountViewHolder holder, int position) {
        Count count=counts.get(position);
        holder.binding.score.setText(String.valueOf(count.getCount()));
        holder.binding.time.setText(String.valueOf(count.getTime()));
        holder.binding.data.setText(String.valueOf(count.getDate()));
        if (count.getCount()<3){
            holder.binding.imageView.setImageResource(R.drawable.sad);

        }else if (count.getCount()>=3 && count.getCount()<5){
            holder.binding.imageView.setImageResource(R.drawable.good);

        } else if (count.getCount()>=5) {
            holder.binding.imageView.setImageResource(R.drawable.cool);
    }




}

    @Override
    public int getItemCount() {
        return counts.size();
    }
}
