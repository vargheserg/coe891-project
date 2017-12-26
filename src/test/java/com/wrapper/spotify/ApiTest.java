package com.wrapper.spotify;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.neovisionaries.i18n.CountryCode;
import com.neovisionaries.i18n.LanguageCode;
import com.wrapper.spotify.UtilProtos.Url.Scheme;
import com.wrapper.spotify.model_objects.AlbumType;
import com.wrapper.spotify.model_objects.PlaylistTrackPosition;
import com.wrapper.spotify.requests.Request;
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.wrapper.spotify.Assertions.*;
import static org.junit.Assert.assertEquals;

public class ApiTest {

  SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");

  @Test
  public void shouldCreateAGetAlbumUrl() {
    Api api = Api.DEFAULT_API;
    Request request = api.getAlbum("5oEljuMoe9MXH6tBIPbd5e").build();
    assertEquals("https://api.spotify.com:443/v1/albums/5oEljuMoe9MXH6tBIPbd5e", request.toString(false));
  }

  @Test
  public void shouldCreateAGetAudioFeaturesUrl() {
    Api api = Api.DEFAULT_API;
    Request request = api.getAudioFeature("1hmNbafW4sAPNaGc7LeXAZ").build();
    assertEquals("https://api.spotify.com:443/v1/audio-features/1hmNbafW4sAPNaGc7LeXAZ", request.toString(false));
  }

  @Test
  public void shouldCreateAGetTracksForAlbumUrl() {
    Api api = Api.DEFAULT_API;
    Request request = api.getTracksForAlbum("5oEljuMoe9MXH6tBIPbd5e").build();
    String test = request.toString();
    assertEquals(
            "https://api.spotify.com:443/v1/albums/5oEljuMoe9MXH6tBIPbd5e/tracks",
            request.toString(false)
    );
  }

  @Test
  public void shouldReplacePlaylistsTracks() {
    Api api = Api.DEFAULT_API;
    Request request = api.replacePlaylistsTracks("userId", "5oEljuMoe9MXH6tBIPbd5e", new String[]{}).build();
    assertEquals(
            "https://api.spotify.com:443/v1/users/userId/playlists/5oEljuMoe9MXH6tBIPbd5e/tracks",
            request.toString(false)
    );
  }

  @Test
  public void shouldUnfollowPlaylist() {
    Api api = Api.DEFAULT_API;
    Request request = api.unfollowPlaylist(
            "userId", "5oEljuMoe9MXH6tBIPbd5e"
    ).build();
    assertEquals(
            "https://api.spotify.com:443/v1/users/userId/playlists/5oEljuMoe9MXH6tBIPbd5e/followers",
            request.toString(false)
    );
  }

  @Test
  public void shouldCreateAGetRecentlyPlayedTracksUrl() {
    Api api = Api.DEFAULT_API;
    Request request = api.getRecentlyPlayedTracks().build();
    assertEquals("https://api.spotify.com:443/v1/me/player/recently-played", request.toString(false));
  }

  @Test
  public void shouldCreateAGetArtistUrl() {
    Api api = Api.DEFAULT_API;
    Request request = api.getArtist("5rSXSAkZ67PYJSvpUpkOr7").build();
    assertEquals("https://api.spotify.com:443/v1/artists/5rSXSAkZ67PYJSvpUpkOr7", request.toString(false));
  }

  @Test
  public void shouldCreateAGetTrackUrl() {
    Api api = Api.DEFAULT_API;
    Request request = api.getTrack("6hDH3YWFdcUNQjubYztIsG").build();
    assertEquals("https://api.spotify.com:443/v1/tracks/6hDH3YWFdcUNQjubYztIsG", request.toString(false));
  }

  @Test
  public void shouldCreateAGetAlbumsUrl() {
    Api api = Api.DEFAULT_API;
    Request request = api.getAlbums("6hDH3YWFdcUNQjubYztIsG", "2IA4WEsWAYpV9eKkwR2UYv").build();
    assertEquals("https://api.spotify.com:443/v1/albums", request.toString(false));
    assertHasParameter(request.toUrl(), "ids", "6hDH3YWFdcUNQjubYztIsG,2IA4WEsWAYpV9eKkwR2UYv");
  }

