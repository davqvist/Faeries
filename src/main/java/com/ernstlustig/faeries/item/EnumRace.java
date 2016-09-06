package com.ernstlustig.faeries.item;

import com.ernstlustig.faeries.init.ModItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum EnumRace {

    ROCK( Arrays.asList( new Product( new ItemStack( Item.getItemFromBlock( Blocks.COBBLESTONE ) ), 80 ),
                        new Product( new ItemStack( Item.getItemFromBlock( Blocks.STONE ), 1, 1 ), 10 ),
                        new Product( new ItemStack( Item.getItemFromBlock( Blocks.STONE ), 1, 3 ), 10 ),
                        new Product( new ItemStack( Item.getItemFromBlock( Blocks.STONE ), 1, 5 ), 10 ) ), null, EnumLifespan.NORMAL ),
    EARTH( Arrays.asList( new Product( new ItemStack( Item.getItemFromBlock( Blocks.DIRT ) ), 80 ),
                            new Product( new ItemStack( Item.getItemFromBlock( Blocks.GRASS ) ), 5 ),
                            new Product( new ItemStack( Item.getItemFromBlock( Blocks.DIRT ), 1, 2 ), 10 ) ), null, EnumLifespan.SHORT ),
    WATER( Arrays.asList( new Product( new ItemStack( ModItems.dropwater ), 80 ),
                        new Product( new ItemStack( Item.getItemFromBlock( Blocks.WATERLILY ) ), 10 ) ), null, EnumLifespan.NORMAL ),
    FOREST( Arrays.asList( new Product( new ItemStack( Item.getItemFromBlock( Blocks.SAPLING ) ), 10 ),
                            new Product( new ItemStack( Item.getItemFromBlock( Blocks.SAPLING ), 1, 1 ), 10 ),
                            new Product( new ItemStack( Item.getItemFromBlock( Blocks.SAPLING ), 1, 2 ), 10 ),
                            new Product( new ItemStack( Item.getItemFromBlock( Blocks.SAPLING ), 1, 3 ), 10 ),
                            new Product( new ItemStack( Item.getItemFromBlock( Blocks.SAPLING ), 1, 4 ), 10 ),
                            new Product( new ItemStack( Item.getItemFromBlock( Blocks.SAPLING ), 1, 5 ), 10 ),
                            new Product( new ItemStack( Items.APPLE ), 5 ) ), null, EnumLifespan.NORMAL ),
    DESERT( Arrays.asList( new Product( new ItemStack( Item.getItemFromBlock( Blocks.SAND ) ), 70 ),
                            new Product( new ItemStack( Item.getItemFromBlock( Blocks.SAND ), 1, 1 ), 10 ),
                            new Product( new ItemStack( Item.getItemFromBlock( Blocks.CACTUS ) ), 25 ),
                            new Product( new ItemStack( Item.getItemFromBlock( Blocks.DEADBUSH ) ), 15 ) ), null, EnumLifespan.NORMAL ),
    AIR( Arrays.asList( new Product( new ItemStack( ModItems.faerydust ), 60 ) ), null, EnumLifespan.LONG ),
    CLAY( Arrays.asList( new Product( new ItemStack( Items.CLAY_BALL ), 50 ) ), new Mutation( EnumRace.WATER, EnumRace.EARTH, 20 ), EnumLifespan.LONG ),
    GRAVEL( Arrays.asList( new Product( new ItemStack( Item.getItemFromBlock( Blocks.GRAVEL ) ), 70 ),
                            new Product( new ItemStack( Items.FLINT ), 7 ) ), new Mutation( EnumRace.ROCK, EnumRace.EARTH, 20 ), EnumLifespan.NORMAL ),
    SNOW( Arrays.asList( new Product( new ItemStack( Items.SNOWBALL ), 20 ),
                        new Product( new ItemStack( Item.getItemFromBlock( Blocks.ICE ) ), 10 ),
                        new Product( new ItemStack( Item.getItemFromBlock( Blocks.PACKED_ICE ) ), 4 ) ), new Mutation( EnumRace.AIR, EnumRace.WATER, 20 ), EnumLifespan.NORMAL ),
    FLOWER( Arrays.asList( new Product( new ItemStack( Item.getItemFromBlock( Blocks.YELLOW_FLOWER ) ), 3 ),
                            new Product( new ItemStack( Item.getItemFromBlock( Blocks.RED_FLOWER ) ), 3 ),
                            new Product( new ItemStack( Item.getItemFromBlock( Blocks.RED_FLOWER ), 1, 1 ), 3 ),
                            new Product( new ItemStack( Item.getItemFromBlock( Blocks.RED_FLOWER ), 1, 2 ), 3 ),
                            new Product( new ItemStack( Item.getItemFromBlock( Blocks.RED_FLOWER ), 1, 3 ), 3 ),
                            new Product( new ItemStack( Item.getItemFromBlock( Blocks.RED_FLOWER ), 1, 4 ), 3 ),
                            new Product( new ItemStack( Item.getItemFromBlock( Blocks.RED_FLOWER ), 1, 5 ), 3 ),
                            new Product( new ItemStack( Item.getItemFromBlock( Blocks.RED_FLOWER ), 1, 6 ), 3 ),
                            new Product( new ItemStack( Item.getItemFromBlock( Blocks.RED_FLOWER ), 1, 7 ), 3 ),
                            new Product( new ItemStack( Item.getItemFromBlock( Blocks.RED_FLOWER ), 1, 8 ), 3 ) ), new Mutation( EnumRace.FOREST, EnumRace.ROCK, 20 ), EnumLifespan.NORMAL ),
    CROP( Arrays.asList( new Product( new ItemStack( Items.WHEAT_SEEDS ), 20 ),
                        new Product( new ItemStack( Items.WHEAT ), 20 ) ), new Mutation( EnumRace.FOREST, EnumRace.WATER, 18 ), EnumLifespan.SHORT ),
    MELON( Arrays.asList( new Product( new ItemStack( Items.MELON ), 40 ) ), new Mutation( EnumRace.CROP, EnumRace.WATER, 15 ), EnumLifespan.SHORT ),
    PUMPKIN( Arrays.asList( new Product( new ItemStack( Item.getItemFromBlock( Blocks.PUMPKIN ) ), 10 ) ), new Mutation( EnumRace.CROP, EnumRace.ROCK, 15 ), EnumLifespan.SHORT ),
    BEET( Arrays.asList( new Product( new ItemStack( Items.BEETROOT ), 40 ),
            new Product( new ItemStack( Items.BEETROOT_SEEDS ), 40 ) ), new Mutation( EnumRace.CROP, EnumRace.EARTH, 15 ), EnumLifespan.SHORT ),
    CARROT( Arrays.asList( new Product( new ItemStack( Items.CARROT ), 35 ) ), new Mutation( EnumRace.CROP, EnumRace.PUMPKIN, 12 ), EnumLifespan.SHORTER ),
    POTATO( Arrays.asList( new Product( new ItemStack( Items.POTATO ), 35 ),
                        new Product( new ItemStack( Items.POISONOUS_POTATO ), 5 ) ), new Mutation( EnumRace.CROP, EnumRace.BEET, 15 ), EnumLifespan.SHORTER ),
    JUNGLE( Arrays.asList( new Product( new ItemStack( Items.DYE, 1, 3 ), 20 ),
                        new Product( new ItemStack( Item.getItemFromBlock( Blocks.VINE ) ), 8 ) ), new Mutation( EnumRace.EARTH, EnumRace.FOREST, 18 ), EnumLifespan.SHORT ),
    SUGAR( Arrays.asList( new Product( new ItemStack( Items.REEDS ), 30 ) ), new Mutation( EnumRace.CROP, EnumRace.DESERT, 15 ), EnumLifespan.SHORT ),
    SPONGE( Arrays.asList( new Product( new ItemStack( Item.getItemFromBlock( Blocks.SPONGE ) ), 10 ) ), new Mutation( EnumRace.WATER, EnumRace.DESERT, 15 ), EnumLifespan.LONG ),
    FISH( Arrays.asList( new Product( new ItemStack( Items.FISH ), 8 ),
                        new Product( new ItemStack( Items.FISH, 1, 1 ), 8 ),
                        new Product( new ItemStack( Items.FISH, 1, 2 ), 8 ),
                        new Product( new ItemStack( Items.FISH, 1, 3 ), 8 ) ), new Mutation( EnumRace.SPONGE, EnumRace.WATER, 12 ), EnumLifespan.LONG ),
    CHICKEN( Arrays.asList( new Product( new ItemStack( Items.FEATHER ), 10 ),
                            new Product( new ItemStack( Items.EGG ), 10 ),
                            new Product( new ItemStack( Items.CHICKEN ), 10 ) ), new Mutation( EnumRace.FISH, EnumRace.CROP, 10 ), EnumLifespan.LONGER ),
    COW( Arrays.asList( new Product( new ItemStack( Items.LEATHER ), 10 ),
                    new Product( new ItemStack( ModItems.dropmilk ), 80 ),
                    new Product( new ItemStack( Items.BEEF ), 10 ) ), new Mutation( EnumRace.FISH, EnumRace.ROCK, 10 ), EnumLifespan.LONGER ),
    PIG( Arrays.asList( new Product( new ItemStack( Items.PORKCHOP ), 10 ) ), new Mutation( EnumRace.FISH, EnumRace.POTATO, 10 ), EnumLifespan.LONGER ),
    RABBIT( Arrays.asList( new Product( new ItemStack( Items.RABBIT_HIDE ), 10 ),
                        new Product( new ItemStack( Items.RABBIT_FOOT ), 2 ),
                        new Product( new ItemStack( Items.RABBIT ), 10 ) ), new Mutation( EnumRace.FISH, EnumRace.CARROT, 10 ), EnumLifespan.LONGER ),
    SHEEP( Arrays.asList( new Product( new ItemStack( Item.getItemFromBlock( Blocks.WOOL ) ), 10 ),
                        new Product( new ItemStack( Items.MUTTON ), 10 ) ), new Mutation( EnumRace.FISH, EnumRace.EARTH, 10 ), EnumLifespan.LONGER ),
    SQUID( Arrays.asList( new Product( new ItemStack( Items.DYE ), 10 ) ), new Mutation( EnumRace.FISH, EnumRace.WATER, 10 ), EnumLifespan.LONGER ),
    NIGHT( Arrays.asList( new Product( new ItemStack( ModItems.dreamdust ), 60 ) ), new Mutation( EnumRace.AIR, EnumRace.DESERT, 18 ), EnumLifespan.LONGER ),
    MUSHROOM( Arrays.asList( new Product( new ItemStack( Item.getItemFromBlock( Blocks.BROWN_MUSHROOM ) ), 8 ),
                            new Product( new ItemStack( Item.getItemFromBlock( Blocks.RED_MUSHROOM ) ), 8 ),
                            new Product( new ItemStack( Item.getItemFromBlock( Blocks.MYCELIUM ) ), 5 ) ), new Mutation( EnumRace.NIGHT, EnumRace.EARTH, 15 ), EnumLifespan.NORMAL ),
    SPIDER( Arrays.asList( new Product( new ItemStack( Items.STRING ), 8 ),
                        new Product( new ItemStack( Items.SPIDER_EYE ), 2 ),
                        new Product( new ItemStack( Item.getItemFromBlock( Blocks.WEB ) ), 5 ) ), new Mutation( EnumRace.NIGHT, EnumRace.SHEEP, 7 ), EnumLifespan.LONGEST ),
    FLESH( Arrays.asList( new Product( new ItemStack( Items.ROTTEN_FLESH ), 8 ),
                        new Product( new ItemStack( ModItems.shardheadzombie ), 5 ) ), new Mutation( EnumRace.NIGHT, EnumRace.CHICKEN, 7 ), EnumLifespan.LONGEST ),
    BONE( Arrays.asList( new Product( new ItemStack( Items.BONE ), 8 ),
                        new Product( new ItemStack( ModItems.shardskullskeleton ), 5 ) ), new Mutation( EnumRace.NIGHT, EnumRace.COW, 7 ), EnumLifespan.LONGEST ),
    CREEPER( Arrays.asList( new Product( new ItemStack( Items.GUNPOWDER ), 8 ),
                        new Product( new ItemStack( ModItems.shardheadcreeper ), 5 ) ), new Mutation( EnumRace.NIGHT, EnumRace.PIG, 7 ), EnumLifespan.LONGEST ),
    PRISMARINE( Arrays.asList( new Product( new ItemStack( Items.PRISMARINE_SHARD ), 8 ),
                            new Product( new ItemStack( Items.PRISMARINE_CRYSTALS ), 4 ) ), new Mutation( EnumRace.NIGHT, EnumRace.SQUID, 7 ), EnumLifespan.LONGEST ),
    SLIME( Arrays.asList( new Product( new ItemStack( Items.SLIME_BALL ), 8 ) ), new Mutation( EnumRace.NIGHT, EnumRace.MUSHROOM, 7 ), EnumLifespan.LONGEST ),
    ENDER( Arrays.asList( new Product( new ItemStack( ModItems.shardenderpearl ), 10 ) ), new Mutation( EnumRace.NIGHT, EnumRace.PRISMARINE, 5 ), EnumLifespan.LONGEST ),
    COAL( Arrays.asList( new Product( new ItemStack( Items.COAL ), 10 ) ), new Mutation( EnumRace.NIGHT, EnumRace.ROCK, 12 ), EnumLifespan.NORMAL ),
    LAPIS( Arrays.asList( new Product( new ItemStack( Items.DYE, 1, 4 ), 5 ) ), new Mutation( EnumRace.COAL, EnumRace.FLOWER, 10 ), EnumLifespan.NORMAL ),
    REDSTONE( Arrays.asList( new Product( new ItemStack( Items.REDSTONE ), 8 ) ), new Mutation( EnumRace.COAL, EnumRace.MELON, 10 ), EnumLifespan.NORMAL ),
    IRON( Arrays.asList( new Product( new ItemStack( ModItems.nuggetiron ), 20 ) ), new Mutation( EnumRace.COAL, EnumRace.SUGAR, 10 ), EnumLifespan.NORMAL ),
    GOLD( Arrays.asList( new Product( new ItemStack( Items.GOLD_NUGGET ), 8 ) ), new Mutation( EnumRace.IRON, EnumRace.REDSTONE, 8 ), EnumLifespan.NORMAL ),
    DIAMOND( Arrays.asList( new Product( new ItemStack( ModItems.nuggetdiamond ), 2 ) ), new Mutation( EnumRace.GOLD, EnumRace.WATER, 5 ), EnumLifespan.NORMAL ),
    EMERALD( Arrays.asList( new Product( new ItemStack( ModItems.nuggetemerald ), 2 ) ), new Mutation( EnumRace.GOLD, EnumRace.LAPIS, 5 ), EnumLifespan.NORMAL ),
    NETHER( Arrays.asList( new Product( new ItemStack( Item.getItemFromBlock( Blocks.NETHERRACK ) ), 70 ) ), null, EnumLifespan.NORMAL, -1 ),
    SOUL( Arrays.asList( new Product( new ItemStack( Item.getItemFromBlock( Blocks.SOUL_SAND ) ), 30 ) ), new Mutation( EnumRace.NETHER, EnumRace.DESERT, 18 ), EnumLifespan.LONGER ),
    LAVA( Arrays.asList( new Product( new ItemStack( ModItems.droplava ), 80 ) ), new Mutation( EnumRace.NETHER, EnumRace.WATER, 15 ), EnumLifespan.NORMAL ),
    OBSIDIAN( Arrays.asList( new Product( new ItemStack( Item.getItemFromBlock( Blocks.OBSIDIAN ) ), 8 ) ), new Mutation( EnumRace.LAVA, EnumRace.WATER, 12 ), EnumLifespan.LONGER ),
    WART( Arrays.asList( new Product( new ItemStack( Items.NETHER_WART ), 15 ) ), new Mutation( EnumRace.SOUL, EnumRace.CROP, 15 ), EnumLifespan.SHORT ),
    QUARTZ( Arrays.asList( new Product( new ItemStack( Items.QUARTZ ), 10 ) ), new Mutation( EnumRace.NETHER, EnumRace.COAL, 12 ), EnumLifespan.NORMAL ),
    GLOW( Arrays.asList( new Product( new ItemStack( Items.GLOWSTONE_DUST ), 10 ) ), new Mutation( EnumRace.NETHER, EnumRace.REDSTONE, 12 ), EnumLifespan.NORMAL ),
    BLAZE( Arrays.asList( new Product( new ItemStack( Items.BLAZE_ROD ), 8 ) ), new Mutation( EnumRace.NIGHT, EnumRace.LAVA, 7 ), EnumLifespan.LONGEST ),
    GHAST( Arrays.asList( new Product( new ItemStack( Items.GHAST_TEAR ), 2 ) ), new Mutation( EnumRace.NIGHT, EnumRace.QUARTZ, 7 ), EnumLifespan.LONGEST ),
    WITHER( Arrays.asList( new Product( new ItemStack( ModItems.shardskullwither ), 5 ),
                            new Product( new ItemStack( ModItems.shivernetherstar ), 2 ) ), new Mutation( EnumRace.NIGHT, EnumRace.SOUL, 5 ), EnumLifespan.LONGEST ),
    END( Arrays.asList( new Product( new ItemStack( Item.getItemFromBlock( Blocks.END_STONE ) ), 70 ) ), null, EnumLifespan.LONG, 1 ),
    CHORUS( Arrays.asList( new Product( new ItemStack( Items.CHORUS_FRUIT ), 15 ) ), new Mutation( EnumRace.END, EnumRace.CROP, 15 ), EnumLifespan.LONG ),
    DRAGON( Arrays.asList( new Product( new ItemStack( ModItems.shardheaddragon ), 5 ),
                        new Product( new ItemStack( ModItems.shiverdragonegg ), 3 ),
                        new Product( new ItemStack( Items.DRAGON_BREATH ), 15 ) ), new Mutation( EnumRace.END, EnumRace.DIAMOND, 8 ), EnumLifespan.LONG );

    private final List<Product> products;
    private final EnumLifespan lifespan;
    private final Mutation mutation;
    private List<Mutation> mutations = new ArrayList<Mutation>();
    private List<EnumRace> mutationraces = new ArrayList<EnumRace>();
    private final int dimension;

    EnumRace( List<Product> products, Mutation mutation, EnumLifespan lifespan ){
        this( products, mutation, lifespan, 0 );
    }

    EnumRace( List<Product> products, Mutation mutation, EnumLifespan lifespan, int dimension ){
        this.products = products;
        this.lifespan = lifespan;
        this.mutation = mutation;
        this.dimension = dimension;
        if( mutation != null ) {
            mutation.getRace1().addToMutations( mutation );
            mutation.getRace2().addToMutations( mutation );
            mutation.getRace1().addToMutationRaces( this );
            mutation.getRace2().addToMutationRaces( this );
        }
    }

    public int getCatchableDimension(){ return dimension; }

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

    public Mutation getMutation(){
        return mutation;
    }

    public List<Mutation> getMutations(){
        return mutations;
    }

    public List<EnumRace> getMutationRaces(){
        return mutationraces;
    }
}
