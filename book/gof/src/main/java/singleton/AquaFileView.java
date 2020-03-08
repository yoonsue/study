package singleton;

import javax.swing.*;

public class AquaFileView {
    public Icon getIcon(final File f) {
        final FileInfo info = getFileInfoFor(f);
        if (info.icon != null) return info.icon;

        if (f==null) {
            info.icon = AquaIcon.SystemIcon.getDocumentIconUIResource();
        } else {
            final AquaIcon.FileIcon fileIcon = new AquaIcon.FileIcon(f);
            info.icon = fileIcon;
            if (!fileIcon.hasIconRef()) {
                if (f.isDirectory()) {
                    if
                }
            }
        }
    }
}
