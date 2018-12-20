package sample.GermanyToNumber;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class GermanyParserTest {

    @Test
    void getNumber() {
        ScriptEngineManager factory = new ScriptEngineManager();
        // create a Nashorn script engine
        ScriptEngine engine = factory.getEngineByName("nashorn");
        // evaluate JavaScript statement
        int k = 0;
        String nem = "";
        try {
            engine.eval(getStrNum());
            Invocable invocable = (Invocable) engine;

            for (int i = 1; i < 1000; i++) {
                k = i;
                Object result = invocable.invokeFunction("m", String.valueOf(i));
                nem = result.toString();
//                String res = split(result.toString());
                String repRes = split(result.toString())
                        .replace('ß', 'b')
                        .replace('ü', 'u')
                        .replace('ö', 'o');

                int getNum = GermanyParser.getNumber(repRes);
                assertEquals(i, getNum,  "Результаты отличаются.\n" +
                        "i: " + i + "\n" +
                        "getNum: " + getNum + "\n" +
                        "Str: " + repRes + "\n" +
                        "Base str: " + result.toString());

//                System.out.println(split("OK: " + i));
            }
        } catch (final Exception se) {
            se.printStackTrace();
            fail("Тест выбросил исключение на числе c номером " + k + " нем: " + nem);
        }
    }

    private String split(String word) {
        StringBuilder str = new StringBuilder();
        StringBuilder basicStr = new StringBuilder(word.trim().toLowerCase());

        while (basicStr.length() > 0) {
            int iZero = 0;
            int iH = basicStr.indexOf("hundert");
            iZero = iH != 0 ? iZero : "hundert".length();
            int iU = basicStr.indexOf("und");
            iZero = iU != 0 ? iZero : "und".length();
//            int iZ = basicStr.indexOf("zig");
//            iZero = iZ != 0 ? iZero : "zig".length();
//            int iB = basicStr.indexOf("ßig");
//            iZero = iB != 0 ? iZero : "zig".length();
//            int iZh = basicStr.indexOf("zehn");
//            iZero = iZh != 0 ? iZero : "zehn".length();
            int min = Math.min(iH < 0 ? 99999 : iH, iU < 0 ? 99999 : iU);
//            min = Math.min(min, iZ < 0 ? 99999 : iZ);
//            min = Math.min(min, iB < 0 ? 99999 : iB);
//            min = Math.min(min, iZh < 0 ? 99999 : iZh);
            if (min == 99999) {
                min = basicStr.length();
            }

            str.append(basicStr.substring(0, min)).append(" ");
            basicStr.delete(0, min);
            if (iZero != 0) {
                str.append(basicStr.substring(0, iZero)).append(" ");
                basicStr.delete(0, iZero);
            }
        }

        return str.toString();
    }

    String getStrNum() {
        return "var j = {\n" +
                "        \"0\": \"\",\n" +
                "        1: \"eins\",\n" +
                "        2: \"zwei\",\n" +
                "        3: \"drei\",\n" +
                "        4: \"vier\",\n" +
                "        5: \"fünf\",\n" +
                "        6: \"sechs\",\n" +
                "        7: \"sieben\",\n" +
                "        8: \"acht\",\n" +
                "        9: \"neun\",\n" +
                "        10: \"zehn\",\n" +
                "        11: \"elf\",\n" +
                "        12: \"zwölf\",\n" +
                "        13: \"dreizehn\",\n" +
                "        14: \"vierzehn\",\n" +
                "        15: \"fünfzehn\",\n" +
                "        16: \"sechzehn\",\n" +
                "        17: \"siebzehn\",\n" +
                "        18: \"achtzehn\",\n" +
                "        19: \"neunzehn\",\n" +
                "        20: \"zwanzig\",\n" +
                "        30: \"dreißig\",\n" +
                "        40: \"vierzig\",\n" +
                "        50: \"fünfzig\",\n" +
                "        60: \"sechzig\",\n" +
                "        70: \"siebzig\",\n" +
                "        80: \"achtzig\",\n" +
                "        90: \"neunzig\"\n" +
                "    },\n" +
                "    k = {\n" +
                "        \"0\": \"\",\n" +
                "        1: \"tausend\",\n" +
                "        2: \"Million\",\n" +
                "        3: \"Milliarde\",\n" +
                "        4: \"Billion\",\n" +
                "        5: \"Billiarde\",\n" +
                "        6: \"Trillion\",\n" +
                "        7: \"Trilliarde\"\n" +
                "    };\n" +
                "\n" +
                "function l(b, a, e, c) {\n" +
                "    if (\"\" == b) tensAsWort = \"\";\n" +
                "    else if (onesAsWort = j[b], 0 == a) 1 < c ? \"eins\" == onesAsWort && (onesAsWort = \"eine \") : 1 == c && \"eins\" == onesAsWort && (onesAsWort = \"ein\"), tensAsWort = onesAsWort;\n" +
                "    else if (1 == a) tensAsWort = j[a + b];\n" +
                "    else {\n" +
                "        if (\"eins\" == onesAsWort || \"eine\" == onesAsWort) onesAsWort = \"ein\";\n" +
                "        tensAsWort = j[10 * a];\n" +
                "        \"\" != onesAsWort && (tensAsWort = onesAsWort + \"und\" + tensAsWort)\n" +
                "    }\n" +
                "    \"\" == e ? hundredsAsWort = \"\" : (hundredsAsWort = j[e], \"eins\" == hundredsAsWort && (hundredsAsWort = \"ein\"), \"\" != hundredsAsWort && (hundredsAsWort += \"hundert\"));\n" +
                "    zahlwort = hundredsAsWort + tensAsWort;\n" +
                "    if (\"\" != zahlwort) {\n" +
                "        cntAsWort = k[c];\n" +
                "        if (1 < c) {\n" +
                "            if (1 != b || 0 != a) \"e\" != cntAsWort[cntAsWort.length - 1] && (cntAsWort += \"e\"), cntAsWort = \" \" + cntAsWort + \"n\";\n" +
                "            cntAsWort += \" \"\n" +
                "        }\n" +
                "        zahlwort += cntAsWort\n" +
                "    }\n" +
                "    return zahlwort\n" +
                "}\n" +
                "\n" +
                "function m(num) {\n" +
                "    for (var b = {value: num}, a = b.value.length, e = \"\", c = 0, f = \"\", g = \"\", h = \"\", d = 1; d <= a; d++)\n" +
                "        if (1 == d % 3) {\n" +
                "            if (f = b.value[a - d], !1 == (0 <= f && 9 >= f) || \" \" == f) return \"\"\n" +
                "        } else if (2 == d % 3) {\n" +
                "        if (g = b.value[a - d], !1 == (0 <= g && 9 >= g) || \" \" == g) return \"\"\n" +
                "    } else if (0 == d % 3) {\n" +
                "        h = b.value[a - d];\n" +
                "        if (!1 == (0 <= h && 9 >= h) || \" \" == h) return \"\";\n" +
                "        e = l(f, g, h, c) + e;\n" +
                "        h = g = f = \"\";\n" +
                "        c++\n" +
                "    }\n" +
                "    return e = l(f, g, h, c) + e\n" +
                "}";
    }
}