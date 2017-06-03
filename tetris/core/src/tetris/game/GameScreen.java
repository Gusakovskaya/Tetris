package tetris.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;

/**
 * Created by zhenya on 5/28/17.
 */
public class GameScreen extends ScreenAdapter {

    private static final float FIELD_POSITION_X = 15;
    private static final float FIELD_POSITION_Y = 15;

    @Override
    public void show(){
        Config.FIELD_POSITION_X = FIELD_POSITION_X;
        Config.FIELD_POSITION_Y = FIELD_POSITION_Y;
        InputHandler ih = new InputHandler();
        Gdx.input.setInputProcessor(ih);
    }

    @Override
    public void render(float delta) {
        clearScreen();
        Logic.update(delta);
        Logic.draw();
    }

    private void clearScreen() {
        Gdx.gl.glClearColor(Color.BLACK.r, Color.BLACK.g, Color.BLACK.b, Color.BLACK.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

}
