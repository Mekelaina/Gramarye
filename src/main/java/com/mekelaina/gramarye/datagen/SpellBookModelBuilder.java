package com.mekelaina.gramarye.datagen;

import com.google.common.base.Preconditions;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.client.model.generators.ModelBuilder;
import net.minecraftforge.client.model.generators.ModelFile;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

//Frankenstein class of ItemModelBuilder and some pieces that just aren't public... for some reason.

public class SpellBookModelBuilder extends ModelBuilder<SpellBookModelBuilder> {

    protected List<OverrideBuilder> overrides = new ArrayList<>();

    public SpellBookModelBuilder(ResourceLocation outputLocation, ExistingFileHelper existingFileHelper) {
        super(outputLocation, existingFileHelper);
    }

    public SpellBookModelBuilder uncheckedTexture(String key, ResourceLocation texture) {
        Preconditions.checkNotNull(key, "Key must not be null");
        Preconditions.checkNotNull(texture, "Texture must not be null");
        this.textures.put(key, texture.toString());
        return this;
    }

    public OverrideBuilder override() {
        OverrideBuilder ret = new OverrideBuilder();
        overrides.add(ret);
        return ret;
    }

    /**
     * Get an existing override builder
     *
     * @param index the index of the existing override builder
     * @return the override builder
     * @throws IndexOutOfBoundsException if {@code} index is out of bounds
     */
    public OverrideBuilder override(int index) {
        Preconditions.checkElementIndex(index, overrides.size(), "override");
        return overrides.get(index);
    }

    @Override
    public JsonObject toJson() {
        JsonObject root = super.toJson();
        if (!overrides.isEmpty()) {
            JsonArray overridesJson = new JsonArray();
            overrides.stream().map(OverrideBuilder::toJson).forEach(overridesJson::add);
            root.add("overrides", overridesJson);
        }
        return root;
    }

    public class OverrideBuilder {

        private ModelFile model;
        private final Map<ResourceLocation, Float> predicates = new LinkedHashMap<>();

        public SpellBookModelBuilder.OverrideBuilder model(ModelFile model) {
            this.model = model;
            model.assertExistence();
            return this;
        }

        public SpellBookModelBuilder.OverrideBuilder predicate(ResourceLocation key, float value) {
            this.predicates.put(key, value);
            return this;
        }

        public SpellBookModelBuilder end() { return SpellBookModelBuilder.this; }

        JsonObject toJson() {
            JsonObject ret = new JsonObject();
            JsonObject predicatesJson = new JsonObject();
            predicates.forEach((key, val) -> predicatesJson.addProperty(serializeLoc(key), val));
            ret.add("predicate", predicatesJson);
            ret.addProperty("model", serializeLoc(model.getLocation()));
            return ret;
        }

        String serializeLoc(ResourceLocation loc) {
            if (loc.getNamespace().equals("minecraft")) {
                return loc.getPath();
            }
            return loc.toString();
        }
    }
}
