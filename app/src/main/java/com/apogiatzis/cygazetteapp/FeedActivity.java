package com.apogiatzis.cygazetteapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.apogiatzis.cygazetteapp.gazetteapi.GazetteAPI;
import com.apogiatzis.cygazetteapp.models.CitationFeedItem;
import com.apogiatzis.cygazetteapp.models.FeedItem;

import java.util.ArrayList;
import java.util.List;

public class FeedActivity extends Activity {

    private GazetteAPI gazetteAPI;
    private ListView lvFeed;
    private FeedItemListAdapter lvFeedAdapter;
    private List<CitationFeedItem> feeditems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        gazetteAPI = GazetteAPI.getInstance();
        feeditems = gazetteAPI.getMockCitationFeed();

        lvFeed = (ListView) findViewById(R.id.listFeed);
        lvFeedAdapter = new FeedItemListAdapter(this, feeditems);
        lvFeed.setAdapter(lvFeedAdapter);

    }

    public void ReadGazetteClicked(View view){
        Intent intent = new Intent(this, GazetteActivity.class);
        startActivity(intent);
    }
}
