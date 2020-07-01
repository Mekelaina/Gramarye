package com.mekelaina.gramarye.client.render;

import com.mekelaina.gramarye.blocks.tiles.ChargerTile;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.Texture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.client.ForgeHooksClient;
import org.lwjgl.opengl.GL11;

public class ChargerTileEntityRenderer extends TileEntityRenderer<ChargerTile> {

    public ChargerTileEntityRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(ChargerTile tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {

    }

   /*    @Override
    public void render(ChargerTile tileEntityIn, double x, double y, double z, float partialTicks, int destroyStage) {

        ItemStack stack = new ItemStack(Items.DIAMOND);

        if(!stack.isEmpty()){
            GlStateManager.enableRescaleNormal();
            GlStateManager.alphaFunc(GL11.GL_GREATER, 0.1f);
            GlStateManager.enableBlend();
            RenderHelper.enableStandardItemLighting();
            GlStateManager.blendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0);
            GlStateManager.pushMatrix();

            //double offset = Math.sin((tileEntityIn.getWorld().getT))

            GlStateManager.translated(x + 0.5, y + 0.6, z + 0.5);
            GlStateManager.rotated((tileEntityIn.getWorld().getGameTime() + partialTicks) * 1.5, 0, 1, 0);

            IBakedModel model = Minecraft.getInstance().getItemRenderer().getItemModelWithOverrides(stack, tileEntityIn.getWorld(), null);
            model = ForgeHooksClient.handleCameraTransforms(model, ItemCameraTransforms.TransformType.GROUND, false);

            Minecraft.getInstance().getItemRenderer().renderItem(stack, model);

            GlStateManager.popMatrix();
            GlStateManager.disableRescaleNormal();
            GlStateManager.disableBlend();
        }

        super.render(tileEntityIn, x, y, z, partialTicks, destroyStage);
    }*/

    private void renderItem(ItemStack stack){

    }
}
