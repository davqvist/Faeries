package com.ernstlustig.faeries.item;

import com.ernstlustig.faeries.init.ModItems;
import com.ernstlustig.faeries.utility.LogHelper;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemSaddle;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class ItemBrailer extends ItemFaeries {

    public ItemBrailer(){
        super();
        this.setMaxStackSize(1);
        setRegistryName("brailer");
        setUnlocalizedName("brailer");
    }

    @SideOnly(Side.CLIENT)
    public boolean isFull3D()
    {
        return true;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World world, EntityPlayer playerIn, EnumHand hand) {
        int percentage = 20;

        if( !world.isRemote ){
            int chance = new Random().nextInt(100);
            int racenr = new Random().nextInt( EnumRace.values().length );
            ItemStack faery = ModItems.faery.setRace( new ItemStack( ModItems.faery ), EnumRace.values()[racenr].name() );
            int gendernr = new Random().nextInt(2);
            faery = ModItems.faery.setGender( faery, ItemFaery.EnumGender.values()[gendernr].name() );
            if( chance<=percentage ){ world.spawnEntityInWorld( new EntityItem( world, playerIn.posX, playerIn.posY, playerIn.posZ, faery ) ); }
            //playerIn.addChatComponentMessage( new TextComponentString( Integer.toString( chance ) ) );
        }
        return new ActionResult<>(EnumActionResult.SUCCESS, stack);
    }
}
