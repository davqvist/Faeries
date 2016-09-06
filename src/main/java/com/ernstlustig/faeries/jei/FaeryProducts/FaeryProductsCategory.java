package com.ernstlustig.faeries.jei.FaeryProducts;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import java.awt.*;
import java.util.*;
import java.util.List;

public class FaeryProductsCategory implements IRecipeCategory<FaeryProductsWrapper> {

    @Nonnull
    private final IDrawable background;

    @Nonnull
    private final String localizedName;

    public FaeryProductsCategory(IGuiHelper guiHelper) {
        ResourceLocation location = new ResourceLocation( "faeries", "textures/gui/guifaeryhousejei.png" );
        background = guiHelper.createDrawable( location, 0, 0, 163, 55, 0, 0, 0, 0 ); //guiHelper.createDrawable( location, 8, 5, 163, 55, 0, 0, 0, 0 );
        localizedName = "Faery Products"; //Translate
    }

    @Nonnull
    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public void drawExtras( @Nonnull Minecraft minecraft ) { }

    @Override
    public void drawAnimations( @Nonnull Minecraft minecraft ) {  }

    @Nonnull
    @Override
    public String getUid() {
        return "faeries.products";
    }

    @Nonnull
    @Override
    public String getTitle() {
        return localizedName;
    }

    @Override
    public void setRecipe( @Nonnull IRecipeLayout recipeLayout, @Nonnull FaeryProductsWrapper recipeWrapper ){
        IGuiItemStackGroup itemStacks = recipeLayout.getItemStacks();
        itemStacks.init( 0, true, 9, 0 );
        itemStacks.setFromRecipe( 0, recipeWrapper.getInputs().get(0) );
        int i = 0;
        for( ItemStack is : recipeWrapper.getOutputs() ){
            i++;
            itemStacks.init( i, true, 65 + 20 * ( ( i - 1 ) % 5 ), 27 * ( ( i - 1 ) / 5 ) );
            itemStacks.setFromRecipe( i, is );
        }
    }
}
