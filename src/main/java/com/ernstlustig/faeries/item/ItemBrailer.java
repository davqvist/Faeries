package com.ernstlustig.faeries.item;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBrailer extends ItemFaeries {

    public ItemBrailer(){
        super();
        this.setMaxStackSize(1);
        setRegistryName("brailer");
        setUnlocalizedName("brailer");
    }

    @SideOnly(Side.CLIENT)
    public boolean isFull3D()
    {
        return true;
    }
}
