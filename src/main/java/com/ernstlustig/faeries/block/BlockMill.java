package com.ernstlustig.faeries.block;

import com.ernstlustig.faeries.Faeries;
import com.ernstlustig.faeries.material.MaterialFaeries;
import com.ernstlustig.faeries.tileentity.TileEntityMill;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nullable;
import java.util.List;

public class BlockMill extends BlockFaeries implements ITileEntityProvider {

    public static final int GUI_ID = 2;
    public static final PropertyDirection FACING = BlockHorizontal.FACING;
    private final AxisAlignedBB BOUNDING_BOX = new AxisAlignedBB( 0.0625 * 2, 0, 0.0625 * 3, 0.0625 * 14, 0.0625 * 20, 0.0625 * 15 );

    public BlockMill(){
        super( MaterialFaeries.STANDARD );
        setHardness( 1.5F );
        setHarvestLevel( "axe", 0 );
        setRegistryName( "mill" );
        this.setUnlocalizedName( "mill" );
        this.setDefaultState( this.blockState.getBaseState().withProperty( FACING, EnumFacing.SOUTH ) );
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
    public AxisAlignedBB getBoundingBox( IBlockState state, IBlockAccess source, BlockPos pos ){
        AxisAlignedBB bb = BOUNDING_BOX;
        if( getMetaFromState( state ) == 0 ){ bb = new AxisAlignedBB( BOUNDING_BOX.minX, BOUNDING_BOX.minY, 1 - BOUNDING_BOX.maxZ, BOUNDING_BOX.maxX, BOUNDING_BOX.maxY, 1 - BOUNDING_BOX.minZ ); }
        if( getMetaFromState( state ) == 1 ){ bb = new AxisAlignedBB( BOUNDING_BOX.minZ, BOUNDING_BOX.minY, BOUNDING_BOX.minX, BOUNDING_BOX.maxZ, BOUNDING_BOX.maxY, BOUNDING_BOX.maxX ); }
        if( getMetaFromState( state ) == 3 ){ bb = new AxisAlignedBB( 1 - BOUNDING_BOX.maxZ, BOUNDING_BOX.minY, BOUNDING_BOX.minX, 1 - BOUNDING_BOX.minZ, BOUNDING_BOX.maxY, BOUNDING_BOX.maxX ); }
        return bb;
    }

    //@Override
    //public AxisAlignedBB getCollisionBoundingBox( IBlockState blockState, World worldIn, BlockPos pos ){ return getBoundingBox( blockState, null, pos ).expandXyz( 0.0625 ); }

    @Override
    public TileEntity createNewTileEntity( World worldIn, int meta ) {
        return new TileEntityMill();
    }

    public IBlockState onBlockPlaced( World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer ){
        return this.getDefaultState().withProperty( FACING, placer.getHorizontalFacing().getOpposite() );
    }

    @Override
    protected BlockStateContainer createBlockState(){
        return new BlockStateContainer( this, new IProperty[] {FACING} );
    }

    @Override
    public int getMetaFromState( IBlockState state )
    {
        EnumFacing facing = (EnumFacing)state.getValue( FACING );
        return facing.getHorizontalIndex();
    }

    @Override
    public boolean onBlockActivated( World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ ){
        if( world.isRemote ){
            return true;
        }
        TileEntity te = world.getTileEntity( pos );
        if ( !( te instanceof TileEntityMill ) ) {
            return false;
        }
        player.openGui( Faeries.instance, GUI_ID, world, pos.getX(), pos.getY(), pos.getZ() );
        return true;
    }

    @Override
    public void breakBlock( World worldIn, BlockPos pos, IBlockState state ){
        TileEntity te = worldIn.getTileEntity( pos );
        if( te instanceof TileEntityMill ){
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
