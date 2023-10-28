package spotify.oauth2.tests;

import org.testng.annotations.Test;

import com.spotify.api.StatusCode;
import com.spotify.api.applicationapi.PlaylistApi;
import com.spotify.pojo.Errorroot;
import com.spotify.pojo.Item;
import com.spotify.utils.DataLoader;
import com.spotify.utils.FakerUtils;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Link;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

@Epic("Spotify oauth 2.0")
@Feature("Create Playlist with different Scenario")
public class PlaylistTests extends BaseTest{

	@Step
	public Item itemlist(String name, String description, boolean _public)
	{
		
		return  Item.builder().name(name).description(description)._public(_public).build();
		
		/*Item requestitem=new Item();
		requestitem.setName(name);
		requestitem.setDescription(description);
		requestitem.set_public(_public);
		return requestitem;*/
	}

	@Step
	public void assertEqual(Item respitem, Item requestitem )
	{
		assertThat(requestitem.getName(), equalTo(respitem.getName()));
		assertThat(requestitem.getDescription(), equalTo(respitem.getDescription()));
		assertThat(requestitem.get_public(), equalTo(respitem.get_public()));
	}
	
	@Step
	public void assertStatusCode(int statuscode, int expectedStatusCode)
	{
		assertThat(statuscode, equalTo(expectedStatusCode));
	}
	
	public void assertGetPlaylist(Item respitem, String name, String description, String playlistid)
	{
		assertThat(respitem.getName(), equalTo(name));
		assertThat(respitem.getDescription(), equalTo(description));
		assertThat(respitem.getId(), equalTo(playlistid));
	}
	
	
	public void assertError(Errorroot respitem, int statuscode, String expectedmessage)
	{
		assertThat(respitem.getError().getStatus(), equalTo(StatusCode.CODE_400.getCode()));
		assertThat(respitem.getError().getMessage(), equalTo(StatusCode.CODE_400.getmsg()));
	}
	
	@Story("create a playlist")
	@Link("https://accounts.spotify.com")
	@Issue("1234")
	@TmsLink("123")
	@Link(name="allure", type="mylink")
	@Description("creating playlist new playlist")
	@Test(description="should be to create playlist")
	public void createPlayList() 
	{
		Item requestitem=itemlist(FakerUtils.generateName(), FakerUtils.generateDescription(), false);
		System.out.println(requestitem);
		Response resp=	PlaylistApi.post(requestitem);
		
		assertStatusCode(resp.getStatusCode(), StatusCode.CODE_201.getCode());
	
		Item respitem=	resp.as(Item.class);
	
		assertEqual(requestitem, respitem);
		
	}
	
	@Test
	public void getPlaylist()
	{
		//String itemid="6Dlv6SFA76QwUouzUdkeKc";
		
		Response res=PlaylistApi.get(DataLoader.getInstance().getplaylistid());
		
		assertStatusCode(res.getStatusCode(), StatusCode.CODE_200.getCode());
		
		Item respitem=res.as(Item.class);
		
		assertGetPlaylist(respitem, "new playlist", "new playlist description", DataLoader.getInstance().getplaylistid());
		
		/*assertThat(respitem.getName(), equalTo("new playlist"));
		assertThat(respitem.getDescription(), equalTo("new playlist description"));
		assertThat(respitem.getId(), equalTo(DataLoader.getInstance().getplaylistid()));*/
		
	}
	
	/*@Test
	public void updatePlaylist()
	{
		
		String payload="{\n"+
			    " \"name\": \"SpotPlaylist\", \n" +
			    " \"description\": \"SpotUpdatedplaylistdescription\", \n"  +
			    " \"public\": false\n" +
			 "}";
		given(requestSpecification)
		.body(payload)
		.when()
		.put("playlists/5Px6MGrhpnaI2si1D3rsHP")
		.then()
		.log()
		.all()
		.assertThat()
		.statusCode(200)
		.body("name", equalTo("SpotPlaylist"),
				"description", equalTo("SpotUpdatedplaylistdescription"));
	}*/
	
	@Story("create a playlist")
	@Test
	public void createPlayListWithOutName()
	{
	
		
		Item requestitem=itemlist("", "", false);
		
		Response resp=	PlaylistApi.post(requestitem);
		assertThat(resp.getStatusCode(), equalTo(400));
	
		Errorroot respitem=resp.as(Errorroot.class);
		
		System.out.println("the status code:" +respitem.getError().getStatus());
		
		assertError(respitem, StatusCode.CODE_400.getCode(), StatusCode.CODE_400.getmsg());
		
	
	}
	
	@Story("create a playlist")
	@Test
	public void createPlayListWithExpiredToken()
	{
		String token1="QCB6Z1WCfxqaGMy1dFSZAKmBM41A4RfDy0Qh8AosQ7H4hyWI6wpHUo-FXDN5x6CPa2v50Ct02F88EWW4AAHDsI7MS1cT3Bb-mfTsfi_Zd2xgX4uTEk3Bs4sBbolcbp98w3naMAvt_--RG0nWOwx6llaiqnLD_4lSsafjWzvctsl2JOtzTeoMwegx1XeW7ey4mkKDVYmfcirC4U9VmR9XXf2q9QjhdcEXpWx9_xy8AtaVvW4IfKCw9skN0C1m9qhD-_ZCZlvCMUGF73Z";
		Item reqitem=itemlist(FakerUtils.generateName(), FakerUtils.generateDescription(), false);
		Response res=PlaylistApi.post(reqitem, token1);
		assertThat(res.getStatusCode(), equalTo(401));
		Errorroot resitem=res.as(Errorroot.class);
		assertThat(resitem.getError().getStatus(), equalTo(StatusCode.CODE_401.getCode()));
	    assertThat(resitem.getError().getMessage(), equalTo(StatusCode.CODE_401.getmsg()));
			
			
	}
	
	
	
}
