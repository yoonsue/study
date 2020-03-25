package abstract_factory.latest;

import com.sun.org.apache.xml.internal.security.utils.JDKXPathAPI;
import com.sun.org.apache.xml.internal.security.utils.XPathAPI;

public class JDKXPathFactory extends XPathFactory {
    @Override
    public XPathAPI newXPathAPI() {
        System.out.println("JDX");
        return new JDKXPathAPI();
    }
}
