package com.ernstlustig.faeries.gui;

import com.ernstlustig.faeries.container.ContainerFaeryHouse;
import com.ernstlustig.faeries.item.ItemFaery;
import com.ernstlustig.faeries.reference.Reference;
import com.ernstlustig.faeries.tileentity.TileEntityFaeryHouse;
import com.ernstlustig.faeries.utility.LogHelper;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;
import sun.rmi.runtime.Log;

import java.awt.*;

public class GuiFaeryHouse extends GuiContainer {
    public static final int WIDTH = 180;
    public static final int HEIGHT = 152;
    private final TileEntityFaeryHouse te;

    private static final ResourceLocation background = new ResourceLocation( Reference.MOD_ID, "textures/gui/guifaeryhouse.png" );

    public GuiFaeryHouse( TileEntityFaeryHouse tileEntity, ContainerFaeryHouse container ){
        super( container );

        xSize = WIDTH;
        ySize = HEIGHT;
        te = tileEntity;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer( float partialTicks, int mouseX, int mouseY ){
        mc.getTextureManager().bindTexture( background );
        drawTexturedModalRect( guiLeft, guiTop, 0, 0, xSize, ySize );
        float progress;
        if( te.hasCouple() ){
            progress = 22 - ( (float)te.getTime() * 22 / te.MAX_TIME );
            drawTexturedModalRect( guiLeft + 43, guiTop + 7, 180, 0, Math.round( progress ), 15 );
            progress = 15 - ( (float)ItemFaery.getAge( te.getCouple() ) * 15 / ItemFaery.getLifespan( te.getCouple() ) );
            drawHorizontalLine( guiLeft + 18, guiLeft + 33 - Math.round( progress ), guiTop + 25, new Color(
                    Math.min( 255, Math.round( 510 * ( 1 - ( (float)ItemFaery.getAge( te.getCouple() ) / ItemFaery.getLifespan( te.getCouple() ) ) ) ) ),
                    Math.min( 255, Math.round( 510 * (float)ItemFaery.getAge( te.getCouple() ) / ItemFaery.getLifespan( te.getCouple() ) ) ),
                    0 ).getRGB()
            );
        }
        if( te.canMarry() ){
            progress = 10 - ( (float)te.getMarryTime() * 10 / te.MAX_MARRYTIME );
            drawTexturedModalRect( guiLeft + 20, guiTop + 40 - Math.round( progress ), 180, 26 - Math.round( progress ), 11, Math.round( progress ) );
        }
    }
}

