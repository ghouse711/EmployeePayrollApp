package com.employeepayrollapp.download;

/*
 DownloadToken represents time-bound access to a download.
*/

public class DownloadToken {

    private long createdTime;
    private long expiryMillis;

    public DownloadToken() {

        createdTime = System.currentTimeMillis();

        // token valid for 1 minute
        expiryMillis = 60 * 1000;
    }

    /*
     Checks whether the token is still valid.
    */
    public boolean isExpired() {

        long now = System.currentTimeMillis();

        return (now - createdTime) > expiryMillis;
    }
}