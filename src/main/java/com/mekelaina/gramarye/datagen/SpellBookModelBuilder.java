package com.mekelaina.gramarye.datagen;

import com.google.common.base.Preconditions;
import net.minecraft.resources.ResourcePackType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ModelBuilder;

public class SpellBookModelBuilder extends ModelBuilder<SpellBookModelBuilder> {
    public SpellBookModelBuilder(ResourceLocation outputLocation, ExistingFileHelper existingFileHelper) {
        super(outputLocation, existingFileHelper);
    }

    public SpellBookModelBuilder uncheckedTexture(String key, ResourceLocation texture) {
        Preconditions.checkNotNull(key, "Key must not be null");
        Preconditions.checkNotNull(texture, "Texture must not be null");
        this.textures.put(key, texture.toString());
        return this;
    }
}
