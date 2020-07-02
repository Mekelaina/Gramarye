package com.mekelaina.gramarye.spell;

import com.mekelaina.gramarye.Gramarye;
import net.minecraft.util.ResourceLocation;

public enum ESpellElement {
    Fire(0),
    Water(1),
    Earth(2),
    Air(3),
    Mystic(4),
    Void(5),
    Nature(6),
    Redstone(7);

    private final float value;

    ESpellElement(float value){
        this.value = value;
    }

    public float getValue() {
        return value;
    }

    public ESpellElement getElementFromFloat(float f){
        int temp = (int)f;
        switch (temp) {
            case 0: return Fire;
            case 1: return Water;
            case 2: return Earth;
            case 3: return Air;
            case 5: return Void;
            case 6: return Nature;
            case 7: return Redstone;
            default: return Mystic;
        }
    }

    public ResourceLocation getElementResourceLocation() {
        return new ResourceLocation(Gramarye.MODID + ":item/book/" + this.name().toLowerCase());
    }

    @Override
    public String toString() {
        return "ESpellElement{" +
                "value=" + value +
                '}';
    }
}
