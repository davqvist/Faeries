package com.ernstlustig.faeries.gui;

import com.ernstlustig.faeries.container.ContainerMill;
import com.ernstlustig.faeries.reference.Reference;
import com.ernstlustig.faeries.tileentity.TileEntityMill;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;

import java.awt.*;

public class GuiMill extends GuiContainer {
    public static final int WIDTH = 180;
    public static final int HEIGHT = 152;
    private final TileEntityMill te;

    private static final ResourceLocation background = new ResourceLocation( Reference.MOD_ID, "textures/gui/guimill.png" );

    public GuiMill( TileEntityMill tileEntity, ContainerMill container ){
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
        if( te.hasFood() ){
            progress = 22 - ( (float)te.getTime() * 22 / te.MAX_TIME );
            drawTexturedModalRect( guiLeft + 53, guiTop + 24, 180, 0, Math.round( progress ), 15 );
        }
    }
}
