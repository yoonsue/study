package adapter.latest;

import sun.util.locale.provider.ResourceBundleBasedAdapter;
import sun.util.resources.LocaleData;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.spi.LocaleNameProvider;
import java.util.spi.LocaleServiceProvider;
import java.util.spi.TimeZoneNameProvider;

// Adapter
public class JRELocaleProviderAdapter extends LocaleProviderAdapter implements ResourceBundleBasedAdapter {

    private final ConcurrentMap<String, Set<String>> langtagSets
            = new ConcurrentHashMap<>();

    private volatile LocaleData localeData;

    @Override
    public LocaleProviderAdapter.Type getAdapterType() {
        return Type.JRE;
    }

    @Override
    public <P extends LocaleServiceProvider> P getLocaleServiceProvider(Class<P> c) {
        switch (c.getSimpleName()) {
            case "LocaleNameProvider":
                return (P) getLocaleNameProvider();
            case "TimeZoneNameProvider":
                return (P) getTimeZoneNameProvider();
            default:
                throw new InternalError("should not come down here");
        }
    }

    private volatile LocaleNameProvider localeNameProvider;
    protected volatile TimeZoneNameProvider timeZoneNameProvider;

    public LocaleNameProvider getLocaleNameProvider() {
        return localeNameProvider;
    }

    public TimeZoneNameProvider getTimeZoneNameProvider() {
        return timeZoneNameProvider;
    }

    public Set<String> getLanguageTagSet(String category) {
        Set<String> tagset = langtagSets.get(category);
        return tagset;
    }

    @Override
    public LocaleData getLocaleData() {
        return localeData;
    }
}
