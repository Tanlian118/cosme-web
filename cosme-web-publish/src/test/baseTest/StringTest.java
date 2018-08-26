package baseTest;

import org.junit.Test;

/**
 * @author Tanlian
 * @create 2018-08-26 16:04
 **/
public class StringTest {

//    编译器每次碰到"+"的时候，会new一个StringBuilder出来，接着调用append方法，在调用toString方法，生成新字符串。
//    对内存是一种浪费，效率很不好。
    @Test
    public void testStringPlus() {
        String str = "111";
        str = str + "222";
        str += "333";
        System.out.println(str);


//       1.7后全局字符串常量池保存的只是引用了，真正的对象还是在堆中。
//       String xxxx = "Hello";
//      "Hello"对应的String类型的对象保存在堆中，字符串常量池保留该对象的引用。

//   "aaa"并不是对象，它叫做字符串常量，是在编译期间就能确定且放入常量池里。
//    "aaa"是常量，但是它本质上也是一个字符串，当然可以使用字符串的各个方法。
        String a ="aaaa";
        String b = "bbb";
        a.equals(b);
    }




//    StringBuffer和StringBuilder原理一样，无非是在底层维护了一个char数组，每次append的时候就往char数组里面放字符而已，
// 在最终sb.toString()的时候，用一个new String()方法把char数组里面的内容都转成String，
// 这样，整个过程中只产生了一个StringBuilder对象与一个String对象，非常节省空间。
// StringBuilder唯一的性能损耗点在于char数组不够的时候需要进行扩容，扩容需要进行数组拷贝，一定程度上降低了效率。
//  StringBuffer和StringBuilder用法一模一样，
// 唯一的区别只是StringBuffer是线程安全的，它对所有方法都做了同步，
// StringBuilder是线程非安全的，所以在不涉及线程安全的场景，比如方法内部，尽量使用StringBuilder，避免同步带来的消耗。
// 另外，StringBuffer和StringBuilder还有一个优化点，
// 上面说了，扩容的时候有性能上的损耗，那么如果可以估计到要拼接的字符串的长度的话，尽量利用构造函数指定他们的长度。

    @Test
    public void stringBuilder() {
        StringBuilder sb = new StringBuilder("111");
        sb.append("222");
        sb.append("111");
        sb.append("111");
        sb.append("444");
        System.out.println(sb.toString());
    }

}