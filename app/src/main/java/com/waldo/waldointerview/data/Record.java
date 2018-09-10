package com.waldo.waldointerview.data;

/**
 * Created by useruser on 9/10/18.
 */

public class Record {

    private final String imageURL;

    /**
     *
     * @param imageURL - url of the image for the album
     */
    public Record(final String imageURL) {
        this.imageURL = imageURL;
    }

    public String getImageURL() {
        return imageURL;
    }

}
