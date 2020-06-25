package com.mekelaina.gramarye.items.book;

import com.mekelaina.gramarye.Gramarye;
import com.mekelaina.gramarye.Spell.Spell;
import com.mekelaina.gramarye.Spell.SpellCastContext;
import com.mekelaina.gramarye.Spell.SpellCastResult;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class GenericSpellBook extends Item{
    public static final String NBT_TAG_NAME_BOOKTYPE = "booktype";
    public static final String NBT_TAG_NAME_MANA = "mana";

    private final Spell boundSpell;
    private final int maxMana;
    private EBookType bookType;

    public GenericSpellBook(Spell spellToBind) {
        super(new Properties()
                .maxStackSize(1)
                .maxDamage(0)
                .group(Gramarye.setup.itemGroup)
        );
        this.boundSpell = spellToBind;
        this.bookType = EBookType.Looted;
        this.maxMana = 1000;

        addPropertyOverride(new ResourceLocation(Gramarye.MODID, "booktype"), GenericSpellBook::getBookTypePropertyOverride);
    }

    @Nonnull
    @Override
    public ActionResultType onItemUse(@Nonnull ItemUseContext context) {
        int availableMana = getBookMana(context.getItem());

        //Looted spells should have a last hurrah if applicable imho.
        if(availableMana < this.boundSpell.getManaCost() && getBookType(context.getItem()) == EBookType.Crafted) {
            return ActionResultType.FAIL;
        }

        SpellCastContext spellContext = new SpellCastContext(context, availableMana);
        SpellCastResult spellResultContext = boundSpell.onSpellCast(spellContext);

        if(spellResultContext.spellResult == ActionResultType.SUCCESS) {
            if (spellResultContext.doesSpellHaveStaticManaCost()) {
                deductManaCost(context, this.boundSpell.getManaCost());
            } else {
                deductManaCost(context, spellResultContext.finalManaCost);
            }
        }

        return  spellResultContext.spellResult;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new StringTextComponent("Book Type: " + getBookType(stack).name()));
    }

    @Override
    public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
        if(this.isInGroup(group)) {
            for(EBookType bookType : EBookType.values()) {
                ItemStack subItemStack = new ItemStack(this, 1);
                setBookType(subItemStack, bookType);
                items.add(subItemStack);
            }
        }
    }

    private void deductManaCost(ItemUseContext context, int manaCost) {
        ItemStack spellBookItemStack = context.getItem();
        if(context.getPlayer() != null) {
            int currentMana = getBookMana(spellBookItemStack);
            int newMana = currentMana - manaCost;
            if(newMana <= 0 ){
                if(getBookType(spellBookItemStack) == EBookType.Looted) {
                    context.getPlayer().sendBreakAnimation(context.getHand());
                    spellBookItemStack.shrink(1);
                    context.getPlayer().addStat(Stats.ITEM_BROKEN.get(spellBookItemStack.getItem()));

                    return;
                }

                setBookMana(spellBookItemStack, 0);
            } else {
                setBookMana(spellBookItemStack, newMana);
            }
        }
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        int currentMana = getBookMana(stack);
        return currentMana > 0 && currentMana < maxMana;
    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack) {
        int currentMana = getBookMana(stack);
        double percentManaLeft = MathHelper.clamp(((double)currentMana) / ((double)maxMana), 0.0, 1.0);
        return 1.0 - percentManaLeft;
    }

    public int getMaxMana() {
        return this.maxMana;
    }

    public static EBookType getBookType(ItemStack itemStack) {
        CompoundNBT compoundNBT = itemStack.getOrCreateTag();
        return EBookType.fromNBT(compoundNBT, NBT_TAG_NAME_BOOKTYPE);
    }

    public static void setBookType(ItemStack itemStack, EBookType bookType) {
        CompoundNBT compoundNBT = itemStack.getOrCreateTag();
        bookType.putIntoNBT(compoundNBT, NBT_TAG_NAME_BOOKTYPE);
    }

    public static int getBookMana(ItemStack itemStack) {
        CompoundNBT compoundNBT = itemStack.getOrCreateTag();
        int nbtMana = ((GenericSpellBook)itemStack.getItem()).getMaxMana();
        if(compoundNBT.contains(NBT_TAG_NAME_MANA)) {
            nbtMana = compoundNBT.getInt(NBT_TAG_NAME_MANA);
        } else {
            setBookMana(itemStack, nbtMana);
        }
        return nbtMana;
    }

    public static void setBookMana(ItemStack itemStack, int mana) {
        CompoundNBT compoundNBT = itemStack.getOrCreateTag();
        compoundNBT.putInt(NBT_TAG_NAME_MANA, mana);
    }

    private static byte getBookTypePropertyOverride(ItemStack itemStack, @Nullable World world, @Nullable LivingEntity livingEntity)
    {
        EBookType bookType = getBookType(itemStack);
        return bookType.getPropertyOverrideValue();
    }
}
