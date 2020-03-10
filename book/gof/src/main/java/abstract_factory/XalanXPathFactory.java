package abstract_factory;

import com.sun.org.apache.xml.internal.security.utils.XPathAPI;
import com.sun.org.apache.xml.internal.security.utils.XalanXPathAPI;

public class XalanXPathFactory extends XPathFactory {
    @Override
    public XPathAPI newXPathAPI() {
        System.out.println("Xalan");
        return new XalanXPathAPI();
    }
}
