package swingy.model.artifact;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Helm extends Artifact {
    private int hitPoints;

    public Helm(String name, int hitPoints) {
        super(name);
        this.type = ArtifactEnum.HELM;
        this.hitPoints = hitPoints;
    }
}
