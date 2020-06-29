package com.mekelaina.gramarye.blocks.tiles;

import com.mekelaina.gramarye.util.CustomEnergyStorage;
import com.mekelaina.gramarye.util.IEMItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.concurrent.atomic.AtomicReference;

public class ChargerTile extends TileEntity implements ITickableTileEntity {

    private LazyOptional<IItemHandler> handler = LazyOptional.of(this::createHandler);
    private LazyOptional<IEnergyStorage> energy = LazyOptional.of(this::createEnergy);

    public long lastChangeTime;

    public ChargerTile() {
        super(ModTileEntities.CHARGER_TILE.get());
    }

    @Override
    public void tick() {

    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY){
            return handler.cast();
        }
        if(cap == CapabilityEnergy.ENERGY){
            return energy.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void read(CompoundNBT compound) {
        CompoundNBT invTag = compound.getCompound("inv");
        handler.ifPresent(handler1 -> ((INBTSerializable<CompoundNBT>)handler1).deserializeNBT(invTag));
        CompoundNBT energyTag = compound.getCompound("experion");
        energy.ifPresent(iEnergyStorage -> ((CustomEnergyStorage)iEnergyStorage).setEnergy(energyTag.getInt("experion")));
        super.read(compound);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        handler.ifPresent(handler1 -> {
            CompoundNBT compoundNBT = ((INBTSerializable<CompoundNBT>)handler1).serializeNBT();
            compound.put("inv", compoundNBT);
        });
        energy.ifPresent(iEnergyStorage -> {
            CompoundNBT compoundNBT = ((INBTSerializable<CompoundNBT>)iEnergyStorage).serializeNBT();
            compound.put("experion", compoundNBT);
        });
        return super.write(compound);
    }

    private ItemStackHandler createHandler() {
        return new ItemStackHandler(1) {
            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                if(stack.getItem() instanceof IEMItem){
                    return true;
                }
                return false;
            }

            @Override
            protected void onContentsChanged(int slot) {
                if(!world.isRemote){
                    lastChangeTime = world.getGameTime();
                }
                markDirty();
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                if(stack.getItem() instanceof IEMItem){
                    return super.insertItem(slot, stack, simulate);
                }
                return stack;
            }
        };
    }

    public ItemStack getCurrentItem(){
        AtomicReference<ItemStack> stack = new AtomicReference<ItemStack>(ItemStack.EMPTY);
        handler.ifPresent(handler1 -> {
           stack.set(handler1.getStackInSlot(0));
        });

        return stack.get();
    }

    private CustomEnergyStorage createEnergy(){
        return new CustomEnergyStorage(10, 0);
    }
}
