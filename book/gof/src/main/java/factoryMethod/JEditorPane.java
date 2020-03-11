package factoryMethod;

import javax.swing.*;
import javax.swing.plaf.basic.BasicEditorPaneUI;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.EditorKit;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

// Creator
public class JEditorPane extends JTextComponent {
    public JEditorPane() {
        super();
    }
    public void setText(String t) {
        try {
            Document doc = getDocument();
            doc.remove(0, doc.getLength());
            if (t == null || t.equals("")) {
                return ;
            }
            Reader r = new StringReader(t);
            EditorKit kit = getEditorKit();
            kit.read(r, doc, 0);
        } catch (IOException ioe) {
            UIManager.getLookAndFeel().provideErrorFeedback(JEditorPane.this);
        } catch (BadLocationException ble) {
            UIManager.getLookAndFeel().provideErrorFeedback(JEditorPane.this);
        }
    }

    public final void setContentType(String type) {
//        int parm = type.indexOf(';');
//        if (parm > -1) {
//            String parmList = type.substring(parm);
//            type = type.substring(0, parm).trim();
//            if (type.toLowerCase().startsWith("text/")) {
//                setCharsetFromContentTypeParameters(parmList);
//            }
//        }
    }

    public EditorKit getEditorKit() {
        JEditorPane pane = (JEditorPane) getComponent();
        return pane.getEditorKit();
    }

    public JComponent getComponent() {
        JComponent jComponent = null;
        return jComponent;
    }
}
