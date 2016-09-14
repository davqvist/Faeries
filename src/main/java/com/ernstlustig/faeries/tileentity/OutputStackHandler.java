package com.ernstlustig.faeries.tileentity;

import net.minecraft.tileentity.TileEntity;

public class OutputStackHandler extends TileStackHandler {

    public OutputStackHandler( TileEntity te, int size ){
        super( te, size );
    }

    /*@Override
    public ItemStack insertItem( int slot, ItemStack stack, boolean simulate ){
        return stack;
    }*/
}
