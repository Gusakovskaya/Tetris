package tetris.game;

import com.badlogic.gdx.Game;

public class TetrisGame extends Game {

	//GameScreen gameScreen;

	@Override
	public void create () {
		setScreen(new GameScreen());
	}

}
