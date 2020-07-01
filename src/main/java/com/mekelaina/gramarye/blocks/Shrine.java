package com.mekelaina.gramarye.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

import java.util.stream.Stream;

public class Shrine extends Block {

    public Shrine(Properties properties) {
        super(properties);
    }

    public static final VoxelShape bounds = Stream.of(
            Block.makeCuboidShape(2, 0, 2, 14, 1, 14),
            Block.makeCuboidShape(2.2249999999999996, 12, 2.2249999999999996, 13.775, 14, 13.775),
            Block.makeCuboidShape(5.975, 14, 5.975, 10.025, 16, 9.8),
            Block.makeCuboidShape(3.4749999999999996, 1, 3.5, 12.5, 12, 12.525)
    ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return bounds;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}
