package com.mekelaina.gramarye.spell;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FireBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

import static net.minecraft.item.FlintAndSteelItem.*;

public class SpellManaBullet extends Spell{

    public SpellManaBullet() {
        super(new Spell.Properties()
                .setManaCost(10)
                .setSpellLevel(ESpellLevel.Novice)
                .setSpellElement(ESpellElement.Fire)
                .setSpellEnglishName("Mana Bullet")
                .setRegistryName("manabullet"));
    }

    //I stole flint and steel's onItemUse event to use as a POC spell.
    @Override
    public SpellCastResult onSpellCast(SpellCastContext spellContext) {
        PlayerEntity playerentity = spellContext.getPlayer();
        IWorld iworld = spellContext.getWorld();
        BlockPos blockpos = spellContext.getPos();
        BlockPos blockpos1 = blockpos.offset(spellContext.getFace());
        if (canSetFire(iworld.getBlockState(blockpos1), iworld, blockpos1)) {
            iworld.playSound(playerentity, blockpos1, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, iworld.getRandom().nextFloat() * 0.4F + 0.8F);
            BlockState blockstate1 = ((FireBlock) Blocks.FIRE).getStateForPlacement(iworld, blockpos1);
            iworld.setBlockState(blockpos1, blockstate1, 11);
            ItemStack itemstack = spellContext.getItem();
            if (playerentity instanceof ServerPlayerEntity) {
                CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayerEntity) playerentity, blockpos1, itemstack);
            }

            return new SpellCastResult(ActionResultType.SUCCESS);
        } else {
            BlockState blockstate = iworld.getBlockState(blockpos);
            if (isUnlitCampfire(blockstate)) {
                iworld.playSound(playerentity, blockpos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, iworld.getRandom().nextFloat() * 0.4F + 0.8F);
                iworld.setBlockState(blockpos, blockstate.with(BlockStateProperties.LIT, true), 11);
                return new SpellCastResult(ActionResultType.SUCCESS);

            } else {
                return new SpellCastResult(ActionResultType.FAIL);
            }
        }
    }
}