package com.ernstlustig.faeries.world;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeEndDecorator;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.ChunkProviderEnd;
import net.minecraft.world.gen.feature.WorldGenSpikes;

public class ChunkProviderSkyblockEnd extends ChunkProviderEnd {

    private World world;
    private final WorldGenSpikes spikeGen = new WorldGenSpikes();

    public ChunkProviderSkyblockEnd( World world, long seed ){
        super( world, false, seed );
        this.world = world;
    }

    @Override
    public void populate( int x, int z ){
        if( world.getWorldType() instanceof WorldTypeSkyblock ){
            BlockPos blockpos = new BlockPos( x * 16, 0, z * 16 );

            WorldGenSpikes.EndSpike[] aworldgenspikes$endspike = BiomeEndDecorator.getSpikesForWorld( world );

            for( WorldGenSpikes.EndSpike worldgenspikes$endspike : aworldgenspikes$endspike ){
                if( worldgenspikes$endspike.doesStartInChunk( blockpos ) ){
                    this.spikeGen.setSpike( worldgenspikes$endspike );
                    this.spikeGen.generate( world, world.rand, new BlockPos( worldgenspikes$endspike.getCenterX(), 45, worldgenspikes$endspike.getCenterZ() ) );
                }
            }

            if( x == 0 && z == 0 ){
                world.setBlockState( new BlockPos( 0, 45, 0 ), Blocks.END_STONE.getDefaultState() );
            }
        }
    }

    @Override
    public Chunk provideChunk( int x, int z ){
        return new Chunk( world, new ChunkPrimer(), x, z );
    }

}
