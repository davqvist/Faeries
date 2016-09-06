package com.ernstlustig.faeries.jei.FaeryMutation;

import com.ernstlustig.faeries.utility.LogHelper;
import mezz.jei.api.recipe.IRecipeHandler;

import javax.annotation.Nonnull;

public class FaeryMutationHandler implements IRecipeHandler<FaeryMutationWrapper> {

    @Nonnull
    @Override
    public Class<FaeryMutationWrapper> getRecipeClass() {
        return FaeryMutationWrapper.class;
    }

    @Nonnull
    @Override
    public String getRecipeCategoryUid() {
        return "faeries.mutation";
    }

    @Nonnull
    @Override
    public String getRecipeCategoryUid( @Nonnull FaeryMutationWrapper recipe ) {
        return "faeries.mutation";
    }

    @Nonnull
    @Override
    public FaeryMutationWrapper getRecipeWrapper( @Nonnull FaeryMutationWrapper recipe ) {
        return recipe;
    }

    @Override
    public boolean isRecipeValid( @Nonnull FaeryMutationWrapper recipe ){
        if( recipe.getInputs().size() != 2 ){
            LogHelper.error("Recipe has the wrong number of inputs (needs 2).");
            return false;
        }
        if( recipe.getOutputs().size() != 1 ){
            LogHelper.error("Recipe has the wrong number of outputs (needs 1).");
            return false;
        }
        return true;
    }
}
