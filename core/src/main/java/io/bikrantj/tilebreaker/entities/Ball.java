package io.bikrantj.tilebreaker.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import io.bikrantj.tilebreaker.utils.AssetManagerWrapper;

public class Ball {
    private final Circle bounds;
    private final Vector2 velocity;
    private final Color color;
    private final Drawable ballDrawable;
    private float speed;

    public Ball(float x, float y, float radius, float initialSpeed) {
        this.bounds = new Circle(x, y, radius);
        this.velocity = new Vector2(0, -1).nor();
        this.speed = initialSpeed;
        this.color = Color.WHITE;
        this.ballDrawable = AssetManagerWrapper.getGameDrawable(AssetManagerWrapper.GameRegions.BALL);

    }

    public void update(float delta) {
        // Move incrementally instead of resetting
        bounds.x += velocity.x * speed * delta;
        bounds.y += velocity.y * speed * delta;

        // Reduce speed drastically if it's too fast (try something like 100 or less)
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();

        // Bounce or clamp within screen
        if (bounds.x < 0) bounds.x = 0;
        if (bounds.x + bounds.radius > screenWidth) bounds.x = screenWidth - bounds.radius;

        if (bounds.y < 0) bounds.y = 0;
        if (bounds.y + bounds.radius > screenHeight) bounds.y = screenHeight - bounds.radius;
    }

    public void render(SpriteBatch batch) {
//        height = radius * 2;
        ballDrawable.draw(batch, bounds.x - bounds.radius, bounds.y - bounds.radius, bounds.radius * 2, bounds.radius * 2);
    }
}
