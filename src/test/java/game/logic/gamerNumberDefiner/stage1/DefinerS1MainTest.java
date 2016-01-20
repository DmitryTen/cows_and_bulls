package game.logic.gamerNumberDefiner.stage1;

import game.logic.gamerNumberDefiner.NumberDefiner;
import game.logic.gamerNumberDefiner.NumberInfo;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

/**
 * Created by Windows on 24.12.2015.
 *
    Чтобы данный тест функционировал нужно закомментировать строку 55 в NumberDefiner:
    if (stage==2) {
 // return definer.getNumber();
    }
 *
 */
public class DefinerS1MainTest {

    private NumberDefiner stage1Test;
    private ArrayList<NumberInfo> numbersHistory = new ArrayList<>();
    private HashMap<Integer, Boolean> definedIndexes = new HashMap<>();

    private Integer[] rndSecuence = new Integer[10];

    private void fillRndSecuence(){
        ArrayList<Integer> numbersArray = new ArrayList<>();
        for (int b=0; b<10; b++) numbersArray.add(b);
        //Collections.shuffle(numbersArray);
        rndSecuence = numbersArray.toArray(rndSecuence);
    }

    private void setCowsAmount(int cows, int bulls){
        NumberInfo numberInfo = numbersHistory.get(numbersHistory.size()-1);
        numberInfo.setCowsAmount(cows);
        numberInfo.setBullsAmount(bulls);
    }

    protected final int assembleByIndexes(int index1, int index2, int index3, int index4){
        int num = rndSecuence[index1]*1000 + rndSecuence[index2]*100 + rndSecuence[index3]*10 + rndSecuence[index4];
        return num;
    }

    @Before
    public void initiateDefiner(){
        fillRndSecuence();
        stage1Test = new NumberDefiner(rndSecuence, numbersHistory, definedIndexes);
    }


    @Test
    public void test100000 () {
        assertEquals(assembleByIndexes(1, 2, 3, 4), stage1Test.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(5, 6, 7, 8), stage1Test.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(9, 0, 5, 6), stage1Test.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(7, 8, 9, 0), stage1Test.getNumber());
        setCowsAmount(0, 0);
        assertEquals(assembleByIndexes(8, 9, 0, 5), stage1Test.getNumber());
        setCowsAmount(1, 0);
        stage1Test.getNumber();

        assertEquals(true, definedIndexes.get(5));
        assertEquals(false, definedIndexes.get(6));
        assertEquals(false, definedIndexes.get(7));
        assertEquals(false, definedIndexes.get(8));
        assertEquals(false, definedIndexes.get(9));
        assertEquals(false, definedIndexes.get(0));

    }

    @Test
    public void test010000 () {
        assertEquals(assembleByIndexes(1, 2, 3, 4), stage1Test.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(5, 6, 7, 8), stage1Test.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(9, 0, 5, 6), stage1Test.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(7, 8, 9, 0), stage1Test.getNumber());
        setCowsAmount(0, 0);
        assertEquals(assembleByIndexes(8, 9, 0, 5), stage1Test.getNumber());
        setCowsAmount(0, 0);
        stage1Test.getNumber();

        assertEquals(false, definedIndexes.get(5));
        assertEquals(true, definedIndexes.get(6));
        assertEquals(false, definedIndexes.get(7));
        assertEquals(false, definedIndexes.get(8));
        assertEquals(false, definedIndexes.get(9));
        assertEquals(false, definedIndexes.get(0));
    }

    @Test
    public void test001000 () {
        assertEquals(assembleByIndexes(1, 2, 3, 4), stage1Test.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(5, 6, 7, 8), stage1Test.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(9, 0, 5, 6), stage1Test.getNumber());
        setCowsAmount(0, 0);
        assertEquals(assembleByIndexes(0, 5, 6, 7), stage1Test.getNumber());
        setCowsAmount(1, 0);
        stage1Test.getNumber();

        assertEquals(false, definedIndexes.get(5));
        assertEquals(false, definedIndexes.get(6));
        assertEquals(true, definedIndexes.get(7));
        assertEquals(false, definedIndexes.get(8));
        assertEquals(false, definedIndexes.get(9));
        assertEquals(false, definedIndexes.get(0));
    }

