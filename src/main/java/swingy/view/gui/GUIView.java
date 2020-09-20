package swingy.view.gui;

import swingy.model.character.heros.Hero;
import swingy.view.ViewInterface;

import javax.swing.*;
import java.util.List;

public class GUIView extends JFrame implements ViewInterface {

    public GUIView() {
    }

//    interface
    @Override
    public void showStartScreen() {
    }

    @Override
    public void showSelectScreen(List<Hero> heroList) {
    }

    @Override
    public void showCreateScreen() {
    }

    @Override
    public void setEnableStartButton(boolean b) {
    }


    @Override
    public void clearLoadingLabel() {
    }


    @Override
    public void showGameView(Hero hero) {
    }

//    static
    public static void run() {
    }


    private void labelFormatter(String input) {
    }

    public void showErrorDialog(String error) {
    }
}
