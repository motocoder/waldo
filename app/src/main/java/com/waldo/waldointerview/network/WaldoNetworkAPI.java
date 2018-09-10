package com.waldo.waldointerview.network;

import android.util.Log;

import com.waldo.waldointerview.data.Album;
import com.waldo.waldointerview.data.Record;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;

/**
 * Created by useruser on 9/10/18.
 */

public class WaldoNetworkAPI {

    public static final MediaType JSON
        = MediaType.parse("application/json");

    public Album getImageData(final String cookie) throws WaldoNetworkException {

        try {

            final RequestBody body =
                RequestBody.create(
                    JSON,
                    "{\"query\":\"query {\\n  album(id: \\\"dTRydwXhGQgthi1r2cKFmg\\\") {\\n    id,\\n    name,\\n    photos(slice: { limit: 10, offset: 0 }) {\\n      count\\n      records {\\n        urls {\\n          size_code\\n          url\\n          width\\n          height\\n          quality\\n          mime\\n        }\\n      }\\n    }\\n  }\\n}\",\"variables\":null}"
                );

            final OkHttpClient client = new OkHttpClient();

            final Request request =
                new Request.Builder()
                    .url("https://core-graphql.dev.waldo.photos/gql?")
                    .addHeader("Cookie", cookie)
                    .post(body)
                    .build();

            final Response response = client.newCall(request).execute();

            if(response.code() == 200) {

                final JSONObject jsonOb = new JSONObject(response.body().string());

                final JSONObject dataNode = jsonOb.getJSONObject("data");

                final JSONObject albumNode = dataNode.getJSONObject("album");

                final JSONArray records = albumNode.getJSONObject("photos").getJSONArray("records");

                final List<Record> recordData = new ArrayList<>();

                for(int recordIndex = 0; recordIndex < records.length(); recordIndex++) {

                    final JSONArray urls = records.getJSONObject(recordIndex).getJSONArray("urls");

                    for(int urlIndex = 0; urlIndex < urls.length(); urlIndex++) {

                        final JSONObject urlOb = urls.getJSONObject(urlIndex);

                        final String sizeCode = urlOb.getString("size_code");

                        if(sizeCode.equals("large")) {
                            recordData.add(new Record(urlOb.getString("url")));
                        }

                    }

                }

                return new Album(
                    albumNode.getString("id"),
                    albumNode.getString("name"),
                    recordData
                );

            }
            else {
                throw new WaldoNetworkException("invalid response code " + response.code());
            }

        }
        catch (Exception e) {
            throw new WaldoNetworkException(e);
        }

    }

}
