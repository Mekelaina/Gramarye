package com.mekelaina.gramarye.datagen;

import com.mekelaina.gramarye.Gramarye;
import com.mekelaina.gramarye.spell.Spell;
import com.mekelaina.gramarye.spell.SpellSetup;
import com.mekelaina.gramarye.blocks.ModBlocks;
import com.mekelaina.gramarye.items.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class SpellBookLanguageProvider extends LanguageProvider {
    public SpellBookLanguageProvider(DataGenerator gen, String locale) {
        super(gen, Gramarye.MODID, locale);
    }

    @Override
    protected void addTranslations() {
        add(ModBlocks.BROADCASTER.get(), "Broadcaster");
        add(ModBlocks.CHARGER.get(), "Charger");
        add(ModBlocks.CRYSTALBLOCK.get(), "Experion Crystal Charger");
        add(ModBlocks.CRYSTALLIZER.get(), "Experion Crystallizer");
        add(ModBlocks.EMBUINGPILLAR.get(), "Embuing Pillar");
        add(ModBlocks.EMBUINGTABLE.get(), "Embuing Table");
        add(ModBlocks.OBELISK.get(), "Experion Obelisk");
        add(ModBlocks.SHRINE.get(), "Shrine");
        add(ModBlocks.SPELLTABLE.get(), "Spell Table");
        add(ModBlocks.TESSERACT.get(), "Experion Tesseract");
        add(ModBlocks.BATTERY.get(), "Experion Well");

        add(ModItems.CRYSTAL.get(), "Experion Crystal");
        add(ModItems.STAFF.get(), "Oaken Staff");
        add(ModItems.WAND.get(), "Wand");

        add(Gramarye.setup.itemGroup.getTranslationKey(), "Gramarye");

        for(Spell spell : SpellSetup.spellMap.values()) {
            add(ModItems.SPELLBOOK_REGISTRY.get(spell).get(), "Book of " + spell.getSpellEnglishName());
        }
    }
}
