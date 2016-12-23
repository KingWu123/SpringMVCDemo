package com.demo.data;

import java.util.List;

/**
 * Created by kingwu on 14/12/2016.
 */
public interface SpittleRepository {
    List<Spittle> findSpittles(long max, int count);
    Spittle findOneSpitte();
}
