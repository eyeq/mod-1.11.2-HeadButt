package eyeq.headbutt;

import eyeq.headbutt.event.HeadButtEventHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import static eyeq.headbutt.HeadButt.MOD_ID;

@Mod(modid = MOD_ID, version = "1.0", dependencies = "after:eyeq_util")
public class HeadButt {
    public static final String MOD_ID = "eyeq_headbutt";

    @Mod.Instance(MOD_ID)
    public static HeadButt instance;

    public static int harvestLevel;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new HeadButtEventHandler());
        load(new Configuration(event.getSuggestedConfigurationFile()));
    }

    public static void load(Configuration config) {
        config.load();

        String category = "Int";
        harvestLevel = config.get(category, "harvestLevel", 0).getInt();

        if(config.hasChanged()) {
            config.save();
        }
    }
}
