package com.ernstlustig.faeries.proxy;

import com.ernstlustig.faeries.Faeries;
import com.ernstlustig.faeries.init.ModBlocks;
import com.ernstlustig.faeries.init.ModItems;
import com.ernstlustig.faeries.world.SkyblockWorldEvents;
import com.ernstlustig.faeries.world.WorldTypeSkyblock;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
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

        new WorldTypeSkyblock();
    }

    public void init( FMLInitializationEvent e ) {
        MinecraftForge.EVENT_BUS.register( new SkyblockWorldEvents() );
    }

    public void postInit( FMLPostInitializationEvent e ) {

    }
}
