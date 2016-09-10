package com.apogiatzis.cygazetteapp.gazetteapi;

import com.apogiatzis.cygazetteapp.models.Article;
import com.apogiatzis.cygazetteapp.models.Section;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by andre on 05/09/2016.
 */
public interface IGazetteAPI {

    @GET("sections/")
    Call<List<Section>> listSections();

    @GET("tmhma-a/")
    Call<List<Article>> listSectionA();

    @PUT("/tmhma-a/{id}/")
    Call<Article> like(@Path("id") String id, @Body Article article);

    @PUT("/tmhma-a/{id}/")
    Call<Article> dislike(@Path("id") String id, @Body Article article);
}
