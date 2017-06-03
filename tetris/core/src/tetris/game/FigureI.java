package tetris.game;

/**
 * Created by zhenya on 5/30/17.
 */
public class FigureI extends Figure{

    private static final int[][] SHAPE_I = {
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {Colors.SILVER, Colors.SILVER, Colors.SILVER, Colors.SILVER},
            {0, 0, 0, 0}
    };


    public FigureI(float posX, float posY) {
        shape = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++)
            System.arraycopy(SHAPE_I[i], 0, shape[i], 0, SHAPE_I[i].length);
        weight = 4;
        height = 1;
        x = START_X;
        y = START_Y;
        POSITION_X = posX;
        POSITION_Y = posY;
        color = Colors.SILVER;
        createForm();
    }
}
