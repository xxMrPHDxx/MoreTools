package com.mrphd.mtools;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mrphd.mtools.client.item.Items;

import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod(value = MoreTools.MOD_ID)
public class MoreTools {

	public static final String MOD_NAME = "More Tools";
	public static final String MOD_ID = "mtools";
	public static final Logger LOGGER = LogManager.getLogger();

	public MoreTools() {
		MinecraftForge.EVENT_BUS.addListener(this::setupClient);

		MinecraftForge.EVENT_BUS.register(this);
	}

	public void setupClient(final FMLClientSetupEvent event) {
		LOGGER.debug("Setting up client...");
	}

	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class RegistryEvents {

		@SubscribeEvent
		public static void onItemsRegistry(final RegistryEvent.Register<Item> event) {
			event.getRegistry().registerAll(Items.ITEMS.toArray(new Item[0]));
		}

	}

}
