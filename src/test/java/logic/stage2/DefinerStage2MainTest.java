package logic.stage2;

import logic.Definer;
import logic.NumberWithCowsAmount;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

/**
 * Created by Windows on 03.01.2016.
 */
public class DefinerStage2MainTest {

    private Definer definerStage2;
    private Integer[] rndSecuence = new Integer[10];
    ArrayList<NumberWithCowsAmount> numbersHistory;
    HashMap<Integer, Boolean> definedDigits;
    protected HashMap<Integer, CowInfo> cowContainer;
    protected HashMap<Byte, Integer> definedPositions;



    private void fillRndSecuence(){
        ArrayList<Integer> numbersArray = new ArrayList<>();
        for (int b=0; b<10; b++) numbersArray.add(b);
    //    Collections.shuffle(numbersArray);
        rndSecuence = numbersArray.toArray(rndSecuence);
    }

    private void setCowsAmount(int cows, int bulls){
        NumberWithCowsAmount numberWithCowsAmount = numbersHistory.get(numbersHistory.size()-1);
        numberWithCowsAmount.setCowsAmount(cows);
        numberWithCowsAmount.setBullsAmount(bulls);
    }

    protected final int assembleByIndexes(int index1, int index2, int index3, int index4){
        int num = rndSecuence[index1]*1000 + rndSecuence[index2]*100 + rndSecuence[index3]*10 + rndSecuence[index4];
        numbersHistory.add(new NumberWithCowsAmount(num, index1, index2, index3, index4, 0));
        return num;
    }


    private void fillDefinedDigits(boolean b, int i){
        definedDigits.put(i, b);
    }

    @Before
    public void initiateDefiner(){
        fillRndSecuence();
        numbersHistory = new ArrayList<>();
        definedDigits = new HashMap<>();
        cowContainer = new HashMap<>();
        definedPositions = new HashMap<>();

    }


    public void stage1_100000() {
        assembleByIndexes(1, 2, 3, 4);
        setCowsAmount(1, 0);
        assembleByIndexes(5, 6, 7, 8);
        setCowsAmount(1, 0);
        assembleByIndexes(9, 0, 5, 6);
        setCowsAmount(1, 0);
        assembleByIndexes(7, 8, 9, 0);
        setCowsAmount(0, 0);
        assembleByIndexes(8, 9, 0, 5);
        setCowsAmount(1, 0);

        fillDefinedDigits(true, 5);
        fillDefinedDigits(false, 6);
        fillDefinedDigits(false, 7);
        fillDefinedDigits(false, 8);
        fillDefinedDigits(false, 9);
        fillDefinedDigits(false, 0);
    }

    @Test
    public void test100000(){ //2522
        stage1_100000();
        definerStage2 = new DefinerStage2Main(rndSecuence, numbersHistory, definedDigits, cowContainer, definedPositions);
        assertEquals(1500, definerStage2.getNumber());
        setCowsAmount(0, 1);
        assertEquals(2500, definerStage2.getNumber());
        setCowsAmount(0, 2);
        assertEquals(1, definerStage2.getNumber());

    }

    public void stage1_100100_1()  {
        assembleByIndexes(1, 2, 3, 4);
        setCowsAmount(1, 0);
        assembleByIndexes(5, 6, 7, 8);
        setCowsAmount(1, 1);
        assembleByIndexes(9, 0, 5, 6);
        setCowsAmount(1, 0);
        assembleByIndexes(7, 8, 9, 0);
        setCowsAmount(1, 0);
        assembleByIndexes(8, 9, 0, 5);
        setCowsAmount(2, 0);

        fillDefinedDigits(true, 5);
        fillDefinedDigits(false, 6);
        fillDefinedDigits(false, 7);
        fillDefinedDigits(true, 8);
        fillDefinedDigits(false, 9);
        fillDefinedDigits(false, 0);
    }

