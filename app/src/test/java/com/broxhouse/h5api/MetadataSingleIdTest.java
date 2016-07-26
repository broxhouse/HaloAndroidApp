//
//
//package com.broxhouse.h5api;
//
//import com.broxhouse.h5api.models.metadata.Commendation;
//import com.broxhouse.h5api.models.metadata.GameVariant;
//import com.broxhouse.h5api.models.metadata.MapVariant;
//import com.broxhouse.h5api.models.metadata.RequisitionPack;
//import com.broxhouse.h5api.models.stats.matches.Match;
//import com.broxhouse.h5api.models.stats.matches.Page;
//import com.broxhouse.h5api.models.stats.reports.ArenaPlayerStats;
//import com.broxhouse.h5api.models.stats.reports.VersusCarnageReport;
//import com.broxhouse.h5api.utils.Constants;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.junit.runners.JUnit4;
//import retrofit2.Response;
//
//import java.io.IOException;
//import java.util.List;
//
///**
// * Tests metadata endpoints which require an ID to be sent
// */
//@RunWith(JUnit4.class)
//public class MetadataSingleIdTest extends BaseTest {
////    private static String apiKey;
//
//    {
//        setTimeout(Constants.TIMEOUT * 3);
//    }
//
////    @BeforeClass
////    public static void setUpClass() {
////        apiKey = KeyReader.getApiKey();
////    }
//
//    @Test
//    public void testGetGameVariant() throws IOException {
//        ApiFactory factory = new ApiFactory();
//        Stats stats = factory.getStats();
//
//        // Get most recent match
//        Match match = getMatchByType(stats, "arena");
//        Response<VersusCarnageReport<ArenaPlayerStats>> arenaReport = stats
//                .getArenaCarnageReport(match.getId().getMatchId())
//                .execute();
//        Assert.assertEquals(arenaReport.code(), 200);
//
//        VersusCarnageReport<ArenaPlayerStats> report = arenaReport.body();
//        String id = report.getGameVariantId();
//
//        Metadata metadata = factory.getMetadata();
//        Response<GameVariant> gVResponse = metadata.getGameVariant(id).execute();
//
//        Assert.assertEquals(200, gVResponse.code());
//    }
//
//    @Test
//    public void testGetMapVariant() throws IOException {
//        ApiFactory factory = new ApiFactory();
//        Stats stats = factory.getStats();
//
//        // Get most recent match
//        Match match = getMatchByType(stats, "arena");
//        Response<VersusCarnageReport<ArenaPlayerStats>> arenaReport = stats
//                .getArenaCarnageReport(match.getId().getMatchId())
//                .execute();
//        Assert.assertEquals(arenaReport.code(), 200);
//
//        VersusCarnageReport<ArenaPlayerStats> report = arenaReport.body();
//        String id = report.getMapVariantId();
//
//        Metadata metadata = factory.getMetadata();
//        Response<MapVariant> mVResponse = metadata.getMapVariant(id).execute();
//
//        Assert.assertEquals(200, mVResponse.code());
//    }
//
//    @Test
//    public void testGetRequisitionPack() throws IOException {
//        ApiFactory factory = new ApiFactory();
//        Metadata metadata = factory.getMetadata();
//
//        Response<List<Commendation>> commendationsResponse = metadata.getCommendations().execute();
//        Assert.assertEquals(200, commendationsResponse.code());
//
//        List<Commendation> comms = commendationsResponse.body();
//        Assert.assertNotEquals(0, comms.size());
//
//        RequisitionPack pack = null;
//        for (Commendation comm : comms) {
//
//            if (comm.getReward() == null) {
//                continue;
//            }
//
//            if (comm.getReward().getRequisitionPacks() == null) {
//                continue;
//            }
//
//            if (comm.getReward().getRequisitionPacks().size() != 0) {
//                pack = comm.getReward().getRequisitionPacks().get(0);
//                break;
//            }
//        }
//        Assert.assertNotNull(pack);
//
//        String packId = pack.getId();
//        Response<RequisitionPack> rpResponse = metadata.getRequisitionPack(packId).execute();
//        Assert.assertEquals(200, rpResponse.code());
//
//    }
//
//    @Test
//    public void testGetRequisition() {
//        // TODO
//    }
//
//    private Match getMatchByType(Stats stats, String type) throws IOException {
//        Response<Page<Match>> response = stats.getRecentMatchInfo(
//                Constants.PLAYER,
//                type,
//                0,
//                1).execute();
//
//        Assert.assertEquals(response.code(), 200);
//        Assert.assertEquals(response.body().getResultCount(), 1);
//        return response.body().getResults().get(0);
//    }
//}
