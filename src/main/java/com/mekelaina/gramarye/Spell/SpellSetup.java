package com.mekelaina.gramarye.Spell;

import com.mekelaina.gramarye.items.GenericSpellBook;
import com.mekelaina.gramarye.items.ModItems;

import java.util.HashMap;
import java.util.Map;

public class SpellSetup {
    //A holder for our spells so we can dynamically create Spell Book Item registrations during setup.
    //If we were to use a forge registry, our spell register would occur after the item register, and would be useless.
    public static final HashMap<String, Spell> spellMap = new HashMap<>();

    private static void registerSpell(Spell spell) {
        if(spell.registryName != null) {
            registerSpell(spell, spell.registryName);
        }
    }

    private static void registerSpell(Spell spell, String registryName) {
        if(spellMap.containsKey(registryName)) {
            throw new RuntimeException("Spell already registered.");
        } else {
            spellMap.put(registryName, spell);
        }
    }

    public static void registerSpellBooks() {
        registerAllSpells();
        for (Spell spell : SpellSetup.spellMap.values()) {
            ModItems.SPELLBOOK_REGISTRY.put(spell, ModItems.ITEMS.register("book/" + spell.getSpellRegistryName(), () -> new GenericSpellBook(spell)));
        }
    }

    //This is essentially the list of all spells.
    //Any new spell should be placed below.
    private static void registerAllSpells() {
        registerSpell(new SpellSpark());
    }
}
