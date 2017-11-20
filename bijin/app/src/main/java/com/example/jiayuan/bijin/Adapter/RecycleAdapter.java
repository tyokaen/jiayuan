package com.example.jiayuan.bijin.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jiayuan.bijin.R;

/**
 * Created by jiayuan on 2017/08/09.
 */
  public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyViewHolder> {
    private int[] image=null;
    public RecycleAdapter(int[] image) {
        this.image=image;
    }
    public RecycleAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.friend_list, null);
        return new RecycleAdapter(image).new MyViewHolder(v);
    }

    public void onBindViewHolder(RecycleAdapter.MyViewHolder holder, int position) {
        holder.imageView.setImageResource(image[position]);
        holder.v.setTag(position);
    }

    @Override
    public int getItemCount() {
        return image.length;
    }

    public class MyViewHolder
            extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public View v;
        public TextView t;
        public MyViewHolder(View v) {
            super(v);
            this.v=v;
            imageView = (ImageView) v.findViewById(R.id.friends_image);
            t=(TextView)v.findViewById(R.id.friends_name);
        }
    }
    interface OnRecyclerViewItemClickListener {
        void onItemClick(View view,int position );
    }
}

