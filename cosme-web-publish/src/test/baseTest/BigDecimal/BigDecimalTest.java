package baseTest.BigDecimal;

import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author Tanlian
 * @create 2018-09-06 23:24
 **/
public class BigDecimalTest {

    /**
     * BigDecimal支持任意精度，任意长度的浮点数运算，但在运算的时候最好设置各个操作数的小数精确度，特别是除法。
     */

//
//      BigDecimal.setScale();//用于格式化小数点
//       setScale(1);//表示保留一位小数，默认用四舍五入方式
//       setScale(1,BigDecimal.ROUND_DOWN);//直接删除多余的小数位，如2.35会变成2.3
//       setScale(1,BigDecimal.ROUND_UP);//进位处理，2.35变成2.4
//       setScale(1,BigDecimal.ROUND_HALF_UP);//四舍五入，2.35变成2.4
//       setScaler(1,BigDecimal.ROUND_HALF_DOWN);//四舍五入，2.35变成2.3，如果是5则向下舍
    @Test
    public void test() {
        String a = "12.3422342244131311233";
        String b = "20.3612";
        String c = "20.3612";
        BigDecimal da = new BigDecimal(a);
        BigDecimal db = new BigDecimal(b);
        BigDecimal dc = new BigDecimal(c);
//      加运算
        BigDecimal add = da.add(da);
        System.out.println("add:" + add);              //add:24.684
//      减运算
        BigDecimal subtract = da.subtract(db);
        System.out.println("substract:" + subtract);   //substract:-8.0192
//      乘运算
        BigDecimal multiply = da.multiply(db);
        System.out.println("multiply:" + multiply);   //multiply:251.2979304
//      除运算;    3是保留小数，ROUND_HALF_UP是四舍五入
        BigDecimal divide = da.divide(db, 3, BigDecimal.ROUND_HALF_UP);
        System.out.println("divide:" + divide);      //divide:0.606

//        abs()取绝对值
        BigDecimal abs = subtract.abs();
        BigDecimal subtract1 = abs.subtract(subtract);
        System.out.println(subtract1);
//      比较两个值，如果相等返回0 不等返回-1
        int i = db.compareTo(dc);
        System.out.println(i);
//       bigdecimal转为double
        double v = da.doubleValue();
        System.out.println(v);
//比较两个值
        BigDecimal max = da.max(db);
        System.out.println(max);
    }
}
