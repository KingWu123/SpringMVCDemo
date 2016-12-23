package com.demo.data;

import com.demo.data.Spittle;
import com.demo.data.SpittleRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by kingwu on 14/12/2016.
 */
@Component
public class JdbcSpittleRepository implements SpittleRepository {

    @Override
    public List<Spittle> findSpittles(long max, int count) {
        ArrayList<Spittle> spittles = new ArrayList<>();

        for (int i=0; i< count; i ++){
            Spittle spittle = new Spittle("hahaha" + i, new Date());
            spittles.add(spittle);
        }
        return spittles;
    }

    @Override
    public Spittle findOneSpitte() {
        return new Spittle("hahaha11" , new Date());
    }
}
