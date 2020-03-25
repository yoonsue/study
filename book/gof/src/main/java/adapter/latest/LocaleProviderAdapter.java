package adapter.latest;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentMap;
import java.util.spi.LocaleServiceProvider;

// Target
public abstract class LocaleProviderAdapter {

    public static enum Type {
        JRE("sun.util.locale.provider.JRELocaleProviderAdapter", "sun.util.resources", "sun.text.resources"),
        CLDR("sun.util.cldr.CLDRLocaleProviderAdapter", "sun.util.resources.cldr", "sun.text.resources.cldr");

        private final String CLASSNAME;
        private final String UTIL_RESOURCES_PACKAGE;
        private final String TEXT_RESOURCES_PACKAGE;

        Type(String className, String util, String text) {
            CLASSNAME = className;
            UTIL_RESOURCES_PACKAGE = util;
            TEXT_RESOURCES_PACKAGE = text;
        }
    }

    public abstract LocaleProviderAdapter.Type getAdapterType();

    private static final List<Type> adapterPreference = null;

    public static LocaleProviderAdapter forType(Type type) {
        switch (type) {
            case JRE:
            case CLDR:
                LocaleProviderAdapter adapter = null;
                return adapter;
            default:
                throw new InternalError("unknown locale data adapter type");
        }
    }

    private static LocaleProviderAdapter findAdapter(Class<? extends LocaleServiceProvider> providerClass, Locale locale) {
        for (Type type : getAdapterPreference()) {
            LocaleProviderAdapter adapter = forType(type);
            if (adapter != null) {
                LocaleServiceProvider provider = adapter.getLocaleServiceProvider(providerClass);
                if (provider != null) {
                    if (provider.isSupportedLocale(locale)) {
                        return adapter;
                    }
                }
            }
        }
        return null;
    }

    public static List<Type> getAdapterPreference() {
        return adapterPreference;
    }

    public static LocaleProviderAdapter getAdapter(Class<? extends LocaleServiceProvider> providerClass,
                                                   Locale locale) {
        LocaleProviderAdapter adapter;

        ConcurrentMap<Locale, LocaleProviderAdapter> adapterMap = null;

        // Fast look-up for the given locale
        adapter = findAdapter(providerClass, locale);
        if (adapter != null) {
            adapterMap.putIfAbsent(locale, adapter);
            return adapter;
        }

        // Try finding an adapter in the normal candidate locales path of the given locale.
        List<Locale> lookupLocales = ResourceBundle.Control.getControl(ResourceBundle.Control.FORMAT_DEFAULT)
                .getCandidateLocales("", locale);
        for (Locale loc : lookupLocales) {
            if (loc.equals(locale)) {
                // We've already done with this loc.
                continue;
            }
            adapter = findAdapter(providerClass, loc);
            if (adapter != null) {
                adapterMap.putIfAbsent(locale, adapter);
                return adapter;
            }
        }
        return null;
    }

    public abstract <P extends LocaleServiceProvider> P getLocaleServiceProvider(Class<P> c);
}
