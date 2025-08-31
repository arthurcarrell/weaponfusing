package com.asteristired.weaponfusing.Item.itemClasses;

import com.asteristired.weaponfusing.Item.ModItemTags;
import com.asteristired.weaponfusing.ItemList;
import com.asteristired.weaponfusing.StatusEffect.ModStatusEffects;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.List;
import java.util.UUID;

import static com.asteristired.weaponfusing.Weaponfusing.LOGGER;

public class FuseItem extends SwordItem {
    protected Item baseItem;
    private static final UUID ATTACK_DAMAGE_MODIFIER_ID = UUID.fromString("fa233e1c-4180-4865-b01b-bc7f7d3ec3f1");

    public FuseItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings, Item baseItem) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
        this.baseItem = baseItem;
    }

    @Override
    public void onCraft(ItemStack stack, World world, PlayerEntity player) {
        super.onCraft(stack, world, player);
        writeNBTData(stack);
    }

    protected ItemStack getFuseMaterial(ItemStack stack) {
        assert stack.getNbt() != null;
        return new ItemStack(Registries.ITEM.get(new Identifier(stack.getNbt().getString("FuseMaterial"))));
    }

    @Override
    public Text getName(ItemStack stack) {
        String translatedText;
        ItemStack fusedItem = getFuseMaterial(stack);
        if (fusedItem != null) {
            translatedText = fusedItem.getName().getString() + " Sword";
            return Text.literal(translatedText).setStyle(Style.EMPTY.withColor(0x00ff99));
        }
        return super.getName();
    }

    public float getBonusDmg(ItemStack stack) {
        // base addition = 1
        float bonusDmg = 1;

        if (getFuseMaterial(stack) == null) return 0;
        ItemStack itemStack = getFuseMaterial(stack);


        if (itemStack.isIn(ModItemTags.Damage.HIGH)) {
            bonusDmg = 2.5f;
        } else if (itemStack.isIn(ModItemTags.Damage.VERY_HIGH)) {
            bonusDmg = 5f;
        } else if (itemStack.isIn(ModItemTags.Damage.INSANELY_HIGH)) {
            bonusDmg = 20f;
        }
        LOGGER.info(String.valueOf(bonusDmg));
        return bonusDmg;
    }

    private void assignFuseDurability(ItemStack itemStack) {
        int durability;
        ItemStack fuseItem = getFuseMaterial(itemStack);
        if (fuseItem.isIn(ModItemTags.Durability.CONSUMABLE)) {durability = 1;}
        else if (fuseItem.isIn(ModItemTags.Durability.FRAGILE)) {durability = 5;}
        else if (fuseItem.isIn(ModItemTags.Durability.STRONG)) {durability = 30;}
        else if (fuseItem.isIn(ModItemTags.Durability.VERY_STRONG)) {durability = 50;}
        else {
            durability = 15;
        }

        itemStack.getOrCreateNbt().putInt("FuseDurability", durability);
        itemStack.getOrCreateNbt().putInt("FuseMaxDurability", durability);
    }

    public int getFuseDurability(ItemStack itemStack) {
        itemStack.getOrCreateNbt();
        assert itemStack.getNbt() != null;
        if (!itemStack.getNbt().contains("FuseDurability")) {
            assignFuseDurability(itemStack);
        }
        return itemStack.getNbt().getInt("FuseDurability");
    }

    public int getMaxFuseDurability(ItemStack itemStack) {
        itemStack.getOrCreateNbt();
        assert itemStack.getNbt() != null;
        if (!itemStack.getNbt().contains("FuseMaxDurability")) {
            assignFuseDurability(itemStack);
        }
        return itemStack.getNbt().getInt("FuseMaxDurability");
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        writeNBTData(stack);
        ItemStack fuseMaterial = getFuseMaterial(stack);
        //tooltip.add(Text.literal("Fused with " + fuseMaterial.getName().getString()));

        // get durability
        int fuseDura = getFuseDurability(stack);
        int fuseDuraMax = getMaxFuseDurability(stack);

        // - Damage -
        if (fuseMaterial.isIn(ModItemTags.Damage.INSANELY_HIGH)) {
            tooltip.add(Text.translatable("tooltip.weaponfusing.damage.insanely_high").formatted(Formatting.GRAY));
        } else if (fuseMaterial.isIn(ModItemTags.Damage.VERY_HIGH)) {
            tooltip.add(Text.translatable("tooltip.weaponfusing.damage.very_high").formatted(Formatting.GRAY));
        } else if (fuseMaterial.isIn(ModItemTags.Damage.HIGH)) {
            tooltip.add(Text.translatable("tooltip.weaponfusing.damage.high").formatted(Formatting.GRAY));
        }

        // - Effects -
        if (fuseMaterial.isIn(ModItemTags.Effects.FLAMING)) {
            tooltip.add(Text.translatable("tooltip.weaponfusing.effect.flaming").formatted(Formatting.GRAY));
        } else if (fuseMaterial.isIn(ModItemTags.Effects.SHOCKING)) {
            tooltip.add(Text.translatable("tooltip.weaponfusing.effect.shocking").formatted(Formatting.GRAY));
        } else if (fuseMaterial.isIn(ModItemTags.Effects.FREEZING)) {
            tooltip.add(Text.translatable("tooltip.weaponfusing.effect.freezing").formatted(Formatting.GRAY));
        } else if (fuseMaterial.isIn(ModItemTags.Effects.CAN_BE_EATEN)) {
            tooltip.add(Text.translatable("tooltip.weaponfusing.effect.can_be_eaten").formatted(Formatting.GRAY));
        }

        if (fuseDuraMax == 1) {
            tooltip.add(Text.translatable("tooltip.weaponfusing.consumable").formatted(Formatting.RED));
        }

        // display durability
        if (fuseDura < fuseDuraMax) {
            tooltip.add(Text.literal(""));
            tooltip.add(Text.translatable("tooltip.weaponfusing.durability", fuseDura, fuseDuraMax).setStyle(Style.EMPTY.withColor(0x00ff99)));
        }
        super.appendTooltip(stack, world, tooltip, context);
    }

    public void writeNBTData(ItemStack itemStack) {
        itemStack.getOrCreateNbt();

        assert itemStack.getNbt() != null;
        if (!itemStack.getNbt().contains("FuseMaterial")) {
            itemStack.getNbt().putString("FuseMaterial", "minecraft:air");
        }
        ItemStack fuseMaterial = getFuseMaterial(itemStack);

        itemStack.getNbt().putInt("CustomModelData", ItemList.GetCustomModelData(baseItem, fuseMaterial.getItem()));
    }
    @Override
    public ItemStack getDefaultStack() {
        ItemStack itemStack = new ItemStack(this);
        writeNBTData(itemStack);
        return itemStack;
    }

    @Override
    public int getItemBarColor(ItemStack stack) {
        return 0x00ff99;
    }

    @Override
    public int getItemBarStep(ItemStack stack) {
        return Math.round(13.0f * getFuseDurability(stack) / getMaxFuseDurability(stack));
    }

    @Override
    public boolean isItemBarVisible(ItemStack stack) {
        return getFuseDurability(stack) < getMaxFuseDurability(stack);
    }

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(ItemStack stack, EquipmentSlot slot) {
        var modifiers = super.getAttributeModifiers(stack, slot);
        if (slot == EquipmentSlot.MAINHAND) {
            ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();

            // copy all modifiers
            for (var entry : modifiers.entries()) {
                builder.put(entry.getKey(), entry.getValue());
//                if (entry.getKey() != EntityAttributes.GENERIC_ATTACK_DAMAGE) {
//                    builder.put(entry.getKey(), entry.getValue());
//                }
            }

            // Dynamically determine damage
            float damage = getBonusDmg(stack);

            builder.put(EntityAttributes.GENERIC_ATTACK_DAMAGE,
                    new EntityAttributeModifier(ATTACK_DAMAGE_MODIFIER_ID, "fuse_dmg_mod", damage, EntityAttributeModifier.Operation.ADDITION));

            return builder.build();
        }

        return modifiers;
    }

    public void DoEffects(ItemStack fuseMaterial, ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (fuseMaterial.isIn(ModItemTags.Effects.FLAMING)) {
            target.setOnFireFor(3);
        } else if (fuseMaterial.isIn(ModItemTags.Effects.FREEZING)) {
            target.addStatusEffect(new StatusEffectInstance(ModStatusEffects.FROZEN_EFFECT, 100, 0, true, true, true));
        } else if (fuseMaterial.isIn(ModItemTags.Effects.SHOCKING)) {
            target.addStatusEffect(new StatusEffectInstance(ModStatusEffects.SHOCK_EFFECT, 40, 0, true, true, true));
        } else if (fuseMaterial.isIn(ModItemTags.Effects.CAN_BE_EATEN) && attacker instanceof PlayerEntity player) {
            player.getHungerManager().add(1,0.5f);
        }
    }

    public void breakFuse(ItemStack stack, PlayerEntity player) {
        int slotNumber = player.getInventory().getSlotWithStack(stack);
        if (!player.getWorld().isClient) {
            ServerWorld serverWorld = (ServerWorld) player.getWorld();
            serverWorld.playSound(null, player.getBlockPos(), SoundEvents.ENTITY_ITEM_BREAK, SoundCategory.PLAYERS);
        }
        ItemStack replacementItem = baseItem.getDefaultStack();
        replacementItem.setDamage(stack.getDamage()+1);
        player.getInventory().setStack(slotNumber, replacementItem);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        ItemStack fuseMaterial = getFuseMaterial(stack);
        int fuseDurability = getFuseDurability(stack);
        stack.getOrCreateNbt().putInt("FuseDurability", fuseDurability-1);

        DoEffects(fuseMaterial, stack, target, attacker);

        if (fuseDurability-1 <= 0 && attacker instanceof PlayerEntity player) {
            breakFuse(stack, player);
        }
        return super.postHit(stack, target, attacker);
    }
}