    @Test
    public void test000100 () {
        assertEquals(assembleByIndexes(1, 2, 3, 4), stage1Test.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(5, 6, 7, 8), stage1Test.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(9, 0, 5, 6), stage1Test.getNumber());
        setCowsAmount(0, 0);
        assertEquals(assembleByIndexes(0, 5, 6, 7), stage1Test.getNumber());
        setCowsAmount(0, 0);
        stage1Test.getNumber();

        assertEquals(false, definedIndexes.get(5));
        assertEquals(false, definedIndexes.get(6));
        assertEquals(false, definedIndexes.get(7));
        assertEquals(true, definedIndexes.get(8));
        assertEquals(false, definedIndexes.get(9));
        assertEquals(false, definedIndexes.get(0));
    }

    @Test
    public void test000010_specialCase () {
        assertEquals(assembleByIndexes(1, 2, 3, 4), stage1Test.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(5, 6, 7, 8), stage1Test.getNumber());
        setCowsAmount(0, 0);

        stage1Test.getNumber();

        assertEquals(false, definedIndexes.get(5));
        assertEquals(false, definedIndexes.get(6));
        assertEquals(false, definedIndexes.get(7));
        assertEquals(false, definedIndexes.get(8));
    }

    @Test
    public void test110000 () {
        assertEquals(assembleByIndexes(1, 2, 3, 4), stage1Test.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(5, 6, 7, 8), stage1Test.getNumber());
        setCowsAmount(2, 0);
        assertEquals(assembleByIndexes(9, 0, 5, 6), stage1Test.getNumber());
        setCowsAmount(2, 0);
        assertEquals(assembleByIndexes(7, 8, 9, 0), stage1Test.getNumber());
        setCowsAmount(0, 0);
        stage1Test.getNumber();

        assertEquals(true, definedIndexes.get(5));
        assertEquals(true, definedIndexes.get(6));
        assertEquals(false, definedIndexes.get(7));
        assertEquals(false, definedIndexes.get(8));
        assertEquals(false, definedIndexes.get(9));
        assertEquals(false, definedIndexes.get(0));
    }

    @Test
    public void test101000 () {
        assertEquals(assembleByIndexes(1, 2, 3, 4), stage1Test.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(5, 6, 7, 8), stage1Test.getNumber());
        setCowsAmount(2, 0);
        assertEquals(assembleByIndexes(9, 0, 5, 6), stage1Test.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(7, 8, 9, 0), stage1Test.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(8, 9, 0, 5), stage1Test.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(6, 7, 8, 9), stage1Test.getNumber());
        setCowsAmount(1, 0);
        stage1Test.getNumber();

        assertEquals(true, definedIndexes.get(5));
        assertEquals(false, definedIndexes.get(6));
        assertEquals(true, definedIndexes.get(7));
        assertEquals(false, definedIndexes.get(8));
        assertEquals(false, definedIndexes.get(9));
        assertEquals(false, definedIndexes.get(0));
    }

    @Test
    public void test100100 () {
        assertEquals(assembleByIndexes(1, 2, 3, 4), stage1Test.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(5, 6, 7, 8), stage1Test.getNumber());
        setCowsAmount(2, 0);
        assertEquals(assembleByIndexes(9, 0, 5, 6), stage1Test.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(7, 8, 9, 0), stage1Test.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(8, 9, 0, 5), stage1Test.getNumber());
        setCowsAmount(2, 0);
        stage1Test.getNumber();

        assertEquals(true, definedIndexes.get(5));
        assertEquals(false, definedIndexes.get(6));
        assertEquals(false, definedIndexes.get(7));
        assertEquals(true, definedIndexes.get(8));
        assertEquals(false, definedIndexes.get(9));
        assertEquals(false, definedIndexes.get(0));
    }

    @Test
    public void test100010 () {
        assertEquals(assembleByIndexes(1, 2, 3, 4), stage1Test.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(5, 6, 7, 8), stage1Test.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(9, 0, 5, 6), stage1Test.getNumber());
        setCowsAmount(2, 0);
        assertEquals(assembleByIndexes(7, 8, 9, 0), stage1Test.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(6, 7, 8, 9), stage1Test.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(0, 5, 6, 7), stage1Test.getNumber());
        setCowsAmount(1, 0);
        stage1Test.getNumber();

        assertEquals(true, definedIndexes.get(5));
        assertEquals(false, definedIndexes.get(6));
        assertEquals(false, definedIndexes.get(7));
        assertEquals(false, definedIndexes.get(8));
        assertEquals(true, definedIndexes.get(9));
        assertEquals(false, definedIndexes.get(0));
    }

