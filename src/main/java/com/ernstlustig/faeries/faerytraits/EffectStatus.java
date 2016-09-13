package com.ernstlustig.faeries.faerytraits;


import com.ernstlustig.faeries.tileentity.TileEntityFaeryHouse;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.List;

public class EffectStatus {

    Potion potion;

    public EffectStatus( Potion potion ){
        this.potion = potion;
    }

    public void doEffect( TileEntityFaeryHouse te ){
        BlockPos pos = te.getPos();
        AxisAlignedBB aabb = new AxisAlignedBB( pos.getX() - 5, pos.getY() - 5, pos.getZ() - 5, pos.getX() + 5, pos.getY() + 5, pos.getZ() + 5 );
        List<EntityLivingBase> entitylist = te.getWorld().getEntitiesWithinAABB( EntityLivingBase.class, aabb );
        for( EntityLivingBase entity : entitylist ){
            entity.addPotionEffect( new PotionEffect( potion, 200 ) );
        }
    }

    public Potion getPotion(){ return potion; }
}
