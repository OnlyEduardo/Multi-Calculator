package com.swellshinider.access;

import com.swellshinider.Main;
import com.swellshinider.util.SceneName;

public class Access {

    private final String name;
    private final String description;
    private final SceneName sceneName;

    public Access(String name, String description, SceneName sceneName) {
        this.name = name;
        this.description = description;
        this.sceneName = sceneName;
    }

    public void goTo(){
        Main.instance.changeToScene(sceneName);
    }

    public String getName() {
        return name.toLowerCase();
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return name.concat(" <=> ").concat(description);
    }
}
