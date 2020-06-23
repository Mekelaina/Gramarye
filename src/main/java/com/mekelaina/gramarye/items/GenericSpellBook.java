package com.mekelaina.gramarye.items;

import com.mekelaina.gramarye.Gramarye;
import com.mekelaina.gramarye.Spell.Spell;
import net.minecraft.item.Item;

public class GenericSpellBook extends Item {
    private Spell boundSpell;

    public GenericSpellBook() {
        super(new Properties()
                .maxStackSize(1)
                .group(Gramarye.setup.itemGroup)
        );
    }
}