  @Test
  public void shouldCreateAGetAlbumsUrlFromAList() {
    Api api = Api.DEFAULT_API;
    Request request = api.getAlbums(new String[]{"6hDH3YWFdcUNQjubYztIsG", "2IA4WEsWAYpV9eKkwR2UYv"}).build();
    assertEquals("https://api.spotify.com:443/v1/albums", request.toString(false));
    assertHasParameter(request.toUrl(), "ids", "6hDH3YWFdcUNQjubYztIsG,2IA4WEsWAYpV9eKkwR2UYv");
  }

  @Test
  public void shouldCreateAGetTracksUrl() {
    Api api = Api.DEFAULT_API;
    Request request = api.getTracks("6hDH3YWFdcUNQjubYztIsG", "2IA4WEsWAYpV9eKkwR2UYv").build();
    assertEquals("https://api.spotify.com:443/v1/tracks", request.toString(false));
    assertHasParameter(request.toUrl(), "ids", "6hDH3YWFdcUNQjubYztIsG,2IA4WEsWAYpV9eKkwR2UYv");
  }

  @Test
  public void shouldCreateAGetTracksUrlFromList() {
    Api api = Api.DEFAULT_API;
    Request request = api.getTracks(new String[]{"6hDH3YWFdcUNQjubYztIsG", "2IA4WEsWAYpV9eKkwR2UYv"}).build();
    assertEquals("https://api.spotify.com:443/v1/tracks", request.toString(false));
    assertHasParameter(request.toUrl(), "ids", "6hDH3YWFdcUNQjubYztIsG,2IA4WEsWAYpV9eKkwR2UYv");
  }

  @Test
  public void shouldCreateAGetRecommendationsUrl() {
    Api api = Api.DEFAULT_API;
    Request request = api.getRecommendations().build();
    assertEquals("https://api.spotify.com:443/v1/recommendations", request.toString(false));
  }

  @Test
  public void shouldCreateAGetRecommendationsUrlFromList() {
    Api api = Api.DEFAULT_API;
    Request request = api.getRecommendations().build();
    assertEquals("https://api.spotify.com:443/v1/recommendations", request.toString(false));
  }

  @Test
  public void shouldCreateAUrlForArtistsAlbum() {
    Api api = Api.DEFAULT_API;
    Request request = api.getAlbumsForArtist("4AK6F7OLvEQ5QYCBNiQWHq").build();
    assertEquals("https://api.spotify.com:443/v1/artists/4AK6F7OLvEQ5QYCBNiQWHq/albums", request.toString(false));
  }

  @Test
  public void shouldHaveMultipleAlbumTypeParametersInArtistsAlbumUrl() {
    Api api = Api.DEFAULT_API;
    Request request = api.getAlbumsForArtist("4AK6F7OLvEQ5QYCBNiQWHq")
            .album_type(AlbumType.ALBUM, AlbumType.SINGLE)
            .market(CountryCode.SE)
            .build();

    assertEquals("https://api.spotify.com:443/v1/artists/4AK6F7OLvEQ5QYCBNiQWHq/albums", request.toString(false));
    assertHasParameter(request.toUrl(), "album_type", "ALBUM,SINGLE");
    assertHasParameter(request.toUrl(), "market", "SE");
  }

  @Test
  public void shouldHaveSingleAlbumTypeParametersInArtistsAlbumUrl() {
    Api api = Api.DEFAULT_API;
    Request request = api.getAlbumsForArtist("4AK6F7OLvEQ5QYCBNiQWHq").album_type(AlbumType.SINGLE).build();
    assertEquals("https://api.spotify.com:443/v1/artists/4AK6F7OLvEQ5QYCBNiQWHq/albums", request.toString(false));
    assertHasParameter(request.toUrl(), "album_type", "SINGLE");
  }

  @Test
  public void shouldFailIfAlbumTypeParametersIsInArtistsAlbumUrl() {
    Api api = Api.DEFAULT_API;
    Request request = api.getAlbumsForArtist("4AK6F7OLvEQ5QYCBNiQWHq").album_type(AlbumType.SINGLE).build();
    assertEquals("https://api.spotify.com:443/v1/artists/4AK6F7OLvEQ5QYCBNiQWHq/albums", request.toString(false));
    assertHasParameter(request.toUrl(), "album_type", "SINGLE");
  }

