package com.example.signlanguagetranslator;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;


public class DocumentAdapter extends RecyclerView.Adapter<DocumentAdapter.MyDocHolder> {

    private List<DocumentModel>documents;
    Context context;


    @Override
    public MyDocHolder onCreateViewHolder( ViewGroup parent, int viewType) {

       View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.dictionary_item, parent, false);
        return new MyDocHolder(itemView);

    }

    @Override
    public void onBindViewHolder( DocumentAdapter.MyDocHolder holder, int position) {
        DocumentModel document = documents.get(position);
        holder.description.setText(document.getDescription());
        holder.tag.setText(document.getTag());


        holder.itemView.setOnClickListener(v ->{

            Intent in=new Intent(context, VideoDisplay.class);


            in.putExtra("video_id",document.getVideo_id().toString());

            context.startActivity(in);
        });
    }
    @Override
    public int getItemCount() {

        return documents.size();
    }
    public class  MyDocHolder extends RecyclerView.ViewHolder{
        public TextView description,tag;
        public MyDocHolder(View view){
            super(view);
            description=(TextView)view.findViewById(R.id.description);
            tag=(TextView)view.findViewById(R.id.tag);
        }
    }

    public DocumentAdapter(List<DocumentModel> documents, Context con){
        this.documents=documents;
        this.context=con;
    }

    public void filter(String text,List <DocumentModel> doc) {
        documents=new ArrayList<>();
        //documents.clear();
        if(text.isEmpty()){
            documents.addAll(doc);
        } else{
            text = text.toLowerCase();
            for(DocumentModel item: doc){
                if(item.getDescription().toLowerCase().contains(text) || item.getTag().toLowerCase().contains(text)){
                    documents.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }
}
