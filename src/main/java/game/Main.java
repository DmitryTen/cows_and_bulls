package game;

import game.entity.Game;
import game.entity.Player;
import game.entity.ComputerNumber;
import game.facade.ComputerNumberFacade;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Windows on 17.01.2016.
 */
public class Main {

    public static void main(String[] args){

        Integer[] rndSecuence = new Integer[10];
        ArrayList<Integer> numbersArray = new ArrayList<>();
        for (int b=0; b<10; b++) numbersArray.add(b);
        Collections.shuffle(numbersArray);
        rndSecuence = numbersArray.toArray(rndSecuence);


        long secuense=0;
        for (int b=0; b<rndSecuence.length; b++){
            secuense = rndSecuence[b] * (long)Math.pow(10 , 9-b) + secuense;
        }

        System.out.println(secuense);









    }

    static void firstTest(){
        FileSystemXmlApplicationContext context = new FileSystemXmlApplicationContext(
                new String[]{"src/main/java/CowsAndBullsDatabase.xml", "src/main/java/DAOsAndFacades.xml"});

        ComputerNumberFacade nef = (ComputerNumberFacade) context.getBean("numberEntityFacade");

        ComputerNumber ne = new ComputerNumber();
        ne.setNum(4321);
        ne.setBullsAmount(5);
        ne.setCowsAmount(6);
        ne.setStepNumber(7);
        ne.setIndex1(3);
        ne.setIndex2(4);
        ne.setIndex3(8);
        ne.setIndex4(9);

        Player player = new Player();
        player.setPlayerName("Abcdef");
        player.setPlayerId(1);

        Game game = new Game();
        game.setPlayer(player);
        game.setRndSecuence(1234567890);
        game.setGameId(1);

        ne.setGame(game);

   /*     PlayerFacade gf = (PlayerFacade) context.getBean("gamerFacade");
        gf.addPlayer(player);*/

    /*    GameFacade gameFacade = (GameFacade) context.getBean("gameFacade");
        gameFacade.addGame(game);*/

        //   long id = nef.addNumber(ne);
        //   System.out.println(id);

        List<ComputerNumber> numberEntities = nef.findNumber(game);
        System.out.println(numberEntities.get(0).getNum());
        System.out.println(numberEntities.get(1).getNum());


    }
}
