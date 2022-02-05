package dev.rachamon.yanamibot.utils;

import dev.rachamon.yanamibot.YanamiBot;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.serializer.TextSerializers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type Yanami bot util.
 */
public class YanamiBotUtil {
    /**
     * To text text.
     *
     * @param string the string
     * @return the text
     */
    public static Text toText(String string) {
        return TextSerializers.FORMATTING_CODE.deserialize(string);
    }

    public static String stripColor(char symbol, String str) {
        return str.replaceAll("(" + symbol + "([A-Fa-fK-Ok-oRr0-9]))", "");
    }

    public static String getLastColor(String str) {
        if (str.length() > 2) {
            Pattern pattern = Pattern.compile("(&([A-Fa-f0-9])(&([K-Ok-oRr]))?)", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(str);
            String s = "";
            while (matcher.find()) {
                s = matcher.group();
            }
            return s;
        }
        return "";
    }
}
