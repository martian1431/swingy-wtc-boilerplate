package swingy.model.character.heros;

public class Deadpool extends Hero {

    public Deadpool() { super(); }

    public Deadpool(String name) {
        super(name);
        this.type = "Deadpool";
        this.attack += 6;
        this.defense += 1;
        this.hitPoints += 25;
    }
}
