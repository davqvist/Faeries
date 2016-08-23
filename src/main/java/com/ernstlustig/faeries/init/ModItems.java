package com.ernstlustig.faeries.init;

import com.ernstlustig.faeries.item.ItemBrailer;
import com.ernstlustig.faeries.item.ItemFaeries;
import com.ernstlustig.faeries.item.ItemFaery;
import com.ernstlustig.faeries.renderer.FaeriesMeshDefinition;
import com.ernstlustig.faeries.utility.JsonHelper;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ModItems {

    public static final ItemBrailer brailer = new ItemBrailer();
    public static final ItemFaery faery = new ItemFaery();

    public static void registerItems(){
        GameRegistry.register( brailer );
        GameRegistry.register( faery );
    }

    @SideOnly(Side.CLIENT)
    public static void loadTextures(){
        //Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register( brailer, 0, new ModelResourceLocation( brailer.getUnlocalizedName().substring(5), "inventory" ) );
        ModelLoader.setCustomModelResourceLocation( brailer, 0, new ModelResourceLocation( brailer.getUnlocalizedName().substring(5), "inventory" ) );
        //ModelLoader.setCustomModelResourceLocation( faery, 0, new ModelResourceLocation( faery.getUnlocalizedName().substring(5), "inventory" ) );
        List<ModelResourceLocation> models = new ArrayList<ModelResourceLocation>();
        for( ItemFaery.Race race : ItemFaery.Race.values() ){
            models.add( new ModelResourceLocation( faery.getUnlocalizedName().substring(5) + "_" + race.name().toLowerCase( Locale.ENGLISH ), "inventory" ) );
            //JsonHelper.createJson( Integer.toString( i ) );
        }
        ModelLoader.registerItemVariants( faery, models.toArray( new ModelResourceLocation[models.size()] ) );
        ModelLoader.setCustomMeshDefinition( faery, new FaeriesMeshDefinition() );
    }

}
