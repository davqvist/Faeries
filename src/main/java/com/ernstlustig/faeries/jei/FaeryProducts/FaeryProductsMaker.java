package com.ernstlustig.faeries.jei.FaeryProducts;

import com.ernstlustig.faeries.item.EnumRace;
import mezz.jei.api.IItemRegistry;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FaeryProductsMaker {

    public static List<FaeryProductsWrapper> getFaeryProducts(){
        List<FaeryProductsWrapper> recipes = new ArrayList<FaeryProductsWrapper>();
        for( EnumRace race : EnumRace.values() ){
            FaeryProductsWrapper recipe = new FaeryProductsWrapper( race );
            recipes.add( recipe );
        }
        return recipes;
    }
}
