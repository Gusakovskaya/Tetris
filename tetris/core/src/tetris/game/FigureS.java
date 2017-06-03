package tetris.game;

/**
 * Created by zhenya on 5/30/17.
 */
public class FigureS extends Figure{

    private static final int[][] SHAPE_S = {
            {0, 0, 0, 0},
            {0, Colors.YELLOW, Colors.YELLOW, 0},
            {Colors.YELLOW, Colors.YELLOW, 0, 0},
            {0, 0, 0, 0}
    };


    public FigureS(float posX, float posY) {
        shape = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++)
            System.arraycopy(SHAPE_S[i], 0, shape[i], 0, SHAPE_S[i].length);
        weight = 3;
        height = 2;
        x = START_X;
        y = START_Y;
        POSITION_X = posX;
        POSITION_Y = posY;
        color = Colors.YELLOW;
        createForm();
    }
}
