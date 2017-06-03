package tetris.game;

import com.badlogic.gdx.math.MathUtils;

import java.util.ArrayList;

/**
 * Created by zhenya on 5/29/17.
 */
public class Figure {
    protected static final int SIZE = 4;
    protected static int START_X = 3;
    protected static int START_Y = -3;

    protected static float POSITION_X;
    protected static float POSITION_Y;

    protected ArrayList<Block> list_of_Bloks;
    protected int[][] shape;
    protected int weight, height;
    protected int x, y;     //x-горизонталь
    protected int color;

    public Figure(){
        list_of_Bloks =  new ArrayList<Block>();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getColor() {
        return color;
    }

    public int getSIZE() {
        return SIZE;
    }

    public int[][] getShape() {
        return shape;
    }

    public ArrayList<Block> getList_of_Bloks() {
        return list_of_Bloks;
    }


    public static Figure createFigure(float posX, float posY){
        int type = MathUtils.random(6) + 1;
        switch (type){
            case Colors.BLUE:{
                return new FigureL(posX, posY);
            }
            case Colors.RED:{
                return new FigureT(posX, posY);
            }
            case Colors.GREEN:{
                return new FigureJ(posX, posY);
            }
            case Colors.ORANGE:{
                return new FigureZ(posX, posY);
            }
            case Colors.PURPLE:{
                return new FigureO(posX, posY);
            }
            case Colors.SILVER:{
                return new FigureI(posX, posY);
            }
            case Colors.YELLOW:{
                return new FigureS(posX, posY);
            }
        }
        return new Figure();
    }


    public void rotateLeft(){
        int size = Math.max(height, weight);
        int item;
        for (int i = 0; i < size/2; i++)
            for (int j = i; j < size - 1 - i; j++){
                item = shape[i][j];
                shape[i][j] = shape[j][size - 1 - i];
                shape[j][size - 1 - i] = shape[size - 1 - i][size - 1 - j];
                shape[size - 1 - i][size - 1 - j] = shape[size - 1 - j][i];
                shape[size - 1 - j][i] = item;
            }
        int hi = weight;
        weight = height;
        height = hi;
    }

    public void rotateRight(){
        int size = Math.max(height, weight);
        int item;
        for (int i = 0; i < size/2; i++)
            for (int j = i; j < size - 1 - i; j++){
                item = shape[size - 1 - j][i];
                shape[size - 1 - j][i]  = shape[size - 1 - i][size - 1 - j];
                shape[size - 1 - i][size - 1 - j] = shape[j][size - 1 - i];
                shape[j][size - 1 - i] = shape[i][j];
                shape[i][j] = item;
            }
        int hi = weight;
        weight = height;
        height = hi;
    }

    public void stepDown(){
        for (Block block : list_of_Bloks)
            block.setY(block.getY() + 1);
        y++;
    }

    void createForm() {
        for (int x = 0; x < SIZE; x++)
            for (int y = 0; y < SIZE; y++)
                if (shape[y][x] > 0 )
                    list_of_Bloks.add(new Block(POSITION_X,
                                                POSITION_Y,
                                                x + this.x,
                                                y + this.y));
    }

    void draw() {
        for (Block block : list_of_Bloks)
            block.paint(color);
    }

}