    @Test
    public void test100001 () {
        assertEquals(assembleByIndexes(1, 2, 3, 4), stage1Test.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(5, 6, 7, 8), stage1Test.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(9, 0, 5, 6), stage1Test.getNumber());
        setCowsAmount(2, 0);
        assertEquals(assembleByIndexes(7, 8, 9, 0), stage1Test.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(6, 7, 8, 9), stage1Test.getNumber());
        setCowsAmount(0, 0);
        stage1Test.getNumber();

        assertEquals(true, definedIndexes.get(5));
        assertEquals(false, definedIndexes.get(6));
        assertEquals(false, definedIndexes.get(7));
        assertEquals(false, definedIndexes.get(8));
        assertEquals(false, definedIndexes.get(9));
        assertEquals(true, definedIndexes.get(0));
    }

    @Test
    public void test010100 () {
        assertEquals(assembleByIndexes(1, 2, 3, 4), stage1Test.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(5, 6, 7, 8), stage1Test.getNumber());
        setCowsAmount(2, 0);
        assertEquals(assembleByIndexes(9, 0, 5, 6), stage1Test.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(7, 8, 9, 0), stage1Test.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(8, 9, 0, 5), stage1Test.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(6, 7, 8, 9), stage1Test.getNumber());
        setCowsAmount(2, 0);
        stage1Test.getNumber();

        assertEquals(false, definedIndexes.get(5));
        assertEquals(true, definedIndexes.get(6));
        assertEquals(false, definedIndexes.get(7));
        assertEquals(true, definedIndexes.get(8));
        assertEquals(false, definedIndexes.get(9));
        assertEquals(false, definedIndexes.get(0));
    }

    @Test
    public void test111000 () {
        assertEquals(assembleByIndexes(1, 2, 3, 4), stage1Test.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(5, 6, 7, 8), stage1Test.getNumber());
        setCowsAmount(3, 0);
        assertEquals(assembleByIndexes(9, 0, 5, 6), stage1Test.getNumber());
        setCowsAmount(2, 0);
        assertEquals(assembleByIndexes(0, 5, 6, 7), stage1Test.getNumber());
        setCowsAmount(3, 0);
        stage1Test.getNumber();


        stage1Test.getNumber();

        assertEquals(true, definedIndexes.get(5));
        assertEquals(true, definedIndexes.get(6));
        assertEquals(true, definedIndexes.get(7));
        assertEquals(false, definedIndexes.get(8));
        assertEquals(false, definedIndexes.get(9));
        assertEquals(false, definedIndexes.get(0));
    }

    @Test
    public void test110100 () {
        assertEquals(assembleByIndexes(1, 2, 3, 4), stage1Test.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(5, 6, 7, 8), stage1Test.getNumber());
        setCowsAmount(3, 0);
        assertEquals(assembleByIndexes(9, 0, 5, 6), stage1Test.getNumber());
        setCowsAmount(2, 0);
        assertEquals(assembleByIndexes(0, 5, 6, 7), stage1Test.getNumber());
        setCowsAmount(2, 0);
        stage1Test.getNumber();

        assertEquals(true, definedIndexes.get(5));
        assertEquals(true, definedIndexes.get(6));
        assertEquals(false, definedIndexes.get(7));
        assertEquals(true, definedIndexes.get(8));
        assertEquals(false, definedIndexes.get(9));
        assertEquals(false, definedIndexes.get(0));
    }

    @Test
    public void test110010 () {
        assertEquals(assembleByIndexes(1, 2, 3, 4), stage1Test.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(5, 6, 7, 8), stage1Test.getNumber());
        setCowsAmount(2, 0);
        assertEquals(assembleByIndexes(9, 0, 5, 6), stage1Test.getNumber());
        setCowsAmount(3, 0);
        assertEquals(assembleByIndexes(7, 8, 9, 0), stage1Test.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(6, 7, 8, 9), stage1Test.getNumber());
        setCowsAmount(2, 0);
        stage1Test.getNumber();

        assertEquals(true, definedIndexes.get(5));
        assertEquals(true, definedIndexes.get(6));
        assertEquals(false, definedIndexes.get(7));
        assertEquals(false, definedIndexes.get(8));
        assertEquals(true, definedIndexes.get(9));
        assertEquals(false, definedIndexes.get(0));
    }

