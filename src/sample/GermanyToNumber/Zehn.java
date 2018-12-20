package sample.GermanyToNumber;

class Zehn {
    static int parse(Words words, int num) throws Ex {
        String[] wordsArray = words.getWords();
        String zehn = GermanyParser.ZEHN;
        String simpleNum = wordsArray[0].substring(0, wordsArray[0].length() - zehn.length());

        if (!Simple.fromInterval(simpleNum, GermanyParser.ZEHN, 3, 9)) {
            throw new Ex("Неверно указано число формата 13-19.", words, 0);
        }

        num += Simple.getSimpleNum(simpleNum, GermanyParser.ZEHN) + 10;
        findError(wordsArray, words);
        words.move(1);
        wordsArray = words.getWords();


        return GermanyParser.parse(words, num);
    }

    public static void findError(String[] wordsArray, Words words) throws InvalidPosition {
        if (GermanyParser.checkBackRank(GermanyParser.ZIG, words, GermanyParser.ZIG)) {
            throw new InvalidPosition("После числа десятичного формата не может идти число формата 11-19", words, 2);
        } else if (GermanyParser.checkBackRank(GermanyParser.ZEHN, words, GermanyParser.ZEHN)) {
            throw new InvalidPosition("Два числа формата 11-19", words, 1);
        } else if (GermanyParser.checkBackRank(GermanyParser.UND, words, GermanyParser.UND)) {
            throw new InvalidPosition("После числа с und не может стоять число формата 11-19", words, 2);
        }
    }

    static boolean isZehn(String[] words) {
        String zehn = GermanyParser.ZEHN;
        return words.length >= 1 && words[0].length() >= GermanyParser.ZEHN.length()
                && (words[0].lastIndexOf(zehn) == words[0].length() - zehn.length())
                && !words[0].equals(GermanyParser.ZEHN);
    }
}
