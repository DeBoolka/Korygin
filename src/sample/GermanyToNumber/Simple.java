package sample.GermanyToNumber;

class Simple {
    static int parse(Words words, int num) throws Ex {
        String[] wordsArray = words.getWords();

        if (!Simple.fromInterval(wordsArray[0], 1, 12)) {
            throw new Ex("Неверно указано число", words, 0);
        }


        num += Simple.getSimpleNum(wordsArray[0], GermanyParser.SIMPLE);
        findError(wordsArray, words);
        words.move(1);
        wordsArray = words.getWords();


        return GermanyParser.parse(words, num);
    }

    public static void findError(String[] wordsArray, Words words) throws InvalidPosition {
        if (GermanyParser.checkBackRank(GermanyParser.SIMPLE, words, GermanyParser.SIMPLE)) {
            throw new InvalidPosition("Два числа еденичного формата.", words, 0);
        } else if (GermanyParser.checkBackRank(GermanyParser.ZIG, words, GermanyParser.ZIG)) {
            throw new InvalidPosition("После числа десятичного формата не может стоять число еденичного формата.", words, 0);
        }  else if (GermanyParser.checkBackRank(GermanyParser.ZEHN, words, GermanyParser.ZEHN)) {
            throw new InvalidPosition("После числа формата 11-19 не может стоять число еденичного формата.", words, 0);
        }  else if (GermanyParser.checkBackRank(GermanyParser.UND, words, GermanyParser.UND)) {
            throw new InvalidPosition("После числа с und не может стоять число еденичного формата.", words, 0);
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
