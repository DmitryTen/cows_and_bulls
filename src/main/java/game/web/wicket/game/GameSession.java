package game.web.wicket.game;

import game.db.GamePageFacade;
import game.db.entity.Player;
import game.logic.TheGame;
import game.web.wicket.game.models.PageModel;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.cycle.RequestCycle;

/**
 * Created by Windows on 17.02.2016.
 */
public class GameSession extends AuthenticatedWebSession {
    /**
     * Constructor. Note that {@link RequestCycle} is not available until this constructor returns.
     *
     * @param request The current request
     */
    private Player player;
    private TheGame theGame;
    private PageModel pageModel;
    private GamePageFacade gamePageFacade;

    public GameSession(Request request) {
        super(request);
        gamePageFacade = ((WicketApplication)getApplication()).getGamePageFacade();
    }

    @Override
    public Roles getRoles() {

        if(isSignedIn())  return new Roles(Roles.USER);

        return null;
    }

    @Override
    protected boolean authenticate(String username, String password) {

        if (player == null) {
            if(username == null) return false;

            Player authTry = gamePageFacade.getPlayer(username);
            if(authTry.getPlayerName().equals(username) && authTry.getPlayerPassword().equals(password)){
                player = authTry;
                pageModel = new PageModel(username);
                theGame = new TheGame(authTry, pageModel, gamePageFacade);
            }
        }
        return player != null;
    }

    public TheGame getTheGame() {
        return theGame;
    }

    public PageModel getPageModel() {
        return pageModel;
    }

    public GamePageFacade getGamePageFacade() {
        return gamePageFacade;
    }
}
