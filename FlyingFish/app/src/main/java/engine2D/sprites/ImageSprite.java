package engine2D.sprites;

import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import engine2D.BaseGameActivity;

public class ImageSprite extends Sprite{
    protected double  ax, ay, vx, vy;
    private final ImageView view;
    private Drawable drawable;
    protected double width;
    protected double height;

    public ImageSprite(FrameLayout container, Drawable drawable, double width) {
        this.x = 0;
        this.y = 0;
        this.ax = 0;
        this.ay = 0;
        this.vx = 0;
        this.vy = 0;
        this.width = width;
        this.height = drawable.getIntrinsicHeight() * width / drawable.getIntrinsicWidth();
        this.drawable = drawable;
        this.view = new ImageView(container.getContext());
        view.setImageDrawable(drawable);
        container.addView(view);
        draw();
    }

    public void step(double dt) {
        x += vx * dt;
        y += vy * dt;
        vx += ax * dt;
        vy += ay * dt;
    }

    public void setVelocity(double vx, double vy) {
        this.vy = vy;
        this.vx = vx;
    }

    public void draw() {
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view.getLayoutParams();
        params.width = (int) width;
        params.height = (int) height;
        params.leftMargin = (int) x;
        params.topMargin = (int) y;
        params.gravity = Gravity.LEFT + Gravity.TOP;
        view.setLayoutParams(params);
    }

    public double getWidth(){
        return width;
    }

    public double getHeight(){
        return height;
    }


    public final void setAcceleration(double ax, double ay){
        this.ay = ay;
        this.ax = ax;
    }

    public void remove() {
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }
    }

    @Override
    public void burst() {
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }

    }
}
