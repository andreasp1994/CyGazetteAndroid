package com.apogiatzis.cygazetteapp;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.apogiatzis.cygazetteapp.misc.Config;
import com.apogiatzis.cygazetteapp.misc.Design;
import com.apogiatzis.cygazetteapp.models.Article;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.ConsoleHandler;

/**
 * Created by andre on 17/08/2016.
 */
public class GazetteArticleListAdapter extends ArrayAdapter<Article>  {

    private List<Article> articles;
    private Context mContext;

    private static class ViewHolder {
        TextView tvIssue;
        TextView tvDate;
        TextView tvCitations;
        TextView tvLikes;
        TextView tvDislikes;
        TextView tvPages;
        RelativeLayout itemLayout;
    }

    public GazetteArticleListAdapter(Context context, List<Article> articles){
        super(context, R.layout.gazette_article_item, articles);
        this.mContext = getContext();
        this.articles=articles;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Article article = getItem(position);

        final ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.gazette_article_item, parent, false);
            viewHolder.tvIssue = (TextView) convertView.findViewById(R.id.tvIssue);
            viewHolder.tvDate = (TextView) convertView.findViewById(R.id.tvDate);
            viewHolder.tvCitations = (TextView) convertView.findViewById(R.id.tvCitations);
            viewHolder.tvLikes = (TextView) convertView.findViewById(R.id.tvLikes);
            viewHolder.tvDislikes = (TextView) convertView.findViewById(R.id.tvDislikes);
            viewHolder.tvPages = (TextView) convertView.findViewById(R.id.tvPages);
            viewHolder.itemLayout = (RelativeLayout) convertView.findViewById(R.id.itemLayout);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //Set view values
        viewHolder.tvIssue.setText(article.getIssue());
        viewHolder.tvDate.setText(" - " + article.getDate());
        viewHolder.tvCitations.setText(article.getCitations().toString());
        viewHolder.tvLikes.setText(article.getLikes().toString());
        viewHolder.tvDislikes.setText(article.getDislikes().toString());
        viewHolder.itemLayout.setBackgroundColor(Design.pickColor());

        return convertView;
    }
}
