package io.bikrantj.tilebreaker.screens;

import com.badlogic.gdx.ScreenAdapter;
import io.bikrantj.tilebreaker.Main;

public class BaseScreen extends ScreenAdapter {

    protected final Main game;

    public BaseScreen(Main game) {
        this.game = game;
    }

    @Override
    public void dispose() {

    }
}
