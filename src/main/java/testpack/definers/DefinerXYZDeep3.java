package testpack.definers;

import testpack.NumberFactory;

/**
 * Created by Windows on 22.12.2015.
 */
public class DefinerXYZDeep3 extends Definer2{

    private int x[] = new int[3];
    private int y[] = new int[3];
    private int z[] = new int[3];
    private int x85[];
    private int y67[];
    private int z90[];
    private int x69[];
    private int y05[];
    private int z78[];

    private DefinerXYZ definerXYZ;

    public DefinerXYZDeep3(int[] x, int[] y, int[] z, int[] x85, int[] y67, int[] z90, int[] x69, int[] y05, int[] z78){
        this.x=x;
        this.y=y;
        this.z=z;
        this.x85=x85;
        this.y67=y67;
        this.z90=z90;
        this.x69=x69;
        this.y05=y05;
        this.z78=z78;

    }

    @Override
    public int getNumber() throws DefinerException{
        if(stepNumber==numbersHistory.size()-1) handleResults();
        else getNumberFromCurrentDefiner();
    }

    private int handleResults(){
        x69[0] = getRes(stepNumber)-z78[0];
        y05[0] = x[0] + z[0] - x69[0];
        definerXYZ = new DefinerXYZ(x,y,z,x85,y67);
        definerXYZ.setStepNumber(numbersHistory.size());
        definerXYZ.getNumber();
    }

    private int getNumberFromCurrentDefiner() throws DefinerException{
        if (x[0]==1 && y[0]==1 && x85[0]==1){
            if (x[0]==1 && y[0]==1 && z[0]==1) return get111Number(z,x,y);
            return defineResults11(x, y, z);
        }

        if (y[0]==1 && z[0]==1 && x85[0]==1){
            return defineResults11(y, z, x);
        }

        if (z[0]==1 && x[0]==1 && x85[0]==1){
            return defineResults11(z, x, y);
        }


    }

    private int defineResults11(int[] xx, int[] yy, int[] zz) throws DefinerException{
        if (x85[0]==1 && x69[0]==0) {
            putIntoDefinedDigits(x69);
            putIntoDefinedDigits(y05);


        }
        if (x85[0]==1 && x69[0]==2) {
            putIntoDefinedDigits();


        }
        if (x85[0]==1 && x69[0]==1) {
            putIntoDefinedDigits();
        }



        NumberFactory n = NumberFactory.getInstance();
        n.setStage(2);
        return n.getARandomNumber();
    }

    private int get111Number(int[] equals1X, int[] equals1Y, int[] someZ){
        x69 = new int[3];
        y05 = new int[3];
        z78 = someZ;
        x69[1] = equals1Y[2];
        x69[2] = equals1X[1];
        y05[1] = equals1X[2];
        y05[2] = equals1Y[1];
        return assembleByIndexes(equals1Y[2], someZ[1], someZ[2], equals1X[1]);
    }

    private void putIntoDefinedDigits(int[] x){
        if (x[0]==0) {
            definedDigits.put(x[1], false);
            definedDigits.put(x[2], false);
        }
        if (x[0]==2) {
            definedDigits.put(x[1], true);
            definedDigits.put(x[2], true);
        }
    }



}
