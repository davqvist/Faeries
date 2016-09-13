package com.ernstlustig.faeries.faerytraits;

public class Mutation {

    private EnumRace race1;
    private EnumRace race2;
    private int chance;

    Mutation( EnumRace race1, EnumRace race2, int chance ){
        this.race1 = race1;
        this.race2 = race2;
        this.chance = chance;
    }

    public EnumRace getRace1(){
        return race1;
    }

    public EnumRace getRace2(){
        return race2;
    }

    public int getChance(){
        return chance;
    }
}
