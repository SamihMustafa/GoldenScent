package com.media.goldenscent;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by Samih on 17-Jun-18.
 */
class CarouselAdapter extends RecyclerView.Adapter<CarouselAdapter.ViewHolder> {


    private static final int ITEMS_PER_PAGE = 3;
    private final Context context;

    public CarouselAdapter(Context context) {
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.carousel_list_item, null, false);

        int itemWidth = parent.getWidth() / ITEMS_PER_PAGE;
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(itemWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
        v.setLayoutParams(lp);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       Glide.with(context).load("https://picsum.photos/200/300/?image=" + position).into((ImageView) holder.image);
    }

    @Override
    public int getItemCount() {
        return 7;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View image;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.carouselImageView);
        }
    }
}
