package swingy.model.character.heros;

public class Wolverine extends Hero {

    public Wolverine() { super(); }

    public Wolverine(String name) {
        super(name);
        this.type = "Wolverine";
        this.attack += 10;
        this.defense += 3;
        this.hitPoints += 75;
    }
}
