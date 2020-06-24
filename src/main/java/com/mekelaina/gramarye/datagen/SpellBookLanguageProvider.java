package com.mekelaina.gramarye.datagen;

import com.mekelaina.gramarye.Gramarye;
import com.mekelaina.gramarye.Spell.Spell;
import com.mekelaina.gramarye.Spell.SpellSetup;
import com.mekelaina.gramarye.items.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class SpellBookLanguageProvider extends LanguageProvider {
    public SpellBookLanguageProvider(DataGenerator gen, String locale) {
        super(gen, Gramarye.MODID, locale);
    }

    @Override
    protected void addTranslations() {
        for(Spell spell : SpellSetup.spellMap.values()) {
            add(ModItems.SPELLBOOK_REGISTRY.get(spell).get(), "Book of " + spell.getSpellEnglishName());
        }
    }
}
