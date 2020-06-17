package com.mekelaina.gramarye.blocks.screens;

import com.mekelaina.gramarye.Gramarye;
import com.mekelaina.gramarye.blocks.containers.ObeliskContainer;
import com.mekelaina.gramarye.blocks.tiles.ObeliskTile;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class ObeliskScreen extends ContainerScreen<ObeliskContainer> {

    private ResourceLocation GUI = new ResourceLocation(Gramarye.MODID, "textures/gui/test_gui.png");
    private ObeliskTile obeliskTile;

    public ObeliskScreen(ObeliskContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
        this.obeliskTile = screenContainer.getTileEntity();
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        super.render(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        this.font.drawString(this.title.getFormattedText(), 8.0f, 6.0f, 4210752);
        this.font.drawString(this.playerInventory.getDisplayName().getFormattedText(), 8.0f, (float)(this.ySize - 94), 4210752);
       // drawString(Minecraft.getInstance().fontRenderer, "Energy: " + container.getEnergy(), this.xSize - (this.xSize / 2), 6, 0x4AEDD9);
        //renderHoveredToolTip(mouseX - this.xSize - (this.xSize / 2), mouseY);
        Gramarye.LOGGER.debug("mouse x: " + mouseX + "mouse y: " + mouseY);
        if((mouseX >= guiLeft+ 137 && mouseX <= guiLeft + 169) && (mouseY + guiTop >= 7 && guiTop + mouseY <= 71)) {
            blit(mouseX, mouseY,this.guiLeft + 129, this.guiTop + 166, 32, 20);
        }
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color4f(1f, 1f, 1f, 1f);
        this.minecraft.getTextureManager().bindTexture(GUI);
        int relX = (this.width - this.xSize) / 2;
        int relY = (this.height - this.ySize) / 2;
        this.blit(relX, relY, 0, 0, this.xSize, this.ySize);

        int k = this.getExperienceStoredScaled(64);
        blit(this.guiLeft + 137, this.guiTop + 7, 176, 1, 32, k);
    }

    private int getExperienceStoredScaled(int pixels) {
        int i = this.container.getEnergy();
        int j = this.obeliskTile.getCapacity();
        return i != 0 && j != 0 ? i * pixels / j : 0;
    }
}
