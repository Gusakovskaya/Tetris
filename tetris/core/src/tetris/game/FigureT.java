package tetris.game;

/**
 * Created by zhenya on 5/29/17.
 */
public class FigureT extends Figure{

    private static final int[][] SHAPE_T = {
            {0, 0, 0, 0},
            {0, Colors.RED, 0, 0},
            {Colors.RED, Colors.RED, Colors.RED, 0},
            {0, 0, 0, 0}
    };


    public FigureT(float posX, float posY) {
        shape = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++)
            System.arraycopy(SHAPE_T[i], 0, shape[i], 0, SHAPE_T[i].length);
        weight = 3;
        height = 2;
        x = START_X;
        y = START_Y;
        POSITION_X = posX;
        POSITION_Y = posY;
        color = Colors.RED;
        createForm();
    }
}
