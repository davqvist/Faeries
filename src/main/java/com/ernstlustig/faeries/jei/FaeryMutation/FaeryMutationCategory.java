package com.ernstlustig.faeries.jei.FaeryMutation;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public class FaeryMutationCategory implements IRecipeCategory<FaeryMutationWrapper> {

    @Nonnull
    private final IDrawable background;

    @Nonnull
    private final String localizedName;

    public FaeryMutationCategory(IGuiHelper guiHelper) {
        ResourceLocation location = new ResourceLocation( "faeries", "textures/gui/guifaeryhousejei.png" );
        background = guiHelper.createDrawable( location, 0, 0, 36, 55, 0, 0, 0, 0 );
        localizedName = "Faery Mutation"; //Translate
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
        return "faeries.mutation";
    }

    @Nonnull
    @Override
    public String getTitle() {
        return localizedName;
    }

    @Override
    public void setRecipe(@Nonnull IRecipeLayout recipeLayout, @Nonnull FaeryMutationWrapper recipeWrapper ){
        IGuiItemStackGroup itemStacks = recipeLayout.getItemStacks();
        itemStacks.init( 0, true, 9, 0 );
        itemStacks.setFromRecipe( 0, recipeWrapper.getOutputs().get(0) );
        itemStacks.init( 1, true, 0, 37 );
        itemStacks.setFromRecipe( 1, recipeWrapper.getInputs().get(0) );
        itemStacks.init( 2, true, 18, 37 );
        itemStacks.setFromRecipe( 2, recipeWrapper.getInputs().get(1) );
    }
}
