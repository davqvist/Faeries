package com.ernstlustig.faeries.item;

import com.ernstlustig.faeries.init.ModItems;
import com.ernstlustig.faeries.utility.NBTHelper;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Locale;

public class ItemFaery extends ItemFaeries {

    public enum EnumGender{ MALE, FEMALE, COUPLE }

    public ItemFaery(){
        super();
        setRegistryName("faery");
        setUnlocalizedName("faery");
    }

    public static String getRace( ItemStack itemstack ){
        NBTTagCompound nbtTagCompound = NBTHelper.getTagCompound( itemstack );
        if( nbtTagCompound.hasKey( "race" ) ){ return nbtTagCompound.getString( "race" ); }
        return "default";
    }

    public ItemStack setRace( ItemStack itemstack, String name ){
        NBTTagCompound nbtTagCompound = NBTHelper.getTagCompound( itemstack );
        nbtTagCompound.setString( "race", name );
        return itemstack;
    }

    public static String getGender( ItemStack itemstack ){
        NBTTagCompound nbtTagCompound = NBTHelper.getTagCompound( itemstack );
        if( nbtTagCompound.hasKey( "gender" ) ){ return nbtTagCompound.getString( "gender" ); }
        return "default";
    }

    public ItemStack setGender( ItemStack itemstack, String name ){
        NBTTagCompound nbtTagCompound = NBTHelper.getTagCompound( itemstack );
        nbtTagCompound.setString( "gender", name );
        //if( name.equals( EnumGender.COUPLE ) ){ itemstack.getItem().setMaxStackSize( 1 ); }
        return itemstack;
    }

    public static int getAge( ItemStack itemstack ){
        NBTTagCompound nbtTagCompound = NBTHelper.getTagCompound( itemstack );
        if( nbtTagCompound.hasKey( "age" ) ){ return nbtTagCompound.getInteger( "age" ); }
        return ItemFaery.getLifespan( itemstack );
    }

    public ItemStack setAge( ItemStack itemstack, int age ){
        NBTTagCompound nbtTagCompound = NBTHelper.getTagCompound( itemstack );
        nbtTagCompound.setInteger( "age", age );
        return itemstack;
    }

    public static int getLifespan( ItemStack itemstack ){
        NBTTagCompound nbtTagCompound = NBTHelper.getTagCompound( itemstack );
        if( nbtTagCompound.hasKey( "lifespan" ) ){ return nbtTagCompound.getInteger( "lifespan" ); }
        int maxage = EnumRace.valueOf( ModItems.faery.getRace( itemstack ) ).getLifespan().getMaxAge();
        ModItems.faery.setLifespan( itemstack, maxage );
        return maxage;
    }

    public ItemStack setLifespan( ItemStack itemstack, int lifespan ){
        NBTTagCompound nbtTagCompound = NBTHelper.getTagCompound( itemstack );
        nbtTagCompound.setInteger( "lifespan", lifespan );
        return itemstack;
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
        tooltip.add( "Lifespan: " + EnumLifespan.getLifespanFromMaxAge( ModItems.faery.getLifespan( stack ) ) );
    }

    @Override
    public void getSubItems( Item itemIn, CreativeTabs tab, List<ItemStack> subItems ){
        super.getSubItems( itemIn, tab, subItems );
        for( EnumRace race : EnumRace.values() ){
            for( EnumGender gender : EnumGender.values() ){
                ItemStack itemstack = new ItemStack( itemIn );
                itemstack = this.setRace( itemstack, race.name() );
                itemstack = this.setGender( itemstack, gender.name() );
                subItems.add( itemstack );
            }
        }
    }


}