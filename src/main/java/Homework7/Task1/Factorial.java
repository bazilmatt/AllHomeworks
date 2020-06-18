package Homework7.Task1;

import java.awt.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Factorial {
    //public static int[] startArray = {1, 3, 5, 29, 100, 12, 12, 12};
    private static int[] startArray = {1023, 6544, 654, 876, 225, 4598, 998, 365, 546, 10, 2, 32};
    static HashMap<Integer,BigInteger> resultMap = new HashMap<>();

    public static void setResultMap(Integer k, BigInteger v) {
        Factorial.resultMap.put(k, v);
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int value : startArray) {
            executorService.submit(new Calculator(value));
        }


        executorService.shutdown();
        try{
            executorService.awaitTermination(1, TimeUnit.MINUTES);
        } catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(resultMap.toString());
    }
}

class Calculator implements Runnable {
    private int element;
    BigInteger rez = BigInteger.valueOf(1);

    public Calculator(int id) {
        this.element = id;
    }

    public void run() {
        try {
            for (int i = 1; i <= element; i++) {
                rez = rez.multiply(BigInteger.valueOf(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Factorial of " + element + " is " + rez);
        Factorial.setResultMap(element, rez);
    }

    public BigInteger getRez() {
        return rez;
    }
}
