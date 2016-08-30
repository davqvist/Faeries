package com.ernstlustig.faeries.block;

import com.ernstlustig.faeries.Faeries;
import com.ernstlustig.faeries.tileentity.TileEntityFaeryHouse;
import com.ernstlustig.faeries.tileentity.TileEntityMill;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
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
    private final AxisAlignedBB BOUNDING_BOX = new AxisAlignedBB( 0.0625 * 4, 0, 0.0625 * 4, 0.0625 * 12, 0.0625 * 13, 0.0625 * 12 );

    public BlockMill(){
        super( Material.WOOD );
        setHardness( 2.5F );
        setRegistryName( "mill" );
        this.setUnlocalizedName( "mill" );
        this.setDefaultState( this.blockState.getBaseState().withProperty( FACING, EnumFacing.SOUTH ) );
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
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos){
        return BOUNDING_BOX;
    }

    @Override
    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn){
        super.addCollisionBoxToList( pos, entityBox, collidingBoxes, BOUNDING_BOX );
    }

    @Override
    public TileEntity createNewTileEntity( World worldIn, int meta ) {
        return new TileEntityMill();
    }

    public IBlockState onBlockPlaced( World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer ){
        return this.getDefaultState().withProperty( FACING, placer.getHorizontalFacing().getOpposite() );
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer( this, new IProperty[] {FACING} );
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        EnumFacing facing = (EnumFacing)state.getValue( FACING );
        return facing.getHorizontalIndex();
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ ){
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
