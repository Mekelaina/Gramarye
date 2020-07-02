package com.mekelaina.gramarye.items;

import com.mekelaina.gramarye.capabilities.Experia;
import com.mekelaina.gramarye.spell.Spell;
import com.mekelaina.gramarye.spell.SpellCastContext;
import com.mekelaina.gramarye.spell.SpellCastResult;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;

import javax.annotation.Nonnull;

public abstract class AbstractSpellCaster extends Item {
    protected final Spell BOUND_SPELL;
    protected final int MAX_MANA;

    public AbstractSpellCaster(int maxMana, Spell spellToBind, Properties properties) {
        super(properties);
        this.BOUND_SPELL = spellToBind;
        this.MAX_MANA = maxMana;

    }

    /* Subclasses of this should override getManaSupplier to choose where to take the mana from.
     * E.G. If the item has an IItemHandler and you want to use the mana from one of the items it's holding.
     */
    public abstract Experia getManaSupplier(ItemStack item);

    /*
     *  called before the spell is cast when mana is too low to cast a spell but is not 0.
     *  called after the spell is cast when there is enough mana to cast but it will reach 0 after.
     */
    public abstract void onManaEmpty(SpellCastContext castContext);

    @Nonnull
    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        Experia manaSupplier = getManaSupplier(context.getItem());
        int availableMana = manaSupplier.getExperiaAmount();

        if(availableMana < this.BOUND_SPELL.getManaCost()) {
            if(availableMana > 0) {
                manaSupplier.setExperiaAmount(0);
                this.onManaEmpty(new SpellCastContext(context, availableMana, true));
                return ActionResultType.PASS;
            } else {
                return ActionResultType.FAIL;
            }
        }

        SpellCastContext castContext = new SpellCastContext(context, availableMana, false);
        SpellCastResult result = this.castAttachedSpell(castContext);

        int manaCost = result.finalManaCost > 0 ? result.finalManaCost : this.BOUND_SPELL.getManaCost();

        if(depleteManaFromSupplier(manaCost, manaSupplier)) {
            this.onManaEmpty(castContext);
        }

        return result.spellResult;
    }

    public SpellCastResult castAttachedSpell(SpellCastContext castContext) {
        return this.BOUND_SPELL.onSpellCast(castContext);
    }

    public boolean depleteManaFromSupplier(int amountToDeplete, Experia manaSupplier) {
        int availableMana = manaSupplier.getExperiaAmount();
        if(amountToDeplete >= availableMana) {
            manaSupplier.setExperiaAmount(0);
            return true;
        } else {
            manaSupplier.subtractExperia(amountToDeplete);
            return false;
        }
    }
}
