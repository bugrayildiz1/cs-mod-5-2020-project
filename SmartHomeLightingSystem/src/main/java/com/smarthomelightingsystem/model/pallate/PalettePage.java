package com.smarthomelightingsystem.model.pallate;

import com.smarthomelightingsystem.model.pallate.Animation;
import com.smarthomelightingsystem.model.pallate.Preset;

import java.util.ArrayList;
import java.util.List;

/**
 * The Palette class stores information about palette page of 
 * Smart Home Lighting System web app, works with LED strip 
 * brightness and color.
 * 
 * @author Jasper van Amerongen
 * @author Albina Shynkar
 * @author Lola Solovyeva
 * @author Ilya Averchenko
 * @author Bugra Yildiz
 * @author Alexandru Matcov
 * 
 * @version 1
 */
public class PalettePage {

    List<Animation> animations = new ArrayList<>();
    List<Preset> presets = new ArrayList<>();

    public PalettePage() {
    }

    public List<Animation> getAnimations() {
        return animations;
    }

    public void setAnimations(List<Animation> animations) {
        this.animations = animations;
    }

    public List<Preset> getPresets() {
        return presets;
    }

    public void setPresets(List<Preset> presets) {
        this.presets = presets;
    }

}
