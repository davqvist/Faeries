package com.ernstlustig.faeries.renderer;

import com.ernstlustig.faeries.init.ModItems;
import com.ernstlustig.faeries.item.ItemFaeries;
import com.ernstlustig.faeries.item.ItemFaery;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;

import java.util.Locale;

public class FaeriesMeshDefinition implements ItemMeshDefinition {

        @Override
        public ModelResourceLocation getModelLocation( ItemStack stack ){
            return new ModelResourceLocation( ModItems.faery.getUnlocalizedName().substring(5) + "_" + ItemFaery.getRace( stack ).toLowerCase( Locale.ENGLISH ), "inventory" );
        }

}
