package swingy.model.artifact;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Armor extends Artifact {

    private int defense;

    public Armor(String name, int defense) {
        super(name);
        this.type = ArtifactEnum.ARMOR;
        this.defense = defense;
    }
}
