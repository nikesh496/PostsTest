package com.nikesh.displaypostsexample.view;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.nikesh.displaypostsexample.R;
import com.nikesh.displaypostsexample.adapter.RecyclerAdapter;
import com.nikesh.displaypostsexample.base.CommonNavigator;
import com.nikesh.displaypostsexample.model.PostsModel;
import com.nikesh.displaypostsexample.viewmodel.PostsViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CommonNavigator {
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private PostsViewModel viewModel;
    private RecyclerAdapter adapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(PostsViewModel.class);
        viewModel.setNavigator(this);

        swipeRefreshLayout = findViewById(R.id.swipeLayout);
        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);
        bindAdapter(new ArrayList<PostsModel.hitsList>());

        viewModel.getPostsApiCall();

        viewModel.getPostsModelMutableLiveData().observe(this, new Observer<PostsModel>() {
            @Override
            public void onChanged(PostsModel postsModel) {
                adapter.setList(postsModel.getData());
                swipeRefreshLayout.setRefreshing(false);
                recyclerView.setVisibility(View.VISIBLE);
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                recyclerView.setVisibility(View.GONE);
                viewModel.getPostsApiCall();
                recyclerView.setVisibility(View.VISIBLE);
            }
        });
    }

    private void bindAdapter(List<PostsModel.hitsList> postsModels) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        adapter = new RecyclerAdapter(this, postsModels);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void handleError(Throwable throwable) {

    }

    @Override
    public void loadProgressBar(boolean showProgress) {
        if (showProgress) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    public void switchToggle(int position) {

    }
}
