package com.ernstlustig.faeries.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Product {

    private ItemStack itemstack;
    private int chance;

    Product( ItemStack itemstack, int chance ){
        this.itemstack = itemstack;
        this.chance = chance;
    }

    public ItemStack getItemStack(){
        return itemstack.copy();
    }

    public int getChance(){
        return chance;
    }
}
