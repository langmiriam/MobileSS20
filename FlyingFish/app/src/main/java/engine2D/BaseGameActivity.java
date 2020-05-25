package engine2D;
import android.app.Activity;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.widget.TextView;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.Nullable;

public class BaseGameActivity extends Activity {

    private Map<String, Typeface> typefaces = new HashMap<String, Typeface>();
    protected float density;
    protected int screenWidth;
    protected int screenHeight;

    protected void addTypeFace(String name) {
        Typeface typeface = Typeface.createFromAsset(getAssets(), name + ".ttf");
        typefaces.put(name, typeface);
    }

    protected void setTypeface(TextView textView, String typeface) {
        Typeface t = typefaces.get(typefaces);
        if (t != null) {
            textView.setTypeface(t);
        }
    }

    protected float scale(float v) {
        return density * v;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        density = getResources().getDisplayMetrics().density;
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
        screenHeight= size.y;
        Log.d("TEST", "density" + display);
    }
}
