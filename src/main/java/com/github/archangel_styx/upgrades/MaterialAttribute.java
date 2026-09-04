package com.github.archangel_styx.upgrades;

import net.minecraft.core.Holder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import org.jspecify.annotations.NonNull;

public record MaterialAttribute(String material, Holder<Attribute> attribute, float value, EquipmentSlot slot) {
    @Override
    public @NonNull String toString() {
        String[] var = attribute.value().getDescriptionId().split("\\.");
        String var2 = var[var.length - 1];
        return String.format("%s_%s_%s", material, slot.toString().toLowerCase(), var2);
    }
}
