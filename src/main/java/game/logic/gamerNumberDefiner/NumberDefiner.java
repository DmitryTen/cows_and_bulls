package game.logic.gamerNumberDefiner;


import game.logic.gamerNumberDefiner.stage1.DefinerS1Main;
import game.logic.gamerNumberDefiner.stage2.CowInfo;
import game.logic.gamerNumberDefiner.stage2.DefinerS2Main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
/**
 * Created by Windows on 18.12.2015.
 *
 * Кратко: данный класс выдает числа по полученным быкам и коровам.
 *
 * Данный класс:
 * 1. Хранит всю историю предыдущих запросов.
 * 2. Хранит информацию о стадии определения числа (2 основных стадии: stage1 и stage2)
 *
 * Определением числа занимаются другие классы, наследующие интерфейс Definer. Определение числа идет в два этапа (стадии)
 *
 * 1-ЫЙ ЭТАП (stage1):
 * необходимо определить значимые и незначимые цифры. Значимые цифры - те которые присутствуют в числе
 * (бык либо корова). Незначимые цифры: цифры которых точно нет в числе (ни бык ни корова). Поиск значимых и незначимых
 * цифр идет по следующей схеме. У игрока запрашиваются числа в следующем порядке:
 *
 * Число-№1: 1 2 3 4 = A (суммарное количество быков и коров)
 * Число-№2: 5 6 7 8 = B
 * Число-№3: 9 0 5 6 = C
 * Число-№4: 7 8 9 0 = D,  и т.д.
 *      начиная с числа 2 программа будет определять значимость цифр 56 78 90, а к 1234 - вернется только на этапе2
 *      Из чисел 2,3,4 в итоге получается три уравнения с тремя неизвестными Х:[56], Y:[78], Z:[90]
 *      Число№2: X + Y = B
 *      Число№3: Z + X = C
 *      Число№4: Y + Z = D
 *      Каждое из X,Y,Z может равнятся либо 0 либо 1 либо 2, например:
 *          0 - если переменная (например X:[56]) равна нулю, это значит что цифры(индексы) 5 и 6 - незначимы, и в числе
 *          - отсутствуют.
 *          1 - если переменная (например X:[56]) равна единице, это значит, что одна из цифр(индексов 5 или 6) является
 *          значимой и присутсвует в числе, а другая при этом - наоборот не является значимой и в числе - отсутствует.
 *          2 - если переменная (например X:[56]) равна двойке, это значит, что обе цифры(индекса 5 и 6) являются значимыми
 *          и присутсвуют в числе.
 *              С вариантами 0 и 2 - все понятно, с вариантом 1 - остается неопределенность. Для решения данной
 *              неопределенности переменные с неопределенными значениями - перемешиваются и запрашиваются у игрока.
 *              Например получили следующий результат уравнения: Х:[56] = 1, Y:[78] = 1, Z:[90] = 1,
 *              перемешиваем Х и Y, и получаем X1 Y1:
 *          X1:[85], Y1:[67], Z1:[90]   (Z1 == Z)
 *      У игрока опять запрашивается число:
 *      Число№5: Х1 + Z1 = E       Z1 == Z, поэтому отсюда легко вычислить X1 а следовательно и Y1: если X1 = 0 значит
 *                                  Y1 = 2, если X1 = 2, значит Y1 = 0. Если X1 = 1, нужно перемешивать число еще раз с Z.
 *
 * Логика 1го этапа окончена.
 * Результатом 1-го этапа чаще всего являются информация об индексах 5,6,7,8,9,0 , о том, какие из них значимые, а какие
 * - нет. Опираясь на эту информацию, возможна работа второго этапа.
 *
 * ====================================================================================================================
 * 2-ОЙ ЭТАП (stage2):
 * В первом этапе быки никак не отличались от коров, т.к. велся поиск значимых цифр быки складывались
 * с коровами, получалось суммарное количество значимых цифр. Во втором этапе ведется поиск быков и их позиций.
 *
 * К сведению: все позиции в stage2 имеют размер byte, если переменная приводится к byte, значит это - позиция в числе.
 *
 * Ко второму этапу программа приходит с багажом:
 *      1.ArrayList<NumberInfo> numbersHistory;
 *      2.HashMap<Integer, Boolean> definedIndexes;
 *
 *  См. stage2.DefinerS2Main
 */
public class NumberDefiner {

