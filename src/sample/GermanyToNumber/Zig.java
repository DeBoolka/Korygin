package sample.GermanyToNumber;

class Zig {
    static int parse(Words words, int num) throws Ex {
        String[] wordsArray = words.getWords();
        String zig = GermanyParser.ZIG;
        String simpleNum = wordsArray[0].substring(0, wordsArray[0].length() - zig.length());

        if (!Simple.fromInterval(simpleNum, GermanyParser.ZIG, 2, 9)) {
            throw new Ex("Неверно указано число десятичного формата.", words, 0);
        } else if (simpleNum.equals("drei") && wordsArray[0].lastIndexOf("big") != wordsArray[0].length() - zig.length()) {
            throw new Ex("После drei должно идти \'ßig\'.", words, 0);
        }


        num += Simple.getSimpleNum(simpleNum, GermanyParser.ZIG) * 10;
        findError(wordsArray, words);
        words.move(1);
        wordsArray = words.getWords();


        return GermanyParser.parse(words, num);
    }

    public static void findError(String[] wordsArray, Words words) throws InvalidPosition {
        if (GermanyParser.checkBackRank(GermanyParser.ZIG, words, GermanyParser.ZIG)) {
            throw new InvalidPosition("Два числа десятичного формата", words, 0);
        } else if (GermanyParser.checkBackRank(GermanyParser.ZEHN, words, GermanyParser.ZEHN)) {
            throw new InvalidPosition("После числа формата 11-19 не может стоять число десятичного формата", words, 1);
        } /*else if (!GermanyParser.checkBackRank(GermanyParser.UND, words, GermanyParser.UND)) {
            throw new InvalidPosition("После числа с und должно идти число десятичного формата", words, 1);
        }*/
    }

    static boolean isZig(String[] words) {
        String zig = GermanyParser.ZIG;
        return words.length >= 1 && words[0].length() >= 3
                && (words[0].lastIndexOf(zig) == words[0].length() - zig.length() ||
                    words[0].indexOf("drei") == 0 && words[0].lastIndexOf("big") == words[0].length() - zig.length());
    }
}
