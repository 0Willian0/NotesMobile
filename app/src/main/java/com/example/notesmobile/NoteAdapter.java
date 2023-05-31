package com.example.notesmobile;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.PrintStream;
import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteViewHolder> implements View.OnClickListener{

    protected Context context;
    protected ArrayList<Notes> itens;
    NoteViewHolder holder;
    Notes notes;
    public NoteAdapter(Context context, ArrayList<Notes> itens)
    {
        this.context = context;
        this.itens = itens;

    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int typeView)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.item_view, parent, false);
        NoteViewHolder viewHolder = new NoteViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder noteViewHolder, int position)
    {
        noteViewHolder.id.setText(Integer.toString(itens.get(position).getId()));
        noteViewHolder.title.setText(itens.get(position).getTitle());
        noteViewHolder.description.setText(itens.get(position).getDescription());

        noteViewHolder.delete.setOnClickListener(new View.OnClickListener() {
            DB db = new DB(context);
            @Override
            public void onClick(View v) {
                System.out.println(itens.get(noteViewHolder.getAdapterPosition()).getId());
                int deleteId = itens.get(noteViewHolder.getAdapterPosition()).getId();
               long delete = db.deleteTask(deleteId);
                if(delete != -1)
                {
                    Toast.makeText(context, "Deleted Note", Toast.LENGTH_LONG).show();
                    itens.remove(position);
                    notifyDataSetChanged();
                }
            }
        });

    }

    public int getItemCount()
    {
        return itens.size();
    }

    @Override
    public void onClick(View v)
    {
        //db.deleteTask(adapter.itens.get(getAdapterPosition()).getId());

        System.out.println(holder.getAdapterPosition());
    }
}
