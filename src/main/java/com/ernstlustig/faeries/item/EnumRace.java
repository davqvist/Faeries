package com.ernstlustig.faeries.item;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

import java.util.ArrayList;
import java.util.List;

public enum EnumRace {

    ROCK( Item.getItemFromBlock( Blocks.COBBLESTONE ), EnumLifespan.NORMAL, null ),
    EARTH( Item.getItemFromBlock( Blocks.DIRT ), EnumLifespan.SHORT, null ),
    WATER( Items.POTIONITEM, EnumLifespan.NORMAL, null ),
    FOREST( Item.getItemFromBlock( Blocks.SAPLING ), EnumLifespan.NORMAL, null ),
    CLAY( Items.CLAY_BALL, EnumLifespan.LONG, new Mutation( EnumRace.WATER, EnumRace.EARTH, 20 ) ),
    GRAVEL( Item.getItemFromBlock( Blocks.GRAVEL ), EnumLifespan.NORMAL, new Mutation( EnumRace.ROCK, EnumRace.EARTH, 20 ) );

    private final Item item;
    private final EnumLifespan lifespan;
    private final Mutation mutation;
    private List<Mutation> mutations = new ArrayList<Mutation>();
    private List<EnumRace> mutationraces = new ArrayList<EnumRace>();

    EnumRace( Item item, EnumLifespan lifespan, Mutation mutation ){
        this.item = item;
        this.lifespan = lifespan;
        this.mutation = mutation;
        if( mutation != null ) {
            mutation.getRace1().addToMutations(mutation);
            mutation.getRace2().addToMutations(mutation);
            mutation.getRace1().addToMutationRaces(this);
            mutation.getRace2().addToMutationRaces(this);
        }
    }

    public Item getItem(){
        return item;
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
