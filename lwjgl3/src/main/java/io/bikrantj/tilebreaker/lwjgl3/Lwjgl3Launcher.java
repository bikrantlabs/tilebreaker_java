package io.bikrantj.tilebreaker.lwjgl3;

import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import io.bikrantj.tilebreaker.Main;

/**
 * Launches the desktop (LWJGL3) application.
 */
public class Lwjgl3Launcher {
    public static void main(String[] args) {
        if (StartupHelper.startNewJvmIfRequired()) return; // This handles macOS support and helps on Windows.
        createApplication();
    }

    private static Lwjgl3Application createApplication() {
        return new Lwjgl3Application(new Main(), getDefaultConfiguration());
    }

    private static Lwjgl3ApplicationConfiguration getDefaultConfiguration() {
        Lwjgl3ApplicationConfiguration configuration = new Lwjgl3ApplicationConfiguration();
        configuration.setTitle("tilebreaker");
        configuration.useVsync(true);
        configuration.setForegroundFPS(Lwjgl3ApplicationConfiguration.getDisplayMode().refreshRate + 1);

        // Get all available monitors
        Graphics.Monitor[] monitors = Lwjgl3ApplicationConfiguration.getMonitors();

        if (monitors.length > 1) {
            // Use second monitor (index 1)
            Graphics.Monitor secondMonitor = monitors[1];
            Graphics.DisplayMode displayMode = Lwjgl3ApplicationConfiguration.getDisplayMode(secondMonitor);
            int windowWidth = 1920;
            int windowHeight = 1080;
            // Set window to second monitor's position and size
            configuration.setWindowedMode(windowWidth, windowHeight);
            // Calculate center position on second monitor
            int centerX = secondMonitor.virtualX + (displayMode.width - windowWidth) / 2;
            int centerY = secondMonitor.virtualY + (displayMode.height - windowHeight + 60) / 2;
            configuration.setWindowPosition(centerX, centerY);
            System.out.println("Launching on second monitor: " + secondMonitor.name);
        } else {
            // Fallback to primary monitor
            Graphics.DisplayMode displayMode = Lwjgl3ApplicationConfiguration.getDisplayMode();
            configuration.setWindowedMode(1280, 720);
            System.out.println("Only one monitor detected, using primary monitor");
        }

        configuration.setWindowIcon("libgdx128.png", "libgdx64.png", "libgdx32.png", "libgdx16.png");
        configuration.setOpenGLEmulation(Lwjgl3ApplicationConfiguration.GLEmulation.ANGLE_GLES20, 0, 0);

        return configuration;
    }
}
