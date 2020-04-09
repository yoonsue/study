package visitor;

import java.io.File;

public class DirectoryPrinter implements Directory.Visitor {
    @Override
    public void visitFile(File current, int depth) {

    }

    @Override
    public void visitDirectory(File f, int depth, File[] contents) {
        while ( --depth >= 0 ){
            System.out.print("..");
        }
        System.out.println( f.getName() );
    }
}
