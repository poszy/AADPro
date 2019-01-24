package com.example.lu.compass;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;

public class CompassView extends View {

    private float mBearing;

    // references to each resources creaed in string values.
    // these are needed to create new class scoped paint objects

    private Paint markerPaint;
    private Paint textPaint;
    private Paint circlePaint;

    private String northString;
    private String eastString;
    private String southString;
    private String westString;

    private int textHeight;

    public CompassView(Context context) {
        this(context, null);
    }

    public CompassView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CompassView(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setFocusable(true);

        final TypedArray a = context.obtainStyledAttributes( attrs, R.styleable.CompassView, defStyleAttr, 0 );
        if(a.hasValue(R.styleable.CompassView_bearing)){
            setBearing(a.getFloat(R.styleable.CompassView_bearing, 0));
        }

        a.recycle();

        Context c = this.getContext();
        Resources r = this.getResources();

        circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        circlePaint.setColor(ContextCompat.getColor(c, R.color.background_color));
        circlePaint.setStrokeWidth(1);
        circlePaint.setStyle(Paint.Style.FILL_AND_STROKE);

        northString = r.getString(R.string.cardinal_north);
        eastString  = r.getString(R.string.cardinal_east);
        southString = r.getString(R.string.cardinal_south);
        westString  = r.getString(R.string.cardinal_west);

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(ContextCompat.getColor(c, R.color.text_color));

        textHeight = (int) textPaint.measureText("yY");

        markerPaint= new Paint(Paint.ANTI_ALIAS_FLAG);
        markerPaint.setColor(ContextCompat.getColor(c, R.color.marker_color));



    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int measureWidth = measure(widthMeasureSpec);
        int measureHeight = measure(heightMeasureSpec);

        int d = Math.min(measureWidth, measureHeight);
        setMeasuredDimension(d,d);

        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    private int measure(int measureSpec){

        int result = 0;

        // Decode the measurement specifications.
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if(specMode == MeasureSpec.UNSPECIFIED){

            // return a default of 200 if no bounderies are specified.
            result = 200;

        }else{

            // I want ot fill the available space
            // always return the full available bounds
           result = specSize;

        }

        return result;

    } // end measure

    // geting the restored bearing
    // whatever this means.
    public void setBearing(float bearing){ mBearing = bearing; invalidate(); sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED); }
    public float getBearing(){ return mBearing; }




    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int mMeasuredWidth = getMeasuredWidth();
        int mMeasuredHeigth = getMeasuredHeight();

        int px = mMeasuredWidth / 2;
        int py = mMeasuredHeigth / 2;

        int radius = Math.min(px, py);

        // Drawing the outer boundary and the color of the background of the compass
        canvas.drawCircle(px, py, radius, circlePaint);


        // rotate to our prespective, to the top is facing the current bearing
        canvas.save();
        canvas.rotate( -mBearing, px, py);


        // Draw the markings

        int textWidth = (int) textPaint.measureText("W");
        int cardinalX = px-textWidth/2;
        int cardinalY = py-radius+textHeight;

        // Draw the markings every 15 degrees and text every 45


        for(int i = 0; i < 24; i++){

            canvas.drawLine(px, py-radius, px, py-radius + 10, markerPaint);
            canvas.save();
            canvas.translate(0, textHeight);

            // Draw the cardinal points
            if( i % 6 == 0){

                String dirString = "";
                switch (i){

                    case(0) : {

                        dirString = northString;
                        int arrowY = 2*textHeight;
                        canvas.drawLine(px,arrowY,px -5, 3*textHeight, markerPaint);
                        canvas.drawLine(px,arrowY,px +5, 3*textHeight, markerPaint);
                        break;

                    } // end case 0


                    case(6): dirString = eastString; break;
                    case(12): dirString = southString; break;
                    case(18): dirString = westString; break;


                }// end Switch

                canvas.drawText(dirString, cardinalX, cardinalY, textPaint);

            } // end if

            else if ( i % 3 == 0){


                // draw the text every alternate 45 deg
                String angle = String.valueOf(i*15);
                float angleTextWidth = textPaint.measureText(angle);

                int angleTextX = (int) (px-angleTextWidth/2);
                int angleTextY = py-radius+textHeight;
                canvas.drawText(angle,angleTextX,angleTextY, textPaint);


            } // end else if

            canvas.restore();

            canvas.rotate(15, px, py);
        }

        canvas.restore();

    }// end onDraw


    @Override
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event) {
        super.dispatchPopulateAccessibilityEvent(event);

        if(isShown()){
            String bearingStr = String.valueOf(mBearing);
            event.getText().add(bearingStr);
            return true;
        }else{ return false;}
    }



} // end CompasView Class
