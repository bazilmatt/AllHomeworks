package Homework6.task2;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

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
 *
 */

public class PhraseGenerator {

    Random rnd = new Random();
    static Set words = new TreeSet();
    private int n4 = 1000; // number of words in dictionary
    private int n1 = 15; // letters in one word
    private int n2 = 15; // words in one sentence
    private int n3 = 20; // sentences in one paragraph
    private String[] randomWords = new String[n4]; // words dictionary - propability to take one 1/n4
    private final String[] endState = {"! ",". ","? "}; // ends of sentences


    Random random = new Random();
    StringBuilder randomSentence = new StringBuilder();
    StringBuilder randomParagrath = new StringBuilder();


    public void getFiles(String path, int n, int size, String[] words, int probability) throws IOException {
        File file = new File("bbb");
        file.createNewFile();
    }


    public String[] generateDictionary() {
        int n4max = random.nextInt(n4);
        for (int i = 0; i < n4max; i++) {
            char[] word = new char[random.nextInt(n1) + 1];
            for (int j = 0; j < word.length; j++) {
                word[j] = (char) ('a' + random.nextInt(26));
            }
            randomWords[i] = new String(word);
        }
        return randomWords;
    }

    public String twentyPercent(){
        int chance = rnd.nextInt(100);
        return (chance<20)?", ":" ";         //twenty percent probability for random end of sentence markers
    }

    /**
     *
     * @return one sentence with n2 words
     */
    public StringBuilder generateSentence(){
        randomSentence.setLength(0);
        generateDictionary();
        int n2max = random.nextInt(n2);
        for (int i = 0; i < n2max; i++) {
            randomSentence.append(randomWords[rnd.nextInt(randomWords.length)]);
            randomSentence.substring(0,1).toUpperCase();
            if ((i + 1 != n2)) {
                randomSentence.append(twentyPercent());
            } else {
                randomSentence.append(endState[rnd.nextInt(endState.length)]);
            }
        }
        return randomSentence;
    }

    /**
     *
     * @return new paragraph with n3 sentences
     */
    public String generateParagraph(){        // generate random paragraph with 'n3' sentences
        randomParagrath = new StringBuilder();
        int n3max = random.nextInt(n3);
        for (int i = 0; i < n3max; i++) {
            if ((i == 0)) {
                randomParagrath.append('\t');
            }
            randomParagrath.append(generateSentence());
        }
        randomParagrath.append('\n');
        randomParagrath.append('\r');
        return randomParagrath.toString();
    }

}