package com.maku.elimuinterview.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.maku.githubapitestapp.models.MyRepoList;

import com.maku.elimuinterview.R;

import java.util.ArrayList;

public class RepoListInfoAdapter extends RecyclerView.Adapter<RepoListInfoAdapter.ViewHolder>  {


        Context mContext;
        ArrayList<MyRepoList> mMyRepoLists;

public RepoListInfoAdapter(Context context, ArrayList<MyRepoList> Profiles) {
        mContext = context;
        mMyRepoLists = Profiles;
        }

@NonNull
@Override
public RepoListInfoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_item, parent, false);
        RepoListInfoAdapter.ViewHolder viewHolder = new RepoListInfoAdapter.ViewHolder(view);
        return viewHolder;
        }

@Override
public void onBindViewHolder(@NonNull RepoListInfoAdapter.ViewHolder holder, int position) {
        holder.mTextViewName.setText(mMyRepoLists.get(position).getName());
        }

@Override
public int getItemCount() {
        return mMyRepoLists.size();
        }

public class ViewHolder extends RecyclerView.ViewHolder {

    public TextView mTextViewName;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        mTextViewName = itemView.findViewById(R.id.repoName);
    }
}
}