    @Test
    public void test110001 () {
        assertEquals(assembleByIndexes(1, 2, 3, 4), stage1Test.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(5, 6, 7, 8), stage1Test.getNumber());
        setCowsAmount(2, 0);
        assertEquals(assembleByIndexes(9, 0, 5, 6), stage1Test.getNumber());
        setCowsAmount(3, 0);
        assertEquals(assembleByIndexes(7, 8, 9, 0), stage1Test.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(6, 7, 8, 9), stage1Test.getNumber());
        setCowsAmount(1, 0);
        stage1Test.getNumber();

        assertEquals(true, definedIndexes.get(5));
        assertEquals(true, definedIndexes.get(6));
        assertEquals(false, definedIndexes.get(7));
        assertEquals(false, definedIndexes.get(8));
        assertEquals(false, definedIndexes.get(9));
        assertEquals(true, definedIndexes.get(0));
    }

    @Test
    public void test101100 () {
        assertEquals(assembleByIndexes(1, 2, 3, 4), stage1Test.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(5, 6, 7, 8), stage1Test.getNumber());
        setCowsAmount(3, 0);
        assertEquals(assembleByIndexes(9, 0, 5, 6), stage1Test.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(8, 9, 0, 5), stage1Test.getNumber());
        setCowsAmount(2, 0);
        stage1Test.getNumber();

        assertEquals(true, definedIndexes.get(5));
        assertEquals(false, definedIndexes.get(6));
        assertEquals(true, definedIndexes.get(7));
        assertEquals(true, definedIndexes.get(8));
        assertEquals(false, definedIndexes.get(9));
        assertEquals(false, definedIndexes.get(0));
    }

    @Test
    public void test101010_king_size () {
        assertEquals(assembleByIndexes(1, 2, 3, 4), stage1Test.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(5, 6, 7, 8), stage1Test.getNumber());
        setCowsAmount(2, 0);
        assertEquals(assembleByIndexes(9, 0, 5, 6), stage1Test.getNumber());
        setCowsAmount(2, 0);
        assertEquals(assembleByIndexes(7, 8, 9, 0), stage1Test.getNumber());
        setCowsAmount(2, 0);
        assertEquals(assembleByIndexes(8, 9, 0, 5), stage1Test.getNumber());
        setCowsAmount(2, 0);
        assertEquals(assembleByIndexes(6, 7, 8, 9), stage1Test.getNumber());
        setCowsAmount(2, 0);
        assertEquals(assembleByIndexes(9, 5, 6, 7), stage1Test.getNumber());
        setCowsAmount(3, 0);
        stage1Test.getNumber();

        assertEquals(true, definedIndexes.get(5));
        assertEquals(false, definedIndexes.get(6));
        assertEquals(true, definedIndexes.get(7));
        assertEquals(false, definedIndexes.get(8));
        assertEquals(true, definedIndexes.get(9));
        assertEquals(false, definedIndexes.get(0));
    }

    @Test
    public void test101001 () {
        assertEquals(assembleByIndexes(1, 2, 3, 4), stage1Test.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(5, 6, 7, 8), stage1Test.getNumber());
        setCowsAmount(2, 0);
        assertEquals(assembleByIndexes(9, 0, 5, 6), stage1Test.getNumber());
        setCowsAmount(2, 0);
        assertEquals(assembleByIndexes(7, 8, 9, 0), stage1Test.getNumber());
        setCowsAmount(2, 0);
        assertEquals(assembleByIndexes(8, 9, 0, 5), stage1Test.getNumber());
        setCowsAmount(2, 0);
        assertEquals(assembleByIndexes(6, 7, 8, 9), stage1Test.getNumber());
        setCowsAmount(1, 0);
        stage1Test.getNumber();

        assertEquals(true, definedIndexes.get(5));
        assertEquals(false, definedIndexes.get(6));
        assertEquals(true, definedIndexes.get(7));
        assertEquals(false, definedIndexes.get(8));
        assertEquals(false, definedIndexes.get(9));
        assertEquals(true, definedIndexes.get(0));
    }

