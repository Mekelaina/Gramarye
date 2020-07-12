package com.mekelaina.gramarye.items;

import com.mekelaina.gramarye.Gramarye;
import com.mekelaina.gramarye.blocks.ModBlocks;
import com.mekelaina.gramarye.blocks.Obelisk;
import com.mekelaina.gramarye.blocks.tiles.ObeliskTile;
import com.mekelaina.gramarye.entities.ModEntities;
import com.mekelaina.gramarye.entities.SpellBoltEntity;
import com.mekelaina.gramarye.util.IExperiaLinkable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.List;

public class Wand extends Item {

    private BlockPos toLinkLoc;
    private int toLinkDim;
    private boolean isBound = false;

    public Wand(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {

        World world = context.getWorld();
        BlockPos pos = context.getPos();
        PlayerEntity playerIn = context.getPlayer();
        ItemStack stack = context.getItem();
        Hand handIn = context.getHand();
        CompoundNBT nbt = stack.getTag();

        if(playerIn.isSneaking()){
            if(nbt == null){
                nbt = new CompoundNBT();
                nbt.putBoolean("bound", false);
                stack.setTag(nbt);
            }
            if(wasUsedOnObelisk(pos, world)){
                   toLinkLoc = pos;
                   toLinkDim = world.dimension.getType().getId();
                   toLinkDim = context.getWorld().getDimension().getType().getId();
                   nbt.putBoolean("bound", true);
                   nbt.putInt("posx", toLinkLoc.getX());
                   nbt.putInt("posy", toLinkLoc.getY());
                   nbt.putInt("posz", toLinkLoc.getZ());
                   nbt.putInt("posdim", toLinkDim);
                   isBound = true;
                   stack.setTag(nbt);
                   playerIn.setHeldItem(handIn, stack);

            }
            if(wasUsedOnLinkable(pos, world)){
                IExperiaLinkable linkable = (IExperiaLinkable)world.getTileEntity(pos);
                //linkable.link(toLinkLoc, toLinkDim);
                Gramarye.LOGGER.debug(linkable.link(toLinkLoc, toLinkDim));
            }

        }
    
         return ActionResultType.SUCCESS;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        CompoundNBT nbt = stack.getTag();
        if(nbt != null && nbt.contains("bound") && nbt.getBoolean("bound") == true){
            tooltip.add(new StringTextComponent("Stored Obelisk="));
            tooltip.add(new StringTextComponent("x: " + nbt.getInt("posx")));
            tooltip.add(new StringTextComponent("y: " + nbt.getInt("posy")));
            tooltip.add(new StringTextComponent("z: " + nbt.getInt("posz")));
            tooltip.add(new StringTextComponent("Dim: " + DimensionType.getById(nbt.getInt("posdim"))));
        } else {
            tooltip.add(new StringTextComponent("Sneak right click on an Obelisk"));
            tooltip.add(new StringTextComponent("to store it for linking"));
        }
    }

    private boolean wasUsedOnObelisk(BlockPos posIn, World worldIn){
        if(worldIn.getBlockState(posIn).getBlock() instanceof Obelisk && worldIn.getBlockState(posIn).hasTileEntity()){
            return true;
        }
        return false;
    }

    private boolean wasUsedOnLinkable(BlockPos posIn, World worldIn){
        if(worldIn.getBlockState(posIn).hasTileEntity() && worldIn.getTileEntity(posIn) instanceof IExperiaLinkable){
            return true;
        }
        return false;
    }
}
