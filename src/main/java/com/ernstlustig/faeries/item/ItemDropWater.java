package com.ernstlustig.faeries.item;

import net.minecraftforge.fluids.FluidRegistry;

public class ItemDropWater extends ItemDrop {

    public ItemDropWater(){
        super();
        setRegistryName("dropwater");
        setUnlocalizedName("dropwater");
        fluid = FluidRegistry.WATER;
        amount = 1000;
    }

}
