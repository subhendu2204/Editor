package com.cobeats.editor;

import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

/**
 * Created by sony on 05-10-2015.
 */
public class URLDrawable extends BitmapDrawable {
    // the drawable that you need to set, you could set the initial drawing
    // with the loading image if you need to
    protected Drawable drawable;

    @Override
    public void draw(Canvas canvas) {
        // override the draw to facilitate refresh function later
        if(drawable != null) {
            drawable.draw(canvas);
        }
    }
}
