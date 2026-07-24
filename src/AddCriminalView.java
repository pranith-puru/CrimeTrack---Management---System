package com.crimetrack;

import java.io.File;
import java.sql.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class AddCriminalView {
    static String imagePath = "";

    public static Scene create(Stage stage) {
        BorderPane root = UI.shell(stage, "Add New Criminal");
        GridPane form = new GridPane();
        form.setPadding(new Insets(25));
        form.setHgap(15);
        form.setVgap(14);
        form.getStyleClass().add("form-card");

        TextField name = new TextField();
        TextField alias = new TextField();
        TextField address = new TextField();
        ComboBox<String> gender = new ComboBox<>();
        gender.getItems().addAll("Male", "Female", "Other");
        TextField aadhaar = new TextField();
        TextField img = new TextField();
        img.setEditable(false);

        Button choose = new Button("Choose Image");
        choose.setOnAction(e -> {
            FileChooser fc = new FileChooser();
            File f = fc.showOpenDialog(stage);
            if (f != null) {
                imagePath = f.getAbsolutePath();
                img.setText(f.getName());
            }
        });

        Button save = new Button("SAVE");
        save.getStyleClass().add("primary-btn");
        save.setOnAction(e -> {
            try (Connection con = DBConnection.getConnection()) {
                PreparedStatement ps = con.prepareStatement(
                        "INSERT INTO criminal(criminal_no,name,alias_name,address,gender,aadhaar_no,image_path) VALUES(?,?,?,?,?,?,?)");
                String no = "CR" + System.currentTimeMillis()%100000;
                ps.setString(1, no);
                ps.setString(2, name.getText());
                ps.setString(3, alias.getText());
                ps.setString(4, address.getText());
                ps.setString(5, gender.getValue());
                ps.setString(6, aadhaar.getText());
                ps.setString(7, imagePath);
                ps.executeUpdate();
                UI.alert("Criminal record saved successfully");
            } catch(Exception ex) {
                UI.alert(ex.getMessage());
            }
        });

        addRow(form, 0, "Name *", name);
        addRow(form, 1, "Alias", alias);
        addRow(form, 2, "Address *", address);
        addRow(form, 3, "Gender *", gender);
        addRow(form, 4, "Aadhaar No *", aadhaar);
        form.add(new Label("Image"), 0, 5);
        form.add(new HBox(10, img, choose), 1, 5);
        form.add(save, 1, 6);

        root.setCenter(form);
        return new Scene(root, 1000, 620);
    }

    private static void addRow(GridPane g, int r, String label, Control field) {
        g.add(new Label(label), 0, r);
        field.setPrefWidth(360);
        g.add(field, 1, r);
    }
}
