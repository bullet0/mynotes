/**
 * @program: PACKAGE_NAME.MyTest1
 * @description:
 * @author: Mr.BULLET
 * @create: 2020-01-24 09:25
 */
public class MyTest6 {
    public static void main(String[] args) {
        //最后结果是
        //1
        //1
        //1
        //0
        //表示初始化过程时初始化顺序为从上到下
        Singleton singleton = Singleton.getSingleton();
        System.out.println(singleton.c1);
        System.out.println(singleton.c2);
    }
}

class Singleton{
    public static int c1;
    private static Singleton singleton = new Singleton();
    private Singleton(){
        c1++;
        c2++;
        System.out.println(c1); //1
        System.out.println(c2); //1
    }
    //构造器会将c2自增为1,但这里会覆盖为0
    public static int c2 = 0;

    public static Singleton getSingleton(){
        return singleton;
    }
}