package com.apogiatzis.cygazetteapp;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.apogiatzis.cygazetteapp.R;
import com.apogiatzis.cygazetteapp.gazetteapi.GazetteAPI;
import com.apogiatzis.cygazetteapp.misc.Config;
import com.apogiatzis.cygazetteapp.models.Article;
import com.apogiatzis.cygazetteapp.models.Section;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class GazetteArticlesFragment extends Fragment implements View.OnClickListener, ListView.OnItemClickListener{

    private ListView lvArticles;
    private GazetteArticleListAdapter lvArticlesAdapter;
    private GazetteAPI gazetteAPI;
    private List<Article> articles;
    private OnArticleClickListener mListener;

    public GazetteArticlesFragment() {
        this.gazetteAPI = GazetteAPI.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gazette_articles, container, false);

        Bundle bundle = this.getArguments();
        articles = (List<Article>) bundle.get(Config.ARTICLE_LIST_SERIALIZABLE_KEY);

        for (Article article : articles){
            Log.i("DEBUG", article.getLink());
        }

        //Mock articles
//        articles = gazetteAPI.getMockArticles();

        //Get view references;
        lvArticles = (ListView) view.findViewById(R.id.lvArticles);
        lvArticlesAdapter = new GazetteArticleListAdapter(getActivity(), articles);
        lvArticles.setAdapter(lvArticlesAdapter);
        lvArticles.setOnItemClickListener(this);

        this.mListener = (OnArticleClickListener) getActivity();

        return view;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
       this.mListener.onArticleClicked(articles.get(position));
    }



    public interface OnArticleClickListener {
        void onArticleClicked(Article article);
    }
}
