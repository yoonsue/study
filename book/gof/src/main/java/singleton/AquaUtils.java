package singleton;

import sun.awt.AppContext;

import java.lang.ref.SoftReference;

public class AquaUtils {

    abstract static class RecycleableSingleton<T> {
        final T get() {
            return AppContext.getSoftReferenceValue(this, () -> getInstance());
        }

        void reset() {
            AppContext.getAppContext().remove(this);
        }

        abstract T getInstance();
    }
}
