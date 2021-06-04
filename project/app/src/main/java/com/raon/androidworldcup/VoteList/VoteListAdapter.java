package com.raon.androidworldcup.VoteList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.raon.androidworldcup.R;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Member;
import java.util.ArrayList;

public class VoteListAdapter extends RecyclerView.Adapter<VoteListAdapter.ViewHolder> {

    private ArrayList<Vote> list = null;

    public VoteListAdapter(ArrayList<Vote> list){
        this.list = list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageButton btn;
        TextView title;

        public ViewHolder(View itemView) {
            super(itemView);
            btn = itemView.findViewById(R.id.voteImage);
            title = itemView.findViewById(R.id.voteTitle);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.vote, parent, false);
        VoteListAdapter.ViewHolder viewHolder = new VoteListAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(VoteListAdapter.ViewHolder holder, int position) {
        Vote item = list.get(position);

        holder.btn.setImageDrawable(item.getIcon());
        holder.title.setText(item.getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
