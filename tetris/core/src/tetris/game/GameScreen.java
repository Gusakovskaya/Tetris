package tetris.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import javax.swing.*;
import javax.swing.text.Style;

/**
 * Created by zhenya on 5/28/17.
 */
public class GameScreen extends ScreenAdapter {

    private static final float FIELD_POSITION_X = 15;
    private static final float FIELD_POSITION_Y = 15;

    private static String STATE = "START";


    public static String getSTATE() {
        return STATE;
    }

    public static void setSTATE(String STATE) {
        GameScreen.STATE = STATE;
    }

    private Stage stage = new Stage();
    private TextButton startButton, pauseButton, exitButton, resultButton;
    private Skin checkbox_skin;
    private CheckBox checkBoxHard;
    private Label nextLabel;
    private Label pointLabel;
    private Label linesLebel;
    private Skin button_skin;
    private Skin label_skin;

    private Texture button_background_texture = new Texture(Gdx.files.internal("button_background.png"));
    private Texture checkbox_off_texture = new Texture(Gdx.files.internal("checkboxOff.png"));
    private Texture checkbox_on_texture = new Texture(Gdx.files.internal("checkboxOn.png"));

    @Override
    public void show(){
        createButtonSkin();
        createLabelSkin();
        createCheckboxSkin();
        createStartButton();
        createPauseButton();
        createExitButton();
        createResultButton();
        createCheckbox();
        createLabels();
        Config.FIELD_POSITION_X = FIELD_POSITION_X;
        Config.FIELD_POSITION_Y = FIELD_POSITION_Y;
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        Gdx.input.setInputProcessor(inputMultiplexer);
        InputHandler ih = new InputHandler();
        inputMultiplexer.addProcessor(stage);
        inputMultiplexer.addProcessor(ih);


        stage.addActor(startButton);
        stage.addActor(pauseButton);
        stage.addActor(exitButton);
        stage.addActor(nextLabel);
        stage.addActor(pointLabel);
        stage.addActor(linesLebel);
        stage.addActor(resultButton);
        stage.addActor(checkBoxHard);
    }

    @Override
    public void render(float delta) {
        clearScreen();
        if (STATE.equals("PLAY"))
            Logic.update(delta);
        Logic.draw();

        pointLabel.setText(String.format("Points: %1$d", Logic.points));
        linesLebel.setText(String.format("Lines: %1$d", Logic.lines));
        stage.act();
        stage.draw();
    }

    private void clearScreen() {
        Gdx.gl.glClearColor(Color.BLACK.r, Color.BLACK.g, Color.BLACK.b, Color.BLACK.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }


    private void createButtonSkin() {
        BitmapFont font = new BitmapFont();
        button_skin = new Skin();
        button_skin.add("font", font);

        button_skin.add("background", button_background_texture);

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.fontColor = Color.WHITE;
        textButtonStyle.up = button_skin.newDrawable("background");
        textButtonStyle.down = button_skin.newDrawable("background", Color.GRAY);
        textButtonStyle.over = button_skin.newDrawable("background", Color.GRAY);
        textButtonStyle.font = button_skin.getFont("font");
        button_skin.add("default", textButtonStyle);
    }

    private void createLabelSkin() {
        BitmapFont font = new BitmapFont();
        label_skin = new Skin();
        label_skin.add("font", font);

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = label_skin.getFont("font");
        labelStyle.fontColor = Color.WHITE; //new Color(0.23F, 0.423F, 0.764F, 1F);
        label_skin.add("default", labelStyle);
    }

    private void createStartButton() {
        startButton = new TextButton("Start", button_skin);
        startButton.setPosition(300, 210);
        startButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (STATE.equals("START") || STATE.equals("GAMEOVER"))
                    Logic.startNewGame();
                STATE = "PLAY";
            }
        });
    }

    private void createPauseButton(){
        pauseButton = new TextButton("Pause", button_skin);
        pauseButton.setPosition(300, 145);
        pauseButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                STATE = "Pause";
            }
        });
    }

    private void createExitButton(){
        exitButton = new TextButton("Exit", button_skin);
        exitButton.setPosition(300, 15);
        exitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });
    }

    private void createLabels() {
        nextLabel = new Label("Next:", label_skin);
        nextLabel.setPosition(300, 440);

        pointLabel = new Label(String.format("Points: %1$d", Logic.points), label_skin);
        pointLabel.setPosition(300, 300);

        linesLebel =  new Label(String.format("Lines: %1$d", Logic.lines), label_skin);
        linesLebel.setPosition(300, 320);
    }

    private void createResultButton() {
        resultButton = new TextButton("Records", button_skin);
        resultButton.setPosition(300, 80);
        resultButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                JOptionPane.showMessageDialog(null, NetworkManager.getRecords(), "Records", JOptionPane.PLAIN_MESSAGE);
            }
        });
    }

    private void createCheckboxSkin() {

        BitmapFont font = new BitmapFont();
        checkbox_skin = new Skin();
        checkbox_skin.add("font", font);
        checkbox_skin.add("checkboxOff", checkbox_off_texture);
        checkbox_skin.add("checkboxOn", checkbox_on_texture);

        CheckBox.CheckBoxStyle checkBoxStyleStyle = new CheckBox.CheckBoxStyle();
        checkBoxStyleStyle.font = checkbox_skin.getFont("font");

        checkBoxStyleStyle.fontColor = new Color(Color.WHITE);
        checkBoxStyleStyle.checkboxOff = checkbox_skin.getDrawable("checkboxOff");
        checkBoxStyleStyle.checkboxOn = checkbox_skin.getDrawable("checkboxOn");

        checkbox_skin.add("default", checkBoxStyleStyle);
    }

    private void createCheckbox() {
        checkBoxHard = new CheckBox(" Hard Mode", checkbox_skin);
        checkBoxHard.setPosition(300, 270);
        checkBoxHard.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Logic.hardMode = checkBoxHard.isChecked();
            }
        });
    }
}