  @Test
  public void shouldHaveLimitParameterInArtistsAlbumUrl() {
    Api api = Api.DEFAULT_API;
    Request request = api.getAlbumsForArtist("4AK6F7OLvEQ5QYCBNiQWHq").limit(2).build();
    assertEquals("https://api.spotify.com:443/v1/artists/4AK6F7OLvEQ5QYCBNiQWHq/albums", request.toString(false));
    assertHasParameter(request.toUrl(), "limit", "2");
  }

  @Test
  public void shouldHaveOffsetParameterInArtistsAlbumUrl() {
    Api api = Api.DEFAULT_API;
    Request request = api.getAlbumsForArtist("4AK6F7OLvEQ5QYCBNiQWHq").offset(5).build();
    assertEquals("https://api.spotify.com:443/v1/artists/4AK6F7OLvEQ5QYCBNiQWHq/albums", request.toString(false));
    assertHasParameter(request.toUrl(), "offset", "5");
  }

  @Test
  public void shouldHaveSeveralQueryParametersAtTheSameTimeInArtistsAlbumUrl() {
    Api api = Api.DEFAULT_API;
    Request request = api.getAlbumsForArtist("4AK6F7OLvEQ5QYCBNiQWHq").album_type(AlbumType.SINGLE).limit(2).offset(5).build();
    assertEquals("https://api.spotify.com:443/v1/artists/4AK6F7OLvEQ5QYCBNiQWHq/albums", request.toString(false));
    assertHasParameter(request.toUrl(), "offset", "5");
    assertHasParameter(request.toUrl(), "limit", "2");
    assertHasParameter(request.toUrl(), "album_type", "SINGLE");
  }

  @Test
  public void shouldCreateAGetArtistsUrl() {
    Api api = Api.DEFAULT_API;
    Request request = api.getArtists("4AK6F7OLvEQ5QYCBNiQWHq", "6rEzedK7cKWjeQWdAYvWVG").build();
    assertEquals("https://api.spotify.com:443/v1/artists", request.toString(false));
    assertHasParameter(request.toUrl(), "ids", "4AK6F7OLvEQ5QYCBNiQWHq,6rEzedK7cKWjeQWdAYvWVG");
  }

  @Test
  public void shouldCreateSearchUrl() {
    Api api = Api.DEFAULT_API;
    Request request = api.searchTracks("moulat swalf").build();
    assertEquals("https://api.spotify.com:443/v1/search", request.toString(false));
    assertHasParameter(request.toUrl(), "q", "moulat swalf");
    assertHasParameter(request.toUrl(), "type", "track");
  }

  @Test
  public void shouldCreateSearchUrlForAlbum() {
    Api api = Api.DEFAULT_API;
    Request request = api.searchAlbums("meeep").market("from_token").build();
    assertEquals("https://api.spotify.com:443/v1/search", request.toString(false));
    assertHasParameter(request.toUrl(), "q", "meeep");
    assertHasParameter(request.toUrl(), "type", "album");
    assertHasParameter(request.toUrl(), "market", "from_token");
  }

  @Test
  public void shouldCreateSearchUrlForArtist() {
    Api api = Api.DEFAULT_API;
    Request request = api.searchArtists("meeep").market("GB").build();
    assertEquals("https://api.spotify.com:443/v1/search", request.toString(false));
    assertHasParameter(request.toUrl(), "q", "meeep");
    assertHasParameter(request.toUrl(), "type", "artist");
    assertHasParameter(request.toUrl(), "market", "GB");
  }

  @Test
  public void shouldCreateSearchUrlWithLimitParameter() {
    Api api = Api.DEFAULT_API;
    Request request = api.searchTracks("moulat swalf").limit(2).market("SE").build();
    assertEquals("https://api.spotify.com:443/v1/search", request.toString(false));
    assertHasParameter(request.toUrl(), "q", "moulat swalf");
    assertHasParameter(request.toUrl(), "limit", "2");
    assertHasParameter(request.toUrl(), "type", "track");
    assertHasParameter(request.toUrl(), "market", "SE");
  }

