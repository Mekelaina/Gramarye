package com.mekelaina.gramarye.blocks.tiles;

import com.mekelaina.gramarye.Gramarye;
import com.mekelaina.gramarye.blocks.ModBlocks;
import com.mekelaina.gramarye.blocks.containers.ObeliskContainer;
import com.mekelaina.gramarye.items.ModItems;
import com.mekelaina.gramarye.util.CustomEnergyStorage;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
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
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class ObeliskTile extends TileEntity implements ITickableTileEntity, INamedContainerProvider {

    private LazyOptional<IItemHandler> handler = LazyOptional.of(this::createHandler);
    private LazyOptional<IEnergyStorage> energy = LazyOptional.of(this::createEnergy);

    private int counter;
    private int inputType=0;
    private ItemStack lastItem;
    private double speed = 2;
    private double translation = 0;
    private double lastTranslation = 0;

    private Random random = new Random();

    public ObeliskTile() {
        super(ModTileEntities.OBELISK_TILE.get());
    }

    @Override
    public void tick() {
        if(world.isRemote()) {
            return;
        }

        if(counter > 0) {
            counter--;
            if(counter <= 0) {
                energy.ifPresent(e -> ((CustomEnergyStorage)e).addEnergy(getXpPerInput()));
                inputType = 0;
            }
            markDirty();
        }

        if(counter <=0) {
            handler.ifPresent(handler1 -> {
                ItemStack stack = handler1.getStackInSlot(0);
                if(stack.getItem() == Items.EXPERIENCE_BOTTLE || stack.getItem() == ModItems.CRYSTAL.get()
                        || stack.getItem() == ModBlocks.CRYSTALBLOCK.get().asItem()) {
                    lastItem = stack.copy();
                    handler1.extractItem(0, 1, false);
                    counter = 20;
                    markDirty();
                }
            });
        }


        sendOutPower();

    }

    private void sendOutPower() {
        energy.ifPresent(e -> {
            AtomicInteger capacity = new AtomicInteger(e.getEnergyStored());
            if(capacity.get() > 0) {
                for (Direction direction : Direction.values()) {
                    TileEntity tileEntity = world.getTileEntity(pos.offset(direction));
                    if(tileEntity != null) {
                        boolean doContinue = tileEntity.getCapability(CapabilityEnergy.ENERGY, direction).map(handler -> {
                            if(handler.canReceive()) {
                                int received = handler.receiveEnergy(Math.min(capacity.get(), 100), false);
                                capacity.addAndGet(-received);
                                ((CustomEnergyStorage)e).consumeEnergy(received);
                                markDirty();
                                return capacity.get() > 0;
                            } else {
                                return true;
                            }
                        }).orElse(true);
                        if(!doContinue) {
                            return;
                        }
                    }
                }
            }
        });
    }

    @Override
    public void read(CompoundNBT compound) {
        CompoundNBT invTag = compound.getCompound("inv");
        handler.ifPresent(handler1 -> ((INBTSerializable<CompoundNBT>)handler1).deserializeNBT(invTag));
        CompoundNBT energyTag = compound.getCompound("experion");
        energy.ifPresent(energy1 -> ((CustomEnergyStorage)energy1).setEnergy(energyTag.getInt("experion")));
        CompoundNBT speedTag = compound.getCompound("speed");
        this.speed = speedTag.getDouble("speed");
        super.read(compound);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        handler.ifPresent(h -> {
            CompoundNBT compoundNBT = ((INBTSerializable<CompoundNBT>)h).serializeNBT();
            compound.put("inv", compoundNBT);
        });
        energy.ifPresent(h -> {
            CompoundNBT compoundNBT = ((INBTSerializable<CompoundNBT>)h).serializeNBT();
            compound.put("experion", compoundNBT);
        });
        compound.putDouble("speed", this.speed);
        return super.write(compound);
    }

    private ItemStackHandler createHandler() {
        return new ItemStackHandler(1){
            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                if(stack.getItem() == Items.EXPERIENCE_BOTTLE || stack.getItem() == ModItems.CRYSTAL.get()
                        || stack.getItem() == ModBlocks.CRYSTALBLOCK.get().asItem()) {
                    return true;
                }
                return false;
            }

            @Override
            protected void onContentsChanged(int slot) {
                markDirty();
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                if(stack.getItem() != ModItems.CRYSTAL.get() && stack.getItem() != Items.EXPERIENCE_BOTTLE
                && stack.getItem() != ModBlocks.CRYSTALBLOCK.get().asItem()){
                    return stack;
                }
                return super.insertItem(slot, stack, simulate);
            }
        };
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return handler.cast();
        }
        if(cap == CapabilityEnergy.ENERGY) {
            return energy.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public ITextComponent getDisplayName() {
        return new TranslationTextComponent("block.gramarye.obelisk");
    }

    @Nullable
    @Override
    public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return new ObeliskContainer(i, world, pos, playerInventory, playerEntity);
    }

    private IEnergyStorage createEnergy() {
        return new CustomEnergyStorage(2048, 0);
    }

    private int getXpPerInput()
    {
       if(lastItem != null) {
           if(lastItem.getItem() == Items.EXPERIENCE_BOTTLE) {
               lastItem = ItemStack.EMPTY;
               return 3 + this.world.rand.nextInt(5) + this.world.rand.nextInt(5);
           }
           if(lastItem.getItem() == ModItems.CRYSTAL.get()) {
               lastItem = ItemStack.EMPTY;
               return 20;
           }
           if(lastItem.getItem() == ModBlocks.CRYSTALBLOCK.get().asItem()) {
               lastItem = ItemStack.EMPTY;
               return 100;
           }
       }
       return 0;

    }

    private void calculateTranslation() {
        this.lastTranslation = this.translation;
        this.translation += (float)(Math.sin(speed * counter));
    }

    public double getTranslation() {
        return translation;
    }

    public int getCapacity() {
        AtomicInteger temp = new AtomicInteger();
         energy.ifPresent(e -> {
             temp.set(e.getMaxEnergyStored());});
         return temp.get();
    }

    private boolean isItemValid(ItemStack stack) {
        //Gramarye.LOGGER.debug("called from is item valid");
        if(stack.getItem() == Items.EXPERIENCE_BOTTLE) {
           inputType = 1;
            Gramarye.LOGGER.debug("xp bottle " + inputType);
           return true;
       } else if(stack.getItem() == ModItems.CRYSTAL.get()) {
           inputType = 2;
            Gramarye.LOGGER.debug("crystal " + inputType);
           return true;
       } else if(stack.getItem() == ModBlocks.CRYSTALBLOCK.get().asItem()) {
            inputType = 3;
            Gramarye.LOGGER.debug("cryatal blcok  " + inputType);
            return true;
        }
        return false;
    }
}
