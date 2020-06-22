package com.mekelaina.gramarye.entities;

import com.mekelaina.gramarye.items.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractFireballEntity;
import net.minecraft.entity.projectile.DamagingProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.Util;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public abstract class AbstractBoltEntity2 extends DamagingProjectileEntity implements IRendersAsItem {

    private static final DataParameter<ItemStack> itemStackData = EntityDataManager.createKey(AbstractBoltEntity2.class, DataSerializers.ITEMSTACK);

    public AbstractBoltEntity2(EntityType<? extends DamagingProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    public AbstractBoltEntity2(EntityType<? extends DamagingProjectileEntity> entityType, double x, double y, double z, double xAcc, double yAcc, double zAcc, World world) {
        super(entityType, x, y, z, xAcc, yAcc, zAcc, world);
    }

    public AbstractBoltEntity2(EntityType<? extends DamagingProjectileEntity> entityType, LivingEntity livingEntity, double xAcc, double yAcc, double zAcc, World world) {
        super(entityType, livingEntity, xAcc, yAcc, zAcc, world);
    }

    public void initItemStackData(ItemStack itemStack) {
        if (itemStack.getItem() != Items.FIRE_CHARGE || itemStack.hasTag()) {
            this.getDataManager().set(itemStackData, Util.make(itemStack.copy(), (itemStack1) -> {
                itemStack1.setCount(1);
            }));
        }
    }

    protected ItemStack getItemStackDataItem() {
        return this.getDataManager().get(itemStackData);
    }

    @OnlyIn(Dist.CLIENT)
    public ItemStack getItem() {
        ItemStack itemstack = this.getItemStackDataItem();
        return itemstack.isEmpty() ? new ItemStack(ModItems.DEBUG.get()) : itemstack;
    }

    protected void registerData() {
        this.getDataManager().register(itemStackData, ItemStack.EMPTY);
    }

    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        ItemStack itemstack = this.getItemStackDataItem();
        if (!itemstack.isEmpty()) {
            compound.put("Item", itemstack.write(new CompoundNBT()));
        }

    }

    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        ItemStack itemstack = ItemStack.read(compound.getCompound("Item"));
        this.initItemStackData(itemstack);
    }

    @Override
    protected boolean isFireballFiery() {
        return false;
    }

    @Override
    protected IParticleData getParticle() {
        return ParticleTypes.ENCHANT;
    }
}
