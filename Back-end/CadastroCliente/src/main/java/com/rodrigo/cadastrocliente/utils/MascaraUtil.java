package com.rodrigo.cadastrocliente.utils;

import javax.swing.text.MaskFormatter;
import java.text.ParseException;

public class MascaraUtil {

    public static String formatString(String value, String pattern){
        MaskFormatter mf;
        try {
            mf = new MaskFormatter(pattern);
            mf.setValueContainsLiteralCharacters(false);
            return mf.valueToString(value);
        }catch (ParseException ex){
            return value;
        }
    }

}
