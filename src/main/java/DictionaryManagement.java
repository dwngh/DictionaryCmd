import dict.Dictionary;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class DictionaryManagement {
    // Attributes
    private Dictionary dict;
    private String DICT_LOCATION = "src/main/java/dictionaries.txt";
    private String OUTPUT_DICT = "src/main/java/dictionariesOut.txt";

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

    /**show all words in dictionary. */
    public String[][] showDict() {
        return dict.showAllDictionary();
    }

    public void insertFromFile() {
        try {
            BufferedReader buf = new BufferedReader(new FileReader(DICT_LOCATION));
            String line = null;
            String[] dict_word;
            dict.clean();
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
    public void editWord() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the word you want to edit: ");
        String wtg = sc.nextLine();
        System.out.println("The meaning of the word is after editing is: ");
        String wel = sc.nextLine();
        if (!dict.edit(wtg, wel)) {
            System.out.println("Couldn't find the word to fix!");
        } else {
            System.out.println("The word has been successfully corrected!");
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

    /**Export To File. */
    public void dictionaryExportToFile() {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(OUTPUT_DICT));
            String[][] wr = dict.showAllDictionary();
            if (wr[0][0] == null) {
                System.out.println("Dictionary is empty!");
            } else {
                int n = 0;
                while (n < wr.length) {
                    out.write(wr[n][0]);
                    out.write("\t");
                    out.write(wr[n][1]);
                    out.write("\n");
                    ++n;
                }
            }
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getOUTPUT_DICT() {
        return OUTPUT_DICT;
    }

    /**
     * Delete method with commandline
     * */
    public void delete() {
        System.out.print("Index of word you want to delete: ");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        String[] word = dict.getWord(n);
        if (word == null) System.out.println("Invalid index! ");
        else {
            System.out.println("Are you sure to delete word:" + word[0] + "? (Y to confirm)");
            System.out.print(">>>> ");
            char chr = sc.nextLine().charAt(0);
            if (chr == 'Y') {
                dict.delete(n);
                System.out.println("Deleted! ");
            } else {
                System.out.println("Terminated! ");
            }
        }
    }

    
}
