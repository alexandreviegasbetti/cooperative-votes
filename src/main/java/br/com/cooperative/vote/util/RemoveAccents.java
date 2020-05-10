package br.com.cooperative.vote.util;

import java.text.Normalizer;

public class RemoveAccents {

    public static String removeAccents(String str) {
        str = Normalizer.normalize(str, Normalizer.Form.NFD);
        str = str.replaceAll("[^\\p{ASCII}]", "");
        return str;
    }
}
