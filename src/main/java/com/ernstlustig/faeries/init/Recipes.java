package com.ernstlustig.faeries.init;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class Recipes {

    public static void init(){
        GameRegistry.addRecipe( new ItemStack( ModItems.brailer ), "  s", " sw", "s  ", 's', Items.STICK, 'w', Blocks.WOOL );
        GameRegistry.addRecipe( new ItemStack( ModBlocks.faeryhouse ), " p ", "p p", "w w", 'p', Blocks.PLANKS, 'w', Blocks.LOG );
        //GameRegistry.addRecipe( new ItemStack( ModBlocks.faeryvilla ), "dnd", "nhn", "bcb", 'd', ModItems.faerydust, 'n', Blocks.NETHER_BRICK, 'h', ModBlocks.faeryhouse, 'b', Blocks.BRICK_BLOCK, 'c', Blocks.CAULDRON );
        GameRegistry.addRecipe( new ItemStack( ModBlocks.faeryvilla ), "dnd", "nhn", "bcb", 'd', ModItems.faerydust, 'n', Blocks.NETHER_BRICK, 'h', ModBlocks.faeryhouse, 'b', Blocks.BRICK_BLOCK, 'c', Items.CAULDRON );
        GameRegistry.addRecipe( new ItemStack( ModBlocks.mill ), " w ", "wsw", " p ", 'w', Blocks.WOOL, 's', Items.STICK, 'p', Blocks.PLANKS );
        GameRegistry.addRecipe( new ItemStack( Items.SKULL, 1, 2 ), "sss", "sss", "sss", 's', ModItems.shardheadzombie );
        GameRegistry.addRecipe( new ItemStack( Items.SKULL ), "sss", "sss", "sss", 's', ModItems.shardskullskeleton );
        GameRegistry.addRecipe( new ItemStack( Items.SKULL, 1, 4 ), "sss", "sss", "sss", 's', ModItems.shardheadcreeper );
        GameRegistry.addRecipe( new ItemStack( Items.SKULL, 1, 1 ), "sss", "sss", "sss", 's', ModItems.shardskullwither );
        GameRegistry.addRecipe( new ItemStack( Items.SKULL, 1, 5 ), "sss", "sss", "sss", 's', ModItems.shardheaddragon );
        GameRegistry.addRecipe( new ItemStack( Items.ENDER_PEARL ), "sss", "sss", "sss", 's', ModItems.shardenderpearl );
        GameRegistry.addRecipe( new ItemStack( Items.NETHER_STAR ), "sss", "sss", "sss", 's', ModItems.shardnetherstar );
        GameRegistry.addRecipe( new ItemStack( ModItems.shardnetherstar ), "sss", "sss", "sss", 's', ModItems.shivernetherstar );
        GameRegistry.addRecipe( new ItemStack( Blocks.DRAGON_EGG ), "sss", "sss", "sss", 's', ModItems.sharddragonegg );
        GameRegistry.addRecipe( new ItemStack( ModItems.sharddragonegg ), "sss", "sss", "sss", 's', ModItems.shiverdragonegg );
        GameRegistry.addRecipe( new ItemStack( Items.IRON_INGOT ), "nnn", "nnn", "nnn", 'n', ModItems.nuggetiron );
        GameRegistry.addRecipe( new ItemStack( Items.DIAMOND ), "nnn", "nnn", "nnn", 'n', ModItems.nuggetdiamond );
        GameRegistry.addRecipe( new ItemStack( Items.EMERALD ), "nnn", "nnn", "nnn", 'n', ModItems.nuggetemerald );
    }
}
