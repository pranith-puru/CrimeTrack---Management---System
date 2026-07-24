package com.crimetrack;

import java.sql.*;
import javafx.collections.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class ViewCriminalsView {
    public static Scene create(Stage stage) {
        BorderPane root = UI.shell(stage, "View All Criminals");
        VBox box = new VBox(15);
        box.setPadding(new Insets(25));

        TextField search = new TextField();
        search.setPromptText("Search by name...");
        TableView<Criminal> table = table(load(""));

        search.textProperty().addListener((obs, old, val) -> table.setItems(load(val)));

        box.getChildren().addAll(search, table);
        root.setCenter(box);
        return new Scene(root, 1000, 620);
    }

    private static TableView<Criminal> table(ObservableList<Criminal> data) {
        TableView<Criminal> t = new TableView<>();
        TableColumn<Criminal, String> no = col("Criminal No", "criminalNo", 120);
        TableColumn<Criminal, String> name = col("Name", "name", 150);
        TableColumn<Criminal, String> alias = col("Alias", "aliasName", 120);
        TableColumn<Criminal, String> aadhaar = col("Aadhaar No", "aadhaarNo", 160);
        TableColumn<Criminal, String> gender = col("Gender", "gender", 100);
        t.getColumns().addAll(no, name, alias, aadhaar, gender);
        t.setItems(data);
        t.setPrefHeight(450);
        return t;
    }

    private static TableColumn<Criminal, String> col(String title, String prop, int width) {
        TableColumn<Criminal, String> c = new TableColumn<>(title);
        c.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>(prop));
        c.setPrefWidth(width);
        return c;
    }

    private static ObservableList<Criminal> load(String key) {
        ObservableList<Criminal> list = FXCollections.observableArrayList();
        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM criminal WHERE name LIKE ?");
            ps.setString(1, "%" + key + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Criminal(rs.getInt("criminal_id"), rs.getString("criminal_no"), rs.getString("name"),
                        rs.getString("alias_name"), rs.getString("address"), rs.getString("gender"),
                        rs.getString("aadhaar_no"), rs.getString("image_path")));
            }
        } catch(Exception ignored) {}
        return list;
    }
}
