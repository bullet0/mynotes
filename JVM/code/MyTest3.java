import java.util.UUID;

/**
 * @program: PACKAGE_NAME.MyTest1
 * @description:
 * @author: Mr.BULLET
 * @create: 2020-01-24 09:25
 */
public class MyTest3 {
    public static void main(String[] args) {
        //只输出
        //MyChild3
        //1a900915-6cd9-45ef-897f-9451c4d55bde
        //表示主动使用子类,原因是因为被final定义的变量虽为常量,但常量的值无法在在编译期间推测出来,只能在运行时确定,
        //所以会先初始化MyChild3
        System.out.println(MyChild3.str2);
    }
}

class MyChild3 {
    public static final String str2 = UUID.randomUUID().toString();
    static{
        System.out.println("MyChild3");
    }
}
