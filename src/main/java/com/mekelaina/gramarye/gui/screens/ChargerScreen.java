package com.mekelaina.gramarye.gui.screens;

import com.mekelaina.gramarye.Gramarye;
import com.mekelaina.gramarye.blocks.tiles.ChargerTile;
import com.mekelaina.gramarye.gui.containers.ChargerContainer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class ChargerScreen extends ContainerScreen<ChargerContainer> {

    private ResourceLocation GUI;
    private ChargerTile chargerTile;

    public ChargerScreen(ChargerContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
        this.chargerTile = screenContainer.getTileEntity();
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        this.minecraft.getTextureManager().bindTexture(GUI);
        int relX = (this.width - this.xSize) / 2;
        int relY = (this.height - this.ySize) / 2;
        this.blit(relX, relY, 0, 0, this.xSize, this.ySize);
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        super.render(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void init() {
        super.init();
        GUI = new ResourceLocation(Gramarye.MODID, "textures/gui/charger_gui.png");
    }
}



