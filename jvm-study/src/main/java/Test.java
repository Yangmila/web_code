public class Test {

    private static class P{
        protected int x = 3;

        public P(){
            System.out.println(x);//1
            s();
            System.out.println(x);//2
        }
        protected void s(){
            x = 4;
        }
    }

    //字节码层面上，对象的构造方法为<init>字节码指令，收集成员变量，实例代码块，构造方法
    private static class C extends P{
        protected int x = 1;

        public C(){
//            super();//字节码层面，隐的父类构造方法调用
            System.out.println(x);//3
        }
        protected void s(){
            x = 6;
        }
    }

    public static void main(String[] args) {
        C c = new C();
        System.out.println(c.x);//4
    }
}


