import java.io.IOException;
import java.util.Scanner;

public class DictionaryApplication {
    static void instruction() {
        System.out.println("Choose one of number below to perform an action: ");
        System.out.println("1. dictionaryBasic");
        System.out.println("2. dictionaryAdvance");
        System.out.println("3. dictionarySearcher");
        System.out.println("4. Edit");
        System.out.println("5. Delete");
        System.out.println("6. Save to file");
        System.out.println("7. Exit");
    }

    static boolean loop(DictionaryCommandline dc) throws IOException {
        Scanner s = new Scanner(System.in);
        instruction();
        System.out.print(">>> ");
        int n = s.nextInt();
        switch (n) {
            case 1:
                dc.dictionaryBasic();
                break;
            case 2:
                dc.dictionaryAdvanced();
                break;
            case 3:
                dc.dictionarySearcher();
                break;
            case 4:
                dc.edit();
                break;
            case 5:
                dc.delete();
                break;
            case 6:
                dc.outFile();
                break;
            case 7:
                return false;
            default:
                System.out.println("Invalid number!");
        };
        System.out.print("Press the enter key to continue");
        s.nextLine();
        s.nextLine();
        System.out.println("\n\n\n\n\n\n\n");
        return true;
    }
    public static void main(String[] args) throws IOException {
        DictionaryCommandline dc = new DictionaryCommandline();
        while (loop(dc)) {};
    }
}