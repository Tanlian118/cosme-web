package reflectionTest;

/**
 * @author Tanlian
 * @create 2018-08-26 16:40
 **/
public class ReflectionTest {

    public static void main(String[] args) throws Exception {
        Class<?> c = Class.forName("reflectionTest.Reflection");
        Reflection[] rs = new Reflection[2];

        // 获取java.lang.Class的Class对象
        System.out.println("1.Class.getClass()：" + c.getClass());
        // 获取类的加载器
        System.out.println("2.Class.getClassLoader()：" + c.getClassLoader());
        // 获取父类Class对象
        System.out.println("3.Class.getSuperclass()：" + c.getSuperclass());
        // 获取类的接口列表，注意返回的是一个数组
        System.out.println("4.Class.getInterfaces()：" + c.getInterfaces()[0] + ", " + c.getInterfaces()[1]);
        // 获取该数组的Class对象
        System.out.println("5.Class.getComponentType()：" + rs.getClass().getComponentType());
        // 根据Class实例化出一个类实例来,默认调用无参构造方法
        Reflection r = (Reflection) c.newInstance();
        System.out.println("6.Class.newInstance()：" + r);
    }
}
