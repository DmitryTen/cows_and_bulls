package logic.definers;

import logic.NumberWithCowsAmount;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

/**
 * Created by Windows on 24.12.2015.
 */
public class DefinerMainTest {

    private Definer definer;
    private static Integer[] rndSecuence = new Integer[10];

    private static void fillRndSecuence(){
        ArrayList<Integer> numbersArray = new ArrayList<>();
        for (int b=0; b<10; b++) numbersArray.add(b);
        Collections.shuffle(numbersArray);
        rndSecuence = numbersArray.toArray(rndSecuence);
    }

    private void setCowsAmount(int cows, int bulls){
        ArrayList<NumberWithCowsAmount> numersHistory = definer.getNumbersHistory();
        NumberWithCowsAmount numberWithCowsAmount = numersHistory.get(numersHistory.size()-1);
        numberWithCowsAmount.setCowsAmount(cows);
        numberWithCowsAmount.setBullsAmount(bulls);
    }

    protected final int assembleByIndexes(int index1, int index2, int index3, int index4){
        int num = rndSecuence[index1]*1000 + rndSecuence[index2]*100 + rndSecuence[index3]*10 + rndSecuence[index4];
        return num;
    }

    @BeforeClass
    public static void initiateRndSecuence(){
        fillRndSecuence();
    }

    @Before
    public void initiateDefiner(){
        definer = new DefinerMain(rndSecuence, new ArrayList<>(), new HashMap<>());
    }


