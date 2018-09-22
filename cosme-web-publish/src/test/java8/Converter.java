package java8;

/**
 * @author Tanlian
 * @create 2018-09-22 12:51
 **/
@FunctionalInterface
public interface Converter<S,T> {
    T convert(S from);
}

