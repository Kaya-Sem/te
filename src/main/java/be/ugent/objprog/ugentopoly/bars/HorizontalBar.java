package be.ugent.objprog.ugentopoly.bars;

import be.ugent.objprog.ugentopoly.gameBoard.MiddleSection;
import be.ugent.objprog.ugentopoly.tiles.tileViews.SmallTile;
import be.ugent.objprog.ugentopoly.tiles.tileViews.Tile;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

import java.util.List;

public class HorizontalBar extends GridPane implements Bar{
    private static final int NUM_COLS = 8;
    private static final double COL_WIDTH = Tile.SHORT_SIDE;

    private final List<? extends Tile> tiles;

    private void initializeColumnConstraints(){
        for (int i=0; i< NUM_COLS; i++) {
            getColumnConstraints().add(new ColumnConstraints(COL_WIDTH));
        }
    }

    public HorizontalBar(SmallTile[] tiles) {
        this.tiles = List.of(tiles);
        setPrefWidth(MiddleSection.getSize());
        setPrefHeight(Tile.LONG_SIDE);

        initializeColumnConstraints();
        populate();
    }

    public void populate(){
        if (tiles.size() == 9) {
            for (int i = 0; i < 9; i++) {
                Tile tile = tiles.get(i);
                add(tile, i, 0);
            }
        } else {
            throw new IllegalArgumentException("tileViews list must be of size 9!");
        }
    }

    public void applyRotation(double angle) {
        for (Tile tile : this.tiles) {
            tile.applyRotation(angle);
        }
    }

}