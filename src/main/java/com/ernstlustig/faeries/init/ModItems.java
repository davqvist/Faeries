package com.ernstlustig.faeries.init;

import com.ernstlustig.faeries.item.*;
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
    public static final ItemWaterDrop waterdrop = new ItemWaterDrop();
    public static final ItemFaeryDust faerydust = new ItemFaeryDust();

    public static void registerItems(){
        GameRegistry.register( brailer );
        GameRegistry.register( faery );
        GameRegistry.register( waterdrop );
        GameRegistry.register( faerydust );
    }

    @SideOnly(Side.CLIENT)
    public static void loadTextures(){
        //Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register( brailer, 0, new ModelResourceLocation( brailer.getUnlocalizedName().substring(5), "inventory" ) );
        ModelLoader.setCustomModelResourceLocation( brailer, 0, new ModelResourceLocation( brailer.getUnlocalizedName().substring(5), "inventory" ) );
        List<ModelResourceLocation> models = new ArrayList<ModelResourceLocation>();
        for( EnumRace race : EnumRace.values() ){
            for( ItemFaery.EnumGender gender: ItemFaery.EnumGender.values() ){
                models.add( new ModelResourceLocation( faery.getUnlocalizedName().substring(5) + "_" + race.name().toLowerCase( Locale.ENGLISH ) + "_" + gender.name().toLowerCase( Locale.ENGLISH ), "inventory" ) );
                //JsonHelper.createJson( Integer.toString( i ) );
            }
        }
        ModelLoader.registerItemVariants( faery, models.toArray( new ModelResourceLocation[models.size()] ) );
        ModelLoader.setCustomMeshDefinition( faery, new FaeriesMeshDefinition() );
        ModelLoader.setCustomModelResourceLocation( waterdrop, 0, new ModelResourceLocation( waterdrop.getUnlocalizedName().substring(5), "inventory" ) );
        ModelLoader.setCustomModelResourceLocation( faerydust, 0, new ModelResourceLocation( faerydust.getUnlocalizedName().substring(5), "inventory" ) );
    }

}
