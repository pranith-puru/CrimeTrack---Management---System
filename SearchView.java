package com.crimetrack;

import java.sql.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class SearchView {
    public static Scene create(Stage stage) {
        BorderPane root = UI.shell(stage, "Search Criminal");

        VBox box = new VBox(18);
        box.setPadding(new Insets(25));
        HBox searchBar = new HBox(10);
        ComboBox<String> type = new ComboBox<>();
        type.getItems().addAll("By Aadhaar No", "By FIR No");
        type.setValue("By Aadhaar No");
        TextField value = new TextField();
        value.setPromptText("Enter value...");
        Button search = new Button("SEARCH");
        search.getStyleClass().add("primary-btn");
        searchBar.getChildren().addAll(type, value, search);

        TextArea result = new TextArea();
        result.setEditable(false);
        result.setPrefHeight(330);

        search.setOnAction(e -> {
            String sql = type.getValue().contains("Aadhaar")
                    ? "SELECT * FROM criminal WHERE aadhaar_no=?"
                    : "SELECT c.* FROM criminal c JOIN fir f ON c.name=f.criminal_name WHERE f.fir_no=?";
            try (Connection con = DBConnection.getConnection()) {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, value.getText());
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    result.setText("Criminal No : " + rs.getString("criminal_no") +
                            "\nName        : " + rs.getString("name") +
                            "\nAlias       : " + rs.getString("alias_name") +
                            "\nAddress     : " + rs.getString("address") +
                            "\nGender      : " + rs.getString("gender") +
                            "\nAadhaar No  : " + rs.getString("aadhaar_no") +
                            "\nImage       : " + rs.getString("image_path"));
                } else result.setText("No record found.");
            } catch(Exception ex) { result.setText(ex.getMessage()); }
        });

        box.getChildren().addAll(searchBar, new Label("Criminal Details"), result);
        root.setCenter(box);
        return new Scene(root, 1000, 620);
    }
}
