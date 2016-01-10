package logic.stage1;

import logic.NumberFactory;
import logic.NumberWithCowsAmount;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Windows on 19.12.2015.
 */
public class DefinerStage1Main extends DefinerStage1 {

    private int [] x = {-1, 5, 6};
    private int [] y = {-1, 7, 8};
    private int [] z = {-1, 9, 0};
    private DefinerStage1 definerStage1Deep1;


    public DefinerStage1Main(Integer[] rndSecuence, ArrayList<NumberWithCowsAmount> numbersHistory, HashMap<Integer, Boolean> definedDigits){
        super(rndSecuence, numbersHistory, definedDigits, 0);
    }

    public int getNumber (){
        if (numbersHistory.size()==0) return assembleByIndexes(1,2,3,4);

        if (getRes(numbersHistory.size() - 1)==0 && numbersHistory.size()<=2) return goToAnotherStage(false);
        if (getRes(numbersHistory.size() - 1)==4 && numbersHistory.size()==1) return goToAnotherStage(true);

        if (numbersHistory.size()==1) return assembleByIndexes(x[1], x[2], y[1], y[2]);
        if (numbersHistory.size()==2) return assembleByIndexes(z[1],z[2],x[1],x[2]);
        if (numbersHistory.size()==3) return checkOnSpecialCasesAndAssemble();

        if (definerStage1Deep1 == null) {
            x[0] = (getRes(1) - getRes(3) + getRes(2)) / 2;
            y[0] = (getRes(1) - getRes(2) + getRes(3)) / 2;
            z[0] = (getRes(3) - getRes(1) + getRes(2)) / 2;
            definerStage1Deep1 = new DefinerStage1Deep1(rndSecuence, numbersHistory, definedIndexes, numbersHistory.size());
            definerStage1Deep1.setVariables(x, y, z);
        }

        return definerStage1Deep1.getNumber();
    }

    private int checkOnSpecialCasesAndAssemble() {
        if ((getRes(1)-getRes(2)) == 2) return getCase1();
        if ((getRes(2)-getRes(1)) == 2) return getCase2();
        if (getRes(2) == 0) return getCase3();

        return assembleByIndexes(y[1], y[2], z[1], z[2]);
    }

    private int getCase1() {
        y[0] = 2;
        z[0] = 0;
        x[0] = getRes(2);
        definerStage1Deep1 = new DefinerStage1Deep1(rndSecuence, numbersHistory, definedIndexes, numbersHistory.size());
        definerStage1Deep1.setVariables(x,y,z);
        return definerStage1Deep1.getNumber();
    }

    private int getCase2() {
        y[0] = 0;
        z[0] = 2;
        x[0] = getRes(1);
        definerStage1Deep1 = new DefinerStage1Deep1(rndSecuence, numbersHistory, definedIndexes, numbersHistory.size());
        definerStage1Deep1.setVariables(x, y, z);
        return definerStage1Deep1.getNumber();
    }

    private int getCase3() {
        y[0] = getRes(1);
        z[0] = 0;
        x[0] = 0;
        definerStage1Deep1 = new DefinerStage1Deep1(rndSecuence, numbersHistory, definedIndexes, numbersHistory.size());
        definerStage1Deep1.setVariables(x, y, z);
        return definerStage1Deep1.getNumber();
    }

    private int goToAnotherStage(boolean res) {
        putDefinedDigit(numbersHistory.get(numbersHistory.size() - 1).getIndexOfPosition1(), res);
        putDefinedDigit(numbersHistory.get(numbersHistory.size() - 1).getIndexOfPosition2(), res);
        putDefinedDigit(numbersHistory.get(numbersHistory.size() - 1).getIndexOfPosition3(), res);
        putDefinedDigit(numbersHistory.get(numbersHistory.size() - 1).getIndexOfPosition4(), res);

        toLogResDefinedDigits("1");
        NumberFactory factory = NumberFactory.getInstance();
        if (res) factory.setStage(3);
        else factory.setStage(2);

        return factory.getNumber();
    }

}
