package interpreter;

public class MyPattern implements java.io.Serializable {

    private String pattern;
    private int flags;

    transient Node root;

    static final Node accept = new Node();

    private transient volatile boolean compiled;

    transient int capturingGroupCount;

    public static MyPattern compile(String regex) {
        return new MyPattern(regex, 0);
    }

    public static MyPattern compile(String regex, int flags) {
        return new MyPattern(regex, flags);
    }

    private MyPattern(String p, int f) {
        if (f != 0) {
            throw new IllegalArgumentException("Unknown flag 0x" + Integer.toHexString(f));
        }
        pattern = p;
        flags = f;
    }

    public String pattern() {
        return pattern;
    }

    public void compile() {
        System.out.println("compiling...");
    }

    public MyMatcher matcher(CharSequence input){
        if (!compiled) {
            synchronized (this) {
                if (!compiled)
                    compile();
            }
        }
        MyMatcher m = new MyMatcher(this, input);
        return m;
    }

    static class Node extends Object {
        Node next;
        Node() {
            next = MyPattern.accept;
        }

        boolean match(MyMatcher matcher, int i, CharSequence seq) {
            matcher.last = i;
            matcher.groups[0] = matcher.first;
            matcher.groups[1] = matcher.last;
            return true;
        }
    }
}
