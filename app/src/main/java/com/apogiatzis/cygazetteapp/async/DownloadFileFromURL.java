package com.apogiatzis.cygazetteapp.async;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.widget.TextView;

import com.apogiatzis.cygazetteapp.ReadArticleFragment;
import com.apogiatzis.cygazetteapp.models.Article;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Background Async Task to download file
 * */
public class DownloadFileFromURL extends AsyncTask<Article, String, Article> {

    private Context mContext;
    private OnArticleDownloadedListener mListener;

    public DownloadFileFromURL(Context context){
        this.mContext = context;
        this.mListener =(OnArticleDownloadedListener) context;
    }

    /**
     * Before starting background thread
     * */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // Change Fragment??
    }

    /**
     * Downloading file in background thread
     * */
    @Override
    protected Article doInBackground(Article... articles) {
        int count;
        String fileDest = null;
        try {
            URL url = new URL(articles[0].getLink());
            URLConnection connection = url.openConnection();
            connection.connect();
            // getting file length
            int lenghtOfFile = connection.getContentLength();

            // input stream to read file - with 8k buffer
            InputStream input = new BufferedInputStream(url.openStream(), 8192);

            // Output stream to write file
            fileDest = this.mContext.getFilesDir().getAbsolutePath() + "/" + articles[0].getId() + ".pdf" ;
            Log.i("DEBUG", fileDest);
            OutputStream output = new FileOutputStream(fileDest);

            byte data[] = new byte[1024];

            long total = 0;

            while ((count = input.read(data)) != -1) {
                total += count;
                // publishing the progress....
                // After this onProgressUpdate will be called
                publishProgress(""+(int)((total*100)/lenghtOfFile));

                // writing data to file
                output.write(data, 0, count);
            }

            // flushing output
            output.flush();

            // closing streams
            output.close();
            input.close();

        } catch (Exception e) {
            Log.e("Error: ", e.getMessage());
        }

        return articles[0];
    }

    /**
     * Updating progress bar
     * */
    protected void onProgressUpdate(String... progress) {
        Log.i("DEBUG", progress[0]);
    }

    /**
     * After completing background task
     * Dismiss the progress dialog
     * **/
    @Override
    protected void onPostExecute(Article article) {
        // dismiss the dialog after the file was downloaded
        this.mListener.OnArticleDownloaded(article);

    }

    public interface OnArticleDownloadedListener {
        void OnArticleDownloaded(Article article);
    }

}
