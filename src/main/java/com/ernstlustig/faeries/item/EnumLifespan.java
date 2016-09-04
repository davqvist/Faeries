package com.ernstlustig.faeries.item;

public enum EnumLifespan {
    SHORTEST( 20 ),
    SHORTER( 30 ),
    SHORT( 40 ),
    NORMAL( 50 ),
    LONG( 60 ),
    LONGER( 70 ),
    LONGEST( 80 );

    private final int maxage;

    EnumLifespan( int maxage ){ this.maxage = maxage; }

    public int getMaxAge(){ return maxage; }

    public static String getLifespanFromMaxAge( int maxage ){
        for( EnumLifespan lifespan : EnumLifespan.values() ){
            if( lifespan.getMaxAge() == maxage ){ return lifespan.toString(); }
        }
        return "";
    }
}
