package com.ernstlustig.faeries.item;

import com.ernstlustig.faeries.utility.LogHelper;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Locale;

public class ItemFaery extends ItemFaeries {

    public enum Race { ROCK, EARTH, WATER, FOREST }

    public ItemFaery(){
        super();
        setRegistryName("faery");
        setUnlocalizedName("faery");
    }

    public ItemStack setRace( ItemStack itemstack, String name ){
        NBTTagCompound nbtTagCompound = itemstack.getTagCompound();
        if( nbtTagCompound == null ){
            nbtTagCompound = new NBTTagCompound();
            itemstack.setTagCompound( nbtTagCompound );
        }
        nbtTagCompound.setString( "race", name );
        return itemstack;
    }

    public static String getRace( ItemStack itemstack ){
        NBTTagCompound nbtTagCompound = itemstack.getTagCompound();
        if( nbtTagCompound == null ){
            nbtTagCompound = new NBTTagCompound();
            itemstack.setTagCompound( nbtTagCompound );
        }
        if( nbtTagCompound.hasKey( "race" ) ){ return nbtTagCompound.getString( "race" ); }
        return "default";
    }

    @Override
    public String getItemStackDisplayName( ItemStack stack ){
        //TODO: Translation
        //I18n.translateToLocal(this.getUnlocalizedName(stack) + ".name")
        NBTTagCompound nbtTagCompound = stack.getTagCompound();
        if( nbtTagCompound != null && nbtTagCompound.hasKey( "race" ) ) {
            String race = nbtTagCompound.getString( "race" );
            return race.substring( 0, 1 ) + race.substring( 1 ).toLowerCase( Locale.ENGLISH ) + " Faery";
        }
        return "Faery";
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced )
    {
        super.addInformation( stack, playerIn, tooltip, advanced );
        /*NBTTagCompound nbtTagCompound = stack.getTagCompound();
        if( nbtTagCompound != null && nbtTagCompound.hasKey( "race" ) ) {
            tooltip.add( Integer.toString( nbtTagCompound.getInteger( "race" ) ) );
        }*/
    }

    @Override
    public void getSubItems( Item itemIn, CreativeTabs tab, List<ItemStack> subItems ){
        super.getSubItems( itemIn, tab, subItems );
        for( Race race : Race.values() ){
            ItemStack itemstack = new ItemStack( itemIn );
            subItems.add( this.setRace( itemstack, race.name() ) );
        }
    }


}