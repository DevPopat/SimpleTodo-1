package com.codepath.basictodo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

//items adapter extends from RecyclerView.Adapter as all recyclerviews require an adapter
public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

    public interface OnLongClicklistener{
        void onItemLongClicked(int position);
    }
    //initializing the "list" of items
    List<String> items;
    OnLongClicklistener longClicklistener;


    public ItemsAdapter(List<String> items, OnLongClicklistener longClicklistener) {
        this.items=items;
        this.longClicklistener=longClicklistener;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //use layout inflator to inflate a view

        //(5) STRETCH GOAL: We have used simple_list_item_1.xml as our layout for the ViewHolder (i.e, each row).
        // Can you make your own custom layout and use that instead? Just switch the layout name with the one below
        // You can create a new layout file under res/layout. Do something creative!

        View todoView=  LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1,parent,false);
        //wrap it isnide a viewHolder and return it

        return new ViewHolder(todoView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    //(3) We need to grab the item at 'position'. Create a variable String item and grab the item at the given position.
        String item=items.get(position);

        //bind the item into the specified viewholder
        holder.bind(item);

    }
    // tells how many items are in the list
    @Override
    public int getItemCount() {
        // (4) This line must return the size/length of our items list. Please change the code to do so.
        return 1;
    }
    // container to provide easy access to view that represent each row of the list
    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem= itemView.findViewById(android.R.id.text1);

        }
        //update the view inside of the view holder with this data
        public void bind(String item) {
            tvItem.setText(item);
            tvItem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    //remove the item from the recyclerview
                    longClicklistener.onItemLongClicked(getAdapterPosition());

                    return true;
                }
            });
        }
    }
}
