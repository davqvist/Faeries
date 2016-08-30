package com.ernstlustig.faeries.init;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class Recipes {

    public static void init(){
        GameRegistry.addRecipe( new ItemStack( ModItems.brailer ), "  s", " sw", "s  ", 's', Items.STICK, 'w', Blocks.WOOL );
        GameRegistry.addRecipe( new ItemStack( ModBlocks.faeryHouse ), "ppp", " w ", " w ", 'p', Blocks.PLANKS, 'w', Blocks.LOG );
        GameRegistry.addRecipe( new ItemStack( ModBlocks.mill ), " w ", "wsw", " p ", 'w', Blocks.WOOL, 's', Items.STICK, 'p', Blocks.PLANKS );
    }
}
