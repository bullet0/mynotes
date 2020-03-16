/**
 * @program: PACKAGE_NAME.MyTest1
 * @description:
 * @author: Mr.BULLET
 * @create: 2020-01-24 09:25
 */
public class MyTest5 {
    public static void main(String[] args) {
        //主动使用子接口,并不会去先初始化父接口,所以这里输出2,但是不会初始化MyParent5
        System.out.println(MyChild5.b);
    }
}

interface MyParent5 {
    //接口本身属性就是 public static final ,可以不写
    //如果父接口被实例化,则这里一定会初始化Thread对象,并输出MyParent5
    public static final Thread a = new Thread(){
        {
            System.out.println("MyParent5");
        }
    };

}
interface MyChild5 extends MyParent5{
    public static final int b = 2;
}
