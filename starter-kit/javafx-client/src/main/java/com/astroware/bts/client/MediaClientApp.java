package com.astroware.bts.client;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.nio.file.Path;
import java.util.List;

public class MediaClientApp extends Application {
    private final TextField serverField = new TextField("http://localhost:8080");
    private final TextField userField = new TextField("user");
    private final PasswordField passwordField = new PasswordField();
    private final TextField searchField = new TextField();
    private final ListView<AssetDto> list = new ListView<>();
    private final Label status = new Label("Non connecté");
    private final Label details = new Label();
    private final StackPane preview = new StackPane();

    private ApiClient api;
    private MediaPlayer player;

    @Override
    public void start(Stage stage) {
        passwordField.setText("user123!");

        Button connect = new Button("Connexion");
        connect.setOnAction(event -> connect());

        HBox login = new HBox(
            8,
            new Label("Serveur"), serverField,
            new Label("Utilisateur"), userField,
            new Label("Mot de passe"), passwordField,
            connect
        );
        login.setAlignment(Pos.CENTER_LEFT);

        Button search = new Button("Rechercher");
        search.setOnAction(event -> loadAssets());

        HBox toolbar = new HBox(8, searchField, search);
        HBox.setHgrow(searchField, Priority.ALWAYS);

        Button play = new Button("Prévisualiser / lire");
        play.setOnAction(event -> previewSelected());

        VBox right = new VBox(12, details, play, preview);
        right.setPadding(new Insets(8));
        VBox.setVgrow(preview, Priority.ALWAYS);
        preview.setMinSize(400, 320);

        SplitPane split = new SplitPane(list, right);
        split.setDividerPositions(0.38);

        list.getSelectionModel().selectedItemProperty().addListener(
            (obs, oldValue, asset) -> showDetails(asset)
        );

        BorderPane root = new BorderPane();
        VBox top = new VBox(10, login, toolbar, status);
        top.setPadding(new Insets(12));
        root.setTop(top);
        root.setCenter(split);

        stage.setTitle("BTS SIO — Java Media Starter");
        stage.setScene(new Scene(root, 1050, 680));
        stage.show();

        stage.setOnCloseRequest(event -> {
            if (player != null) player.dispose();
        });
    }

    private void connect() {
        api = new ApiClient(
            serverField.getText(),
            userField.getText(),
            passwordField.getText()
        );
        loadAssets();
    }

    private void loadAssets() {
        if (api == null) {
            status.setText("Connectez-vous d'abord.");
            return;
        }

        status.setText("Chargement...");
        list.setDisable(true);

        api.list(searchField.getText())
            .whenComplete((assets, error) -> Platform.runLater(() -> {
                list.setDisable(false);
                if (error != null) {
                    status.setText("Erreur : " + rootMessage(error));
                    return;
                }
                list.getItems().setAll(assets == null ? List.of() : assets);
                status.setText(list.getItems().size() + " média(s)");
            }));
    }

    private void showDetails(AssetDto asset) {
        if (asset == null) {
            details.setText("");
            return;
        }

        details.setText(
            asset.title() + "\n" +
            "Type : " + asset.mimeType() + "\n" +
            "Fichier : " + asset.originalFilename() + "\n" +
            "Taille : " + asset.sizeBytes() + " octets\n" +
            "Statut : " + asset.status()
        );
    }

    private void previewSelected() {
        AssetDto asset = list.getSelectionModel().getSelectedItem();
        if (asset == null || api == null) return;

        status.setText("Téléchargement sécurisé...");
        api.download(asset).whenComplete((path, error) -> Platform.runLater(() -> {
            if (error != null) {
                status.setText("Erreur média : " + rootMessage(error));
                return;
            }
            showMedia(asset, path);
            status.setText("Média prêt.");
        }));
    }

    private void showMedia(AssetDto asset, Path path) {
        preview.getChildren().clear();
        if (player != null) {
            player.dispose();
            player = null;
        }

        String type = asset.mimeType() == null ? "" : asset.mimeType();

        if (type.startsWith("image/")) {
            ImageView view = new ImageView(new Image(path.toUri().toString()));
            view.setPreserveRatio(true);
            view.setFitWidth(520);
            view.setFitHeight(420);
            preview.getChildren().add(view);
            return;
        }

        if (type.startsWith("audio/") || type.startsWith("video/")) {
            player = new MediaPlayer(new Media(path.toUri().toString()));
            MediaView view = new MediaView(player);
            view.setPreserveRatio(true);
            view.setFitWidth(520);
            view.setFitHeight(420);
            preview.getChildren().add(view);
            player.play();
            return;
        }

        preview.getChildren().add(
            new Label("Preview intégrée non prévue pour ce type.\nFichier téléchargé : " + path)
        );
    }

    private String rootMessage(Throwable error) {
        Throwable current = error;
        while (current.getCause() != null) current = current.getCause();
        return current.getMessage();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
