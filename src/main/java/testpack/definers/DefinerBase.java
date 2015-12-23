package testpack.definers;

import java.util.ArrayList;

/**
 * Created by Windows on 19.12.2015.
 */
public class DefinerBase extends Definer2 {

    private ArrayList<Integer> a = new ArrayList<>();

    private int [] x = {-1, 5, 6};
    private int [] y = {-1, 7, 8};
    private int [] z = {-1, 9, 0};
    private Definer2 definer;


    public DefinerBase() {

    }

    public int getNumber ()throws DefinerException{

        if (numbersHistory.size()==0) return assembleByIndexes(1,2,3,4);
        if (numbersHistory.size()==1) return assembleByIndexes(x[1],x[2],y[1],y[2]);
        if (numbersHistory.size()==2) return assembleByIndexes(z[1],z[2],x[1],x[2]);
        if (numbersHistory.size()==3) return initiateABC();
        if (numbersHistory.size()==4) initiateXYZ();
        return definer.getNumber();

    }

    private int initiateABC() throws DefinerException{
        a.add(numbersHistory.get(0).getResultSum());
        a.add(numbersHistory.get(1).getResultSum());
        a.add(numbersHistory.get(2).getResultSum());
        if ((a.get(1)-a.get(2))!= 2 || (a.get(2)-a.get(1))!=2) return assembleByIndexes(y[1],y[2],z[1],z[2]);
        else {
            if ((a.get(2)-a.get(1))==2){
                y[0] = 0;
                z[0] = 2;
                x[0] = numbersHistory.get(1).getResultSum();
                definer = new DefinerXYZ(x,y,z);
                definer.setStepNumber(numbersHistory.size());
                return definer.getNumber();
            }
            if (a.get(1)-a.get(2)==2){
                y[0] = 2;
                z[0] = 0;
                x[0] = numbersHistory.get(2).getResultSum();
                definer = new DefinerXYZ(x,y,z);
                definer.setStepNumber(numbersHistory.size());
                return definer.getNumber();
            }
        }
        return 0;
    }

    private void initiateXYZ(){
        if (definer==null) {
            a.add(numbersHistory.get(3).getResultSum());
            x[0] = (a.get(1) - a.get(3) + a.get(2)) / 2;
            y[0] = (a.get(1) - a.get(2) + a.get(3)) / 2;
            z[0] = (a.get(3) - a.get(1) + a.get(2)) / 2;
            definer = new DefinerXYZ(x,y,z);
            definer.setStepNumber(numbersHistory.size());
        }
    }


}
