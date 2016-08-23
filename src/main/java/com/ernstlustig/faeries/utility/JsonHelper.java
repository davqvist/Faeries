package com.ernstlustig.faeries.utility;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class JsonHelper {

    public static void createJson( String submodel ) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        JsonObject entry = new JsonObject();
        entry.addProperty( "parent", "faeries:item/faery" );

        JsonObject texture = new JsonObject();
        texture.addProperty( "layer0", "faeries:items/faery_" + submodel );
        entry.add( "textures", texture );

        System.out.println( gson.toJson( entry ) );
    }
}
