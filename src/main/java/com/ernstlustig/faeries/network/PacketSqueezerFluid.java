package com.ernstlustig.faeries.network;

import com.ernstlustig.faeries.tileentity.TileEntitySqueezer;
import com.ernstlustig.faeries.utility.LogHelper;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IThreadListener;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketSqueezerFluid extends PacketTileEntity<TileEntitySqueezer> implements IMessageHandler<PacketSqueezerFluid, IMessage>, IMessage {

    private NBTTagCompound compound;

    public PacketSqueezerFluid() { }

    public PacketSqueezerFluid( TileEntitySqueezer te ){
        super( te );
        compound = new NBTTagCompound();
        if( te.getFluidTank().getFluidAmount() > 0 ){
            te.getFluidTank().writeToNBT( compound );
        }
    }

    @Override
    public void fromBytes( ByteBuf buf ){
        super.fromBytes( buf );
        compound = ByteBufUtils.readTag( buf );
    }

    @Override
    public void toBytes( ByteBuf buf ){
        super.toBytes( buf );
        ByteBufUtils.writeTag( buf, compound );
    }


    @Override
    public IMessage onMessage( PacketSqueezerFluid message, MessageContext ctx ){
        TileEntitySqueezer te = message.getTileEntity( Minecraft.getMinecraft().theWorld );
        if( te == null ){ return null; }
        te.getFluidTank().readFromNBT( message.compound );
        if( te.getFluidTank().getFluidAmount() > 0 ) {
            LogHelper.info( te.getFluidTank().getFluid().getLocalizedName() + " : " + te.getFluidTank().getFluidAmount() );
        }
        return null;
    }

}
