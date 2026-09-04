package com.github.archangel_styx;

import com.github.archangel_styx.upgrades.AttributeModifiers_old;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.Identifier;

import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.equipment.trim.TrimMaterial;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.github.archangel_styx.upgrades.AttributeModifiers_old.*;

public class ArmorUpgrades implements ModInitializer {
	public static final String MOD_ID = "armorupgrades";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ServerEntityEvents.EQUIPMENT_CHANGE.register((entity, slot, oldStack, newStack) -> {
			if (!slot.isArmor()) return;
			Holder<TrimMaterial> oldMat = getMaterial(oldStack);
			Holder<TrimMaterial> newMat = getMaterial(newStack);
			ItemAttributeModifiers attributes = newStack.get(DataComponents.ATTRIBUTE_MODIFIERS);
			if (attributes == null) return;
			List<ItemAttributeModifiers.Entry> modifiers = attributes.modifiers();
			LOGGER.info(modifiers.toString());
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
		AttributeModifiers_old.initialize();
		LOGGER.info("ArmorUpgrades initialized");
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
