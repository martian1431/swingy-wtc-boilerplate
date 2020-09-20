package swingy.model;

import swingy.model.character.heros.Hero;


import java.sql.*;

public class GameModel {

    private GameModel() {}

    public static void getInstance() {
    }

    public void setupDatabase() {
        createTable();
    }

    private void createTable() {
    }

    public void heroExists(String name) throws SQLException {
    }

    public void insertHero(Hero newHero) throws SQLException {
    }

    public void numberOfHeroes() throws SQLException {
    }

    public void retrieveAllHeroes() throws SQLException {
    }

    public void retrieveHeroData(String name) throws SQLException {
    }

    private void parseResult(ResultSet rs) throws SQLException {
    }

    public void updateHero(Hero hero) throws SQLException {
    }

    public void retrieveDatabase() {
    }


    public static void moveHero(int direction) {
    }

    public static void action() {
    }

    public static void fight() {
    }

    public static void run() {
    }

    private static void battleGains() {
    }

    private static void equip(String artifactType) {
    }

    public static void goal() {
    }
}
