package com.mekelaina.gramarye.spell;

public abstract class Spell {
    protected final int manaCost;
    protected final ESpellLevel spellLevel;
    protected final ESpellElement spellElement;
    protected final String spellEnglishName;
    protected final String registryName;

    public Spell(Spell.Properties properties) {
        this.manaCost = properties.manaCost;
        this.spellEnglishName = properties.localizationKey;
        this.registryName = properties.registryName;
        this.spellLevel = properties.spellLevel;
        this.spellElement = properties.spellElement;
    }

    public abstract SpellCastResult onSpellCast(SpellCastContext spellContext);

    public ESpellLevel getSpellLevel() {
        return spellLevel;
    }

    public ESpellElement getSpellElement() {
        return spellElement;
    }

    public String getSpellRegistryName() {
        return this.registryName;
    }

    public String getSpellEnglishName() {
        return this.spellEnglishName;
    }

    public int getManaCost() {
        return this.manaCost;
    }

    public static class Properties {
        private int manaCost;
        private ESpellLevel spellLevel;
        private ESpellElement spellElement;
        private String localizationKey;
        private String registryName;

        public Spell.Properties setManaCost(int manaCost) {
            this.manaCost = manaCost;
            return this;
        }

        public Spell.Properties setSpellEnglishName(String localizationKey) {
            this.localizationKey = localizationKey;
            return this;
        }

        public Spell.Properties setRegistryName(String registryName) {
            this.registryName = registryName;
            return this;
        }

        public Spell.Properties setSpellLevel(ESpellLevel spellLevel) {
            this.spellLevel = spellLevel;
            return this;
        }

        public Spell.Properties setSpellElement(ESpellElement spellElement) {
            this.spellElement = spellElement;
            return this;
        }


    }
}
