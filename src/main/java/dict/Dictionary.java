package dict;

import java.util.ArrayList;

public class Dictionary {
    // Attribute
    private ArrayList<Word> dict;

    // Constructor
    public Dictionary() {
        dict = new ArrayList<Word>();
    }

    /**
     * Adding new word to the dictionary
     * @param word_explain      word_explain of new Word
     * @param word_target       word_target of new Word
     * */
    public void insertNewWord(String word_target, String word_explain) {
        dict.add(new Word(word_target, word_explain));
    }

    /**
     * Get all words with lookup request
     * @param lookup    If it's empty, method will return all the words
     *                  Otherwise, it will return the words which starting with lookup string */
    public String[][] getAllWords(String lookup) {
        String[][] r = new String[dict.size()][2];
        if (lookup == null || lookup.length() == 0) {
            for (int i = 0; i < dict.size(); i++) {
                r[i][0] = dict.get(i).getWord_target();
                r[i][1] = dict.get(i).getWord_explain();
            }
        } else {
            int index = 0;
            for (Word item : dict) {
                boolean isSimilar = true;
                for (int i = 0; i < lookup.length(); i++) {
                    if (item.getWord_target().charAt(i) != lookup.charAt(i)) {
                        isSimilar = false;
                        break;
                    }
                }
                if (isSimilar) {
                    r[index][0] = item.getWord_target();
                    r[index][1] = item.getWord_explain();
                    index++;
                }
            }
        }
        return r;
    }

    /**
     * Get word_explain of given word_target
     * @param word_target
     * @return If that word_target is existed, return its word_explain
     *          Otherwise, return null
     */
    public String getWordExplain(String word_target) {
        for (Word item : dict) {
            if (item.getWord_target().equals(word_target)) {
                return item.getWord_explain();
            }
        }
        return null;
    }

    /** Delete a Word from Dictionary */
    public void delete(int index) {
        dict.remove(index);
    }

    /** Edit word_explain of a word_target in Dictionary
     * @param word_target
     * @param word_explain
     * @return  True if there's the word with given word_target
     *          False if there's not word with given word_target */
    public boolean edit(String word_target, String word_explain) {
        boolean isExisted = false;
        for (Word item : dict) {
            if (item.getWord_target().equals(word_target)) {
                isExisted = true;
                item.setWord_explain(word_explain);
            }
        }
        return isExisted;
    }
}
