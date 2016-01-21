package game.web.controller;

import game.entity.ComputerNumber;
import game.entity.Player;
import game.entity.PlayerNumber;
import game.facade.ComputerNumberFacade;
import game.facade.GameFacade;
import game.facade.PlayerFacade;
import game.facade.PlayerNumberFacade;
import game.logic.TheGame;
import game.view.NumberView;
import game.view.PageView;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Windows on 17.01.2016.
 */
public class MainController extends AbstractController {

    protected static final Logger log = Logger.getLogger(MainController.class);

    private GameFacade gameFacade;
    private PlayerFacade playerFacade;
    private ComputerNumberFacade computerNumberFacade;
    private PlayerNumberFacade playerNumberFacade;

    private static HashMap<String, Player> sessionIdGamer = new HashMap<String, Player>();

    public void setGameFacade(GameFacade gameFacade) {
        this.gameFacade = gameFacade;
    }

    public void setPlayerFacade(PlayerFacade playerFacade) {
        this.playerFacade = playerFacade;
    }

    public void setPlayerNumberFacade(PlayerNumberFacade playerNumberFacade) {
        this.playerNumberFacade = playerNumberFacade;
    }

    public void setComputerNumberFacade(ComputerNumberFacade computerNumberFacade) {
        this.computerNumberFacade = computerNumberFacade;
    }

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest arg0, HttpServletResponse arg1)
            throws Exception {
        HttpSession session = arg0.getSession();

        if (session.isNew()) {
            return new ModelAndView("gamePage/authorisation");
        }

        if (arg0.getParameter("login") != null) {
            Player player = createPlayer(arg0.getParameter("login"));
            createNewGame(player, session);
            return new ModelAndView("gamePage/playerStep");
        }

        if (arg0.getParameter("playerNumber") != null) {
            return handlePlayersStep(session, Integer.valueOf(arg0.getParameter("playerNumber")));
        }

        if (arg0.getParameter("cow") != null){
            return handleComputerStep(session,
                    Integer.valueOf(arg0.getParameter("previousStepPCNumber")),
                    Integer.valueOf(arg0.getParameter("cow")),
                    Integer.valueOf(arg0.getParameter("bull")));
        }

        if (arg0.getParameter("newGame") != null){
            Player player = ((TheGame)session.getAttribute("theGame")).getGame().getPlayer();
            createNewGame(player, session);
            return new ModelAndView("gamePage/playerStep");
        }

        return new ModelAndView("gamePage/authorisation");
    }

    private Player createPlayer(String name){
        Player player = playerFacade.getGamer(name);
        if (player == null){
            player = new Player(name);
            playerFacade.addGamer(player);
        }
        return player;
    }

    private void createNewGame(Player player, HttpSession session){
        TheGame theGame = new TheGame(player);
        long gameId = gameFacade.addGame(theGame.getGame());
        theGame.setIdIntoGame(gameId);
        session.setAttribute("theGame", theGame);
    }


    private ModelAndView handlePlayersStep(HttpSession session, int number){
        TheGame theGame = (TheGame) session.getAttribute("theGame");

        PlayerNumber playerNumberDBEntity = theGame.handlePlayersNumberReturnDBEntity(number);
        playerNumberFacade.addNumber(playerNumberDBEntity);

        List<NumberView> playerListNumberView = playerNumberFacade.getViewList(theGame.getGame());
        List<NumberView> computerListNumberView = computerNumberFacade.getViewList(theGame.getGame());
        int definedNumber = theGame.getNumberFromDefiner();

        PageView pageView = new PageView(playerListNumberView, computerListNumberView, definedNumber);

        return new ModelAndView("gamePage/computerStep", "pageView", pageView);
    }



    private ModelAndView handleComputerStep(HttpSession session, int num, int cowAmount, int bullAmount){
        TheGame theGame = (TheGame) session.getAttribute("theGame");

        ComputerNumber computerNumberDBEntity = theGame.handleComputerNumberReturnDBEntity(num, cowAmount, bullAmount);
        computerNumberFacade.addNumber(computerNumberDBEntity);

        List<NumberView> playerListNumberView = playerNumberFacade.getViewList(theGame.getGame());
        List<NumberView> computerListNumberView = computerNumberFacade.getViewList(theGame.getGame());

        PageView pageView = new PageView(playerListNumberView, computerListNumberView);

        if(theGame.getWinner()>0) return getWinnerPage(theGame, pageView);

        return new ModelAndView("gamePage/playerStep", "pageView", pageView);
    }

    private ModelAndView getWinnerPage(TheGame theGame, PageView pageView){
        pageView.setPlayerName(theGame.getGame().getPlayer().getPlayerName());
        pageView.setPlayersNumber(theGame.revealNumProposedByPlayer());
        pageView.setComputersNumber(theGame.revealNumProposedByComputer());

        if(theGame.getWinner()==1) pageView.setWinner(theGame.getGame().getPlayer().getPlayerName() + ", Congradulations!!!");
        if(theGame.getWinner()==2) pageView.setWinner("Computer, try again!");
        if(theGame.getWinner()==3) pageView.setWinner("Friendship Wins! Try again!");

        return new ModelAndView("gamePage/winnerFound", "pageView", pageView);
    }


}
