package com.mekelaina.gramarye.gui.element;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.ReadBookScreen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;

public class CustomButton extends Button {

    protected WidgetWrapper[] buttonParts;
    protected ResourceLocation texture;
    protected boolean hasImage;
    //private boolean isClicked = false;

    public CustomButton(int widthIn, int heightIn, int width, int height, String text, IPressable onPress) {
        super(widthIn, heightIn, width, height, text, onPress);
        hasImage = false;
    }

    public CustomButton(int widthIn, int heightIn, int width, int height, WidgetWrapper[] parts, IPressable onPress) {
        super(widthIn, heightIn, width, height, "", onPress);
        this.buttonParts = parts;
        hasImage = buttonParts.length > 3 ? true : false;
    }

    public void setButtonParts(WidgetWrapper... widgetWrappers) throws IllegalArgumentException {
        if(widgetWrappers.length >= 3){
            buttonParts = new WidgetWrapper[widgetWrappers.length];
        } else {
            throw new IllegalArgumentException("Button Parts must have at least 3 elements");
        }
        int i = 0;
        for(WidgetWrapper w : widgetWrappers) {
            buttonParts[i] = w;
            i++;
        }
    }

    public void setTexture(ResourceLocation texture) {
        this.texture = texture;
    }

    @Override
    public void renderButton(int p_renderButton_1_, int p_renderButton_2_, float p_renderButton_3_) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        Minecraft.getInstance().getTextureManager().bindTexture(texture);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);

        int i = buttonParts[0].getX();
        int j = buttonParts[0].getY();

        if(isHovered()){
             i = buttonParts[2].getX();
             j = buttonParts[2].getY();
        }

        if(isFocused()){
            i = buttonParts[2].getX();
            j = buttonParts[2].getY();
        }

        RenderSystem.disableDepthTest();
        this.blit(this.x, this.y, i, j, width, height);

        if(hasImage){
            i = buttonParts[3].getX();
            j = buttonParts[3].getY();
            this.blit(this.x, this.y, i, j, height, height);
        }
        RenderSystem.enableDepthTest();

    }


}
