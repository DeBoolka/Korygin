package sample.GermanyToNumber;

class Hundred {
    static int parse(Words words, int num) throws Ex {
        String[] wordsArray = words.getWords();

        if (wordsArray[0].equals(GermanyParser.HUNDRED)) {
            throw new Ex("Перед hundert должно идти число еденичного формата.", words, 0);
        } else if (!Simple.fromInterval(wordsArray[0], GermanyParser.HUNDRED, 1, 9)) {
            throw new Ex("Неверно указано число сотенного формата: " + wordsArray[0], words, 0);
        }

        num += Simple.getSimpleNum(wordsArray[0], GermanyParser.HUNDRED) * 100;

        if (GermanyParser.checkBackRank(GermanyParser.HUNDRED, words, GermanyParser.HUNDRED)) {
            throw new InvalidPosition("Два числа соттеного формата", words, 1);
        }

        words.move(2);
        wordsArray = words.getWords();


        return GermanyParser.parse(words
                , num
                , GermanyParser.UND
                , GermanyParser.ZIG
                , GermanyParser.ZEHN
                , GermanyParser.SIMPLE);
    }

    public static void findError(String[] wordsArray, Words words) throws InvalidPosition {
        String wordsArrayB[] = words.getWords();
        if (wordsArrayB.length > 0 && wordsArray[0].equals(GermanyParser.HUNDRED)) {
        } else if (wordsArrayB.length > 0 && !Simple.fromInterval(wordsArray[0], GermanyParser.HUNDRED, 1, 9)) {
            throw new InvalidPosition("Ошибка в слове: " + wordsArray[0], words, 0);
        }

        if (GermanyParser.checkBackRank(GermanyParser.HUNDRED, words, GermanyParser.HUNDRED)) {
            throw new InvalidPosition("Два числа соттеного формата", words, 1);
        } else if (GermanyParser.checkBackRank(GermanyParser.ZEHN, words, GermanyParser.ZEHN)) {
            throw new InvalidPosition("После числа десятичного формата не может идти число соттеного формата.", words, 1);
        } else if (GermanyParser.checkNextRank(GermanyParser.UND, wordsArray, GermanyParser.UND)) {
            throw new InvalidPosition("После числа с und не может идти число соттеного формата", words, 1);
        } else if (!GermanyParser.checkBackRank(GermanyParser.ZIG, words, GermanyParser.ZIG)) {
            throw new InvalidPosition("После числа десятичного формата не может идти число соттеного формата. ", words, 0);
        }
    }

    static boolean isHundred(String[] words) {
        return words.length > 1 && (words[0].equals(GermanyParser.HUNDRED) || words[1].equals(GermanyParser.HUNDRED))
                || words.length > 0 && (words[0].equals(GermanyParser.HUNDRED));
    }

}
