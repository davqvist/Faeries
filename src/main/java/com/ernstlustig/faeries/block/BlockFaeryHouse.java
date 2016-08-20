package com.ernstlustig.faeries.block;

import net.minecraft.util.BlockRenderLayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockFaeryHouse extends BlockFaeries {

    public BlockFaeryHouse(){
        super();
        setRegistryName("faeryHouse");
        this.setUnlocalizedName("faeryHouse");
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.SOLID;
    }

}
