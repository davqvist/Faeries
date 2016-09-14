package com.ernstlustig.faeries.world;

import net.minecraftforge.event.terraingen.WorldTypeEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SkyblockTerrainEvents {

    @SubscribeEvent
    public void onBiomeSize( WorldTypeEvent.BiomeSize event ){
        if( event.getWorldType() instanceof WorldTypeSkyblock ){
            event.setNewSize(1);
        }
    }
}