package com.waldo.waldointerview;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.waldo.waldointerview.data.Album;
import com.waldo.waldointerview.network.WaldoNetworkAPI;

import junit.framework.TestCase;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void testGettingData() throws Exception {

        final WaldoNetworkAPI api = new WaldoNetworkAPI();

        final Album album = api.getImageData(
            "__dev.waldo.auth__=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NvdW50X2lkIjoiZjdkMWI5ZWYtYWE0Yi00YjY3LWFhZjQtYzYzZmE1MDYzOWM0Iiwicm9sZXMiOlsiY29uc3VtZXIiXSwiaXNzIjoid2FsZG86Y29yZSIsImdyYW50cyI6WyJhbGJ1bXM6dmlldzpwdWJsaWMiLCJhbGJ1bXM6ZWRpdDpvd25lZCIsImFsYnVtczpjcmVhdGU6cHJpdmF0ZSIsImFsYnVtczp2aWV3OmpvaW5lZCIsImFsYnVtczpkZWxldGU6b3duZWQiLCJhbGJ1bXM6Y3JlYXRlOnB1YmxpYyIsImFsYnVtczpjcmVhdGU6b3duZWQiLCJhbGJ1bXM6dmlldzpvd25lZCJdLCJleHAiOjE1MzkwNDQ1MzksImlhdCI6MTUzNjQ1MjUzOX0.xTQhv0QCkk4FqAdnmIFeezISw2TyKEleRKTJ1bNOtos"
        );

        TestCase.assertNotNull(album.getId());
        TestCase.assertNotNull(album.getName());
        TestCase.assertTrue(album.getRecords().size() > 0);

    }

}
