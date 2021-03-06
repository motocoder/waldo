package com.waldo.waldointerview.data;

import java.util.List;

/**
 * Created by useruser on 9/10/18.
 */

public class Album {

    private final String id;
    private final String name;
    private final List<Record> records;

    /**
     *
     * @param id - id of the album
     * @param name - name of the album
     * @param records - list of records for the album
     */
    public Album(
        final String id,
        final String name,
        final List<Record> records
    ) {

        this.id = id;
        this.name = name;
        this.records = records;

    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Record> getRecords() {
        return records;
    }
}
