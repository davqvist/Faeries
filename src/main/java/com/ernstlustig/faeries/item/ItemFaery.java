package com.ernstlustig.faeries.item;

import com.ernstlustig.faeries.init.ModItems;
import com.ernstlustig.faeries.utility.NBTHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class ItemFaery extends ItemFaeries {

    public enum EnumGender{ MALE, FEMALE, COUPLE }

    public ItemFaery(){
        super();
        setRegistryName("faery");
        setUnlocalizedName("faery");
    }

    // RACE
    public static String getRace( ItemStack itemstack ){
        NBTTagCompound nbtTagCompound = NBTHelper.getTagCompound( itemstack );
        if( nbtTagCompound.hasKey( "race" ) ){ return nbtTagCompound.getString( "race" ); }
        return "default";
    }

    public ItemStack setRace( ItemStack itemstack, String name ){
        NBTTagCompound nbtTagCompound = NBTHelper.getTagCompound( itemstack );
        nbtTagCompound.setString( "race", name );
        if( !nbtTagCompound.hasKey( "passiverace" ) ){ itemstack = setPassiveRace( itemstack, name ); }
        return itemstack;
    }

    public static String getPassiveRace( ItemStack itemstack ){
        NBTTagCompound nbtTagCompound = NBTHelper.getTagCompound( itemstack );
        if( nbtTagCompound.hasKey( "passiverace" ) ){ return nbtTagCompound.getString( "passiverace" ); }
        return "default";
    }

    public ItemStack setPassiveRace( ItemStack itemstack, String name ){
        NBTTagCompound nbtTagCompound = NBTHelper.getTagCompound( itemstack );
        nbtTagCompound.setString( "passiverace", name );
        return itemstack;
    }

    public static String getCoupleRace( ItemStack itemstack ){
        NBTTagCompound nbtTagCompound = NBTHelper.getTagCompound( itemstack );
        if( nbtTagCompound.hasKey( "couplerace" ) ){ return nbtTagCompound.getString( "couplerace" ); }
        return "default";
    }

    public ItemStack setCoupleRace( ItemStack itemstack, String name ){
        NBTTagCompound nbtTagCompound = NBTHelper.getTagCompound( itemstack );
        nbtTagCompound.setString( "couplerace", name );
        return itemstack;
    }

    public static String getCouplePassiveRace( ItemStack itemstack ){
        NBTTagCompound nbtTagCompound = NBTHelper.getTagCompound( itemstack );
        if( nbtTagCompound.hasKey( "couplepassiverace" ) ){ return nbtTagCompound.getString( "couplepassiverace" ); }
        return "default";
    }

    public ItemStack setCouplePassiveRace( ItemStack itemstack, String name ){
        NBTTagCompound nbtTagCompound = NBTHelper.getTagCompound( itemstack );
        nbtTagCompound.setString( "couplepassiverace", name );
        return itemstack;
    }

    //GENDER
    public static String getGender( ItemStack itemstack ){
        NBTTagCompound nbtTagCompound = NBTHelper.getTagCompound( itemstack );
        if( nbtTagCompound.hasKey( "gender" ) ){ return nbtTagCompound.getString( "gender" ); }
        return "default";
    }

    public ItemStack setGender( ItemStack itemstack, String name ){
        NBTTagCompound nbtTagCompound = NBTHelper.getTagCompound( itemstack );
        nbtTagCompound.setString( "gender", name );
        return itemstack;
    }

    //AGE
    public static int getAge( ItemStack itemstack ){
        NBTTagCompound nbtTagCompound = NBTHelper.getTagCompound( itemstack );
        if( nbtTagCompound.hasKey( "age" ) ){ return nbtTagCompound.getInteger( "age" ); }
        return ItemFaery.getLifespan( itemstack );
    }

    public ItemStack setAge( ItemStack itemstack, int age ){
        NBTTagCompound nbtTagCompound = NBTHelper.getTagCompound( itemstack );
        nbtTagCompound.setInteger( "age", age );
        return itemstack;
    }

    //LIFESPAN
    public static int getLifespan( ItemStack itemstack ){
        NBTTagCompound nbtTagCompound = NBTHelper.getTagCompound( itemstack );
        if( nbtTagCompound.hasKey( "lifespan" ) ){ return nbtTagCompound.getInteger( "lifespan" ); }
        int maxage = EnumRace.valueOf( ModItems.faery.getRace( itemstack ) ).getLifespan().getMaxAge();
        itemstack = ModItems.faery.setLifespan( itemstack, maxage );
        if( !nbtTagCompound.hasKey( "passivelifespan" ) ){ int temp = getPassiveLifespan( itemstack ); }
        return maxage;
    }

    public ItemStack setLifespan( ItemStack itemstack, int lifespan ){
        NBTTagCompound nbtTagCompound = NBTHelper.getTagCompound( itemstack );
        nbtTagCompound.setInteger( "lifespan", lifespan );
        return itemstack;
    }

    public static int getPassiveLifespan( ItemStack itemstack ){
        NBTTagCompound nbtTagCompound = NBTHelper.getTagCompound( itemstack );
        if( nbtTagCompound.hasKey( "passivelifespan" ) ){ return nbtTagCompound.getInteger( "passivelifespan" ); }
        int maxage = EnumRace.valueOf( ModItems.faery.getPassiveRace( itemstack ) ).getLifespan().getMaxAge();
        itemstack = ModItems.faery.setPassiveLifespan( itemstack, maxage );
        return maxage;
    }

    public ItemStack setPassiveLifespan( ItemStack itemstack, int lifespan ){
        NBTTagCompound nbtTagCompound = NBTHelper.getTagCompound( itemstack );
        nbtTagCompound.setInteger( "passivelifespan", lifespan );
        return itemstack;
    }

    public static int getCoupleLifespan( ItemStack itemstack ){
        NBTTagCompound nbtTagCompound = NBTHelper.getTagCompound( itemstack );
        if( nbtTagCompound.hasKey( "couplelifespan" ) ){ return nbtTagCompound.getInteger( "couplelifespan" ); }
        return 0;
    }

    public ItemStack setCoupleLifespan( ItemStack itemstack, int lifespan ){
        NBTTagCompound nbtTagCompound = NBTHelper.getTagCompound( itemstack );
        nbtTagCompound.setInteger( "couplelifespan", lifespan );
        return itemstack;
    }

    public static int getCouplePassiveLifespan( ItemStack itemstack ){
        NBTTagCompound nbtTagCompound = NBTHelper.getTagCompound( itemstack );
        if( nbtTagCompound.hasKey( "couplepassivelifespan" ) ){ return nbtTagCompound.getInteger( "couplepassivelifespan" ); }
        return 0;
    }

    public ItemStack setCouplePassiveLifespan( ItemStack itemstack, int lifespan ){
        NBTTagCompound nbtTagCompound = NBTHelper.getTagCompound( itemstack );
        nbtTagCompound.setInteger( "couplepassivelifespan", lifespan );
        return itemstack;
    }

    //FERTILITY
    public static int getFertility( ItemStack itemstack ){
        NBTTagCompound nbtTagCompound = NBTHelper.getTagCompound( itemstack );
        if( nbtTagCompound.hasKey( "fertility" ) ){ return nbtTagCompound.getInteger( "fertility" ); }
        int fertility = EnumRace.valueOf( ModItems.faery.getRace( itemstack ) ).getFertility();
        itemstack = ModItems.faery.setFertility( itemstack, fertility );
        if( !nbtTagCompound.hasKey( "passivefertility" ) ){ int temp = getPassiveFertility( itemstack ); }
        return fertility;
    }

    public ItemStack setFertility( ItemStack itemstack, int fertility ){
        NBTTagCompound nbtTagCompound = NBTHelper.getTagCompound( itemstack );
        nbtTagCompound.setInteger( "fertility", fertility );
        return itemstack;
    }

    public static int getPassiveFertility( ItemStack itemstack ){
        NBTTagCompound nbtTagCompound = NBTHelper.getTagCompound( itemstack );
        if( nbtTagCompound.hasKey( "passivefertility" ) ){ return nbtTagCompound.getInteger( "passivefertility" ); }
        int fertility = EnumRace.valueOf( ModItems.faery.getPassiveRace( itemstack ) ).getFertility();
        itemstack = ModItems.faery.setPassiveFertility( itemstack, fertility );
        return fertility;
    }

    public ItemStack setPassiveFertility( ItemStack itemstack, int lifespan ){
        NBTTagCompound nbtTagCompound = NBTHelper.getTagCompound( itemstack );
        nbtTagCompound.setInteger( "passivefertility", lifespan );
        return itemstack;
    }

    public static int getCoupleFertility( ItemStack itemstack ){
        NBTTagCompound nbtTagCompound = NBTHelper.getTagCompound( itemstack );
        if( nbtTagCompound.hasKey( "couplefertility" ) ){ return nbtTagCompound.getInteger( "couplefertility" ); }
        return 0;
    }

    public ItemStack setCoupleFertility( ItemStack itemstack, int fertility ){
        NBTTagCompound nbtTagCompound = NBTHelper.getTagCompound( itemstack );
        nbtTagCompound.setInteger( "couplefertility", fertility );
        return itemstack;
    }

    public static int getCouplePassiveFertility( ItemStack itemstack ){
        NBTTagCompound nbtTagCompound = NBTHelper.getTagCompound( itemstack );
        if( nbtTagCompound.hasKey( "couplepassivefertility" ) ){ return nbtTagCompound.getInteger( "couplepassivefertility" ); }
        return 0;
    }

    public ItemStack setCouplePassiveFertility( ItemStack itemstack, int fertility ){
        NBTTagCompound nbtTagCompound = NBTHelper.getTagCompound( itemstack );
        nbtTagCompound.setInteger( "couplepassivefertility", fertility );
        return itemstack;
    }

    //PRODUCTIVITY
    public static float getProductivity( ItemStack itemstack ){
        NBTTagCompound nbtTagCompound = NBTHelper.getTagCompound( itemstack );
        if( nbtTagCompound.hasKey( "productivity" ) ){ return nbtTagCompound.getFloat( "productivity" ); }
        float productivity = EnumRace.valueOf( ModItems.faery.getRace( itemstack ) ).getProductivity().getProductivity();
        itemstack = ModItems.faery.setProductivity( itemstack, productivity );
        if( !nbtTagCompound.hasKey( "passiveproductivity" ) ){ float temp = getPassiveProductivity( itemstack ); }
        return productivity;
    }

    public ItemStack setProductivity( ItemStack itemstack, float productivity ){
        NBTTagCompound nbtTagCompound = NBTHelper.getTagCompound( itemstack );
        nbtTagCompound.setFloat( "productivity", productivity );
        return itemstack;
    }

    public static float getPassiveProductivity( ItemStack itemstack ){
        NBTTagCompound nbtTagCompound = NBTHelper.getTagCompound( itemstack );
        if( nbtTagCompound.hasKey( "passiveproductivity" ) ){ return nbtTagCompound.getFloat( "passiveproductivity" ); }
        float productivity = EnumRace.valueOf( ModItems.faery.getPassiveRace( itemstack ) ).getProductivity().getProductivity();
        itemstack = ModItems.faery.setPassiveProductivity( itemstack, productivity );
        return productivity;
    }

    public ItemStack setPassiveProductivity( ItemStack itemstack, float productivity ){
        NBTTagCompound nbtTagCompound = NBTHelper.getTagCompound( itemstack );
        nbtTagCompound.setFloat( "passiveproductivity", productivity );
        return itemstack;
    }

    public static float getCoupleProductivity( ItemStack itemstack ){
        NBTTagCompound nbtTagCompound = NBTHelper.getTagCompound( itemstack );
        if( nbtTagCompound.hasKey( "coupleproductivity" ) ){ return nbtTagCompound.getFloat( "coupleproductivity" ); }
        return 0;
    }

    public ItemStack setCoupleProductivity( ItemStack itemstack, float productivity ){
        NBTTagCompound nbtTagCompound = NBTHelper.getTagCompound( itemstack );
        nbtTagCompound.setFloat( "coupleproductivity", productivity );
        return itemstack;
    }

    public static float getCouplePassiveProductivity( ItemStack itemstack ){
        NBTTagCompound nbtTagCompound = NBTHelper.getTagCompound( itemstack );
        if( nbtTagCompound.hasKey( "couplepassiveproductivity" ) ){ return nbtTagCompound.getFloat( "couplepassiveproductivity" ); }
        return 0;
    }

    public ItemStack setCouplePassiveProductivity( ItemStack itemstack, float productivity ){
        NBTTagCompound nbtTagCompound = NBTHelper.getTagCompound( itemstack );
        nbtTagCompound.setFloat( "couplepassiveproductivity", productivity );
        return itemstack;
    }

    public ItemStack setCoupleTraits( ItemStack itemstack, ItemStack female ){
        if( ItemFaery.getGender( itemstack ).equals( EnumGender.COUPLE.toString() ) ) {
            itemstack = ModItems.faery.setCoupleRace( itemstack, ItemFaery.getRace( female ) );
            itemstack = ModItems.faery.setCouplePassiveRace( itemstack, ItemFaery.getPassiveRace( female ) );
            itemstack = ModItems.faery.setCoupleLifespan( itemstack, ItemFaery.getLifespan( female ) );
            itemstack = ModItems.faery.setCouplePassiveLifespan( itemstack, ItemFaery.getPassiveLifespan( female ) );
            itemstack = ModItems.faery.setCoupleFertility( itemstack, ItemFaery.getFertility( female ) );
            itemstack = ModItems.faery.setCouplePassiveFertility( itemstack, ItemFaery.getPassiveFertility( female ) );
            itemstack = ModItems.faery.setCoupleProductivity( itemstack, ItemFaery.getProductivity( female ) );
            itemstack = ModItems.faery.setCouplePassiveProductivity( itemstack, ItemFaery.getPassiveProductivity( female ) );
        }
        return itemstack;
    }

    public ItemStack getOffspring( ItemStack itemstack ){
        if( ItemFaery.getGender( itemstack ).equals( EnumGender.COUPLE.toString() ) ) {
            ItemStack offspring = new ItemStack( ModItems.faery );
            List<String> races = Arrays.asList( ModItems.faery.getRace( itemstack ), ModItems.faery.getPassiveRace( itemstack ), ModItems.faery.getCoupleRace( itemstack ), ModItems.faery.getCouplePassiveRace( itemstack ) );
            List<Integer> lifespans = Arrays.asList( ModItems.faery.getLifespan( itemstack ), ModItems.faery.getPassiveLifespan( itemstack ), ModItems.faery.getCoupleLifespan( itemstack ), ModItems.faery.getCouplePassiveLifespan( itemstack ) );
            List<Integer> fertilities = Arrays.asList( ModItems.faery.getFertility( itemstack ), ModItems.faery.getPassiveFertility( itemstack ), ModItems.faery.getCoupleFertility( itemstack ), ModItems.faery.getCouplePassiveFertility( itemstack ) );
            List<Float> productivities = Arrays.asList( ModItems.faery.getProductivity( itemstack ), ModItems.faery.getPassiveProductivity( itemstack ), ModItems.faery.getCoupleProductivity( itemstack ), ModItems.faery.getCouplePassiveProductivity( itemstack ) );
            int[][] mapping = new int[][]{ { 0, 2 }, { 0, 3 }, { 1, 2 }, { 1, 3 } };
            //Mutationcheck 1
            int coin = Minecraft.getMinecraft().theWorld.rand.nextInt(4);
            List<Mutation> possiblemutations = EnumRace.valueOf( races.get( mapping[coin][0] ) ).getMutations();
            Mutation resultingmutation = null;
            int found = 0;
            for( Mutation m : possiblemutations ){
                if( m.getRace1() == EnumRace.valueOf( races.get( mapping[coin][0] ) ) && m.getRace2() == EnumRace.valueOf( races.get( mapping[coin][1] ) ) ){ resultingmutation = m; }
                if( m.getRace1() == EnumRace.valueOf( races.get( mapping[coin][1] ) ) && m.getRace2() == EnumRace.valueOf( races.get( mapping[coin][0] ) ) ){ resultingmutation = m; }
                if( resultingmutation == null ){ found++; }
            }
            EnumRace race = null;
            if( resultingmutation != null ){
                int percentage = Minecraft.getMinecraft().theWorld.rand.nextInt(100);
                if( percentage < resultingmutation.getChance() ){
                    race = EnumRace.valueOf( races.get( mapping[coin][0] ) ).getMutationRaces().get( found );
                }
            }
            if( race == null ){
                int coin2 = Minecraft.getMinecraft().theWorld.rand.nextInt(2);
                race = EnumRace.valueOf( races.get( mapping[coin][coin2] ) );
                coin2 = Minecraft.getMinecraft().theWorld.rand.nextInt(2);
                offspring = ModItems.faery.setLifespan( offspring, lifespans.get( mapping[coin][coin2] ) );
                coin2 = Minecraft.getMinecraft().theWorld.rand.nextInt(2);
                offspring = ModItems.faery.setFertility( offspring, fertilities.get( mapping[coin][coin2] ) );
                coin2 = Minecraft.getMinecraft().theWorld.rand.nextInt(2);
                offspring = ModItems.faery.setProductivity( offspring, productivities.get( mapping[coin][coin2] ) );
            }
            offspring = ModItems.faery.setRace( offspring, race.toString() );
            //Mutationcheck 2
            coin = Minecraft.getMinecraft().theWorld.rand.nextInt(4);
            resultingmutation = null;
            found = 0;
            for( Mutation m : possiblemutations ){
                if( m.getRace1() == EnumRace.valueOf( races.get( mapping[coin][0] ) ) && m.getRace2() == EnumRace.valueOf( races.get( mapping[coin][1] ) ) ){ resultingmutation = m; }
                if( m.getRace1() == EnumRace.valueOf( races.get( mapping[coin][1] ) ) && m.getRace2() == EnumRace.valueOf( races.get( mapping[coin][0] ) ) ){ resultingmutation = m; }
                if( resultingmutation == null ){ found++; }
            }
            race = null;
            if( resultingmutation != null ){
                int percentage = Minecraft.getMinecraft().theWorld.rand.nextInt(100);
                if( percentage < resultingmutation.getChance() ){
                    race = EnumRace.valueOf( races.get( mapping[coin][0] ) ).getMutationRaces().get( found );
                }
            }
            if( race == null ){
                int coin2 = Minecraft.getMinecraft().theWorld.rand.nextInt(2);
                race = EnumRace.valueOf( races.get( mapping[coin][coin2] ) );
                coin2 = Minecraft.getMinecraft().theWorld.rand.nextInt(2);
                offspring = ModItems.faery.setPassiveLifespan( offspring, lifespans.get( mapping[coin][coin2] ) );
                coin2 = Minecraft.getMinecraft().theWorld.rand.nextInt(2);
                offspring = ModItems.faery.setPassiveFertility( offspring, fertilities.get( mapping[coin][coin2] ) );
                coin2 = Minecraft.getMinecraft().theWorld.rand.nextInt(2);
                offspring = ModItems.faery.setPassiveProductivity( offspring, productivities.get( mapping[coin][coin2] ) );
            }
            offspring = ModItems.faery.setPassiveRace( offspring, race.toString() );
            coin = Minecraft.getMinecraft().theWorld.rand.nextInt(2);
            offspring = ModItems.faery.setGender( offspring, ItemFaery.EnumGender.values()[coin].name() );
            return offspring;
        }
        return null;
    }

    @Override
    public String getItemStackDisplayName( ItemStack stack ){
        //TODO: Translation
        //I18n.translateToLocal(this.getUnlocalizedName(stack) + ".name")
        NBTTagCompound nbtTagCompound = stack.getTagCompound();
        if( nbtTagCompound != null && nbtTagCompound.hasKey( "race" ) ) {
            String race = nbtTagCompound.getString( "race" );
            return race.substring( 0, 1 ) + race.substring( 1 ).toLowerCase( Locale.ENGLISH ) + " Faery";
        }
        return "Faery";
    }

    @SideOnly(Side.CLIENT)
    public void addInformation( ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced ){
        super.addInformation( stack, playerIn, tooltip, advanced );
        if( !ModItems.faery.getRace( stack ).equals( "default") ){
            tooltip.add( "Race: " + ModItems.faery.getRace( stack ) + " (" + ModItems.faery.getPassiveRace( stack ) + ")");
            tooltip.add( "Lifespan: " + EnumLifespan.getLifespanFromMaxAge( ModItems.faery.getLifespan( stack ) ) + " (" + EnumLifespan.getLifespanFromMaxAge( ModItems.faery.getPassiveLifespan( stack ) ) + ")" );
            tooltip.add( "Fertility: " + ModItems.faery.getFertility( stack ) + " (" + ModItems.faery.getPassiveFertility( stack ) + ")" );
            tooltip.add( "Productivity: " + EnumProductivity.getProductivityfromModifier( ModItems.faery.getProductivity( stack ) ) + " (" + EnumProductivity.getProductivityfromModifier( ModItems.faery.getPassiveProductivity( stack ) ) + ")" );
            if( ModItems.faery.getGender( stack ).equals( EnumGender.COUPLE.toString() ) ){
                tooltip.add( "  Race: " + ModItems.faery.getCoupleRace( stack ) + " (" + ModItems.faery.getCouplePassiveRace( stack ) + ")");
                tooltip.add( "  Lifespan: " + EnumLifespan.getLifespanFromMaxAge( ModItems.faery.getCoupleLifespan( stack ) ) + " (" + EnumLifespan.getLifespanFromMaxAge( ModItems.faery.getCouplePassiveLifespan( stack ) ) + ")" );
                tooltip.add( "  Fertility: " + ModItems.faery.getCoupleFertility( stack ) + " (" + ModItems.faery.getCouplePassiveFertility( stack ) + ")" );
                tooltip.add( "  Productivity: " + EnumProductivity.getProductivityfromModifier( ModItems.faery.getCoupleProductivity( stack ) ) + " (" + EnumProductivity.getProductivityfromModifier( ModItems.faery.getCouplePassiveProductivity( stack ) ) + ")" );
            }
        }
    }

    @Override
    public void getSubItems( Item itemIn, CreativeTabs tab, List<ItemStack> subItems ){
        super.getSubItems( itemIn, tab, subItems );
        for( EnumRace race : EnumRace.values() ){
            for( EnumGender gender : EnumGender.values() ){
                ItemStack itemstack = new ItemStack( itemIn );
                itemstack = this.setRace( itemstack, race.name() );
                itemstack = this.setGender( itemstack, gender.name() );
                subItems.add( itemstack );
                //TODO: Initialize Couples correctly
            }
        }
    }


}