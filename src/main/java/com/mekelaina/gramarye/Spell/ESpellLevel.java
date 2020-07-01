package com.mekelaina.gramarye.Spell;

import com.mekelaina.gramarye.Gramarye;
import net.minecraft.util.ResourceLocation;

public enum ESpellLevel {
    Novice(0),
    Apprentice(1),
    Adept(3),
    Expert(4),
    Master(5);

    private final float value;

    ESpellLevel(float value){
        this.value = value;
    }

    public float getValue() {
        return value;
    }

    public ResourceLocation getLevelResourceLocation() {
        return new ResourceLocation(Gramarye.MODID + ":item/book/" + this.name().toLowerCase());
    }

    @Override
    public String toString() {
        return "ESpellLevel{" +
                "value=" + value +
                '}';
    }
}
