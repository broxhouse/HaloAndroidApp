

package com.broxhouse.h5api.models.metadata;

import com.google.gson.annotations.SerializedName;

public class MapVariant {
    /**
     * A localized name, suitable for display to users.
     */
    private String name;

    /**
     * A localized description, suitable for display to users.
     */
    private String description;

    /**
     * A reference to an image. This may be null if there is no image defined.
     */
    private String mapImageUrl;

    /**
     * The ID of the map this is a variant for. Maps are available via the Metadata API.
     */
    private String mapId;

    /**
     * The ID that uniquely identifies this map variant.
     */
    private String id;

    /**
     * Internal use only. Do not use.
     */
    @SerializedName("contentId")
    private String contentId;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getMapImageUrl() {
        return mapImageUrl;
    }

    public String getMapId() {
        return mapId;
    }

    public String getId() {
        return id;
    }
}
