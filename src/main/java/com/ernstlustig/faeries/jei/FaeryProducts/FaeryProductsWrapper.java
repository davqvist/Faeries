package com.ernstlustig.faeries.jei.FaeryProducts;

import com.ernstlustig.faeries.init.ModItems;
import com.ernstlustig.faeries.item.EnumRace;
import com.ernstlustig.faeries.item.ItemFaery;
import com.ernstlustig.faeries.item.Product;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FaeryProductsWrapper extends BlankRecipeWrapper {

    private final EnumRace race;
    private final ItemStack input;
    private final List<ItemStack> outputs;
    private final List<Integer> outputpercentages;

    public FaeryProductsWrapper( EnumRace race ){
        this.race = race;

        ItemStack couple = new ItemStack( ModItems.faery );
        couple = ModItems.faery.setRace( couple, race.name() );
        couple = ModItems.faery.setGender( couple, ItemFaery.EnumGender.COUPLE.name() );
        input = couple;

        outputs = new ArrayList<ItemStack>();
        outputpercentages = new ArrayList<Integer>();
        for( Product product : race.getProducts() ){
            outputs.add( product.getItemStack() );
            outputpercentages.add( product.getChance() );
        }
    }

    @Nonnull
    @Override
    public List getInputs(){
        return Arrays.asList( input );
    }

    @Nonnull
    @Override
    public List<ItemStack> getOutputs(){
        return outputs;
    }

    public List<Integer> getOutputPercentages(){
        return outputpercentages;
    }

    @Override
    public void drawInfo( @Nonnull Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY ){
        int i = 0;
        for( int p : outputpercentages ){
            i++;
            minecraft.fontRendererObj.drawString( p + "%", 66 + 20 * ( ( i - 1 ) % 5 ), 19 + 27 * ( ( i - 1 ) / 5 ), Color.gray.getRGB() );
        }
    }
}
