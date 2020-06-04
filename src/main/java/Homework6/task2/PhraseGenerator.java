package Homework6.task2;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Random;

/**
 * Предложение состоит из 1<=n1<=15 слов. В предложении после произвольных слов могут находиться запятые.
 * Слово состоит из 1<=n2<=15 латинских букв
 * Слова разделены одним пробелом
 * Предложение начинается с заглавной буквы
 * Предложение заканчивается (.|!|?)+" "
 * Текст состоит из абзацев. в одном абзаце 1<=n3<=20 предложений.
 * В конце абзаца стоит разрыв строки и перенос каретки.
 * Есть массив слов 1<=n4<=1000. Есть вероятность probability вхождения
 * одного из слов этого массива в следующее предложение (1/probability).
 * <p>
 * Необходимо написать метод getFiles(String path, int n, int size, String[] words, int probability),
 * который создаст n файлов размером size в каталоге path. words - массив слов, probability - вероятность.
 */

public class PhraseGenerator {

    Random rnd = new Random();
    // static Set words = new TreeSet();
    private final int n4 = 1000; // number of words in dictionary
    private final String[] dictionary = new String[n4]; // words dictionary - propability to take one 1/n4
    private final String[] endState = {"! ", ". ", "? "}; // ends of sentences


    Random random = new Random();
    StringBuilder randomSentence = new StringBuilder();
    StringBuilder randomParagrath = new StringBuilder();
    String buffer = "";

    public void getFiles(String path,
                         int n, int size,
                         String[] words,
                         int probability) {

        for (int i = 0; i < n; i++) {
            String filename = path + i;

            File file = new File(filename);

            long fileleft;

            while (file.length() < size) {
                System.out.println("fillength = " + file.length());
                fileleft = size - file.length();
                System.out.println("fileleft = " + fileleft);
                try (OutputStreamWriter fos = new OutputStreamWriter(new FileOutputStream(filename))) {
                    if (buffer.length() > fileleft) {
                        buffer = buffer.substring(0, (int) (fileleft));
                        System.out.println(buffer);
                        fos.append(buffer);
                    } else {
                        System.out.println(buffer);
                        fos.append(buffer);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

    }

    public String generateWord() {
        // letters in one word
        int n1 = 15;
        char[] word = new char[random.nextInt(n1) + 1];
        for (int j = 0; j < word.length; j++) {
            word[j] = (char) ('a' + random.nextInt(26));
        }
        return new String(word);
    }

    public String[] generateDictionary() {
        int n4max = random.nextInt(n4);
        for (int i = 0; i < n4max; i++) {
            dictionary[i] = generateWord();
        }
        return dictionary;
    }

    public String twentyPercent() {
        int chance = rnd.nextInt(100);
        return (chance < 20) ? ", " : " ";         //twenty percent probability for random end of sentence markers
    }

    /**
     * @return one sentence with n2 words
     */
    public StringBuilder generateSentence() {

        randomSentence.setLength(0);
        // words in one sentence
        int n2 = 15;
        int n2max = random.nextInt(n2);
        for (int i = 0; i < n2max; i++) {
            int probability = 80;
            String[] dictionary = generateDictionary();
            if (random.nextInt(100) < probability) {
                randomSentence.append(dictionary[rnd.nextInt(dictionary.length)]);
            } else {
                randomSentence.append(generateWord());
            }
            randomSentence.substring(0, 1).toUpperCase();
            if ((i + 1 != n2)) {
                randomSentence.append(twentyPercent());
            } else {
                randomSentence.append(endState[rnd.nextInt(endState.length)]);
            }
        }
        return randomSentence;
    }

    /**
     * @return new paragraph with n3 sentences
     */
    public String generateParagraph() {        // generate random paragraph with 'n3' sentences
        randomParagrath = new StringBuilder();
        // sentences in one paragraph
        int n3 = 20;
        int n3max = random.nextInt(n3);
        for (int i = 0; i < n3max; i++) {
            if ((i == 0)) {
                randomParagrath.append('\t');
            }
            randomParagrath.append(generateSentence());
        }
        randomParagrath.append('\r');
        randomParagrath.append('\n');

        return randomParagrath.toString();
    }

}