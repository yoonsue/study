package abstract_factory.previous;

import abstract_factory.previous.Appearance;
import abstract_factory.previous.GUIFactory;
import abstract_factory.previous.OSXFactory.OSXFactory;
import abstract_factory.previous.WindowFactory.WindowFactory;

import java.util.Scanner;

public class Main {
    public static void Main()
    {
        GUIFactory factory;
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        if (input == Appearance.OSX.toString()) {
            factory = new OSXFactory();
        } else if  (input == Appearance.WINDOW.toString()) {
            factory = new WindowFactory();
        }
    }
}
