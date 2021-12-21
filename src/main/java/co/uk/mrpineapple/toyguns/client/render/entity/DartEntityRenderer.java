package co.uk.mrpineapple.toyguns.client.render.entity;

import co.uk.mrpineapple.toyguns.common.entity.DartEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

/*
 * This is our render class for our projectile entity.
 * We need to extend the EntityRenderer and implement the entity we want to render - in this case it's our DartEntity
 * You should only ever have this if you are wanting to render the projectile after it has been shot.
 * Otherwise it will be a waste.
 */

/**
 * Author: Mr. Pineapple
 */
public class DartEntityRenderer extends EntityRenderer<DartEntity> {
    //Generic constructor
    public DartEntityRenderer(EntityRendererProvider.Context rendererManager) {
        super(rendererManager);
    }

    //We are going to be rendering a model, so the texture isn't needed.
    @Override
    public ResourceLocation getTextureLocation(DartEntity entity) {
        return null;
    }

    /*
     * This is our render method - where all the magic happens.
     */
    @Override
    public void render(DartEntity entity, float entityYaw, float partialTicks, PoseStack matrices, MultiBufferSource buffer, int light) {
        /*
         * In the gun JSON file we can determine if we want the bullets to be visible
         * We can check it here with the isVisible method provided by the Gun Mod.
         * In this case, if that field is not true, then we just return - which, in turn doesn't render anything
         *
         * However, we also check if the ticks that the entity has existed for is less than or equal to 1, and then we can remove it.
         */
        if(!entity.getProjectile().isVisible() || entity.tickCount <= 1) {
            return;
        }

        /*
         * With rendering we need to actually display the model.
         * Whenever we render we need to use the push method and then set our properties
         * Then when we are done, we need to remember to pop.
         * In this case we are only rendering one thing, so there's not too much here
         *
         * In the case of my Food Mod, when I am rendering things into the pizza oven
         * or the pizza box, I have a lot more in this method as I have a few checks
         * and more things are being rendered.
         * Any mod that dynamically has things rendered will most likely (or should at least)
         * be using a TileEntityRenderer which, in turn they will use code similar to this.
         * For examples you can view vanilla classes such as CampfireTileEntityRenderer.
         */
        matrices.pushPose();
        matrices.mulPose(Vector3f.YP.rotationDegrees(180F));
        matrices.mulPose(Vector3f.YP.rotationDegrees(entityYaw));
        matrices.mulPose(Vector3f.XP.rotationDegrees(entity.getXRot()));

        Minecraft.getInstance().getItemRenderer().renderStatic(entity.getItem(), ItemTransforms.TransformType.NONE, light, OverlayTexture.NO_OVERLAY, matrices, buffer, 0);
        matrices.popPose();
    }

}
