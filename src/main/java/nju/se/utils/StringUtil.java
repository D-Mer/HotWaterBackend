package nju.se.utils;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author jh
 * @date 2020/3/3
 */
public class StringUtil {

    private static final Map<Character, Character> ACCENTS = new TreeMap<>();
    static {
            ACCENTS.put('Á', 'A');
            ACCENTS.put('À', 'A');
            ACCENTS.put('Â', 'A');
            ACCENTS.put('á', 'a');
            ACCENTS.put('à', 'a');
            ACCENTS.put('â', 'a');
            ACCENTS.put('ä', 'a');
            ACCENTS.put('ą', 'a');
            ACCENTS.put('ß', 'B');
            ACCENTS.put('ç', 'c');
            ACCENTS.put('ć', 'c');
            ACCENTS.put('È', 'E');
            ACCENTS.put('É', 'E');
            ACCENTS.put('Ê', 'E');
            ACCENTS.put('Ë', 'E');
            ACCENTS.put('è', 'e');
            ACCENTS.put('é', 'e');
            ACCENTS.put('ê', 'e');
            ACCENTS.put('ë', 'e');
            ACCENTS.put('Î', 'I');
            ACCENTS.put('Ï', 'I');
            ACCENTS.put('î', 'i');
            ACCENTS.put('ï', 'i');
            ACCENTS.put('⊘', 'o');
            ACCENTS.put('Ô', 'O');
            ACCENTS.put('Ö', 'O');
            ACCENTS.put('ø', 'o');
            ACCENTS.put('ó', 'o');
            ACCENTS.put('ô', 'o');
            ACCENTS.put('ö', 'o');
            ACCENTS.put('õ', 'o');
            ACCENTS.put('ò', 'o');
            ACCENTS.put('Û', 'U');
            ACCENTS.put('Ù', 'U');
            ACCENTS.put('ü', 'u');
            ACCENTS.put('û', 'u');
            ACCENTS.put('ù', 'u');
    }

    public static String toUUID(Integer id, String type) {
        return type + "-" + id;
    }

    public static String stripAccents(String raw) {
        if (raw == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder(raw);
        for (int i = 0; i < raw.length(); i++) {
            char c = raw.charAt(i);
            if (ACCENTS.containsKey(c)) {
                sb.setCharAt(i, ACCENTS.get(c));
            }
        }
        return sb.toString();
    }


}
