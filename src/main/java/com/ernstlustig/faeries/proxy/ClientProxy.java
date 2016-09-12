package com.ernstlustig.faeries.proxy;

import com.ernstlustig.faeries.init.ModBlocks;
import com.ernstlustig.faeries.init.ModItems;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {

    @Override
    public void preInit( FMLPreInitializationEvent e ){
        super.preInit( e );

        ModItems.loadTextures();
        ModBlocks.loadTextures();
    }
}
