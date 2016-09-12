package com.ernstlustig.faeries.tileentity;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class AutoOutputStackHandler extends OutputStackHandler{

    public AutoOutputStackHandler( TileEntity te, OutputStackHandler stackhandler ){
        super( te, stackhandler.getSlots() );
        this.stacks = stackhandler.getStacks();
    }

    @Override
    public ItemStack insertItem( int slot, ItemStack stack, boolean simulate ){
        return stack;
    }
}
