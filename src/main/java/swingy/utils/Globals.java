package swingy.utils;

import swingy.model.artifact.Artifact;
import swingy.model.character.villian.Villian;
import swingy.model.character.heros.Hero;
import swingy.view.gui.GUIView;

public class Globals {
    private Globals() {

    }

    public static boolean DISPLAY_LOGO = true;
    public static boolean CONSOLE_MODE = true;

    public static boolean ARTIFACT_DROPPED;
    public static boolean GOAL_REACHED;
    public static boolean HERO_RAN;


    public static Hero hero;
    public static Grid grid;
    public static Artifact artifact;
    public static Villian villian;

    public static GUIView guiView;

    public static final String showStartScreen = "SHOW_START_SCREEN";
    public static final String showSelect = "SHOW_SELECT";
    public static final String showCreateScreen = "SHOW_CREATE_SCREEN";
    public static final String createNewHero = "CREATE_NEW_HERO";
    public static final String showSelectError = "SHOW_SELECT_ERROR";
    public static final String showStats = "SHOW_STATS";
    public static final String showGameView = "SHOW_GAME_VIEW";
    public static final String selectHeroById = "SELECT_HERO_BY_ID";
    public static final String moveNorth = "MOVE_NORTH";
    public static final String moveEast = "MOVE_EAST";
    public static final String moveSouth = "MOVE_SOUTH";
    public static final String moveWest = "MOVE_WEST";
    public static final String startFight = "START_FIGHT";
    public static final String run = "RUN";
    public static final String continueFight = "CONTINUE_FIGHT";
    public static final String pickupItem = "PICKUP_ITEM";
    public static final String leaveItem = "LEAVE_ITEM";
    public static final String exitGame = "EXIT";
    public static final String playAgain = "PLAY_AGAIN";
}
