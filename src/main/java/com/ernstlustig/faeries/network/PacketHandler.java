package com.ernstlustig.faeries.network;

import com.ernstlustig.faeries.reference.Reference;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

public class PacketHandler {

    public static final SimpleNetworkWrapper INSTANCE = new SimpleNetworkWrapper( Reference.MOD_ID );

    private static int ID = 0;

    public static int nextID() {
        return ID++;
    }

    public static void sendToAllAround( IMessage message, TileEntity te, int range ){
        BlockPos p = te.getPos();
        INSTANCE.sendToAllAround( message, new TargetPoint( te.getWorld().provider.getDimension(), p.getX(), p.getY(), p.getZ(), range ) );
    }

    public static void sendTo( IMessage message, EntityPlayerMP player ){
        INSTANCE.sendTo( message, player );
    }
}
