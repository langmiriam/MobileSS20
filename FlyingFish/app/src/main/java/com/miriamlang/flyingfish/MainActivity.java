package com.miriamlang.flyingfish;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import androidx.annotation.RequiresApi;
import engine2D.AnimationsAdapter;
import engine2D.BaseGameActivity;
import engine2D.sprites.BackgroundSprite;
import engine2D.sprites.ImageSprite;
import engine2D.sprites.Sprite;

;

public class MainActivity extends BaseGameActivity implements View.OnClickListener {
    private ViewGroup container;
    private static final String TYPEFACE_TITLE = "Arial";
    private Fish fish;
    private BackgroundSprite bckg;
    private BackgroundSprite bckg2;
    private BackgroundSprite bckg3;
    private Set<ImageSprite> stangen = new HashSet<ImageSprite>();
    private Drawable drawableFish;
    private Drawable drawableBckg;
    private Drawable drawableBckg2;
    private Drawable drawableBckg3;
    private Drawable drawableStange;
    private Random random;
    private double timer;
    private ScheduledExecutorService executor;
    private Level level;
    private List<Sprite> objects = new ArrayList<Sprite>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        container = (ViewGroup) findViewById(R.id.container);
        drawableFish = getResources().getDrawable(R.drawable.dummy_fish);
        drawableBckg3 = getResources().getDrawable(R.drawable.backg3);
        drawableBckg2 = getResources().getDrawable(R.drawable.backg2);
        drawableBckg = getResources().getDrawable(R.drawable.unterground);
        drawableStange = getResources().getDrawable(R.drawable.stange);
        random = new Random();

        showStartFragment();
    }

    private void showStartFragment() {
        container.removeAllViews();
        View start = getLayoutInflater().inflate(R.layout.start, null);
        start.findViewById(R.id.start).setOnClickListener(this);
        container.addView(start);

        Animation a = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        start.startAnimation(a);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.start) {
            Animation a = AnimationUtils.loadAnimation(this, R.anim.blink);
            v.startAnimation(a);
            a.setAnimationListener(new AnimationsAdapter() {
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onAnimationEnd(Animation animation) {
                    startGame();
                }
            });
        }
    }

    private long start;
    private Level level1;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void startGame() {
        container.removeAllViews();
        stangen.clear();

        bckg3 = new BackgroundSprite((FrameLayout) container, drawableBckg3, scale(415));
        bckg2 = new BackgroundSprite((FrameLayout) container, drawableBckg2, scale(415));
        bckg = new BackgroundSprite((FrameLayout) container, drawableBckg, scale(415));

        level1 = Level.createlevel1();
        bckg3.setPosition(0, 1250);
        bckg3.setSpeed(30);
        bckg3.draw();
        bckg2.setPosition(0, 1295);
        bckg2.setSpeed(90);
        bckg2.draw();
        bckg.setPosition(0, screenHeight - bckg.getHeight());
        bckg.setSpeed(220);
        bckg.draw();

        fish = new Fish((FrameLayout) container, drawableFish, scale(100));
        fish.setPosition(100, 200);
        fish.setAcceleration(0, 1000);
        executor = Executors.newSingleThreadScheduledExecutor();
        lastTime = System.currentTimeMillis();
        executor.scheduleAtFixedRate(moveRunnable, 0, 10, TimeUnit.MILLISECONDS);
    }

    private long lastTime;
    private double x = 0;

    private Runnable moveRunnable = new Runnable() {
        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    long time = System.currentTimeMillis();

                    long dt = time - lastTime;
                    lastTime = time;
                    x += dt * 0.001;
                    bckg3.draw();
                    bckg3.step(dt * 0.001);
                    bckg2.draw();
                    bckg2.step(dt * 0.001);
                    bckg.draw();
                    bckg.step(dt * 0.001);

                    Obstacle o = level1.getObstacle(x * 0.1);
                    if (o != null && o.type == Obstacle.Type.TOP) {
                        ImageSprite is = new ImageSprite((FrameLayout) container, drawableStange, scale(60));
                        is.setPosition(screenWidth + 20, scale(o.height) - is.getHeight());
                        is.setVelocity(-120, 0);
                        objects.add(is);
                    }
                    if (o != null && o.type == Obstacle.Type.BOTTOM) {
                        ImageSprite is = new ImageSprite((FrameLayout) container, drawableStange, scale(60));
                        is.setPosition(screenWidth + 20, scale(-o.height) + screenHeight);
                        is.setVelocity(-120, 0);
                        objects.add(is);
                    }
                    for (int i = objects.size() - 1; i >= 0; i--) {
                        Sprite sp = objects.get(i);
                        Log.d("TEST", "x position:  " + sp.getX());
                        if (sp.getX() + sp.getWidth() < 0) {
                            objects.remove(sp);
                            sp.burst();
                        }
                    }
                    for (Sprite ob : objects) {
                        ob.draw();
                        ob.step(dt * 0.001);
                    }
                    fish.draw();
                    fish.step(dt * 0.001);
                    if(collision()){
                        Log.d("TEST", "Collision");
                    }

                }
            });
        }
    };

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        fish.setVelocity(0, -550);
        return true;
    }

    private boolean collision() {
        for (Sprite sp : objects) {
            if (fish.overLaps(sp)) {
                return true;
            }
        }
        return false;
    }
}
