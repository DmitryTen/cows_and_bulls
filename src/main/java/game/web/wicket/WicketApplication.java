package game.web.wicket;

import game.db.GamePageFacade;
import game.web.wicket.pages.MySignInPage;
import org.apache.wicket.Page;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.markup.html.WebPage;

/**
 * Created by Windows on 12.02.2016.
 */
public class WicketApplication extends AuthenticatedWebApplication {

    GamePageFacade gamePageFacade;

    @Override
    public Class<? extends Page> getHomePage() {

        System.out.println("I'm here 1.");
        return BasePage.class;
    }

    @Override
    protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass() {
        System.out.println("I'm here 2.");
        return GameSession.class;
    }

    @Override
    protected Class<? extends WebPage> getSignInPageClass() {
        System.out.println("I'm here 3.");
        return MySignInPage.class;
    }

    @Override
    protected void init()
    {
        super.init();
        getDebugSettings().setDevelopmentUtilitiesEnabled(true);
        getRequestCycleSettings().setResponseRequestEncoding("UTF-8");
        getMarkupSettings().setDefaultMarkupEncoding("UTF-8");
    }

    public GamePageFacade getGamePageFacade() {
        return gamePageFacade;
    }

    public void setGamePageFacade(GamePageFacade gamePageFacade) {
        this.gamePageFacade = gamePageFacade;
    }
}
