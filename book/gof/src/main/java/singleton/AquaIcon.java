package singleton;

import javax.swing.*;
import javax.swing.plaf.IconUIResource;
import javax.swing.plaf.UIResource;
import java.awt.*;

public class AquaIcon {

    // Singleton Pattern
    static class SystemIconSingleton extends AquaUtils.RecycleableSingleton<SystemIcon> {
        final String selector;

        public SystemIconSingleton(String selector) {
            this.selector = selector;
        }

        @Override
        protected SystemIcon getInstance() {
            return new SystemIcon(selector);
        }
    }

    // Singleton Pattern
    static class SystemIconUIResourceSingleton extends AquaUtils.RecycleableSingleton<IconUIResource> {
        final String selector;

        public SystemIconUIResourceSingleton(String selector) {
            this.selector = selector;
        }

        @Override
        IconUIResource getInstance() {
            return new IconUIResource(new SystemIcon(selector));
        }
    }

    abstract static class CachingScalingIcon implements Icon, UIResource {
        int width;
        int height;
        Image image;

        public  CachingScalingIcon(final int width, final int height) {
            this.width = width;
            this.height = height;
        }
    }

    static class SystemIcon extends CachingScalingIcon {
        final String selector;

        private static final SystemIconUIResourceSingleton documentIcon = new SystemIconUIResourceSingleton("docu");
        static IconUIResource getDocumentIconUIResource() {return documentIcon.get(); }

        public SystemIcon(final String iconSelector, final int width, final int height) {
            super(width, height);
            selector = iconSelector;
        }

        public SystemIcon(final String iconSelector) {
            this(iconSelector, 16, 16);
        }

        @Override
        public void paintIcon(Component c, Graphics g, int x, int y) {

        }

        @Override
        public int getIconWidth() {
            return 0;
        }

        @Override
        public int getIconHeight() {
            return 0;
        }
    }

}
