package com.nikesh.displaypostsexample.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.nikesh.displaypostsexample.R;
import com.nikesh.displaypostsexample.model.PostsModel;
import com.nikesh.displaypostsexample.view.MainActivity;

import java.util.List;

public class RecyclerAdapter  extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>  {
    Context context;
    private List<PostsModel.hitsList> postsList;
    MainActivity listener;
    private boolean enableDisableSwitch;

    public RecyclerAdapter(Context context, List<PostsModel.hitsList> postsModels){
        this.context = context;
        listener = (MainActivity) context;
        postsList = postsModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup holder, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.posts_recycler_row, holder, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tv_postsCreatedAt.setText(postsList.get(position).getCreated_at());
        holder.tv_postsTitle.setText(postsList.get(position).getTitle());

        holder.row_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             switchToggle(holder);
            }
        });
    }

    private void switchToggle(ViewHolder holder) {
        if(enableDisableSwitch){
            holder.switchCompat.setChecked(true);
            enableDisableSwitch = false;
        } else {
            holder.switchCompat.setChecked(false);
            enableDisableSwitch = true;
        }
    }

    @Override
    public int getItemCount() {
        if(postsList != null && postsList.size() >0){
            return postsList.size();
        } else
        return 0;
    }

    public void setList(List<PostsModel.hitsList> data) {
        this.postsList = data;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tv_postsCreatedAt,tv_postsTitle;
        public ConstraintLayout row_layout;
        public SwitchCompat switchCompat;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_postsCreatedAt = itemView.findViewById(R.id.tv_postsCreatedAt);
            tv_postsTitle = itemView.findViewById(R.id.tv_postsTitle);
            row_layout = itemView.findViewById(R.id.row_layout);
            switchCompat = itemView.findViewById(R.id.selectDeselectPost);
        }
    }
}
