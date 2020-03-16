import java.util.UUID;

/**
 * @program: PACKAGE_NAME.MyTest1
 * @description:
 * @author: Mr.BULLET
 * @create: 2020-01-24 09:25
 */
public class MyTest4 {
    public static void main(String[] args) {
        //什么都不会输出,因为这个数组类型不是MyChild4,而是JVM生成的一个 [LMyChild4 类型的class对象
        //这个 [LMyChild4 类型的对象继承自Object类
        MyChild4[] my = new MyChild4[1];
    }
}

class MyChild4 {
    static{
        System.out.println("MyChild4");
    }
}
