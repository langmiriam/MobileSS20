package com.miriamlang.flyingfish;

import android.graphics.drawable.Drawable;
import android.widget.FrameLayout;

import engine2D.sprites.ImageSprite;

public class Fish extends ImageSprite {

    private double fishWidht;
    private double fishHeight;

    public Fish(FrameLayout container, Drawable drawable, double width) {
        super(container, drawable, width);
        fishWidht = drawable.getMinimumWidth();
        fishHeight = drawable.getMinimumHeight();
    }

    @Override
    public void step(double dt) {
        super.step(dt);
        if (y < 0 ) {
            y = 0;
           }
        if (y > (1794 - fishHeight)) y = (1794 - fishHeight);
    }

}
