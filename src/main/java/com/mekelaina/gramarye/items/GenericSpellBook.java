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
    private static Spell boundSpell;
   // private  final Spell boundSpell;

    public GenericSpellBook(Spell spellToBind) {
        super(new Properties()
                .maxStackSize(1)
                .group(Gramarye.setup.itemGroup)
        );
        this.boundSpell = spellToBind;
        this.addPropertyOverride(new ResourceLocation("element"), GenericSpellBook::getElementPropertyOverride);
        this.addPropertyOverride(new ResourceLocation("level"), GenericSpellBook::getLevelPropertyOverride);


    }

    @Nullable
    @Override
    public IItemPropertyGetter getPropertyGetter(ResourceLocation key) {
        return super.getPropertyGetter(key);
    }

    private static float getElementPropertyOverride(ItemStack stack, @Nullable World world, @Nullable LivingEntity entity){
        Gramarye.LOGGER.debug("loop zoop");
        return boundSpell.getSpellElement().getValue();
    }

    private static float getLevelPropertyOverride(ItemStack stack, @Nullable World world, @Nullable LivingEntity entity){
        return boundSpell.getSpellLevel().getValue();
    }


    @Override
    public boolean updateItemStackNBT(CompoundNBT nbt) {
        return super.updateItemStackNBT(nbt);
    }

    @Nullable
    @Override
    public CompoundNBT getShareTag(ItemStack stack) {

        CompoundNBT compoundNBT = stack.getOrCreateTag();
        compoundNBT.putFloat("spellElement", boundSpell.getSpellElement().getValue());
        compoundNBT.putFloat("spellLevel", boundSpell.getSpellLevel().getValue());
        return compoundNBT;
    }

    @Override
    public void readShareTag(ItemStack stack, @Nullable CompoundNBT nbt) {
        super.readShareTag(stack, nbt);
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        context.getPlayer().sendStatusMessage(new StringTextComponent(boundSpell.getSpellElement().toString()
        + " " + boundSpell.getSpellLevel().toString()), true);
       //context.getPlayer().sendStatusMessage(new StringTextComponent(boundSpell.getSpellLevel().toString()), true);
        return boundSpell.onSpellCast(context);
    }

}
