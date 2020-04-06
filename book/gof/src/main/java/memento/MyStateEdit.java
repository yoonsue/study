package memento;

import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.StateEditable;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

public class MyStateEdit extends AbstractUndoableEdit {
    protected StateEditable object;

    // Memento
    protected Hashtable<Object,Object> preState;
    protected Hashtable<Object,Object> postState;

    protected String undoRedoName;

    public MyStateEdit(StateEditable anObject) {
        super();
        init (anObject,null);
    }

    protected void init (StateEditable anObject, String name) {
        this.object = anObject;
        this.preState = new Hashtable<Object, Object>(11);
        this.object.storeState(this.preState);
        this.postState = null;
        this.undoRedoName = name;
    }

    public void end() {
        this.postState = new Hashtable<Object, Object>(11);
        this.object.storeState(this.postState);
        this.removeRedundantState();
    }

    public void undo() {
        super.undo();
        this.object.restoreState(preState);
    }

    public void redo() {
        super.redo();
        this.object.restoreState(postState);
    }

    public String getPresentationName() {
        return this.undoRedoName;
    }
    // -- End of narrow interface --

    protected void removeRedundantState() {
        Vector<Object> uselessKeys = new Vector<>();
        Enumeration<Object> myKeys = preState.keys();

        // Locate redundant state
        while (myKeys.hasMoreElements()) {
            Object myKey = myKeys.nextElement();
            if (postState.containsKey(myKey) &&
                    postState.get(myKey).equals(preState.get(myKey))) {
                uselessKeys.addElement(myKey);
            }
        }

        // Remove redundant state
        for (int i = uselessKeys.size()-1; i >= 0; i--) {
            Object myKey = uselessKeys.elementAt(i);
            preState.remove(myKey);
            postState.remove(myKey);
        }
    }
    // -- End of wide interface --
}
