package game.logic.computersNumber;


import game.entity.PlayerNumber;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by Windows on 20.01.2016.
 */
public class CowBullDefinerTest {

    private CowBullDefiner cowBullDefiner;
    PlayerNumber playerNumber = new PlayerNumber();



    @Test
    public void proposed1234 () {
        cowBullDefiner = new CowBullDefiner(1234);

        playerNumber.setNum(4321);
        cowBullDefiner.fillDBEntityByBullsAndCows(playerNumber);
        assertEquals(4, playerNumber.getCowsAmount());
        assertEquals(0, playerNumber.getBullsAmount());

        playerNumber.setNum(4433);
        cowBullDefiner.fillDBEntityByBullsAndCows(playerNumber);
        assertEquals(1, playerNumber.getCowsAmount());
        assertEquals(1, playerNumber.getBullsAmount());

        playerNumber.setNum(1234);
        cowBullDefiner.fillDBEntityByBullsAndCows(playerNumber);
        assertEquals(0, playerNumber.getCowsAmount());
        assertEquals(4, playerNumber.getBullsAmount());

        playerNumber.setNum(5678);
        cowBullDefiner.fillDBEntityByBullsAndCows(playerNumber);
        assertEquals(0, playerNumber.getCowsAmount());
        assertEquals(0, playerNumber.getBullsAmount());
    }

    @Test
    public void proposed5555 () {
        cowBullDefiner = new CowBullDefiner(4555);

        playerNumber.setNum(3245);
        cowBullDefiner.fillDBEntityByBullsAndCows(playerNumber);
        assertEquals(1, playerNumber.getCowsAmount());
        assertEquals(1, playerNumber.getBullsAmount());

        playerNumber.setNum(5532);
        cowBullDefiner.fillDBEntityByBullsAndCows(playerNumber);
        assertEquals(1, playerNumber.getCowsAmount());
        assertEquals(1, playerNumber.getBullsAmount());
    }
}
