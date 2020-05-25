package engine2D.sprites;

import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import engine2D.BaseGameActivity;

public abstract class Sprite extends BaseGameActivity {

    protected double x, y;
    protected double z;
    protected boolean visible = true;
    protected boolean active = true;

    public double getX(){
        return this.x;
    }

    public double getY(){
        return this.y;
    }

    public void setZ(int z){
        this.z = z;
    }

    public void drawIfVisible(){
        if (visible) draw();
    }

    public abstract  void draw();

    public abstract double getWidth();

    public abstract double getHeight();

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public abstract void step(double dt);

    public void burst(){
    };

    public boolean overLaps(Sprite s) {
        if ((this.x + this.getWidth()) < s.x) {
            return false;
        }
        if (this.x > (s.x + s.getWidth())) {
            return false;
        }
        if ((this.y + this.getHeight()) < s.y) {
            return false;
        }
        if (this.y > (s.y + s.getHeight())) {
            return false;
        }
        else return true;
    }




}
