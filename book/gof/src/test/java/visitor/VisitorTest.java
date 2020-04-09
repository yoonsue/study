package visitor;

import org.junit.jupiter.api.Test;

class VisitorTest {

    @Test
    void DirectoryTest() {
        Directory d = new Directory("C:\\");
        d.traverse( new DirectoryPrinter());
    }

}