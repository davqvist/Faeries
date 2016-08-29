package com.ernstlustig.faeries.item;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum EnumRace {

    ROCK( Arrays.asList( new Product( new ItemStack( Item.getItemFromBlock( Blocks.COBBLESTONE ) ), 80 ) ), EnumLifespan.NORMAL, null ),
    EARTH( Arrays.asList( new Product( new ItemStack( Item.getItemFromBlock( Blocks.DIRT ) ), 80 ),
                            new Product( new ItemStack( Item.getItemFromBlock( Blocks.GRASS ) ), 5 ) ), EnumLifespan.SHORT, null ),
    WATER( Arrays.asList( new Product( new ItemStack( Items.POTIONITEM ), 80 ) ), EnumLifespan.NORMAL, null ),
    FOREST( Arrays.asList( new Product( new ItemStack( Item.getItemFromBlock( Blocks.SAPLING ) ), 10 ),
                            new Product( new ItemStack( Item.getItemFromBlock( Blocks.SAPLING ), 1, 1 ), 10 ),
                            new Product( new ItemStack( Item.getItemFromBlock( Blocks.SAPLING ), 1, 2 ), 10 ),
                            new Product( new ItemStack( Item.getItemFromBlock( Blocks.SAPLING ), 1, 3 ), 10 ),
                            new Product( new ItemStack( Item.getItemFromBlock( Blocks.SAPLING ), 1, 4 ), 10 ),
                            new Product( new ItemStack( Item.getItemFromBlock( Blocks.SAPLING ), 1, 5 ), 10 ) ), EnumLifespan.NORMAL, null ),
    CLAY( Arrays.asList( new Product( new ItemStack( Items.CLAY_BALL ), 50 ) ), EnumLifespan.LONG, new Mutation( EnumRace.WATER, EnumRace.EARTH, 20 ) ),
    GRAVEL( Arrays.asList( new Product( new ItemStack( Item.getItemFromBlock( Blocks.GRAVEL ) ), 70 ) ), EnumLifespan.NORMAL, new Mutation( EnumRace.ROCK, EnumRace.EARTH, 20 ) );

    private final List<Product> products;
    private final EnumLifespan lifespan;
    private final Mutation mutation;
    private List<Mutation> mutations = new ArrayList<Mutation>();
    private List<EnumRace> mutationraces = new ArrayList<EnumRace>();

    EnumRace( List<Product> products, EnumLifespan lifespan, Mutation mutation ){
        this.products = products;
        this.lifespan = lifespan;
        this.mutation = mutation;
        if( mutation != null ) {
            mutation.getRace1().addToMutations(mutation);
            mutation.getRace2().addToMutations(mutation);
            mutation.getRace1().addToMutationRaces(this);
            mutation.getRace2().addToMutationRaces(this);
        }
    }

    public List<Product> getProducts(){
        return products;
    }

    public EnumLifespan getLifespan() { return lifespan; }

    public boolean isCatchable(){ return( mutation == null ); }

    public void addToMutations( Mutation mutation ){
        mutations.add( mutation );
    }

    public void addToMutationRaces( EnumRace race ){
        mutationraces.add( race );
    }

    public List<Mutation> getMutations(){
        return mutations;
    }

    public List<EnumRace> getMutationRaces(){
        return mutationraces;
    }
    //TODO: add multiple items, each with a percentage chance
}
