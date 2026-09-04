package com.github.archangel_styx.upgrades;

import com.github.archangel_styx.ArmorUpgrades;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.armortrim.ArmorTrim;
import net.minecraft.world.item.armortrim.TrimMaterial;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.github.archangel_styx.ArmorUpgrades.LOGGER;

public class AttributeModifiers {
    /*
    *   POTENTIAL MODIFIERS:
    *   POTENCY - how well the thing does something
    *   EFFICIENCY - how quickly the thing does something
    *   ATTRIBUTE BONUS - what bonus attributes if any
    *
    *   EMERALD - +POTENCY -EFFICIENCY +LUCK?
    *   REDSTONE - +EFFICIENCY +MOVEMENT SPEED?
    *   LAPIZ - +POTENCY
    *   AMETHYST - -GRAVITY +SAFE FALL DISTANCE
    *   QUARTZ - +POTENCY +EFFICIENCY +ATTACK DAMAGE
    *   NETHERITE - +ARMOR +ARMOR TOUGHNESS +GRAVITY -SPEED
    *   DIAMOND - +ARMOR +EFFICIENCY -POTENCY
    *   GOLD - +ATTACK SPEED +MINING SPEED -EFFICIENCY -POTENCY
    *   IRON - ++EFFICIENCY
    *   COPPER - ++POTENCY
    * */

    record MaterialSlot(String material, EquipmentSlot slot) {}
    record ModifierSpec(Holder<Attribute> attribute, float value, AttributeModifier.Operation op) {}
    record MaterialProfile(String name, Map<EquipmentSlot, List<ModifierSpec>> modifiers) {}

