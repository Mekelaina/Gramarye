package com.mekelaina.gramarye.items;

import com.mekelaina.gramarye.Gramarye;
import com.mekelaina.gramarye.Spell.Spell;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class GenericSpellBook extends Item{
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
