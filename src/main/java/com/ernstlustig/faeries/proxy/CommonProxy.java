package com.ernstlustig.faeries.proxy;

import com.ernstlustig.faeries.Faeries;
import com.ernstlustig.faeries.init.ModBlocks;
import com.ernstlustig.faeries.init.ModItems;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public abstract class CommonProxy {

    public void preInit( FMLPreInitializationEvent e ){
        //ConfigurationHandler.init( event.getSuggestedConfigurationFile() );
        //FMLCommonHandler.instance().bus().register( new ConfigurationHandler() );
        ModItems.registerItems();
        ModBlocks.registerBlocks();

        ModItems.loadTextures();
        ModBlocks.loadTextures();
        NetworkRegistry.INSTANCE.registerGuiHandler( Faeries.instance, new GuiProxy() );
    }

    public void init( FMLInitializationEvent e ) {

    }

    public void postInit( FMLPostInitializationEvent e ) {

    }
}
