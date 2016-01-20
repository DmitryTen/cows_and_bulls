package game.logic.gamerNumberDefiner.stage2;

import game.logic.gamerNumberDefiner.NumberDefiner;
import game.logic.gamerNumberDefiner.NumberInfo;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

/**
 * Created by Windows on 03.01.2016.
 */
public class DefinerS2MainTest {

    private NumberDefiner definerStage2;
    private Integer[] rndSecuence = new Integer[10];
    private ArrayList<NumberInfo> numbersHistory = new ArrayList<>();
    private HashMap<Integer, Boolean> definedIndexes = new HashMap<>();
    private HashMap<Byte, Integer> definedPositions = new HashMap<>();
    private HashMap<Integer, CowInfo> cowContainer = new HashMap<>();


    private void fillRndSecuence(){
        ArrayList<Integer> numbersArray = new ArrayList<>();
        for (int b=0; b<10; b++) numbersArray.add(b);
       // Collections.shuffle(numbersArray);
        rndSecuence = numbersArray.toArray(rndSecuence);
    }

    private void setCowsAmount(int cows, int bulls){
        NumberInfo numberInfo = numbersHistory.get(numbersHistory.size()-1);
        numberInfo.setCowsAmount(cows);
        numberInfo.setBullsAmount(bulls);
    }

    protected final int assembleByIndexes(int index1, int index2, int index3, int index4){
        int num = rndSecuence[index1]*1000 + rndSecuence[index2]*100 + rndSecuence[index3]*10 + rndSecuence[index4];
        numbersHistory.add(new NumberInfo(num, index1, index2, index3, index4, 0));
        return num;
    }


    private void fillDefinedDigits(boolean b, int i){
        definedIndexes.put(i, b);
    }

    @Before
    public void initiateDefiner(){
        fillRndSecuence();
        definerStage2 = new NumberDefiner(rndSecuence, numbersHistory, definedIndexes, definedPositions, cowContainer);
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

        assertEquals(1500, definerStage2.getNumber());
        setCowsAmount(0, 1);
        assertEquals(2500, definerStage2.getNumber());
        setCowsAmount(0, 2);

        assertEquals(2522, definerStage2.getNumber());
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

        assertEquals(5100, definerStage2.getNumber());
        setCowsAmount(0, 1);
        assertEquals(5280, definerStage2.getNumber());
        setCowsAmount(0, 2);
        assertEquals(583, definerStage2.getNumber());
        setCowsAmount(1, 2);
        assertEquals(588, definerStage2.getNumber());
        setCowsAmount(1, 1);

        assertEquals(5383, definerStage2.getNumber());
    }

    @Test
    public void test100100_2(){ //2528
        stage1_100100_1();

        assertEquals(5100, definerStage2.getNumber());
        setCowsAmount(1, 0);
        assertEquals(2580, definerStage2.getNumber());
        setCowsAmount(1, 2);
        assertEquals(580, definerStage2.getNumber());
        setCowsAmount(1, 1);

        assertEquals(2528, definerStage2.getNumber());
    }

    @Test
    public void test100100_3(){  //3558
        stage1_100100_2();

        assertEquals(5108, definerStage2.getNumber());
        setCowsAmount(1, 1);
        assertEquals(2508, definerStage2.getNumber());
        setCowsAmount(0, 2);
        assertEquals(3558, definerStage2.getNumber());
        setCowsAmount(0, 4);

        assertEquals(3558, definerStage2.getNumber());
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

        assertEquals(5100, definerStage2.getNumber());
        setCowsAmount(0, 2);

        assertEquals(5169, definerStage2.getNumber());
    }

    @Test
    public void test110010_2() { //5961
        stage1_110010_2();

        assertEquals(5961, definerStage2.getNumber());
        setCowsAmount(0, 4);

        assertEquals(5961, definerStage2.getNumber());
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

        assertEquals(111, definerStage2.getNumber());
        setCowsAmount(0, 0);
        assertEquals(5000, definerStage2.getNumber());
        setCowsAmount(0, 0);
        assertEquals(6000, definerStage2.getNumber());
        setCowsAmount(0, 0);
        assertEquals(7000, definerStage2.getNumber());
        setCowsAmount(0, 1);
        assertEquals(780, definerStage2.getNumber());
        setCowsAmount(0, 1);
        assertEquals(779, definerStage2.getNumber());
        setCowsAmount(0, 2);

        assertEquals(7777, definerStage2.getNumber());
    }

    @Test
    public void test0000_specialCase_2() { //8975
        stage1_0000_specialCase();

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

        assertEquals(8975, definerStage2.getNumber());
    }