    public void stage1_100100_2()  {
        assembleByIndexes(1, 2, 3, 4);
        setCowsAmount(1, 0);
        assembleByIndexes(5, 6, 7, 8);
        setCowsAmount(1, 1);
        assembleByIndexes(9, 0, 5, 6);
        setCowsAmount(0, 1);
        assembleByIndexes(7, 8, 9, 0);
        setCowsAmount(1, 0);
        assembleByIndexes(8, 9, 0, 5);
        setCowsAmount(2, 0);

        fillDefinedDigits(true, 5);
        fillDefinedDigits(false, 6);
        fillDefinedDigits(false, 7);
        fillDefinedDigits(true, 8);
        fillDefinedDigits(false, 9);
        fillDefinedDigits(false, 0);
    }

    @Test
    public void test100100_1(){  //5383
        stage1_100100_1();
        definerStage2 = new DefinerStage2Main(rndSecuence, numbersHistory, definedDigits, cowContainer, definedPositions);
        assertEquals(5100, definerStage2.getNumber());
        setCowsAmount(0, 1);
        assertEquals(5280, definerStage2.getNumber());
        setCowsAmount(0, 2);
        assertEquals(5380, definerStage2.getNumber());
        setCowsAmount(0, 3);
        assertEquals(1, definerStage2.getNumber());
    }

    @Test
    public void test100100_2(){ //2528
        stage1_100100_1();
        definerStage2 = new DefinerStage2Main(rndSecuence, numbersHistory, definedDigits, cowContainer, definedPositions);
        assertEquals(5100, definerStage2.getNumber());
        setCowsAmount(1, 0);
        assertEquals(2580, definerStage2.getNumber());
        setCowsAmount(1, 2);
        assertEquals(580, definerStage2.getNumber());
        setCowsAmount(1, 1);
        assertEquals(1, definerStage2.getNumber());
    }

    @Test
    public void test100100_3(){  //3558
        stage1_100100_2();
        definerStage2 = new DefinerStage2Main(rndSecuence, numbersHistory, definedDigits, cowContainer, definedPositions);
        assertEquals(1058, definerStage2.getNumber());
        setCowsAmount(0, 2);
        assertEquals(2058, definerStage2.getNumber());
        setCowsAmount(0, 2);
        assertEquals(3058, definerStage2.getNumber());
        setCowsAmount(0, 3);
        assertEquals(1, definerStage2.getNumber());
    }

    public void stage1_110010_1() {
        assembleByIndexes(1, 2, 3, 4);
        setCowsAmount(1, 0);
        assembleByIndexes(5, 6, 7, 8);
        setCowsAmount(1, 1);
        assembleByIndexes(9, 0, 5, 6);
        setCowsAmount(3, 0);
        assembleByIndexes(7, 8, 9, 0);
        setCowsAmount(1, 0);
        assembleByIndexes(6, 7, 8, 9);
        setCowsAmount(1, 1);

        fillDefinedDigits(true, 5);
        fillDefinedDigits(true, 6);
        fillDefinedDigits(false, 7);
        fillDefinedDigits(false, 8);
        fillDefinedDigits(true, 9);
        fillDefinedDigits(false, 0);
    }

    public void stage1_110010_2() {
        assembleByIndexes(1, 2, 3, 4);
        setCowsAmount(1, 0);
        assembleByIndexes(5, 6, 7, 8);
        setCowsAmount(1, 1);
        assembleByIndexes(9, 0, 5, 6);
        setCowsAmount(3, 0);
        assembleByIndexes(7, 8, 9, 0);
        setCowsAmount(1, 0);
        assembleByIndexes(6, 7, 8, 9);
        setCowsAmount(2, 0);

        fillDefinedDigits(true, 5);
        fillDefinedDigits(true, 6);
        fillDefinedDigits(false, 7);
        fillDefinedDigits(false, 8);
        fillDefinedDigits(true, 9);
        fillDefinedDigits(false, 0);
    }


    @Test
    public void test110010_1() { //5169
        stage1_110010_1();
        definerStage2 = new DefinerStage2Main(rndSecuence, numbersHistory, definedDigits, cowContainer, definedPositions);
        assertEquals(5100, definerStage2.getNumber());
        setCowsAmount(0, 2);
        assertEquals(1, definerStage2.getNumber());
    }

    @Test
    public void test110010_2() { //5961
        stage1_110010_2();
        definerStage2 = new DefinerStage2Main(rndSecuence, numbersHistory, definedDigits, cowContainer, definedPositions);
        assertEquals(5961, definerStage2.getNumber());
        setCowsAmount(0, 4);
        assertEquals(1, definerStage2.getNumber());
    }

