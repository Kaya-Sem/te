package be.ugent.objprog.ugentopoly.factories;

import be.ugent.objprog.ugentopoly.tiles.TileTuple;
import be.ugent.objprog.ugentopoly.tiles.tileModels.*;
import be.ugent.objprog.ugentopoly.tiles.tileViews.*;
import be.ugent.objprog.ugentopoly.tiles.tileViews.cornerTiles.*;
import be.ugent.objprog.ugentopoly.tiles.tileViews.cornerTiles.JailCornerTileView;
import be.ugent.objprog.ugentopoly.tiles.tileViews.cornerTiles.StartCornerTileViewView;

import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

/*
NON-URGENT add documentation
*/

// HACK split into subfactories?
public class TileFactory {
    private final Map<String, String> areaColors;
    private Map<String, String> data = null;
    private final int startAmount;

    private final Map<String, Supplier<TileTuple>> tileMethods = Map.of(
            "START", this::createStartTile,
            "FREE_PARKING", this::createFreeParkingTile,
            "JAIL", this::createJailTile,
            "GO_TO_JAIL", this::createGoToJailTile,
            "STREET", this::createStreetModel,
            "CHEST", this::createChestTile,
            "TAX", this::createTaxTile,
            "RAILWAY", this::createRailway,
            "CHANCE", this::createChanceTile,
            "UTILITY", this::createUtility);

    public TileFactory(Map<String, String> areaColors, int startAmount) {
        this.areaColors = Objects.requireNonNull(areaColors);
        this.startAmount = startAmount;
    }

    // TODO explain why
    public TileTuple forge(Map<String, String> tileData) {
        data = tileData;
        return tileMethods.get(data.get("type")).get();
    }

    private TileTuple createChanceTile() {
        ChanceTileModel model = new ChanceTileModel(
                data.get("id"), Integer.parseInt(data.get("position"))
        );
        ChanceTileView view = new ChanceTileView(model);

        return new TileTuple(model, view);
    }

    private TileTuple createFreeParkingTile() {
        FreeParkingModel model = new FreeParkingModel(
                data.get("id"),
                Integer.parseInt(data.get("position")));
        FreeParkingCornerTileView view = new FreeParkingCornerTileView(model);

        return new TileTuple(model, view);
    }

    private TileTuple createGoToJailTile() {
        GoToJailTileModel model = new GoToJailTileModel(
                data.get("id"), Integer.parseInt(data.get("position"))
        );
        GoToJailCornerTileView view = new GoToJailCornerTileView(model);

        return new TileTuple(model, view);
    }

    private TileTuple createJailTile() {
        JailTileModel model = new JailTileModel(
                data.get("id"), Integer.parseInt(data.get("position"))
        );
        JailCornerTileView view = new JailCornerTileView(model);

        return new TileTuple(model, view);
    }

    private TileTuple createChestTile() {
        ChestTileModel model = new ChestTileModel(
                data.get("id"), Integer.parseInt(data.get("position"))
        );
        ChestTileView view = new ChestTileView(model);

        return new TileTuple(model, view);
    }

    private TileTuple createUtility() {
       UtilityTileModel model = new UtilityTileModel(
                data.get("id"),
                Integer.parseInt(data.get("position")),
                Integer.parseInt(data.get("cost")));
        UtilityTileView view = new UtilityTileView(model);

        return new TileTuple(model, view);
    }

    private TileTuple createTaxTile() {
        TaxTileModel model = new TaxTileModel(
                data.get("id"),
                Integer.parseInt(data.get("position")),
                Integer.parseInt(data.get("amount")));
        TaxTileView view = new TaxTileView(model);

        return new TileTuple(model, view);
    }

    private TileTuple createStartTile() {
        StartTileModel model = new StartTileModel(
                data.get("id"),
                Integer.parseInt(data.get("position")),
               startAmount
        );
        StartCornerTileViewView view = new StartCornerTileViewView(model);

        return new TileTuple(model, view);
    }

    private TileTuple createRailway() {
        RailwayTileModel model = new RailwayTileModel(
                data.get("id"),
                Integer.parseInt(data.get("position")),
                Integer.parseInt(data.get("cost")),
                Integer.parseInt(data.get("rent")));
        RailwayTileView view = new RailwayTileView(model);

        return new TileTuple(model, view);
    }

    private TileTuple createStreetModel() {
        StreetTileModel model = new StreetTileModel(
                data.get("id"),
                Integer.parseInt(data.get("position")),
                areaColors.get(data.get("area")),
                data.get("area"),
                data.get("cost"),
                data.get("rent0"));
        StreetTileView view = new StreetTileView(model);

        return new TileTuple(model, view);
    }

}