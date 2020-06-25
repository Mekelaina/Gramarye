package com.mekelaina.gramarye.Spell;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/*
 * I would just inherit from ItemUseContext, but rayTraceResult isn't public and doesn't have a direct getter, so I can't implement it's super constructor.
 * Thus you get the "I can't change vanilla files" discount SpellCastContext extends ItemUseContext.
 */
public class SpellCastContext {
    protected final ItemUseContext parentContext;
    protected final int availableMana;

    public SpellCastContext(ItemUseContext context, int availableMana) {
        this.parentContext = context;
        this.availableMana = availableMana;
    }

    public BlockPos getPos() {
        return this.parentContext.getPos();
    }

    public Direction getFace() {
        return this.parentContext.getFace();
    }

    public Vec3d getHitVec() {
        return this.parentContext.getHitVec();
    }

    public boolean func_221533_k() {
        return this.parentContext.func_221533_k();
    }

    public ItemStack getItem() {
        return this.parentContext.getItem();
    }

    @Nullable
    public PlayerEntity getPlayer() {
        return this.parentContext.getPlayer();
    }

    public Hand getHand() {
        return this.parentContext.getHand();
    }

    public World getWorld() {
        return this.parentContext.getWorld();
    }

    public Direction getPlacementHorizontalFacing() {
        PlayerEntity player = this.getPlayer();
        return player == null ? Direction.NORTH : player.getHorizontalFacing();
    }

    public boolean isPlacerSneaking() {
        PlayerEntity player = this.getPlayer();
        return player != null && player.isSneaking();
    }

    public float getPlacementYaw() {
        PlayerEntity player = this.getPlayer();
        return player == null ? 0.0F : player.rotationYaw;
    }
}