  @Test
  public void shouldCreateSearchUrlWithOffsetParameter() {
    Api api = Api.DEFAULT_API;
    Request request = api.searchTracks("moulat swalf").offset(2).build();
    assertEquals("https://api.spotify.com:443/v1/search", request.toString(false));
    assertHasParameter(request.toUrl(), "q", "moulat swalf");
    assertHasParameter(request.toUrl(), "offset", "2");
    assertHasParameter(request.toUrl(), "type", "track");
  }

  @Test
  public void shouldModifySchemeInUrl() {
    Api api = Api.builder().scheme(Scheme.HTTP).build();
    Request request = api.getAlbum("5oEljuMoe9MXH6tBIPbd5e").build();
    assertEquals("http://api.spotify.com:443/v1/albums/5oEljuMoe9MXH6tBIPbd5e", request.toString(false));
  }

  @Test
  public void shouldModifyPortInUrl() {
    Api api = Api.builder().port(8080).build();
    Request request = api.getAlbum("5oEljuMoe9MXH6tBIPbd5e").build();
    assertEquals("https://api.spotify.com:8080/v1/albums/5oEljuMoe9MXH6tBIPbd5e", request.toString(false));
  }

  @Test
  public void shouldModifyHostInUrl() {
    Api api = Api.builder().host("www.wrapper.se").build();
    Request request = api.getAlbum("5oEljuMoe9MXH6tBIPbd5e").build();
    assertEquals("https://www.wrapper.se:443/v1/albums/5oEljuMoe9MXH6tBIPbd5e", request.toString(false));
  }

  @Test
  public void shouldCreateTopTracksUrl() {
    Api api = Api.DEFAULT_API;
    Request request = api.getTopTracksForArtist("0LcJLqbBmaGUft1e9Mm8HV", CountryCode.GB).build();
    assertEquals("https://api.spotify.com:443/v1/artists/0LcJLqbBmaGUft1e9Mm8HV/toptracks", request.toString(false));
    assertHasParameter(request.toUrl(), "country", "GB");
  }

  @Test
  public void shouldCreateUserProfileUrl() {
    Api api = Api.DEFAULT_API;
    Request request = api.getUser("wizzler").build();
    assertEquals("https://api.spotify.com:443/v1/users/wizzler", request.toString(false));
  }

  @Test
  public void shouldCreateUrlForListingAUsersPlaylists() throws Exception {
    final String accessToken = "myVeryLongAccessToken";
    final Api api = Api.builder().accessToken(accessToken).build();

    final Request request = api.getPlaylistsForUser("wizzler").build();

    assertEquals("https://api.spotify.com:443/v1/users/wizzler/playlists", request.toString(false));
    assertHasHeader(request.toUrl(), "Authorization", "Bearer " + accessToken);
  }

  @Test
  public void shouldCreateRequestForTokensUrl() {
    final String clientId = "myClientId";
    final String clientSecret = "myClientSecret";
    final String redirectURI = "myRedirectUri";
    final String code = "returnedCode";

    final Api api = Api.builder()
            .clientId(clientId)
            .clientSecret(clientSecret)
            .redirectURI(redirectURI)
            .build();

    final Request request = api.authorizationCodeGrant(code).build();

    assertEquals("https://accounts.spotify.com:443/api/token", request.toString(false));
    assertHasBodyParameter(request.toUrl(), "grant_type", "authorization_code");
    assertHasBodyParameter(request.toUrl(), "code", code);
    assertHasBodyParameter(request.toUrl(), "redirect_uri", redirectURI);

    final String idSecret = clientId + ":" + clientSecret;
    assertHasHeader(request.toUrl(), "Authorization", "Basic " + new String(Base64.encodeBase64(idSecret.getBytes())));
  }

