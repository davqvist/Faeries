package com.ernstlustig.faeries.jei.FaeryProducts;

import com.ernstlustig.faeries.utility.LogHelper;
import mezz.jei.api.recipe.IRecipeHandler;

import javax.annotation.Nonnull;

public class FaeryProductsHandler implements IRecipeHandler<FaeryProductsWrapper> {

    @Nonnull
    @Override
    public Class<FaeryProductsWrapper> getRecipeClass() {
        return FaeryProductsWrapper.class;
    }

    @Nonnull
    @Override
    public String getRecipeCategoryUid() {
        return "faeries.products";
    }

    @Nonnull
    @Override
    public String getRecipeCategoryUid( @Nonnull FaeryProductsWrapper recipe ) {
        return "faeries.products";
    }

    @Nonnull
    @Override
    public FaeryProductsWrapper getRecipeWrapper( @Nonnull FaeryProductsWrapper recipe ) {
        return recipe;
    }

    @Override
    public boolean isRecipeValid( @Nonnull FaeryProductsWrapper recipe ){
        if( recipe.getInputs().size() != 1 ){
            LogHelper.error("Recipe has the wrong number of inputs (needs 1).");
            return false;
        }
        if( recipe.getOutputs().size() < 1 ){
            LogHelper.error("Recipe has the wrong number of outputs (needs at least 1).");
            return false;
        }
        return true;
    }
}
