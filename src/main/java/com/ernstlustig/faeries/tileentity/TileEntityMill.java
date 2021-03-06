package com.ernstlustig.faeries.tileentity;

import com.ernstlustig.faeries.init.ModItems;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;

public class TileEntityMill extends TileEntity implements ITickable {

    public static final int INPUT_SIZE = 1;
    public static final int OUTPUT_SIZE = 9;
    private int time;
    private boolean flag;
    public static final int MAX_TIME = 400;

    private MillStackHandler inputStack = new MillStackHandler( this, INPUT_SIZE );
    private OutputStackHandler outputStack = new OutputStackHandler( this, OUTPUT_SIZE );

    @Override
    public void update() {
        if( !hasFood() ){
            time = 0;
            flag = false;
        }
        if( time <= 0 && hasFood() ) {
            if( flag && !worldObj.isRemote ){
                if( inputStack.getStackInSlot(0).getItem() instanceof ItemFood ){
                    int healamount = ((ItemFood) inputStack.getStackInSlot(0).getItem()).getHealAmount( inputStack.getStackInSlot(0) );
                    setItemStackInOutputSlot( new ItemStack( ModItems.mashedfood, healamount ) );
                    inputStack.extractItem( 0, 1, false );
                }
            }
            time = MAX_TIME;
            flag = true;
        }
        time--;
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

    public NBTTagCompound getUpdateTag() {
        return this.writeToNBT( new NBTTagCompound() );
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
        return super.hasCapability( capability, facing );
    }

    @Override
    public <T> T getCapability( Capability<T> capability, EnumFacing facing ){
        if( capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ){
            if( facing == null ){ return (T) new CombinedInvWrapper( inputStack, outputStack ); }
            return (T) new CombinedInvWrapper( new AutoInputStackHandler( this, inputStack ), new AutoOutputStackHandler( this, outputStack ) );
        }
        return super.getCapability( capability, facing );
    }

    public int getTime(){
        return time;
    }

    public boolean hasFood(){
        return ( inputStack.getStackInSlot(0) != null );
    }

    private void setItemStackInOutputSlot( ItemStack itemstack ){
        boolean temp = false;
        int i = 0;
        while( !temp ){
            if( outputStack.insertItem( i, itemstack, false ) == null ){ temp = true; }
            i++;
            if( i == OUTPUT_SIZE && !temp ){
                EntityItem produceEntity = new EntityItem( worldObj, pos.getX(), pos.getY()+1, pos.getZ(), itemstack );
                worldObj.spawnEntityInWorld( produceEntity );
                temp = true;
            }
        }
    }

    public class MillStackHandler extends InputStackHandler{

        public MillStackHandler( TileEntity te, int size ){
            super( te, size );
        }

        @Override
        public ItemStack insertItem( int slot, ItemStack stack, boolean simulate ){
            if( !( stack.getItem() instanceof ItemFood ) ){ return stack; }
            return super.insertItem( slot, stack, simulate );
        }
    }

    public class AutoInputStackHandler extends MillStackHandler{

        public AutoInputStackHandler( TileEntity te, MillStackHandler stackhandler ){
            super( te, stackhandler.getSlots() );
            this.stacks = stackhandler.getStacks();
        }

        @Override
        public ItemStack extractItem(int slot, int amount, boolean simulate ){
            return null;
        }
    }
}
