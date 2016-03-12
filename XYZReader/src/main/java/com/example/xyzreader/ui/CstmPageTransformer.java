package com.example.xyzreader.ui;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by Ian on 2/28/2016.
 */
public class CstmPageTransformer implements ViewPager.PageTransformer {

    public static final float MIN_SCALE = 0.85f;
    public static final float MIN_ALPHA = 0.5f;

    @Override
    public void transformPage (View page, float position) {
        int width = page.getWidth();
        int height = page.getHeight();

        if (position < -1 ) {

            // Page if off screen to the left
            page.setAlpha( 0 );

        } else if ( position <= 1) {

            // Modify the default slide transition to shrink the page as well
            float scaleFactor = Math.max( MIN_SCALE, 1 - Math.abs( position ) );
            float vertMargin = height * (1 - scaleFactor) / 2;
            float horzMargin = width * (1 - scaleFactor) / 2;

            if (position < 0) {
                page.setTranslationX( horzMargin - vertMargin / 2);
            } else {
                page.setTranslationY( -horzMargin + vertMargin / 2 );
            }

            // Scale the page down (between MIN_SCALE and 1)
            page.setScaleX( scaleFactor );
            page.setScaleY( scaleFactor );

            // Fade the page relative to its size.
            page.setAlpha(MIN_ALPHA + (scaleFactor - MIN_SCALE) / (1 - MIN_SCALE) * (1 -
                    MIN_ALPHA));

        } else {

            // Page if off screen to the right
            page.setAlpha( 0 );
        }
    }
}
