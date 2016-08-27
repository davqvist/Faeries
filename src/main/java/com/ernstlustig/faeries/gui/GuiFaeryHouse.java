package com.ernstlustig.faeries.gui;

import com.ernstlustig.faeries.container.ContainerFaeryHouse;
import com.ernstlustig.faeries.reference.Reference;
import com.ernstlustig.faeries.tileentity.TileEntityFaeryHouse;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;

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
        if( te.hasFaery() ){
            float progress = 22 - ( te.getTime() * 22 / te.MAX_TIME );
            drawTexturedModalRect( guiLeft + 43, guiTop + 7, 180, 0, Math.round( progress ), 15 );
        }
    }
}