  @Test
  public void shouldCreateRefreshAccessTokenUrl() {
    final String clientId = "myClientId";
    final String clientSecret = "myClientSecret";
    final String refreshToken = "myRefreshToken";

    final Api api = Api
            .builder()
            .clientId(clientId)
            .clientSecret(clientSecret)
            .refreshToken(refreshToken)
            .build();

    final Request request = api.refreshAccessToken().build();

    assertEquals("https://accounts.spotify.com:443/api/token", request.toString(false));
    assertHasBodyParameter(request.toUrl(), "grant_type", "refresh_token");
    assertHasBodyParameter(request.toUrl(), "refresh_token", refreshToken);

    final String idSecret = clientId + ":" + clientSecret;
    assertHasHeader(request.toUrl(), "Authorization", "Basic " + new String(Base64.encodeBase64(idSecret.getBytes())));
  }

  @Test
  public void shouldCreatePlaylistLookupUrl() {
    final String accessToken = "myVeryLongAccessToken";
    final Api api = Api.builder().accessToken(accessToken).build();

    final String playlistId = "3ktAYNcRHpazJ9qecm3ptn";
    final String userId = "thelinmichael";

    final Request request = api.getPlaylist(userId, playlistId).build();

    assertEquals("https://api.spotify.com:443/v1/users/" + userId + "/playlists/" + playlistId, request.toString(false));
    assertHasHeader(request.toUrl(), "Authorization", "Bearer " + accessToken);
  }

  @Test
  public void shouldCreateCurrentUserLookupUrl() {
    final String accessToken = "myVeryLongAccessToken";

    final Api api = Api.builder().accessToken(accessToken).build();

    final Request request = api.getMe().build();

    assertEquals("https://api.spotify.com:443/v1/me", request.toString(false));
    assertHasHeader(request.toUrl(), "Authorization", "Bearer " + accessToken);
  }

  @Test
  public void shouldCreateCreatePlaylistUrl() {
    final String accessToken = "myVeryLongAccessToken";
    final Api api = Api.builder().accessToken(accessToken).build();

    final String myUsername = "thelinmichael";
    final String title = "The greatest playlist ever";
    final boolean publicAccess = true;

    final Request request = api.createPlaylist(myUsername, title).publicAccess(publicAccess).build();

    assertEquals("https://api.spotify.com:443/v1/users/thelinmichael/playlists", request.toString(false));
    assertHasHeader(request.toUrl(), "Authorization", "Bearer " + accessToken);
    assertHasHeader(request.toUrl(), "Content-Type", "application/json");
    assertHasJsonBody(request.toUrl(), "{\"name\":\"The greatest playlist ever\",\"public\":\"true\"}");
    assertHasHeader(request.toUrl(), "Authorization", "Bearer " + accessToken);
  }

  @Test
  public void shouldCreateAddTrackToPlaylistUrl() {
    final String accessToken = "myVeryLongAccessToken";
    final Api api = Api.builder().accessToken(accessToken).build();

    final String myUsername = "thelinmichael";
    final String myPlaylistId = "5ieJqeLJjjI8iJWaxeBLuK";
    final String[] tracksToAdd = {"spotify:track:4BYGxv4rxSNcTgT3DsFB9o", "spotify:tracks:0BG2iE6McPhmAEKIhfqy1X"};
    final int insertIndex = 3;

    final Request request = api.addTracksToPlaylist(myUsername, myPlaylistId, tracksToAdd).position(insertIndex).build();

    assertEquals("https://api.spotify.com:443/v1/users/thelinmichael/playlists/" + myPlaylistId + "/tracks", request.toString(false));
    assertHasHeader(request.toUrl(), "Authorization", "Bearer " + accessToken);
    assertHasHeader(request.toUrl(), "Content-Type", "application/json");
    assertHasJsonBody(request.toUrl(), "[\"spotify:track:4BYGxv4rxSNcTgT3DsFB9o\",\"spotify:tracks:0BG2iE6McPhmAEKIhfqy1X\"]");
    assertHasParameter(request.toUrl(), "position", String.valueOf(insertIndex));
    assertHasHeader(request.toUrl(), "Authorization", "Bearer " + accessToken);
  }

