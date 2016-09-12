package com.ernstlustig.faeries.block;

import com.ernstlustig.faeries.Faeries;
import com.ernstlustig.faeries.material.MaterialFaeries;
import com.ernstlustig.faeries.network.PacketHandler;
import com.ernstlustig.faeries.network.PacketSqueezerFluid;
import com.ernstlustig.faeries.tileentity.TileEntityMill;
import com.ernstlustig.faeries.tileentity.TileEntitySqueezer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class BlockSqueezer extends BlockFaeries implements ITileEntityProvider {

    public static final int GUI_ID = 3;

    public BlockSqueezer(){
        super( MaterialFaeries.STANDARD );
        setHardness( 1.5F );
        setHarvestLevel( "axe", 0 );
        setRegistryName( "squeezer" );
        this.setUnlocalizedName( "squeezer" );
        PacketHandler.INSTANCE.registerMessage( PacketSqueezerFluid.class, PacketSqueezerFluid.class, PacketHandler.nextID(), Side.CLIENT );
    }

    @Override
    public boolean isBlockNormalCube( IBlockState blockState ){
        return false;
    }

    @Override
    public boolean isOpaqueCube( IBlockState blockState ){
        return false;
    }

    @Override
    public TileEntity createNewTileEntity( World worldIn, int meta ) {
        return new TileEntitySqueezer();
    }

    @Override
    public boolean onBlockActivated( World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ ){
        TileEntity te = world.getTileEntity( pos );
        if ( !( te instanceof TileEntitySqueezer ) ) {
            return false;
        }
        if( te.hasCapability( CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, side ) ){
            IFluidHandler fluidHandler = te.getCapability( CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, side );
            boolean success = FluidUtil.interactWithFluidHandler( heldItem, fluidHandler, player );
            if( success ){ return true; }
        }
        if( !world.isRemote ){
            player.openGui( Faeries.instance, GUI_ID, world, pos.getX(), pos.getY(), pos.getZ() );
        }
       return true;
    }

    @Override
    public void breakBlock( World worldIn, BlockPos pos, IBlockState state ){
        TileEntity te = worldIn.getTileEntity( pos );
        if( te instanceof TileEntitySqueezer ){
            IItemHandler ih = te.getCapability( CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null );
            for( int i=0; i<ih.getSlots(); i++ ){
                if( ih.getStackInSlot( i ) != null ){
                    worldIn.spawnEntityInWorld( new EntityItem( worldIn, pos.getX(), pos.getY(), pos.getZ(), ih.getStackInSlot( i ) ) );
                }
            }
        }

        super.breakBlock( worldIn, pos, state );
    }
}