    public void stage1_0000_specialCase() {
        assembleByIndexes(1, 2, 3, 4);
        setCowsAmount(0, 0);

        fillDefinedDigits(false, 1);
        fillDefinedDigits(false, 2);
        fillDefinedDigits(false, 3);
        fillDefinedDigits(false, 4);
    }

    @Test
    public void test0000_specialCase_1() { //7777
        stage1_0000_specialCase();
        definerStage2 = new DefinerStage2Main(rndSecuence, numbersHistory, definedDigits, cowContainer, definedPositions);
        assertEquals(111, definerStage2.getNumber());
        setCowsAmount(0, 0);
        assertEquals(5000, definerStage2.getNumber());
        setCowsAmount(0, 0);
        assertEquals(6000, definerStage2.getNumber());
        setCowsAmount(0, 0);
        assertEquals(7000, definerStage2.getNumber());
        setCowsAmount(0, 1);
        assertEquals(7800, definerStage2.getNumber());
        setCowsAmount(0, 1);
        assertEquals(7900, definerStage2.getNumber());
        setCowsAmount(0, 1);
        assertEquals(1, definerStage2.getNumber());
    }

    @Test
    public void test0000_specialCase_2() { //8975
        stage1_0000_specialCase();
        definerStage2 = new DefinerStage2Main(rndSecuence, numbersHistory, definedDigits, cowContainer, definedPositions);
        assertEquals(111, definerStage2.getNumber());
        setCowsAmount(0, 0);
        assertEquals(5000, definerStage2.getNumber());
        setCowsAmount(1, 0);
        assertEquals(6500, definerStage2.getNumber());
        setCowsAmount(1, 0);
        assertEquals(7050, definerStage2.getNumber());
        setCowsAmount(2, 0);
        assertEquals(8705, definerStage2.getNumber());
        setCowsAmount(1, 2);
        assertEquals(9705, definerStage2.getNumber());
        setCowsAmount(2, 1);
        assertEquals(1, definerStage2.getNumber());
        setCowsAmount(2, 1);
    }

    @Test
    public void test0000_specialCase_3() { //9875
        stage1_0000_specialCase();
        definerStage2 = new DefinerStage2Main(rndSecuence, numbersHistory, definedDigits, cowContainer, definedPositions);
        assertEquals(111, definerStage2.getNumber());
        setCowsAmount(0, 0);
        assertEquals(5000, definerStage2.getNumber());
        setCowsAmount(1, 0);
        assertEquals(6500, definerStage2.getNumber());
        setCowsAmount(1, 0);
        assertEquals(7050, definerStage2.getNumber());
        setCowsAmount(2, 0);
        assertEquals(8705, definerStage2.getNumber());
        setCowsAmount(2, 1);
        assertEquals(9875, definerStage2.getNumber());
        setCowsAmount(0, 4);
        assertEquals(1, definerStage2.getNumber());
        setCowsAmount(2, 1);
    }

    public void stage1_000010_specialCase() {
        assembleByIndexes(1, 2, 3, 4);
        setCowsAmount(1, 0);
        assembleByIndexes(5, 6, 7, 8);
        setCowsAmount(0, 0);

        fillDefinedDigits(false, 5);
        fillDefinedDigits(false, 6);
        fillDefinedDigits(false, 7);
        fillDefinedDigits(false, 8);
    }

    @Test
    public void test000010_specialCase() { //3333
        stage1_000010_specialCase();
        definerStage2 = new DefinerStage2Main(rndSecuence, numbersHistory, definedDigits, cowContainer, definedPositions);
        assertEquals(555, definerStage2.getNumber());
        setCowsAmount(0, 0);
        assertEquals(1000, definerStage2.getNumber());
        setCowsAmount(0, 0);
        assertEquals(2000, definerStage2.getNumber());
        setCowsAmount(0, 0);
        assertEquals(3000, definerStage2.getNumber());
        setCowsAmount(0, 1);
        assertEquals(3900, definerStage2.getNumber());
        setCowsAmount(0, 1);
        assertEquals(1, definerStage2.getNumber());
    }

}
