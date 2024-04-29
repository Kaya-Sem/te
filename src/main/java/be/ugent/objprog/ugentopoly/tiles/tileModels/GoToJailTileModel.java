package be.ugent.objprog.ugentopoly.tiles.tileModels;

import be.ugent.objprog.ugentopoly.CustomImage;
import be.ugent.objprog.ugentopoly.DisplayCardController;
import be.ugent.objprog.ugentopoly.GameModel;
import be.ugent.objprog.ugentopoly.players.PlayerModel;
import be.ugent.objprog.ugentopoly.tiles.tileCards.BasicVerticalCard;

import java.util.function.Consumer;

public class GoToJailTileModel extends TileModel{

    private final CustomImage image = new CustomImage("go_to_jail.png");

    public GoToJailTileModel(String tileID, int tilePosition, DisplayCardController controller) {
        super(tileID, tilePosition, controller);
        card = new BasicVerticalCard(image, tileName); // HACK
    }

    @Override
    public Consumer<GameModel> getPlayerTileInteraction() {
        return gameModel -> {
            PlayerModel currentPlayer = gameModel.getCurrentPlayerMove();
            gameModel.getGameController().moveCurrentPlayerToJail();
            gameModel.addLog(currentPlayer.getPlayerName(), "moet een nachtje in de Overpoort spenderen");
        };
    }

    public CustomImage getImage() {
        return image;
    }
}