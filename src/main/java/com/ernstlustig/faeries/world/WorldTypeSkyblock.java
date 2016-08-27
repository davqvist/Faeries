package com.ernstlustig.faeries.world;

import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderFlat;

public class WorldTypeSkyblock extends WorldType {

    public WorldTypeSkyblock() {
        super("faeries-skyblock");
    }

    public static boolean isWorldSkyblock( World world ){
        return world.getWorldInfo().getTerrainType() instanceof WorldTypeSkyblock;
    }

    @Override
    public int getMinimumSpawnHeight( World world ){
        return 70;
    }

    @Override
    public net.minecraft.world.chunk.IChunkGenerator getChunkGenerator( World world, String generatorOptions ){
        return new ChunkProviderFlat( world, world.getSeed(), false, "2;1x0;" );
    }
}
