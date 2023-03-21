import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class WordGenerator {
    final static boolean IS_WORDLIST_MODE = true; // true = take inputs from word list, false = generate random words
    final static int WORDLIST_LINE_LIMIT = 2000;
    final static String WORDLIST_FILENAME = "wordlist.txt";
    final static String FILE_FORMAT = "in";
    final static int NUM_OF_FILES = 10;
    final static int CHAR_LIMIT = 4; // 63; // used only in not wordlist mode
    final static char[] ALPHANUMERIC ="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789#!".toCharArray(); // used only in not wordlist mode
    final static int MAX_WORDS = 10000;

    public static void main(String[] args) throws IOException {
        // Generate files
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(WORDLIST_FILENAME))) {
            String line;
            int curLine = 0;
            while ((line = br.readLine()) != null) {
                String[] strings = line.split("\n");
                for (String string : strings) {
                    lines.add(string);
                }

                curLine++;
                if (curLine >= WORDLIST_LINE_LIMIT) {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        String[] listOfWords = lines.toArray(new String[0]);

        for (int curFile = 0; curFile < NUM_OF_FILES; curFile++) {
            PrintWriter write = new PrintWriter(new File(FILE_FORMAT + curFile + ".txt"));
            int words = MAX_WORDS; // Math.max((int) (Math.random() * MAX_WORDS), 8 * MAX_WORDS / 10);

            if (IS_WORDLIST_MODE) {
                for (int curWord = 0; curWord < words; curWord++) {
                    write.println(listOfWords[(int) (Math.random() * listOfWords.length)] + generateWord());
                }

                continue;
            }
            // RANDOM WORD MODE
            for (int curWord = 0; curWord < words; curWord++) {
                write.println(generateWord());
            }

            write.close();
        }
    }

    public static String generateWord() {
        // Generate character count of random word
        int charCount = (int) ((Math.random() * CHAR_LIMIT) + 1);
        String result = "";

        // Generate the word, character by character
        for (int i = 0; i < charCount; i++) {
            result += ALPHANUMERIC[(int) (Math.random() * ALPHANUMERIC.length)];
        }

        return result;
    }
}