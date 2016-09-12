package com.ernstlustig.faeries.item;

import net.minecraftforge.fluids.FluidRegistry;

public class ItemDropLava extends ItemDrop {

    public ItemDropLava(){
        super();
        setRegistryName("droplava");
        setUnlocalizedName("droplava");
        fluid = FluidRegistry.LAVA;
        amount = 100;
    }

}
