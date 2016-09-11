package com.apogiatzis.cygazetteapp.gazetteapi;

import android.os.Environment;

import com.apogiatzis.cygazetteapp.models.Article;
import com.apogiatzis.cygazetteapp.models.CitationFeedItem;
import com.apogiatzis.cygazetteapp.models.Comment;
import com.apogiatzis.cygazetteapp.models.FeedItem;
import com.apogiatzis.cygazetteapp.models.Section;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by andre on 17/08/2016.
 */
public class GazetteAPI {

    //TODO: make this class a signleton
    private static GazetteAPI instance = null;

    private final String HOST = "192.168.1.135";
    private final String PORT = "2403";

    private IGazetteAPI service;

    public static GazetteAPI getInstance() {
        if(instance == null) {
            instance = new GazetteAPI();
        }
        return instance;
    }

    protected GazetteAPI(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(this.getHost())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(IGazetteAPI.class);
    }

    private String getHost(){
        return "http://" + this.HOST + ":" + this.PORT + "/";
    }

    public List<Article> getMockArticles(){
        ArrayList<Article> mockArticles = new ArrayList<Article>();
        mockArticles.add(new Article(Environment.getExternalStorageDirectory().getAbsolutePath() + "/4730C.pdf" , "4730", "13/4/2016", 40L,10L,55L));
        mockArticles.add(new Article(Environment.getExternalStorageDirectory().getAbsolutePath() + "/CV.pdf", "5000", "10/3/2013", 30L,14L,10L));
        mockArticles.add(new Article(Environment.getExternalStorageDirectory().getAbsolutePath() + "/4730C.pdf", "4210", "4/10/2010", 90L,10L,76L));
        mockArticles.add(new Article(Environment.getExternalStorageDirectory().getAbsolutePath() + "/4730C.pdf", "4120", "13/4/2014", 454L,19L,4L));
        mockArticles.add(new Article(Environment.getExternalStorageDirectory().getAbsolutePath() + "/4730C.pdf", "4740", "13/4/2017", 12L,100L,5L));
        return mockArticles;
    }

    public List<CitationFeedItem> getMockCitationFeed(){
        ArrayList<CitationFeedItem> mockFeedItems = new ArrayList<CitationFeedItem>();
        mockFeedItems.add(new CitationFeedItem("13/5/2016 7:00", getCommentsGroup1(),43L, 31L, "Andreas Pogiatzis", "null" ));
        mockFeedItems.add(new CitationFeedItem("15/5/2016 3:00", getCommentsGroup1(),3L, 43L, "Nikos Kouasimodos", "null" ));
        mockFeedItems.add(new CitationFeedItem("17/7/2016 3:20", getCommentsGroup1(),90L, 1L, "Manolis Artemi", "null" ));
        return mockFeedItems;
    }

    public ArrayList<Comment> getCommentsGroup1() {
        ArrayList<Comment> mockComments = new ArrayList<Comment>();
        mockComments.add( new Comment("Andreas Pogiatzis", "4/5/2016 5:65", "Very Nice!"));
        mockComments.add( new Comment("Nikos Eustathiou", "4/5/2016 6:12", "No point of doing that..."));
        mockComments.add( new Comment("Anpnymous", "4/5/2016 8:43", "Agree"));
        mockComments.add( new Comment("Euaggelos Patatas", "4/5/2016 8:50", "Mia pitta souvlakia"));
        return mockComments;
    }

    /*
    Not used now but in the future sectios should be populated by the API
     */
    public List<Section> getSections(){
        Call<List<Section>> call = service.listSections();
        List<Section> sections = null;
        try {
            sections = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sections;
    }

    public void listSectionA(Callback<List<Article>> callback){
        Call<List<Article>> call = service.listSectionA();
        call.enqueue(callback);
    }

    public void like(Article article, Callback<Article> callback){
        Call<Article> call = service.like(article.getId(), article);
        call.enqueue(callback);
    }

    public void dislike(Article article, Callback<Article> callback){
        Call<Article> call = service.dislike(article.getId(), article);
        call.enqueue(callback);
    }


}