  @Test
  public void shouldCreateRemoveTrackFromPlaylistUrl() {
    final String accessToken = "myVeryLongAccessToken";
    final Api api = Api.builder().accessToken(accessToken).build();

    final String myUsername = "thelinmichael";
    final String myPlaylistId = "5ieJqeLJjjI8iJWaxeBLuK";
    final String snapshotId = "JbtmHBDBAYu3/bt8BOXKjzKx3i0b6LCa/wVjyl6qQ2Yf6nFXkbmzuEa+ZI/U1yF+";
    final String track1Uri = "spotify:track:4BYGxv4rxSNcTgT3DsFB9o";
    final String track2Uri = "spotify:track:0BG2iE6McPhmAEKIhfqy1X";
    final int track2Position = 5;
    PlaylistTrackPosition playlistTrackPosition1 = new PlaylistTrackPosition(track1Uri);
    PlaylistTrackPosition playlistTrackPosition2 = new PlaylistTrackPosition(track2Uri, new int[]{track2Position});
    final PlaylistTrackPosition[] tracksToRemove = {playlistTrackPosition1, playlistTrackPosition2};

    final String expectedJsonBody = String.format("{\"tracks\":[{\"uri\":\"%s\"},{\"uri\":\"%s\",\"positions\":[%s]}],\"snapshot_id\":\"%s\"}",
            track1Uri, track2Uri, String.valueOf(track2Position), snapshotId);

    final Request request = api.removeTrackFromPlaylist(myUsername, myPlaylistId, tracksToRemove).snapshotId(snapshotId).build();

    assertEquals("https://api.spotify.com:443/v1/users/thelinmichael/playlists/" + myPlaylistId + "/tracks", request.toString(false));
    assertHasHeader(request.toUrl(), "Authorization", "Bearer " + accessToken);
    assertHasJsonBody(request.toUrl(), expectedJsonBody);
  }

  @Test
  public void shouldCreateReorderTracksInPlaylistUrl() {
    final String accessToken = "myVeryLongAccessToken";
    final Api api = Api.builder().accessToken(accessToken).build();

    final String myUsername = "thelinmichael";
    final String myPlaylistId = "5ieJqeLJjjI8iJWaxeBLuK";
    final String snapshotId = "JbtmHBDBAYu3/bt8BOXKjzKx3i0b6LCa/wVjyl6qQ2Yf6nFXkbmzuEa+ZI/U1yF+";
    final int rangeStart = 10;
    final int rangeLength = 2;
    final int insertBefore = 5;

    final String expectedJsonBody = String.format("{\"range_start\":%s,\"insert_before\":%s,\"range_length\":%s,\"snapshot_id\":\"%s\"}",
            String.valueOf(rangeStart), String.valueOf(insertBefore), String.valueOf(rangeLength), snapshotId);

    final Request request = api.reorderTracksInPlaylist(myUsername, myPlaylistId, rangeStart, insertBefore).rangeLength(rangeLength).snapshotId(snapshotId).build();

    assertEquals("https://api.spotify.com:443/v1/users/thelinmichael/playlists/" + myPlaylistId + "/tracks", request.toString(false));
    assertHasHeader(request.toUrl(), "Authorization", "Bearer " + accessToken);
    assertHasJsonBody(request.toUrl(), expectedJsonBody);
  }

  @Test
  public void shouldCreateChangePlaylistDetailsUrl() {
    final String accessToken = "myVeryLongAccessToken";
    final Api api = Api.builder().accessToken(accessToken).build();

    final String myUsername = "thelinmichael";
    final String myPlaylistId = "5ieJqeLJjjI8iJWaxeBLuK";

    final boolean isPublic = false;
    final String name = "Testing name change";

    final Request request = api.changePlaylistDetails(myUsername, myPlaylistId)
            .publicAccess(isPublic)
            .name(name)
            .build();

    assertEquals("https://api.spotify.com:443/v1/users/thelinmichael/playlists/" + myPlaylistId,
            request.toString(false));
    assertHasHeader(request.toUrl(), "Authorization", "Bearer " + accessToken);
    assertHasHeader(request.toUrl(), "Content-Type", "application/json");

    JsonObject jsonBody = new JsonParser().parse(request.toUrl().getJsonBody()).getAsJsonObject();
    assertEquals(name, jsonBody.get("name").getAsString());
    assertEquals(isPublic, jsonBody.get("public").getAsBoolean());

    assertHasHeader(request.toUrl(), "Authorization", "Bearer " + accessToken);
  }

