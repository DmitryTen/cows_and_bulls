package testpack;

/**
 * Created by Windows on 18.12.2015.
 */
public class NumberWithCowsAmount {

    private int num;
    private int cowsAmount;
    private int bullsAmount;
    private int stepNumber;
    private int index1;
    private int index2;
    private int index3;
    private int index4;

    private static int counter;

    public int getNum() {
        return num;
    }

    public NumberWithCowsAmount(int num, int index1, int index2, int index3, int index4) {
        this.num = num;
        this.index1 = index1;
        this.index2 = index2;
        this.index3 = index3;
        this.index4 = index4;
        stepNumber=counter;
        counter++;
    }

    public int getCowsAmount() {
        return cowsAmount;
    }

    public void setCowsAmount(int cowsAmount) {
        this.cowsAmount = cowsAmount;
    }

    public int getBullsAmount() {
        return bullsAmount;
    }

    public void setBullsAmount(int bullsAmount) {
        this.bullsAmount = bullsAmount;
    }

    public int getResultSum() {

        return (cowsAmount + bullsAmount);
    }

    public int getIndex1() {
        return index1;
    }

    public int getIndex2() {
        return index2;
    }

    public int getIndex3() {
        return index3;
    }

    public int getIndex4() {
        return index4;
    }


}
