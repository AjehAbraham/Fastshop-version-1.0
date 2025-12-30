 package com.fastshop.myApp;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class SwipeGesture {
    public GestureDetector gestureDetector;
    public SwipeListener swipeListener;

    public interface SwipeListener {
        void onSwipeDown();
        void onSwipeUp();
        void onSwipeLeft();
        void onSwipeRight();
    }

    public SwipeGesture(Context context, SwipeListener swipeListener) {
        this.swipeListener = swipeListener;
        gestureDetector = new GestureDetector(context, new GestureListener());
    }

    public void setOnTouchListener(View view) {
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        });
    }

    private class GestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final int SWIPE_THRESHOLD = 200;
        private static final int SWIPE_VELOCITY_THRESHOLD = 500;

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float diffY = e2.getY() - e1.getY();
            float diffX = e2.getX() - e1.getX();

            if (Math.abs(diffY) > Math.abs(diffX)) {
                if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffY > 0) {
                        swipeListener.onSwipeDown();
                    } else {
                        swipeListener.onSwipeUp();
                    }
                    return true;
                }
            } else {
                if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffX > 0) {
                        swipeListener.onSwipeRight();
                    } else {
                        swipeListener.onSwipeLeft();
                    }
                    return true;
                }
            }
            return false;
        }
    }
}
/*import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class SwipeDetector {

    public GestureDetector gestureDetector;
    public SwipeListener swipeListener;

    public interface SwipeListener {
        void onSwipeDown();
        void onSwipeUp();
        void onSwipeLeft();
        void onSwipeRight();
    }

    public SwipeDetector(Context context, SwipeListener swipeListener) {
        this.swipeListener = swipeListener;
        gestureDetector = new GestureDetector(context, new GestureListener());
    }

    public void setOnTouchListener(View view) {
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        });
    }

    private class GestureListener extends GestureDetector.SimpleOnGestureListener {

        private static final int SWIPE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float diffY = e2.getY() - e1.getY();
            float diffX = e2.getX() - e1.getX();

            if (Math.abs(diffY) > Math.abs(diffX)) {
                if (diffY > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                    swipeListener.onSwipeDown();
                    return true;
                } else if (diffY < -SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                    swipeListener.onSwipeUp();
                    return true;
                }
            } else {
                if (diffX > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    swipeListener.onSwipeRight();
                    return true;
                } else if (diffX < -SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    swipeListener.onSwipeLeft();
                    return true;
                }
            }

            return false;
        }
    }
}*/