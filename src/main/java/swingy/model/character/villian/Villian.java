package swingy.model.character.villian;

import swingy.model.character.heros.Hero;

public abstract class Villian extends Hero {
    public Villian(int level) {
        this.level = level;
    }
}