package com.demo.data;

/**
 * Created by kingwu on 16/12/2016.
 */
public interface SpitterRepository {

    Spitter addSpitter(Spitter spitter);
    Spitter findOne(long id);

}
