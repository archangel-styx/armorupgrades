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

import java.util.List;
import java.util.Map;

import static com.github.archangel_styx.ArmorUpgrades.MOD_ID;

public class AttributeModifiers {
    public final static Identifier HELM_MODIFIER_ID = Identifier.fromNamespaceAndPath(MOD_ID, "helm_modifier");
    public final static Identifier CHEST_MODIFIER_ID = Identifier.fromNamespaceAndPath(MOD_ID, "chest_modifier");
    public final static Identifier LEGS_MODIFIER_ID = Identifier.fromNamespaceAndPath(MOD_ID, "legs_modifier");
    public final static Identifier FEET_MODIFIER_ID = Identifier.fromNamespaceAndPath(MOD_ID, "boots_modifier");

    record ModifierSpec(Holder<Attribute> attribute, AttributeModifier modifier) {}

    private final static Map<String, Map<EquipmentSlot, List<ModifierSpec>>> MATERIALS = Map.ofEntries(
            Map.entry("minecraft:amethyst", Map.of(
                    EquipmentSlot.HEAD, List.of(
                            new ModifierSpec(Attributes.GRAVITY, new AttributeModifier(HELM_MODIFIER_ID, -0.05f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE))
                    ),
                    EquipmentSlot.CHEST, List.of(
                            new ModifierSpec(Attributes.GRAVITY, new AttributeModifier(CHEST_MODIFIER_ID, -0.09f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE))
                    ),
                    EquipmentSlot.LEGS, List.of(
                            new ModifierSpec(Attributes.GRAVITY, new AttributeModifier(LEGS_MODIFIER_ID, -0.07f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE))
                    ),
                    EquipmentSlot.FEET, List.of(
                            new ModifierSpec(Attributes.GRAVITY, new AttributeModifier(FEET_MODIFIER_ID, -0.04f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)),
                            new ModifierSpec(Attributes.SAFE_FALL_DISTANCE, new AttributeModifier(FEET_MODIFIER_ID, 5.0f, AttributeModifier.Operation.ADD_VALUE))
                    )
            )),
            Map.entry("minecraft:diamond", Map.of(
                    EquipmentSlot.HEAD, List.of(
                            new ModifierSpec(Attributes.ARMOR, new AttributeModifier(HELM_MODIFIER_ID,0.05f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
                    )),
                    EquipmentSlot.CHEST, List.of(
                            new ModifierSpec(Attributes.ARMOR, new AttributeModifier(CHEST_MODIFIER_ID,0.09f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
                    )),
                    EquipmentSlot.LEGS, List.of(
                            new ModifierSpec(Attributes.ARMOR, new AttributeModifier(LEGS_MODIFIER_ID, 0.07f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
                    )),
                    EquipmentSlot.FEET, List.of(
                            new ModifierSpec(Attributes.ARMOR, new AttributeModifier(FEET_MODIFIER_ID,0.04f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
                    ))
            )),
            Map.entry("minecraft:emerald", Map.of(
                    EquipmentSlot.HEAD, List.of(
                            new ModifierSpec(Attributes.LUCK, new AttributeModifier(HELM_MODIFIER_ID,1.0f, AttributeModifier.Operation.ADD_VALUE)
                    )),
                    EquipmentSlot.CHEST, List.of(
                            new ModifierSpec(Attributes.LUCK, new AttributeModifier(CHEST_MODIFIER_ID,0.5f, AttributeModifier.Operation.ADD_VALUE)

                    )),
                    EquipmentSlot.LEGS, List.of(
                            new ModifierSpec(Attributes.LUCK, new AttributeModifier(LEGS_MODIFIER_ID,0.5f, AttributeModifier.Operation.ADD_VALUE)

                    )),
                    EquipmentSlot.FEET, List.of(
                            new ModifierSpec(Attributes.LUCK, new AttributeModifier(FEET_MODIFIER_ID,0.5f, AttributeModifier.Operation.ADD_VALUE)
                    ))
            )),
            Map.entry("minecraft:gold", Map.of(
                    EquipmentSlot.HEAD, List.of(
                            new ModifierSpec(Attributes.MINING_EFFICIENCY, new AttributeModifier(HELM_MODIFIER_ID,0.05f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)),
                            new ModifierSpec(Attributes.ATTACK_SPEED, new AttributeModifier(HELM_MODIFIER_ID,0.05f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
                            )),
                    EquipmentSlot.CHEST, List.of(
                            new ModifierSpec(Attributes.MINING_EFFICIENCY, new AttributeModifier(CHEST_MODIFIER_ID,0.05f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)),
                            new ModifierSpec(Attributes.ATTACK_SPEED, new AttributeModifier(CHEST_MODIFIER_ID,0.1f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
                    )),
                    EquipmentSlot.LEGS, List.of(
                            new ModifierSpec(Attributes.MINING_EFFICIENCY, new AttributeModifier(LEGS_MODIFIER_ID,0.05f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)),
                            new ModifierSpec(Attributes.ATTACK_SPEED, new AttributeModifier(LEGS_MODIFIER_ID,0.05f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
                    )),
                    EquipmentSlot.FEET, List.of(
                            new ModifierSpec(Attributes.MINING_EFFICIENCY, new AttributeModifier(FEET_MODIFIER_ID,0.05f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)),
                            new ModifierSpec(Attributes.ATTACK_SPEED, new AttributeModifier(FEET_MODIFIER_ID,0.05f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
                            )))),
            Map.entry("minecraft:netherite", Map.of(
                    EquipmentSlot.HEAD, List.of(
                            new ModifierSpec(Attributes.ARMOR, new AttributeModifier(HELM_MODIFIER_ID,0.06f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)),
                            new ModifierSpec(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(HELM_MODIFIER_ID,0.02f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)),
                            new ModifierSpec(Attributes.GRAVITY, new AttributeModifier(HELM_MODIFIER_ID,0.01f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)),
                            new ModifierSpec(Attributes.MOVEMENT_SPEED, new AttributeModifier(HELM_MODIFIER_ID,-0.01f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE))
                    ),
                    EquipmentSlot.CHEST, List.of(
                            new ModifierSpec(Attributes.ARMOR, new AttributeModifier(CHEST_MODIFIER_ID,0.12f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)),
                            new ModifierSpec(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(CHEST_MODIFIER_ID,0.04f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)),
                            new ModifierSpec(Attributes.GRAVITY, new AttributeModifier(CHEST_MODIFIER_ID,0.05f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)),
                            new ModifierSpec(Attributes.MOVEMENT_SPEED, new AttributeModifier(CHEST_MODIFIER_ID,-0.05f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE))

                    ),
                    EquipmentSlot.LEGS, List.of(
                            new ModifierSpec(Attributes.ARMOR, new AttributeModifier(LEGS_MODIFIER_ID,0.07f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)),
                            new ModifierSpec(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(LEGS_MODIFIER_ID,0.03f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)),
                            new ModifierSpec(Attributes.GRAVITY, new AttributeModifier(LEGS_MODIFIER_ID,0.03f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)),
                            new ModifierSpec(Attributes.MOVEMENT_SPEED, new AttributeModifier(LEGS_MODIFIER_ID,-0.03f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE))
                    ),
                    EquipmentSlot.FEET, List.of(
                            new ModifierSpec(Attributes.ARMOR, new AttributeModifier(FEET_MODIFIER_ID,0.04f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)),
                            new ModifierSpec(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(FEET_MODIFIER_ID,0.01f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)),
                            new ModifierSpec(Attributes.GRAVITY, new AttributeModifier(FEET_MODIFIER_ID,0.01f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)),
                            new ModifierSpec(Attributes.MOVEMENT_SPEED, new AttributeModifier(FEET_MODIFIER_ID,-0.01f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE))
                    ))),
            Map.entry("minecraft:quartz", Map.of(
                    EquipmentSlot.HEAD, List.of(
                            new ModifierSpec(Attributes.ATTACK_DAMAGE, new AttributeModifier(HELM_MODIFIER_ID,0.05f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE))
                    ),
                    EquipmentSlot.CHEST, List.of(
                            new ModifierSpec(Attributes.ATTACK_DAMAGE, new AttributeModifier(CHEST_MODIFIER_ID,0.05f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE))
                    ),
                    EquipmentSlot.LEGS, List.of(
                            new ModifierSpec(Attributes.ATTACK_DAMAGE, new AttributeModifier(LEGS_MODIFIER_ID,0.05f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE))
                    ),
                    EquipmentSlot.FEET, List.of(
                            new ModifierSpec(Attributes.ATTACK_DAMAGE, new AttributeModifier(FEET_MODIFIER_ID,0.05f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE))
                    )
            )),
            Map.entry("minecraft:redstone", Map.of(
                    EquipmentSlot.HEAD, List.of(
                            new ModifierSpec(Attributes.MOVEMENT_SPEED, new AttributeModifier(HELM_MODIFIER_ID,0.02f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
                    )),
                    EquipmentSlot.CHEST, List.of(
                            new ModifierSpec(Attributes.MOVEMENT_SPEED, new AttributeModifier(CHEST_MODIFIER_ID,0.02f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
                    )),
                    EquipmentSlot.LEGS, List.of(
                            new ModifierSpec(Attributes.MOVEMENT_SPEED, new AttributeModifier(LEGS_MODIFIER_ID,0.06f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
                    )),
                    EquipmentSlot.FEET, List.of(
                            new ModifierSpec(Attributes.MOVEMENT_SPEED, new AttributeModifier(FEET_MODIFIER_ID,0.1f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
                    ))
            ))
    );

    public static void applyModifiers(ItemStack stack, ArmorTrim trim)
    {
        EquipmentSlot slot = stack.get(DataComponents.EQUIPPABLE).slot();

        EquipmentSlotGroup slotGroup = switch(slot) {
            case EquipmentSlot.HEAD -> EquipmentSlotGroup.HEAD;
            case EquipmentSlot.CHEST -> EquipmentSlotGroup.CHEST;
            case EquipmentSlot.LEGS -> EquipmentSlotGroup.LEGS;
            case EquipmentSlot.FEET -> EquipmentSlotGroup.FEET;
            default -> null;
        };

        if (slotGroup == null) return;

        ItemAttributeModifiers existing = stack.get(DataComponents.ATTRIBUTE_MODIFIERS);
        ItemAttributeModifiers.Builder builder = ItemAttributeModifiers.builder();

        if (existing != null)
        {
            for (ItemAttributeModifiers.Entry entry : existing.modifiers())
            {
                if (!isFlagged(entry.modifier().id()))
                {
                    builder.add(entry.attribute(), entry.modifier(), entry.slot());
                }
            }
        }

        Map<EquipmentSlot, List<ModifierSpec>> map = MATERIALS.getOrDefault(trim.material().getRegisteredName(), null);
        if (map == null) {
            stack.set(DataComponents.ATTRIBUTE_MODIFIERS, builder.build());
            return;
        }

        List<ModifierSpec> modifiers = map.get(slot);

        for (ModifierSpec spec : modifiers) {
            builder.add(spec.attribute, spec.modifier, slotGroup);
        }

        stack.set(DataComponents.ATTRIBUTE_MODIFIERS, builder.build());
    }



    private static boolean isFlagged(Identifier id)
    {
        return id.getNamespace().equals(MOD_ID);
    }
}
