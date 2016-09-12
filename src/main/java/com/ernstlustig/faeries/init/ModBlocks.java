package com.ernstlustig.faeries.init;

import com.ernstlustig.faeries.block.BlockFaeryHouse;
import com.ernstlustig.faeries.block.BlockFaeryVilla;
import com.ernstlustig.faeries.block.BlockMill;
import com.ernstlustig.faeries.block.BlockSqueezer;
import com.ernstlustig.faeries.tileentity.TileEntityFaeryHouse;
import com.ernstlustig.faeries.reference.Reference;
import com.ernstlustig.faeries.tileentity.TileEntityMill;
import com.ernstlustig.faeries.tileentity.TileEntitySqueezer;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@GameRegistry.ObjectHolder( Reference.MOD_ID )
public class ModBlocks {
    public static final BlockFaeryHouse faeryhouse = new BlockFaeryHouse();
    public static final BlockFaeryVilla faeryvilla = new BlockFaeryVilla();
    public static final BlockMill mill = new BlockMill();
    public static final BlockSqueezer squeezer = new BlockSqueezer();

    public static void registerBlocks(){
        GameRegistry.register( faeryhouse );
        GameRegistry.register( new ItemBlock( faeryhouse ), faeryhouse.getRegistryName() );
        GameRegistry.registerTileEntity( TileEntityFaeryHouse.class, Reference.MOD_ID + "_tileentityfaeryhouse" );
        GameRegistry.register( faeryvilla );
        GameRegistry.register( new ItemBlock( faeryvilla ), faeryvilla.getRegistryName() );
        GameRegistry.register( mill );
        GameRegistry.register( new ItemBlock( mill ), mill.getRegistryName() );
        GameRegistry.registerTileEntity( TileEntityMill.class, Reference.MOD_ID + "_tileentitymill" );
        GameRegistry.register( squeezer );
        GameRegistry.register( new ItemBlock( squeezer ), squeezer.getRegistryName() );
        GameRegistry.registerTileEntity( TileEntitySqueezer.class, Reference.MOD_ID + "_tileentitysqueezer" );
    }

    @SideOnly(Side.CLIENT)
    public static void loadTextures(){
        ModelLoader.setCustomModelResourceLocation( Item.getItemFromBlock( faeryhouse ), 0, new ModelResourceLocation( faeryhouse.getUnlocalizedName().substring(5), "inventory"));
        ModelLoader.setCustomModelResourceLocation( Item.getItemFromBlock( faeryvilla ), 0, new ModelResourceLocation( faeryvilla.getUnlocalizedName().substring(5), "inventory"));
        ModelLoader.setCustomModelResourceLocation( Item.getItemFromBlock( mill ), 0, new ModelResourceLocation( mill.getUnlocalizedName().substring(5), "inventory"));
        ModelLoader.setCustomModelResourceLocation( Item.getItemFromBlock( squeezer ), 0, new ModelResourceLocation( squeezer.getUnlocalizedName().substring(5), "inventory"));
        //Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register( blockItem, 0, new ModelResourceLocation( faeryHouse.getUnlocalizedName().substring(5), "inventory" ) );
    }
}