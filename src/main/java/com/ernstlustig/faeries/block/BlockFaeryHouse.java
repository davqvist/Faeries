package com.ernstlustig.faeries.block;

import com.ernstlustig.faeries.Faeries;
import com.ernstlustig.faeries.init.ModBlocks;
import com.ernstlustig.faeries.tileentity.TileEntityFaeryHouse;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import java.util.ArrayList;

public class BlockFaeryHouse extends BlockFaeries implements ITileEntityProvider {

    public static final int GUI_ID = 1;

    public BlockFaeryHouse(){
        super( Material.WOOD );
        setHardness(2.5F);
        setRegistryName("faeryHouse");
        this.setUnlocalizedName("faeryHouse");
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.SOLID;
    }

    @Override
    public boolean isBlockNormalCube(IBlockState blockState) {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState blockState) {
        return false;
    }

    @Override
    public TileEntity createNewTileEntity( World worldIn, int meta ) {
        return new TileEntityFaeryHouse();
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ ){
        if( world.isRemote ){
            return true;
        }
        TileEntity te = world.getTileEntity( pos );
        if ( !( te instanceof TileEntityFaeryHouse ) ) {
            return false;
        }
        player.openGui( Faeries.instance, GUI_ID, world, pos.getX(), pos.getY(), pos.getZ() );
        return true;
    }

    @Override
    public void breakBlock( World worldIn, BlockPos pos, IBlockState state ){
        TileEntity te = worldIn.getTileEntity( pos );
        if( te instanceof TileEntityFaeryHouse ){
            IItemHandler ih = te.getCapability( CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null );
            for( int i=0; i<ih.getSlots(); i++ ){
                if( ih.getStackInSlot( i ) != null ){
                    worldIn.spawnEntityInWorld( new EntityItem( worldIn, pos.getX(), pos.getY(), pos.getZ(), ih.getStackInSlot( i ) ) );
                }
            }
        }

        super.breakBlock(worldIn, pos, state);
    }
}
