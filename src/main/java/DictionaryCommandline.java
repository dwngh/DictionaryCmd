import java.util.Scanner;

public class DictionaryCommandline {
    // Attributes
    private DictionaryManagement dict_mng;

    /** Constructor */
    public DictionaryCommandline() {
        dict_mng = new DictionaryManagement();
    }

    /** Show all words in the dictionary */
    public void showAllWords() {
        String[][] list = dict_mng.getWords("");
        for (int i = 0; i < list.length; i++) {
            System.out.print(i + "    ");
            System.out.print(list[i][0] + "   ");
            System.out.println(list[i][1]);
        }
    };

    public void dictionaryBasic() {
        dict_mng.insertFromCommandline();
        showAllWords();
    };

    public void dictionaryAdvanced() {
        dict_mng.insertFromFile();
        showAllWords();
        dict_mng.dictionaryLookup();
    };

    public void dictionarySearcher() {
        dict_mng.insertFromFile();
        Scanner sc = new Scanner(System.in);
        System.out.print("Search: ");
        String lookup = sc.nextLine();
        String[][] r = dict_mng.getWords(lookup);
        System.out.println("Result: ");
        System.out.println("-------------------------");
        if (r[0][0] == null) {
            System.out.println("    No such words!");
        } else {
            int i = 0;
            while (r[i][0] != null) {
                System.out.println("    " + i + ". " + r[i++][0]);
            }
        }
        System.out.println("-------------------------");
    };
}
