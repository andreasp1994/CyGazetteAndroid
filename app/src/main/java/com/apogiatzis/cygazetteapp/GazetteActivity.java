package com.apogiatzis.cygazetteapp;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.apogiatzis.cygazetteapp.async.DownloadFileFromURL;
import com.apogiatzis.cygazetteapp.gazetteapi.GazetteAPI;
import com.apogiatzis.cygazetteapp.misc.Config;
import com.apogiatzis.cygazetteapp.models.Article;
import com.apogiatzis.cygazetteapp.models.Section;
import com.apogiatzis.cygazetteapp.permissions.PermissionsManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GazetteActivity extends Activity implements GazetteSectionsFragment.OnSectionClickedListener,
                                                            GazetteArticlesFragment.OnArticleClickListener,
                                                            ReadArticleFragment.OnArticleActionListener,
                                                            DownloadFileFromURL.OnArticleDownloadedListener{
    private GazetteAPI gazetteAPI;
    private FragmentManager fm;
    private Fragment transitionFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gazette);

        //Initialize API Service
        gazetteAPI = GazetteAPI.getInstance();

        //Load initial fragment
        Fragment gazetteSectionsFrag = new GazetteSectionsFragment();
        fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.llContainer, gazetteSectionsFrag);
        ft.attach(gazetteSectionsFrag);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void onSectionClicked(Section section) {
        switch (section.getId()){
            case Section.SECTIONID_TMHMA_A:
                //Show loading fragment
                changeFragment(new LoadingFragment(), true);

                //Get the data in the background
                gazetteAPI.listSectionA(new Callback<List<Article>>() {
                    @Override
                    public void onResponse(Call<List<Article>> call, Response<List<Article>> response) {
                        ArrayList<Article> articles = (ArrayList<Article>) response.body();
                        Fragment fragment = new GazetteArticlesFragment();
                        Bundle bundle = new Bundle();
                        bundle.putParcelableArrayList(Config.ARTICLE_LIST_SERIALIZABLE_KEY, articles );
                        fragment.setArguments(bundle);
                        changeFragment(fragment, true);
                    }

                    @Override
                    public void onFailure(Call<List<Article>> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
                break;
            case Section.SECTIONID_TMHMA_B:
                break;
            case Section.SECTIONID_TMHMA_C:
                break;
        }
    }

    @Override
    public void onArticleClicked(Article article) {
        changeFragment(new LoadingFragment(), true);
        DownloadFileFromURL downloadFileFromURL = new DownloadFileFromURL(this);
        downloadFileFromURL.execute(article);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case PermissionsManager.STORAGE_PERMISSION_CODE:
                changeFragment(transitionFragment, true);
        }
    }

    /*
    Transitions fragments from left to right
    */
    public void changeFragment(Fragment fragment, boolean addToBackStack){
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.setCustomAnimations(R.animator.fragment_slide_left_enter,
                R.animator.fragment_slide_left_exit,
                R.animator.fragment_slide_right_enter,
                R.animator.fragment_slide_right_exit);
        ft.replace(R.id.llContainer, fragment);
        ft.attach(fragment);
        if (addToBackStack)
            ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void onArticleLikeClicked(Article article) {
        article.like();
        gazetteAPI.like(article, new Callback<Article>() {
            @Override
            public void onResponse(Call<Article> call, Response<Article> response) {
                Log.i("DEBUG", "LIKED");
            }

            @Override
            public void onFailure(Call<Article> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onArticleDislikeClicked(Article article) {
        article.dislike();
        gazetteAPI.dislike(article, new Callback<Article>() {
            @Override
            public void onResponse(Call<Article> call, Response<Article> response) {
                Log.i("DEBUG", "DISLIKED");
            }

            @Override
            public void onFailure(Call<Article> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onArticleSelectRegionClicked(Article article) {

    }

    @Override
    public void OnArticleDownloaded(Article article) {
        transitionFragment = new ReadArticleFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(Config.ARTICLE_SERIALIZABLE_KEY, article );
        transitionFragment.setArguments(bundle);
        PermissionsManager.requestStoragePermission(this);
    }
}
