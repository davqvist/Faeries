package com.ernstlustig.faeries.gui;

import com.ernstlustig.faeries.container.ContainerSqueezer;
import com.ernstlustig.faeries.reference.Reference;
import com.ernstlustig.faeries.tileentity.TileEntitySqueezer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

import java.awt.*;
import java.util.*;

public class GuiSqueezer extends GuiContainer {

    public static final int WIDTH = 180;
    public static final int HEIGHT = 152;
    private final TileEntitySqueezer te;

    private static final ResourceLocation background = new ResourceLocation( Reference.MOD_ID, "textures/gui/guisqueezer.png" );

    public GuiSqueezer( TileEntitySqueezer tileEntity, ContainerSqueezer container ){
        super( container );

        xSize = WIDTH;
        ySize = HEIGHT;
        te = tileEntity;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer( float partialTicks, int mouseX, int mouseY ){
        mc.getTextureManager().bindTexture( background );
        drawTexturedModalRect( guiLeft, guiTop, 0, 0, xSize, ySize );
        if( te.hasDrop() ){
            float progress = 22 - ( (float)te.getTime() * 22 / te.MAX_TIME );
            drawTexturedModalRect( guiLeft + 43, guiTop + 25, 180, 0, Math.round( progress ), 15 );
        }
        FluidStack fluid = te.getFluidTank().getFluid();
        if( fluid != null && fluid.amount > 0 ) {
            ResourceLocation location = te.getFluidTank().getFluid().getFluid().getStill();
            TextureAtlasSprite sprite = mc.getTextureMapBlocks().getAtlasSprite( location.toString() );
            mc.renderEngine.bindTexture( TextureMap.LOCATION_BLOCKS_TEXTURE );
            float filllevel = (float)fluid.amount * 48 / te.CAPACITY;
            drawTexturedModalRect( guiLeft + 82, guiTop + 54 - Math.round( filllevel ), sprite, 16, Math.round( filllevel ) );

            if( mouseX >= guiLeft + 82 && mouseX <= guiLeft + 82 + 15 && mouseY >= guiTop + 6 && mouseY <= guiTop + 53 ){
                ArrayList<String> textLines = new ArrayList<String>();
                textLines.add( fluid.getLocalizedName() );
                textLines.add( fluid.amount + "/" + te.getFluidTank().getCapacity() + " mB" );
                drawHoveringText( textLines, mouseX, mouseY );
            }
        }
    }



}
