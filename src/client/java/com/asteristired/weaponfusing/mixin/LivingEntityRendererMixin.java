package com.asteristired.weaponfusing.mixin;

import com.asteristired.weaponfusing.client.Entity.feature.FrozenOverlayFeature;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntityRenderer.class)
public abstract class LivingEntityRendererMixin<T extends LivingEntity, M extends EntityModel<T>> {

    @SuppressWarnings("unchecked") // kinda sucks that i gotta do this but I gotta do it
    @Inject(method = "<init>", at = @At("TAIL"))
    private void addFrozenOverlayFeature(EntityRendererFactory.Context context, EntityModel<T> model, float shadowRadius, CallbackInfo ci) {
        LivingEntityRendererAccessor<T, M> accessor = (LivingEntityRendererAccessor<T, M>) this;
        accessor.getFeatures().add(new FrozenOverlayFeature<>((LivingEntityRenderer<T, M>) (Object) this));
    }
}

