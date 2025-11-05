package io.bikrantj.tilebreaker.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.bikrantj.tilebreaker.Main;
import io.bikrantj.tilebreaker.utils.AssetManagerWrapper;

public class MainMenuScreen extends BaseScreen {
    private final Stage stage;
    private final Skin skin;

    public MainMenuScreen(Main game) {
        super(game);
        this.skin = AssetManagerWrapper.getSkin();

        this.stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Table root = new Table();
//        root.setDebug(true);
        root.setFillParent(true);
        root.center();

        Label title = new Label("TileBreaker", skin, "default");
        title.setFontScale(1.7f);


        TextButton play = new TextButton("Play", skin, "menu");
        TextButton upgrades = new TextButton("Upgrades", skin, "menu");
        TextButton quit = new TextButton("Exit", skin, "menu_quit");

        root.add(title).colspan(2).row();
        root.add(play).width(240).height(50).padTop(32).row();
        root.add(upgrades).width(240).height(50).padTop(16).row();
        root.add(quit).width(240).height(50).padTop(16).row();
//
        stage.addActor(root);
//
        play.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                // switch to GameScreen
//                game.setScreen(new GameScreen(game));

            }
        });


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.15f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        stage.dispose();

    }
}
