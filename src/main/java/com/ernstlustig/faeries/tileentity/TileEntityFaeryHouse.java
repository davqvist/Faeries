package com.ernstlustig.faeries.tileentity;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;

public class TileEntityFaeryHouse extends TileEntity implements ITickable {

    public static final int INPUT_SIZE = 1;
    public static final int OUTPUT_SIZE = 15;
    private int time;
    private boolean flag = false;
    public static final int MAX_TIME = 400;

    private ItemStackHandler inputStack = new ItemStackHandler( INPUT_SIZE ) {
        @Override
        protected void onContentsChanged( int slot ){
            TileEntityFaeryHouse.this.markDirty();
        }
    };
    private ItemStackHandler outputStack = new ItemStackHandler( OUTPUT_SIZE ) {
        @Override
        protected void onContentsChanged( int slot ){
            TileEntityFaeryHouse.this.markDirty();
        }
    };

    @Override
    public void update() {
        if( !hasFaery() ){
            time = 0;
            flag = false;
        }
        if( time <= 0 && hasFaery() ){
            if( flag && !worldObj.isRemote ){
                EntityItem produce = new EntityItem( worldObj, pos.getX(), pos.getY()+1, pos.getZ(), new ItemStack( Items.STICK ) );
                worldObj.spawnEntityInWorld( produce );
            }
            time = MAX_TIME;
            flag = true;
        }
        time--;
        if( hasFaery() ){
            markDirty();
        }
    }

    @Override
    public void readFromNBT( NBTTagCompound compound ){
        super.readFromNBT( compound );
        if( compound.hasKey( "input" ) ){
            inputStack.deserializeNBT( (NBTTagCompound) compound.getTag( "input" ) );
        }
        if( compound.hasKey( "output" ) ){
            outputStack.deserializeNBT( (NBTTagCompound) compound.getTag( "output" ) );
        }
        if( compound.hasKey( "time" ) ){
            time = compound.getInteger( "time" );
        }
    }

    @Override
    public NBTTagCompound writeToNBT( NBTTagCompound compound ){
        super.writeToNBT( compound );
        compound.setTag( "input", inputStack.serializeNBT() );
        compound.setTag( "output", outputStack.serializeNBT() );
        compound.setInteger( "time", time );
        return compound;
    }

    public boolean canInteractWith( EntityPlayer playerIn ){
        return !isInvalid() && playerIn.getDistanceSq( pos.add( 0.5D, 0.5D, 0.5D ) ) <= 64D;
    }

    @Override
    public boolean hasCapability( Capability<?> capability, EnumFacing facing ){
        if( capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ){
            return true;
        }
        return super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability( Capability<T> capability, EnumFacing facing ){
        if( capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ){
            if(  facing == null ){ return (T) new CombinedInvWrapper( inputStack, outputStack ); }
            return (T) outputStack;
        }
        return super.getCapability(capability, facing);
    }

    public int getTime(){
        return time;
    }

    public boolean hasFaery(){
        return ( inputStack.getStackInSlot(0) != null );
    }

}