package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
}
