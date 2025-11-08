package io.bikrantj.tilebreaker.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class InputManager {
    public static KeyInput keyInput = KeyInput.NONE; // Initialize with NONE

    public static void processInput() {
        // Reset to NONE first
        keyInput = KeyInput.NONE;

        // Check for key presses (handle both keys pressed - right takes priority)
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            keyInput = KeyInput.RIGHT;
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            keyInput = KeyInput.LEFT;
        }
    }

    // Optional: Add method to check specific key
    public static boolean isRightPressed() {
        return keyInput == KeyInput.RIGHT;
    }

    public static boolean isLeftPressed() {
        return keyInput == KeyInput.LEFT;
    }
}
