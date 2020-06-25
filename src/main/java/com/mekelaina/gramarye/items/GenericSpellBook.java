package com.mekelaina.gramarye.items;

import com.mekelaina.gramarye.Gramarye;
import com.mekelaina.gramarye.Spell.Spell;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;

public class GenericSpellBook extends DefaultEMItem {
    private final Spell boundSpell;

    public GenericSpellBook(Spell spellToBind) {
        super(new Properties()
                .maxStackSize(1)
                .group(Gramarye.setup.itemGroup)
        );
        this.boundSpell = spellToBind;
    }

    public GenericSpellBook(Spell spellToBind, boolean canBeRecharged) {
        this(spellToBind);
        this.rechargeable = canBeRecharged;
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        return boundSpell.onSpellCast(context);
    }

    public void setRechargeable(boolean rechargeable) {
        this.rechargeable = rechargeable;
    }
}