    /*Случайная последовательность цифр*/
    private Integer[] rndSecuence = new Integer[10];
    /*
    информация о числах, количестве быков и коров. Цифры, которые учавствуют в числах представлены как индексы
    (в предыдущем этапе это тоже были индексы, но решил не забивать голову. В общем 56 78 90 - все индексы, а цифры
    подставляются случайно с помощью случайной последовательности цифр rndSequence*/
    private ArrayList<NumberInfo> numbersHistory;
    /*
    Значимые и незначимые индексы определенные в первом этапе*/
    private HashMap<Integer, Boolean> definedIndexes;
    /*
    Карта быков. Сюда попадают индексые, позиции которых уже ясны.
    Ключ: позиция индекса (от 0 до 3) Значение: индекс. Как только все 4 позиции будут найдены, игра - закончена*/
    private HashMap<Byte, Integer> definedPositions;
    /*
    Самый важный объект на этапе 2. Для каждого значимого индекса создается свой объект CowInfo.
    CowInfo запоминает позициии, на которых побывал индекс. Если позиция "бычья" то CowInfo сообщает другим CowInfo, что
    позция занята. Если позиция "коровья", то CowInfo просто запоминает ее и больше ей не пользуется.
    * Ключ: индекс, значение: CowInfo */
    private HashMap<Integer, CowInfo> cowContainer;

    private int stage = 1;
    private Definer definer;

    public NumberDefiner() {
        fillRndSecuence();
        numbersHistory = new ArrayList<>();
        definedIndexes = new HashMap<>();
        cowContainer = new HashMap<>();
        definedPositions = new HashMap<>();

        definer = new DefinerS1Main(rndSecuence, numbersHistory, definedIndexes, this);
    }

    public NumberDefiner(Integer[] rndSecuence, ArrayList<NumberInfo> numbersHistory, HashMap<Integer, Boolean> definedIndexes) {
        this.rndSecuence = rndSecuence;
        this.numbersHistory = numbersHistory;
        this.definedIndexes = definedIndexes;
        cowContainer = new HashMap<>();
        definedPositions = new HashMap<>();
        definer = new DefinerS1Main(rndSecuence, numbersHistory, definedIndexes, this);
    }

    public NumberDefiner(Integer[] rndSecuence, ArrayList<NumberInfo> numbersHistory, HashMap<Integer, Boolean> definedIndexes,
                         HashMap<Byte, Integer> definedPositions, HashMap<Integer, CowInfo> cowContainer){
        this.rndSecuence = rndSecuence;
        this.numbersHistory = numbersHistory;
        this.definedIndexes = definedIndexes;
        this.cowContainer = cowContainer;
        this.definedPositions = definedPositions;
        stage=2;
        definer = new DefinerS2Main(rndSecuence, numbersHistory, definedIndexes, cowContainer, definedPositions, this);
    }

    private void fillRndSecuence(){
        ArrayList<Integer> numbersArray = new ArrayList<>();
        for (int b=0; b<10; b++) numbersArray.add(b);
        Collections.shuffle(numbersArray);
        rndSecuence = numbersArray.toArray(rndSecuence);
    }

    public long getRndSecuense(){
        long secuense=0;
        for (int b=0; b<rndSecuence.length; b++){
            secuense = rndSecuence[b] * (long)Math.pow(10 , 9-b) + secuense;
        }
        return secuense;
    }

    public int getNumber(){
        if (stage==1) {
            return definer.getNumber();
        }
        if (stage==2) {
           return definer.getNumber();
        }
        if (stage==3) {
            return putFinalNumberIntoNumbersHistory();
        }
        return 0;
    }


    public NumberInfo setCowsAndGetNumberInfo(int number, int cows, int bulls){
        NumberInfo numberInfo = numbersHistory.get(numbersHistory.size() - 1);
        if (numberInfo.getNum() == number){
            numberInfo.setCowsAmount(cows);
            numberInfo.setBullsAmount(bulls);
        } else throw new Error("Number from page doesn't match with numberInfo from logic");

        return numberInfo;
    }


    public void setStage(int stage) {
        if (stage==2) definer = new DefinerS2Main(rndSecuence, numbersHistory, definedIndexes, cowContainer, definedPositions, this);
        this.stage = stage;
    }

    private int putFinalNumberIntoNumbersHistory(){
        int num = rndSecuence[definedPositions.get((byte)0)]*1000
                + rndSecuence[definedPositions.get((byte)1)]*100
                + rndSecuence[definedPositions.get((byte)2)]*10
                + rndSecuence[definedPositions.get((byte)3)];

        numbersHistory.add(new NumberInfo(num,
                definedPositions.get((byte)0),
                definedPositions.get((byte)1),
                definedPositions.get((byte)2),
                definedPositions.get((byte)3),
                numbersHistory.size()+1));

        return num;
    }

    public String revealNumProposedByPlayer() {
        String stringNumber = "";
        if(definedPositions.containsKey((byte)0)) stringNumber = stringNumber + rndSecuence[definedPositions.get((byte)0)];
        else stringNumber = stringNumber + "X";
        if(definedPositions.containsKey((byte)1)) stringNumber = stringNumber + rndSecuence[definedPositions.get((byte)1)];
        else stringNumber = stringNumber + "X";
        if(definedPositions.containsKey((byte)2)) stringNumber = stringNumber + rndSecuence[definedPositions.get((byte)2)];
        else stringNumber = stringNumber + "X";
        if(definedPositions.containsKey((byte)3)) stringNumber = stringNumber + rndSecuence[definedPositions.get((byte)3)];
        else stringNumber = stringNumber + "X";
        return stringNumber;
    }
}
