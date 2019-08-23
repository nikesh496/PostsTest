package com.nikesh.displaypostsexample.apiservice;

import com.nikesh.displaypostsexample.model.PostsModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIPostsService {

//    https://hn.algolia.com/api/v1/search_by_date?tags=story&page=1
    @GET("api/v1/search_by_date?tags=story&page=1")
    Call<PostsModel> getPostsCall();
}
