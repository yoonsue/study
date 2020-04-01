package interpreter;

public class MyMatcher {

    MyPattern parentPattern;
    CharSequence text;

    int[] groups;

    int first = -1, last = 0;
    int oldLast = -1;
    int from, to;

    boolean hitEnd;
    boolean requireEnd;

    MyMatcher() {

    }

    MyMatcher(MyPattern parent, CharSequence text) {
       this.parentPattern = parent;
       this.text = text;

       int parentGroupCount = Math.max(parent.capturingGroupCount, 10);
       groups = new int[parentGroupCount * 2];
    }

    public MyPattern pattern() {
        return parentPattern;
    }

    public boolean find() {
        int nextSearchIndex = last;
        if (nextSearchIndex == first)
            nextSearchIndex++;

        // If next search starts before region, start it at region
        if (nextSearchIndex < from)
            nextSearchIndex = from;

        // If next search starts beyond region then it fails
        if (nextSearchIndex > to) {
            for (int i = 0; i < groups.length; i++)
                groups[i] = -1;
            return false;
        }
        return search(nextSearchIndex);
    }

    boolean search(int from) {
        this.hitEnd = false;
        this.requireEnd = false;
        from        = from < 0 ? 0 : from;
        this.first  = from;
        this.oldLast = oldLast < 0 ? from : oldLast;
        for (int i = 0; i < groups.length; i++)
            groups[i] = -1;
        boolean result = true; //parentPattern.root.match(this, from, text);
        if (!result)
            this.first = -1;
        this.oldLast = this.last;
        return result;
    }

    public String group() {
        return group(0);
    }

    public String group(int group) {
        return getSubSequence(groups[group * 2], groups[group * 2 + 1]).toString();
    }

    CharSequence getSubSequence(int beginIndex, int endIndex) {
        return text.subSequence(beginIndex, endIndex);
    }

}
