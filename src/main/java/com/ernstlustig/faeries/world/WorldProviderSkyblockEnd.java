package com.ernstlustig.faeries.world;

import net.minecraft.world.WorldProviderEnd;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.gen.ChunkProviderEnd;

public class WorldProviderSkyblockEnd extends WorldProviderEnd {

    @Override
    public IChunkGenerator createChunkGenerator()
    {
        if( worldObj.getWorldType() instanceof WorldTypeSkyblock ) {
            return new ChunkProviderSkyblockEnd( worldObj, worldObj.getSeed() );
        }
        return new ChunkProviderEnd( worldObj, worldObj.getWorldInfo().isMapFeaturesEnabled(), worldObj.getSeed() );
    }
}
