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


    public static void main(String[] args) throws IOException {
        PhraseGenerator pg = new PhraseGenerator();

        for (int i = 0; i < 5; i++) {
            OutputStreamWriter fos;
            fos = new OutputStreamWriter(new FileOutputStream(String.valueOf(i)));
            try {
                fos.append(pg.generateParagraph());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                fos.close();
            }
        }

    }
}
