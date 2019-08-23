package com.nikesh.displaypostsexample.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.nikesh.displaypostsexample.APIClient;
import com.nikesh.displaypostsexample.apiservice.APIPostsService;
import com.nikesh.displaypostsexample.base.BaseViewModel;
import com.nikesh.displaypostsexample.base.CommonNavigator;
import com.nikesh.displaypostsexample.model.PostsModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostsViewModel extends BaseViewModel<CommonNavigator> {
    private MutableLiveData<PostsModel> postsModelMutableLiveData;

    public PostsViewModel(){
    postsModelMutableLiveData = new MutableLiveData<>();
    loadData();
    }

    private void loadData() {
        postsModelMutableLiveData.setValue(new PostsModel());
    }


    public void getPostsApiCall() {
        try {
            APIPostsService apiPostsService = APIClient.getClient().create(APIPostsService.class);

            getNavigator().loadProgressBar(true);

            Call<PostsModel> getPostsServiceCall = apiPostsService.getPostsCall();
            getPostsServiceCall.enqueue(new Callback<PostsModel>() {
                @Override
                public void onResponse(Call<PostsModel> call, Response<PostsModel> response) {
                    getNavigator().loadProgressBar(false);
                    if(response.code() == 200 && response.body() != null){
                        postsModelMutableLiveData.setValue(response.body());
                    } else {
                        resetModel();
                    }
                }
                @Override
                public void onFailure(Call<PostsModel> call, Throwable t) {
                    getNavigator().loadProgressBar(false);
                }
            });
        } catch (Exception e) {
            Log.e("getPostsCall", "getPostsApiCall: "+ e.getMessage());
            getNavigator().handleError(e);
            getNavigator().loadProgressBar(false);
        }
    }

    public void resetModel(){
        loadData();
    }

    public LiveData<PostsModel> getPostsModelMutableLiveData() {
        return postsModelMutableLiveData;
    }
}
