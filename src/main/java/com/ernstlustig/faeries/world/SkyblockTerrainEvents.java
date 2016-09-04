package com.ernstlustig.faeries.world;

import com.ernstlustig.faeries.utility.LogHelper;
import net.minecraftforge.event.terraingen.WorldTypeEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SkyblockTerrainEvents {

    @SubscribeEvent
    public void onBiomeSize( WorldTypeEvent.BiomeSize event ){
        if( event.getWorldType().getWorldTypeName().equals( "faeries-skyblock" ) ){
            event.setNewSize(1);
        }
    }
}