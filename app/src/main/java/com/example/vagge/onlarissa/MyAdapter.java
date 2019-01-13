package com.example.vagge.onlarissa;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter <MyAdapter.ViewHolder> {

    private List<ListItem> listItems;
    private Context context;

    public MyAdapter(List<ListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from ( parent.getContext() )
                .inflate ( R.layout.list_item, parent, false );
        return new ViewHolder (v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        final ListItem listItem = listItems.get ( position );

        if(listItem.getTitle ().matches ( "null" )){
            viewHolder.textViewTitle.setVisibility ( View.GONE );
        }else {
            viewHolder.textViewTitle.setText ( listItem.getTitle ( ) );
        }

        if(listItem.getDesc ().matches ( "null" )){
            viewHolder.textViewDesc.setVisibility ( View.GONE );
        }else {
            viewHolder.textViewDesc.setText ( listItem.getDesc ( ) );
        }

        if(listItem.getDate ().matches ( "null" )){
            viewHolder.textViewDate.setVisibility ( View.GONE );
        }else {
            viewHolder.textViewDate.setText ( listItem.getDate ( ) );
        }

        if(listItem.getImg().matches ( "null" )){
            Picasso.with(context).load("http://thaigifts.or.th/wp-content/uploads/2017/03/no-image.jpg").into ( viewHolder.ImageUrl );
        }else {
            Picasso.with(context).load(listItem.getImg()).into ( viewHolder.ImageUrl );
        }


        viewHolder.linearlayout.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                String url = listItem.getUrl ();
                String title = listItem.getTitle ();

                Intent website = new Intent(context, WebPageView.class);
                website.putExtra("URL", url);
                website.putExtra("TITLE", title);
                context.startActivity(website);
            }
        } );

    }

    @Override
    public int getItemCount() {
        return listItems.size ();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewTitle;
        public TextView textViewDesc;
        public TextView textViewDate;
        public ImageView ImageUrl;
        public LinearLayout linearlayout;

        public ViewHolder(@NonNull View itemView) {
            super ( itemView );

            textViewTitle = (TextView) itemView.findViewById ( R.id.textViewTitle );
            textViewDesc = (TextView) itemView.findViewById ( R.id.textViewDesc );
            ImageUrl = (ImageView) itemView.findViewById ( R.id.imageurl );
            textViewDate = (TextView) itemView.findViewById ( R.id.textViewDate);
            linearlayout = (LinearLayout) itemView.findViewById ( R.id.linearlayout );
        }
    }
}
