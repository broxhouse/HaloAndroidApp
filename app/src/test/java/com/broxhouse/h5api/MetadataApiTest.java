//
//
//package com.broxhouse.h5api;
//
//import com.broxhouse.h5api.models.metadata.*;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.junit.runners.JUnit4;
//import retrofit2.Response;
//
//import java.io.IOException;
//import java.util.List;
//
//@RunWith(JUnit4.class)
//public class MetadataApiTest extends BaseTest {
//
////    private static String ;
////
////    @BeforeClass
////    public static void setUpClass() {
////         = KeyReader.getApiKey();
////    }
//
//    @Test
//    public void testWeapons() throws IOException {
//        ApiFactory factory = new ApiFactory();
//        Metadata metadata = factory.getMetadata();
//        Response<List<Weapon>> weapons = metadata.getWeapons().execute();
//
//        Assert.assertEquals(200, weapons.code());
//    }
//
//    @Test
//    public void testVehicles() throws IOException {
//        ApiFactory factory = new ApiFactory();
//        Metadata metadata = factory.getMetadata();
//        Response<List<Vehicle>> vehicles = metadata.getVehicles().execute();
//        Assert.assertEquals(200, vehicles.code());
//    }
//
//    @Test
//    public void testTeamColor() throws IOException {
//        ApiFactory factory = new ApiFactory();
//        Metadata metadata = factory.getMetadata();
//        Response<List<TeamColor>> teamColors = metadata.getTeamColors().execute();
//        Assert.assertEquals(200, teamColors.code());
//    }
//
//    @Test
//    public void testSpartanRank() throws IOException {
//        ApiFactory factory = new ApiFactory();
//        Metadata metadata = factory.getMetadata();
//        Response<List<SpartanRank>> spartanRanks = metadata.getSpartanRanks().execute();
//        Assert.assertEquals(200, spartanRanks.code());
//    }
//
//    @Test
//    public void testSkull() throws IOException {
//        ApiFactory factory = new ApiFactory();
//        Metadata metadata = factory.getMetadata();
//        Response<List<Skull>> skulls = metadata.getSkulls().execute();
//        Assert.assertEquals(200, skulls.code());
//    }
//
//    @Test
//    public void testCampaignMissions() throws IOException {
//        ApiFactory factory = new ApiFactory();
//        Metadata metadata = factory.getMetadata();
//        Response<List<CampaignMission>> campaignMissions = metadata.getCampaignMissions().execute();
//        Assert.assertEquals(200, campaignMissions.code());
//    }
//
//    @Test
//    public void testCommendations() throws IOException {
//        ApiFactory factory = new ApiFactory();
//        Metadata metadata = factory.getMetadata();
//        Response<List<Commendation>> commendations = metadata.getCommendations().execute();
//        Assert.assertEquals(200, commendations.code());
//    }
//
//    @Test
//    public void testCSRDesignations() throws IOException {
//        ApiFactory factory = new ApiFactory();
//        Metadata metadata = factory.getMetadata();
//        Response<List<CSRDesignation>> csrDesignations = metadata.getCSRDesignations().execute();
//        Assert.assertEquals(200, csrDesignations.code());
//    }
//
//    @Test
//    public void testEnemies() throws IOException {
//        ApiFactory factory = new ApiFactory();
//        Metadata metadata = factory.getMetadata();
//        Response<List<Enemy>> enemies = metadata.getEnemies().execute();
//        Assert.assertEquals(200, enemies.code());
//    }
//
//    @Test
//    public void testFlexibleStats() throws IOException {
//        ApiFactory factory = new ApiFactory();
//        Metadata metadata = factory.getMetadata();
//        Response<List<FlexibleStat>> flexibleStats = metadata.getFlexibleStats().execute();
//        Assert.assertEquals(200, flexibleStats.code());
//    }
//
//    @Test
//    public void testGameBaseVariants() throws IOException {
//        ApiFactory factory = new ApiFactory();
//        Metadata metadata = factory.getMetadata();
//        Response<List<GameBaseVariant>> gameBaseVariants = metadata.getGameBaseVariants().execute();
//        Assert.assertEquals(200, gameBaseVariants.code());
//    }
//
//    @Test
//    public void testImpulses() throws IOException {
//        ApiFactory factory = new ApiFactory();
//        Metadata metadata = factory.getMetadata();
//        Response<List<Impulse>> impulses = metadata.getImpulses().execute();
//        Assert.assertEquals(200, impulses.code());
//    }
//
//    @Test
//    public void testMaps() throws IOException {
//        ApiFactory factory = new ApiFactory();
//        Metadata metadata = factory.getMetadata();
//        Response<List<Map>> maps = metadata.getMaps().execute();
//        Assert.assertEquals(200, maps.code());
//    }
//
//    @Test
//    public void testMedals() throws IOException {
//        ApiFactory factory = new ApiFactory();
//        Metadata metadata = factory.getMetadata();
//        Response<List<Medal>> response = metadata.getMedals().execute();
//        Assert.assertEquals(200, response.code());
//    }
//
//    @Test
//    public void testPlaylists() throws IOException {
//        ApiFactory factory = new ApiFactory();
//        Metadata metadata = factory.getMetadata();
//        Response<List<Playlist>> response = metadata.getPlaylists().execute();
//        Assert.assertEquals(200, response.code());
//    }
//}
