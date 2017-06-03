package tetris.game;

/**
 * Created by zhenya on 5/30/17.
 */
public class FigureJ extends Figure{

    private static final int[][] SHAPE_J = {
            {0, 0, Colors.GREEN, 0},
            {0, 0, Colors.GREEN, 0},
            {0, Colors.GREEN, Colors.GREEN, 0},
            {0, 0, 0, 0}
    };


    public FigureJ(float posX, float posY) {
        shape = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++)
            System.arraycopy(SHAPE_J[i], 0, shape[i], 0, SHAPE_J[i].length);
        weight = 2;
        height = 3;
        x = START_X;
        y = START_Y;
        POSITION_X = posX;
        POSITION_Y = posY;
        color = Colors.GREEN;
        createForm();
    }
}
