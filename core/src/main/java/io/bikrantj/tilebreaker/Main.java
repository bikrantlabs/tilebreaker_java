package io.bikrantj.tilebreaker;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import io.bikrantj.tilebreaker.screens.MainMenuScreen;
import io.bikrantj.tilebreaker.utils.AssetManagerWrapper;

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms.
 */
public class Main extends Game {
    public SpriteBatch batch;
    public TextureAtlas atlas;

    @Override
    public void create() {
        try {
            AssetManagerWrapper.loadAssets();
            AssetManagerWrapper.finishLoading();

            batch = new SpriteBatch();
            Skin skin = AssetManagerWrapper.getSkin();

            // Verify everything worked
            System.out.println("Has button? " + skin.has("button", Drawable.class));

            batch = new SpriteBatch();
            atlas = AssetManagerWrapper.getTextureAtlas();

            // Proceed with your game
            setScreen(new MainMenuScreen(this));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        batch.dispose();
        AssetManagerWrapper.dispose();
    }
}
