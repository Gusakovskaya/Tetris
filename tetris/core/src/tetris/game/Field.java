package tetris.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.awt.event.HierarchyBoundsAdapter;
import java.util.Arrays;

/**
 * Created by zhenya on 5/29/17.
 */
public class Field {
    private static final int BLOCK_SIZE = Config.BLOCK_SIZE;
    protected int WIDTH;
    protected int HEIGHT;

    private final float LOCATION_X;
    private final float LOCATION_Y;

    private int[][] matrix;

    private ShapeRenderer shapeRenderer;
    private Texture blockTexture;
    private SpriteBatch batch;

    private Texture redBlockTexture;
    private Texture greenBlockTexture;
    private Texture yellowBlockTexture;
    private Texture blueBlockTexture;
    private Texture purpleBlockTexture;
    private Texture silverBlockTexture;
    private Texture orangeBlockTexture;


    public Field(float x, float y, int newWight,int newHeight){
        LOCATION_X = x;
        LOCATION_Y = y;
        WIDTH = newWight;
        HEIGHT = newHeight;
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        redBlockTexture = new Texture(Gdx.files.internal("red_Block.png"));
        blueBlockTexture = new Texture(Gdx.files.internal("blue_Block.png"));
        greenBlockTexture = new Texture(Gdx.files.internal("green_Block.png"));
        yellowBlockTexture = new Texture(Gdx.files.internal("yellow_Block.png"));
        purpleBlockTexture = new Texture(Gdx.files.internal("purple_Block.png"));
        silverBlockTexture = new Texture(Gdx.files.internal("silver_Block.png"));
        orangeBlockTexture = new Texture(Gdx.files.internal("orange_Block.png"));
        blockTexture = redBlockTexture;
        matrix = new int[HEIGHT + 1][WIDTH];
        Arrays.fill(matrix[HEIGHT], 1);
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public void draw(){
        drawGrid();
        drawField();
    }

    private void drawGrid() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

        shapeRenderer.setColor(0.23F, 0.423F, 0.764F, 0.5F);
        for (int x = BLOCK_SIZE; x < WIDTH * BLOCK_SIZE; x += BLOCK_SIZE)
        {
            for (int y = BLOCK_SIZE; y < HEIGHT * BLOCK_SIZE; y += BLOCK_SIZE)
            {
                shapeRenderer.line( LOCATION_X + (float) x,
                                    LOCATION_Y + (float)(y - 2),
                                    LOCATION_X +(float) x,
                                    LOCATION_Y + (float)(y + 2));
                shapeRenderer.line(LOCATION_X + (float) (x - 2),
                                    LOCATION_Y + (float) y,
                                    LOCATION_X + (float) (x + 2),
                                    LOCATION_Y + (float) y);
            }
        }

        shapeRenderer.line(LOCATION_X + WIDTH * BLOCK_SIZE,
                            LOCATION_Y,
                            LOCATION_X + WIDTH * BLOCK_SIZE,
                            LOCATION_Y + HEIGHT * BLOCK_SIZE);
        shapeRenderer.line(LOCATION_X + 0.01F,
                            LOCATION_Y,
                            LOCATION_X + 0.01F,
                            LOCATION_Y + HEIGHT * BLOCK_SIZE);
        shapeRenderer.line(LOCATION_X,
                            LOCATION_Y,
                            LOCATION_X + WIDTH * BLOCK_SIZE,
                            LOCATION_Y);
        shapeRenderer.line(LOCATION_X,
                            LOCATION_Y + HEIGHT * BLOCK_SIZE - 1,
                            LOCATION_X +WIDTH * BLOCK_SIZE,
                            LOCATION_Y + HEIGHT * BLOCK_SIZE - 1);

        shapeRenderer.end();
    }

    public void drawField() {
        batch.begin();
        for (int x = 0; x < WIDTH; x++)
            for (int y = 0; y < HEIGHT; y++) {
                if (matrix[y][x] > 0) {
                    chooseBlockTexture(matrix[y][x]);
                    batch.draw(blockTexture,
                            LOCATION_X + (x * BLOCK_SIZE),
                            LOCATION_Y + (HEIGHT - 1 - y) * BLOCK_SIZE);
                }
            }
        batch.end();
    }

    public void clear(){
        for (int i = 0; i < HEIGHT; i++)
            for (int j = 0; j < WIDTH; j++)
                matrix[i][j] = 0;
    }

    public void chooseBlockTexture(int value){
        switch (value){
            case Colors.BLUE:{
                blockTexture = blueBlockTexture;
                return;
            }
            case Colors.RED:{
                blockTexture = redBlockTexture;
                return;
            }
            case Colors.GREEN:{
                blockTexture = greenBlockTexture;
                return;
            }
            case Colors.ORANGE:{
                blockTexture = orangeBlockTexture;
                return;
            }
            case Colors.PURPLE:{
                blockTexture = purpleBlockTexture;
                return;
            }
            case Colors.SILVER:{
                blockTexture = silverBlockTexture;
                return;
            }
            case Colors.YELLOW:{
                blockTexture = yellowBlockTexture;
                return;
            }
        }
    }

    public void moveRight(){
        int buffer;
        for (int i = 0; i < HEIGHT; i++) {
            buffer = matrix[i][0];
            System.arraycopy(matrix[i],1, matrix[i], 0, WIDTH -1);
            matrix[i][WIDTH - 1] = buffer;
        }
    }

    public void moveLeft(){
        int buffer;
        for (int i = 0; i < HEIGHT; i++) {
            buffer = matrix[i][WIDTH - 1];
            System.arraycopy(matrix[i],0, matrix[i], 1, WIDTH -1);
            matrix[i][0] = buffer;
        }
    }

}
