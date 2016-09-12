package com.ernstlustig.faeries.tileentity;

import com.ernstlustig.faeries.item.ItemDrop;
import com.ernstlustig.faeries.network.PacketHandler;
import com.ernstlustig.faeries.network.PacketSqueezerFluid;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidHandlerItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntitySqueezer extends TileEntity implements ITickable {

    public static final int INPUT_SIZE = 1;
    public static final int CAPACITY = Fluid.BUCKET_VOLUME * 8;
    private int time;
    private boolean flag;
    private boolean stoptimer;
    public static final int MAX_TIME = 400;
    private FluidTank tank = new FluidTank( CAPACITY ) {
        @Override
        protected void onContentsChanged(){
            TileEntitySqueezer.this.markDirty();
            PacketHandler.sendToAllAround( new PacketSqueezerFluid( TileEntitySqueezer.this ), TileEntitySqueezer.this, 16 );
        }

        //@Override
        //public boolean canFill(){ return false; }
    };

    private SqueezerStackHandler inputStack = new SqueezerStackHandler( this, INPUT_SIZE );

    @Override
    public void update() {
        if( !hasDrop() ){
            time = 0;
            flag = false;
            stoptimer = false;
        } else {
            Item item = inputStack.getStackInSlot(0).getItem();
            if( item instanceof ItemDrop ){
                ItemDrop drop = (ItemDrop) item;
                if( drop.getFluid() != null && drop.getAmount() > 0 ){
                    if( tank.getFluid() != null && ( tank.getFluid().getFluid() != drop.getFluid() || tank.getFluid().amount + drop.getAmount() > CAPACITY ) ) {
                        stoptimer = true;
                    } else{ stoptimer = false; }
                    if( !stoptimer && time <= 0 ){
                        if( flag && !worldObj.isRemote ){
                            FluidStack fluid = new FluidStack( drop.getFluid(), drop.getAmount() );
                            tank.fill( fluid, true );
                            inputStack.extractItem( 0, 1, false );
                        }
                        time = MAX_TIME;
                        flag = true;
                    }
                }
            }
        }
        if( !stoptimer ){ time--; }
    }

    @Override
    public void readFromNBT( NBTTagCompound compound ){
        super.readFromNBT( compound );
        if( compound.hasKey( "input" ) ){
            inputStack.deserializeNBT( (NBTTagCompound) compound.getTag( "input" ) );
        }
        if( compound.hasKey( "time" ) ){
            time = compound.getInteger( "time" );
        }
        tank.readFromNBT( compound );
    }

    public NBTTagCompound getUpdateTag() {
        return this.writeToNBT( new NBTTagCompound() );
    }

    @Override
    public NBTTagCompound writeToNBT( NBTTagCompound compound ){
        super.writeToNBT( compound );
        compound.setTag( "input", inputStack.serializeNBT() );
        compound.setInteger( "time", time );
        tank.writeToNBT( compound );
        return compound;
    }

    public boolean canInteractWith( EntityPlayer playerIn ){
        return !isInvalid() && playerIn.getDistanceSq( pos.add( 0.5D, 0.5D, 0.5D ) ) <= 64D;
    }

    @Override
    public boolean hasCapability( Capability<?> capability, EnumFacing facing ){
        if( capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY ){
            return true;
        }
        return super.hasCapability( capability, facing );
    }

    @Override
    public <T> T getCapability( Capability<T> capability, EnumFacing facing ){
        if( capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ){
            if( facing == null ){ return (T) inputStack; }
            return (T) new AutoInputStackHandler( this, inputStack );
        }
        if( capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY ){
            return (T) tank;
        }
        return super.getCapability( capability, facing );
    }

    public FluidTank getFluidTank(){ return tank; }

    public int getTime(){
        return time;
    }

    public boolean hasDrop(){
        return ( inputStack.getStackInSlot(0) != null );
    }

    public class SqueezerStackHandler extends InputStackHandler{

        public SqueezerStackHandler( TileEntity te, int size ) {
            super( te,size );
        }

        @Override
        public ItemStack insertItem( int slot, ItemStack stack, boolean simulate ){
            if( !( stack.getItem() instanceof ItemDrop ) ){ return stack; }
            return super.insertItem( slot, stack, simulate );
        }
    }

    public class AutoInputStackHandler extends SqueezerStackHandler{

        public AutoInputStackHandler( TileEntity te, SqueezerStackHandler stackhandler ){
            super( te, stackhandler.getSlots() );
            this.stacks = stackhandler.getStacks();
        }

        @Override
        public ItemStack extractItem(int slot, int amount, boolean simulate ){
            return null;
        }
    }
}
