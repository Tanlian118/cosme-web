package baseTest;

/**
 * @author Tanlian
 * @create 2018-09-05 22:40
 **/
public class PracticeTest {

    public String str = "6";

    public static void main(String[] args) {

        PracticeTest sv = new PracticeTest();

        sv.change(sv.str); //6
        System.out.println(sv.str); //6
    }

    public void change(String str) {
        str = "10";
    }
}
