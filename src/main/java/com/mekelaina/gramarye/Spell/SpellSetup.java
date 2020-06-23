package com.mekelaina.gramarye.Spell;

import java.util.Map;

public class SpellSetup {
    //A holder for our spells so we can dynamically create Spell Book Item registrations during setup.
    //If we were to use a forge registry, our spell register would occur after the item register, and would be useless.
    private Map<String, Spell> spellMap;

    private void registerSpell(Spell spell) {
        if(spell.registryName != null) {
            registerSpell(spell, spell.registryName);
        }
    }

    private void registerSpell(Spell spell, String registryName) {
        if(spellMap.containsKey(registryName)) {
            throw new RuntimeException("Spell already registered.");
        } else {
            spellMap.put(registryName, spell);
        }
    }

    public void setupSpellBooks() {
        registerAllSpells();

    }

    //This is essentially the list of all spells.
    //Any new spell should be placed below.
    private void registerAllSpells() {
        registerSpell(new SpellSpark());
    }
}
