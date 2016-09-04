package com.ernstlustig.faeries.block;

import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockFaeryHouse extends BlockFaeryHouseBase {

    public BlockFaeryHouse(){
        setRegistryName( "faeryhouse" );
        this.setUnlocalizedName( "faeryhouse" );
    }

}
