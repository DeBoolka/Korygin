package sample.NumberToOldRussian;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OldRussianTest {

    @Test
    void getNumber() {
        assertEquals(OldRussian.getNumber(222), "СИБ");
        assertEquals(OldRussian.getNumber(319), "ТIЗ");
    }
}