package sample.GermanyToNumber;

class Hundred {
    static int parse(Words words, int num) throws Ex {
        String wordsArray[] = words.getWords();

        if (!Simple.fromInterval(wordsArray[0], GermanyParser.HUNDRED, 1, 9)) {
            throw new Ex("Неверно указано число сотенного формата.", words, 0);
        }

        num += Simple.getSimpleNum(wordsArray[0], GermanyParser.HUNDRED) * 100;
        words.move(2);
        wordsArray = words.getWords();

        if (GermanyParser.checkNextRank(GermanyParser.HUNDRED, wordsArray, GermanyParser.HUNDRED)) {
            throw new InvalidPosition("После числа соттеного формата не может идти число соттеного формата", words, 1);
        }

        return GermanyParser.parse(words
                , num
                , GermanyParser.UND
                , GermanyParser.ZIG
                , GermanyParser.ZEHN
                , GermanyParser.SIMPLE);
    }

    static boolean isHundred(String[] words) {
        return words.length > 1 && words[1].equals(GermanyParser.HUNDRED);
    }

}
