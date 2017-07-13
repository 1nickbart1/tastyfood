package by.tastyfood.utils;

/**
 * Created by Nikolay on 03.06.2017.
 */
public class StringUtils {

    public static String findValueInString(String stringForParsing, String key){
        if(stringForParsing == null || key == null){
            return null;
        }

        for (String keyInString : stringForParsing.split(";")) {
            if (keyInString.trim().startsWith(key)) {
                return keyInString.substring(keyInString.indexOf('=') + 1).trim()
                        .replace("\"", "");
            }
        }
        return null;
    }

    public static String transliterate(String sourceString){
        StringBuilder sb = new StringBuilder(sourceString.length());
        for (int i = 0; i<sourceString.length(); i++) {
            String l = sourceString.substring(i, i+1);
            if (TranalitLettersContainer.getLetters().containsKey(l)) {
                sb.append(TranalitLettersContainer.getLetters().get(l));
            }
            else {
                sb.append(l);
            }
        }

         return sb.toString();
    }

    public  boolean isEmpty(String string){
        if(string == null){
            return true;
        }
        if("".equals(string.trim())){
            return true;
        }

        return false;
    }

}
