package com.ernstlustig.faeries.world;

import com.ernstlustig.faeries.init.ModBlocks;
import com.ernstlustig.faeries.init.ModItems;
import com.ernstlustig.faeries.utility.LogHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.terraingen.WorldTypeEvent;
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
        //PLATFORM
        for( int i = 0; i < 5; i++ ) {
            for( int j = 0; j < 3; j++ ){
                world.setBlockState( new BlockPos( x - 2 + i, y - 1, z - 1 + j ), Blocks.GRASS.getDefaultState(), 3 );
                world.setBlockState( new BlockPos( x - 2 + i, y - 2, z - 1 + j ), Blocks.BEDROCK.getDefaultState(), 3 );
            }
            world.setBlockState( new BlockPos( x - 2 + i, y, z + 1 ), ModBlocks.faeryhouse.getStateFromMeta( 2 ), 3 );
        }
        //NETHER PORTAL
        world.setBlockState( new BlockPos( x, y, z + 64 ), Blocks.OBSIDIAN.getDefaultState(), 3 );
        world.setBlockState( new BlockPos( x + 1, y, z + 64 ), Blocks.OBSIDIAN.getDefaultState(), 3 );
        world.setBlockState( new BlockPos( x - 1, y + 1, z + 64 ), Blocks.OBSIDIAN.getDefaultState(), 3 );
        world.setBlockState( new BlockPos( x - 1, y + 2, z + 64 ), Blocks.OBSIDIAN.getDefaultState(), 3 );
        world.setBlockState( new BlockPos( x - 1, y + 3, z + 64 ), Blocks.OBSIDIAN.getDefaultState(), 3 );
        world.setBlockState( new BlockPos( x + 2, y + 1, z + 64 ), Blocks.OBSIDIAN.getDefaultState(), 3 );
        world.setBlockState( new BlockPos( x + 2, y + 2, z + 64 ), Blocks.OBSIDIAN.getDefaultState(), 3 );
        world.setBlockState( new BlockPos( x + 2, y + 3, z + 64 ), Blocks.OBSIDIAN.getDefaultState(), 3 );
        world.setBlockState( new BlockPos( x, y + 4, z + 64 ), Blocks.OBSIDIAN.getDefaultState(), 3 );
        world.setBlockState( new BlockPos( x + 1, y + 4, z + 64 ), Blocks.OBSIDIAN.getDefaultState(), 3 );
        //END PORTAL
        world.setBlockState( new BlockPos( x - 1, y, z + 128 ), Blocks.END_PORTAL_FRAME.getStateFromMeta( 0 ), 3 );
        world.setBlockState( new BlockPos( x, y, z + 128 ), Blocks.END_PORTAL_FRAME.getStateFromMeta( 0 ), 3 );
        world.setBlockState( new BlockPos( x + 1, y, z + 128 ), Blocks.END_PORTAL_FRAME.getStateFromMeta( 0 ), 3 );
        world.setBlockState( new BlockPos( x - 2, y, z + 129 ), Blocks.END_PORTAL_FRAME.getStateFromMeta( 3 ), 3 );
        world.setBlockState( new BlockPos( x - 2, y, z + 130 ), Blocks.END_PORTAL_FRAME.getStateFromMeta( 3 ), 3 );
        world.setBlockState( new BlockPos( x - 2, y, z + 131 ), Blocks.END_PORTAL_FRAME.getStateFromMeta( 3 ), 3 );
        world.setBlockState( new BlockPos( x + 2, y, z + 129 ), Blocks.END_PORTAL_FRAME.getStateFromMeta( 1 ), 3 );
        world.setBlockState( new BlockPos( x + 2, y, z + 130 ), Blocks.END_PORTAL_FRAME.getStateFromMeta( 1 ), 3 );
        world.setBlockState( new BlockPos( x + 2, y, z + 131 ), Blocks.END_PORTAL_FRAME.getStateFromMeta( 1 ), 3 );
        world.setBlockState( new BlockPos( x - 1, y, z + 132 ), Blocks.END_PORTAL_FRAME.getStateFromMeta( 2 ), 3 );
        world.setBlockState( new BlockPos( x, y, z + 132 ), Blocks.END_PORTAL_FRAME.getStateFromMeta( 2 ), 3 );
        world.setBlockState( new BlockPos( x + 1, y, z + 132 ), Blocks.END_PORTAL_FRAME.getStateFromMeta( 2 ), 3 );
    }
}
