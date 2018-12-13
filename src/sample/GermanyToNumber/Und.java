package sample.GermanyToNumber;

class Und {
    static int parse(Words words, int num) throws Ex {
        String wordsArray[] = words.getWords();

        if (!Simple.fromInterval(wordsArray[0], GermanyParser.UND, 1, 9)) {
            throw new Ex("Неверно указано простое число перед und", words, 0);
        }


        num += Simple.getSimpleNum(wordsArray[0], GermanyParser.UND);
        words.move(2);
        wordsArray = words.getWords();

        findError(wordsArray, words);

        return GermanyParser.parse(words
                , num
                , GermanyParser.ZIG);
    }

    private static void findError(String[] wordsArray, Words words) throws InvalidPosition {
        if (GermanyParser.checkNextRank(GermanyParser.HUNDRED, wordsArray, GermanyParser.HUNDRED)) {
            throw new InvalidPosition("После und не должно идти число соттеного формата", words, 1);
        } else if (GermanyParser.checkNextRank(GermanyParser.ZEHN, wordsArray, GermanyParser.ZEHN)) {
            throw new InvalidPosition("После und не должно идти число формата 13-19", words, 1);
        } /*else if (GermanyParser.checkNextRank(GermanyParser.SIMPLE, wordsArray, GermanyParser.SIMPLE)) {
            throw new InvalidPosition("После едениц не должны индети единицы", words, 1);
        } */else if (!GermanyParser.checkNextRank(GermanyParser.ZIG, wordsArray, GermanyParser.ZIG)) {
            throw new InvalidPosition("После und должно быть число десятичного формата", words, 0);
        }
    }

    static boolean isUnd(String[] words) {
        return words.length > 1 && words[1].equals(GermanyParser.UND);
    }
}
