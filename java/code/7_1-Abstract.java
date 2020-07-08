

/**
 * @desc 抽象类，
 *      注意：有抽象方法的一定是抽象类
 */
abstract class Student{
    // 抽象类中的抽象方法，只有方法名，没有方法体
    abstract final void study();

    // 抽象类中可以有实际的成员函数，
    void sleep(){

    }
}

/**
 * @desc 类继承抽象类，对其内部的抽象方法进行重新实现
 */
class Managerr extends Student{

    public void study(){
        System.out.printIn("学习");
    }

}