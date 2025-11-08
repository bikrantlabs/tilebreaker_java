package io.bikrantj.tilebreaker.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import io.bikrantj.tilebreaker.utils.AssetManagerWrapper;
import io.bikrantj.tilebreaker.utils.InputManager;

public class Paddle {
    public float x, y, width, height, speed;
    private Drawable paddleTexture;

    public Paddle(float x, float y, float width, float height, float speed) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;

        paddleTexture = AssetManagerWrapper.getGameDrawable(AssetManagerWrapper.GameRegions.PADDLE);
    }

    public void update(float delta) {
        if (InputManager.isRightPressed()) {
            x += speed * delta;
            System.out.println(("X is: " + x));
        } else if (InputManager.isLeftPressed()) {
            System.out.println("Left pressed");
            x -= speed * delta;
        }

    }

    public void render(SpriteBatch batch) {
        paddleTexture.draw(batch, x, y, width, height);

    }
}
