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

import java.util.ArrayList;
import java.util.List;
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
        int percentage = 10;

        if( !world.isRemote ){
            int chance = new Random().nextInt(100);
            List<EnumRace> catchable = new ArrayList<EnumRace>();
            for( EnumRace race : EnumRace.values() ){
                if( race.isCatchable() ){ catchable.add( race ); }
            }
            int racenr = new Random().nextInt( catchable.size() );
            ItemStack faery = ModItems.faery.setRace( new ItemStack( ModItems.faery ), catchable.get( racenr ).name() );
            int gendernr = new Random().nextInt(2);
            faery = ModItems.faery.setGender( faery, ItemFaery.EnumGender.values()[gendernr].name() );
            if( chance<=percentage ){ world.spawnEntityInWorld( new EntityItem( world, playerIn.posX, playerIn.posY, playerIn.posZ, faery ) ); }
        }
        return new ActionResult<>(EnumActionResult.SUCCESS, stack);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation( ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced ){
        super.addInformation( stack, playerIn, tooltip, advanced );
        tooltip.add( "Catches Faeries" );
    }
}
