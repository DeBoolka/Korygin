package sample.GermanyToNumber;

import java.util.Arrays;

class Words {

    private final String[] words;

    private int start = 0;

    private int end;

    Words(String words[]) {
        this.words = words;
        end = words.length;
    }

    String[] getWords() {
        return Arrays.copyOfRange(words, start, end);
    }

    String[] getWords(boolean all) {
        if (all) {
            return words;
        } else {
            return Arrays.copyOfRange(words, start, end);
        }
    }

    void move(int moveIndex) {
        start += moveIndex;
    }

    int getStart() {
        return start;
    }

    String getBasicString() {
        return Arrays.toString(words);
    }

}
