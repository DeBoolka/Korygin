package sample.GermanyToNumber;

class Simple {
    static int parse(Words words, int num) throws Ex {
        String wordsArray[] = words.getWords();

        if (!Simple.fromInterval(wordsArray[0], 1, 12)) {
            throw new Ex("Неверно указано число", words, 0);
        }


        num += Simple.getSimpleNum(wordsArray[0], GermanyParser.SIMPLE);
        words.move(1);
        wordsArray = words.getWords();

        findError(wordsArray, words);

        return GermanyParser.parse(words, num);
    }

    private static void findError(String[] wordsArray, Words words) throws InvalidPosition {
        if (GermanyParser.checkNextRank(GermanyParser.UND, wordsArray, GermanyParser.UND)) {
            throw new InvalidPosition("После числа еденичного формата не может идти und", words, 1);
        } else if (GermanyParser.checkNextRank(GermanyParser.ZIG, wordsArray, GermanyParser.ZIG)) {
            throw new InvalidPosition("После числа еденичного формата не может идти число десятичного формата", words, 0);
        } else if (GermanyParser.checkNextRank(GermanyParser.HUNDRED, wordsArray, GermanyParser.HUNDRED)) {
            throw new InvalidPosition("После числа еденичного формата не может идти число соттеного формата", words, 1);
        } else if (GermanyParser.checkNextRank(GermanyParser.ZEHN, wordsArray, GermanyParser.ZEHN)) {
            throw new InvalidPosition("После числа еденичного формата не может идти число формата 13-19", words, 1);
        } else if (GermanyParser.checkNextRank(GermanyParser.SIMPLE, wordsArray, GermanyParser.SIMPLE)) {
            throw new InvalidPosition("После числа еденичного формата не может идти число еденичного формата", words, 0);
        }
    }

    static boolean isSimple(String[] words) {
        return getSimpleNum(words[0]) != -1;
    }

    static int getSimpleNum(String word) {
        switch (word) {
            case "eins":
                return 1;
            case "zwei":
                return 2;
            case "drei":
                return 3;
            case "vier":
                return 4;
            case "funf":
                return 5;
            case "sechs":
                return 6;
            case "sieben":
                return 7;
            case "acht":
                return 8;
            case "neun":
                return 9;
            case "zehn":
                return 10;
            case "elf":
                return 11;
            case "zwolf":
                return 12;
        }

        return -1;
    }

    static int getSimpleNum(String word, String rank) {
        int num = getSimpleNum(word);
        switch (rank) {
            case GermanyParser.ZEHN:
                switch (word) {
                    case "sech":
                        return 6;
                    case "sieb":
                        return 7;
                    default:
                        return getNumExcept(num, 6, 7);
                }
            case GermanyParser.ZIG:
                switch (word) {
                    case "zwan":
                        return 2;
                    case "sech":
                        return 6;
                    case "sieb":
                        return 7;
                    default:
                        return getNumExcept(num, 2, 6, 7);
                }
            case GermanyParser.UND:
            case GermanyParser.HUNDRED:
                switch (word) {
                    case "ein":
                        return 1;
                    default:
                        return getNumExcept(num, 1);
                }
        }

        return num;
    }

    private static int getNumExcept(int num, int... numExcept) {
        for (int temp : numExcept) {
            if (num == temp) {
                return -1;
            }
        }

        return num;
    }

    static boolean fromInterval(String word, String rank, int from, int to) {
        int num = getSimpleNum(word, rank);
        return num >= from && num <= to;
    }

    static boolean fromInterval(String word, int from, int to) {
        return fromInterval(word, GermanyParser.SIMPLE, from, to);
    }

}
