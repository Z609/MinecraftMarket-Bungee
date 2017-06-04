package me.z609.api;

import com.google.common.io.ByteStreams;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.*;

/**
 * Created by albert on 6/4/17.
 */
public class BungeeConfigurations {

    private static ConfigurationProvider provider = ConfigurationProvider.getProvider(YamlConfiguration.class);

    public static Configuration getConfiguration(File file) throws IOException {
        if(!file.exists())
            createDefaultConfig(file, file.getName());
        return provider.load(file);
    }

    public static void saveConfiguration(Configuration configuration, File file) throws IOException {
        if(!file.getParentFile().exists())
            file.getParentFile().mkdirs();
        provider.save(configuration, file);
    }

    public static boolean createDefaultConfig(File file, String resourcePath){
        if(file.exists())
            return false;
        try {
            if(!file.getParentFile().exists())
                file.getParentFile().mkdirs();
            file.createNewFile();
            try (InputStream input = BungeeConfigurations.class.getClassLoader().getResourceAsStream(resourcePath);
                 OutputStream output = new FileOutputStream(file)) {
                ByteStreams.copy(input, output);
            }
            return true;
        } catch (Exception ex) {
            System.err.println("[z609-MCM] Could not load resource \"" + resourcePath + "\"");
            ex.printStackTrace();
            return false;
        }
    }

}