    private static final List<MaterialProfile> MATERIALS = List.of(
            new MaterialProfile("amethyst", Map.of(
                    EquipmentSlot.HEAD, List.of(
                            new ModifierSpec(Attributes.GRAVITY, -0.001f, AttributeModifier.Operation.ADD_VALUE),
                            new ModifierSpec(Attributes.SAFE_FALL_DISTANCE, 1.0f, AttributeModifier.Operation.ADD_VALUE)
                    ),
                    EquipmentSlot.CHEST, List.of(
                            new ModifierSpec(Attributes.GRAVITY, -0.002f, AttributeModifier.Operation.ADD_VALUE),
                            new ModifierSpec(Attributes.SAFE_FALL_DISTANCE, 1.0f, AttributeModifier.Operation.ADD_VALUE)
                    ),
                    EquipmentSlot.LEGS, List.of(
                            new ModifierSpec(Attributes.GRAVITY, -0.001f, AttributeModifier.Operation.ADD_VALUE),
                            new ModifierSpec(Attributes.SAFE_FALL_DISTANCE, 1.0f, AttributeModifier.Operation.ADD_VALUE)
                    ),
                    EquipmentSlot.FEET, List.of(
                            new ModifierSpec(Attributes.GRAVITY, -0.001f, AttributeModifier.Operation.ADD_VALUE),
                            new ModifierSpec(Attributes.SAFE_FALL_DISTANCE, 1.0f, AttributeModifier.Operation.ADD_VALUE)
                    )
            )),
            new MaterialProfile("diamond", Map.of(
                    EquipmentSlot.HEAD, List.of(
                            new ModifierSpec(Attributes.ARMOR, 0.5f, AttributeModifier.Operation.ADD_VALUE)
                    ),
                    EquipmentSlot.CHEST, List.of(
                            new ModifierSpec(Attributes.ARMOR, 1.0f, AttributeModifier.Operation.ADD_VALUE)
                    ),
                    EquipmentSlot.LEGS, List.of(
                            new ModifierSpec(Attributes.ARMOR, 0.5f, AttributeModifier.Operation.ADD_VALUE)
                    ),
                    EquipmentSlot.FEET, List.of(
                            new ModifierSpec(Attributes.ARMOR, 0.5f, AttributeModifier.Operation.ADD_VALUE)
                    )
            )),
            new MaterialProfile("emerald", Map.of(
                    EquipmentSlot.HEAD, List.of(
                            new ModifierSpec(Attributes.LUCK, 1.0f, AttributeModifier.Operation.ADD_VALUE)
                    ),
                    EquipmentSlot.CHEST, List.of(
                            new ModifierSpec(Attributes.LUCK, 0.5f, AttributeModifier.Operation.ADD_VALUE)

                    ),
                    EquipmentSlot.LEGS, List.of(
                            new ModifierSpec(Attributes.LUCK, 0.5f, AttributeModifier.Operation.ADD_VALUE)

                    ),
                    EquipmentSlot.FEET, List.of(
                            new ModifierSpec(Attributes.LUCK, 0.5f, AttributeModifier.Operation.ADD_VALUE)
                    )
            )),
            new MaterialProfile("gold", Map.of(
                    EquipmentSlot.HEAD, List.of(
                            new ModifierSpec(Attributes.MINING_EFFICIENCY, 0.3f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL),
                            new ModifierSpec(Attributes.ATTACK_SPEED, 0.1f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
                    ),
                    EquipmentSlot.CHEST, List.of(
                            new ModifierSpec(Attributes.MINING_EFFICIENCY, 0.2f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL),
                            new ModifierSpec(Attributes.ATTACK_SPEED, 0.1f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
                    ),
                    EquipmentSlot.LEGS, List.of(
                            new ModifierSpec(Attributes.MINING_EFFICIENCY, 0.2f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL),
                            new ModifierSpec(Attributes.ATTACK_SPEED, 0.1f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
                    ),
                    EquipmentSlot.FEET, List.of(
                            new ModifierSpec(Attributes.MINING_EFFICIENCY, 0.1f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL),
                            new ModifierSpec(Attributes.ATTACK_SPEED, 0.3f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
                    )
            )),
            new MaterialProfile("netherite", Map.of(
                    EquipmentSlot.HEAD, List.of(
                            new ModifierSpec(Attributes.ARMOR, 0.5f, AttributeModifier.Operation.ADD_VALUE),
                            new ModifierSpec(Attributes.ARMOR_TOUGHNESS, 0.2f, AttributeModifier.Operation.ADD_VALUE),
                            new ModifierSpec(Attributes.GRAVITY, 0.001f, AttributeModifier.Operation.ADD_VALUE),
                            new ModifierSpec(Attributes.MOVEMENT_SPEED, -0.001f, AttributeModifier.Operation.ADD_VALUE)
                    ),
                    EquipmentSlot.CHEST, List.of(
                            new ModifierSpec(Attributes.ARMOR, 1.0f, AttributeModifier.Operation.ADD_VALUE),
                            new ModifierSpec(Attributes.ARMOR_TOUGHNESS, 0.3f, AttributeModifier.Operation.ADD_VALUE),
                            new ModifierSpec(Attributes.GRAVITY, 0.002f, AttributeModifier.Operation.ADD_VALUE),
                            new ModifierSpec(Attributes.MOVEMENT_SPEED, -0.002f, AttributeModifier.Operation.ADD_VALUE)

                    ),
                    EquipmentSlot.LEGS, List.of(
                            new ModifierSpec(Attributes.ARMOR, 1.0f, AttributeModifier.Operation.ADD_VALUE),
                            new ModifierSpec(Attributes.ARMOR_TOUGHNESS, 0.3f, AttributeModifier.Operation.ADD_VALUE),
                            new ModifierSpec(Attributes.GRAVITY, 0.002f, AttributeModifier.Operation.ADD_VALUE),
                            new ModifierSpec(Attributes.MOVEMENT_SPEED, -0.002f, AttributeModifier.Operation.ADD_VALUE)

                    ),
                    EquipmentSlot.FEET, List.of(
                            new ModifierSpec(Attributes.ARMOR, 0.5f, AttributeModifier.Operation.ADD_VALUE),
                            new ModifierSpec(Attributes.ARMOR_TOUGHNESS, 0.2f, AttributeModifier.Operation.ADD_VALUE),
                            new ModifierSpec(Attributes.GRAVITY, 0.001f, AttributeModifier.Operation.ADD_VALUE),
                            new ModifierSpec(Attributes.MOVEMENT_SPEED, -0.001f, AttributeModifier.Operation.ADD_VALUE)

                    )
            )),
            new MaterialProfile("quartz", Map.of(
                    EquipmentSlot.HEAD, List.of(
                            new ModifierSpec(Attributes.ATTACK_DAMAGE, 0.5f, AttributeModifier.Operation.ADD_VALUE)
                    ),
                    EquipmentSlot.CHEST, List.of(
                            new ModifierSpec(Attributes.ATTACK_DAMAGE, 0.5f, AttributeModifier.Operation.ADD_VALUE)
                    ),
                    EquipmentSlot.LEGS, List.of(
                            new ModifierSpec(Attributes.ATTACK_DAMAGE, 0.5f, AttributeModifier.Operation.ADD_VALUE)
                    ),
                    EquipmentSlot.FEET, List.of(
                            new ModifierSpec(Attributes.ATTACK_DAMAGE, 0.5f, AttributeModifier.Operation.ADD_VALUE)
                    )
            )),
            new MaterialProfile("redstone", Map.of(
                    EquipmentSlot.HEAD, List.of(
                            new ModifierSpec(Attributes.MOVEMENT_SPEED, 0.001f, AttributeModifier.Operation.ADD_VALUE)
                    ),
                    EquipmentSlot.CHEST, List.of(
                            new ModifierSpec(Attributes.MOVEMENT_SPEED, 0.001f, AttributeModifier.Operation.ADD_VALUE)
                    ),
                    EquipmentSlot.LEGS, List.of(
                            new ModifierSpec(Attributes.MOVEMENT_SPEED, 0.001f, AttributeModifier.Operation.ADD_VALUE)
                    ),
                    EquipmentSlot.FEET, List.of(
                            new ModifierSpec(Attributes.MOVEMENT_SPEED, 0.003f, AttributeModifier.Operation.ADD_VALUE)
                    )
            ))
    );

    private static final Map<MaterialSlot, Map<Holder<Attribute>, AttributeModifier>> LOOKUP =
            MATERIALS.stream()
                    .flatMap(profile -> profile.modifiers().entrySet().stream().flatMap(se ->
                            se.getValue().stream().map(spec -> Map.entry(
                                    new MaterialSlot(profile.name(), se.getKey()),
                                    modify(spec.op(), new MaterialAttribute(profile.name(), spec.attribute(), spec.value(), se.getKey()))
                            ))
                    ))
                    .collect(Collectors.toUnmodifiableMap(
                            Map.Entry::getKey,
                            Map.Entry::getValue,
                            (a, b) -> { var merged = new HashMap<>(a); merged.putAll(b); return Map.copyOf(merged); }
                    ));

    public static void initialize() {
        LOGGER.info("Initializing modifiers...");
    }

    public static Map<Holder<Attribute>, AttributeModifier> getModifiers(Holder<TrimMaterial> trim, EquipmentSlot slot)
    {
        String material = trim.value().assetName();
        return LOOKUP.get(new MaterialSlot(material, slot));
    }

    public static Holder<TrimMaterial> getMaterial(ItemStack stack)
    {
        ArmorTrim trim = stack.getComponents().get(DataComponents.TRIM);
        if (trim == null)
        {
            return null;
        }
        return trim.material();
    }

    public static void applyModifiers(Map<Holder<Attribute>, AttributeModifier> modifiers, LivingEntity entity)
    {
        for (Map.Entry<Holder<Attribute>, AttributeModifier> entry : modifiers.entrySet()) {
            AttributeModifier attributeModifier = entry.getValue();
            AttributeInstance attr = entity.getAttribute(entry.getKey());
            if (attr != null && attributeModifier != null) {
                attr.addOrReplacePermanentModifier(attributeModifier);
            }
        }
    }

    public static void removeModifiers(Map<Holder<Attribute>, AttributeModifier> modifiers, LivingEntity entity)
    {
        for (Map.Entry<Holder<Attribute>, AttributeModifier> entry : modifiers.entrySet()) {
            ResourceLocation id = entry.getValue().id();
            AttributeInstance attr = entity.getAttribute(entry.getKey());
            if (attr != null) {
                attr.removeModifier(id);
            }
        }
    }

    private static Map<Holder<Attribute>, AttributeModifier> modify(AttributeModifier.Operation operation, MaterialAttribute... matAtts)
    {
        Map<Holder<Attribute>, AttributeModifier> map = new HashMap<>();
        for (MaterialAttribute mat : matAtts) {
            map.putAll(modify(operation, mat));
        }

        return map;
    }

    private static Map<Holder<Attribute>, AttributeModifier> modify(AttributeModifier.Operation operation, MaterialAttribute matAtt)
    {
        Holder<Attribute> attr = matAtt.attribute();
        ResourceLocation id = Objects.requireNonNull(ResourceLocation.tryBuild(ArmorUpgrades.MOD_ID, matAtt.toString()));

        return Map.of(attr, new AttributeModifier(
                id, matAtt.value(),
                operation
        ));
    }
}
