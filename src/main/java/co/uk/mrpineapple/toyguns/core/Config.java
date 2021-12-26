package co.uk.mrpineapple.toyguns.core;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

/*
 * This is our config class. In here we can specify things that can be changed by the player.
 * The config allows users to take more control of the mod. For instance should a biome spawn?
 *
 * In this case, currently, I am only letting the player decide the chance of a dart being lost.
 */

/**
 * Author: Mr. Pineapple
 */
public class Config {
    /*
     * This is an inner class of the config class.
     * This is the common config.
     * In the future we might have a client and server config.
     */
    public static class Common {
        //Initialise our value
        public final ForgeConfigSpec.BooleanValue dartLoss;
        public final ForgeConfigSpec.IntValue dartLossChance;
        public final ForgeConfigSpec.BooleanValue gunJams;
        public final ForgeConfigSpec.IntValue dartJamChance;
        public final ForgeConfigSpec.BooleanValue showJamStatusMessage;

        //Constructor for the Common Config
        Common(ForgeConfigSpec.Builder builder) {
            //What we will be pushing to the config, this has a title of common
            builder.push("common"); {
                /*
                 * We can then add a comment (highly recommended) to inform the user what they are changing.
                 * Notice here that we are connecting it the value from above.
                 * Then in the case of an integer we need to set the range.
                 * This takes in the path (what it will be called). The default value. The minimum value. Then the max value.
                 */
                this.dartLoss = builder.comment("Should some darts have a chance to be lost when shot? if set to false the chance will be ignored").define("enableDartLoss", true);
                this.dartLossChance = builder.comment("The chance of a dart being lost when shot is 1/this number").defineInRange("dartLossMinimum", 20, 0, Integer.MAX_VALUE);
                this.gunJams = builder.comment("Should Toy Guns jam? if set to false the chance will be ignored").define("enableGunJamming", true);
                this.dartJamChance = builder.comment("The chance of a dart being lost is 1/this number").defineInRange("dartJamMinimum", 100, 1, Integer.MAX_VALUE);
                this.showJamStatusMessage = builder.comment("Show a status message when a gun jams").define("showGunJamStatusMessage", true);

            }
            //Remember to pop this section
            builder.pop();
        }
    }

    /*
     * Now we need to be able to access these values across our project.
     * We create a static variable of COMMON (same goes for server/client when added) so we can call the values in the mod.
     * Then we initialise them.
     */
    static final ForgeConfigSpec commonSpec;
    public static final Config.Common COMMON;

    static {
        final Pair<Common, ForgeConfigSpec> commonSpecPair = new ForgeConfigSpec.Builder().configure(Common::new);
        commonSpec = commonSpecPair.getRight();
        COMMON = commonSpecPair.getLeft();
    }
}