    @Test
    public void test100110 () {
        assertEquals(assembleByIndexes(1, 2, 3, 4), stage1Test.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(5, 6, 7, 8), stage1Test.getNumber());
        setCowsAmount(2, 0);
        assertEquals(assembleByIndexes(9, 0, 5, 6), stage1Test.getNumber());
        setCowsAmount(2, 0);
        assertEquals(assembleByIndexes(7, 8, 9, 0), stage1Test.getNumber());
        setCowsAmount(2, 0);
        assertEquals(assembleByIndexes(8, 9, 0, 5), stage1Test.getNumber());
        setCowsAmount(3, 0);
        assertEquals(assembleByIndexes(6, 7, 8, 9), stage1Test.getNumber());
        setCowsAmount(2, 0);
        stage1Test.getNumber();

        assertEquals(true, definedIndexes.get(5));
        assertEquals(false, definedIndexes.get(6));
        assertEquals(false, definedIndexes.get(7));
        assertEquals(true, definedIndexes.get(8));
        assertEquals(true, definedIndexes.get(9));
        assertEquals(false, definedIndexes.get(0));
    }

    @Test
    public void test100101 () {
        assertEquals(assembleByIndexes(1, 2, 3, 4), stage1Test.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(5, 6, 7, 8), stage1Test.getNumber());
        setCowsAmount(2, 0);
        assertEquals(assembleByIndexes(9, 0, 5, 6), stage1Test.getNumber());
        setCowsAmount(2, 0);
        assertEquals(assembleByIndexes(7, 8, 9, 0), stage1Test.getNumber());
        setCowsAmount(2, 0);
        assertEquals(assembleByIndexes(8, 9, 0, 5), stage1Test.getNumber());
        setCowsAmount(3, 0);
        assertEquals(assembleByIndexes(6, 7, 8, 9), stage1Test.getNumber());
        setCowsAmount(1, 0);
        stage1Test.getNumber();

        assertEquals(true, definedIndexes.get(5));
        assertEquals(false, definedIndexes.get(6));
        assertEquals(false, definedIndexes.get(7));
        assertEquals(true, definedIndexes.get(8));
        assertEquals(false, definedIndexes.get(9));
        assertEquals(true, definedIndexes.get(0));
    }

    @Test
    public void test011100 () {
        assertEquals(assembleByIndexes(1, 2, 3, 4), stage1Test.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(5, 6, 7, 8), stage1Test.getNumber());
        setCowsAmount(3, 0);
        assertEquals(assembleByIndexes(9, 0, 5, 6), stage1Test.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(8, 9, 0, 5), stage1Test.getNumber());
        setCowsAmount(1, 0);
        stage1Test.getNumber();

        assertEquals(false, definedIndexes.get(5));
        assertEquals(true, definedIndexes.get(6));
        assertEquals(true, definedIndexes.get(7));
        assertEquals(true, definedIndexes.get(8));
        assertEquals(false, definedIndexes.get(9));
        assertEquals(false, definedIndexes.get(0));
    }

    @Test
    public void test011010 () {
        assertEquals(assembleByIndexes(1, 2, 3, 4), stage1Test.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(5, 6, 7, 8), stage1Test.getNumber());
        setCowsAmount(2, 0);
        assertEquals(assembleByIndexes(9, 0, 5, 6), stage1Test.getNumber());
        setCowsAmount(2, 0);
        assertEquals(assembleByIndexes(7, 8, 9, 0), stage1Test.getNumber());
        setCowsAmount(2, 0);
        assertEquals(assembleByIndexes(8, 9, 0, 5), stage1Test.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(6, 7, 8, 9), stage1Test.getNumber());
        setCowsAmount(3, 0);
        stage1Test.getNumber();

        assertEquals(false, definedIndexes.get(5));
        assertEquals(true, definedIndexes.get(6));
        assertEquals(true, definedIndexes.get(7));
        assertEquals(false, definedIndexes.get(8));
        assertEquals(true, definedIndexes.get(9));
        assertEquals(false, definedIndexes.get(0));
    }

