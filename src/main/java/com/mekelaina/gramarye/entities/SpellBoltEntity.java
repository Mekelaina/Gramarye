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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

public class SpellBoltEntity extends AbstractBoltEntity2{

    protected boolean doesDamage = false;
    protected float damageAmt = 0f;
    protected DamageSource damageSource = DamageSource.GENERIC;

    public SpellBoltEntity(EntityType<SpellBoltEntity> entityType, World world) {
        super(entityType, world);

    }

    @OnlyIn(Dist.CLIENT)
    public SpellBoltEntity(World worldIn, double x, double y, double z, double accelX, double accelY, double accelZ) {
        super(ModEntities.SPELLBOLT.get(), x, y, z, accelX, accelY, accelZ, worldIn);
    }

    public SpellBoltEntity(World worldIn, LivingEntity shooter, double accelX, double accelY, double accelZ) {
        super(ModEntities.SPELLBOLT.get(), shooter, accelX, accelY, accelZ, worldIn);
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
