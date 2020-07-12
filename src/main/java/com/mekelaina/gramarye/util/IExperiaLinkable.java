package com.mekelaina.gramarye.util;

import net.minecraft.util.math.BlockPos;

public interface IExperiaLinkable {

    public abstract boolean link(BlockPos pos, int dimID);

    public abstract void unlink();

    public abstract boolean isLinkValid();


}
