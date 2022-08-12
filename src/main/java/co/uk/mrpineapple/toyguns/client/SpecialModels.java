package co.uk.mrpineapple.toyguns.client;

import co.uk.mrpineapple.toyguns.core.ToyGuns;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

/*
 * This class will be used to register special models (like the grenade launcher)
 * We can 'copy' from the SpecialModels class in the base gun mod as there
 * isn't an interface provided to implement.
 */

/**
 * Author: Mr. Pineapple
 */
@EventBusSubscriber(modid = ToyGuns.ID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public enum SpecialModels {
    //The values in this class are stored here so we can call upon them.
    REVOLVER("gun/multi/revolver"),
    REVOLVER_CHAMBER("gun/multi/revolver_chamber");

    //Variables
    private ResourceLocation modelLocation;
    private boolean specialModel;
    @OnlyIn(Dist.CLIENT)
    private BakedModel cachedModel;

    SpecialModels(String modelName) {
        //Get the file path for the special modes, and set them to true (the are going to be special models)
        this(new ResourceLocation(ToyGuns.ID, "special/" + modelName), true);
    }

    //Second Constructor to feed variables
    SpecialModels(ResourceLocation resourceLocation, boolean specialModel) {
        this.modelLocation = resourceLocation;
        this.specialModel = specialModel;
    }

    //Get the item's model
    @OnlyIn(Dist.CLIENT)
    public BakedModel getModel() {
        if(this.cachedModel == null) {
            BakedModel model = Minecraft.getInstance().getModelManager().getModel(this.modelLocation);
            if(model == Minecraft.getInstance().getModelManager().getMissingModel()) {
                return model;
            } this.cachedModel = model;
        } return this.cachedModel;
    }

    public static void registerAdditional(ModelEvent.RegisterAdditional event) {
        for(SpecialModels model : values()) {
            if(model.specialModel) {
                event.register(model.modelLocation);
            }
        }
    }

    public static void clearCache() {
        for(SpecialModels model : values()) {
            if(model.specialModel) {
                model.cachedModel = null;
            }
        }
    }
}
