package com.ernstlustig.faeries.material;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class MaterialFaeries extends Material {

    public static final MaterialFaeries STANDARD = new MaterialFaeries( MapColor.WOOD );

    public MaterialFaeries( MapColor color ){ super( color ); }

    @Override
    public boolean blocksMovement()
    {
        return false;
    }
}
