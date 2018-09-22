package java8;

import org.junit.Test;

/**
 * @author Tanlian
 * @create 2018-09-22 12:53
 **/
public class ConverterTest {

    @Test
    public void test() {
        Converter<String, Integer> converter = (from) -> Integer.valueOf(from);
        Integer converted = converter.convert("123");
        System.out.println(converted);
    }
    }