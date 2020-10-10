import java.util.List;

class View{
    public String name;
    public String id;
    public int width;
    public int height;

    public View(String name,String id,int width,int height){
        this.name = name;
        this.id = id;
        this.width = width;
        this.height = height;
    }

    public String getName(){
        return name;
    }
    // ...其他操作
}

class ViewGroup extends View {
    // ViewGroup的子节点可以是View，
    //      而ViewGroup是View的子类，所以子节点也可以是ViewGroup
    public List<View> children = null;

    public String groupName;

    // ...其他属性

    public void addView(View view){
        this.children.add(view);
    }

    // 根据继承判断是否是ViewGroup类型
    public boolean isViewGroup(View view){
        return view instanceof ViewGroup;
    }

    // ...其他相关操作
}


class TextView extends View{
    // text view 的一些操作
}

class SurfaceView extends View{
    // surface view 的一些操作
}

class TestViewGroup{
    public static void main(String[] args){
        View A = new View();

        ViewGroup B = new ViewGroup();
    }
}
