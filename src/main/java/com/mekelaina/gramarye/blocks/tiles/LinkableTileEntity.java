package com.mekelaina.gramarye.blocks.tiles;

import com.mekelaina.gramarye.util.IExperiaLinkable;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;

public abstract class LinkableTileEntity extends TileEntity implements IExperiaLinkable {

    protected BlockPos linkedBlock;
    protected int linkedDimension;
    protected BlockPos currentPosition;
    protected int currentDimension;
    protected boolean isLinked = false;
    protected boolean hasBeenInited = false;

    public LinkableTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    @Override
    public boolean link(BlockPos pos, int dimID) {
        if(pos.withinDistance(currentPosition, 32.0) && dimID == currentDimension){
            linkedBlock = pos;
            linkedDimension = dimID;
            isLinked = true;
            return true;
        }
        return false;
    }

    @Override
    public void unlink() {
        linkedBlock = pos;
        linkedDimension = 0;
        isLinked = false;
    }

    @Override
    public boolean isLinkValid() {
        return true;
    }

    protected void init(){
        this.currentPosition = pos;
        this.currentDimension = world.dimension.getType().getId();
        hasBeenInited = true;
    }
}
