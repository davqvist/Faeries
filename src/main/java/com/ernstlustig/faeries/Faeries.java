package com.ernstlustig.faeries;

import com.ernstlustig.faeries.init.ModItems;
import com.ernstlustig.faeries.proxy.IProxy;
import com.ernstlustig.faeries.reference.Reference;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod( modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, guiFactory = Reference.GUI_FACTORY_CLASS )
public class Faeries {

    @Mod.Instance( Reference.MOD_ID )
    public static Faeries instance;

    @SidedProxy( clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS )
    public static IProxy proxy;

    @Mod.EventHandler
    public void preInit( FMLPreInitializationEvent event ){
        //ConfigurationHandler.init( event.getSuggestedConfigurationFile() );
        //FMLCommonHandler.instance().bus().register( new ConfigurationHandler() );
        ModItems.registerItems();
        //ModBlocks.registerBlocks();
    }

    @Mod.EventHandler
    public void init( FMLInitializationEvent event ){
        ModItems.loadTextures();
        //ModBlocks.loadTextures();
    }

    @Mod.EventHandler
    public void postInit( FMLPostInitializationEvent event ){

    }

}
