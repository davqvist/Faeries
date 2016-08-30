package com.ernstlustig.faeries.tileentity;

import com.ernstlustig.faeries.init.ModItems;
import com.ernstlustig.faeries.item.EnumRace;
import com.ernstlustig.faeries.item.ItemFaery;
import com.ernstlustig.faeries.item.Product;
import com.ernstlustig.faeries.utility.LogHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;
import sun.rmi.runtime.Log;

import java.util.Random;

public class TileEntityFaeryHouse extends TileEntity implements ITickable {

    public static final int INPUT_SIZE = 4;
    public static final int OUTPUT_SIZE = 15;
    private int time;
    private int marrytime;
    private boolean flag;
    private boolean marryflag;
    public static final int MAX_TIME = 400;
    public static final int MAX_MARRYTIME = 100;

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
        if( !hasCouple() ){
            time = 0;
            flag = false;
        }
        if( time <= 0 && hasCouple() ){
            if( flag && !worldObj.isRemote ){
                for( Product product : EnumRace.valueOf( ItemFaery.getRace( inputStack.getStackInSlot(0) ) ).getProducts() ){
                    int percentage = new Random().nextInt(100);
                    int foodmodifier = ( inputStack.getStackInSlot(3) != null ) ? 2 : 1;
                    if( percentage < 0.5f * product.getChance() * foodmodifier ) {
                        setItemStackInOutputSlot( product.getItemStack() );
                    }
                }
                if( inputStack.getStackInSlot(3) != null ){ inputStack.extractItem( 3, 1, false ); }
                int age = ItemFaery.getAge( inputStack.getStackInSlot(0) );
                age = Math.max( 0, age-1 );
                if( age > 0 ){ ModItems.faery.setAge( inputStack.getStackInSlot(0), age ); } else{
                    for( int i=1; i<=3; i++ ){
                        ItemStack offspring = ModItems.faery.getOffspring( inputStack.getStackInSlot(0) );
                        setItemStackInOutputSlot( offspring );
                    }
                    inputStack.extractItem( 0, 1, false );
                }
            }
            time = MAX_TIME;
            flag = true;
        }
        time--;

        if( !canMarry() ){
            marrytime = 0;
            marryflag = false;
        }
        if( marrytime <= 0 && canMarry() ){
            if( marryflag && !worldObj.isRemote ){
                ItemStack couple = inputStack.getStackInSlot(1).copy();
                couple.stackSize = 1;
                couple = ModItems.faery.setGender( couple, ItemFaery.EnumGender.COUPLE.toString() );
                couple = ModItems.faery.setCoupleTraits( couple, inputStack.getStackInSlot(2) );
                inputStack.insertItem( 0, couple, false );
                inputStack.extractItem( 1, 1, false );
                inputStack.extractItem( 2, 1, false );
            }
            marrytime = MAX_MARRYTIME;
            marryflag = true;
        }
        marrytime--;
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
        return super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability( Capability<T> capability, EnumFacing facing ){
        if( capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ){
            if( facing == null ){ return (T) new CombinedInvWrapper( inputStack, outputStack ); }
            return (T) outputStack;
        }
        return super.getCapability(capability, facing);
    }

    public int getTime(){
        return time;
    }
    public int getMarryTime(){
        return marrytime;
    }

    public boolean hasCouple(){
        return ( inputStack.getStackInSlot(0) != null );
    }

    public boolean canMarry(){
        return ( inputStack.getStackInSlot(0) == null ) && ( inputStack.getStackInSlot(1) != null ) && ( inputStack.getStackInSlot(2) != null );
    }

    public ItemStack getCouple(){
        return inputStack.getStackInSlot(0);
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
}

