package be.ugent.objprog.ugentopoly.tiles.tile.cornerTiles;

import be.ugent.objprog.ugentopoly.parsers.PropertyLoader;
import be.ugent.objprog.ugentopoly.tileCards.BasicVerticalCard;
import be.ugent.objprog.ugentopoly.tiles.tile.TileImageView;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class FreeParkingCornerTile extends CornerTile {
    private final Image image = new Image("/be/ugent/objprog/ugentopoly/assets/free_parking.png");

    public FreeParkingCornerTile(Record companion, String id) {
        super(companion, id);
        setup(id);
        this.card = new BasicVerticalCard(this.image, PropertyLoader.getLabel(id));
    }

    // TODO extract setFont and label to class. (or css?)
    @Override
    protected void setup(String id) {
        TileImageView image = new TileImageView(this.image, 1.5, true);

        String[] text = PropertyLoader.getLabel(id).split(" ");
        Label textLabel1 = new Label(text[0]);
        Label textLabel2 = new Label(text[1]);

        textLabel1.setFont(Font.font("Arial", FontWeight.BOLD, 10));
        textLabel2.setFont(Font.font("Arial", FontWeight.BOLD, 10));

        VBox vBox = new VBox(textLabel1, image, textLabel2);

        vBox.setAlignment(Pos.CENTER);
        vBox.setRotate(-135);

        getChildren().addAll(vBox, this.tileButton );
    }
}