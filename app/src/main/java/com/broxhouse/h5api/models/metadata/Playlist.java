

package com.broxhouse.h5api.models.metadata;

import com.google.gson.annotations.SerializedName;

public class Playlist {
    /**
     * A localized name for the playlist, suitable for display to users. The text is
     * title cased.
     */
    private String name;

    /**
     * A localized description for the playlist, suitable for display to users.
     */
    private String description;

    /**
     * Indicates if a Csr (competitive skill rank) is shown for players who participate
     * in this playlist.
     */
    @SerializedName("isRanked")
    private boolean isRanked;

    /**
     * An image used to illustrate this playlist.
     */
    private String imageUrl;

    /**
     * The game mode played in this playlist. Options are:
     * - Arena
     * - Campaign
     * - Custom
     * - Warzone
     */
    private String gameMode;

    /**
     * Indicates if this playlist is currently available for play.
     */
    @SerializedName("isActive")
    private boolean isActive;

    /**
     * The ID that uniquely identifies this playlist.
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

    public boolean isRanked() {
        return isRanked;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getGameMode() {
        return gameMode;
    }

    public boolean isActive() {
        return isActive;
    }

    public String getId() {
        return id;
    }
}
