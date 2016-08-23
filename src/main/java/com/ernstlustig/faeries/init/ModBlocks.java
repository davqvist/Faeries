package com.ernstlustig.faeries.init;

import com.ernstlustig.faeries.block.BlockFaeries;
import com.ernstlustig.faeries.block.BlockFaeryHouse;
import com.ernstlustig.faeries.reference.Reference;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@GameRegistry.ObjectHolder( Reference.MOD_ID )
public class ModBlocks {
    public static final BlockFaeries faeryHouse = new BlockFaeryHouse();

    public static void registerBlocks(){
        GameRegistry.register( faeryHouse );
        GameRegistry.register( new ItemBlock( faeryHouse ), faeryHouse.getRegistryName() );
    }

    @SideOnly(Side.CLIENT)
    public static void loadTextures(){
        Item blockItem = Item.getItemFromBlock( faeryHouse );
        ModelLoader.setCustomModelResourceLocation( blockItem, 0, new ModelResourceLocation( faeryHouse.getUnlocalizedName().substring(5), "inventory"));
        //Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register( blockItem, 0, new ModelResourceLocation( faeryHouse.getUnlocalizedName().substring(5), "inventory" ) );
        //LogHelper.info( "INFO: "+faeryHouse.getUnlocalizedName().substring(5) );
    }
}