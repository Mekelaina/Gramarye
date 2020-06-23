package com.mekelaina.gramarye.Spell;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemUseContext;

public class Spell {
    protected final int manaCost;
    protected final ESpellLevel spellLevel;
    protected final ESpellElement spellElement;
    protected final String localizationKey;
    protected final String registryName;



    public Spell(Spell.Properties properties) {
        this.manaCost = properties.manaCost;
        this.localizationKey = properties.localizationKey;
        this.registryName = properties.registryName;
        this.spellLevel = properties.spellLevel;
        this.spellElement = properties.spellElement;
    }

    public void onSpellCast(ItemUseContext spellContext) {
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

        public Spell.Properties setLocalizationKey(String localizationKey) {
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
