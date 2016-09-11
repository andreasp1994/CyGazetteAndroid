package com.apogiatzis.cygazetteapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.apogiatzis.cygazetteapp.models.Article;
import com.apogiatzis.cygazetteapp.models.Comment;

import java.util.List;

/**
 * Created by andre on 11/09/2016.
 */
public class CommentsListAdapter extends ArrayAdapter<Comment> {

    private Context mContext;
    private List<Comment> comments;

    private static class ViewHolder {
        TextView tvUser;
        TextView tvContent;
    }

    public CommentsListAdapter(Context context, List<Comment> comments){
        super(context, R.layout.comment_item, comments);
        this.mContext = getContext();
        this.comments = comments;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Comment comment = getItem(position);

        final ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.comment_item, parent, false);
            viewHolder.tvUser = (TextView) convertView.findViewById(R.id.tvCommentUsername);
            viewHolder.tvContent = (TextView) convertView.findViewById(R.id.tvCommentContent);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tvUser.setText(comment.getUser());
        viewHolder.tvContent.setText(comment.getContent());

        return convertView;
    }


}
