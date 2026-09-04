package com.github.archangel_styx.upgrades;

import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.equipment.trim.ArmorTrim;
import net.minecraft.world.item.equipment.trim.TrimMaterials;

import java.util.List;
import java.util.Map;

import static com.github.archangel_styx.ArmorUpgrades.LOGGER;
import static com.github.archangel_styx.ArmorUpgrades.MOD_ID;

public class AttributeModifiers {
    public final static Identifier HELM_MODIFIER_ID = Identifier.fromNamespaceAndPath(MOD_ID, "helm_modifier");
    public final static Identifier CHEST_MODIFIER_ID = Identifier.fromNamespaceAndPath(MOD_ID, "chest_modifier");
    public final static Identifier LEGS_MODIFIER_ID = Identifier.fromNamespaceAndPath(MOD_ID, "legs_modifier");
    public final static Identifier BOOTS_MODIFIER_ID = Identifier.fromNamespaceAndPath(MOD_ID, "boots_modifier");

    record ModifierSpec(Holder<Attribute> attribute, float value, AttributeModifier.Operation op) {}
    record MaterialProfile(Map<EquipmentSlot, List<ModifierSpec>> attributes) {}

    private final static Map<Identifier, MaterialProfile> MATERIALS = Map.ofEntries(
            Map.entry(TrimMaterials.AMETHYST.identifier(),
                    new MaterialProfile(
                            Map.of(
                                    EquipmentSlot.HEAD, List.of(

                                    ))))
    );

    public static void applyModifiers(ItemStack stack, ArmorTrim trim)
    {
        EquipmentSlot slot = stack.get(DataComponents.EQUIPPABLE).slot();
        ItemAttributeModifiers currentMods = stack.get(DataComponents.ATTRIBUTE_MODIFIERS);
        ItemAttributeModifiers newMods;
        if (currentMods == null) return;

        switch(slot) {
            case HEAD:
                break;
            case CHEST:
            case LEGS:
            case FEET:
            default: newMods = currentMods;
        }


        LOGGER.info(newMods.toString());
        stack.set(DataComponents.ATTRIBUTE_MODIFIERS, newMods);
    }
}
