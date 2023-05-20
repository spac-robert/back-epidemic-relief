package ro.robert.epidemicrelief.service;

import java.text.ParseException;

/**
 * Contains business logic related to Jobs
 */
public interface JobService {

    /**
     * Update products from database
     */
    void jobUpdateLots();

    void jobSubscription() throws ParseException;
}
