package com.example.goldenberrymod;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

@Mod(GoldenBerryMod.MODID)
public class GoldenBerryMod {
    public static final String MODID = "goldenberrymod";

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);

    // Food properties: 4 hunger, 11.4 saturation
    // Saturation = Hunger * Modifier * 2  =>  11.4 = 4 * 1.425 * 2
    public static final FoodProperties GOLDEN_BERRY_FOOD = new FoodProperties.Builder()
            .nutrition(4)
            .saturationModifier(1.425f)
            .build();

    public static final DeferredItem<Item> GOLDEN_BERRY = ITEMS.register(
            "golden_berry",
            () -> new Item(new Item.Properties().food(GOLDEN_BERRY_FOOD))
    );

    public GoldenBerryMod(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
        modEventBus.addListener(this::addCreative);
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS) {
            event.accept(GOLDEN_BERRY);
        }
    }
}
