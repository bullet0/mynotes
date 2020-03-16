/**
 * @program: PACKAGE_NAME.MyTest1
 * @description:
 * @author: Mr.BULLET
 * @create: 2020-01-24 09:25
 */
public class MyTest2 {
    public static void main(String[] args) {
        //只输出
        //HW-str2
        //表示并没有主动使用子类,原因是因为被final定义的变量为常量,常量在编译期间会存入到调用类的常量池中,所以下面
        //的代码相当于调用MyTest2.str2,跟MyChild已经没有关系了
        System.out.println(MyChild.str2);
    }
}
class MyChild {
    public static final String str2 = "HW-str2";
    static{
        System.out.println("MyChild1");
    }
}
