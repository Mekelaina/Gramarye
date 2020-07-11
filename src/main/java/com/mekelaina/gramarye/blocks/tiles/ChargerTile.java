package com.mekelaina.gramarye.blocks.tiles;

import com.mekelaina.gramarye.Gramarye;
import com.mekelaina.gramarye.capabilities.CapabilityExperia;
import com.mekelaina.gramarye.gui.containers.ChargerContainer;
import com.mekelaina.gramarye.gui.containers.ObeliskContainer;
import com.mekelaina.gramarye.util.CustomEnergyStorage;
import com.mekelaina.gramarye.util.IEMItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
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

public class ChargerTile extends TileEntity implements ITickableTileEntity, INamedContainerProvider {

    private LazyOptional<IItemHandler> handler = LazyOptional.of(this::createHandler);
    private LazyOptional<IEnergyStorage> energy = LazyOptional.of(this::createEnergy);

    private int age;

    public ChargerTile() {
        super(ModTileEntities.CHARGER_TILE.get());
    }

    @Override
    public void tick() {
        if(!this.removed) {
            this.age++;
        }
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
        return new ItemStackHandler(4) {
            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                if(stack.getCapability(CapabilityExperia.CAPABILITY_EXPERIA).isPresent()){
                    return true;
                }
                return false;
            }

            @Override
            protected void onContentsChanged(int slot) {
                /*if(!world.isRemote){
                    lastChangeTime = world.getGameTime();
                }*/
                markDirty();
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                if(stack.getCapability(CapabilityExperia.CAPABILITY_EXPERIA).isPresent()){
                    //Gramarye.LOGGER.debug("gweem");
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

    public void insertItem(ItemStack stack) {
        handler.ifPresent(handler1 -> {
            handler1.insertItem(0, stack, false);
        });
    }

    public ItemStack removeItem() {
        AtomicReference<ItemStack> stack = new AtomicReference<>(ItemStack.EMPTY);
        handler.ifPresent(handler1 -> {
           stack.set(handler1.extractItem(0,1, false));
        });
        return stack.get();
    }

    private CustomEnergyStorage createEnergy(){
        return new CustomEnergyStorage(10, 0);
    }

    public int getAge() {
        return age;
    }

    @Override
    public ITextComponent getDisplayName() {
        return new TranslationTextComponent("block.gramarye.charger");
    }

    @Nullable
    @Override
    public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return new ChargerContainer(i, world, pos, playerInventory, playerEntity);
    }
}
