package sample.GermanyToNumber;

class Zehn {
    static int parse(Words words, int num) throws Ex {
        String wordsArray[] = words.getWords();
        String zehn = GermanyParser.ZEHN;
        String simpleNum = wordsArray[0].substring(0, wordsArray[0].length() - zehn.length());

        if (!Simple.fromInterval(simpleNum, GermanyParser.ZEHN, 3, 9)) {
            throw new Ex("Неверно указано число формата 13-19.", words, 0);
        }

        num += Simple.getSimpleNum(simpleNum, GermanyParser.ZEHN) + 10;
        words.move(1);
        wordsArray = words.getWords();

        findError(wordsArray, words);

        return GermanyParser.parse(words, num);
    }

    private static void findError(String[] wordsArray, Words words) throws InvalidPosition {
        if (GermanyParser.checkNextRank(GermanyParser.ZIG, wordsArray, GermanyParser.ZIG)) {
            throw new InvalidPosition("После числа формата 13-19 не может стоять число десятичного формата", words, 2);
        } else if (GermanyParser.checkNextRank(GermanyParser.HUNDRED, wordsArray, GermanyParser.HUNDRED)) {
            throw new InvalidPosition("После числа формата 13-19 не может стоять число сотенного формата", words, 2);
        } else if (GermanyParser.checkNextRank(GermanyParser.ZEHN, wordsArray, GermanyParser.ZEHN)) {
            throw new InvalidPosition("После числа формата 13-19 не может стоять число формата 13-19", words, 1);
        } else if (GermanyParser.checkNextRank(GermanyParser.SIMPLE, wordsArray, GermanyParser.SIMPLE)) {
            throw new InvalidPosition("После числа формата 13-19 не может стоять число единичного формата", words, 1);
        } else if (GermanyParser.checkNextRank(GermanyParser.UND, wordsArray, GermanyParser.UND)) {
            throw new InvalidPosition("После числа формата 13-19 не может стоять число с und", words, 2);
        }
    }

    static boolean isZehn(String[] words) {
        String zehn = GermanyParser.ZEHN;
        return words.length >= 1 && words[0].length() >= GermanyParser.ZEHN.length()
                && (words[0].lastIndexOf(zehn) == words[0].length() - zehn.length())
                && !words[0].equals(GermanyParser.ZEHN);
    }
}
