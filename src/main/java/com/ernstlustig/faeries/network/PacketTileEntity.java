package com.ernstlustig.faeries.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class PacketTileEntity<T extends TileEntity> implements IMessage {

    private long posserialized;
    protected int x;
    protected int y;
    protected int z;

    protected PacketTileEntity() {
    }

    protected PacketTileEntity( T tileentity ) {
        posserialized = tileentity.getPos().toLong();
    }

    @Override
    public void toBytes( ByteBuf buf ){
        buf.writeLong( posserialized );
    }

    @Override
    public void fromBytes( ByteBuf buf ){
        posserialized = buf.readLong();
        BlockPos bp = getBlockPos();
        x = bp.getX();
        y = bp.getY();
        z = bp.getZ();
    }

    public BlockPos getBlockPos() {
        return BlockPos.fromLong( posserialized );
    }

    protected T getTileEntity( World worldObj ){
        if( worldObj == null ){ return null; }
        TileEntity te = worldObj.getTileEntity( getBlockPos() );
        if( te == null ){ return null; }
        return (T) te;
    }
}
