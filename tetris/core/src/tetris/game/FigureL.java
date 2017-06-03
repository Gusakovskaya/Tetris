package tetris.game;

/**
 * Created by zhenya on 5/30/17.
 */
public class FigureL extends Figure{

    private static final int[][] SHAPE_L = {
            {0, Colors.BLUE, 0, 0},
            {0, Colors.BLUE, 0, 0},
            {0, Colors.BLUE, Colors.BLUE, 0},
            {0, 0, 0, 0}
    };


    public FigureL(float posX, float posY) {
        shape = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++)
            System.arraycopy(SHAPE_L[i], 0, shape[i], 0, SHAPE_L[i].length);
        weight = 2;
        height = 3;
        x = START_X;
        y = START_Y;
        POSITION_X = posX;
        POSITION_Y = posY;
        color = Colors.BLUE;
        createForm();
    }
}
