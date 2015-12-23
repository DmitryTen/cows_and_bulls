package testpack.definers;

import testpack.NumberFactory;

/**
 * Created by Windows on 19.12.2015.
 */
public class DefinerMain extends Definer {

    private int [] x = {-1, 5, 6};
    private int [] y = {-1, 7, 8};
    private int [] z = {-1, 9, 0};
    private Definer definer;


    public DefinerMain() {
    }

    public int getNumber ()throws DefinerException{
        if (numbersHistory.size()==0) return assembleByIndexes(1,2,3,4);

        if (getRes(numbersHistory.size() - 1)==0) return getNumFromAnotherStage(false);
        if (getRes(numbersHistory.size() - 1)==4) return getNumFromAnotherStage(true);

        if (numbersHistory.size()==1) return assembleByIndexes(x[1],x[2],y[1],y[2]);
        if (numbersHistory.size()==2) return assembleByIndexes(z[1],z[2],x[1],x[2]);
        if (numbersHistory.size()==3) return checkOnYAndZDifference();
        if (numbersHistory.size()==4) initiateXYZ();
        return definer.getNumber();

    }

    private int checkOnYAndZDifference() throws DefinerException{
        if ((getRes(1)-getRes(2))!= 2 || (getRes(2)-getRes(1))!=2) return assembleByIndexes(y[1],y[2],z[1],z[2]);
        else {
            if ((getRes(2)-getRes(1))==2){
                y[0] = 0;
                z[0] = 2;
                x[0] = getRes(1);
                definer = new DefinerDeep1(x,y,z);
                definer.setStepNumber(numbersHistory.size());
                return definer.getNumber();
            }
            if (getRes(1)-getRes(2)==2){
                y[0] = 2;
                z[0] = 0;
                x[0] = getRes(2);
                definer = new DefinerDeep1(x,y,z);
                definer.setStepNumber(numbersHistory.size());
                return definer.getNumber();
            }
        }
        return 0;
    }

    private void initiateXYZ(){
        if (definer==null) {
            x[0] = (getRes(1) - getRes(3) + getRes(2)) / 2;
            y[0] = (getRes(1) - getRes(2) + getRes(3)) / 2;
            z[0] = (getRes(3) - getRes(1) + getRes(2)) / 2;
            definer = new DefinerDeep1(x,y,z);
            definer.setStepNumber(numbersHistory.size());
        }
    }

    private int getNumFromAnotherStage(boolean res) throws DefinerException{
        definedDigits.put(numbersHistory.get(numbersHistory.size()-1).getIndex1(), res);
        definedDigits.put(numbersHistory.get(numbersHistory.size()-1).getIndex2(), res);
        definedDigits.put(numbersHistory.get(numbersHistory.size()-1).getIndex3(), res);
        definedDigits.put(numbersHistory.get(numbersHistory.size()-1).getIndex4(), res);

        NumberFactory factory = NumberFactory.getInstance();
        if (res) factory.setStage(3);
        else factory.setStage(2);

        return factory.getARandomNumber();
    }

}
