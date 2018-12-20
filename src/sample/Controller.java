package sample;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import sample.GermanyToNumber.Ex;
import sample.GermanyToNumber.GermanyParser;
import sample.NumberToOldRussian.OldRussian;

public class Controller {

    public Text rtn_text;
    public Button btn;
    public TextField field;

    public void click(ActionEvent actionEvent) {
        String germanyText = field.getText();
        int num = 0;
        String oldRussian = "";
        try {
            num = GermanyParser.getNumber(germanyText);
            oldRussian = OldRussian.getNumber(num);
        } catch (Ex ex) {
            rtn_text.setText(ex.toString());
            return;
        }

        rtn_text.setText("Нем: " + germanyText +
                "\nАраб: " + String.valueOf(num) +
                "\nСлав: " + oldRussian);
    }

    public void keyPress(KeyEvent keyEvent) {
        int pos = field.getCaretPosition();

        if (keyEvent.isAltDown() && keyEvent.getText().equals("u")) {
            field.setText(field.getText().substring(0, field.getCaretPosition())   + "ü"
                    + field.getText().substring(field.getCaretPosition()));
            field.positionCaret(pos + 1);

        } else if (keyEvent.isAltDown() && keyEvent.getText().equals("b")) {
            field.setText(field.getText().substring(0, field.getCaretPosition())   + "ß"
                    + field.getText().substring(field.getCaretPosition()));
            field.positionCaret(pos + 1);

        } else if (keyEvent.isAltDown() && keyEvent.getText().equals("o")) {
            field.setText(field.getText().substring(0, field.getCaretPosition())   + "ö"
                    + field.getText().substring(field.getCaretPosition()));
            field.positionCaret(pos + 1);
        }
    }
}
