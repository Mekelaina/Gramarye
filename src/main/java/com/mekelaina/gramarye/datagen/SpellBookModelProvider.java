package com.mekelaina.gramarye.datagen;

import com.mekelaina.gramarye.Gramarye;
import com.mekelaina.gramarye.Spell.Spell;
import com.mekelaina.gramarye.Spell.SpellSetup;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile.UncheckedModelFile;


public class SpellBookModelProvider extends ItemModelProvider {
    public SpellBookModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, Gramarye.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        for(Spell spell : SpellSetup.spellMap.values()) {
            getBuilder("item/book/" + spell.getSpellRegistryName())
                    .parent(new UncheckedModelFile(Gramarye.MODID + ":item/spellbook_base"));
        }
    }

    @Override
    public String getName() {
        return "Spellbook Item Models";
    }
}
