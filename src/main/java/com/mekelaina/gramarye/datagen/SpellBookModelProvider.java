package com.mekelaina.gramarye.datagen;

import com.mekelaina.gramarye.Gramarye;
import com.mekelaina.gramarye.Spell.ESpellElement;
import com.mekelaina.gramarye.Spell.ESpellLevel;
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
        registerBaseModelsVariants((Gramarye.MODID + ":item/book/crafted_spellbook"), "crafted");

        for(Spell spell : SpellSetup.spellMap.values()) {
            getBuilder("item/book/" + spell.getSpellRegistryName())
                    .parent(getExistingFile(mcLoc("generated")))
                    .uncheckedTexture("layer0", new ResourceLocation(Gramarye.MODID, "item/book/loot_spellbook"))
                    .uncheckedTexture("layer1", spell.getSpellElement().getElementResourceLocation())
                    .uncheckedTexture("layer2", spell.getSpellLevel().getLevelResourceLocation())
                    .override().predicate(new ResourceLocation(Gramarye.MODID, "booktype"), 1)
                    .model(new UncheckedModelFile(new ResourceLocation(Gramarye.MODID, "item/book/crafted_" + spell.getSpellElement().name().toLowerCase() + "_" + spell.getSpellLevel().name().toLowerCase())))
                    .end();
        }
    }

    /*
     * Generates Model json files for all possible variants of a spellbook. These are mainly to be used for spellbook overrides.
     * The format for each variant is gramarye:item/book/namePrefix_element_level
     * e.g. gramarye:item/book/crafted_fire_novice
     */
    private void registerBaseModelsVariants(String baseSpellBookResourceLocation, String namePrefix) {
        for(ESpellElement element : ESpellElement.values()) {
            for(ESpellLevel level : ESpellLevel.values()) {
                getBuilder("item/book/" + namePrefix + "_" + element.name().toLowerCase() + "_" + level.name().toLowerCase())
                        .parent(getExistingFile(mcLoc("generated")))
                        .uncheckedTexture("layer0", new ResourceLocation(baseSpellBookResourceLocation))
                        .uncheckedTexture("layer1", element.getElementResourceLocation())
                        .uncheckedTexture("layer2", level.getLevelResourceLocation());
            }
        }
    }

    @Override
    public String getName() {
        return "Spellbook Item Models";
    }
}
