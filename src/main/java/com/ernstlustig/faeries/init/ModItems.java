package com.ernstlustig.faeries.init;

import com.ernstlustig.faeries.item.ItemBrailer;
import com.ernstlustig.faeries.item.ItemFaeries;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModItems {

    public static final ItemFaeries brailer = new ItemBrailer();

    public static void registerItems(){
        GameRegistry.register( brailer );
    }

    @SideOnly(Side.CLIENT)
    public static void loadTextures(){
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register( brailer, 0, new ModelResourceLocation( brailer.getUnlocalizedName().substring(5), "inventory" ) );
        //ModelLoader.setCustomModelResourceLocation( brailer, 0, new ModelResourceLocation( brailer.getUnlocalizedName().substring(5), "inventory" ) );
    }

}
