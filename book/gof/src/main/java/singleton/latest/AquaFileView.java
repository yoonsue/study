package singleton.latest;

import javax.swing.*;
import java.io.File;
import java.io.UnsupportedEncodingException;

public class AquaFileView {

    static class FileInfo {
        final boolean isDirectory;
        final String absolutePath;
        byte[] pathBytes;

        String displayName;
        Icon icon;

        FileInfo(final File file) {
            isDirectory = file.isDirectory();
            absolutePath = file.getAbsolutePath();
            try {
                pathBytes = absolutePath.getBytes("UTF-8");
            } catch (final UnsupportedEncodingException e) {
                pathBytes = new byte[0];
            }
        }
    }

    public Icon getIcon(final File f) {
        final FileInfo info = null;
        if (info.icon != null) return info.icon;

        if (f==null) {
            info.icon = AquaIcon.SystemIcon.getDocumentIconUIResource();
        } else {

        }
        return info.icon;
    }
}
