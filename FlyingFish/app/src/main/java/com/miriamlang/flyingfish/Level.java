package com.miriamlang.flyingfish;

import android.icu.util.IslamicCalendar;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class Level {
    protected List<Obstacle> obstacles;
    private int currentObstacle;

    public Level(){
        obstacles = new ArrayList<Obstacle>();
    }

    public void add(Obstacle o){
        obstacles.add(o);
    }

    public static Level createlevel1(){
        Level level = new Level();
        level.add(new Obstacle(200, Obstacle.Type.TOP, 0));
        level.add(new Obstacle(200, Obstacle.Type.BOTTOM, 0));
        level.add(new Obstacle(250, Obstacle.Type.TOP, 1));
        level.add(new Obstacle(250, Obstacle.Type.BOTTOM, 1));
        level.add(new Obstacle(180, Obstacle.Type.TOP, 2));
        level.add(new Obstacle(180, Obstacle.Type.BOTTOM, 2));
        level.add(new Obstacle(200, Obstacle.Type.TOP, 3));
        level.add(new Obstacle(200, Obstacle.Type.BOTTOM, 3));
        level.add(new Obstacle(250, Obstacle.Type.TOP, 4));
        level.add(new Obstacle(250, Obstacle.Type.BOTTOM, 4));
        level.add(new Obstacle(200, Obstacle.Type.TOP, 5));
        level.add(new Obstacle(200, Obstacle.Type.BOTTOM, 5));
        level.add(new Obstacle(250, Obstacle.Type.TOP, 6));
        level.add(new Obstacle(250, Obstacle.Type.BOTTOM, 6));
        level.add(new Obstacle(180, Obstacle.Type.TOP, 7));
        level.add(new Obstacle(180, Obstacle.Type.BOTTOM, 7));
        level.add(new Obstacle(200, Obstacle.Type.TOP, 8));
        level.add(new Obstacle(200, Obstacle.Type.BOTTOM, 8));
        level.add(new Obstacle(250, Obstacle.Type.TOP, 9));
        level.add(new Obstacle(250, Obstacle.Type.BOTTOM, 9));
        return level;
    }

    public Obstacle getObstacle(double x){
            if (currentObstacle >= obstacles.size()) {
                return null;
            }
            Obstacle o = obstacles.get(currentObstacle);
            if (x < o.x ) {
                return null;
            }
            currentObstacle++;
            return o;
    }




}