  @Test
  public void shouldCreateClientCredentialsGrantUrl() {
    final String clientId = "myClientId";
    final String clientSecret = "myClientSecret";

    final Api api = Api.builder()
            .clientId(clientId)
            .clientSecret(clientSecret)
            .build();

    final String[] scopes = {"some-scope", "some-other-scope"};

    final Request request = api.clientCredentialsGrant().scopes(scopes).build();

    assertEquals("https://accounts.spotify.com:443/api/token", request.toString(false));

    assertHasBodyParameter(request.toUrl(), "grant_type", "client_credentials");
    assertHasBodyParameter(request.toUrl(), "scope", "some-scope some-other-scope");

    final String idSecret = clientId + ":" + clientSecret;
    assertHasHeader(request.toUrl(), "Authorization", "Basic " + new String(Base64.encodeBase64(idSecret.getBytes())));
  }

  @Test
  public void shouldCreateAGetPlaylistTracksURL() {
    final String accessToken = "myAccessToken";
    final String userId = "thelinmichael";
    final String playlistId = "5ieJqeLJjjI8iJWaxeBLuK";

    final Api api = Api.builder()
            .accessToken(accessToken)
            .build();

    final Request request = api
            .getPlaylistTracks(userId, playlistId)
            .fields("items")
            .limit(20)
            .offset(1)
            .build();

    assertEquals("https://api.spotify.com:443/v1/users/" + userId + "/playlists/" + playlistId + "/tracks", request.toString(false));
    assertHasParameter(request.toUrl(), "fields", "items");
    assertHasParameter(request.toUrl(), "limit", "20");
    assertHasParameter(request.toUrl(), "offset", "1");
    assertHasHeader(request.toUrl(), "Authorization", "Bearer " + accessToken);
  }

  @Test
  public void shouldCreateRelatedArtistsURL() {
    final Api api = Api.DEFAULT_API;

    final String artistId = "0qeei9KQnptjwb8MgkqEoy";

    final Request request = api.getArtistRelatedArtists(artistId).build();

    assertEquals("https://api.spotify.com:443/v1/artists/" + artistId + "/related-artists", request.toString(false));
  }

  @Test
  public void shouldCreateAuthorizeURL() {
    final String redirectURI = "http://www.michaelthelin.se/test-callback";
    final String clientId = "fcecfc79122e4cd299473677a17cbd4d";

    final Api api = Api.builder()
            .clientId(clientId)
            .redirectURI(redirectURI)
            .build();

    final String[] scopes = {"user-read-private", "user-read-email"};
    final String state = "someExpectedStateString";

    String authorizeUrlString = UrlUtil.urlToString(api.createAuthorizeURL(scopes, state));
    assertEquals("https://accounts.spotify.com:443/authorize?client_id=fcecfc79122e4cd299473677a17cbd4d&response_type=code&redirect_uri=http%3A%2F%2Fwww.michaelthelin.se%2Ftest-callback&scope=user-read-private+user-read-email&state=someExpectedStateString", authorizeUrlString);
  }

  @Test
  public void shouldCreateAuthorizeUrlWithOptionalParameters() {
    final String redirectURI = "http://www.michaelthelin.se/test-callback";
    final String clientId = "fcecfc79122e4cd299473677a17cbd4d";

    final Api api = Api.builder()
            .clientId(clientId)
            .redirectURI(redirectURI)
            .build();

    final String[] scopes = {"user-read-private", "user-read-email"};
    final String state = "someExpectedStateString";

    String authorizeUrlString = api.createAuthorizeURL(scopes)
            .state(state)
            .build()
            .toString();

    assertEquals("https://accounts.spotify.com:443/authorize?client_id=fcecfc79122e4cd299473677a17cbd4d&response_type=code&redirect_uri=http%3A%2F%2Fwww.michaelthelin.se%2Ftest-callback&scope=user-read-private+user-read-email&state=someExpectedStateString", authorizeUrlString);
  }

