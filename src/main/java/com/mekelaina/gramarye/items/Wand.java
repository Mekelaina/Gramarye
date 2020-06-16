package com.mekelaina.gramarye.items;

import com.mekelaina.gramarye.entities.ModEntities;
import com.mekelaina.gramarye.entities.SpellBoltEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class Wand extends Item {

    public Wand(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        SpellBoltEntity spellBoltEntity = new SpellBoltEntity(ModEntities.SPELLBOLT.get(), playerIn, 2, 2, 2, worldIn);
        worldIn.addEntity(spellBoltEntity);

        return new ActionResult<ItemStack>(ActionResultType.SUCCESS, playerIn.getHeldItem(handIn));
    }
}
