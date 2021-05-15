package com.linear.B_Spec;

/*
    多线程龟兔赛跑问题，乌龟和兔子比赛跑200米的距离，
    名叫兔子的线程每执行一次兔子就向前跑5米，每跑50米休息10毫秒，
    名叫乌龟的线程每执行一次乌龟就向前跑1米，乌龟会一直跑不休息
    用多线程实现求出最后的胜利者是谁

*/
class _Race implements Runnable {
    //winner:只有一个胜利者
    private static String winner;
    int lengthr = 200;//lengthr为兔子需要跑的长度
    int lengtht = 200;//lengtht为乌龟需要跑的长度

    // 重写run方法，编写奔跑操作
    public void run() {
        while (true) {
            String tname = Thread.currentThread().getName(); // 线程名

            //判断线程的名字是兔子
            if (tname.equals("兔子")) {
                //兔子以5米的速度跑，
                //线程每运行一次，就打印"XXX距离终点还有YYY米(XXX为线程名字，YYY为剩余的距离)"，
                //每跑50米休息10毫秒（线程休眠10毫秒），当剩余距离为0米时不再休息，请补全此处代码：
                int disr = 5;

                // __________________①__________________

                lengthr -= disr;
                System.out.println(tname + " 距离终点还有 "+lengthr+" 米");

                if(lengthr % 50 == 0 && lengthr > 0){
                    try {
                        Thread.sleep(10); // 每跑50米休息10ms
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                //调用gameOver方法判断比赛是否结束
                boolean flag = gameOver(lengthr);
                if (flag) {
                    break;
                }
            }
            //判断线程的名字是乌龟
            if (tname.equals("乌龟")) {
                //乌龟以1米的速度，会一直跑不休息，
                //线程每运行一次，就打印"XXX距离终点还有YYY米(XXX为线程名字，YYY为剩余的距离)"，请补全此处代码：
                int dist = 1;

                // __________________②__________________
                lengtht -= dist;
                System.out.println(tname + " 距离终点还有 "+lengtht+" 米");

                //调用gameOver方法判断比赛是否结束
                boolean flag = gameOver(lengtht);
                if (flag) {
                    break;
                }
            }
        }
    }
    //判断比赛是否结束
    private boolean gameOver(int lenght) {
        if (winner != null) { //如果存在胜利者
            return true;
        }
        if ( lenght == 0) {  //判断如果跑到了比赛结束
            winner = Thread.currentThread().getName();
            System.out.println("比赛结束");
            System.out.println("胜利者----->" + winner);
            return true;
        }
        return false;
    }
}


public class B01_Race {

    public static void main(String[] args) {
        _Race race = new _Race(); //创建一个比赛
        //要求创建两个线程名字分别为：兔子、乌龟。并启动线程，请补全此处代码：

        // __________________③__________________
        Thread t = new Thread(race);
        t.setName("乌龟");

        Thread r = new Thread(race);
        r.setName("兔子");

        t.start();
        r.start();
    }
}
