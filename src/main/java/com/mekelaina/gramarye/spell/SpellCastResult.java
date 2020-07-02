package com.mekelaina.gramarye.spell;

import net.minecraft.util.ActionResultType;

public class SpellCastResult {
    public final ActionResultType spellResult;
    public final int finalManaCost;

    public SpellCastResult(ActionResultType spellResult, int finalManaCost) {
        this.spellResult = spellResult;
        this.finalManaCost = finalManaCost;
    }

    public SpellCastResult(ActionResultType spellResult) {
        this.spellResult = spellResult;
        this.finalManaCost = -1;
    }

    public boolean doesSpellHaveStaticManaCost() {
        return finalManaCost == -1;
    }
}
