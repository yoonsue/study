package abstract_factory;

import org.junit.jupiter.api.Test;

import javax.xml.xpath.XPath;

import static org.junit.jupiter.api.Assertions.*;

public class XPathTest {

    @Test
    public void testNamespaceContext() {
        XPathFactory xPathFactory = XPathFactory.newInstance();
        xPathFactory.newXPathAPI();
    }
}