package com.mekelaina.gramarye.gui.screens;

import com.mekelaina.gramarye.Gramarye;
import com.mekelaina.gramarye.gui.containers.ObeliskContainer;
import com.mekelaina.gramarye.blocks.tiles.ObeliskTile;
import com.mekelaina.gramarye.gui.element.CustomButton;
import com.mekelaina.gramarye.gui.element.WidgetWrapper;
import com.mekelaina.gramarye.network.Networking;
import com.mekelaina.gramarye.network.PacketObelisk;
import com.mekelaina.gramarye.util.XPMathUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.awt.*;

public class ObeliskScreen extends ContainerScreen<ObeliskContainer> {

    private ResourceLocation GUI ;
    private ObeliskTile obeliskTile;
    private Rectangle xp;

    private CustomButton depositLvl;
    private CustomButton retrieveLvl;
    private CustomButton depositTenLvl;
    private CustomButton retrieveTenLvl;
    private CustomButton depositAllLvl;
    private CustomButton retrieveAllLvl;

    public ObeliskScreen(ObeliskContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
        this.obeliskTile = screenContainer.getTileEntity();
        xp = new Rectangle(135, 5, 36, 68);

    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        super.render(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
       // this.font.drawString(this.title.getFormattedText(), 8.0f, 6.0f, 4210752);
        //this.font.drawString(this.playerInventory.getDisplayName().getFormattedText(), 8.0f, (float)(this.ySize - 94), 4210752);
       // drawString(Minecraft.getInstance().fontRenderer, "Energy: " + container.getEnergy(), this.xSize - (this.xSize / 2), 6, 0x4AEDD9);
        //renderHoveredToolTip(mouseX - this.xSize - (this.xSize / 2), mouseY);

        String toDraw = "Xp: " + this.container.getEnergy() + " Lvl: " + XPMathUtils.getClosestLevelByXp(this.container.getEnergy());

       // Gramarye.LOGGER.debug(font.getStringWidth(toDraw));
        this.font.drawString(toDraw, (float)(this.xSize - (font.getStringWidth(toDraw) + 4)), (float)(this.ySize - 92), 0xF5FF8F);

        /*//Gramarye.LOGGER.debug("mouse x: " + mouseX + "mouse y: " + mouseY);
        if(isOverXpBar(mouseX, mouseY)) {
            blit(mouseX, mouseY, 129, 166, 32, 20);
            drawString(Minecraft.getInstance().fontRenderer, toDraw, mouseX , mouseY, 0x4AEDD9);
        }*/
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
       // GlStateManager.color4f(1f, 1f, 1f, 1f);

        this.minecraft.getTextureManager().bindTexture(GUI);
        int relX = (this.width - this.xSize) / 2;
        int relY = (this.height - this.ySize) / 2;
        this.blit(relX, relY, 0, 0, this.xSize, this.ySize);



        int k = this.getExperienceStoredScaled(64);
       // Gramarye.LOGGER.debug(64);
        //k += (k-64) ;
        blit(this.guiLeft + 71, this.guiTop + 7 + (64-k), 176, 1+(64-k), 32, k);
    }

    private int getExperienceStoredScaled(int pixels) {
        int i = this.obeliskTile.getEnergy();
        int j = this.obeliskTile.getCapacity();
        return i != 0 && j != 0 ? i * pixels / j : 0;
    }

    private boolean isOverXpBar(int mouseX, int mouseY) {
        return mouseX >= this.guiLeft + this.xp.x && mouseY >= this.guiTop + xp.y && mouseX < xp.x + xp.width && mouseY < xp.y + xp.height;
    }

    @Override
    protected void init() {
        super.init();
        GUI = new ResourceLocation(Gramarye.MODID, "textures/gui/test_gui2.png");
        WidgetWrapper[] temp = new WidgetWrapper[] {new WidgetWrapper(176, 65, 20, 20), new WidgetWrapper(176, 85, 20, 20),
                new WidgetWrapper(176, 105, 20, 20), new WidgetWrapper(176, 125, 20, 20)};
        depositLvl = new CustomButton(this.guiLeft + 118, this.guiTop + 5, 20, 20, temp, p_onPress_1_ -> insertOneLevel());
        depositLvl.setTexture(GUI);
        this.addButton(depositLvl);

        temp = new WidgetWrapper[] {new WidgetWrapper(176, 65, 20, 20), new WidgetWrapper(176, 85, 20, 20),
                new WidgetWrapper(176, 105, 20, 20), new WidgetWrapper(176, 145, 20, 20)};
        retrieveLvl = depositLvl = new CustomButton(this.guiLeft + 145, this.guiTop + 5, 20, 20, temp, p_onPress_1_ -> retrieveOneLevel());
        retrieveLvl.setTexture(GUI);
        this.addButton(retrieveLvl);

        temp = new WidgetWrapper[] {new WidgetWrapper(176, 65, 20, 20), new WidgetWrapper(176, 85, 20, 20),
                new WidgetWrapper(176, 105, 20, 20), new WidgetWrapper(196, 65, 20, 20)};
        depositTenLvl = new CustomButton(this.guiLeft + 118, this.guiTop + 29, 20, 20, temp, p_onPress_1_ -> insertTenLevels());
        depositTenLvl.setTexture(GUI);
        this.addButton(depositTenLvl);

        temp = new WidgetWrapper[] {new WidgetWrapper(176, 65, 20, 20), new WidgetWrapper(176, 85, 20, 20),
                new WidgetWrapper(176, 105, 20, 20), new WidgetWrapper(196, 85, 20, 20)};
        retrieveTenLvl = new CustomButton(this.guiLeft + 145, this.guiTop + 29, 20, 20, temp, p_onPress_1_ -> retrieveTenLevels());
        retrieveTenLvl.setTexture(GUI);
        this.addButton(retrieveTenLvl);

        temp = new WidgetWrapper[] {new WidgetWrapper(176, 65, 20, 20), new WidgetWrapper(176, 85, 20, 20),
                new WidgetWrapper(176, 105, 20, 20), new WidgetWrapper(196, 105, 20, 20)};
        depositAllLvl = new CustomButton(this.guiLeft + 118, this.guiTop + 53, 20, 20, temp, p_onPress_1_ -> insertAllLevels());
        depositAllLvl.setTexture(GUI);
        this.addButton(depositAllLvl);

        temp = new WidgetWrapper[] {new WidgetWrapper(176, 65, 20, 20), new WidgetWrapper(176, 85, 20, 20),
                new WidgetWrapper(176, 105, 20, 20), new WidgetWrapper(196, 125, 20, 20)};
        retrieveAllLvl = new CustomButton(this.guiLeft + 145, this.guiTop + 53, 20, 20, temp, p_onPress_1_ -> retrieveAllLevels());
        retrieveAllLvl.setTexture(GUI);
        this.addButton(retrieveAllLvl);
    }

    private void initButtons() {



        //this.addButton(new Button(this.guiLeft + 84, this.guiTop + 5, 20, 20, "Boop", p_onPress_1_ -> sendMessage()));
    }

    private void sendMessage(){
        this.container.getPlayer().sendStatusMessage(new StringTextComponent("test"), false);
        //this.container.getPlayer().e
    }

    private void insertOneLevel(){
        PlayerEntity player = container.getPlayer();
        Gramarye.LOGGER.debug("player");
        if(!player.isCreative()) {
            int playerLvl = player.experienceLevel;
            int playerTotal = player.experienceTotal;
            int obeliskLast = this.obeliskTile.getEnergy();
            if(playerTotal > 0 && playerLvl > 0) {
                int currentLvlsWorth = XPMathUtils.getXpToLevelUp(playerLvl-1);
               this.obeliskTile.insertXp(currentLvlsWorth);

               player.giveExperiencePoints(-(this.obeliskTile.getEnergy() - obeliskLast));


            } else if(playerTotal > 0 && playerLvl <= 0) {

                this.obeliskTile.insertXp(playerTotal);
                player.giveExperiencePoints(-playerTotal);

            }
        } else {
            int currentLvl = XPMathUtils.getClosestLevelByXp(this.obeliskTile.getEnergy());
            int toAdd = XPMathUtils.totalXpByLevel(currentLvl+1) - this.obeliskTile.getEnergy();
            this.obeliskTile.insertXp(toAdd);

        }
        Networking.INSTANCE.sendToServer(new PacketObelisk(minecraft.player.dimension, obeliskTile.getPos(),
                this.obeliskTile.getEnergy(), player.experienceTotal));

    }
    public void retrieveOneLevel() {
        PlayerEntity player = container.getPlayer();
        Gramarye.LOGGER.debug("player");
        if(!player.isCreative()) {
            int obeliskTotal = this.container.getEnergy();
            int obeliskLvl = XPMathUtils.getClosestLevelByXp(obeliskTotal);
            int obeliskLast = this.container.getEnergy();
            if(obeliskTotal > 0 && obeliskLvl > 0) {
                int currentLvlsWorth = XPMathUtils.getXpToLevelUp(obeliskLvl-1);
                this.obeliskTile.removeXp(currentLvlsWorth);
                player.giveExperiencePoints((this.container.getEnergy()-obeliskLast));

            } else if(obeliskTotal > 0 && obeliskLvl <= 0) {

                this.obeliskTile.removeXp(obeliskTotal);
                player.giveExperiencePoints(obeliskTotal);

            }
        } else {
            int currentLvl = XPMathUtils.getClosestLevelByXp(this.obeliskTile.getEnergy());
            int toRemove =  this.obeliskTile.getEnergy() - XPMathUtils.totalXpByLevel(currentLvl-1);
            this.obeliskTile.removeXp(toRemove);

        }

        Networking.INSTANCE.sendToServer(new PacketObelisk(minecraft.player.dimension, obeliskTile.getPos(),
                this.obeliskTile.getEnergy(), player.experienceTotal));
    }

    private void insertTenLevels() {
        PlayerEntity player = container.getPlayer();
        int obeliskLast = this.container.getEnergy();
        if(!player.isCreative()) {
            int playerLvl = player.experienceLevel;
            int playerTotal = player.experienceTotal;

            if(playerLvl >= 10){
                int temp = XPMathUtils.getXpDifferenceBetweenLevel(playerLvl, playerLvl-10);
                this.obeliskTile.insertXp(temp);
            } else {
                this.obeliskTile.insertXp(playerTotal);
            }
            player.giveExperiencePoints(-(this.obeliskTile.getEnergy() - obeliskLast));

        } else {
            int obeliskLevel = XPMathUtils.getClosestLevelByXp(obeliskLast);
            int target = XPMathUtils.getXpDifferenceBetweenLevel(obeliskLevel+10, obeliskLevel);
            this.obeliskTile.insertXp(target);
        }

        Networking.INSTANCE.sendToServer(new PacketObelisk(minecraft.player.dimension, obeliskTile.getPos(),
                this.obeliskTile.getEnergy(), player.experienceTotal));
    }

    private void retrieveTenLevels() {
        PlayerEntity player = container.getPlayer();
        int obeliskLast = this.container.getEnergy();
        if(!player.isCreative()) {
            int obeliskLvl = XPMathUtils.getClosestLevelByXp(obeliskLast);
            if(obeliskLvl >= 10){
                int temp = XPMathUtils.getXpDifferenceBetweenLevel(obeliskLvl, obeliskLvl-10);
                this.obeliskTile.removeXp(temp);
            } else {
                this.obeliskTile.removeXp(obeliskLast);
            }
            player.giveExperiencePoints((obeliskLast - this.container.getEnergy()));
        } else {
            int obeliskLevel = XPMathUtils.getClosestLevelByXp(obeliskLast);
            int target = XPMathUtils.getXpDifferenceBetweenLevel(obeliskLevel, obeliskLevel-10);
            this.obeliskTile.removeXp(target);
        }

        Networking.INSTANCE.sendToServer(new PacketObelisk(minecraft.player.dimension, obeliskTile.getPos(),
                this.obeliskTile.getEnergy(), player.experienceTotal));
    }

    private void insertAllLevels(){
        PlayerEntity player = container.getPlayer();
        int obeliskLast = this.container.getEnergy();

        if(!player.isCreative()){
            this.obeliskTile.insertXp(player.experienceTotal);
            player.giveExperiencePoints(-(this.obeliskTile.getEnergy() - obeliskLast));
        } else {
            this.obeliskTile.insertXp(this.obeliskTile.getCapacity());
        }

        Networking.INSTANCE.sendToServer(new PacketObelisk(minecraft.player.dimension, obeliskTile.getPos(),
                this.obeliskTile.getEnergy(), player.experienceTotal));
    }

    private void retrieveAllLevels(){
        PlayerEntity player = container.getPlayer();
        int obeliskLast = this.container.getEnergy();

        if(!player.isCreative()){
            this.obeliskTile.removeXp(obeliskLast);
            player.giveExperiencePoints(obeliskLast);
        } else {
            this.obeliskTile.removeXp(obeliskLast);
        }

        Networking.INSTANCE.sendToServer(new PacketObelisk(minecraft.player.dimension, obeliskTile.getPos(),
                this.obeliskTile.getEnergy(), player.experienceTotal));
    }
}
