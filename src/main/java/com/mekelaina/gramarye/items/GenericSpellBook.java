package com.mekelaina.gramarye.items;

import com.mekelaina.gramarye.Gramarye;
import com.mekelaina.gramarye.Spell.Spell;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;

public class GenericSpellBook extends Item {
    private final Spell boundSpell;

    public GenericSpellBook(Spell spellToBind) {
        super(new Properties()
                .maxStackSize(1)
                .group(Gramarye.setup.itemGroup)
        );
        this.boundSpell = spellToBind;
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        return boundSpell.onSpellCast(context);
    }
}
