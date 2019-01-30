package com.example.crazynet.popularmoviesstage1;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.crazynet.popularmoviesstage1.model.videoItem;

import java.util.ArrayList;

/**
 * Created by CrazyNet on 30/01/2019.
 */

public class movieRecyclerAdapter extends RecyclerView.Adapter<movieRecyclerAdapter.viewholder> {

    ArrayList<videoItem> arrayList ;

    private final movieRecyclerAdapter.ListItemClickListener mOnClickListener;

    public movieRecyclerAdapter (ArrayList<videoItem> arrayList, movieRecyclerAdapter.ListItemClickListener mOnClickListener) {
        this.arrayList = arrayList;
        this.mOnClickListener = mOnClickListener;
    }

    public interface ListItemClickListener{
        public void onListItemClick(int index);
    }

    public class viewholder extends RecyclerView.ViewHolder{
            ImageView img ;
            TextView trailer ;
        public viewholder(View itemView ,  final movieRecyclerAdapter.ListItemClickListener listener) {
            super(itemView);
            img = itemView.findViewById(R.id.play);
            trailer = itemView.findViewById(R.id.trailer);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null)
                    {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION)
                        {
                            listener.onListItemClick(position);
                        }
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trailer_item,parent,false);
        return new movieRecyclerAdapter.viewholder(view ,mOnClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
         int index  = position +1;
         holder.trailer.setText("Trailer "+ index);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


}