    @Test
    public void test0000_specialCase_3() { //9875
        stage1_0000_specialCase();

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

        assertEquals(9875, definerStage2.getNumber());
    }

    @Test
    public void test0000_specialCase_4() { //9568
        stage1_0000_specialCase();

        assertEquals(111, definerStage2.getNumber());
        setCowsAmount(0, 0);
        assertEquals(5000, definerStage2.getNumber());
        setCowsAmount(1, 0);
        assertEquals(6500, definerStage2.getNumber());
        setCowsAmount(1, 1);
        assertEquals(7500, definerStage2.getNumber());
        setCowsAmount(0, 1);
        assertEquals(8560, definerStage2.getNumber());
        setCowsAmount(1, 2);
        assertEquals(9560, definerStage2.getNumber());
        setCowsAmount(0, 3);

        assertEquals(9568, definerStage2.getNumber());
    }

    @Test
    public void test0000_specialCase_5() { //9567
        stage1_0000_specialCase();

        assertEquals(111, definerStage2.getNumber());
        setCowsAmount(0, 0);
        assertEquals(5000, definerStage2.getNumber());
        setCowsAmount(1, 0);
        assertEquals(6500, definerStage2.getNumber());
        setCowsAmount(1, 1);
        assertEquals(7500, definerStage2.getNumber());
        setCowsAmount(1, 1);
        assertEquals(8560, definerStage2.getNumber());
        setCowsAmount(0, 2);
        assertEquals(9567, definerStage2.getNumber());
        setCowsAmount(0, 4);

        assertEquals(9567, definerStage2.getNumber());
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

        assertEquals(555, definerStage2.getNumber());
        setCowsAmount(0, 0);
        assertEquals(1000, definerStage2.getNumber());
        setCowsAmount(0, 0);
        assertEquals(2000, definerStage2.getNumber());
        setCowsAmount(0, 0);
        assertEquals(3000, definerStage2.getNumber());
        setCowsAmount(0, 1);
        assertEquals(390, definerStage2.getNumber());
        setCowsAmount(0, 1);

        assertEquals(3333, definerStage2.getNumber());
    }

    public void stage1_101010_king_size () {
        assembleByIndexes(1, 2, 3, 4);
        setCowsAmount(1, 0);
        assembleByIndexes(5, 6, 7, 8);
        setCowsAmount(1, 1);
        assembleByIndexes(9, 0, 5, 6);
        setCowsAmount(2, 0);
        assembleByIndexes(7, 8, 9, 0);
        setCowsAmount(2, 0);
        assembleByIndexes(8, 9, 0, 5);
        setCowsAmount(2, 0);
        assembleByIndexes(6, 7, 8, 9);
        setCowsAmount(1, 1);
        assembleByIndexes(9, 5, 6, 7);
        setCowsAmount(2, 1);

        fillDefinedDigits(true, 5);
        fillDefinedDigits(false, 6);
        fillDefinedDigits(true, 7);
        fillDefinedDigits(false, 8);
        fillDefinedDigits(true, 9);
        fillDefinedDigits(false, 0);
    }

    @Test
    public void test101010_king_size() { //3579
        stage1_101010_king_size();

        assertEquals(5109, definerStage2.getNumber());
        setCowsAmount(1, 1);
        assertEquals(2579, definerStage2.getNumber());
        setCowsAmount(0, 3);
        assertEquals(3579, definerStage2.getNumber());
        setCowsAmount(0, 4);

        assertEquals(3579, definerStage2.getNumber());
    }

    public void stage1_1111_special_case () {
        assembleByIndexes(1, 2, 3, 4);
        setCowsAmount(3, 1);


        fillDefinedDigits(true, 1);
        fillDefinedDigits(true, 2);
        fillDefinedDigits(true, 3);
        fillDefinedDigits(true, 4);
        fillDefinedDigits(false, 5);
        fillDefinedDigits(false, 6);
        fillDefinedDigits(false, 7);
        fillDefinedDigits(false, 8);
        fillDefinedDigits(false, 9);
        fillDefinedDigits(false, 0);

    }

    @Test
    public void test1111_special_case() { //3241
        stage1_1111_special_case();

        assertEquals(1000, definerStage2.getNumber());
        setCowsAmount(1, 0);
        assertEquals(2000, definerStage2.getNumber());
        setCowsAmount(1, 0);
        assertEquals(3000, definerStage2.getNumber());
        setCowsAmount(0, 1);
        assertEquals(3130, definerStage2.getNumber());
        setCowsAmount(1, 1);
        assertEquals(3200, definerStage2.getNumber());
        setCowsAmount(0, 2);
        assertEquals(3212, definerStage2.getNumber());
        setCowsAmount(1, 2);

        assertEquals(3241, definerStage2.getNumber());


    }


}
