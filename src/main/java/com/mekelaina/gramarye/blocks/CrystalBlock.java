package com.mekelaina.gramarye.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.*;
import net.minecraft.world.IBlockReader;

import java.util.stream.Stream;

public class CrystalBlock extends Block {



    private static final VoxelShape bounds = Stream.of(
            Block.makeCuboidShape(-1.625, 0.010000000000001563, 3.4750000000000014, 17.625, 11.475, 12.475000000000001),
            Block.makeCuboidShape(3.5, 0.010000000000001563, -1.6499999999999986, 12.5, 11.475, 17.6),
            Block.makeCuboidShape(3.5, 0.010000000000001563, 3.5, 12.5, 19.45, 12.5)
    ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();


    public CrystalBlock(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return bounds;
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }
}
