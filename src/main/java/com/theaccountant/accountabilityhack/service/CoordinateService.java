package com.theaccountant.accountabilityhack.service;

import com.theaccountant.accountabilityhack.data.Address;
import com.theaccountant.accountabilityhack.data.Coordinate;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * TODO: localfocus
 */
public final class CoordinateService {

    private static final String GOOGLE_API = "https://maps.googleapis.com/maps/api/geocode/json?address=";

    private static final JSONParser jsonParser = new JSONParser();

    public final Coordinate queryCoordinates(final Address address) {
        final String query = String.format("%s %s %s, %s", address.getStreetname(), address.getStreetNr(),
               address.getZipcode(), address.getCity()).replace(' ', '-');
        final JSONObject json;
        try {
            final URL url = new URL(GOOGLE_API + query);
            final URLConnection conn = url.openConnection();
            final InputStream inputStream = conn.getInputStream();
            json = (JSONObject) jsonParser.parse(new InputStreamReader(inputStream, "UTF-8"));
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
        final String result = (String) json.get("status");
        if (!result.equals("OK")) {
            return null;
        }
        final JSONArray results = (JSONArray) json.get("results");
        final JSONObject geometry = (JSONObject) ((JSONObject) results.get(0)).get("geometry");
        final JSONObject location = (JSONObject) geometry.get("location");
        final double lat = (Double) location.get("lat");
        final double lng = (Double) location.get("lng");
        return Coordinate.builder().latitude(lat).longitude(lng).build();
    }
}
