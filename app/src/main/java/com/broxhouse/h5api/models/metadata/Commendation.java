

package com.broxhouse.h5api.models.metadata;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Commendation {

    public static class Level {

        /**
         * levels: The reward for earning this level.
         * requiredLevels: null
         */
        private Reward reward;

        /**
         * levels: For progressive commendations this indicates the threshold that the player
         * must meet or exceed to consider the commendation level "completed". For meta
         * commendations, this value is always zero.
         * ----
         * requiredLevels: The threshold that the player must meet or exceed in order
         * to consider the level requirement met.
         */
        private int threshold;

        /**
         * The ID that uniquely identifies this commendation level or the level that must
         * be met.
         */
        private String id;

        /**
         * Internal use only. Do not use.
         */
        @SerializedName("contentId")
        private String contentId;

        public Reward getReward() {
            return reward;
        }

        public int getThreshold() {
            return threshold;
        }

        public String getId() {
            return id;
        }
    }

    public static class Category {
        /**
         * A localized name for the category, suitable for display to users. The text is
         * title cased.
         */
        private String name;

        /**
         * An image that is used as the icon for this category.
         */
        private String iconImageUrl;

        /**
         * Internal use. The order in which the category should be displayed relative to
         * other categories. The lower the value, the more important the category - more
         * important categories should be shown before or ahead of less important
         * categories.
         */
        private int order;

        /**
         * The ID that uniquely identifies this category.
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

        public String getIconImageUrl() {
            return iconImageUrl;
        }

        public int getOrder() {
            return order;
        }

        public String getId() {
            return id;
        }
    }

    /**
     * Progressive or Meta
     */
    private String type;

    /**
     * Localized name
     */
    private String name;

    /**
     * Localized Description
     */
    private String description;

    /**
     * Icon image for the commendation
     */
    private String iconImageUrl;

    /**
     * One or more levels that model what a player must do to earn rewards and complete
     * the commendation.
     */
    private List<Level> levels;

    /**
     * For meta commendations, the commendation is considered "completed" when all
     * required levels have been "completed". This list contains one or more Level Ids
     * from other commendations. For progressive commendations, this list is empty.
     */
    private List<Level> requiredLevels;

    /**
     * The reward the player will receive for earning this commendation.
     */
    private Reward reward;

    /**
     * Information about how this commendation should be categorized when shown to users.
     */
    private Category category;

    /**
     * Whether this commendation is enabled or not.
     */
    private boolean enabled;

    /**
     * The ID that uniquely identifies this commendation.
     */
    private String id;

    /**
     * Internal use only. Do not use.
     */
    @SerializedName("contentId")
    private String contentId;

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getIconImageUrl() {
        return iconImageUrl;
    }

    public List<Level> getLevels() {
        return levels;
    }

    public List<Level> getRequiredLevels() {
        return requiredLevels;
    }

    public Reward getReward() {
        return reward;
    }

    public Category getCategory() {
        return category;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getId() {
        return id;
    }
}
