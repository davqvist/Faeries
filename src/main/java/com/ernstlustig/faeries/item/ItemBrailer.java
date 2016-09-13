package com.ernstlustig.faeries.item;

import com.ernstlustig.faeries.init.ModItems;
import com.ernstlustig.faeries.faerytraits.EnumRace;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

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
            int chance = world.rand.nextInt(100);
            if( chance <= percentage ){
               List<EnumRace> catchable = new ArrayList<EnumRace>();
               for( EnumRace race : EnumRace.values() ){
                   if( race.isCatchable() && race.getCatchableDimension() == playerIn.dimension ){ catchable.add( race ); }
               }
               int racenr = world.rand.nextInt( catchable.size() );
               ItemStack faery = ModItems.faery.setRace( new ItemStack( ModItems.faery ), catchable.get( racenr ).name() );
               int gendernr = world.rand.nextInt(2);
               faery = ModItems.faery.setGender( faery, ItemFaery.EnumGender.values()[gendernr].name() );
               world.spawnEntityInWorld( new EntityItem( world, playerIn.posX, playerIn.posY, playerIn.posZ, faery ) );
            }
            //LogHelper.info( world.getBiomeGenForCoords( playerIn.getPosition() ).getBiomeName() );
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
