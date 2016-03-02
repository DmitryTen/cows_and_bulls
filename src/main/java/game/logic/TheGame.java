package game.logic;

import game.db.GamePageFacade;
import game.db.entity.ComputerNumber;
import game.db.entity.Game;
import game.db.entity.Player;
import game.db.entity.PlayerNumber;
import game.logic.computersNumber.CowBullDefiner;
import game.logic.gamerNumberDefiner.NumberDefiner;
import game.logic.gamerNumberDefiner.NumberInfo;
import game.web.wicket.game.models.NumberModel;
import game.web.wicket.game.models.PageModel;

/**
 * Created by Windows on 18.01.2016.
 *
 * Класс - принимающий данные введенные игроком. Также класс определяет победителя в игре.
 *
 */
public class TheGame {
    //Второстепенная ветвь, для игрока. Выдает коров и быков исходя из числа.
    private CowBullDefiner cowBullDefiner;

    //Главная логическая ветвь, собирает числа исходя из быков и коров.
    private NumberDefiner numberDefiner;
    //Объект для работы с БД
    private GamePageFacade gamePageFacade;

    private Game game;

    private byte winner = -1;


    public TheGame(Player player, PageModel pageModel, GamePageFacade gamePageFacade) {
        this.cowBullDefiner = new CowBullDefiner();
        this.numberDefiner = new NumberDefiner();
        this.game = new Game(player);
        this.gamePageFacade = gamePageFacade;

        game.setRndSecuence(numberDefiner.getRndSecuense());
        this.gamePageFacade.addGame(game);

        pageModel.setComputer_number(numberDefiner.getNumber());
    }

    public void startNewGame(PageModel pageModel){
        cowBullDefiner = new CowBullDefiner();
        numberDefiner = new NumberDefiner();
        game = new Game(game.getPlayer());
        game.setRndSecuence(numberDefiner.getRndSecuense());
        gamePageFacade.addGame(game);
        pageModel.setComputer_number(numberDefiner.getNumber());
        winner = -1;
    }


    private void checkWinner(NumberModel player, NumberModel computer){
        if (player.getBulls()==4 && computer.getBulls()==4) winner=3;// friendship Wins
        if (player.getBulls()==4 && computer.getBulls()!=4) winner=1;// player Wins
        if (player.getBulls()!=4 && computer.getBulls()==4) winner=2; // computer Wins
    }

    public Game getGame(){
        return game;
    }

    public void defineNumberViewsAndPutThemInto(PageModel pageModel)  {
        NumberInfo numberInfo = numberDefiner.setCowsAndGetNumberInfo(pageModel.getComputer_number(),
                pageModel.getCow_group(), pageModel.getBull_group());
        gamePageFacade.addComputerNumber(new ComputerNumber(numberInfo, game));

        NumberModel computerNumberModel= new NumberModel(numberInfo, "DeepBlue");
        NumberModel playerNumberModel  = cowBullDefiner.createNumberViewAndDefineCowBullAmount(pageModel);
        gamePageFacade.addPlayerNumber(new PlayerNumber(playerNumberModel.getNumber(), game, playerNumberModel.getStep()));

        checkWinner(playerNumberModel, computerNumberModel);

        pageModel.addToPlayerList(playerNumberModel);
        pageModel.addToComputerList(computerNumberModel);
        pageModel.setComputer_number(numberDefiner.getNumber());
        pageModel.setPlayer_number(0);
        pageModel.setCow_group(0);
        pageModel.setBull_group(0);
        if(winner!=-1) addWinnerAndDefinedNumber(pageModel);
    }

    private void addWinnerAndDefinedNumber(PageModel pageModel){
        if(winner==1) pageModel.setWinner(pageModel.getPlayer_name() + " WINS! Congradulations!");
        if(winner==2) pageModel.setWinner("DeepBlue WINS! Try again!");
        if(winner==3) pageModel.setWinner("Friendship WINS! Congradulations! Try again!");
        pageModel.setProposedPlayerNumber("Число, загаданное " + pageModel.getPlayer_name() + ": " + numberDefiner.revealNumProposedByPlayer());
        pageModel.setProposedComputerNumber("Число, загаданное DeepBlue: " + cowBullDefiner.getProposedNumber());
    }

}
