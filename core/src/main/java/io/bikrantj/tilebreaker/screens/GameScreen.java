package io.bikrantj.tilebreaker.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;
import io.bikrantj.tilebreaker.Main;
import io.bikrantj.tilebreaker.entities.Ball;
import io.bikrantj.tilebreaker.entities.Paddle;
import io.bikrantj.tilebreaker.utils.AssetManagerWrapper;
import io.bikrantj.tilebreaker.utils.InputManager;

public class GameScreen extends BaseScreen {
    private final Ball ball;
    private final Paddle paddle;
    private final SpriteBatch spriteBatch;

    private final Stage uiStage;
    private final Skin skin;

    public GameScreen(Main game) {
        super(game);

        spriteBatch = new SpriteBatch();

        // 2️⃣ Setup UI Stage (Scene2D)
        skin = AssetManagerWrapper.getSkin();
        uiStage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        Gdx.input.setInputProcessor(uiStage); // (optional, for UI input)

        // Example HUD element
        Label scoreLabel = new Label("Score: 0", skin);
        scoreLabel.setPosition(10, Gdx.graphics.getHeight() - 30);
        uiStage.addActor(scoreLabel);

        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();

        // 3️⃣ Create gameplay entities
        ball = new Ball(screenWidth / 2, screenHeight / 2, 16, 250);
        paddle = new Paddle(screenWidth / 2 + 50, 64, 128, 32, 350);
    }


    @Override
    public void render(float delta) {
        InputManager.processInput();
        // Clear screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Update ball and camera
        ball.update(delta);
        paddle.update(delta);

        spriteBatch.begin();
        ball.render(spriteBatch);
        paddle.render(spriteBatch);
        spriteBatch.end();
        // 2️⃣ Draw UI Stage (HUD)
        uiStage.act(delta);
        uiStage.draw();
    }

    @Override
    public void resize(int width, int height) {
//        camera.setToOrtho(false, width, height);
        uiStage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
        uiStage.dispose();
    }
}
