package swingy.view;

import swingy.model.character.heros.Hero;

import java.util.List;

public interface ViewInterface {
  void setEnableStartButton(boolean b);
  void showStartScreen();
  void showSelectScreen(List<Hero> heroList);
  void showCreateScreen();
  void showGameView(Hero hero);
  void showErrorDialog(String error);
  void clearLoadingLabel();
}
