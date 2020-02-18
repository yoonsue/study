package AbstractFactory;

import AbstractFactory.OSXFactory.OSXFactory;
import AbstractFactory.WindowFactory.WindowFactory;

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
