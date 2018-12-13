package sample.NumberToOldRussian;

public class OldRussian {

    public static String getNumber(int num) {
        StringBuilder str = new StringBuilder();
        int i = 1;
        while (num != 0) {
            int k = num % 10;
            addChar(k, str, i);
            num /= 10;
            i *= 10;
        }

        return str.reverse().toString();
    }

    private static void addChar(int k, StringBuilder str, int deg) {
        char getCh = getChar(k * deg);
        if (getCh == 0) {
            return;
        }

        str.append(getCh);
    }

    private static char getChar(int i) {
        switch (i) {
            case 1:
                return 'А';
            case 2:
                return 'Б';
            case 3:
                return 'В';
            case 4:
                return 'Г';
            case 5:
                return 'Д';
            case 6:
                return 'Е';
            case 7:
                return 'Ж';
            case 8:
                return 'S';
            case 9:
                return 'З';
            case 10:
                return 'I';
            case 20:
                return 'И';
            case 30:
                return 'Ћ';
            case 40:
                return 'К';
            case 50:
                return 'Л';
            case 60:
                return 'М';
            case 70:
                return 'Н';
            case 80:
                return 'О';
            case 90:
                return 'П';
            case 100:
                return 'Р';
            case 200:
                return 'С';
            case 300:
                return 'Т';
            case 400:
                return 'У';
            case 500:
                return 'Ф';
            case 600:
                return 'Х';
            case 700:
                return 'Ѡ';
            case 800:
                return 'Щ';
            case 900:
                return 'Ц';
            default:
                return 0;

        }

    }

}
