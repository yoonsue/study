package abstract_factory;

import com.sun.org.apache.xml.internal.security.utils.XPathAPI;
import com.sun.org.apache.xml.internal.security.utils.XalanXPathAPI;

public abstract class XPathFactory {
    private static boolean xalanInstalled;

    protected static synchronized boolean isXalanInstalled() {
        return xalanInstalled;
    }

    public static XPathFactory newInstance() {
        if (!isXalanInstalled()) {
            return new JDKXPathFactory();
        }

        if (XalanXPathAPI.isInstalled()) {
            return new XalanXPathFactory();
        }

        return new JDKXPathFactory();
    }

    public abstract XPathAPI newXPathAPI();
}
