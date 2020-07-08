
/**
 * @desc 接口
 */
interface Inter{
    public static final int NUM = 3;
    public abstract void show();

}
interface InterA{
    public abstract void show();
}
class Demo{
    public void func(){};
}

// 用extends可以进行类和类的继承
class Test extends Demo{

}

/**
 * @desc 用implements可以进行类对接口的实现，同时需要具体实现接口的抽象方法
 *      可以同时implements多个接口
 */
class Test2 implements Inter,InterA{
    public void show(){

    }
}

/**
 * @desc 对类的继承，对接口的实现，可以同时进行
 */
class Test3 extends Demo implements Inter,InterA{
    public void show(){

    }
}

