package com.mekelaina.gramarye.gui.containers;

import com.mekelaina.gramarye.blocks.ModBlocks;
import com.mekelaina.gramarye.blocks.tiles.ChargerTile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

import javax.annotation.Nullable;

public class ChargerContainer extends ExtendedContainer {

    public ChargerContainer(int windowId, World world, BlockPos pos, PlayerInventory playerInventory, PlayerEntity player) {
        super(ModContainers.CHARGER_CONTAINER.get(), windowId);
        tileEntity = world.getTileEntity(pos);
        this.playerEntity = player;
        this.playerInventory = new InvWrapper(playerInventory);
        tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(handler -> {
            addSlot(new SlotItemHandler(handler, 0, 81, 33));
        });
        layoutPlayerInventorySlots(8, 84);
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return isWithinUsableDistance(IWorldPosCallable.of(tileEntity.getWorld(), tileEntity.getPos()), playerIn, ModBlocks.CHARGER.get());
    }

    public ChargerTile getTileEntity(){
        return (ChargerTile)tileEntity;
    }
}

