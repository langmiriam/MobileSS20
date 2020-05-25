package engine2D.particles;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.ImageView;

import engine2D.sprites.Sprite;

public class Partikel extends Sprite {

    private float x;
    private float y;
    private double alpha = 255;
    private float speedX;
    private float speedY;
    private double speedAlpha;
    private RectF dst = new RectF();
    private double scaleFactor;
    private final ImageView view;
    protected double width;
    protected double height;

    public Partikel(FrameLayout container, Drawable drawable, double width) {
        this.width = width;
        this.view = new ImageView(container.getContext());
        view.setImageDrawable(drawable);
        this.height = drawable.getIntrinsicHeight() * width / drawable.getIntrinsicWidth();
    }

    public void drawPartikel(Canvas canvas) {
/*        paint.setAlpha((int) this.alpha);
        double width = bitmap.getWidth() * scaleFactor;
        double height = bitmap.getHeight() * scaleFactor;
        dst.set((float) x, (float) y, (float)(width + x), (float)(height + y));
        canvas.drawBitmap(bitmap, null, dst, paint);*/
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view.getLayoutParams();
        params.width = (int) width;
        params.height = (int) height;
        params.leftMargin = (int) x;
        params.topMargin = (int) y;
        params.gravity = Gravity.LEFT + Gravity.TOP;
        view.setLayoutParams(params);

    }

    public void setScale(double scalefaktor) {
        this.scaleFactor = scalefaktor;
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void setSpeedAlpha(double speedAlpha){
        this.speedAlpha = speedAlpha;
    }

    public void setSpeed(float speedX, float speedY) {
        this.speedX = speedX;
        this.speedY = speedY;
    }


    @Override
    public void draw() {

    }

    @Override
    public double getWidth() {
        return 0;
    }

    @Override
    public double getHeight() {
        return 0;
    }

    @Override
    public void step(double dt) {
        this.x += speedX;
        this.y += speedY;
        this.alpha += speedAlpha;
        if (this.alpha < 0){
            this.alpha = 0;
        }
        if(this.alpha > 255){
            this.alpha = 255;
        }
    }
}
