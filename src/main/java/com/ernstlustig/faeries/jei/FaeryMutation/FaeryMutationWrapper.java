package com.ernstlustig.faeries.jei.FaeryMutation;

import com.ernstlustig.faeries.init.ModItems;
import com.ernstlustig.faeries.faerytraits.EnumRace;
import com.ernstlustig.faeries.item.ItemFaery;
import com.ernstlustig.faeries.faerytraits.Mutation;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FaeryMutationWrapper extends BlankRecipeWrapper {

    private final EnumRace race;
    private final List<ItemStack> inputs;
    private final ItemStack output;
    private final int outputpercentage;

    public FaeryMutationWrapper( EnumRace race ){
        this.race = race;
        ItemStack couple = new ItemStack( ModItems.faery );
        couple = ModItems.faery.setRace( couple, race.name() );
        couple = ModItems.faery.setGender( couple, ItemFaery.EnumGender.COUPLE.name() );
        output = couple;

        Mutation mutation = race.getMutation();
        inputs = new ArrayList<ItemStack>();
        ItemStack male = new ItemStack( ModItems.faery );
        male = ModItems.faery.setRace( male, mutation.getRace1().name() );
        male = ModItems.faery.setGender( male, ItemFaery.EnumGender.MALE.name() );
        inputs.add( male );

        ItemStack female = new ItemStack( ModItems.faery );
        female = ModItems.faery.setRace( female, mutation.getRace2().name() );
        female = ModItems.faery.setGender( female, ItemFaery.EnumGender.FEMALE.name() );
        inputs.add( female );

        outputpercentage = mutation.getChance();
    }

    @Nonnull
    @Override
    public List getInputs(){
        return inputs;
    }

    @Nonnull
    @Override
    public List<ItemStack> getOutputs(){
        return Arrays.asList( output );
    }

    public int getOutputPercentage(){
        return outputpercentage;
    }

    @Override
    public void drawInfo(@Nonnull Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY ){
        minecraft.fontRendererObj.drawString( outputpercentage + "%", 25, 26, Color.gray.getRGB() );
    }
}
