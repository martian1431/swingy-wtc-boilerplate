package swingy.model.character.villian;

public class Magneto extends Villian {

    public Magneto(int level) {
        super(level);
        this.name = "The Kid Next Door";
        this.type = "Magneto";
        this.attack = level + 6;
        this.defense = level + 2;
        this.hitPoints = level + 15;
    }
}
