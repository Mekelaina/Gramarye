package com.mekelaina.gramarye.items;

import com.mekelaina.gramarye.spell.ESpellElement;
import com.mekelaina.gramarye.spell.ESpellLevel;
import com.mekelaina.gramarye.spell.Spell;
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
