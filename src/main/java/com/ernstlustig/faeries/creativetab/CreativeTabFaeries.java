package com.ernstlustig.faeries.creativetab;

import com.ernstlustig.faeries.init.ModItems;
import com.ernstlustig.faeries.reference.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabFaeries {
    public static final CreativeTabs FAERIES_TAB = new CreativeTabs( Reference.MOD_ID ) {
        @Override
        public Item getTabIconItem() {
            return ModItems.brailer;
        }

        @Override
        public String getTranslatedTabLabel(){
            return Reference.MOD_NAME;
        }
    };
}
