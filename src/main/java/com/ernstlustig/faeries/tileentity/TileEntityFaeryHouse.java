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
    private final float housemodifier;

    private FaeryCoupleStackHandler coupleStack = new FaeryCoupleStackHandler( this, 1 );
    private FaeryMaleStackHandler maleStack = new FaeryMaleStackHandler( this, 1 );
    private FaeryFemaleStackHandler femaleStack = new FaeryFemaleStackHandler( this, 1 );
    private FaeryFoodStackHandler foodStack = new FaeryFoodStackHandler( this, 1 );
    private OutputStackHandler outputStack = new OutputStackHandler( this, OUTPUT_SIZE );

    public TileEntityFaeryHouse( float housemodifier ){
        super();
        this.housemodifier = housemodifier;
    }

    @Override
    public void update() {
        if( !hasCouple() ){
            time = 0;
            flag = false;
        }
        if( time <= 0 && hasCouple() ){
            if( flag && !worldObj.isRemote ){
                for( Product product : EnumRace.valueOf( ItemFaery.getRace( coupleStack.getStackInSlot(0) ) ).getProducts() ){
                    int percentage = new Random().nextInt(100);
                    int foodmodifier = ( foodStack.getStackInSlot(0) != null ) ? 2 : 1;
                    if( percentage < housemodifier * product.getChance() * foodmodifier * ItemFaery.getProductivity( coupleStack.getStackInSlot(0) ) ) {
                        setItemStackInOutputSlot( product.getItemStack() );
                    }
                }
                if( foodStack.getStackInSlot(0) != null ){ foodStack.extractItem( 0, 1, false ); }
                int age = ItemFaery.getAge( coupleStack.getStackInSlot(0) );
                age = Math.max( 0, age-1 );
                if( age > 0 ){ ModItems.faery.setAge( coupleStack.getStackInSlot(0), age ); } else{
                    for( int i=1; i <= ItemFaery.getFertility( coupleStack.getStackInSlot(0) ); i++ ){
                        ItemStack offspring = ModItems.faery.getOffspring( coupleStack.getStackInSlot(0) );
                        setItemStackInOutputSlot( offspring );
                    }
                    coupleStack.extractItem( 0, 1, false );
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
                ItemStack couple = maleStack.getStackInSlot(0).copy();
                couple.stackSize = 1;
                couple = ModItems.faery.setGender( couple, ItemFaery.EnumGender.COUPLE.toString() );
                couple = ModItems.faery.setCoupleTraits( couple, femaleStack.getStackInSlot(0) );
                coupleStack.insertItem( 0, couple, false );
                maleStack.extractItem( 0, 1, false );
                femaleStack.extractItem( 0, 1, false );
            }
            marrytime = MAX_MARRYTIME;
            marryflag = true;
        }
        marrytime--;
    }

    @Override
    public void readFromNBT( NBTTagCompound compound ){
        super.readFromNBT( compound );
        if( compound.hasKey( "couple" ) ){
            coupleStack.deserializeNBT( (NBTTagCompound) compound.getTag( "couple" ) );
        }
        if( compound.hasKey( "male" ) ){
            maleStack.deserializeNBT( (NBTTagCompound) compound.getTag( "male" ) );
        }
        if( compound.hasKey( "female" ) ){
            femaleStack.deserializeNBT( (NBTTagCompound) compound.getTag( "female" ) );
        }
        if( compound.hasKey( "food" ) ){
            foodStack.deserializeNBT( (NBTTagCompound) compound.getTag( "food" ) );
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
        compound.setTag( "couple", coupleStack.serializeNBT() );
        compound.setTag( "male", maleStack.serializeNBT() );
        compound.setTag( "female", femaleStack.serializeNBT() );
        compound.setTag( "food", foodStack.serializeNBT() );
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
            if( facing == null ){ return (T) new CombinedInvWrapper( coupleStack, maleStack, femaleStack, foodStack, outputStack ); }
            return (T) new CombinedInvWrapper( new AutoFaeryCoupleStackHandler( this, coupleStack ), new AutoFaeryMaleStackHandler( this, maleStack ), new AutoFaeryFemaleStackHandler( this, femaleStack ), new AutoFaeryFoodStackHandler( this, foodStack ), new AutoOutputStackHandler( this, outputStack ) );
        }
        return super.getCapability( capability, facing );
    }

    public int getTime(){
        return time;
    }
    public int getMarryTime(){
        return marrytime;
    }

    public boolean hasCouple(){
        return ( coupleStack.getStackInSlot(0) != null );
    }

    public boolean canMarry(){
        return ( coupleStack.getStackInSlot(0) == null ) && ( maleStack.getStackInSlot(0) != null ) && ( femaleStack.getStackInSlot(0) != null );
    }

    public ItemStack getCouple(){
        return coupleStack.getStackInSlot(0);
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

    public class FaeryCoupleStackHandler extends InputStackHandler{

        public FaeryCoupleStackHandler( TileEntity te, int size ){
            super( te, size );
        }

        @Override
        public ItemStack insertItem( int slot, ItemStack stack, boolean simulate ){
            if( !( ( stack.getItem() == ModItems.faery ) && ItemFaery.getGender( stack ).equals( ItemFaery.EnumGender.COUPLE.toString() ) ) ){ return stack; }
            return super.insertItem( slot, stack, simulate );
        }
    }

    public class FaeryMaleStackHandler extends InputStackHandler{

        public FaeryMaleStackHandler( TileEntity te, int size ){
            super( te, size );
        }

        @Override
        public ItemStack insertItem( int slot, ItemStack stack, boolean simulate ){
            if( !( ( stack.getItem() == ModItems.faery ) && ItemFaery.getGender( stack ).equals( ItemFaery.EnumGender.MALE.toString() ) ) ){ return stack; }
            return super.insertItem( slot, stack, simulate );
        }
    }

    public class FaeryFemaleStackHandler extends InputStackHandler{

        public FaeryFemaleStackHandler( TileEntity te, int size ){
            super( te, size );
        }

        @Override
        public ItemStack insertItem( int slot, ItemStack stack, boolean simulate ){
            if( !( ( stack.getItem() == ModItems.faery ) && ItemFaery.getGender( stack ).equals( ItemFaery.EnumGender.FEMALE.toString() ) ) ){ return stack; }
            return super.insertItem( slot, stack, simulate );
        }
    }

    public class FaeryFoodStackHandler extends InputStackHandler{

        public FaeryFoodStackHandler( TileEntity te, int size ){
            super( te, size );
        }

        @Override
        public ItemStack insertItem( int slot, ItemStack stack, boolean simulate ){
            if( stack.getItem() != ModItems.mashedfood ){ return stack; }
            return super.insertItem( slot, stack, simulate );
        }
    }

    public class AutoFaeryCoupleStackHandler extends FaeryCoupleStackHandler{

        public AutoFaeryCoupleStackHandler( TileEntity te, FaeryCoupleStackHandler stackhandler ){
            super( te, stackhandler.getSlots() );
            this.stacks = stackhandler.getStacks();
        }

        @Override
        public ItemStack extractItem(int slot, int amount, boolean simulate ){
            return null;
        }
    }

    public class AutoFaeryMaleStackHandler extends FaeryMaleStackHandler{

        public AutoFaeryMaleStackHandler( TileEntity te, FaeryMaleStackHandler stackhandler ){
            super( te, stackhandler.getSlots() );
            this.stacks = stackhandler.getStacks();
        }

        @Override
        public ItemStack extractItem(int slot, int amount, boolean simulate ){
            return null;
        }
    }

    public class AutoFaeryFemaleStackHandler extends FaeryFemaleStackHandler{

        public AutoFaeryFemaleStackHandler( TileEntity te, FaeryFemaleStackHandler stackhandler ){
            super( te, stackhandler.getSlots() );
            this.stacks = stackhandler.getStacks();
        }

        @Override
        public ItemStack extractItem(int slot, int amount, boolean simulate ){
            return null;
        }
    }

    public class AutoFaeryFoodStackHandler extends FaeryFoodStackHandler{

        public AutoFaeryFoodStackHandler( TileEntity te, FaeryFoodStackHandler stackhandler ){
            super( te, stackhandler.getSlots() );
            this.stacks = stackhandler.getStacks();
        }

        @Override
        public ItemStack extractItem(int slot, int amount, boolean simulate ){
            return null;
        }

        @Override
        public ItemStack insertItem( int slot, ItemStack stack, boolean simulate ){
            return stack;
        }
    }
}

