package testpack.definers;

import testpack.NumberFactory;

/**
 * Created by Windows on 22.12.2015.
 */
public class DefinerXYZDeep2 extends Definer2{

    private int x[] = new int[3];
    private int y[] = new int[3];
    private int z[] = new int[3];

    private int x85[];
    private int y67[];
    private int z90[];

    private int x69[];
    private int y05[];
    private int z78[];

    private Definer2 definerXYZ;

    public DefinerXYZDeep2(int[] x, int[] y, int[] z, int[] x85, int[] y67, int[] z90){
        this.x=x;
        this.y=y;
        this.z=z;
        this.x85=x85;
        this.y67=y67;
        this.z90=z90;

    }

    @Override
    public int getNumber() throws DefinerException{
        if(stepNumber==numbersHistory.size()-1) return handleResults();
        else return getNumberFromCurrentDefiner();
    }

    private int handleResults() throws DefinerException{
        x69[0] = getRes(stepNumber)-z78[0];
        y05[0] = x[0] + z[0] - x69[0];
        definerXYZ = new DefinerXYZDeep3(x,y,z,x85,y67,z90,x69,y05,z78);
        definerXYZ.setStepNumber(numbersHistory.size());
        return definerXYZ.getNumber();
    }

    private int getNumberFromCurrentDefiner() throws DefinerException{
        if (x[0]==1 && y[0]==1){
//            if (x[0]==1 && y[0]==1 && z[0]==1) return get111Number(z,x,y);
            return defineResults11(x, y, z);
        }

        if (y[0]==1 && z[0]==1){
            return defineResults11(y, z, x);
        }

        if (z[0]==1 && x[0]==1){
            return defineResults11(z, x, y);
        }

        if (x[0]==1){
            putIntoDefinedDigits(y);
            putIntoDefinedDigits(z);
            return defineResults1(x, y, z);
        }

        if (y[0]==1) {
            putIntoDefinedDigits(x);
            putIntoDefinedDigits(z);
            return defineResults1(y, z, x);
        }

        if (z[0]==1){
            putIntoDefinedDigits(x);
            putIntoDefinedDigits(y);
            return defineResults1(z, x, y);
        }
        return -10;
    }

    private int defineResults11(int[] xx, int[] yy, int[] zz) throws DefinerException{
 //       if (z90[0]==1) throw new DefinerException("Œ¯Ë·Í‡");
        if (x85[0]==0) {
            putIntoDefinedDigits(x85);
            putIntoDefinedDigits(y67);
            putIntoDefinedDigits(z90);
        }
        if (x85[0]==2) {
            putIntoDefinedDigits(x85);
            putIntoDefinedDigits(y67);
            putIntoDefinedDigits(z90);
        }
        if (x85[0]==1) {
            return get111Number(zz, xx, yy);
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

    private int defineResults1(int[] xx, int[] yy, int[] zz) throws DefinerException{
        if (yy[0]==2){
            if (x85[0]-1-z90[0]==1) {
                definedDigits.put(xx[1], true);
                definedDigits.put(xx[2], false);
            }
            if (x85[0]-1-z90[0]==0) {
                definedDigits.put(xx[1], false);
                definedDigits.put(xx[2], true);
            }
        }
        if (yy[0]==0){
            if (x85[0]-0-z90[0]==1) {
                definedDigits.put(xx[1], true);
                definedDigits.put(xx[2], false);
            }
            if (x85[0]-0-z90[0]==0) {
                definedDigits.put(xx[1], false);
                definedDigits.put(xx[2], true);
            }
        }
        NumberFactory n = NumberFactory.getInstance();
        n.setStage(2);
        return n.getARandomNumber();
    }


}
