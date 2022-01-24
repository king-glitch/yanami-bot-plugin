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

public class YanamiBotFileAbstract<T> {
    private final YanamiBot plugin;

    private T root;
    private CommentedConfigurationNode configRoot;
    private ConfigurationLoader<CommentedConfigurationNode> configLoader;


    private Class<T> clazzType;
    private T clazz;
    private String name;
    private String header;

    public YanamiBotFileAbstract(YanamiBot plugin, String fileName) {
        this.plugin = plugin;
        this.name = fileName;
        this.plugin.getLogger().info("Loading configuration -> " + fileName + " config module");

    }

    public T build() {
        try {
            Files.createDirectories(plugin.getDirectory().toFile().toPath());
            File mainConfig = new File(this.plugin.getDirectory().toFile(), this.getName());
            if (!mainConfig.exists()) {
                this.plugin.getLogger().info("Creating " + this.getName() + " Configuration...");
                mainConfig.createNewFile();
            }

            configLoader = HoconConfigurationLoader.builder().setFile(mainConfig).build();
            configRoot = configLoader.load(ConfigurationOptions.defaults().setHeader(this.header));
            root = configRoot.getValue(TypeToken.of(this.getClazzType()), this.getClazz());

            this.save();
            plugin.getLogger().success("loaded " + this.getName() + " configuration...");
            return clazz;

        } catch (ObjectMappingException | IOException e) {
            this.plugin.getLogger().error(Arrays.toString(e.getStackTrace()));
        }
        return clazz;
    }

    protected void save() {
        try {
            configRoot.setValue(TypeToken.of(this.getClazzType()), this.getRoot());
            this.configLoader.save(configRoot);
        } catch (IOException | ObjectMappingException e) {
            this.plugin.getLogger().error(Arrays.toString(e.getStackTrace()));
        }
    }

    protected T getRoot() {
        return this.root;
    }

    public Class<T> getClazzType() {
        return clazzType;
    }

    public T getClazz() {
        return clazz;
    }

    public String getName() {
        return name;
    }

    public YanamiBotFileAbstract<T> setClazzType(Class<T> clazzType) {
        this.clazzType = clazzType;
        return this;
    }

    public YanamiBotFileAbstract<T> setClazz(T clazz) {
        this.clazz = clazz;
        return this;
    }

    public YanamiBotFileAbstract<T> setName(String name) {
        this.name = name;
        return this;
    }

    public YanamiBotFileAbstract<T> setHeader(String header) {
        this.header = header;
        return this;
    }
}
