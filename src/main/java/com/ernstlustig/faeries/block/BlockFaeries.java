package com.ernstlustig.faeries.block;

import com.ernstlustig.faeries.creativetab.CreativeTabFaeries;
import com.ernstlustig.faeries.reference.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockFaeries extends Block {
    public BlockFaeries( Material material ){
        super( material );
        this.setCreativeTab( CreativeTabFaeries.FAERIES_TAB );
    }

    public BlockFaeries(){
        this( Material.ROCK );
    }

    @Override
    public String getUnlocalizedName(){
        return String.format( "tile.%s%s", Reference.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName( super.getUnlocalizedName() ) );
    }

    protected String getUnwrappedUnlocalizedName( String unlocalizedName ){
        return unlocalizedName.substring( unlocalizedName.indexOf(".") + 1 );
    }
}
