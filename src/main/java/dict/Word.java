package dict;

public class Word {
    private String word_target;
    private String word_explain;

    /** Getter and setter */
    String getWord_target() {
        return word_target;
    }

    String getWord_explain() {
        return word_explain;
    }

    void setWord_target(String wt) {
        word_target = wt;
    }

    void setWord_explain(String we) {
        word_explain = we;
    }

    /** Constructor */
    public Word(String wt, String we) {
        word_explain = we;
        word_target = wt;
    }
}
