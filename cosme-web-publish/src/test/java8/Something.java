package java8;

import org.junit.Test;

/**
 * @author Tanlian
 * @create 2018-09-22 13:39
 **/
public class Something {

    String startswith(String s) {
        return String.valueOf(s.charAt(0));
    }

    @Test
    public void test1() {
        Something something = new Something();
        Converter<String, String> converter = something::startswith;
        String convert = converter.convert("hhhh");
        System.out.println(convert);
    }
}
