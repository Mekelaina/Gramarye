package com.mekelaina.gramarye.Spell;

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

    @Override
    public String toString() {
        return "ESpellLevel{" +
                "value=" + value +
                '}';
    }
}
