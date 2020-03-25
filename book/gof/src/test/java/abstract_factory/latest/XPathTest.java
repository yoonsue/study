package abstract_factory.latest;

import abstract_factory.latest.XPathFactory;
import org.junit.jupiter.api.Test;

public class XPathTest {

    @Test
    public void testNamespaceContext() {
        XPathFactory xPathFactory = XPathFactory.newInstance();
        xPathFactory.newXPathAPI();
    }
}