package sample.GermanyToNumber;

public class Ex extends Exception {

    private final boolean printIndex;

    private Words words;

    private int wordIndex;

    private String description;

    Ex(String message, Words words, int wordIndex) {
        super(message);
        this.words = words;
        this.wordIndex = words.getStart() + wordIndex;
        this.description = "Ошибка при разборе слова";
        this.printIndex = false;
    }

    public Ex(String message, Words words, int wordIndex, boolean b) {
        super(message);
        this.words = words;
        this.wordIndex = words.getStart() + wordIndex;
        this.description = "Ошибка при разборе слова";
        this.printIndex = b;
    }

    @Override
    public String toString() {
        if (!printIndex) {
            return getMessage();
        }

        String[] wrds = words.getWords(true);
        if (wordIndex >= wrds.length) {
            return "Ошибка после слова: " + wrds[wrds.length - 1];
        }
        return "Ошибка в слове: " + wrds[wordIndex];

        /*return description + "\n" +
                getMessage() + "\n" +
                "Слово: " + words.getBasicString() + "\n" +
                "Ошибка в слове: " + (words.getStart() + wordIndex) + "\n";*/
    }
}
