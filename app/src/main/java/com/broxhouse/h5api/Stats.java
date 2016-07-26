//
//
//package com.broxhouse.h5api;
//
//import com.broxhouse.h5api.models.stats.matches.Match;
//import com.broxhouse.h5api.models.stats.matches.Page;
//import com.broxhouse.h5api.models.stats.reports.*;
//import com.broxhouse.h5api.models.stats.servicerecords.*;
//import retrofit2.Call;
//import retrofit2.http.GET;
//import retrofit2.http.Path;
//import retrofit2.http.Query;
//
//public interface Stats {
//
//    /**
//     * Retrieves a list of matches that the player has participated in
//     * and which have completed processing. If the player is currently
//     * in a match, it is not returned in this API. Matches will usually
//     * appear in this list within a minute of the match ending.
//     *
//     * @param player    The Player's gamertag.
//     * @param modes     combination of warzone, custom, arena, or campaign
//     * @param start     Start offset
//     * @param count     # results to return
//     * @return Call object for desired report
//     */
//    @GET("/stats/h5/players/{player}/matches")
//    Call<Page<Match>> getRecentMatchInfo(@Path("player") String player,
//                                         @Query("modes") String modes,
//                                         @Query("start") int start,
//                                         @Query("count") int count);
//
//    /**
//     * Retrieves detailed statistics for an arena match.
//     * Some match details are available while the match is in-progress,
//     * but the behavior for incomplete matches in undefined.
//     *
//     * @param matchId An ID that uniquely identifies a match. Match IDs
//     *                can be retrieved from the "GET Matches for Player" API.
//     * @return Call object for desired report
//     */
//    @GET("/stats/h5/arena/matches/{matchId}")
//    Call<VersusCarnageReport<ArenaPlayerStats>> getArenaCarnageReport(@Path("matchId") String matchId);
//
//    /**
//     * Retrieves detailed statistics for a warzone match.
//     * Some match details are available while the match is in-progress,
//     * but the behavior for incomplete matches in undefined.
//     *
//     * @param matchId An ID that uniquely identifies a match. Match IDs
//     *                can be retrieved from the "GET Matches for Player" API.
//     * @return Call object for desired report
//     */
//    @GET("/stats/h5/warzone/matches/{matchId}")
//    Call<VersusCarnageReport<WarzonePlayerStats>> getWarzoneCarnageReport(@Path("matchId") String matchId);
//
//    /**
//     * Retrieves detailed statistics for a custom match.
//     * Some match details are available while the match is in-progress,
//     * but the behavior for incomplete matches in undefined.
//     * @param matchId An ID that uniquely identifies a match.
//     *                Match IDs can be retrieved from the "GET Matches for Player" API.
//     * @return Call object for desired report
//     */
//    @GET("/stats/h5/custom/matches/{matchId}")
//    Call<VersusCarnageReport<VersusPlayerStats>> getCustomCarnageReport(@Path("matchId") String matchId);
//
//    /**
//     * Retrieves detailed statistics for a campaign match.
//     * Some match details are available while the match is in-progress,
//     * but the behavior for incomplete matches in undefined.
//     * @param matchId An ID that uniquely identifies a match.
//     *                Match IDs can be retrieved from the "GET Matches for Player" API.
//     * @return Call object for desired report
//     */
//    @GET("/stats/h5/campaign/matches/{matchId}")
//    Call<CampaignCarnageReport> getCampaignCarnageReport(@Path("matchId") String matchId);
//
//    /**
//     * Retrieves players' Warzone Service Records.
//     * A Service Record contains a player's lifetime statistics in the game mode.
//     *
//     * @param gamertags A list of gamertags to query service records for
//     * @return Call object for desired service record
//     */
//    @GET("/stats/h5/servicerecords/warzone")
//    Call<ServiceRecordCollection<WarzoneResult>> getWarzoneServiceRecords(@Query("players") String gamertags);
//
//    /**
//     * Retrieves players' Arena Service Records.
//     * A Service Record contains a player's lifetime statistics in the game mode.
//     *
//     * @param gamertags A list of gamertags to query service records for
//     * @return Call object for desired service record
//     */
//    @GET("/stats/h5/servicerecords/arena")
//    Call<ServiceRecordCollection<ArenaResult>> getArenaServiceRecords(@Query("players") String gamertags);
//
//    /**
//     * Retrieves players' Custom Game Service Records.
//     * A Service Record contains a player's lifetime statistics in the game mode.
//     *
//     * @param gamertags A list of gamertags to query service records for
//     * @return Call object for desired service record
//     */
//    @GET("/stats/h5/servicerecords/custom")
//    Call<ServiceRecordCollection<CustomResult>> getCustomServiceRecords(@Query("players") String gamertags);
//
//    /**
//     * Retrieves players' Campaign Service Records.
//     * A Service Record contains a player's lifetime statistics in the game mode.
//     *
//     * @param gamertags A list of gamertags to query service records for
//     * @return Call object for desired service record
//     */
//    @GET("/stats/h5/servicerecords/campaign")
//    Call<ServiceRecordCollection<CampaignResult>> getCampaignServiceRecords(@Query("players") String gamertags);
//}
