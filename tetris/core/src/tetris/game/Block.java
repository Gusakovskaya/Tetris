package tetris.game;


import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by zhenya on 5/29/17.
 */
public class Block {
    private static final int BLOCK_SIZE = Config.BLOCK_SIZE;
    private static final int FIELD_HEIGHT = Config.FIELD_HEIGHT;

    private static final ShapeRenderer shapeRenderer = new ShapeRenderer();

    private final float POSITION_X;
    private final float POSITION_Y;

    private int x, y;

    public Block(float position_X, float position_Y, int newX, int newY) {
        x = newX;
        y = newY;
        POSITION_X = position_X;
        POSITION_Y = position_Y;
    }

    void setX(int x) { this.x = x; }
    void setY(int y) { this.y = y; }

    int getX() { return x; }
    int getY() { return y; }

    void paint(int color) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        setColor(color);
        if(x >= 0 && y >= 0)
            shapeRenderer.rect(POSITION_X + (x * BLOCK_SIZE) + 1,
                                POSITION_Y + ((FIELD_HEIGHT - 1 - y) * BLOCK_SIZE) + 1,
                                BLOCK_SIZE - 2,
                                BLOCK_SIZE - 2);
        shapeRenderer.end();
    }

    void setColor(int color){
        switch (color){
            case Colors.RED: {
                shapeRenderer.setColor(com.badlogic.gdx.graphics.Color.RED.r,
                        com.badlogic.gdx.graphics.Color.RED.g,
                        com.badlogic.gdx.graphics.Color.RED.b,
                        com.badlogic.gdx.graphics.Color.RED.a);
                return;
            }
            case Colors.BLUE: {
                shapeRenderer.setColor(com.badlogic.gdx.graphics.Color.BLUE.r,
                        com.badlogic.gdx.graphics.Color.BLUE.g,
                        com.badlogic.gdx.graphics.Color.BLUE.b,
                        com.badlogic.gdx.graphics.Color.BLUE.a);
                return;
            }
            case Colors.GREEN: {
                shapeRenderer.setColor(com.badlogic.gdx.graphics.Color.GREEN.r,
                        com.badlogic.gdx.graphics.Color.GREEN.g,
                        com.badlogic.gdx.graphics.Color.GREEN.b,
                        com.badlogic.gdx.graphics.Color.GREEN.a);
                return;
            }
            case Colors.YELLOW: {
                shapeRenderer.setColor(com.badlogic.gdx.graphics.Color.YELLOW.r,
                        com.badlogic.gdx.graphics.Color.YELLOW.g,
                        com.badlogic.gdx.graphics.Color.YELLOW.b,
                        com.badlogic.gdx.graphics.Color.YELLOW.a);
                return;
            }
            case Colors.ORANGE: {
                shapeRenderer.setColor(com.badlogic.gdx.graphics.Color.ORANGE.r,
                        com.badlogic.gdx.graphics.Color.ORANGE.g,
                        com.badlogic.gdx.graphics.Color.ORANGE.b,
                        com.badlogic.gdx.graphics.Color.ORANGE.a);
                return;
            }
            case Colors.PURPLE: {
                shapeRenderer.setColor(com.badlogic.gdx.graphics.Color.PURPLE.r,
                        com.badlogic.gdx.graphics.Color.PURPLE.g,
                        com.badlogic.gdx.graphics.Color.PURPLE.b,
                        com.badlogic.gdx.graphics.Color.PURPLE.a);
                return;
            }
            case Colors.SILVER: {
                shapeRenderer.setColor(192, 192, 192, 1.0F);
                return;
            }
        }
    }
}
