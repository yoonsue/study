package templateMethod;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class TemplateMethodTest {

    @Test
    public void exception() throws Exception {
        char[] buf = "hello world".toCharArray();
        MReader r = new MCharArrayReader(buf);
        r.reset();
    }

    @Test
    public void supportedTemplateMethod() throws Exception {
        char[] buf = "hello world".toCharArray();
        MReader r = new MCharArrayReader(buf);
        Assertions.assertEquals('h', r.read());
    }

}