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
    private RecyclerViewAdapter.OnItemListener mOnItemListener;

    public RecyclerViewAdapter(Context context, LayoutInflater inflater, List<Note> accounts,
                               RecyclerViewAdapter.OnItemListener mOnItemListener) {
        this.context = context;
        this.inflater = inflater;
        this.notes = accounts;
        this.mOnItemListener = mOnItemListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycler_view, parent, false);
        return new RecyclerViewAdapter.ViewHolder(view, mOnItemListener);
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

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        final TextView title;
        final TextView date;
        RecyclerViewAdapter.OnItemListener onItemListener;

        ViewHolder(View view, RecyclerViewAdapter.OnItemListener onItemListener) {
            super(view);
            title = view.findViewById(R.id.textView_n);
            date = view.findViewById(R.id.textView_d);
            this.onItemListener = onItemListener;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            try {
                mOnItemListener.onItemClick(getAdapterPosition());
            } catch (IllegalAccessException | NoSuchFieldException | InterruptedException ignored) {

            }
        }
    }

    public interface OnItemListener {
        void onItemClick(int position) throws IllegalAccessException, NoSuchFieldException, InterruptedException;
    }
}