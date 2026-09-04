package com.github.archangel_styx;

import com.github.archangel_styx.upgrades.AttributeModifiers;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.core.Holder;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.armortrim.TrimMaterial;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.github.archangel_styx.upgrades.AttributeModifiers.*;

public class ArmorUpgrades implements ModInitializer {
	public static final String MOD_ID = "armorupgrades";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ServerEntityEvents.EQUIPMENT_CHANGE.register((entity, slot, oldStack, newStack) -> {
			if (!slot.isArmor()) return;
			Holder<TrimMaterial> oldMat = getMaterial(oldStack);
			Holder<TrimMaterial> newMat = getMaterial(newStack);
			if (oldMat != null) {
				var oldMods = getModifiers(oldMat, slot);
				if (oldMods != null) removeModifiers(oldMods, entity);
			}
			if (newMat != null) {
				var newMods = getModifiers(newMat, slot);
				if (newMods != null) applyModifiers(newMods, entity);
			}

			/*
			1. get trim material.

			2. use trim material to get the mapping of each attribute on that material.

			3. for each attribute apply the modifier.
			 */
		});
		AttributeModifiers.initialize();
		LOGGER.info("ArmorUpgrades initialized");
	}

	public static ResourceLocation id(String path) {
		return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
	}
}
