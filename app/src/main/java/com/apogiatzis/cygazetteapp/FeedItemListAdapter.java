package com.apogiatzis.cygazetteapp;

import android.content.Context;
import android.media.Image;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.apogiatzis.cygazetteapp.models.Article;
import com.apogiatzis.cygazetteapp.models.CitationFeedItem;
import com.apogiatzis.cygazetteapp.models.Comment;
import com.apogiatzis.cygazetteapp.models.FeedItem;

import java.util.List;

/**
 * Created by andre on 10/09/2016.
 */
public class FeedItemListAdapter extends ArrayAdapter<CitationFeedItem> {

    private List<CitationFeedItem> feedItems;
    private Context mContext;

    private static class ViewHolder {
        TextView tvUser;
        TextView tvDate;
        ImageView ivCitation;
        TextView tvLikes;
        TextView tvDislikes;
        TextView tvComments;
        ListView lvComments;
        ImageView ivComments;
        LinearLayout llCommentsContainer;
        ImageView ivLikeBtn;
    }

    public FeedItemListAdapter(Context context, List<CitationFeedItem> feedItems){
        super(context, R.layout.feed_citation_item, feedItems);
        this.mContext = context;
        this.feedItems = feedItems;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final CitationFeedItem feedItem = this.feedItems.get(position);

        final ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.feed_citation_item, parent, false);
            viewHolder.tvLikes = (TextView) convertView.findViewById(R.id.tvLikes);
            viewHolder.tvDislikes = (TextView) convertView.findViewById(R.id.tvDislikes);
            viewHolder.tvUser = (TextView) convertView.findViewById(R.id.tvUser);
            viewHolder.tvComments = (TextView) convertView.findViewById(R.id.tvComments);
            viewHolder.lvComments = (ListView) convertView.findViewById(R.id.lvComments);
            viewHolder.llCommentsContainer = (LinearLayout) convertView.findViewById(R.id.llComments);
            viewHolder.ivComments = (ImageView) convertView.findViewById(R.id.ivComment);
            viewHolder.ivLikeBtn = (ImageView) convertView.findViewById(R.id.ivLikeBtn);
            //Image citation

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tvLikes.setText(feedItem.getLikes().toString());
        viewHolder.tvDislikes.setText(feedItem.getDislikes().toString());
        viewHolder.tvUser.setText(feedItem.getUser());
        viewHolder.tvComments.setText(String.valueOf(feedItem.getComments().size()));

        List<Comment> comments = feedItem.getComments();
        CommentsListAdapter adapter = new CommentsListAdapter(this.mContext, comments);
        viewHolder.lvComments.setAdapter(adapter);

        viewHolder.tvComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewHolder.llCommentsContainer.getVisibility() == View.GONE){
                    viewHolder.llCommentsContainer.setVisibility(View.VISIBLE);
                } else {
                    viewHolder.llCommentsContainer.setVisibility(View.GONE);
                }
            }
        });
        viewHolder.ivComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewHolder.llCommentsContainer.getVisibility() == View.GONE){
                    viewHolder.llCommentsContainer.setVisibility(View.VISIBLE);
                } else {
                    viewHolder.llCommentsContainer.setVisibility(View.GONE);
                }
            }
        });

        return convertView;
    }

}
