package sample.GermanyToNumber;

class Zig {
    static int parse(Words words, int num) throws Ex {
        String wordsArray[] = words.getWords();
        String zig = GermanyParser.ZIG;
        String simpleNum = wordsArray[0].substring(0, wordsArray[0].length() - zig.length());

        if (!Simple.fromInterval(simpleNum, GermanyParser.ZIG, 2, 9)) {
            throw new Ex("Неверно указано число десятичного формата.", words, 0);
        } else if (simpleNum.equals("drei") && wordsArray[0].lastIndexOf("big") != wordsArray[0].length() - zig.length()) {
            throw new Ex("После drei должно идти \'ßig\'.", words, 0);
        }


        num += Simple.getSimpleNum(simpleNum, GermanyParser.ZIG) * 10;
        words.move(1);
        wordsArray = words.getWords();

        findError(wordsArray, words);

        return GermanyParser.parse(words, num);
    }

    private static void findError(String[] wordsArray, Words words) throws InvalidPosition {
        if (GermanyParser.checkNextRank(GermanyParser.ZIG, wordsArray, GermanyParser.ZIG)) {
            throw new InvalidPosition("После числа десятичного формата не может идти число десятичного формата", words, 0);
        } else if (GermanyParser.checkNextRank(GermanyParser.HUNDRED, wordsArray, GermanyParser.HUNDRED)) {
            throw new InvalidPosition("После числа десятичного формата не может идти число соттеного формата", words, 1);
        } else if (GermanyParser.checkNextRank(GermanyParser.ZEHN, wordsArray, GermanyParser.ZEHN)) {
            throw new InvalidPosition("После числа десятичного формата не может идти число формата 13-19", words, 1);
        } else if (GermanyParser.checkNextRank(GermanyParser.SIMPLE, wordsArray, GermanyParser.SIMPLE)) {
            throw new InvalidPosition("После числа десятичного формата не может идти число еденичного формата", words, 0);
        } else if (GermanyParser.checkNextRank(GermanyParser.UND, wordsArray, GermanyParser.UND)) {
            throw new InvalidPosition("После числа десятичного формата не может идти und", words, 1);
        }
    }

    static boolean isZig(String[] words) {
        String zig = GermanyParser.ZIG;
        return words.length >= 1 && words[0].length() >= 3
                && (words[0].lastIndexOf(zig) == words[0].length() - zig.length() ||
                    words[0].indexOf("drei") == 0 && words[0].lastIndexOf("big") == words[0].length() - zig.length());
    }
}
