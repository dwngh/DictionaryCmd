import dict.Dictionary;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class DictionaryManagement {
    // Attributes
    private Dictionary dict;
    private String DICT_LOCATION = "src/main/java/dictionaries.txt";

    /**
     * Constructor
     * */
    public DictionaryManagement() {
        dict = new Dictionary();
    }

    /**
     * Function get words with lookup
     * @param lookup (set lookup = "" to get all words)
     * */
    public String[][] getWords(String lookup) {
        return dict.getAllWords(lookup);
    }

    public void insertFromFile() {
        try {
            BufferedReader buf = new BufferedReader(new FileReader(DICT_LOCATION));
            String line = null;
            String[] dict_word;
            while (true) {
                line = buf.readLine();
                if (line == null) {
                    break;
                } else {
                    dict_word = line.split("\\t", 2);
                    if (dict_word.length != 2) {
                        throw new Exception("Invalid line");
                    } else {
                        dict.insertNewWord(dict_word[0], dict_word[1]);
                    }
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    public void dictionaryLookup() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Word to lookup: ");
        String word_target = sc.nextLine();
        String word_explain = dict.getWordExplain(word_target);
        if (word_explain == null) {
            System.out.println("There's not that word yet! ");
        } else {
            System.out.print("Word explain: ");
            System.out.println(word_explain);
        }
    }

    /**
     * Commandline functions and methods.
     * */
    public void insertFromCommandline() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < n; i++) {
            String word_target, word_explain;
            word_target = sc.nextLine();
            word_explain = sc.nextLine();
            dict.insertNewWord(word_target, word_explain);
        }
    }
}
