package com.bridgelabz.qa.api_automation;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Rest_Api_Automation_Testing_Test {
	
	String token;
	String userId;
	String playlistID;
	
	@BeforeTest
	
	public void getToken() {
		token = "Bearer BQBzWCFHBz97sHbSVi_arMhFyqu15NIxIYCjP3F6wLOua8kYL6IvVz2wPteYEsTXkTDF_HW59NdPOyzPST7EJpvnf9_brsWLEIsPK3AH7gpF7s2p7m79fWo4jli88sQwy8JNu0lc4_kF8fYkqnByHSY2VrZWR5sUMGN1t_Kx-zH1YKrGLhBmFBQYBAa9vRxunYZ_o64lMJDaEw-5M8fD_uVIVH8aSsVpCpEsSDl_8gDbe8qy12_UIR8a6dFeB7RcdFq4RNNNEONvKZ_DuWywhhMcMdOgRQjt7tHqVG5z";
	}
	
//            **********USERS PROFILE**********            //

	@Test(priority = 1)
	
	public void get_Current_User_Profile() {
		
		Response response = RestAssured.given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/me");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
		userId = response.path("id");
		System.out.println("Current user id is:" + userId);
		Assert.assertEquals("31jfxmebwewcmgvnjepf6h6tobxm", userId);
	}
	
	@Test(priority = 2)
	
public void get_User_Profile() {
		
		Response response = RestAssured.given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/users/"+userId+"");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
		Assert.assertEquals("31jfxmebwewcmgvnjepf6h6tobxm", userId);
	}	
	
//            **********SEARCH**********            //
	
	@Test(priority = 3)
	
public void search_For_Item() {
		
		Response response = RestAssured.given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.queryParam("q", "Ajay Atul")
				.queryParam("type", "track")
				.when()
				.get("https://api.spotify.com/v1/search");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
		Assert.assertEquals(response.getStatusCode(), 200);
	}	
	
//            **********PLAYLISTS**********            //
	
	@Test(priority = 4)
	
public void create_Playlist() {
		
		Response response = RestAssured.given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
			    .body("{\r\n"
			    		+ "  \"name\": \"Hindi Song1\",\r\n"
			    		+ "  \"description\": \"New playlist description\",\r\n"
			    		+ "  \"public\": false\r\n"
			    		+ "}")
				.when()
				.post("https://api.spotify.com/v1/users/"+userId+"/playlists");
		response.prettyPrint();
		response.then().assertThat().statusCode(201);
		playlistID = response.path("id");
		System.out.println("Hindi Songs Playlist Id is:" + playlistID);
	}		
	
	@Test(priority = 5)
	
public void get_Current_User_Playlist() {
		
		Response response = RestAssured.given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/me/playlists");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
		Assert.assertEquals(response.getStatusCode(), 200);
	}	
	
	@Test(priority = 6)
	
public void get_User_Playlists() {
		
		Response response = RestAssured.given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/users/"+userId+"/playlists");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority = 7)
	
public void get_Playlist() {
		
		Response response = RestAssured.given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/playlists/"+playlistID+"");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority = 8)
	
public void add_Items_To_Playlist() {
		
		Response response = RestAssured.given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
			    .queryParam("uris", "spotify:track:73y649QhnXdcm6fRdvfraO,spotify:track:1sL8hnnZaB2Xzm1J66y4Tt,spotify:track:0fRQGNuIp5cdr9TBShw1bN,spotify:track:6Ozx2ngGtXrqznTKhKBlrT")
				.when()
				.post("https://api.spotify.com/v1/playlists/"+playlistID+"/tracks");
		response.prettyPrint();
		response.then().assertThat().statusCode(201);
     }
	
	@Test(priority = 9)
	
public void get_Playlist_Items() {
		
		Response response = RestAssured.given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/playlists/"+playlistID+"/tracks");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority = 10)
	
public void update_Playlist_Items() {
		
		Response response = RestAssured.given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
                .body("{\r\n"
                		+ "  \"range_start\": 1,\r\n"
                		+ "  \"insert_before\": 4,\r\n"
                		+ "  \"range_length\": 2\r\n"
                		+ "}")
				.when()
				.put("https://api.spotify.com/v1/playlists/"+playlistID+"/tracks");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
		Assert.assertEquals(response.getStatusCode(), 200);
     }
	
	@Test(priority = 11)
	
public void change_Playlist_Details() {
		
		Response response = RestAssured.given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
                .body("{\r\n"
                		+ "  \"name\": \"Hindi Songs2\",\r\n"
                		+ "  \"description\": \"Updated playlist description\",\r\n"
                		+ "  \"public\": false\r\n"
                		+ "}")
				.when()
				.put("https://api.spotify.com/v1/playlists/"+playlistID+"");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
		Assert.assertEquals(response.getStatusCode(), 200);
     }
	
	@Test(priority = 12)
	
public void remove_Playlist_Items() {
		
		Response response = RestAssured.given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
                .body("{\r\n"
                		+ " \"tracks\": [\r\n"
                		+ "      { \r\n"
                		+ "      \"uri\": \"spotify:track:73y649QhnXdcm6fRdvfraO\",\r\n"
                		+ "     \"position\":[0]\r\n"
                		+ "      }\r\n"
                		+ "      ]\r\n"
                		+ " }")
				.when()
				.delete("https://api.spotify.com/v1/playlists/"+playlistID+"/tracks");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
		Assert.assertEquals(response.getStatusCode(), 200);
     }
	
	@Test(priority = 13)
	
public void get_Playlist_Cover_Image() {
		
		Response response = RestAssured.given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.header("Authorization", token)
				.when()
				.get("	https://api.spotify.com/v1/playlists/"+playlistID+"/images");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
//            **********TRACKS**********            //
	
	@Test(priority = 14)
	
public void get_Track() {
		
		Response response = RestAssured.given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/tracks/6Ozx2ngGtXrqznTKhKBlrT");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
		Assert.assertEquals(response.getStatusCode(), 200);
	}	
	
	@Test(priority = 15)
	
public void get_Several_Tracks() {
		
		Response response = RestAssured.given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.queryParam("ids", "7Els4IIFYa4P4RxBkZkxdd,7tVqxbTLELBul4nLg08mMn,7sVEoPc5ZUUQerRkgTypUZ")
				.when()
				.get("https://api.spotify.com/v1/tracks");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
		Assert.assertEquals(response.getStatusCode(), 200);
	}	
	
	@Test(priority = 16)
	
public void get_Track_Audio_Feature() {
		
		Response response = RestAssured.given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/audio-features/6Ozx2ngGtXrqznTKhKBlrT");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
		Assert.assertEquals(response.getStatusCode(), 200);
	}	
	
	@Test(priority = 17)
	
public void get_Tracks_Audio_Feature() {
		
		Response response = RestAssured.given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.queryParam("ids", "7Els4IIFYa4P4RxBkZkxdd,7tVqxbTLELBul4nLg08mMn,7sVEoPc5ZUUQerRkgTypUZ")
				.when()
				.get("https://api.spotify.com/v1/audio-features");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
		Assert.assertEquals(response.getStatusCode(), 200);
     }
	
	@Test(priority = 18)
	
public void get_Track_Audio_Analysis() {
		
		Response response = RestAssured.given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/audio-analysis/6Ozx2ngGtXrqznTKhKBlrT");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
		Assert.assertEquals(response.getStatusCode(), 200);
	}	
}