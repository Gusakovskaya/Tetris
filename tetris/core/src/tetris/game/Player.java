package tetris.game;

import com.badlogic.gdx.utils.Json;

import java.util.Comparator;

/**
 * Created by zhenya on 6/4/17.
 */
public class Player implements Comparable<Player> {

    private String nickName;
    protected int points;
    private int lines;

    public Player(String name, int point, int line){
        nickName = name;
        points = point;
        lines = line;
    }

    public String toString() {
        return String.format("Name: %1$s, Points: %2$s, Lines: %3$s\n", nickName, points, lines);
    }

    @Override
    public int compareTo(Player o) {
        if (points < o.points) {
            return 1;
        }
        else if (points == o.points) {
            return 0;
        }
        else {
            return -1;
        }
    }
}

