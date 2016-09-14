package com.ernstlustig.faeries.world;

import com.ernstlustig.faeries.utility.LogHelper;
import net.minecraft.world.WorldProviderHell;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.gen.ChunkProviderFlat;
import net.minecraft.world.gen.ChunkProviderHell;

public class WorldProviderSkyblockHell extends WorldProviderHell {

    @Override
    public IChunkGenerator createChunkGenerator()
    {
        if( worldObj.getWorldType() instanceof WorldTypeSkyblock ) {
            return new ChunkProviderFlat( worldObj, worldObj.getSeed(), false, "2;1x0;" );
        }
        return new ChunkProviderHell( worldObj, worldObj.getWorldInfo().isMapFeaturesEnabled(), worldObj.getSeed() );
    }
}