    @Test
    public void test100000 () throws DefinerException{
        assertEquals(assembleByIndexes(1, 2, 3, 4), definer.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(5, 6, 7, 8), definer.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(9, 0, 5, 6), definer.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(7, 8, 9, 0), definer.getNumber());
        setCowsAmount(0, 0);
        assertEquals(assembleByIndexes(8, 9, 0, 5), definer.getNumber());
        setCowsAmount(1, 0);
        definer.getNumber();

        HashMap<Integer, Boolean> definedDigits = definer.getDefinedDigits();

        assertEquals(true, definedDigits.get(rndSecuence[5]));
        assertEquals(false, definedDigits.get(rndSecuence[6]));
        assertEquals(false, definedDigits.get(rndSecuence[7]));
        assertEquals(false, definedDigits.get(rndSecuence[8]));
        assertEquals(false, definedDigits.get(rndSecuence[9]));
        assertEquals(false, definedDigits.get(rndSecuence[0]));

    }
/*
    @Test
    public void test010000 () throws DefinerException{
        assertEquals(assembleByIndexes(1, 2, 3, 4), definer.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(5, 6, 7, 8), definer.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(9, 0, 5, 6), definer.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(7, 8, 9, 0), definer.getNumber());
        setCowsAmount(0, 0);
        assertEquals(assembleByIndexes(8, 9, 0, 5), definer.getNumber());
        setCowsAmount(0, 0);
        definer.getNumber();

        HashMap<Integer, Boolean> definedDigits = definer.getDefinedDigits();
        assertEquals(false, definedDigits.get(rndSecuence[5]));
        assertEquals(true, definedDigits.get(rndSecuence[6]));
        assertEquals(false, definedDigits.get(rndSecuence[7]));
        assertEquals(false, definedDigits.get(rndSecuence[8]));
        assertEquals(false, definedDigits.get(rndSecuence[9]));
        assertEquals(false, definedDigits.get(rndSecuence[0]));
    }

    @Test
    public void test001000 () throws DefinerException{
        assertEquals(assembleByIndexes(1, 2, 3, 4), definer.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(5, 6, 7, 8), definer.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(9, 0, 5, 6), definer.getNumber());
        setCowsAmount(0, 0);
        assertEquals(assembleByIndexes(0, 5, 6, 7), definer.getNumber());
        setCowsAmount(1, 0);
        definer.getNumber();

        HashMap<Integer, Boolean> definedDigits = definer.getDefinedDigits();
        assertEquals(false, definedDigits.get(rndSecuence[5]));
        assertEquals(false, definedDigits.get(rndSecuence[6]));
        assertEquals(true, definedDigits.get(rndSecuence[7]));
        assertEquals(false, definedDigits.get(rndSecuence[8]));
        assertEquals(false, definedDigits.get(rndSecuence[9]));
        assertEquals(false, definedDigits.get(rndSecuence[0]));
    }

    @Test
    public void test000100 () throws DefinerException{
        assertEquals(assembleByIndexes(1, 2, 3, 4), definer.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(5, 6, 7, 8), definer.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(9, 0, 5, 6), definer.getNumber());
        setCowsAmount(0, 0);
        assertEquals(assembleByIndexes(0, 5, 6, 7), definer.getNumber());
        setCowsAmount(0, 0);
        definer.getNumber();

        HashMap<Integer, Boolean> definedDigits = definer.getDefinedDigits();
        assertEquals(false, definedDigits.get(rndSecuence[5]));
        assertEquals(false, definedDigits.get(rndSecuence[6]));
        assertEquals(false, definedDigits.get(rndSecuence[7]));
        assertEquals(true, definedDigits.get(rndSecuence[8]));
        assertEquals(false, definedDigits.get(rndSecuence[9]));
        assertEquals(false, definedDigits.get(rndSecuence[0]));
    }

    @Test
    public void test000010_specialCase () throws DefinerException{
        assertEquals(assembleByIndexes(1, 2, 3, 4), definer.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(5, 6, 7, 8), definer.getNumber());
        setCowsAmount(0, 0);

        definer.getNumber();

        HashMap<Integer, Boolean> definedDigits = definer.getDefinedDigits();
        assertEquals(false, definedDigits.get(rndSecuence[5]));
        assertEquals(false, definedDigits.get(rndSecuence[6]));
        assertEquals(false, definedDigits.get(rndSecuence[7]));
        assertEquals(false, definedDigits.get(rndSecuence[8]));
    }

    @Test
    public void test110000 () throws DefinerException{
        assertEquals(assembleByIndexes(1, 2, 3, 4), definer.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(5, 6, 7, 8), definer.getNumber());
        setCowsAmount(2, 0);
        assertEquals(assembleByIndexes(9, 0, 5, 6), definer.getNumber());
        setCowsAmount(2, 0);
        assertEquals(assembleByIndexes(7, 8, 9, 0), definer.getNumber());
        setCowsAmount(0, 0);
        definer.getNumber();

        HashMap<Integer, Boolean> definedDigits = definer.getDefinedDigits();
        assertEquals(true, definedDigits.get(rndSecuence[5]));
        assertEquals(true, definedDigits.get(rndSecuence[6]));
        assertEquals(false, definedDigits.get(rndSecuence[7]));
        assertEquals(false, definedDigits.get(rndSecuence[8]));
        assertEquals(false, definedDigits.get(rndSecuence[9]));
        assertEquals(false, definedDigits.get(rndSecuence[0]));
    }

    @Test
    public void test101000 () throws DefinerException{
        assertEquals(assembleByIndexes(1, 2, 3, 4), definer.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(5, 6, 7, 8), definer.getNumber());
        setCowsAmount(2, 0);
        assertEquals(assembleByIndexes(9, 0, 5, 6), definer.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(7, 8, 9, 0), definer.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(8, 9, 0, 5), definer.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(6, 7, 8, 9), definer.getNumber());
        setCowsAmount(1, 0);
        definer.getNumber();

        HashMap<Integer, Boolean> definedDigits = definer.getDefinedDigits();
        assertEquals(true, definedDigits.get(rndSecuence[5]));
        assertEquals(false, definedDigits.get(rndSecuence[6]));
        assertEquals(true, definedDigits.get(rndSecuence[7]));
        assertEquals(false, definedDigits.get(rndSecuence[8]));
        assertEquals(false, definedDigits.get(rndSecuence[9]));
        assertEquals(false, definedDigits.get(rndSecuence[0]));
    }

    @Test
    public void test100100 () throws DefinerException{
        assertEquals(assembleByIndexes(1, 2, 3, 4), definer.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(5, 6, 7, 8), definer.getNumber());
        setCowsAmount(2, 0);
        assertEquals(assembleByIndexes(9, 0, 5, 6), definer.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(7, 8, 9, 0), definer.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(8, 9, 0, 5), definer.getNumber());
        setCowsAmount(2, 0);
        definer.getNumber();

        HashMap<Integer, Boolean> definedDigits = definer.getDefinedDigits();
        assertEquals(true, definedDigits.get(rndSecuence[5]));
        assertEquals(false, definedDigits.get(rndSecuence[6]));
        assertEquals(false, definedDigits.get(rndSecuence[7]));
        assertEquals(true, definedDigits.get(rndSecuence[8]));
        assertEquals(false, definedDigits.get(rndSecuence[9]));
        assertEquals(false, definedDigits.get(rndSecuence[0]));
    }

    @Test
    public void test100010 () throws DefinerException{
        assertEquals(assembleByIndexes(1, 2, 3, 4), definer.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(5, 6, 7, 8), definer.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(9, 0, 5, 6), definer.getNumber());
        setCowsAmount(2, 0);
        assertEquals(assembleByIndexes(7, 8, 9, 0), definer.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(6, 7, 8, 9), definer.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(0, 5, 6, 7), definer.getNumber());
        setCowsAmount(1, 0);
        definer.getNumber();

        HashMap<Integer, Boolean> definedDigits = definer.getDefinedDigits();
        assertEquals(true, definedDigits.get(rndSecuence[5]));
        assertEquals(false, definedDigits.get(rndSecuence[6]));
        assertEquals(false, definedDigits.get(rndSecuence[7]));
        assertEquals(false, definedDigits.get(rndSecuence[8]));
        assertEquals(true, definedDigits.get(rndSecuence[9]));
        assertEquals(false, definedDigits.get(rndSecuence[0]));
    }

    @Test
    public void test100001 () throws DefinerException{
        assertEquals(assembleByIndexes(1, 2, 3, 4), definer.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(5, 6, 7, 8), definer.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(9, 0, 5, 6), definer.getNumber());
        setCowsAmount(2, 0);
        assertEquals(assembleByIndexes(7, 8, 9, 0), definer.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(6, 7, 8, 9), definer.getNumber());
        setCowsAmount(0, 0);
        definer.getNumber();

        HashMap<Integer, Boolean> definedDigits = definer.getDefinedDigits();
        assertEquals(true, definedDigits.get(rndSecuence[5]));
        assertEquals(false, definedDigits.get(rndSecuence[6]));
        assertEquals(false, definedDigits.get(rndSecuence[7]));
        assertEquals(false, definedDigits.get(rndSecuence[8]));
        assertEquals(false, definedDigits.get(rndSecuence[9]));
        assertEquals(true, definedDigits.get(rndSecuence[0]));
    }

    @Test
    public void test111000 () throws DefinerException{
        assertEquals(assembleByIndexes(1, 2, 3, 4), definer.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(5, 6, 7, 8), definer.getNumber());
        setCowsAmount(3, 0);
        assertEquals(assembleByIndexes(9, 0, 5, 6), definer.getNumber());
        setCowsAmount(2, 0);
        assertEquals(assembleByIndexes(7, 8, 9, 0), definer.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(0, 5, 6, 7), definer.getNumber());
        setCowsAmount(3, 0);
        definer.getNumber();


        definer.getNumber();

        HashMap<Integer, Boolean> definedDigits = definer.getDefinedDigits();
        assertEquals(true, definedDigits.get(rndSecuence[5]));
        assertEquals(true, definedDigits.get(rndSecuence[6]));
        assertEquals(true, definedDigits.get(rndSecuence[7]));
        assertEquals(false, definedDigits.get(rndSecuence[8]));
        assertEquals(false, definedDigits.get(rndSecuence[9]));
        assertEquals(false, definedDigits.get(rndSecuence[0]));
    }

    @Test
    public void test110100 () throws DefinerException{
        assertEquals(assembleByIndexes(1, 2, 3, 4), definer.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(5, 6, 7, 8), definer.getNumber());
        setCowsAmount(3, 0);
        assertEquals(assembleByIndexes(9, 0, 5, 6), definer.getNumber());
        setCowsAmount(2, 0);
        assertEquals(assembleByIndexes(7, 8, 9, 0), definer.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(0, 5, 6, 7), definer.getNumber());
        setCowsAmount(2, 0);
        definer.getNumber();

        HashMap<Integer, Boolean> definedDigits = definer.getDefinedDigits();
        assertEquals(true, definedDigits.get(rndSecuence[5]));
        assertEquals(true, definedDigits.get(rndSecuence[6]));
        assertEquals(false, definedDigits.get(rndSecuence[7]));
        assertEquals(true, definedDigits.get(rndSecuence[8]));
        assertEquals(false, definedDigits.get(rndSecuence[9]));
        assertEquals(false, definedDigits.get(rndSecuence[0]));
    }

    @Test
    public void test110010 () throws DefinerException{
        assertEquals(assembleByIndexes(1, 2, 3, 4), definer.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(5, 6, 7, 8), definer.getNumber());
        setCowsAmount(2, 0);
        assertEquals(assembleByIndexes(9, 0, 5, 6), definer.getNumber());
        setCowsAmount(3, 0);
        assertEquals(assembleByIndexes(7, 8, 9, 0), definer.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(6, 7, 8, 9), definer.getNumber());
        setCowsAmount(2, 0);
        definer.getNumber();

        HashMap<Integer, Boolean> definedDigits = definer.getDefinedDigits();
        assertEquals(true, definedDigits.get(rndSecuence[5]));
        assertEquals(true, definedDigits.get(rndSecuence[6]));
        assertEquals(false, definedDigits.get(rndSecuence[7]));
        assertEquals(false, definedDigits.get(rndSecuence[8]));
        assertEquals(true, definedDigits.get(rndSecuence[9]));
        assertEquals(false, definedDigits.get(rndSecuence[0]));
    }

    @Test
    public void test110001 () throws DefinerException{
        assertEquals(assembleByIndexes(1, 2, 3, 4), definer.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(5, 6, 7, 8), definer.getNumber());
        setCowsAmount(2, 0);
        assertEquals(assembleByIndexes(9, 0, 5, 6), definer.getNumber());
        setCowsAmount(3, 0);
        assertEquals(assembleByIndexes(7, 8, 9, 0), definer.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(6, 7, 8, 9), definer.getNumber());
        setCowsAmount(1, 0);
        definer.getNumber();

        HashMap<Integer, Boolean> definedDigits = definer.getDefinedDigits();
        assertEquals(true, definedDigits.get(rndSecuence[5]));
        assertEquals(true, definedDigits.get(rndSecuence[6]));
        assertEquals(false, definedDigits.get(rndSecuence[7]));
        assertEquals(false, definedDigits.get(rndSecuence[8]));
        assertEquals(false, definedDigits.get(rndSecuence[9]));
        assertEquals(true, definedDigits.get(rndSecuence[0]));
    }

    @Test
    public void test101100 () throws DefinerException{
        assertEquals(assembleByIndexes(1, 2, 3, 4), definer.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(5, 6, 7, 8), definer.getNumber());
        setCowsAmount(3, 0);
        assertEquals(assembleByIndexes(9, 0, 5, 6), definer.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(8, 9, 0, 5), definer.getNumber());
        setCowsAmount(2, 0);
        definer.getNumber();

        HashMap<Integer, Boolean> definedDigits = definer.getDefinedDigits();
        assertEquals(true, definedDigits.get(rndSecuence[5]));
        assertEquals(false, definedDigits.get(rndSecuence[6]));
        assertEquals(true, definedDigits.get(rndSecuence[7]));
        assertEquals(true, definedDigits.get(rndSecuence[8]));
        assertEquals(false, definedDigits.get(rndSecuence[9]));
        assertEquals(false, definedDigits.get(rndSecuence[0]));
    }

    @Test
    public void test101010_king_size () throws DefinerException{
        assertEquals(assembleByIndexes(1, 2, 3, 4), definer.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(5, 6, 7, 8), definer.getNumber());
        setCowsAmount(2, 0);
        assertEquals(assembleByIndexes(9, 0, 5, 6), definer.getNumber());
        setCowsAmount(2, 0);
        assertEquals(assembleByIndexes(7, 8, 9, 0), definer.getNumber());
        setCowsAmount(2, 0);
        assertEquals(assembleByIndexes(8, 9, 0, 5), definer.getNumber());
        setCowsAmount(2, 0);
        assertEquals(assembleByIndexes(6, 7, 8, 9), definer.getNumber());
        setCowsAmount(2, 0);
        assertEquals(assembleByIndexes(9, 5, 5, 7), definer.getNumber());
        setCowsAmount(4, 0);
        definer.getNumber();

        HashMap<Integer, Boolean> definedDigits = definer.getDefinedDigits();
        assertEquals(true, definedDigits.get(rndSecuence[5]));
        assertEquals(false, definedDigits.get(rndSecuence[6]));
        assertEquals(true, definedDigits.get(rndSecuence[7]));
        assertEquals(false, definedDigits.get(rndSecuence[8]));
        assertEquals(true, definedDigits.get(rndSecuence[9]));
        assertEquals(false, definedDigits.get(rndSecuence[0]));
    }

    @Test
    public void test101001 () throws DefinerException{
        assertEquals(assembleByIndexes(1, 2, 3, 4), definer.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(5, 6, 7, 8), definer.getNumber());
        setCowsAmount(2, 0);
        assertEquals(assembleByIndexes(9, 0, 5, 6), definer.getNumber());
        setCowsAmount(2, 0);
        assertEquals(assembleByIndexes(7, 8, 9, 0), definer.getNumber());
        setCowsAmount(2, 0);
        assertEquals(assembleByIndexes(8, 9, 0, 5), definer.getNumber());
        setCowsAmount(2, 0);
        assertEquals(assembleByIndexes(6, 7, 8, 9), definer.getNumber());
        setCowsAmount(1, 0);
        definer.getNumber();

        HashMap<Integer, Boolean> definedDigits = definer.getDefinedDigits();
        assertEquals(true, definedDigits.get(rndSecuence[5]));
        assertEquals(false, definedDigits.get(rndSecuence[6]));
        assertEquals(true, definedDigits.get(rndSecuence[7]));
        assertEquals(false, definedDigits.get(rndSecuence[8]));
        assertEquals(false, definedDigits.get(rndSecuence[9]));
        assertEquals(true, definedDigits.get(rndSecuence[0]));
    }

    @Test
    public void test100110 () throws DefinerException{
        assertEquals(assembleByIndexes(1, 2, 3, 4), definer.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(5, 6, 7, 8), definer.getNumber());
        setCowsAmount(2, 0);
        assertEquals(assembleByIndexes(9, 0, 5, 6), definer.getNumber());
        setCowsAmount(2, 0);
        assertEquals(assembleByIndexes(7, 8, 9, 0), definer.getNumber());
        setCowsAmount(2, 0);
        assertEquals(assembleByIndexes(8, 9, 0, 5), definer.getNumber());
        setCowsAmount(3, 0);
        assertEquals(assembleByIndexes(6, 7, 8, 9), definer.getNumber());
        setCowsAmount(2, 0);
        definer.getNumber();

        HashMap<Integer, Boolean> definedDigits = definer.getDefinedDigits();
        assertEquals(true, definedDigits.get(rndSecuence[5]));
        assertEquals(false, definedDigits.get(rndSecuence[6]));
        assertEquals(false, definedDigits.get(rndSecuence[7]));
        assertEquals(true, definedDigits.get(rndSecuence[8]));
        assertEquals(true, definedDigits.get(rndSecuence[9]));
        assertEquals(false, definedDigits.get(rndSecuence[0]));
    }

    @Test
    public void test100101 () throws DefinerException{
        assertEquals(assembleByIndexes(1, 2, 3, 4), definer.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(5, 6, 7, 8), definer.getNumber());
        setCowsAmount(2, 0);
        assertEquals(assembleByIndexes(9, 0, 5, 6), definer.getNumber());
        setCowsAmount(2, 0);
        assertEquals(assembleByIndexes(7, 8, 9, 0), definer.getNumber());
        setCowsAmount(2, 0);
        assertEquals(assembleByIndexes(8, 9, 0, 5), definer.getNumber());
        setCowsAmount(3, 0);
        assertEquals(assembleByIndexes(6, 7, 8, 9), definer.getNumber());
        setCowsAmount(1, 0);
        definer.getNumber();

        HashMap<Integer, Boolean> definedDigits = definer.getDefinedDigits();
        assertEquals(true, definedDigits.get(rndSecuence[5]));
        assertEquals(false, definedDigits.get(rndSecuence[6]));
        assertEquals(false, definedDigits.get(rndSecuence[7]));
        assertEquals(true, definedDigits.get(rndSecuence[8]));
        assertEquals(false, definedDigits.get(rndSecuence[9]));
        assertEquals(true, definedDigits.get(rndSecuence[0]));
    }

    @Test
    public void test011100 () throws DefinerException{
        assertEquals(assembleByIndexes(1, 2, 3, 4), definer.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(5, 6, 7, 8), definer.getNumber());
        setCowsAmount(3, 0);
        assertEquals(assembleByIndexes(9, 0, 5, 6), definer.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(8, 9, 0, 5), definer.getNumber());
        setCowsAmount(1, 0);
        definer.getNumber();

        HashMap<Integer, Boolean> definedDigits = definer.getDefinedDigits();
        assertEquals(false, definedDigits.get(rndSecuence[5]));
        assertEquals(true, definedDigits.get(rndSecuence[6]));
        assertEquals(true, definedDigits.get(rndSecuence[7]));
        assertEquals(true, definedDigits.get(rndSecuence[8]));
        assertEquals(false, definedDigits.get(rndSecuence[9]));
        assertEquals(false, definedDigits.get(rndSecuence[0]));
    }

    @Test
    public void test011010 () throws DefinerException{
        assertEquals(assembleByIndexes(1, 2, 3, 4), definer.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(5, 6, 7, 8), definer.getNumber());
        setCowsAmount(2, 0);
        assertEquals(assembleByIndexes(9, 0, 5, 6), definer.getNumber());
        setCowsAmount(2, 0);
        assertEquals(assembleByIndexes(7, 8, 9, 0), definer.getNumber());
        setCowsAmount(2, 0);
        assertEquals(assembleByIndexes(8, 9, 0, 5), definer.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(6, 7, 8, 9), definer.getNumber());
        setCowsAmount(3, 0);
        definer.getNumber();

        HashMap<Integer, Boolean> definedDigits = definer.getDefinedDigits();
        assertEquals(false, definedDigits.get(rndSecuence[5]));
        assertEquals(true, definedDigits.get(rndSecuence[6]));
        assertEquals(true, definedDigits.get(rndSecuence[7]));
        assertEquals(false, definedDigits.get(rndSecuence[8]));
        assertEquals(true, definedDigits.get(rndSecuence[9]));
        assertEquals(false, definedDigits.get(rndSecuence[0]));
    }

    @Test
    public void test011001 () throws DefinerException{
        assertEquals(assembleByIndexes(1, 2, 3, 4), definer.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(5, 6, 7, 8), definer.getNumber());
        setCowsAmount(2, 0);
        assertEquals(assembleByIndexes(9, 0, 5, 6), definer.getNumber());
        setCowsAmount(2, 0);
        assertEquals(assembleByIndexes(7, 8, 9, 0), definer.getNumber());
        setCowsAmount(2, 0);
        assertEquals(assembleByIndexes(8, 9, 0, 5), definer.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(6, 7, 8, 9), definer.getNumber());
        setCowsAmount(2, 0);
        definer.getNumber();

        HashMap<Integer, Boolean> definedDigits = definer.getDefinedDigits();
        assertEquals(false, definedDigits.get(rndSecuence[5]));
        assertEquals(true, definedDigits.get(rndSecuence[6]));
        assertEquals(true, definedDigits.get(rndSecuence[7]));
        assertEquals(false, definedDigits.get(rndSecuence[8]));
        assertEquals(false, definedDigits.get(rndSecuence[9]));
        assertEquals(true, definedDigits.get(rndSecuence[0]));
    }

    @Test
    public void test010110 () throws DefinerException{
        assertEquals(assembleByIndexes(1, 2, 3, 4), definer.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(5, 6, 7, 8), definer.getNumber());
        setCowsAmount(2, 0);
        assertEquals(assembleByIndexes(9, 0, 5, 6), definer.getNumber());
        setCowsAmount(2, 0);
        assertEquals(assembleByIndexes(7, 8, 9, 0), definer.getNumber());
        setCowsAmount(2, 0);
        assertEquals(assembleByIndexes(8, 9, 0, 5), definer.getNumber());
        setCowsAmount(2, 0);
        assertEquals(assembleByIndexes(6, 7, 8, 9), definer.getNumber());
        setCowsAmount(3, 0);
        definer.getNumber();

        HashMap<Integer, Boolean> definedDigits = definer.getDefinedDigits();
        assertEquals(false, definedDigits.get(rndSecuence[5]));
        assertEquals(true, definedDigits.get(rndSecuence[6]));
        assertEquals(false, definedDigits.get(rndSecuence[7]));
        assertEquals(true, definedDigits.get(rndSecuence[8]));
        assertEquals(true, definedDigits.get(rndSecuence[9]));
        assertEquals(false, definedDigits.get(rndSecuence[0]));
    }

    @Test
    public void test010101_king_size () throws DefinerException{
        assertEquals(assembleByIndexes(1, 2, 3, 4), definer.getNumber());
        setCowsAmount(1, 0);
        assertEquals(assembleByIndexes(5, 6, 7, 8), definer.getNumber());
        setCowsAmount(2, 0);
        assertEquals(assembleByIndexes(9, 0, 5, 6), definer.getNumber());
        setCowsAmount(2, 0);
        assertEquals(assembleByIndexes(7, 8, 9, 0), definer.getNumber());
        setCowsAmount(2, 0);
        assertEquals(assembleByIndexes(8, 9, 0, 5), definer.getNumber());
        setCowsAmount(2, 0);
        assertEquals(assembleByIndexes(6, 7, 8, 9), definer.getNumber());
        setCowsAmount(2, 0);
        assertEquals(assembleByIndexes(9, 5, 5, 7), definer.getNumber());
        setCowsAmount(0, 0);
        definer.getNumber();

        HashMap<Integer, Boolean> definedDigits = definer.getDefinedDigits();
        assertEquals(false, definedDigits.get(rndSecuence[5]));
        assertEquals(true, definedDigits.get(rndSecuence[6]));
        assertEquals(false, definedDigits.get(rndSecuence[7]));
        assertEquals(true, definedDigits.get(rndSecuence[8]));
        assertEquals(false, definedDigits.get(rndSecuence[9]));
        assertEquals(true, definedDigits.get(rndSecuence[0]));
    }

*/
}
