package com.mekelaina.gramarye.items;

import com.mekelaina.gramarye.Spell.ESpellElement;
import com.mekelaina.gramarye.Spell.ESpellLevel;
import com.mekelaina.gramarye.Spell.Spell;
import net.minecraft.item.Item;

public class DebugItem extends Item {

    private ESpellElement element;
    private ESpellLevel level;
    private Spell spell;

    public DebugItem(Properties properties) {
        super(properties);

    }

    public void setSpellAndProperties(Spell spell) {
        this.spell = spell;
       /* this.element = spell.getSpellElement();
        this.level = spell.getSpellLevel();*/
    }



}
