/**
 * @program: PACKAGE_NAME.MyTest1
 * @description:
 * @author: Mr.BULLET
 * @create: 2020-01-24 09:25
 */
public class MyTest1 {
    public static void main(String[] args) {
        //输出
        //MyParent1
        //HW
        //表示使用子类访问父类的静态变量时,并不会主动使用子类,所以子类没有初始化,而父类被初始化了
//        System.out.println(MyChild1.str);
        //注释掉上面代码,执行下面代码
        //输出
        //MyParent1
        //MyChild1
        //HW-str2
        //表示主动使用子类的静态变量时,会主动使用子类,便会初始化子类,而子类初始化需要其父类全部初始化
        System.out.println(MyChild1.str2);

    }
}
class MyParent1{
    public static String str = "HW-str1";
    static{
        System.out.println("MyParent1");
    }
}
class MyChild1 extends MyParent1{
    public static String str2 = "HW-str2";
    static{
        System.out.println("MyChild1");
    }
}
