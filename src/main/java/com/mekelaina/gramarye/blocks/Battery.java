package com.mekelaina.gramarye.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class Battery extends Block {

    public static final VoxelShape bounds = Block.makeCuboidShape(3.75, 0, 3.75, 12.25, 14.5, 12.25);

    public Battery(Properties properties) {
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

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(BlockStateProperties.FACING);
    }

    public static Direction getFacingFromEntity(BlockPos clickedBlock, LivingEntity placer) {

        return Direction.getFacingFromVector((float)(placer.posX - clickedBlock.getX()), (float)(placer.posY - clickedBlock.getY()),
                (float)(placer.posZ - clickedBlock.getZ()));
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        if(placer != null){
            worldIn.setBlockState(pos, state.with(BlockStateProperties.FACING, getFacingFromEntity(pos, placer)), 2);
        }
    }
}
