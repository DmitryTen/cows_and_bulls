package game.logic.gamerNumberDefiner;


import game.logic.gamerNumberDefiner.stage1.DefinerS1Main;
import game.logic.gamerNumberDefiner.stage2.CowInfo;
import game.logic.gamerNumberDefiner.stage2.DefinerS2Main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
/**
 * Created by Windows on 18.12.2015.
 */
public class NumberDefiner {

    private Integer[] rndSecuence = new Integer[10];
    private ArrayList<NumberInfo> numbersHistory;
    private HashMap<Integer, Boolean> definedIndexes;
    private HashMap<Byte, Integer> definedPositions;
    private HashMap<Integer, CowInfo> cowContainer;

    private int stage = 1;
    private Definer definer;


    public NumberDefiner() {
        fillRndSecuence();
        numbersHistory = new ArrayList<>();
        definedIndexes = new HashMap<>();
        cowContainer = new HashMap<>();
        definedPositions = new HashMap<>();

        definer = new DefinerS1Main(rndSecuence, numbersHistory, definedIndexes, this);
    }

    public NumberDefiner(Integer[] rndSecuence, ArrayList<NumberInfo> numbersHistory, HashMap<Integer, Boolean> definedIndexes) {
        this.rndSecuence = rndSecuence;
        this.numbersHistory = numbersHistory;
        this.definedIndexes = definedIndexes;
        cowContainer = new HashMap<>();
        definedPositions = new HashMap<>();
        definer = new DefinerS1Main(rndSecuence, numbersHistory, definedIndexes, this);
    }

    public NumberDefiner(Integer[] rndSecuence, ArrayList<NumberInfo> numbersHistory, HashMap<Integer, Boolean> definedIndexes,
                         HashMap<Byte, Integer> definedPositions, HashMap<Integer, CowInfo> cowContainer){
        this.rndSecuence = rndSecuence;
        this.numbersHistory = numbersHistory;
        this.definedIndexes = definedIndexes;
        this.cowContainer = cowContainer;
        this.definedPositions = definedPositions;
        stage=2;
        definer = new DefinerS2Main(rndSecuence, numbersHistory, definedIndexes, cowContainer, definedPositions, this);
    }

    private void fillRndSecuence(){
        ArrayList<Integer> numbersArray = new ArrayList<>();
        for (int b=0; b<10; b++) numbersArray.add(b);
        Collections.shuffle(numbersArray);
        rndSecuence = numbersArray.toArray(rndSecuence);
    }

    public long getRndSecuense(){
        long secuense=0;
        for (int b=0; b<rndSecuence.length; b++){
            secuense = rndSecuence[b] * (long)Math.pow(10 , 9-b) + secuense;
        }
        return secuense;
    }

    public int getNumber(){
        if (stage==1) {
            return definer.getNumber();
        }
        if (stage==2) {
           return definer.getNumber();
        }
        if (stage==3) {
            return getFinalNumber();
        }
        return 0;
    }


    public NumberInfo setCowsAndGetNumberInfo(int number, int cows, int bulls){
        NumberInfo numberInfo = numbersHistory.get(numbersHistory.size() - 1);
        if (numberInfo.getNum() == number){
            numberInfo.setCowsAmount(cows);
            numberInfo.setBullsAmount(bulls);
        }
        return numberInfo;
    }


    public void setStage(int stage) {
        if (stage==2) definer = new DefinerS2Main(rndSecuence, numbersHistory, definedIndexes, cowContainer, definedPositions, this);
        this.stage = stage;
    }

    private int getFinalNumber(){
        return definedPositions.get((byte)0)*1000 + definedPositions.get((byte)1)*100 + definedPositions.get((byte)2)*10 + definedPositions.get((byte)3);
    }

    public String revealNumProposedByPlayer() {
        String stringNumber = "";
        if(definedPositions.containsKey((byte)0)) stringNumber = stringNumber + rndSecuence[definedPositions.get((byte)0)];
        else stringNumber = stringNumber + "X";
        if(definedPositions.containsKey((byte)1)) stringNumber = stringNumber + rndSecuence[definedPositions.get((byte)1)];
        else stringNumber = stringNumber + "X";
        if(definedPositions.containsKey((byte)2)) stringNumber = stringNumber + rndSecuence[definedPositions.get((byte)2)];
        else stringNumber = stringNumber + "X";
        if(definedPositions.containsKey((byte)3)) stringNumber = stringNumber + rndSecuence[definedPositions.get((byte)3)];
        else stringNumber = stringNumber + "X";
        return stringNumber;
    }
}
