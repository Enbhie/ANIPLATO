package com.aniplato;

import com.aniplato.util.Navigator;
import com.aniplato.util.Navigator.Screen;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Locale;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Baseline 1280x720 scene; Navigator swaps its root
        Scene scene = new Scene(new Pane(), 1280, 720);
        Navigator.init(scene);
        Navigator.show(Navigator.isPreviewMode() ? previewDashboard() : Screen.PENDING_REGISTRATIONS);

        primaryStage.setTitle("ANIPLATO - Surplus Food Rescue System");
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private static Screen previewDashboard() {
        String role = System.getenv("ANIPLATO_PREVIEW_ROLE");
        return switch (role == null || role.isBlank() ? "admin" : role.trim().toLowerCase(Locale.ROOT)) {
            case "admin" -> Screen.ADMIN_DASHBOARD;
            case "donor" -> Screen.DONOR_DASHBOARD;
            case "foodgroup" -> Screen.FOODGROUP_DASHBOARD;
            default -> throw new IllegalArgumentException(
                    "ANIPLATO_PREVIEW_ROLE must be admin, donor, or foodgroup.");
        };
    }
}