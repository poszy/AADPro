package com.practice.algorithmsbylu.ExtendedClases;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;

public class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

    private GestureDetector mgestureDetection;
    private ClickListener mOnItemClickListeners;

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View child = rv.findChildViewUnder(e.getX(), e.getY());
        if (child != null && mOnItemClickListeners != null && mgestureDetection.onTouchEvent(e)) {
            mOnItemClickListeners.onClick(child, rv.getChildPosition(child));
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener mOnItemClickListener) {

        this.mOnItemClickListeners = mOnItemClickListener;

        mgestureDetection = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if (child != null && mOnItemClickListener != null) {
                    mOnItemClickListener.onLongClick(child, recyclerView.getChildPosition(child));
                }
            }
        });

    }

}
