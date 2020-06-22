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
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class Wand extends Item {

    public Wand(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        Vec3d forward = playerIn.getForward();
        SpellBoltEntity spellBoltEntity = new SpellBoltEntity(worldIn, playerIn, forward.getX() + 2, forward.getY() ,
                forward.getZ() + 2);

        worldIn.addEntity(spellBoltEntity);

        return new ActionResult<ItemStack>(ActionResultType.SUCCESS, playerIn.getHeldItem(handIn));
    }
}
