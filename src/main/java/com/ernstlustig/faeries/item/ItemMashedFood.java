package com.ernstlustig.faeries.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemMashedFood extends ItemFaeries {

    public ItemMashedFood(){
        super();
        setRegistryName("mashedfood");
        setUnlocalizedName("mashedfood");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation( ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced ){
        super.addInformation( stack, playerIn, tooltip, advanced );
        tooltip.add( "2x Production" );
    }
}
