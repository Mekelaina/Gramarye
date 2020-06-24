package com.mekelaina.gramarye.datagen;

import com.mekelaina.gramarye.Gramarye;
import com.mekelaina.gramarye.Spell.Spell;
import com.mekelaina.gramarye.Spell.SpellSetup;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile.UncheckedModelFile;
import net.minecraftforge.client.model.generators.ModelProvider;

import java.util.function.BiFunction;
import java.util.function.Function;

public class SpellBookModelProvider extends ModelProvider<SpellBookModelBuilder> {
    public SpellBookModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, Gramarye.MODID, ITEM_FOLDER, SpellBookModelBuilder::new, existingFileHelper);
    }


    @Override
    protected void registerModels() {
        for(Spell spell : SpellSetup.spellMap.values()) {
            getBuilder("item/book/" + spell.getSpellRegistryName())
                    .parent(getExistingFile(mcLoc("generated")))
                    .uncheckedTexture("layer0", new ResourceLocation(Gramarye.MODID + ":item/book/loot_spellbook"))
                    .uncheckedTexture("layer1", spell.getSpellElement().getElementResourceLocation())
                    .uncheckedTexture("layer2", spell.getSpellLevel().getLevelResourceLocation());
        }
    }

    @Override
    public String getName() {
        return "Spellbook Item Models";
    }
}
