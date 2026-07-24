package com.crimetrack;

import java.sql.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class LoginView {
    public static Scene create(Stage stage) {
        VBox root = new VBox(18);
        root.setAlignment(Pos.CENTER);
        root.getStyleClass().add("login-root");
        root.getStylesheets().add(LoginView.class.getResource("/style.css").toExternalForm());

        Label brand = new Label("🛡 CRIMETRACK");
        brand.getStyleClass().add("brand");
        Label sub = new Label("Crime Management System");
        sub.getStyleClass().add("subtitle");

        VBox card = new VBox(15);
        card.setAlignment(Pos.CENTER);
        card.getStyleClass().add("login-card");
        card.setMaxWidth(360);

        Label loginText = new Label("Login to Your Account");
        loginText.getStyleClass().add("login-title");

        TextField user = new TextField();
        user.setPromptText("Username");
        PasswordField pass = new PasswordField();
        pass.setPromptText("Password");

        Button login = new Button("LOGIN  →");
        login.getStyleClass().add("primary-btn");

        login.setOnAction(e -> {
            try (Connection con = DBConnection.getConnection()) {
                PreparedStatement ps = con.prepareStatement("SELECT * FROM login WHERE username=? AND password=?");
                ps.setString(1, user.getText());
                ps.setString(2, pass.getText());
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    stage.setTitle("CrimeTrack - Dashboard");
                    stage.setScene(DashboardView.create(stage));
                } else {
                    UI.alert("Invalid username or password");
                }
            } catch (Exception ex) {
                UI.alert("Database error: " + ex.getMessage());
            }
        });

        card.getChildren().addAll(loginText, user, pass, login);
        root.getChildren().addAll(brand, sub, card, new Label("© 2026 CrimeTrack System"));
        return new Scene(root, 900, 560);
    }
}
