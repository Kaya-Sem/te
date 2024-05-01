package be.ugent.objprog.ugentopoly.playertabpane;

import be.ugent.objprog.ugentopoly.CustomImageView;
import be.ugent.objprog.ugentopoly.tiles.tileModels.TileModel;
import be.ugent.objprog.ugentopoly.tiles.tileinterface.ColorTile;
import be.ugent.objprog.ugentopoly.tiles.tileinterface.ImageTile;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.HashMap;
import java.util.Map;

public class TileCell extends ListCell<TileModel> {
    private final Map<Class<?>, TileGraphicGenerator> generatorMap = new HashMap<>();
    private static final double GRAPHICSIZE = 40;

    public TileCell() {
        generatorMap.put(ColorTile.class, TileCell::generateColorView);
        generatorMap.put(ImageTile.class, TileCell::generateImageView);
    }

    @Override
    protected void updateItem(TileModel tile, boolean empty) {
        super.updateItem(tile, empty);

        if (empty || tile == null) {
            setText(null);
            setGraphic(null);
        } else {
            setText(tile.getName());
            generatorMap.entrySet().stream()
                    .filter(entry -> entry.getKey().isInstance(tile))
                    .findFirst()
                    .ifPresent(entry -> setGraphic(entry.getValue().generate(tile)));

        }
    }

    private static ImageView generateColorView(TileModel tile) {
        ColorTile colorTile = (ColorTile) tile;
        Rectangle rect = new Rectangle(GRAPHICSIZE, GRAPHICSIZE);
        rect.setFill(Color.web(colorTile.getColor()));
        return new ImageView(rectToImage(rect));
    }

    private static ImageView generateImageView(TileModel tile) {
        ImageTile imageTile = (ImageTile) tile;
        return new CustomImageView(GRAPHICSIZE, GRAPHICSIZE, imageTile.getImage());
    }

    private static Image rectToImage(Rectangle rectangle) {
        rectangle.setArcHeight(5);
        rectangle.setArcWidth(5);
        return rectangle.snapshot(null, null);
    }
}