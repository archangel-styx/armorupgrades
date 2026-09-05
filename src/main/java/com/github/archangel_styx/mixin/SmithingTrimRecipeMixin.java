package com.github.archangel_styx.mixin;

import com.github.archangel_styx.upgrades.AttributeModifiers;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.SmithingTrimRecipe;
import net.minecraft.world.item.equipment.trim.ArmorTrim;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SmithingTrimRecipe.class)
public class SmithingTrimRecipeMixin {
    @Inject(
            method = "applyTrim",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/ItemStack;set(Lnet/minecraft/core/component/DataComponentType;Ljava/lang/Object;)Ljava/lang/Object;",
                    shift = At.Shift.AFTER
            )
    )
    private static void injectingModifiers_afterTrim(CallbackInfoReturnable<ItemStack> cir, @Local(ordinal = 2) ItemStack stack)
    {
        ArmorTrim trim = stack.get(DataComponents.TRIM);
        AttributeModifiers.applyModifiers(stack, trim);
    }
}
