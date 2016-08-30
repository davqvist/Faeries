package com.ernstlustig.faeries.init;

import com.ernstlustig.faeries.block.BlockFaeries;
import com.ernstlustig.faeries.block.BlockFaeryHouse;
import com.ernstlustig.faeries.block.BlockMill;
import com.ernstlustig.faeries.tileentity.TileEntityFaeryHouse;
import com.ernstlustig.faeries.reference.Reference;
import com.ernstlustig.faeries.tileentity.TileEntityMill;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@GameRegistry.ObjectHolder( Reference.MOD_ID )
public class ModBlocks {
    public static final BlockFaeryHouse faeryHouse = new BlockFaeryHouse();
    public static final BlockMill mill = new BlockMill();

    public static void registerBlocks(){
        GameRegistry.register( faeryHouse );
        GameRegistry.register( new ItemBlock( faeryHouse ), faeryHouse.getRegistryName() );
        GameRegistry.registerTileEntity( TileEntityFaeryHouse.class, Reference.MOD_ID + "_tileentityfaeryhouse" );
        GameRegistry.register( mill );
        GameRegistry.register( new ItemBlock( mill ), mill.getRegistryName() );
        GameRegistry.registerTileEntity( TileEntityMill.class, Reference.MOD_ID + "_tileentitymill" );
    }

    @SideOnly(Side.CLIENT)
    public static void loadTextures(){
        ModelLoader.setCustomModelResourceLocation( Item.getItemFromBlock( faeryHouse ), 0, new ModelResourceLocation( faeryHouse.getUnlocalizedName().substring(5), "inventory"));
        ModelLoader.setCustomModelResourceLocation( Item.getItemFromBlock( mill ), 0, new ModelResourceLocation( mill.getUnlocalizedName().substring(5), "inventory"));
        //Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register( blockItem, 0, new ModelResourceLocation( faeryHouse.getUnlocalizedName().substring(5), "inventory" ) );
        //LogHelper.info( "INFO: "+faeryHouse.getUnlocalizedName().substring(5) );
    }
}