package com.ernstlustig.faeries.world;

import com.ernstlustig.faeries.init.ModBlocks;
import com.ernstlustig.faeries.init.ModItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SkyblockWorldEvents {

    @SubscribeEvent
    public void onPlayerUpdate( LivingEvent.LivingUpdateEvent event ){
        if( event.getEntityLiving() instanceof EntityPlayer && !event.getEntity().worldObj.isRemote ){
            EntityPlayer player = (EntityPlayer) event.getEntityLiving();
            NBTTagCompound data = player.getEntityData();
            if( !data.hasKey( EntityPlayer.PERSISTED_NBT_TAG ) ) {
                data.setTag( EntityPlayer.PERSISTED_NBT_TAG, new NBTTagCompound() );
            }
            NBTTagCompound nbtTagCompound = data.getCompoundTag( EntityPlayer.PERSISTED_NBT_TAG );

            if( player.ticksExisted > 3 && !nbtTagCompound.getBoolean( "made_island" ) ){
                World world = player.worldObj;
                if( WorldTypeSkyblock.isWorldSkyblock( world ) ){
                    BlockPos coords = world.getSpawnPoint();
                    if( world.provider.getDimension() == 0 ) {
                        spawnPlayer( player, coords.getX(), coords.getY(), coords.getZ(), false );
                    }
                }

                nbtTagCompound.setBoolean( "made_island", true );
            }
        }
    }

    public static void spawnPlayer(EntityPlayer player, int x, int y, int z, boolean fabricated) {

        createSkyblock( player.worldObj, x, y, z );

        if( player instanceof EntityPlayerMP) {
            EntityPlayerMP pmp = (EntityPlayerMP) player;
            pmp.setPositionAndUpdate( x + 0.5, y + 1.6, z + 0.5 );
            pmp.setSpawnChunk( new BlockPos( x, y, z ), true, 0 );
            player.inventory.addItemStackToInventory( new ItemStack( ModItems.brailer ) );
        }
    }

    public static void createSkyblock( World world, int x, int y, int z ){
        for( int i = 0; i < 5; i++ ) {
            for (int j = 0; j < 3; j++) {
                world.setBlockState( new BlockPos( x - 2 + i, y - 1, z - 1 + j ), Blocks.GRASS.getDefaultState(), 3 );
                world.setBlockState( new BlockPos( x, y - 2, z ), Blocks.BEDROCK.getDefaultState(), 3 );
            }
            world.setBlockState( new BlockPos( x - 2 + i, y, z + 1 ), ModBlocks.faeryHouse.getDefaultState(), 3 );
        }
    }
}
