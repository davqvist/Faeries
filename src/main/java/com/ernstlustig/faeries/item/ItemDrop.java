package com.ernstlustig.faeries.item;

import net.minecraftforge.fluids.Fluid;

public abstract class ItemDrop extends ItemFaeries{
        protected Fluid fluid;
        protected int amount;

        public Fluid getFluid(){
                return fluid;
        }

        public int getAmount(){
                return amount;
        }
}
