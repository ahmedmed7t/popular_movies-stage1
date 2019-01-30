package com.example.crazynet.popularmoviesstage1;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.crazynet.popularmoviesstage1.model.reviewItem;

import java.util.ArrayList;

/**
 * Created by CrazyNet on 30/01/2019.
 */

public class reviewRecyclerAdapter extends RecyclerView.Adapter<reviewRecyclerAdapter.viewholder> {
    ArrayList<reviewItem> arrayList;

    public class viewholder extends RecyclerView.ViewHolder {

        TextView author , content;

        public viewholder(View itemView) {
            super(itemView);
            author = itemView.findViewById(R.id.author);
            content = itemView.findViewById(R.id.content);
        }
    }

    public reviewRecyclerAdapter(ArrayList<reviewItem> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_item,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        holder.author.setText(arrayList.get(position).getAuthor());
        holder.content.setText(arrayList.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


}
