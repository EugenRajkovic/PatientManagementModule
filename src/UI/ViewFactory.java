/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import UI.GUI.GuiView;
import UI.Console.ConsoleView;

/**
 *
 * @author Eugen
 */
public class ViewFactory {
    public static IView CurrentView(int view){
        switch (view) {
            case 1:
                return new ConsoleView();
            case 2:
                return new GuiView();
            default:
                throw new AssertionError();
        }
    }
}
