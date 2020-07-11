package com.mekelaina.gramarye.items.book;

import com.mekelaina.gramarye.Gramarye;
import com.mekelaina.gramarye.capabilities.CapabilityExperia;
import com.mekelaina.gramarye.capabilities.CapabilityProviderExperia;
import com.mekelaina.gramarye.capabilities.Experia;
import com.mekelaina.gramarye.items.AbstractSpellCaster;
import com.mekelaina.gramarye.spell.Spell;
import com.mekelaina.gramarye.spell.SpellCastContext;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.stats.Stats;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

public class GenericSpellBook extends AbstractSpellCaster {
    public static final String NBT_TAG_NAME_BOOKTYPE = "booktype";

    public GenericSpellBook(Spell spellToBind) {
        super(1000, spellToBind, new Properties()
            .maxStackSize(1)
            .maxDamage(0)
            .group(Gramarye.setup.itemGroup)
        );

        addPropertyOverride(new ResourceLocation(Gramarye.MODID, "booktype"), GenericSpellBook::getBookTypePropertyOverride);
    }

    @Override
    public Experia getManaSupplier(ItemStack item) {
        return item.getCapability(CapabilityExperia.CAPABILITY_EXPERIA).orElse(null);
    }

    @Override
    public void onManaEmpty(SpellCastContext castContext) {
        if(castContext.getPlayer() == null) return;
        EBookType bookType = getBookType(castContext.getItem());

        if(bookType == EBookType.Looted) {
            if(castContext.notEnoughManaToCast()) castAttachedSpell(castContext);
            castContext.getPlayer().sendBreakAnimation(castContext.getHand());
            castContext.getItem().shrink(1);
            castContext.getPlayer().addStat(Stats.ITEM_BROKEN.get(castContext.getItem().getItem()));
        }
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT nbt) {
        return new CapabilityProviderExperia(MAX_MANA);
    }



    @Override
    @ParametersAreNonnullByDefault
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new StringTextComponent("Book Type: " + getBookType(stack).name()));
    }

    @Override
    @ParametersAreNonnullByDefault
    public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
        if(this.isInGroup(group)) {
            for(EBookType bookType : EBookType.values()) {
                ItemStack subItemStack = new ItemStack(this, 1);
                setBookType(subItemStack, bookType);
                items.add(subItemStack);
            }
        }
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        int currentMana = getManaSupplier(stack).getExperiaAmount();
        return currentMana > 0 && currentMana < MAX_MANA;
    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack) {
        int currentMana = getManaSupplier(stack).getExperiaAmount();
        double percentManaLeft = MathHelper.clamp(((double)currentMana) / ((double)MAX_MANA), 0.0, 1.0);
        return 1.0 - percentManaLeft;
    }

    public static EBookType getBookType(ItemStack itemStack) {
        CompoundNBT compoundNBT = itemStack.getOrCreateTag();
        return EBookType.fromNBT(compoundNBT, NBT_TAG_NAME_BOOKTYPE);
    }

    public static void setBookType(ItemStack itemStack, EBookType bookType) {
        CompoundNBT compoundNBT = itemStack.getOrCreateTag();
        bookType.putIntoNBT(compoundNBT, NBT_TAG_NAME_BOOKTYPE);
    }

    private static byte getBookTypePropertyOverride(ItemStack itemStack, @Nullable World world, @Nullable LivingEntity livingEntity)
    {
        EBookType bookType = getBookType(itemStack);
        return bookType.getPropertyOverrideValue();
    }
}
