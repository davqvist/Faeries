package com.ernstlustig.faeries.jei.FaeryProducts;

import com.ernstlustig.faeries.faerytraits.EnumRace;

import java.util.ArrayList;
import java.util.List;

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
