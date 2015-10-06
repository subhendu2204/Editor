package com.cobeats.editor;

import android.content.Context;
import android.text.SpanWatcher;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;

public class DroidEditor extends EditText {
    public DroidEditor(Context context) {
        super(context);
        init();
    }

    public DroidEditor(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DroidEditor(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }
    private void init() {
        //addTextChangedListener(this);
    }
}
