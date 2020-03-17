package adapter;

import org.junit.jupiter.api.Test;

import java.text.spi.DateFormatProvider;
import java.util.Locale;

class AdapterTest {

//    @ParameterizedTest
    static void adapterTest(String expected, String lang, String ctry) {
        Locale testLocale = new Locale(lang, ctry);
        LocaleProviderAdapter adapter = LocaleProviderAdapter.getAdapter(DateFormatProvider.class, testLocale);
        LocaleProviderAdapter.Type type = adapter.getAdapterType();
        if (!type.toString().equals(expected)) {
            throw new RuntimeException("Returned locale data adatper is not correct");
        }

    }

}