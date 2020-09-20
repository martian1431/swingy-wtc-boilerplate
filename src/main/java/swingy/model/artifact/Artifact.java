package swingy.model.artifact;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public abstract class Artifact implements Serializable {
    private static final long serialVersionUID = 2776303584447042497L;

    String name;
    ArtifactEnum type;

    Artifact(String name) {
        this.name = name;
    }
}
