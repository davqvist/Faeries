package com.ernstlustig.faeries.jei.FaeryMutation;

import com.ernstlustig.faeries.faerytraits.EnumRace;

import java.util.ArrayList;
import java.util.List;

public class FaeryMutationMaker {

    public static List<FaeryMutationWrapper> getFaeryMutations(){
        List<FaeryMutationWrapper> recipes = new ArrayList<FaeryMutationWrapper>();
        for( EnumRace race : EnumRace.values() ){
            if( !race.isCatchable() ) {
                FaeryMutationWrapper recipe = new FaeryMutationWrapper(race);
                recipes.add(recipe);
            }
        }
        return recipes;
    }
}
