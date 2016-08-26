package com.ernstlustig.faeries.item;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public enum EnumRace {

    ROCK( Item.getItemFromBlock( Blocks.COBBLESTONE ) ),
    EARTH( Item.getItemFromBlock( Blocks.DIRT ) ),
    WATER( Items.POTIONITEM ),
    FOREST( Item.getItemFromBlock( Blocks.SAPLING ) );

    private Item item;

    EnumRace( Item item ){
        this.item = item;
    }

    public Item getItem(){
        return item;
    }
}
