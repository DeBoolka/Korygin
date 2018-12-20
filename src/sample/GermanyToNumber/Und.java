package sample.GermanyToNumber;

class Und {
    static int parse(Words words, int num) throws Ex {
        String[] wordsArray = words.getWords();

        if (wordsArray[0].equals("und")) {
            throw new Ex("Перед und должно идти число еденичного формата.", words, 0);
        } else if (!Simple.fromInterval(wordsArray[0], GermanyParser.UND, 1, 9)) {
            throw new Ex("Ошибка в слове: " + wordsArray[0], words, 0);
        }


        num += Simple.getSimpleNum(wordsArray[0], GermanyParser.UND);

        findError(wordsArray, words);

        words.move(2);
        wordsArray = words.getWords();

        if (wordsArray.length == 0) {
            throw new Ex("После слова с und должно идти десятичное число.", words, 0);
        }


        return GermanyParser.parse(words
                , num
                , GermanyParser.ZIG);
    }

    public static void findError(String[] wordsArray, Words words) throws InvalidPosition {
        String wordsArrayB[] = words.getWords();
        if (wordsArrayB.length > 0 && wordsArray[0].equals("und")) {
        } else if (wordsArrayB.length > 0 && !Simple.fromInterval(wordsArray[0], GermanyParser.UND, 1, 9)) {
            throw new InvalidPosition("Ошибка в слове: " + wordsArray[0], words, 0);
        }

        if (GermanyParser.checkBackRank(GermanyParser.ZEHN, words, GermanyParser.ZEHN)) {
            throw new InvalidPosition("После числа формата 11-19 не может идти und", words, 1);
        } else if (GermanyParser.checkBackRank(GermanyParser.ZIG, words, GermanyParser.ZIG)) {
            throw new InvalidPosition("После числа десятичного формата не может идти und", words, 0);
        } else if (GermanyParser.checkBackRank(GermanyParser.UND, words, GermanyParser.UND)) {
            throw new InvalidPosition("Два числа формата und", words, 0);
        }
    }

    static boolean isUnd(String[] words) {
        return words.length > 1 && (words[0].equals(GermanyParser.UND) || words[1].equals(GermanyParser.UND))
                || words.length > 0 && (words[0].equals(GermanyParser.UND));
    }
}
