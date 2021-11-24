import org.junit.Test;

import java.util.List;

/**
 * @Author: siyan.liu
 * @Date: 2021/10/25
 */
public class LDemo {



    /**
     * 接口
     */
    interface MathOperate<T> {
        T operate(int a, int b);
    };

    /**
     * 调用接口方法
     * @param a
     * @param b
     * @param operate
     * @param <T>
     * @return
     */
    private <T>T mOperate(int a,int b,MathOperate<T> operate) {
        return operate.operate(a,b);
    }


    @Test
    public void LTest() {

        MathOperate<Integer> addOperate = (a,b) -> a+b;
        MathOperate<Integer> subOperate = (a,b)-> a-b;

        System.out.println(mOperate(1,2,addOperate));
        System.out.println(mOperate(10,8,subOperate));

    }

//    ----------------------------泛型----------------------------------

    /**
     * <T>
     *     泛型的作用：类似Object，但是Object需要强制转换成类，使用泛型不需要强制转换
     */

    //泛型使用

    /**
     * 1、泛型类
     */
    class wideDemo<T> {
        T field;

        /**
         * 2、泛型方法
         * 注意：该方法的<T>和类名后的<T>表示的不是同一个类，如果需要表示同一个类，方法前面的<T>应该去掉.否则最好改成别的字母表示
         * @param param
         * @param <T>
         * @return
         */
        <T> T demoMethod(T param) {
//            return field; 不是一个类型 会报错
            return param;
        }
    }

    /**
     * 3、泛型接口
     */
    interface  wideDemoInter<T>{}


    /**
     * <?> 通配符
     * 作用：用于指定泛型限定范围的，
     * 如有上限通配符<? extends T>、有下限通配符<? super T>、无限定通配符<?>（应该在对类型不做操作的情况下使用）
     */

    /**
     * <?>
     *     意义： 提高了代码的可读性，程序员看到这段代码时，就能够迅速对此建立极简洁的印象，能够快速推断源码作者的意图
     */

    private int getSize (List<?> list) {
//        list.add(11); 不可以做操作
        return list.size();
    }


    /**
     * <? extends T>、<? super T>
     */

    class Base{}

    class Sub extends Base{}

    public List<?> uniExtendsTest(List<? extends Base> list){
        //不具备写的能力
//        list.add(new Sub()); 编译不通过
//        list.add(new Base()); 编译不通过
        return list;
    }

    public List<?> uniSuperTest(List<? super Sub> list) {
        list.add(new Sub());
//        list.add(new Base()); 编译不通过
        return list;
    }


    /**
     * 类型擦除：泛型是jdk1。5之后引入的概念， 泛型在编译期间生效，但是在jvm中是不生效的 按照Object来处理的
     * 但是限定了类型上限则会按照上限类型处理
     * 因此，在使用反射调用方法时如果入参为T，则应该传入Object或者上限类型才会找到该方法
     * 相应带来的局限性是，由于泛型在编译期生效，所以在限定范围比如List<Integer> 向list中add元素，虽然在编译器上只能add Integer类型的值，
     * 但是使用反射是可以加其他类型的元素的，因为jvm会将T识别成Object
     */


    /**
     * 注意： 泛型只可以接受Object 不接受int等基本类型
     */





}
