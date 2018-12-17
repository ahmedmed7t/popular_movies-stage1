package com.example.crazynet.popularmoviesstage1;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Medhat on 14/12/2018.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.viewholder> {

    ArrayList<String> arrayList ;
    private final ListItemClickListener mOnClickListener;

    public RecyclerAdapter(ArrayList<String> arrayList, ListItemClickListener mOnClickListener) {
        this.arrayList = arrayList;
        this.mOnClickListener = mOnClickListener;
    }

    public interface ListItemClickListener{
        public void onListItemClick(int index);
    }

    class viewholder extends RecyclerView.ViewHolder{

        ImageView img ;

        public viewholder(View itemView ,  final ListItemClickListener listener) {
            super(itemView);

            img = itemView.findViewById(R.id.img);
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
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new viewholder(view ,mOnClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        String img = arrayList.get(position);
        Picasso.with(holder.itemView.getContext()).load("http://image.tmdb.org/t/p/w780"+img).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
