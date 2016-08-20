package com.ernstlustig.faeries.item;

import com.ernstlustig.faeries.reference.Reference;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemFaeries extends Item {

    public ItemFaeries(){
        super();
        this.setMaxStackSize(64);
        //this.setCreativeTab( CreativeTabFaeries.FAERIES_TAB );
    }

    @Override
    public String getUnlocalizedName()
    {
        return String.format( "item.%s%s", Reference.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName( super.getUnlocalizedName() ) );
    }

    @Override
    public String getUnlocalizedName( ItemStack itemStack )
    {
        return String.format( "item.%s%s", Reference.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName( super.getUnlocalizedName() ) );
    }

    protected String getUnwrappedUnlocalizedName( String unlocalizedName )
    {
        return unlocalizedName.substring( unlocalizedName.indexOf(".") + 1 );
    }
}
