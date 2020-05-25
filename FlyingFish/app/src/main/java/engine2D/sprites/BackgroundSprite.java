package engine2D.sprites;

import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class BackgroundSprite extends Sprite {
    protected double vx;
    private final ImageView view1;
    private final ImageView view2;
    private final double width;
    private final double height;


    public BackgroundSprite(FrameLayout container, Drawable drawable, double width) {
        this.width = width;
        this.height = drawable.getIntrinsicHeight() * width / drawable.getIntrinsicWidth();
        view1 = new ImageView(container.getContext());
        view2 = new ImageView(container.getContext());
        view1.setImageDrawable(drawable);
        view2.setImageDrawable(drawable);
        container.addView(view1);
        container.addView(view2);
        draw();
    }


    @Override
    public void draw() {
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view1.getLayoutParams();
        params.width = (int) width;
        params.height = (int) height;
        params.leftMargin = (int) x;
        params.topMargin = (int) y;
        params.gravity = Gravity.LEFT + Gravity.TOP;
        view1.setLayoutParams(params);
        FrameLayout.LayoutParams params2 = (FrameLayout.LayoutParams) view2.getLayoutParams();
        params2.width = (int) width;
        params2.height = (int) height;
        params2.leftMargin =(int) x + ((int )width - 4);
        params2.topMargin = params.topMargin;
        params2.gravity = Gravity.LEFT + Gravity.TOP;
        view2.setLayoutParams(params2);
       if (x + width< 0){
           x = 0;
           params.leftMargin = (int) x;
       }
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public void step(double dt) {
        x += vx * dt;
    }

    public void setSpeed(double speed) {
        this.vx = -speed;
    }



}
