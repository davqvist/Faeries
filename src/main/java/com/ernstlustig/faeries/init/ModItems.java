package com.ernstlustig.faeries.init;

import com.ernstlustig.faeries.item.*;
import com.ernstlustig.faeries.renderer.FaeriesMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ModItems {

    public static final ItemBrailer brailer = new ItemBrailer();
    public static final ItemFaery faery = new ItemFaery();
    public static final ItemMashedFood mashedfood = new ItemMashedFood();
    public static final ItemDropWater dropwater = new ItemDropWater();
    public static final ItemDropMilk dropmilk = new ItemDropMilk();
    public static final ItemDropLava droplava = new ItemDropLava();
    public static final ItemFaeryDust faerydust = new ItemFaeryDust();
    public static final ItemDreamDust dreamdust = new ItemDreamDust();
    public static final ItemShardHeadZombie shardheadzombie = new ItemShardHeadZombie();
    public static final ItemShardSkullSkeleton shardskullskeleton = new ItemShardSkullSkeleton();
    public static final ItemShardHeadCreeper shardheadcreeper = new ItemShardHeadCreeper();
    public static final ItemShardSkullWither shardskullwither = new ItemShardSkullWither();
    public static final ItemShardHeadDragon shardheaddragon = new ItemShardHeadDragon();
    public static final ItemShardEnderPearl shardenderpearl = new ItemShardEnderPearl();
    public static final ItemShardNetherStar shardnetherstar = new ItemShardNetherStar();
    public static final ItemShiverNetherStar shivernetherstar = new ItemShiverNetherStar();
    public static final ItemShardDragonEgg sharddragonegg = new ItemShardDragonEgg();
    public static final ItemShiverDragonEgg shiverdragonegg = new ItemShiverDragonEgg();
    public static final ItemNuggetIron nuggetiron = new ItemNuggetIron();
    public static final ItemNuggetDiamond nuggetdiamond = new ItemNuggetDiamond();
    public static final ItemNuggetEmerald nuggetemerald = new ItemNuggetEmerald();

    public static void registerItems(){
        GameRegistry.register( brailer );
        GameRegistry.register( faery );
        GameRegistry.register( mashedfood );
        GameRegistry.register( dropwater );
        GameRegistry.register( dropmilk );
        GameRegistry.register( droplava );
        GameRegistry.register( faerydust );
        GameRegistry.register( dreamdust );
        GameRegistry.register( shardheadzombie );
        GameRegistry.register( shardskullskeleton );
        GameRegistry.register( shardheadcreeper );
        GameRegistry.register( shardskullwither );
        GameRegistry.register( shardheaddragon );
        GameRegistry.register( shardenderpearl );
        GameRegistry.register( shardnetherstar );
        GameRegistry.register( shivernetherstar );
        GameRegistry.register( sharddragonegg );
        GameRegistry.register( shiverdragonegg );
        GameRegistry.register( nuggetiron );
        OreDictionary.registerOre( "nuggetIron", nuggetiron );
        GameRegistry.register( nuggetdiamond );
        OreDictionary.registerOre( "nuggetDiamond", nuggetdiamond );
        GameRegistry.register( nuggetemerald );
        OreDictionary.registerOre( "nuggetEmerald", nuggetemerald );
    }

    @SideOnly(Side.CLIENT)
    public static void loadTextures(){
        //Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register( brailer, 0, new ModelResourceLocation( brailer.getUnlocalizedName().substring(5), "inventory" ) );
        ModelLoader.setCustomModelResourceLocation( brailer, 0, new ModelResourceLocation( brailer.getUnlocalizedName().substring(5), "inventory" ) );
        List<ModelResourceLocation> models = new ArrayList<ModelResourceLocation>();
        for( EnumRace race : EnumRace.values() ){
            for( ItemFaery.EnumGender gender: ItemFaery.EnumGender.values() ){
                models.add( new ModelResourceLocation( faery.getUnlocalizedName().substring(5) + "_" + race.name().toLowerCase( Locale.ENGLISH ) + "_" + gender.name().toLowerCase( Locale.ENGLISH ), "inventory" ) );
                //JsonHelper.createJson( Integer.toString( i ) );
            }
        }
        ModelLoader.registerItemVariants( faery, models.toArray( new ModelResourceLocation[models.size()] ) );
        ModelLoader.setCustomMeshDefinition( faery, new FaeriesMeshDefinition() );
        ModelLoader.setCustomModelResourceLocation( mashedfood, 0, new ModelResourceLocation( mashedfood.getUnlocalizedName().substring(5), "inventory" ) );
        ModelLoader.setCustomModelResourceLocation( dropwater, 0, new ModelResourceLocation( dropwater.getUnlocalizedName().substring(5), "inventory" ) );
        ModelLoader.setCustomModelResourceLocation( dropmilk, 0, new ModelResourceLocation( dropmilk.getUnlocalizedName().substring(5), "inventory" ) );
        ModelLoader.setCustomModelResourceLocation( droplava, 0, new ModelResourceLocation( droplava.getUnlocalizedName().substring(5), "inventory" ) );
        ModelLoader.setCustomModelResourceLocation( faerydust, 0, new ModelResourceLocation( faerydust.getUnlocalizedName().substring(5), "inventory" ) );
        ModelLoader.setCustomModelResourceLocation( dreamdust, 0, new ModelResourceLocation( dreamdust.getUnlocalizedName().substring(5), "inventory" ) );
        ModelLoader.setCustomModelResourceLocation( shardheadzombie, 0, new ModelResourceLocation( shardheadzombie.getUnlocalizedName().substring(5), "inventory" ) );
        ModelLoader.setCustomModelResourceLocation( shardskullskeleton, 0, new ModelResourceLocation( shardskullskeleton.getUnlocalizedName().substring(5), "inventory" ) );
        ModelLoader.setCustomModelResourceLocation( shardheadcreeper, 0, new ModelResourceLocation( shardheadcreeper.getUnlocalizedName().substring(5), "inventory" ) );
        ModelLoader.setCustomModelResourceLocation( shardskullwither, 0, new ModelResourceLocation( shardskullwither.getUnlocalizedName().substring(5), "inventory" ) );
        ModelLoader.setCustomModelResourceLocation( shardheaddragon, 0, new ModelResourceLocation( shardheaddragon.getUnlocalizedName().substring(5), "inventory" ) );
        ModelLoader.setCustomModelResourceLocation( shardenderpearl, 0, new ModelResourceLocation( shardenderpearl.getUnlocalizedName().substring(5), "inventory" ) );
        ModelLoader.setCustomModelResourceLocation( shardnetherstar, 0, new ModelResourceLocation( shardnetherstar.getUnlocalizedName().substring(5), "inventory" ) );
        ModelLoader.setCustomModelResourceLocation( shivernetherstar, 0, new ModelResourceLocation( shivernetherstar.getUnlocalizedName().substring(5), "inventory" ) );
        ModelLoader.setCustomModelResourceLocation( sharddragonegg, 0, new ModelResourceLocation( sharddragonegg.getUnlocalizedName().substring(5), "inventory" ) );
        ModelLoader.setCustomModelResourceLocation( shiverdragonegg, 0, new ModelResourceLocation( shiverdragonegg.getUnlocalizedName().substring(5), "inventory" ) );
        ModelLoader.setCustomModelResourceLocation( nuggetiron, 0, new ModelResourceLocation( nuggetiron.getUnlocalizedName().substring(5), "inventory" ) );
        ModelLoader.setCustomModelResourceLocation( nuggetdiamond, 0, new ModelResourceLocation( nuggetdiamond.getUnlocalizedName().substring(5), "inventory" ) );
        ModelLoader.setCustomModelResourceLocation( nuggetemerald, 0, new ModelResourceLocation( nuggetemerald.getUnlocalizedName().substring(5), "inventory" ) );
    }

}