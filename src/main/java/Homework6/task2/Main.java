package Homework6.task2;

import java.io.*;

/**
 * Homework 6 Task 2
 * Logic and main structure of program
 * <p>
 * Program must write something into the file...
 * /*
 *
 * @author Matveev
 */

public class Main {


    public static void main(String[] args) {
        PhraseGenerator pg = new PhraseGenerator();

        pg.getFiles("test",3, 20000, pg.generateDictionary(), 80);


    }
}
