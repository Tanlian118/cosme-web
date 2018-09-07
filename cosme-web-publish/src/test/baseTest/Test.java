package baseTest;

/**
 * @author Tanlian
 * @create 2018-09-05 23:01
 **/
public class Test
{
    static boolean foo(char c)
    {
        System.out.print(c);
        return true;
    }
    public static void main( String[] argv )
    {
        int i = 0;

//        for(条件1;条件2;条件3) {
//            语句
//        }
//        执行顺序是条件1->条件2->语句->条件3->条件2->语句->条件3->条件2........
//        如果条件2为true，则一直执行。如果条件2位false，则for循环结束

        for ( foo('A'); foo('B') && (i < 2); foo('C')) {
              i++ ;
            foo('D');
        }


        System.out.println("is"+ 100 + 5);
        System.out.println(100 + 5 +"is");
        System.out.println("is"+ (100 + 5));

        int k = 5;
        int j = 10;
        System.out.println(k + ~j);
    }
}