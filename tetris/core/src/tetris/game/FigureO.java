package tetris.game;

/**
 * Created by zhenya on 5/30/17.
 */
public class FigureO extends Figure{

    private static final int[][] SHAPE_O = {
            {0, 0, 0, 0},
            {0, Colors.PURPLE, Colors.PURPLE, 0},
            {0, Colors.PURPLE, Colors.PURPLE, 0},
            {0, 0, 0, 0}
    };


    public FigureO(float posX, float posY) {
        shape = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++)
            System.arraycopy(SHAPE_O[i], 0, shape[i], 0, SHAPE_O[i].length);
        weight = 2;
        height = 2;
        x = START_X;
        y = START_Y;
        POSITION_X = posX;
        POSITION_Y = posY;
        color = Colors.PURPLE;
        createForm();
    }

    @Override
    public void rotateLeft(){

    }

    @Override
    public void rotateRight(){

    }

}
