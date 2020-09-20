package swingy.utils;

import swingy.model.character.heros.Hero;

public abstract class GridFactory {

    public static Grid generateMap(Hero hero) {
        int mapSize = (hero.getLevel() - 1) * 5 + 10 - (hero.getLevel() % 2);

        if (mapSize >= 25) {
            mapSize = 25;
        }
        Grid grid = new Grid(mapSize);
        grid.registerHero(hero);
        grid.spreadEnemies();
        return (grid);
    }
}
