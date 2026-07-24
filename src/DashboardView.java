package com.crimetrack;

import java.sql.*;
import javafx.collections.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class DashboardView {
    public static Scene create(Stage stage) {
        BorderPane root = UI.shell(stage, "Dashboard");

        VBox content = new VBox(20);
        content.setPadding(new Insets(10, 22, 22, 22));

        HBox cards = new HBox(18);
        cards.getChildren().addAll(card("Total Criminals", count("criminal"), "blue"),
                card("Total FIRs", count("fir"), "green"),
                card("Operators", "12", "purple"),
                card("Pending Cases", pendingCases(), "red"));

        TableView<String[]> table = new TableView<>();
        table.getStyleClass().add("data-table");
        String[] cols = {"FIR No", "Name", "Crime", "Date", "Status"};
        for (int i = 0; i < cols.length; i++) {
            final int idx = i;
            TableColumn<String[], String> c = new TableColumn<>(cols[i]);
            c.setCellValueFactory(v -> new javafx.beans.property.SimpleStringProperty(v.getValue()[idx]));
            c.setPrefWidth(130);
            table.getColumns().add(c);
        }
        table.setItems(recentFirs());
        table.setPrefHeight(280);

        content.getChildren().addAll(cards, new Label("Recent FIR Records"), table);
        root.setCenter(content);
        return new Scene(root, 1000, 620);
    }

    private static VBox card(String title, String value, String color) {
        VBox v = new VBox(8);
        v.getStyleClass().addAll("stat-card", color);
        v.getChildren().addAll(new Label(title), new Label(value), new Label("View Details"));
        return v;
    }

    private static String count(String table) {
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM " + table)) {
            rs.next();
            return rs.getString(1);
        } catch (Exception e) { return "0"; }
    }

    private static String pendingCases() {
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM fir WHERE status='Open'")) {
            rs.next();
            return rs.getString(1);
        } catch (Exception e) { return "0"; }
    }

    private static ObservableList<String[]> recentFirs() {
        ObservableList<String[]> data = FXCollections.observableArrayList();
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT fir_no, criminal_name, crime, fir_date, status FROM fir ORDER BY fir_id DESC LIMIT 8")) {
            while (rs.next()) {
                data.add(new String[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)});
            }
        } catch (Exception ignored) {}
        return data;
    }
}
