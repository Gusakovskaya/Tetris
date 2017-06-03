package tetris.game;

/**
 * Created by zhenya on 5/30/17.
 */
public class FigureZ extends Figure{

    private static final int[][] SHAPE_Z = {
            {0, 0, 0, 0},
            {Colors.ORANGE, Colors.ORANGE, 0, 0},
            {0, Colors.ORANGE, Colors.ORANGE, 0},
            {0, 0, 0, 0}
    };


    public FigureZ(float posX, float posY) {
        shape = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++)
            System.arraycopy(SHAPE_Z[i], 0, shape[i], 0, SHAPE_Z[i].length);
        weight = 3;
        height = 2;
        x = START_X;
        y = START_Y;
        POSITION_X = posX;
        POSITION_Y = posY;
        color = Colors.ORANGE;
        createForm();
    }
}
