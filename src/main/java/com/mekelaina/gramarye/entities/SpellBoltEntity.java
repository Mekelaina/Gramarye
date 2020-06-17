package com.mekelaina.gramarye.entities;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class SpellBoltEntity extends AbstractBoltEntity{

    protected boolean doesDamage = false;
    protected float damageAmt = 0f;
    protected DamageSource damageSource = DamageSource.GENERIC;

    public SpellBoltEntity(EntityType<? extends  AbstractBoltEntity> entityType, World world) {
        super(entityType, world);

    }

/*    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }*/

    public SpellBoltEntity(EntityType<? extends AbstractBoltEntity> entityType, LivingEntity shootingEntityIn, double xAccIn, double yAccIn, double zAccIn, World world) {
        super(entityType, shootingEntityIn, xAccIn, yAccIn, zAccIn, world);
    }




    @Override
    protected void onImpact(RayTraceResult result) {
        BlockPos pos = new BlockPos(result.getHitVec().x, result.getHitVec().y, result.getHitVec().z);
        Block block = world.getBlockState(pos).getBlock();
        if(shootingEntity instanceof PlayerEntity && block != Blocks.AIR) {
            ((PlayerEntity) shootingEntity).sendStatusMessage(block.getNameTextComponent(), false);
        }
        this.remove();
    }

    @Override
    protected void registerData() {

    }

    public boolean isDoesDamage() {
        return doesDamage;
    }

    public void setDoesDamage() {
        this.doesDamage = true;
    }

    public float getDamageAmt() {
        return damageAmt;
    }

    public void setDamageAmt(float damageAmt) {
        this.damageAmt = damageAmt;
    }

    public DamageSource getDamageSource() {
        return damageSource;
    }

    public void setDamageSource(DamageSource damageSource) {
        this.damageSource = damageSource;
    }

}
