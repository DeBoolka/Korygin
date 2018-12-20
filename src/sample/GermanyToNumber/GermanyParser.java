package sample.GermanyToNumber;

import java.util.Arrays;

public class GermanyParser {

    static final String HUNDRED = "hundert";
    static final String UND = "und";
    static final String ZEHN = "zehn";
    static final String ZIG = "zig";
    static final String SIMPLE = "simple";
    static final String[] ALL = new String[]{HUNDRED, UND, ZEHN, ZIG, SIMPLE};

    public static int getNumber(String text) throws Ex {
        String wordsArr[] = text.trim().toLowerCase()
                .replace('ß', 'b')
                .replace('ü', 'u')
                .replace('ö', 'o')
                .split("\\s+");
        Words words = new Words(wordsArr);

        return parse(words, 0
                , GermanyParser.HUNDRED
                , GermanyParser.UND
                , GermanyParser.ZIG
                , GermanyParser.ZEHN
                , GermanyParser.SIMPLE);
    }

    static int parse(Words words, int num, String... ranks) throws Ex {
        String wordsArr[] = words.getWords();
        if (wordsArr.length == 0) {
            return num;
        }

        if (checkNextRank(ZIG, wordsArr, ranks)) {
            num = Zig.parse(words, num);
        } else if (checkNextRank(ZEHN, wordsArr, ranks)) {
            num = Zehn.parse(words, num);
        } else if (checkNextRank(HUNDRED, wordsArr, ranks)) {
            num = Hundred.parse(words, num);
        } else if (checkNextRank(UND, wordsArr, ranks)) {
            num = Und.parse(words, num);
        } else if (checkNextRank(SIMPLE, wordsArr, ranks)) {
            num = Simple.parse(words, num);
        } else {
            throw error(words, num, ranks, wordsArr);
        }

        return num;
    }

    private static Ex error(Words words, int num, String[] ranks, String[] wordsArr) throws InvalidPosition {
        if (wordsArr.length > 0) {
            if (Zehn.isZehn(wordsArr)) {
//                words.move(-1);
                Zehn.findError(wordsArr, words);
            } else if (Zig.isZig(wordsArr)) {
//                words.move(-1);
                Zig.findError(wordsArr, words);
            } else if (Simple.isSimple(wordsArr)) {
//                words.move(-1);
                Simple.findError(wordsArr, words);
            } else if (!wordsArr[0].equals(HUNDRED) && Und.isUnd(wordsArr)) {
//                words.move(-2);
                Und.findError(wordsArr, words);
            } else if (!wordsArr[0].equals(UND) && Hundred.isHundred(wordsArr)) {
//                words.move(-2);
                Hundred.findError(wordsArr, words);
            }
        }
        return new InvalidPosition("Ошибка в слове.", words, 0, true);
    }

    static boolean checkNextRank(String rank, String[] words, String... ranks) {
        if (words.length == 0 || !containsInArray(rank, ranks)) {
            return false;
        }

        switch (rank) {
            case ZIG:
                return Zig.isZig(words);
            case ZEHN:
                return Zehn.isZehn(words);
            case SIMPLE:
                return Simple.isSimple(words);
            case UND:
                return Und.isUnd(words);
            case HUNDRED:
                return Hundred.isHundred(words);
            default:
                return false;
        }
    }

    static boolean checkBackRank(String rank, Words words, String... ranks) {
        String[] text = words.getWords(true);
        int startW = words.getStart();
        if (startW == 0) {
            return false;
        }

        if (startW > 0 && !rank.equals(HUNDRED) && !rank.equals(UND)) {
            return GermanyParser.checkNextRank(rank, Arrays.copyOfRange( text, startW - 1, text.length), ALL);
        } else if (startW > 1) {
            return GermanyParser.checkNextRank(rank, Arrays.copyOfRange( text, startW - 2, text.length), ALL);
        }

        return false;
    }

    private static int containsCountWord(String[] words, String word, int numWord) {
        int count = 0;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word)) {
                count++;
                if (count == numWord) {
                    return i;
                }
            }
        }

        return -1;
    }

    private static boolean containsInArray(Object value, Object... array) {
        for (Object item : array) {
            if (item.equals(value)) {
                return true;
            }
        }
        return false;
    }

}
