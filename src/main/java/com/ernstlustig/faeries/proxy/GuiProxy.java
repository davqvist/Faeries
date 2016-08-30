package com.ernstlustig.faeries.proxy;

import com.ernstlustig.faeries.container.ContainerFaeryHouse;
import com.ernstlustig.faeries.container.ContainerMill;
import com.ernstlustig.faeries.gui.GuiFaeryHouse;
import com.ernstlustig.faeries.gui.GuiMill;
import com.ernstlustig.faeries.tileentity.TileEntityFaeryHouse;
import com.ernstlustig.faeries.tileentity.TileEntityMill;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiProxy implements IGuiHandler {

    @Override
    public Object getServerGuiElement( int ID, EntityPlayer player, World world, int x, int y, int z ){
        BlockPos pos = new BlockPos( x, y, z );
        TileEntity te = world.getTileEntity( pos );
        if( te instanceof TileEntityFaeryHouse ){
            return new ContainerFaeryHouse( player.inventory, (TileEntityFaeryHouse) te );
        }
        if( te instanceof TileEntityMill){
            return new ContainerMill( player.inventory, (TileEntityMill) te );
        }
        return null;
    }

    @Override
    public Object getClientGuiElement( int ID, EntityPlayer player, World world, int x, int y, int z ){
        BlockPos pos = new BlockPos( x, y, z );
        TileEntity te = world.getTileEntity( pos );
        if( te instanceof TileEntityFaeryHouse ){
            TileEntityFaeryHouse containerTileEntity = (TileEntityFaeryHouse) te;
            return new GuiFaeryHouse( containerTileEntity, new ContainerFaeryHouse( player.inventory, containerTileEntity ) );
        }
        if( te instanceof TileEntityMill ){
            TileEntityMill containerTileEntity = (TileEntityMill) te;
            return new GuiMill( containerTileEntity, new ContainerMill( player.inventory, containerTileEntity ) );
        }
        return null;
    }

}