    @Test
    public void test011001 () {
        assertEquals(assembleByIndexes(1, 2, 3, 4), stage1Test.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(5, 6, 7, 8), stage1Test.getNumber());
        setCowsAmount(2, 0);
        assertEquals(assembleByIndexes(9, 0, 5, 6), stage1Test.getNumber());
        setCowsAmount(2, 0);
        assertEquals(assembleByIndexes(7, 8, 9, 0), stage1Test.getNumber());
        setCowsAmount(2, 0);
        assertEquals(assembleByIndexes(8, 9, 0, 5), stage1Test.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(6, 7, 8, 9), stage1Test.getNumber());
        setCowsAmount(2, 0);
        stage1Test.getNumber();

        assertEquals(false, definedIndexes.get(5));
        assertEquals(true, definedIndexes.get(6));
        assertEquals(true, definedIndexes.get(7));
        assertEquals(false, definedIndexes.get(8));
        assertEquals(false, definedIndexes.get(9));
        assertEquals(true, definedIndexes.get(0));
    }

    @Test
    public void test010110 () {
        assertEquals(assembleByIndexes(1, 2, 3, 4), stage1Test.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(5, 6, 7, 8), stage1Test.getNumber());
        setCowsAmount(2, 0);
        assertEquals(assembleByIndexes(9, 0, 5, 6), stage1Test.getNumber());
        setCowsAmount(2, 0);
        assertEquals(assembleByIndexes(7, 8, 9, 0), stage1Test.getNumber());
        setCowsAmount(2, 0);
        assertEquals(assembleByIndexes(8, 9, 0, 5), stage1Test.getNumber());
        setCowsAmount(2, 0);
        assertEquals(assembleByIndexes(6, 7, 8, 9), stage1Test.getNumber());
        setCowsAmount(3, 0);
        stage1Test.getNumber();

        assertEquals(false, definedIndexes.get(5));
        assertEquals(true, definedIndexes.get(6));
        assertEquals(false, definedIndexes.get(7));
        assertEquals(true, definedIndexes.get(8));
        assertEquals(true, definedIndexes.get(9));
        assertEquals(false, definedIndexes.get(0));
    }

    @Test
    public void test010101_king_size () {
        assertEquals(assembleByIndexes(1, 2, 3, 4), stage1Test.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(5, 6, 7, 8), stage1Test.getNumber());
        setCowsAmount(2, 0);
        assertEquals(assembleByIndexes(9, 0, 5, 6), stage1Test.getNumber());
        setCowsAmount(2, 0);
        assertEquals(assembleByIndexes(7, 8, 9, 0), stage1Test.getNumber());
        setCowsAmount(2, 0);
        assertEquals(assembleByIndexes(8, 9, 0, 5), stage1Test.getNumber());
        setCowsAmount(2, 0);
        assertEquals(assembleByIndexes(6, 7, 8, 9), stage1Test.getNumber());
        setCowsAmount(2, 0);
        assertEquals(assembleByIndexes(9, 5, 6, 7), stage1Test.getNumber());
        setCowsAmount(1, 0);
        stage1Test.getNumber();

        assertEquals(false, definedIndexes.get(5));
        assertEquals(true, definedIndexes.get(6));
        assertEquals(false, definedIndexes.get(7));
        assertEquals(true, definedIndexes.get(8));
        assertEquals(false, definedIndexes.get(9));
        assertEquals(true, definedIndexes.get(0));
    }

    @Test
    public void test000110 () {
        assertEquals(assembleByIndexes(1, 2, 3, 4), stage1Test.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(5, 6, 7, 8), stage1Test.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(9, 0, 5, 6), stage1Test.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(7, 8, 9, 0), stage1Test.getNumber());
        setCowsAmount(2, 0);
        assertEquals(assembleByIndexes(0, 5, 6, 7), stage1Test.getNumber());
        setCowsAmount(0, 0);
        stage1Test.getNumber();

        assertEquals(false, definedIndexes.get(5));
        assertEquals(false, definedIndexes.get(6));
        assertEquals(false, definedIndexes.get(7));
        assertEquals(true, definedIndexes.get(8));
        assertEquals(true, definedIndexes.get(9));
        assertEquals(false, definedIndexes.get(0));
    }

}
