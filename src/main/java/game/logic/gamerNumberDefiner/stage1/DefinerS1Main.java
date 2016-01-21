package game.logic.gamerNumberDefiner.stage1;

import game.logic.gamerNumberDefiner.NumberDefiner;
import game.logic.gamerNumberDefiner.NumberInfo;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Windows on 19.12.2015.
 */
public class DefinerS1Main extends DefinerS1 {

    private int [] x = {-1, 5, 6};
    private int [] y = {-1, 7, 8};
    private int [] z = {-1, 9, 0};
    private DefinerS1 definerS1Deep1;


    public DefinerS1Main(Integer[] rndSecuence,
                         ArrayList<NumberInfo> numbersHistory,
                         HashMap<Integer, Boolean> definedDigits,
                         NumberDefiner numberDefiner)   {
        super(rndSecuence, numbersHistory, definedDigits, numberDefiner);
    }

    public int getNumber (){
        if (numbersHistory.size()==0) return assembleByIndexes(1,2,3,4);

        if (getRes(numbersHistory.size() - 1)==0 && numbersHistory.size()<=2) return goToAnotherStageFalse();
        if (getRes(0)==4) return goToAnotherStageTrue();

        if (numbersHistory.size()==1) return assembleByIndexes(x[1], x[2], y[1], y[2]);
        if (numbersHistory.size()==2) return assembleByIndexes(z[1],z[2],x[1],x[2]);
        if (numbersHistory.size()==3) return checkOnSpecialCasesAndAssemble();

        if (definerS1Deep1 == null) {
            x[0] = (getRes(1) - getRes(3) + getRes(2)) / 2;
            y[0] = (getRes(1) - getRes(2) + getRes(3)) / 2;
            z[0] = (getRes(3) - getRes(1) + getRes(2)) / 2;
            definerS1Deep1 = new DefinerS1Deep1(rndSecuence, numbersHistory, definedIndexes, numbersHistory.size(), numberDefiner);
            definerS1Deep1.setVariables(x, y, z);
        }

        return definerS1Deep1.getNumber();
    }

    private int checkOnSpecialCasesAndAssemble() {
        if ((getRes(1)-getRes(2)) == 2) return getCase1();
        if ((getRes(2)-getRes(1)) == 2) return getCase2();
        if (getRes(2) == 0) return getCase3();
        if (getRes(0) + getRes(1) == 4) return getCase4();

        return assembleByIndexes(y[1], y[2], z[1], z[2]);
    }

    private int getCase1() {
        y[0] = 2;
        z[0] = 0;
        x[0] = getRes(2);
        definerS1Deep1 = new DefinerS1Deep1(rndSecuence, numbersHistory, definedIndexes, numbersHistory.size(), numberDefiner);
        definerS1Deep1.setVariables(x,y,z);
        return definerS1Deep1.getNumber();
    }

    private int getCase2() {
        y[0] = 0;
        z[0] = 2;
        x[0] = getRes(1);
        definerS1Deep1 = new DefinerS1Deep1(rndSecuence, numbersHistory, definedIndexes, numbersHistory.size(), numberDefiner);
        definerS1Deep1.setVariables(x, y, z);
        return definerS1Deep1.getNumber();
    }

    private int getCase3() {
        y[0] = getRes(1);
        z[0] = 0;
        x[0] = 0;
        definerS1Deep1 = new DefinerS1Deep1(rndSecuence, numbersHistory, definedIndexes, numbersHistory.size(), numberDefiner);
        definerS1Deep1.setVariables(x, y, z);
        return definerS1Deep1.getNumber();
    }

    private int getCase4(){
        z[0] = 0;
        x[0] = getRes(2);
        y[0] = getRes(1) - getRes(2);
        definerS1Deep1 = new DefinerS1Deep1(rndSecuence, numbersHistory, definedIndexes, numbersHistory.size(), numberDefiner);
        definerS1Deep1.setVariables(x, y, z);
        return definerS1Deep1.getNumber();
    }

    private int goToAnotherStageFalse() {
        putDefinedDigit(numbersHistory.get(numbersHistory.size() - 1).getIndexOfPosition1(), false);
        putDefinedDigit(numbersHistory.get(numbersHistory.size() - 1).getIndexOfPosition2(), false);
        putDefinedDigit(numbersHistory.get(numbersHistory.size() - 1).getIndexOfPosition3(), false);
        putDefinedDigit(numbersHistory.get(numbersHistory.size() - 1).getIndexOfPosition4(), false);

        toLogResDefinedDigits("1");
        numberDefiner.setStage(2);
        return numberDefiner.getNumber();
    }

    private int goToAnotherStageTrue() {
        putDefinedDigit(1, true);
        putDefinedDigit(2, true);
        putDefinedDigit(3, true);
        putDefinedDigit(4, true);
        putDefinedDigit(5, false);
        putDefinedDigit(6, false);
        putDefinedDigit(7, false);
        putDefinedDigit(8, false);
        putDefinedDigit(9, false);
        putDefinedDigit(0, false);

        toLogResDefinedDigits("1");
        numberDefiner.setStage(2);
        return numberDefiner.getNumber();
    }

}
