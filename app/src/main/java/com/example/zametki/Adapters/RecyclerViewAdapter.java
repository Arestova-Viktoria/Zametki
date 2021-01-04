package com.example.zametki.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zametki.Models.Note;
import com.example.zametki.R;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<Note> notes;
    private Context context;

    public RecyclerViewAdapter(Context context, LayoutInflater inflater,
                                  List<Note> accounts) {
        this.context = context;
        this.inflater = inflater;
        this.notes = accounts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycler_view, parent, false);
        return new RecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerViewAdapter.ViewHolder holder, final int position) {
        holder.date.setText(notes.get(position).date);
        holder.title.setText(notes.get(position).title);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView title;
        final TextView date;

        ViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.textView_n);
            date = view.findViewById(R.id.textView_d);
        }
    }
}