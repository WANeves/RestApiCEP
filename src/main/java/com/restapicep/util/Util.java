package com.restapicep.util;

import javax.swing.text.MaskFormatter;
import java.text.ParseException;

public class Util {

    public static String format(String pattern, Object value) {
        MaskFormatter mask;
        try {
            mask = new MaskFormatter(pattern);
            mask.setValueContainsLiteralCharacters(false);
            return mask.valueToString(value);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }


    public static boolean isValidCep(String cep) {
        if (cep.length() == 8) {
            if (isMaskValidCep(Util.format("#####-###", cep))) {
                return true;
            }
        }
        return false;
    }


    public static boolean isMaskValidCep(String cep) {
        String pattern = "^\\d{5}-\\d{3}$";
        return cep.matches(pattern);
    }
}
