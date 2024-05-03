package be.ugent.objprog.ugentopoly;

import be.ugent.objprog.ugentopoly.dice.DiceRoller;
import be.ugent.objprog.ugentopoly.gamecards.GameCard;
import be.ugent.objprog.ugentopoly.players.PlayerModel;
import be.ugent.objprog.ugentopoly.tiles.tileModels.TileModel;

import java.util.List;

/*
NON-URGENT write class documentation
Functions as the controller for GameModel, so performs all the updates for it
 */

public class GameController {
    private final GameModel gameModel;
    private final DiceRoller diceRoller;

    public GameController(GameModel gameModel) {
        this.gameModel = gameModel;
        diceRoller = new DiceRoller(this);

        List<PlayerModel> players = gameModel.getPlayerModels();
        players.forEach(player -> gameModel.getTileModels()[0].addPion(player.getPion()));

        gameModel.setCurrentPlayer(players.getFirst());
        gameModel.addLog(gameModel.getCurrentPlayer().getName(), "zal als eerste spelen!");
    }

    public void actMove()  {
        diceRoller.setIsDisabled(Boolean.TRUE);

        int moves = diceRoller.getMostRecentRoll();
        PlayerModel currentPlayer = gameModel.getCurrentPlayer();

        int finalDestination = currentPlayer.getPosition();

        TileModel[] tileModels = gameModel.getTileModels();

        int length = tileModels.length;

        // If a player passes start, but doesn't land on it.
        if (finalDestination != 0 && currentPlayer.getPosition() + moves > length) {
            tileModels[0].executePlayerTileInteraction(gameModel);
        }

        TileModel currentTile = tileModels[finalDestination];
        gameModel.addLog(currentPlayer.getName(), "belande op " + currentTile.getName() );
        moveCurrentPlayerToPosition(currentPlayer.getPosition() + moves);

        diceRoller.setIsDisabled(Boolean.TRUE);
        gameModel.getPlayerModels().forEach(PlayerModel::updateBalanceHistory);
        nextPlayer();
    }

    public void nextPlayer() {
        PlayerModel playerModel = gameModel.getPlayerModelQueue().getNextPlayer();
        gameModel.setCurrentPlayer(playerModel);
        gameModel.addLog(playerModel.getName(), "is aan de beurt!");

        // TODO extract to own method. "Check is in jail" -> playermodel as argument
        if (playerModel.isInJail()) {
            if (playerModel.getLeaveJailCards().isEmpty()) {
                gameModel.addLog(playerModel.getName(), "is stomdronken, en zal dus moeten\ndobbelen tot die nuchter is");
                diceRoller.rollToGetFree();
            } else {
                playerModel.setInJail(false);
                GameCard jailCard = playerModel.getLeaveJailCards().removeFirst();
                playerModel.fireInvalidationEvent(); // update count of get out of jail cards
                if (gameModel.getChanceCardDeck().getSize() > gameModel.getChestCardDeck().getSize()) {
                    gameModel.getChestCardDeck().addCard(jailCard);
                } else {
                    gameModel.getChanceCardDeck().addCard(jailCard);
                }
                gameModel.addLog(playerModel.getName(), "gebruikt een get-out-of-jail kaart\nen geraakt thuis van de Overpoort");

            }
        }
    }

    public void moveCurrentPlayerToPosition(int newPosition) {
        PlayerModel playerModel = gameModel.getCurrentPlayer();
        TileModel[] tileModels = gameModel.getTileModels();

        tileModels[playerModel.getPosition()].removePion(playerModel.getPion());
        playerModel.setPosition(newPosition);
        TileModel finalTile = tileModels[newPosition];

        finalTile.addPion(playerModel.getPion());
        finalTile.executePlayerTileInteraction(gameModel);
    }

    public void freePlayerFromJail(int moves) {
        PlayerModel freePlayer = gameModel.getCurrentPlayer();
        freePlayer.setInJail(false);
        gameModel.addLog(freePlayer.getName(), "gooide een dubbel\nen ontsnapt uit de Overpoort!");
        int position = freePlayer.getPosition() + moves;
        moveCurrentPlayerToPosition(position);
    }

    public void moveCurrentPlayerToJail() {
        gameModel.getCurrentPlayer().setInJail(true);
        moveCurrentPlayerToPosition(10);
    }

    public void addLog(String text) {
        gameModel.addLog(text);
    }

    // remove
    public GameModel getGameModel() {
        return gameModel;
    }

    public DiceRoller getDiceRoller() {
        return diceRoller;
    }
}