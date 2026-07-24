package com.crimetrack;

import java.sql.*;
import javafx.collections.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class FIRView {
    public static Scene create(Stage stage) {
        BorderPane root = UI.shell(stage, "FIR Records");
        VBox box = new VBox(15);
        box.setPadding(new Insets(25));

        Button add = new Button("+ Add New FIR");
        add.getStyleClass().add("primary-btn");
        add.setOnAction(e -> showAddFir());

        TableView<String[]> table = new TableView<>();
        String[] cols = {"FIR No", "Name", "Crime", "Date", "Status"};
        for (int i = 0; i < cols.length; i++) {
            final int idx = i;
            TableColumn<String[], String> c = new TableColumn<>(cols[i]);
            c.setCellValueFactory(v -> new javafx.beans.property.SimpleStringProperty(v.getValue()[idx]));
            c.setPrefWidth(150);
            table.getColumns().add(c);
        }
        table.setItems(load());
        table.setPrefHeight(430);
        box.getChildren().addAll(add, table);
        root.setCenter(box);
        return new Scene(root, 1000, 620);
    }

    private static ObservableList<String[]> load() {
        ObservableList<String[]> list = FXCollections.observableArrayList();
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT fir_no, criminal_name, crime, fir_date, status FROM fir")) {
            while (rs.next()) list.add(new String[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)});
        } catch(Exception ignored) {}
        return list;
    }

    private static void showAddFir() {
        Dialog<ButtonType> d = new Dialog<>();
        d.setTitle("Add FIR");
        GridPane g = new GridPane();
        g.setHgap(10); g.setVgap(10); g.setPadding(new Insets(15));
        TextField fir = new TextField(), name = new TextField(), crime = new TextField(), date = new TextField("2026-05-12"), status = new TextField("Open");
        g.addRow(0, new Label("FIR No"), fir);
        g.addRow(1, new Label("Criminal Name"), name);
        g.addRow(2, new Label("Crime"), crime);
        g.addRow(3, new Label("Date YYYY-MM-DD"), date);
        g.addRow(4, new Label("Status"), status);
        d.getDialogPane().setContent(g);
        d.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        d.showAndWait().ifPresent(b -> {
            if (b == ButtonType.OK) {
                try (Connection con = DBConnection.getConnection()) {
                    PreparedStatement ps = con.prepareStatement("INSERT INTO fir(fir_no,criminal_name,crime,fir_date,status) VALUES(?,?,?,?,?)");
                    ps.setString(1, fir.getText()); ps.setString(2, name.getText()); ps.setString(3, crime.getText());
                    ps.setString(4, date.getText()); ps.setString(5, status.getText());
                    ps.executeUpdate();
                    UI.alert("FIR added. Reopen FIR screen to refresh.");
                } catch(Exception ex) { UI.alert(ex.getMessage()); }
            }
        });
    }
}
