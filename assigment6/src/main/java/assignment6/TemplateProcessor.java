package assignment6;

import java.util.Map;

public class TemplateProcessor {

    public static String process(String template, Customer customer) {
        String result = template;

        Map<String, String> data = customer.getData();

        for (String key : data.keySet()) {
            String placeholder = "[[" + key + "]]";
            String value = data.get(key);

            if (value == null) {
                value = "";
            }

            result = result.replace(placeholder, value);
        }

        return result;
    }
}