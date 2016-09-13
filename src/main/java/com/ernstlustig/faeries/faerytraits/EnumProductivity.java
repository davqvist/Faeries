package com.ernstlustig.faeries.faerytraits;

public enum EnumProductivity {
    SLOWEST( 0.5f ),
    SLOW( 0.75f ),
    NORMAL( 1.0f ),
    FAST( 1.25f ),
    FASTEST( 1.5f );

    private final float percentage;

    EnumProductivity( float percentage ){ this.percentage = percentage; }

    public float getProductivity(){ return percentage; }

    public static String getProductivityfromModifier( float modifier ){
        for( EnumProductivity productivity : EnumProductivity.values() ){
            if( productivity.getProductivity() == modifier ){ return productivity.toString(); }
        }
        return "";
    }
}
