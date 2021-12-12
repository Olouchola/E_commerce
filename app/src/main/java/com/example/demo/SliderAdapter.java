package com.example.demo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.Holder> {

      ArrayList<Publicite> images;
    public SliderAdapter(ArrayList<Publicite> images) {
        this.images = images;
    }

    @Override
    public SliderAdapter.Holder onCreateViewHolder(ViewGroup parent) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.slide_items,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(SliderAdapter.Holder viewHolder, int position) {
           viewHolder.getImageView().setImageResource(images.get(position).getImages());
    }

    @Override
    public int getCount() {
        return images.size();
    }

    public class Holder extends SliderViewAdapter.ViewHolder {
        private ImageView imageView;
        public Holder(View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.image_view);
        }

        public ImageView getImageView() {
            return imageView;
        }
    }
}
