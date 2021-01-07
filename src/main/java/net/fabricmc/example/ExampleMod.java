package net.fabricmc.example;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ExampleMod implements ModInitializer {

	//public static final Block EXAMPLE_BLOCK = new Block(FabricBlockSettings.of(Material.METAL).hardness(4.0f));
	public static final Block EXAMPLE_BLOCK = new ExampleButton(AbstractBlock.Settings.of(Material.SUPPORTED).noCollision().strength(0.5F));

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		Registry.register(Registry.BLOCK, new Identifier("modid", "example_block"), EXAMPLE_BLOCK);
		//Registry.register(Registry.BLOCK, new Identifier("modid", "example_block"), EXAMPLE_BLOCK);
		Registry.register(Registry.ITEM, new Identifier("modid", "example_block"), new BlockItem(EXAMPLE_BLOCK, new Item.Settings().group(ItemGroup.MISC)));
	}
}