  @Test
  public void shouldCreateAuthorizeUrlWithShowDialog() {
    final String redirectURI = "http://www.michaelthelin.se/test-callback";
    final String clientId = "fcecfc79122e4cd299473677a17cbd4d";

    final Api api = Api.builder()
            .clientId(clientId)
            .redirectURI(redirectURI)
            .build();

    final String[] scopes = {"user-read-private", "user-read-email"};
    final String state = "someExpectedStateString";

    String authorizeURLString = UrlUtil.urlToString(api.createAuthorizeURL(scopes, state, true));
    assertEquals("https://accounts.spotify.com:443/authorize?client_id=fcecfc79122e4cd299473677a17cbd4d&response_type=code&redirect_uri=http%3A%2F%2Fwww.michaelthelin.se%2Ftest-callback&scope=user-read-private+user-read-email&state=someExpectedStateString&show_dialog=true", authorizeURLString);
  }

  @Test
  public void shouldCreateGetMyTracksURL() {
    final String accessToken = "myAccessToken";

    final Api api = Api.builder()
            .accessToken(accessToken)
            .build();

    final Request request = api
            .getMySavedTracks()
            .limit(5)
            .offset(1)
            .build();

    assertEquals("https://api.spotify.com:443/v1/me/tracks", request.toString(false));
    assertHasParameter(request.toUrl(), "limit", "5");
    assertHasParameter(request.toUrl(), "offset", "1");
    assertHasHeader(request.toUrl(), "Authorization", "Bearer " + accessToken);
  }

  @Test
  public void shouldCreatePutTracksURL() {
    final String accessToken = "myAccessToken";

    final Api api = Api.builder()
            .accessToken(accessToken)
            .build();

    final Request request = api
            .addToMySavedTracks(new String[]{"test", "test2"})
            .build();

    assertEquals("https://api.spotify.com:443/v1/me/tracks", request.toString(false));
    assertHasHeader(request.toUrl(), "Authorization", "Bearer " + accessToken);
  }

  @Test
  public void shouldCreateRemoveTracksURL() {
    final String accessToken = "myAccessToken";

    final Api api = Api.builder()
            .accessToken(accessToken)
            .build();

    final Request request = api
            .removeFromMySavedTracks(new String[]{"test", "test2"})
            .build();

    assertEquals("https://api.spotify.com:443/v1/me/tracks", request.toString(false));
    assertHasHeader(request.toUrl(), "Authorization", "Bearer " + accessToken);
  }

  @Test
  public void shouldCreateGetNewReleasesRequest() {
    final String accessToken = "myAccessToken";

    final Api api = Api.builder()
            .accessToken(accessToken)
            .build();

    final Request request = api.getNewReleases().limit(4).offset(1).country(CountryCode.SE).build();

    assertEquals("https://api.spotify.com:443/v1/browse/new-releases", request.toString(false));
    assertHasHeader(request.toUrl(), "Authorization", "Bearer " + accessToken);
    assertHasParameter(request.toUrl(), "limit", "4");
    assertHasParameter(request.toUrl(), "offset", "1");
    assertHasParameter(request.toUrl(), "country", "SE");
  }

  @Test
  public void shouldCreateFeaturedPlaylistsRequest() {
    final String accessToken = "myAccessToken";

    final Api api = Api.builder()
            .accessToken(accessToken)
            .build();


    Calendar calendar = Calendar.getInstance();
    calendar.set(2014, Calendar.DECEMBER, 22, 13, 59, 30);
    Date timestamp = calendar.getTime();

    final Request request = api
            .getFeaturedPlaylists()
            .country(CountryCode.SE)
            .locale(LanguageCode.es, CountryCode.MX)
            .limit(5)
            .offset(1)
            .timestamp(timestamp)
            .build();

    DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    assertEquals("https://api.spotify.com:443/v1/browse/featured-playlists", request.toString(false));
    assertHasHeader(request.toUrl(), "Authorization", "Bearer " + accessToken);
    assertHasParameter(request.toUrl(), "limit", "5");
    assertHasParameter(request.toUrl(), "offset", "1");
    assertHasParameter(request.toUrl(), "country", "SE");
    assertHasParameter(request.toUrl(), "locale", "es_MX");
    assertHasParameter(request.toUrl(), "timestamp", simpleDateFormat.format(timestamp));
  }

}
