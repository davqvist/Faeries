package com.ernstlustig.faeries.jei;

import com.ernstlustig.faeries.container.ContainerFaeryHouse;
import com.ernstlustig.faeries.gui.GuiFaeryHouse;
import com.ernstlustig.faeries.init.ModBlocks;
import com.ernstlustig.faeries.init.ModItems;
import com.ernstlustig.faeries.item.ItemFaery;
import com.ernstlustig.faeries.jei.FaeryMutation.FaeryMutationCategory;
import com.ernstlustig.faeries.jei.FaeryMutation.FaeryMutationHandler;
import com.ernstlustig.faeries.jei.FaeryMutation.FaeryMutationMaker;
import com.ernstlustig.faeries.jei.FaeryProducts.FaeryProductsCategory;
import com.ernstlustig.faeries.jei.FaeryProducts.FaeryProductsHandler;
import com.ernstlustig.faeries.jei.FaeryProducts.FaeryProductsMaker;
import mezz.jei.api.*;
import mezz.jei.api.recipe.transfer.IRecipeTransferRegistry;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@JEIPlugin
public class FaeriesJEIPlugin extends BlankModPlugin {

    @Override
    public void register(@Nonnull IModRegistry registry) {
        IJeiHelpers jeiHelpers = registry.getJeiHelpers();
        IGuiHelper guiHelper = jeiHelpers.getGuiHelper();

        ISubtypeRegistry subtypeRegistry = jeiHelpers.getSubtypeRegistry();
        subtypeRegistry.registerNbtInterpreter( ModItems.faery, FaerySubtypeInterpreter.INSTANCE );

        registry.addRecipeCategories( new FaeryProductsCategory( guiHelper ), new FaeryMutationCategory( guiHelper ) );
        registry.addRecipeHandlers( new FaeryProductsHandler(), new FaeryMutationHandler() );

        registry.addRecipeCategoryCraftingItem( new ItemStack( ModBlocks.faeryhouse ), "faeries.products" );
        registry.addRecipeCategoryCraftingItem( new ItemStack( ModBlocks.faeryvilla ), "faeries.products" );
        registry.addRecipeCategoryCraftingItem( new ItemStack( ModBlocks.faeryhouse ), "faeries.mutation" );
        registry.addRecipeCategoryCraftingItem( new ItemStack( ModBlocks.faeryvilla ), "faeries.mutation" );

        registry.addRecipes( FaeryProductsMaker.getFaeryProducts() );
        registry.addRecipes( FaeryMutationMaker.getFaeryMutations() );
    }

    private static class FaerySubtypeInterpreter implements ISubtypeRegistry.ISubtypeInterpreter {
        public static FaerySubtypeInterpreter INSTANCE = new FaerySubtypeInterpreter();

        @Nullable
        @Override
        public String getSubtypeInfo( @Nonnull ItemStack itemStack ){
            return ItemFaery.getRace( itemStack );
        }
    }
}
