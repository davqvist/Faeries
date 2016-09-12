package com.ernstlustig.faeries.container;

import com.ernstlustig.faeries.item.ItemDrop;
import com.ernstlustig.faeries.tileentity.TileEntitySqueezer;
import com.sun.istack.internal.Nullable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerSqueezer extends Container {
    private TileEntitySqueezer te;

    public ContainerSqueezer( IInventory playerInventory, TileEntitySqueezer te ){
        this.te = te;

        addOwnSlots();
        addPlayerSlots( playerInventory );
    }

    private void addOwnSlots() {
        IItemHandler itemHandler = this.te.getCapability( CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null );

        addSlotToContainer( new SlotItemHandler( itemHandler, 0, 18, 24 ){
            @Override
            public boolean isItemValid( ItemStack stack ){
                return stack.getItem() instanceof ItemDrop;
            }
        });
    }

    private void addPlayerSlots( IInventory playerInventory ){
        for( int row = 0; row < 3; row++ ){
            for( int col = 0; col < 9; col++ ){
                int x = 10 + col * 18;
                int y = row * 18 + 70;
                addSlotToContainer( new Slot( playerInventory, col + row * 9 + 9, x, y ) );
            }
        }

        for( int row = 0; row < 9; row++ ){
            int x = 10 + row * 18;
            int y = 58 + 70;
            addSlotToContainer( new Slot( playerInventory, row, x, y ) );
        }
    }

    @Nullable
    @Override
    public ItemStack transferStackInSlot( EntityPlayer playerIn, int index ){
        ItemStack itemstack = null;
        Slot slot = this.inventorySlots.get( index );

        if( slot != null && slot.getHasStack() ){
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if( index < TileEntitySqueezer.INPUT_SIZE ){
                if( !this.mergeItemStack( itemstack1, TileEntitySqueezer.INPUT_SIZE, this.inventorySlots.size(), true ) ){
                    return null;
                }
            } else if( !this.mergeItemStack( itemstack1, 0, TileEntitySqueezer.INPUT_SIZE, false ) ){
                return null;
            }

            if( itemstack1.stackSize == 0 ){
                slot.putStack( null );
            } else {
                slot.onSlotChanged();
            }
        }

        return itemstack;
    }

    @Override
    public boolean canInteractWith( EntityPlayer playerIn ){
        return te.canInteractWith( playerIn );
    }
}
