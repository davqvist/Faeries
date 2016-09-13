package com.ernstlustig.faeries.block;

import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockFaeryVilla extends BlockFaeryHouseBase {

    public BlockFaeryVilla(){
        setRegistryName( "faeryvilla" );
        this.setUnlocalizedName( "faeryvilla" );
        BOUNDING_BOX = new AxisAlignedBB( 0.0625 * 3, 0, 0.0625 * 3, 0.0625 * 13, 0.0625 * 9, 0.0625 * 13 );
        housemodifier = 1.0f;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.SOLID;
    }
}
