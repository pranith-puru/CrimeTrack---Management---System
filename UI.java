package com.crimetrack;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class UI {
    public static BorderPane shell(Stage stage, String title) {
        BorderPane root = new BorderPane();
        root.getStylesheets().add(UI.class.getResource("/style.css").toExternalForm());

        VBox sidebar = new VBox(12);
        sidebar.getStyleClass().add("sidebar");
        sidebar.setPadding(new Insets(20));
        sidebar.setPrefWidth(190);

        Label logo = new Label("🛡 CRIMETRACK");
        logo.getStyleClass().add("logo");

        Button dash = nav("Dashboard");
        Button add = nav("Add Criminal");
        Button search = nav("Search Criminal");
        Button fir = nav("FIR Records");
        Button view = nav("View Criminals");
        Button logout = nav("Logout");

        dash.setOnAction(e -> stage.setScene(DashboardView.create(stage)));
        add.setOnAction(e -> stage.setScene(AddCriminalView.create(stage)));
        search.setOnAction(e -> stage.setScene(SearchView.create(stage)));
        fir.setOnAction(e -> stage.setScene(FIRView.create(stage)));
        view.setOnAction(e -> stage.setScene(ViewCriminalsView.create(stage)));
        logout.setOnAction(e -> stage.setScene(LoginView.create(stage)));

        sidebar.getChildren().addAll(logo, dash, add, search, fir, view, new Separator(), logout);
        root.setLeft(sidebar);

        Label header = new Label(title);
        header.getStyleClass().add("page-title");
        HBox top = new HBox(header);
        top.setPadding(new Insets(18));
        root.setTop(top);
        return root;
    }

    private static Button nav(String text) {
        Button b = new Button(text);
        b.getStyleClass().add("nav-btn");
        b.setMaxWidth(Double.MAX_VALUE);
        return b;
    }

    public static void alert(String msg) {
        new Alert(Alert.AlertType.INFORMATION, msg).showAndWait();
    }
}
