package tetris.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

/**
 * Created by zhenya on 5/30/17.
 */
public class InputHandler implements InputProcessor {

    private float pastMoveTime;

    public boolean keyDown (int keycode){
        if ((keycode == Input.Keys.LEFT || keycode == Input.Keys.RIGHT) && GameScreen.getSTATE().equals("PLAY"))
            Logic.move(keycode);
        if (keycode == Input.Keys.UP && GameScreen.getSTATE().equals("PLAY"))
            Logic.rotateFigure(Logic.getField(),Logic.getCurrentFigure());
        if (keycode == Input.Keys.DOWN){
            pastMoveTime = Logic.getMoveTime();
            Logic.setMoveTime(0.05F);
        }
        if (keycode == Input.Keys.SPACE){
            if (GameScreen.getSTATE().equals("PAUSE"))
                GameScreen.setSTATE("PLAY");
            else {
                if (GameScreen.getSTATE().equals("PLAY"))
                    GameScreen.setSTATE("PAUSE");
            }
        }
        return true;
    }

    public boolean keyUp (int keycode){
        if (keycode == Input.Keys.DOWN)
            Logic.setMoveTime(pastMoveTime);
        return true;
    }

    public boolean keyTyped (char character){
        return true;
    }

    public boolean touchDown (int screenX, int screenY, int pointer, int button) {
        return true;
    }

    public boolean touchUp (int screenX, int screenY, int pointer, int button){
        return true;
    }

    public boolean touchDragged (int screenX, int screenY, int pointer){
        return true;
    }

    public boolean mouseMoved (int screenX, int screenY){
        return true;
    }

    public boolean scrolled (int amount){
        return true;
    }
}
