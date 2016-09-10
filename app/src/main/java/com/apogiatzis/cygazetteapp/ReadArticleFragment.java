package com.apogiatzis.cygazetteapp;


import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.apogiatzis.cygazetteapp.misc.Config;
import com.apogiatzis.cygazetteapp.models.Article;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;

import java.io.File;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReadArticleFragment extends Fragment implements OnLoadCompleteListener {

    private Article article;
    private OnArticleActionListener mListener;
    private PDFView pdfViewArticle;

    public ReadArticleFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_read_article, container, false);

        // Get bundle with article
        Bundle bundle = this.getArguments();
        article = (Article) bundle.get(Config.ARTICLE_SERIALIZABLE_KEY);

        //Load pdf file
        pdfViewArticle = (PDFView) view.findViewById(R.id.pdfViewArticle);
        String fileDest =  getActivity().getFilesDir().getAbsolutePath() + "/"+ article.getId() + ".pdf";
        File fileArticle = new File(fileDest);
        pdfViewArticle.fromFile(fileArticle).
                onLoad(this).
                load();
        pdfViewArticle.resetZoom();

        this.mListener = (OnArticleActionListener) getActivity();

        //Click listeners
        ImageView ivLike = (ImageView) view.findViewById(R.id.ivLike);
        ImageView ivDislike = (ImageView) view.findViewById(R.id.ivDislike);
        ivLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onArticleLikeClicked(article);
            }
        });
        ivDislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onArticleDislikeClicked(article);
            }
        });

        return view;
    }

    @Override
    public void loadComplete(int nbPages) {
        Log.i("DEBUG", String.valueOf(pdfViewArticle.getPageCount()));
    }

    //Interface to communicate with activity
    public interface OnArticleActionListener {
        void onArticleLikeClicked(Article article);

        void onArticleDislikeClicked(Article article);
    }
}
