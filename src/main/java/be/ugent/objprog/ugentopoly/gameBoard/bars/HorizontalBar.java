package be.ugent.objprog.ugentopoly.gameBoard.bars;

import java.util.List;
import java.util.stream.IntStream;

import be.ugent.objprog.ugentopoly.gameBoard.MiddleSection;
import be.ugent.objprog.ugentopoly.tiles.tileViews.TileView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

/*
    initializeRowConstraints() : fill the bar with 8 rows, as to create 8 places for tiles.
    populate() : places 9 received tiles in the slots.
    NON-URGENT write documentation for BARS
*/

public class HorizontalBar extends GridPane implements Bar {
    private static final int NUMTILES = 9;
    private static final double COL_WIDTH = TileView.SHORT_SIDE;
    private final List<? extends TileView> tiles;

    public HorizontalBar(List<TileView> tileViews) {
        this.tiles = tileViews;
        setMinWidth(MiddleSection.getSize());
        setMaxWidth(MiddleSection.getSize());
        setMinHeight(TileView.LONG_SIDE);
        setMaxHeight(TileView.LONG_SIDE);

        initializeColumnConstraints();
        populate();
    }

    private void initializeColumnConstraints() {
        IntStream.range(1, NUMTILES).forEach(i -> getColumnConstraints().add(new ColumnConstraints(COL_WIDTH)));
    }

    public void populate() {
        IntStream.range(0, NUMTILES).forEach(i -> add(tiles.get(i), i, 0));
    }

    public void applyRotation(double angle) {
        tiles.forEach(tile -> tile.applyRotation(angle));
    }

}