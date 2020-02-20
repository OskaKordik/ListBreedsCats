package com.olgazaloznaya.listbreedscats.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.olgazaloznaya.listbreedscats.R;
import com.olgazaloznaya.listbreedscats.api.responses.BreedsResponseList;

import java.util.List;

public class BreedsListAdapter extends RecyclerView.Adapter<BreedsListAdapter.BreedsViewHolder> {
    private List<BreedsResponseList> breedsList;
    private OnBreedsClickListener onBreedsClickListener;

    public BreedsListAdapter(OnBreedsClickListener onBreedsClickListener) {
        this.onBreedsClickListener = onBreedsClickListener;
    }

    public void setBreedsList(List<BreedsResponseList> breedsList) {
        this.breedsList = breedsList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BreedsListAdapter.BreedsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.breeds_list_item, parent,false);
        return new BreedsViewHolder(view, onBreedsClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull BreedsListAdapter.BreedsViewHolder holder, int position) {
        holder.title.setText(breedsList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        if (breedsList != null) {
            return breedsList.size();
        } else return 0;
    }

    class BreedsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView title;
        private OnBreedsClickListener onBreedsClickListener;

        public BreedsViewHolder(@NonNull final View itemView, OnBreedsClickListener onBreedsClickListener) {
            super(itemView);
            this.onBreedsClickListener = onBreedsClickListener;
            title = itemView.findViewById(R.id.breed_title);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onBreedsClickListener.onBreedsClick(getAdapterPosition());
        }
    }
}
