package com.apogiatzis.cygazetteapp;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apogiatzis.cygazetteapp.models.Section;

import java.io.File;


/**
 * A simple {@link Fragment} subclass.
 */
public class GazetteSectionsFragment extends Fragment implements View.OnClickListener {

    private OnSectionClickedListener mListener;

    public GazetteSectionsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_gazette_sections, container, false);

        LinearLayout llSectionTmhmaA = (LinearLayout) view.findViewById(R.id.llSectionTmhmaA);
        LinearLayout llSectionTmhmaB = (LinearLayout) view.findViewById(R.id.llSectionTmhmaB);
        LinearLayout llSectionTmhmaC = (LinearLayout) view.findViewById(R.id.llSectionTmhmaC);
        llSectionTmhmaA.setOnClickListener(this);
        llSectionTmhmaB.setOnClickListener(this);
        llSectionTmhmaC.setOnClickListener(this);

        this.mListener = (OnSectionClickedListener) getActivity();

        TextView menuFeed = (TextView) view.findViewById(R.id.menuFeed);
        menuFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FeedActivity.class);
                startActivity(intent);
            }
        });
        TextView menuSearch = (TextView) view.findViewById(R.id.menuSearch);
        menuSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.llSectionTmhmaA:
                mListener.onSectionClicked(new Section(Section.SECTIONID_TMHMA_A));
                break;
            case R.id.llSectionTmhmaB:
                mListener.onSectionClicked(new Section(Section.SECTIONID_TMHMA_B));
                break;
            case R.id.llSectionTmhmaC:
                mListener.onSectionClicked(new Section(Section.SECTIONID_TMHMA_C));
                break;
        }
    }

    public interface OnSectionClickedListener {
        void onSectionClicked(Section section);
    }

}
