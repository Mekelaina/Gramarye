package com.mekelaina.gramarye.items.book;

import net.minecraft.nbt.CompoundNBT;

import java.util.Optional;

public enum EBookType {
    Looted(0),
    Crafted(1);

    private final byte nbtID;

    EBookType(int nbtID)
    {
        this.nbtID = (byte)nbtID;
    }

    public byte getPropertyOverrideValue() {
        return nbtID;
    }

    public static EBookType fromNBT(CompoundNBT compoundNBT, String tagname)
    {
        byte booktypeID = 0;  // default in case of error
        if (compoundNBT != null && compoundNBT.contains(tagname)) {
            booktypeID = compoundNBT.getByte(tagname);
        }
        Optional<EBookType> bookType = getBookTypeFromID(booktypeID);
        return bookType.orElse(Looted);
    }

    public void putIntoNBT(CompoundNBT compoundNBT, String tagname)
    {
        compoundNBT.putByte(tagname, nbtID);
    }

    private static Optional<EBookType> getBookTypeFromID(byte ID) {
        for (EBookType eBookType : EBookType.values()) {
            if (eBookType.nbtID == ID) return Optional.of(eBookType);
        }
        return Optional.empty();
    }


}
