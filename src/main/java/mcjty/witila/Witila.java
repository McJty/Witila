package mcjty.witila;

import mcjty.witila.proxy.CommonProxy;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

import java.io.File;

@Mod(modid = Witila.MODID, name="Witila", dependencies =
        "required-after:Forge@["+ Witila.MIN_FORGE_VER+",)",
        version = Witila.VERSION)
public class Witila {
    public static final String MODID = "witila";
    public static final String VERSION = "0.1.0";
    public static final String MIN_FORGE_VER = "11.15.0.1600";

    @SidedProxy(clientSide="mcjty.witila.proxy.ClientProxy", serverSide="mcjty.witila.proxy.ServerProxy")
    public static CommonProxy proxy;

    @Mod.Instance("NotEnoughWands")
    public static Witila instance;
    public static Logger logger;
    public static File mainConfigDir;
    public static File modConfigDir;
    public static Configuration config;

    /**
     * Run before anything else. Read your config, create blocks, items, etc, and
     * register them with the GameRegistry.
     */
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        logger = e.getModLog();
        mainConfigDir = e.getModConfigurationDirectory();
        modConfigDir = new File(mainConfigDir.getPath());
        config = new Configuration(new File(modConfigDir, "witila.cfg"));
        proxy.preInit(e);

//        FMLInterModComms.sendMessage("Waila", "register", "mcjty.wailasupport.WailaCompatibility.load");
    }

    /**
     * Do your mod setup. Build whatever data structures you care about. Register recipes.
     */
    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        proxy.init(e);
    }

    /**
     * Handle interaction with other mods, complete your setup based on this.
     */
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.postInit(e);
    }
}
