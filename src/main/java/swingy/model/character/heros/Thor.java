package swingy.model.character.heros;

public class Thor extends Hero {
    public Thor() { super();}

    public Thor(String name) {
        super(name);
        this.type = "Thor";
        this.attack += 8;
        this.defense += 2;
        this.hitPoints += 50;
    }
}
