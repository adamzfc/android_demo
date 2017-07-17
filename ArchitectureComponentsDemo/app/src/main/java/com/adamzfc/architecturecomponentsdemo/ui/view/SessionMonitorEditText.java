package com.adamzfc.architecturecomponentsdemo.ui.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

/**
 * Created by adamzfc on 2017/7/17.
 */

public class SessionMonitorEditText extends android.support.v7.widget.AppCompatEditText implements TextView.OnEditorActionListener {
    public interface OnEditSessionCompleteListener {
        public void onEditSessionComplete(TextView v);
    }

    private OnEditSessionCompleteListener mSessionListener;
    private OnEditorActionListener mActionListener;

    public SessionMonitorEditText (Context context) {
        super(context);
        init();
    }

    public SessionMonitorEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SessionMonitorEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        super.setOnEditorActionListener(this);
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        //View will lose focus if use taps another field to edit
        if (!focused) {
            notifySessionListener();
        }
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
    }

    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        //If it's a BACK key, this will end the session
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
            notifySessionListener();
        }

        return super.onKeyPreIme(keyCode, event);
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        // Forward the event, if necessary
        boolean ret = false;
        if (mActionListener != null) {
            ret = mActionListener.onEditorAction(v, actionId, event);
        }
        //Any default editor action typically end the session, except for returns  in  a multi-line field
        boolean returnAction = (actionId == EditorInfo.IME_NULL);
        boolean multiLine = (getInputType() & EditorInfo.TYPE_TEXT_FLAG_MULTI_LINE) != 0;
        if (!returnAction || !multiLine) {
            notifySessionListener();
        }
        return ret;
    }

    @Override
    public void setOnEditorActionListener(OnEditorActionListener l) {
        mActionListener = l;
    }

    public void setOnEditSessionCompleteListener(OnEditSessionCompleteListener l) {
        mSessionListener = l;
    }

    private void notifySessionListener() {
        if (mSessionListener != null) {
            mSessionListener.onEditSessionComplete(this);
        }
    }
}
