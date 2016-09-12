package com.ernstlustig.faeries.tileentity;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.items.ItemStackHandler;

public class TileStackHandler extends ItemStackHandler {

    private TileEntity te;

    public TileStackHandler(){
        super( 1 );
    }

    public TileStackHandler( TileEntity te, int size ){
        super( size );
        this.te = te;
    }

    @Override
    protected void onContentsChanged( int slot ){
        te.markDirty();
    }

    public ItemStack[] getStacks(){
        return stacks;
    }
}
