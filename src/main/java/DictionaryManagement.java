import dict.Dictionary;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.io.FileWriter;
public class DictionaryManagement {
    // Attributes
    private final Dictionary dict;
    private final String OUTPUT_DICT = "D:\\JAVA_FILE\\Project 2 -dictionary\\dictionariesOut.txt";

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
            String DICT_LOCATION = "src/main/java/dictionaries.txt";
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
                    out.write("     ");
                    out.write(wr[n][1]);
                    out.write("\n");
                    ++n;
                }
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**Edit a word and meaning * . */
    public void dictionaryEdit(int lineNumber, String data) throws IOException {
        Path path = Paths.get("dictionaries.txt");
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        lines.set(lineNumber - 1, data);
        Files.write(path, lines, StandardCharsets.UTF_8);
        }
        /*
        2 đoạn method edit và delete của tui thì tui chưa kiểm thử đc vì tui chưa biết cách cho máy tui build DictionaryManagement thay vì liên tục build Dictionary
        tui dùng inspect code thì không thấy có lỗi gì, nếu các ông thấy có lỗi hay là cái UI không hay lắm thì sửa giúp vào bảo tui
         */

    public void dictionaryDelete() throws IOException {
        File inputFile = new File("src/main/java/dictionaries.txt");
        File tempFile = new File("src/main/java/dictionariestemp.txt");
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
        String currentline;
        while ((currentline = reader.readLine()) != null) {
            if(!currentline.equalsIgnoreCase("")){
            /*ở đây tui muốn đoạn này nhận từ để delete từ người dùng nhưng tui chưa biết làm thế nào
            để cho từ đó vào đây, các ông có cách nào tốt hơn thì bảo tui
             */
                writer.write(currentline + System.getProperty("line.separator"));
            }
        }
        writer.close();
        reader.close();
    }
    /*
    ở cái tệp java này tui nhờ máy quick fix nhiều đoạn cho đỡ lỗi, có thể sai nên các ông kiểm thử
     */
}
