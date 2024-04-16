package be.ugent.objprog.ugentopoly.tiles.tileCards;

import be.ugent.objprog.ugentopoly.tiles.tileModels.StreetTileModel;
import be.ugent.objprog.ugentopoly.tiles.tileViews.Tile;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import static javafx.geometry.Pos.CENTER_LEFT;
import static javafx.geometry.Pos.CENTER_RIGHT;

public class StreetCard extends HorizontalCard {
    public StreetCard(StreetTileModel model) {
        Label textLabel = new Label(model.getStreetName());
        textLabel.setFont(Font.font("Raleway", FontWeight.EXTRA_BOLD, 15));

        Label cost = new Label(model.getCost());
        cost.setFont(Font.font("Arial", FontWeight.THIN, 11));

        VBox info = new VBox(
                textLabel,
                cost,
                new Label("\n"),
                new rentLabel("rent 0", model.getRent0()),
                new rentLabel("rent 1", model.getRent1()),
                new rentLabel("rent 2", model.getRent2()),
                new rentLabel("rent 3", model.getRent3()),
                new rentLabel("rent 4", model.getRent4()),
                new rentLabel("rent 5", model.getRent5())
        );

        info.setAlignment(CENTER_LEFT);
        info.setPadding(new Insets(20, 20, 20, 20));

        Rectangle bar = new Rectangle(Tile.SHORT_SIDE - 3, HEIGHT - 3);
        bar.setFill(Paint.valueOf(model.getColor()));
        bar.setArcHeight(20);
        bar.setArcWidth(20);

        setAlignment(bar, CENTER_RIGHT);

        getChildren().addAll(info, bar);

    }


    public static class rentLabel extends HBox {
        public rentLabel(String preText, String rentAmount){
            Label text = new Label(preText);
            Label amount = new Label(rentAmount + "€");
            amount.setFont(Font.font("Arial", FontWeight.BOLD, 13));
            Region region = new Region();

            setHgrow(region, Priority.SOMETIMES);

            setAlignment(CENTER_LEFT);

            setMaxWidth(WIDTH /3);
            getChildren().addAll(text, region, amount);
        }
    }
}