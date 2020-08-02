
/**
 * @desc 进程间通讯
 */
class Res{
    private String name;
    private String sex;
    private boolean flag = false;
    public synchronized void set(String name, String sex){
        if(flag){
            try{
                // 用wait来阻塞当前进程。
                this.wait();
            }catch(Exception e){}
        }
        this.name = name;
        this.sex = sex;

        flag = true;

        // 用notify唤醒
        this.notify();
    }
    public synchronized void out(){
        if(!flag){
            try{
                // 用wait来阻塞当前进程。
                this.wait();
            }catch(Exception e){}
        }

        System.out.println(name + "——" + sex);
        flag = false;

        this.notify();
    }
}

// input类，作用就是设置name和sex
class Input implements Runnable{
    private Res r;
    Input(Res r){
        this.r = r;
    }
    public void run(){
        int x = 0;
        while(true){
            if(x==0){
                r.set("mike","man");
            }else{
                r.set("lili","woman");
            }
            x = (x+1) % 2
        }
    }
}

/**
 * Output，作用就是打印name和sex，
 * 按理来讲，
 * mike应该是跟man一起打印，
 * lili和woman一起打印。
 */
class Output implements Runnable{
    private Res r;
    Output(Res r){
        this.r = r;
    }
    public void run(){
        while(true){
            r.out();
        }
    }
}

class InputOutputDemo{
    public static void main(String[] args){
        Res r = new Res();

        new Thread(new Input(r)).start();
        new Thread(new Output(r)).start();
    }
}