package com.mekelaina.gramarye.network;

import com.mekelaina.gramarye.blocks.Obelisk;
import com.mekelaina.gramarye.blocks.tiles.ObeliskTile;
import com.mekelaina.gramarye.util.CustomEnergyStorage;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.Objects;
import java.util.function.Supplier;

public class PacketObelisk {


    private final DimensionType type;
    private final BlockPos pos;

    private final int obeliskXp;
    private final int playerXp;

    public PacketObelisk(DimensionType type, BlockPos pos, int obeliskXp, int playerXp) {

        this.type = type;
        this.pos = pos;
        this.obeliskXp = obeliskXp;
        this.playerXp = playerXp;
    }

    public PacketObelisk(PacketBuffer buffer) {
        type = DimensionType.getById(buffer.readInt());
        pos = buffer.readBlockPos();
        obeliskXp = buffer.readInt();
        playerXp = buffer.readInt();
    }

    public void toBytes(PacketBuffer buffer) {
        buffer.writeInt(type.getId());
        buffer.writeBlockPos(pos);
        buffer.writeInt(obeliskXp);
        buffer.writeInt(playerXp);
    }

    public void handle(Supplier<NetworkEvent.Context> contextSupplier) {
        contextSupplier.get().enqueueWork(() -> {
            ServerWorld serverWorld = Objects.requireNonNull(contextSupplier.get().getSender().world.getServer().getWorld(type));
            ServerPlayerEntity playerEntity = Objects.requireNonNull(contextSupplier.get().getSender());
            TileEntity tile = serverWorld.getTileEntity(pos);
            if(tile instanceof ObeliskTile){
                ObeliskTile temp = (ObeliskTile)tile;
                temp.getCapability(CapabilityEnergy.ENERGY, null).ifPresent(iEnergyStorage -> ((CustomEnergyStorage)iEnergyStorage).setEnergy(obeliskXp));
                if(!playerEntity.isCreative()) {
                    playerEntity.giveExperiencePoints(-(playerEntity.experienceTotal-playerXp));
                }
            }
        });
        contextSupplier.get().setPacketHandled(true);
    }
}
