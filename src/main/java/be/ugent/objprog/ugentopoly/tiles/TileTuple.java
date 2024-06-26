package be.ugent.objprog.ugentopoly.tiles;

import be.ugent.objprog.ugentopoly.tiles.tileViews.TileView;
import be.ugent.objprog.ugentopoly.tiles.tileModels.TileModel;

public record TileTuple(TileModel tileModel, TileView tileView) {

    public TileTuple{
        assert (tileModel != null): "initialized a tiletuple with no tileModel";
        assert (tileView != null): "initialized a tiletuple with no tileView";
    }
}