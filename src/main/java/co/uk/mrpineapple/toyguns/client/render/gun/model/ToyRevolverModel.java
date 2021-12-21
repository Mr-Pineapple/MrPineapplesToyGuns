package co.uk.mrpineapple.toyguns.client.render.gun.model;

import co.uk.mrpineapple.toyguns.client.SpecialModels;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.mrcrayfish.guns.client.render.gun.IOverrideModel;
import com.mrcrayfish.guns.client.util.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemCooldowns;
import net.minecraft.world.item.ItemStack;

/*
 * Because the revolver has a rotating chamber, we need to render it in a
 * different way than normal items. In this case we are overriding the model.
 */

/**
 * Author: Mr. Pineapple
 */
public class ToyRevolverModel implements IOverrideModel {

    //The render method, similar to what is in DartEntity. We can render the item
    @Override
    public void render(float v, ItemTransforms.TransformType transformType, ItemStack stack, ItemStack parent, LivingEntity entity, PoseStack matrices, MultiBufferSource renderBuffer, int light, int overlay) {
        //Wer're rendering the body part of the revolved, which, because it is static, we don't need to manipulate
        RenderUtil.renderModel(SpecialModels.REVOLVER.getModel(), transformType, null, stack, parent, matrices, renderBuffer, light, overlay);

        //Always push
        matrices.pushPose();
        //Here we're moving the model into position
        matrices.translate(0, -4.8 * 0.0625, 0);

        //We're getting the cooldown tracker for the item - items like the sword, ender pearl, and chorus fruit all have this too.
        ItemCooldowns tracker = Minecraft.getInstance().player.getCooldowns();
        float cooldown = tracker.getCooldownPercent(stack.getItem(), Minecraft.getInstance().getFrameTime());
        cooldown = (float)easeInOutBack(cooldown);

        //If the gun is jammed we do not want to rotate the chamber
        if(!stack.getTag().getBoolean("isJammed")) {
            //We rotate the chamber part of the model according to the cooldown variable above, which is manipulated by the method provided below.
            matrices.mulPose(Vector3f.ZN.rotationDegrees(45F * cooldown));
        }
        //Move model into place
        matrices.translate(0, 5.8 * 0.0625, 0);

        //Render the chamber part of the gun
        RenderUtil.renderModel(SpecialModels.REVOLVER_CHAMBER.getModel(), transformType, null, stack, parent, matrices, renderBuffer, light, overlay);
        //Always pop
        matrices.popPose();
    }

    //Same method from GrenadeLauncherModel, to make a smooth rotation of the chamber.
    private double easeInOutBack(double x) {
        double c1 = 1.70158;
        double c2 = c1 * 1.525;
        return (x < 0.5 ? (Math.pow(2 * x, 2) * ((c2 + 1) * 2 * x - c2)) / 2 : (Math.pow(2 * x - 2, 2) * ((c2 + 1) * (x * 2 - 2) + c2) + 2) / 2);
    }

    //TODO comments
}
