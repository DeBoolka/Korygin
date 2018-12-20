package Les2;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import sample.GermanyToNumber.Ex;
import sample.GermanyToNumber.GermanyParser;
import sample.NumberToOldRussian.OldRussian;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Controller {
    public TextField field;
    public TextField ffrom;
    public TextField fto;
    public TextField reply;
    public Button btn;

    public void click(ActionEvent actionEvent) {
        List<String> words = new ArrayList<>(List.of(field.getText().trim().split("\\s+")));

        int from;
        int to;
        try {
            from = Integer.valueOf(ffrom.getText()) - 1;
            to = Integer.valueOf(fto.getText()) - 1;
        } catch (NumberFormatException e) {
            sendMessage("Точки интервала не верного формата.");
            return;
        }

        if (from < -1 || to < -1) {
            sendMessage("Точки в интервале не могут быть меньше 0");
            return;
        } else if (to - from < 0) {
            sendMessage("Конечная точка интервала больше начальной");
            return;
        } else if (from >= words.size() || to >= words.size()) {
            sendMessage("Точка интервала превышает количество слов");
            return;
        } else if (from == -1 || to == -1) {
            sendMessage("Порядок слов в предложении начинается с 1");
            return;
        }

        List<String> replyWords = new ArrayList<>();
        replyWords.addAll(words.subList(from, to + 1));
        replyWords.addAll(words.subList(0, from));
        replyWords.addAll(words.subList(to + 1, words.size()));

        reply.setText(String.join(" ",  replyWords));
    }

    void sendMessage(String text) {
        reply.setText(text);
    }
}
