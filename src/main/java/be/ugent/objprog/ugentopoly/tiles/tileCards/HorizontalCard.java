package be.ugent.objprog.ugentopoly.tiles.tileCards;

import be.ugent.objprog.ugentopoly.gameboard.BoardModel;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class HorizontalCard extends TemplateCard {

    public static final Double WIDTH = BoardModel.MIDDLE_AREA_SIZE / 2;
    public static final Double HEIGHT = BoardModel.MIDDLE_AREA_SIZE / 3;
    protected static final int ARC = 20;

    public HorizontalCard() {
        setMinHeight(HEIGHT);
        setMinWidth(WIDTH);
        setMaxHeight(HEIGHT);
        setMaxWidth(WIDTH);
        setBackground(Background.fill(Color.TRANSPARENT));

        Rectangle border = new Rectangle(WIDTH, HEIGHT);
        border.setStroke(Color.BLACK);
        border.setStrokeWidth(3);
        border.setFill(Color.WHITE);
        border.setArcHeight(ARC);
        border.setArcWidth(ARC);

        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(10);
        dropShadow.setOffsetX(-5);
        dropShadow.setOffsetY(5);
        dropShadow.setColor(Color.DIMGREY);
        border.setEffect(dropShadow);

        getChildren().addAll(border);
    }
}