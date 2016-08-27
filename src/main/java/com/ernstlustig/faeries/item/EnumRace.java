package com.ernstlustig.faeries.item;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public enum EnumRace {

    ROCK( Item.getItemFromBlock( Blocks.COBBLESTONE ), EnumLifespan.NORMAL ),
    EARTH( Item.getItemFromBlock( Blocks.DIRT ), EnumLifespan.NORMAL ),
    WATER( Items.POTIONITEM, EnumLifespan.NORMAL ),
    FOREST( Item.getItemFromBlock( Blocks.SAPLING ), EnumLifespan.NORMAL );

    private final Item item;
    private final EnumLifespan lifespan;

    EnumRace( Item item, EnumLifespan lifespan ){
        this.item = item;
        this.lifespan = lifespan;
    }

    public Item getItem(){
        return item;
    }

    public EnumLifespan getLifespan() { return lifespan; }
    //TODO: add multiple items, each with a percentage chance
}
