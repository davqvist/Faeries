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
                            new Product( new ItemStack( Item.getItemFromBlock( Blocks.DIRT ) ), 10 ) ), null, EnumLifespan.SHORT ),
    WATER( Arrays.asList( new Product( new ItemStack( ModItems.waterdrop ), 80 ),
                        new Product( new ItemStack( Item.getItemFromBlock( Blocks.WATERLILY ) ), 10 ) ), null, EnumLifespan.NORMAL ),
    FOREST( Arrays.asList( new Product( new ItemStack( Item.getItemFromBlock( Blocks.SAPLING ) ), 10 ),
                            new Product( new ItemStack( Item.getItemFromBlock( Blocks.SAPLING ), 1, 1 ), 10 ),
                            new Product( new ItemStack( Item.getItemFromBlock( Blocks.SAPLING ), 1, 2 ), 10 ),
                            new Product( new ItemStack( Item.getItemFromBlock( Blocks.SAPLING ), 1, 3 ), 10 ),
                            new Product( new ItemStack( Item.getItemFromBlock( Blocks.SAPLING ), 1, 4 ), 10 ),
                            new Product( new ItemStack( Item.getItemFromBlock( Blocks.SAPLING ), 1, 5 ), 10 ),
                            new Product( new ItemStack( Items.APPLE, 1, 5 ), 5 ) ), null, EnumLifespan.NORMAL ),
    DESERT( Arrays.asList( new Product( new ItemStack( Item.getItemFromBlock( Blocks.SAND ) ), 70 ),
                            new Product( new ItemStack( Item.getItemFromBlock( Blocks.SAND ), 1, 1 ), 10 ) ), null, EnumLifespan.NORMAL ),
    AIR( Arrays.asList( new Product( new ItemStack( ModItems.faerydust ), 60 ) ), null, EnumLifespan.LONG ),
    CLAY( Arrays.asList( new Product( new ItemStack( Items.CLAY_BALL ), 50 ) ), new Mutation( EnumRace.WATER, EnumRace.EARTH, 20 ), EnumLifespan.LONG ),
    GRAVEL( Arrays.asList( new Product( new ItemStack( Item.getItemFromBlock( Blocks.GRAVEL ) ), 70 ),
                            new Product( new ItemStack( Items.FLINT ), 7 ) ), new Mutation( EnumRace.ROCK, EnumRace.EARTH, 20 ), EnumLifespan.NORMAL ),
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
    SUGAR( Arrays.asList( new Product( new ItemStack( Item.getItemFromBlock( Blocks.REEDS ) ), 30 ) ), new Mutation( EnumRace.CROP, EnumRace.DESERT, 15 ), EnumLifespan.SHORT ),
    SPONGE( Arrays.asList( new Product( new ItemStack( Item.getItemFromBlock( Blocks.SPONGE ) ), 10 ) ), new Mutation( EnumRace.WATER, EnumRace.DESERT, 15 ), EnumLifespan.LONG ),
    FISH( Arrays.asList( new Product( new ItemStack( Items.FISH ), 8 ),
                        new Product( new ItemStack( Items.FISH, 1, 1 ), 8 ),
                        new Product( new ItemStack( Items.FISH, 1, 2 ), 8 ),
                        new Product( new ItemStack( Items.FISH, 1, 3 ), 8 ) ), new Mutation( EnumRace.SPONGE, EnumRace.WATER, 12 ), EnumLifespan.LONG ),
    CHICKEN( Arrays.asList( new Product( new ItemStack( Items.FEATHER ), 10 ),
                            new Product( new ItemStack( Items.EGG ), 10 ),
                            new Product( new ItemStack( Items.CHICKEN ), 10 ) ), new Mutation( EnumRace.FISH, EnumRace.CROP, 10 ), EnumLifespan.LONGER ),
    COW( Arrays.asList( new Product( new ItemStack( Items.LEATHER ), 10 ),
                    new Product( new ItemStack( ModItems.milkdrop ), 80 ),
                    new Product( new ItemStack( Items.BEEF ), 10 ) ), new Mutation( EnumRace.FISH, EnumRace.ROCK, 10 ), EnumLifespan.LONGER ),
    PIG( Arrays.asList( new Product( new ItemStack( Items.PORKCHOP ), 10 ) ), new Mutation( EnumRace.FISH, EnumRace.POTATO, 10 ), EnumLifespan.LONGER ),
    RABBIT( Arrays.asList( new Product( new ItemStack( Items.RABBIT_HIDE ), 10 ),
                        new Product( new ItemStack( Items.RABBIT_FOOT ), 2 ),
                        new Product( new ItemStack( Items.RABBIT ), 10 ) ), new Mutation( EnumRace.FISH, EnumRace.CARROT, 10 ), EnumLifespan.LONGER ),
    SHEEP( Arrays.asList( new Product( new ItemStack( Item.getItemFromBlock( Blocks.WOOL ) ), 10 ),
                        new Product( new ItemStack( Items.MUTTON ), 10 ) ), new Mutation( EnumRace.FISH, EnumRace.EARTH, 10 ), EnumLifespan.LONGER ),
    SQUID( Arrays.asList( new Product( new ItemStack( Items.DYE ), 10 ) ), new Mutation( EnumRace.FISH, EnumRace.WATER, 10 ), EnumLifespan.LONGER ),
    NIGHT( Arrays.asList( new Product( new ItemStack( ModItems.dreamdust ), 60 ) ), new Mutation( EnumRace.AIR, EnumRace.DESERT, 18 ), EnumLifespan.LONGER ),
    SPIDER( Arrays.asList( new Product( new ItemStack( Items.STRING ), 8 ),
                        new Product( new ItemStack( Items.SPIDER_EYE ), 2 ) ), new Mutation( EnumRace.NIGHT, EnumRace.SHEEP, 7 ), EnumLifespan.LONGEST ),
    FLESH( Arrays.asList( new Product( new ItemStack( Items.ROTTEN_FLESH ), 8 ),
                        new Product( new ItemStack( ModItems.shardheadzombie ), 5 ) ), new Mutation( EnumRace.NIGHT, EnumRace.CHICKEN, 7 ), EnumLifespan.LONGEST ),
    BONE( Arrays.asList( new Product( new ItemStack( Items.BONE ), 8 ),
                        new Product( new ItemStack( ModItems.shardskullskeleton ), 5 ) ), new Mutation( EnumRace.NIGHT, EnumRace.COW, 7 ), EnumLifespan.LONGEST ),
    CREEPER( Arrays.asList( new Product( new ItemStack( Items.GUNPOWDER ), 8 ),
                        new Product( new ItemStack( ModItems.shardheadcreeper ), 5 ) ), new Mutation( EnumRace.NIGHT, EnumRace.PIG, 7 ), EnumLifespan.LONGEST ),
    PRISMARINE( Arrays.asList( new Product( new ItemStack( Items.PRISMARINE_SHARD ), 8 ),
                            new Product( new ItemStack( Items.PRISMARINE_CRYSTALS ), 4 ) ), new Mutation( EnumRace.NIGHT, EnumRace.SQUID, 7 ), EnumLifespan.LONGEST );

    //FLOWER FLOWER
    //MUSHROOM BOTH MUSHROOMS/MYCELIUM  EARTH+NIGHT or ROCK+EARTH change Gravel to ROCK+DESERT
    //SNOW SNOWBALL/ICE

    //SLIME SLIMEBALLS    AIR+MUSHROOM
    //ENDER  ENDER PEARL SHARDS

    //COAL COAL   ROCK+NIGHT
    //LAPIS LAPIS   COAL+FLOWER
    //REDSTONE REDSTONE   COAL+?
    //IRON IRON NUGGET   COAL+?
    //GOLD GOLD NUGGET   IRON+REDSTONE
    //DIAMOND DIAMOND NUGGET   GOLD+WATER
    //EMERALD EMERALD NUGGET   GOLD+LAPIS

    //NETHER NETHERRACK  catch in the nether
    //SOUL SOULSAND    NETHER+DESERT
    //LAVA LAVA DROP   NETHER+WATER
    //OBSIDIAN OBSIDIAN  WATER+LAVA
    //WARTY NETHERWART   NETHER+CROP
    //QUARTZ QUARTZ    NETHER+COAL
    //GLOWING GLOWSTONE   NETHER+REDSTONE
    //BLAZING BLAZE RODS
    //GHAST GHAST TEARS
    //WITHER WITHER SKELETON SKULL SHARD+NETHER STAR SHARD

    //END ENDSTONE  catch in the end
    //CHORUS CHORUS PLANT  END+CROP
    //DRAGON DRAGON EGG SHARD+ENDER DRAGON HEAD SHARD+DRAGON BREATH  DIAMOND+END

    //CACTUS to desert?
    //DEAD BUSH to desert?
    //EXPERIENCE BOTTLES?
    //COBWEB to Spider?
    //GRASS?
    //MOSSY COBBLESTONE to Stone?

    //LOOT SADDLE+HORSE ARMOR+NAME TAG+ELYTRA?

    private final List<Product> products;
    private final EnumLifespan lifespan;
    private final Mutation mutation;
    private List<Mutation> mutations = new ArrayList<Mutation>();
    private List<EnumRace> mutationraces = new ArrayList<EnumRace>();

    EnumRace( List<Product> products, Mutation mutation, EnumLifespan lifespan ){
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
}
