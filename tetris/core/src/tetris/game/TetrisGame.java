package tetris.game;

import com.badlogic.gdx.Game;

public class TetrisGame extends Game {

	//GameScreen gameScreen;

	@Override
	public void create () {
		//gameScreen = new GameScreen(/*this*/)
		setScreen(new GameScreen());
	}

}
