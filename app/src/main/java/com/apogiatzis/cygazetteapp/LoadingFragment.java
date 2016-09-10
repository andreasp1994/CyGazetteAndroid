package com.apogiatzis.cygazetteapp;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.apogiatzis.cygazetteapp.models.Article;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoadingFragment extends Fragment {

    private ImageView ivLoader;

    public LoadingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_loading, container, false);

        //Start loading animation
        ivLoader = (ImageView) view.findViewById(R.id.ivLoader);
        startLoadingAnimation();

        return view;
    }

    private void startLoadingAnimation() {
        final Animation loaderAnim1 = AnimationUtils.loadAnimation(getActivity(), R.anim.loader_step1);
        final Animation loaderAnim2 = AnimationUtils.loadAnimation(getActivity(), R.anim.loader_step2);
        final Animation loaderAnim3 = AnimationUtils.loadAnimation(getActivity(), R.anim.loader_step3);
        final Animation loaderAnim4 = AnimationUtils.loadAnimation(getActivity(), R.anim.loader_step4);
        loaderAnim1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ivLoader.startAnimation(loaderAnim2);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        loaderAnim2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ivLoader.startAnimation(loaderAnim3);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        loaderAnim3.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ivLoader.startAnimation(loaderAnim4);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        loaderAnim4.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ivLoader.startAnimation(loaderAnim1);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        ivLoader.startAnimation(loaderAnim3);
    }

    public interface OnLoadCompleteListener {
        void OnLoadComplete(List<Article> articles);
    }

}
