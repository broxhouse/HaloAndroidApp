

package com.broxhouse.h5api.models.stats.reports;


import com.google.gson.annotations.SerializedName;

import java.util.List;


public class BaseCarnageReport<S extends BasePlayerStats> {

    /**
     * A list of stats for each player who was present in the match.
     */
    @SerializedName("PlayerStats")
    private List<S> playerStats;

    /**
     * Indicates if the match is completed or not. Some match details are available while
     * the match is in-progress, but the behavior for incomplete matches in undefined.
     */
    @SerializedName("IsMatchOver")
    private boolean isMatchOver;

    /**
     * The length of the match. This is expressed as an ISO 8601 Duration.
     */
    @SerializedName("TotalDuration")
    private String totalDuration;

    /**
     * The variant of the map for this match. Map variants are available via the Metadata
     * API.
     */
    @SerializedName("MapVariantId")
    private String mapVariantId;

    /**
     * The variant of the game for this match. Game variants are available via the Metadata
     * API
     */
    @SerializedName("GameVariantId")
    private String gameVariantId;

    /**
     * The playlist ID of the match. Playlists are available via the Metadata API.
     */
    @SerializedName("PlaylistId")
    private String playlistId;

    /**
     * The ID of the base map for this match. Maps are available via the Metadata API.
     */
    @SerializedName("MapId")
    private String mapId;

    /**
     * The ID of the game base variant for this match. Game base variants are available via
     * the Metadata API.
     */
    @SerializedName("GameBaseVariantId")
    private String gameBaseVariantId;

    /**
     * Whether this was a team-based game or not.
     */
    @SerializedName("IsTeamGame")
    private boolean isTeamGame;

    public List<S> getPlayerStats() {
        return playerStats;
    }

    public boolean isMatchOver() {
        return isMatchOver;
    }

    public String getTotalDuration() {
        return totalDuration;
    }

    public String getMapVariantId() {
        return mapVariantId;
    }

    public String getGameVariantId() {
        return gameVariantId;
    }

    public String getPlaylistId() {
        return playlistId;
    }

    public String getMapId() {
        return mapId;
    }

    public String getGameBaseVariantId() {
        return gameBaseVariantId;
    }

    public boolean isTeamGame() {
        return isTeamGame;
    }
}
