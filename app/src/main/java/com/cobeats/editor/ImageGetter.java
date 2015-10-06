package com.cobeats.editor;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.text.Html;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.security.auth.login.LoginException;

/**
 * Created by sony on 05-10-2015.
 */
public class ImageGetter implements Html.ImageGetter {

    Context c;
    TextView container;

    /***
     * Construct the URLImageParser which will execute AsyncTask and refresh the container
     *
     * @param t
     * @param c
     */
    public ImageGetter(TextView t, Context c) {
        this.c = c;
        this.container = t;
    }

    public Drawable getDrawable(String source) {
        URLDrawable urlDrawable = new URLDrawable();

        // get the actual source
        ImageGetterAsyncTask asyncTask =
                new ImageGetterAsyncTask(urlDrawable);

        asyncTask.execute(source);

        // return reference to URLDrawable where I will change with actual image from
        // the src tag
        return urlDrawable;
    }

    public class ImageGetterAsyncTask extends AsyncTask<String, Void, Drawable> {
        URLDrawable urlDrawable;

        public ImageGetterAsyncTask(URLDrawable d) {
            this.urlDrawable = d;
        }

        @Override
        protected Drawable doInBackground(String... params) {
            String source = params[0];
            return fetchDrawable(source);
        }

        @Override
        protected void onPostExecute(Drawable result) {
            // set the correct bound according to the result from HTTP call
            urlDrawable.setBounds(0, 0, 0 + result.getIntrinsicWidth(), 0
                    + result.getIntrinsicHeight());

            // change the reference of the current drawable to the result
            // from the HTTP call
            Log.d("subhendu","On post execute");
            urlDrawable.drawable = result;

            // redraw the image by invalidating the container
            ImageGetter.this.container.invalidate();

            // For ICS

            ImageGetter.this.container.setHeight(ImageGetter.this.container.getHeight()+ result.getIntrinsicHeight());
            Log.d("subhendu", "Image Result height == " + ImageGetter.this.container.getHeight() + " width == " + result.getIntrinsicWidth());

            // Pre ICS
            //ImageGetter.this.textView.setEllipsize(null);

        }

        /***
         * Get the Drawable from URL
         * @param urlString
         * @return
         */
        public Drawable fetchDrawable(String urlString) {
            try {

                //InputStream is = fetch(urlString);
                InputStream is = new URL(urlString).openStream();
                Drawable drawable = Drawable.createFromStream(is, "src");
                Log.d("subhendu","Image input height == " + drawable.getIntrinsicHeight() + " width == " + drawable.getIntrinsicWidth());
                drawable.setBounds(0, 0, 0 + drawable.getIntrinsicWidth(), 0
                        + drawable.getIntrinsicHeight());
                Log.d("subhendu","drawable fetched");
                return drawable;

            } catch (Exception e) {
                return null;
            }
        }

       /* private InputStream fetch(String urlString) throws MalformedURLException, IOException {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet request = new HttpGet(urlString);
            HttpResponse response = httpClient.execute(request);
            return response.getEntity().getContent();
        }*/
    }
}
