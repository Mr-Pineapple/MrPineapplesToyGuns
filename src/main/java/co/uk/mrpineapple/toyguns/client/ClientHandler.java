package co.uk.mrpineapple.toyguns.client;

import co.uk.mrpineapple.toyguns.client.render.entity.DartEntityRenderer;
import co.uk.mrpineapple.toyguns.core.ToyGuns;
import co.uk.mrpineapple.toyguns.core.registry.EntityRegistry;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * Author: Mr. Pineapple
 */
@Mod.EventBusSubscriber(modid = ToyGuns.ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientHandler {
//
//    //Here we bind the renderer class to the entity - so it knows what to render for the entity.
//        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.DART.get(), DartEntityRenderer::new);

    public static final ModelLayerLocation DART_ENTITY = new ModelLayerLocation(new ResourceLocation(ToyGuns.ID, "goblin_trader"), "main");

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event)
    {
        event.registerEntityRenderer(EntityRegistry.DART.get(), DartEntityRenderer::new);
    }
}
