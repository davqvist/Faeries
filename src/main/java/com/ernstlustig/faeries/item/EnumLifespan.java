package com.ernstlustig.faeries.item;

public enum EnumLifespan {
    SHORTEST( 2 ),
    SHORTER( 3 ),
    SHORT( 4 ),
    NORMAL( 5 ),
    LONG( 6 ),
    LONGER( 7 ),
    LONGEST( 8 );

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
