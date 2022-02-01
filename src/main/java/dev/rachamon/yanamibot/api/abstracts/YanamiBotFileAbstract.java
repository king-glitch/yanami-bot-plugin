package dev.rachamon.yanamibot.api.abstracts;

import com.google.common.reflect.TypeToken;
import dev.rachamon.yanamibot.YanamiBot;
import ninja.leaping.configurate.ConfigurationOptions;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

/**
 * The type Yanami bot file abstract.
 *
 * @param <T> the type parameter
 */
public class YanamiBotFileAbstract<T> {
    private final YanamiBot plugin;

    private T root;
    private CommentedConfigurationNode configRoot;
    private ConfigurationLoader<CommentedConfigurationNode> configLoader;


    private Class<T> clazzType;
    private T clazz;
    private final String name;
    private String header;

    /**
     * Instantiates a new Yanami bot file abstract.
     *
     * @param plugin   the plugin
     * @param fileName the file name
     */
    public YanamiBotFileAbstract(YanamiBot plugin, String fileName) {
        this.plugin = plugin;
        this.name = fileName;
        this.plugin.getLogger().info("Loading configuration -> " + fileName + " config module");

    }

    /**
     * Build t.
     *
     * @return the t
     */
    public T build() {
        try {
            Files.createDirectories(plugin.getDirectory().toFile().toPath());
            File mainConfig = new File(this.plugin.getDirectory().toFile(), this.getName());
            if (!mainConfig.exists()) {
                this.plugin.getLogger().info("Creating " + this.getName() + " Configuration...");
                mainConfig.createNewFile();
            }

            configLoader = HoconConfigurationLoader.builder().setFile(mainConfig).build();
            configRoot = configLoader.load(ConfigurationOptions.defaults().withObjectMapperFactory(this.plugin.getFactory()).withShouldCopyDefaults(true).setHeader(this.header));
            root = configRoot.getValue(TypeToken.of(this.getClazzType()), this.getClazz());

            this.save();
            plugin.getLogger().success("loaded " + this.getName() + " configuration...");
            return clazz;

        } catch (ObjectMappingException | IOException e) {
            this.plugin.getLogger().error(Arrays.toString(e.getStackTrace()));
        }
        return clazz;
    }

    /**
     * Save.
     *
     * @throws ObjectMappingException the object mapping exception
     * @throws IOException            the io exception
     */
    public void save() throws ObjectMappingException, IOException {
        try {
            configRoot.setValue(TypeToken.of(this.getClazzType()), this.getRoot());
            this.configLoader.save(configRoot);
        } catch (IOException | ObjectMappingException e) {
            this.plugin.getLogger().error(e.getMessage());
        }
    }

    /**
     * Save.
     *
     * @param clazz the clazz
     * @param root  the root
     */
    public void save(Class<T> clazz, T root) {
        try {
            configRoot.setValue(TypeToken.of(clazz), root);
            this.configLoader.save(configRoot);
        } catch (IOException | ObjectMappingException e) {
            this.plugin.getLogger().error(e.getMessage());
        }
    }

    /**
     * Gets root.
     *
     * @return the root
     */
    public T getRoot() {
        return this.root;
    }

    /**
     * Gets config root.
     *
     * @return the config root
     */
    public CommentedConfigurationNode getConfigRoot() {
        return configRoot;
    }

    /**
     * Gets config loader.
     *
     * @return the config loader
     */
    public ConfigurationLoader<CommentedConfigurationNode> getConfigLoader() {
        return configLoader;
    }

    /**
     * Gets clazz type.
     *
     * @return the clazz type
     */
    public Class<T> getClazzType() {
        return clazzType;
    }

    /**
     * Gets clazz.
     *
     * @return the clazz
     */
    public T getClazz() {
        return clazz;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets clazz type.
     *
     * @param clazzType the clazz type
     * @return the clazz type
     */
    public YanamiBotFileAbstract<T> setClazzType(Class<T> clazzType) {
        this.clazzType = clazzType;
        return this;
    }

    /**
     * Sets clazz.
     *
     * @param clazz the clazz
     * @return the clazz
     */
    public YanamiBotFileAbstract<T> setClazz(T clazz) {
        this.clazz = clazz;
        return this;
    }


    /**
     * Sets header.
     *
     * @param header the header
     * @return the header
     */
    public YanamiBotFileAbstract<T> setHeader(String header) {
        this.header = header;
        return this;
    }
}
