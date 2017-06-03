package tetris.game;
import com.badlogic.gdx.Input;

/**
 * Created by zhenya on 5/30/17.
 */
public class Logic {

    protected static float FIELD_POSITION_X = Config.FIELD_POSITION_X;
    protected static float FIELD_POSITION_Y = Config.FIELD_POSITION_Y;
    protected static float SMALL_FIELD_POSITION_X = Config.SMALL_FIELD_POSITION_X;
    protected static float SMALL_FIELD_POSITION_Y = Config.SMALL_FIELD_POSITION_Y;

    private static float START_MOVE_TIME = Config.START_MOVE_TIME;
    private static float MOVE_DELTA = Config.MOVE_DELTA;
    private static float MIN_MOVE_TIME = Config.MIN_MOVE_TIME;
    private static float MOVE_TIME = START_MOVE_TIME;
    private static float timer = START_MOVE_TIME;

    private static Field field = new Field(FIELD_POSITION_X, FIELD_POSITION_Y, Config.FIELD_WIDTH, Config.FIELD_HEIGHT);
    private static Field smallField = new Field(SMALL_FIELD_POSITION_X, SMALL_FIELD_POSITION_Y, 4, 4);
    private static Figure currentFigure = Figure.createFigure(FIELD_POSITION_X, FIELD_POSITION_Y);
    private static Figure nextFigure = createNewFigure(FIELD_POSITION_X, FIELD_POSITION_Y);

    public static Field getField() {
        return field;
    }

    public static Figure getCurrentFigure() {
        return currentFigure;
    }

    public static float getMoveTime() {
        return MOVE_TIME;
    }

    public static void setMoveTime(float moveTime) {
        MOVE_TIME = moveTime;
    }

    public static void drop(Field field, Figure figure) {
        for (int i = 0; i < figure.getSIZE(); i++)
            System.arraycopy(figure.getShape()[i], 0, field.getMatrix()[i], 0, figure.getShape()[i].length);
    }

    public static Figure createNewFigure(float x, float y){
        Figure figure = Figure.createFigure(x, y);
        drop(smallField, figure);
        return figure;
    }

    public static void rotateFigure(Field field, Figure figure) {
        currentFigure.rotateLeft();
        if (!isWrongPosition(field, figure)) {
            currentFigure.getList_of_Bloks().clear();
            currentFigure.createForm();
        }
        else
            currentFigure.rotateRight();
    }

    public static void move(int direction) {
        if (!isTouchWall(direction, field, currentFigure)) {
            int dx = 0;
            if (direction == Input.Keys.LEFT)
                dx = -1;
            if (direction == Input.Keys.RIGHT)
                dx = 1;
            for (Block block : currentFigure.getList_of_Bloks())
                block.setX(block.getX() + dx);
            currentFigure.setX(currentFigure.getX() + dx);
        }
    }

    public static void update(float delta){
        timer -= delta;
        if (timer <= 0){
            timer = MOVE_TIME;
            checkFilling(field);
            stepDown(field, currentFigure);
            System.gc();
        }
    }

    public static void stepDown(Field field, Figure figure){
        if (isTouchGround(field, figure))
            if (isGameOver(figure))
                GameScreen.setSTATE("GAMEOVER");
            else
                updateFieldAndFigure(field, figure);
        else
            figure.stepDown();
    }

    public static void draw() {
        field.draw();
        currentFigure.draw();
        smallField.drawField();
    }

    public static boolean isGameOver(Figure figure){
        boolean flagNeg = false;
        boolean flagPos = false;
        for (Block block: figure.getList_of_Bloks()) {
            if (block.getY() < 0)
                return true;
                //flagNeg = true;
            //if (block.getY () >= 0)
            //    if (flagNeg && (field.getMatrix()[block.getY() + 1][block.getX()] != 0))
            //        return true;
            //    else
            //        flagPos = true;
        }
        //if (flagNeg)
        //    return true;
        return false;
    }

    public static void checkFilling(Field field) {
        int row = field.getHEIGHT() - 1;
        boolean wasFilled = false;
        while (row > 0) {
            int filled = 1;
            for (int col = 0; col < field.getWIDTH(); col++)
                filled *= Integer.signum(field.getMatrix()[row][col]);
            if (filled > 0) {
                wasFilled = true;
                for (int i = row; i > 0; i--)
                    System.arraycopy(field.getMatrix()[i - 1], 0,
                            field.getMatrix()[i], 0,
                            field.getWIDTH());
            }
            else
                row--;
        }
        if (wasFilled) {
            MOVE_TIME -= MOVE_DELTA;
            if (MOVE_TIME < MIN_MOVE_TIME)
                MOVE_TIME = MIN_MOVE_TIME;
        }
    }

    public static void leaveOnTheGround(Field field, Figure figure) {
        for (Block block : figure.getList_of_Bloks())
            field.getMatrix()[block.getY()][block.getX()] = figure.getColor();
    }

    public static void changeCurrentFigure(){
        currentFigure = nextFigure;
        nextFigure = createNewFigure(FIELD_POSITION_X, FIELD_POSITION_Y);
    }

    public static void updateFieldAndFigure(Field field, Figure figure){
        leaveOnTheGround(field, figure);
        changeCurrentFigure();
    }


    public static boolean isTouchGround(Field field, Figure figure) {
        for (Block block : figure.getList_of_Bloks())
            if (block.getY() >= -1 && (field.getMatrix()[block.getY() + 1][block.getX()] != 0))
                return true;
        return false;
    }

    public static boolean isWrongPosition(Field field, Figure figure) {
        for (int y = 0; y < figure.getSIZE(); y++)
            for (int x = 0; x < figure.getSIZE(); x++)
                if (figure.getShape()[y][x] > 1) {
                    if (y + figure.y < 0)
                        return true;
                    if (x + figure.x < 0 || x + figure.x > field.getWIDTH() - 1)
                        return true;
                    if (field.getMatrix()[y + figure.y][x + figure.x] > 0)
                        return true;
                }
        return false;
    }

    public static boolean isTouchWall(int key, Field field, Figure figure) {
        for (Block block : figure.getList_of_Bloks()) {
            if (key == Input.Keys.LEFT &&
                    (block.getX() == 0 ||
                        (block.getY() >= 0 &&
                            field.getMatrix()[block.getY()][block.getX() - 1] > 0)))
                return true;
            if (key == Input.Keys.RIGHT &&
                    (block.getX() == field.getWIDTH() - 1 ||
                        (block.getY() >= 0 &&
                            field.getMatrix()[block.getY()][block.getX() + 1] > 0)))

                return true;
        }
        return false;
    }
}

