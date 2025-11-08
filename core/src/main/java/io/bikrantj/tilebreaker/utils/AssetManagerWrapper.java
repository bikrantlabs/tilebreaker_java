package io.bikrantj.tilebreaker.utils;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class AssetManagerWrapper {
    public static final String GAME_ATLAS = "game.atlas";
    public static final String UI_SKIN_ATLAS = "uiskin.atlas";
    public static final String UI_SKIN_JSON = "uiskin.json";

    private static final AssetManager manager = new AssetManager();
    private static Skin uiSkin; // Cache the skin instance

    public static void loadAssets() {
        // Load texture atlases
        manager.load(GAME_ATLAS, TextureAtlas.class);
        manager.load(UI_SKIN_ATLAS, TextureAtlas.class);

    }

    public static void finishLoading() {
        manager.finishLoading();

        // Create and setup the skin after assets are loaded
        setupSkin();
    }

    private static void setupSkin() {
        if (uiSkin != null) return; // Only setup once

        try {
            // Load the UI atlas
            TextureAtlas uiAtlas = manager.get(UI_SKIN_ATLAS, TextureAtlas.class);

            // Create skin and manually register all regions (Solution 3)
            uiSkin = new Skin();

            // Manually register ALL regions as drawables
            for (TextureAtlas.AtlasRegion region : uiAtlas.getRegions()) {
                uiSkin.add(region.name, new TextureRegionDrawable(region), Drawable.class);
                System.out.println("Registered UI drawable: " + region.name);
            }

            // Load the JSON skin definition
            uiSkin.load(manager.getFileHandleResolver().resolve(UI_SKIN_JSON));


//            Font Scaling
            uiSkin.get("menu", TextButton.TextButtonStyle.class).font.getData()
                .setScale(0.7f);
            System.out.println("Skin setup completed successfully!");

        } catch (Exception e) {
            System.err.println("Error setting up skin: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static AssetManager getManager() {
        return manager;
    }

    public static TextureAtlas getTextureAtlas() {
        return manager.get(GAME_ATLAS, TextureAtlas.class);
    }

    public static Drawable getGameDrawable(GameRegions gameRegion) {
        TextureAtlas atlas = getTextureAtlas();
        return new TextureRegionDrawable(atlas.findRegion(gameRegion.name));

    }

    public static TextureAtlas getUiAtlas() {
        return manager.get(UI_SKIN_ATLAS, TextureAtlas.class);
    }

    public static Skin getSkin() {
        if (uiSkin == null) {
            // If skin hasn't been setup yet, do it now
            setupSkin();
        }
        return uiSkin;
    }

    public static void dispose() {
        if (uiSkin != null) {
            uiSkin.dispose();
            uiSkin = null;
        }
        manager.dispose();
    }

    public enum GameRegions {
        PADDLE("paddle"),
        BALL("ball"),
        BRICK("brick_normal"),
        POWERUP("powerup_expand");

        public final String name;

        GameRegions(String name) {
            this.name = name;
        }
    }
}
