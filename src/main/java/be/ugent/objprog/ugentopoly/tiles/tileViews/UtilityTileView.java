package be.ugent.objprog.ugentopoly.tiles.tileViews;

import be.ugent.objprog.ugentopoly.tiles.tileCards.UtilityCard;
import be.ugent.objprog.ugentopoly.tiles.tileModels.UtilityTileModel;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.util.Objects;

public class UtilityTileView extends SmallTile {
    private Image image;

    // Constructor
    public UtilityTileView(UtilityTileModel model) {
        super(model);
        setup();
        card = new UtilityCard(image, String.valueOf(model.getCost()));
    }

    @Override
    protected void setup() {
        String imageName = model.getId().substring(5) + ".png"; // maybe not the most robust way
        String imagePath = "/be/ugent/objprog/ugentopoly/assets/" + imageName;
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
        this.image = image;

        ImageView imageView = new ImageView(image);

        imageView.setFitHeight(SHORT_SIDE - 13);
        imageView.setFitWidth(LONG_SIDE - 13);
        imageView.setPreserveRatio(true);

        StackPane pane = new StackPane(imageView);
        pane.setPrefWidth(Double.MAX_VALUE);
        pane.setPrefHeight(Double.MAX_VALUE);
        pane.setPadding(new Insets(10, 10, 10, 10));

        getChildren().addAll(pane, tileButton, badgeHolders);
    }

    public UtilityTileModel getModel() {
        return (UtilityTileModel) model;
    }
}