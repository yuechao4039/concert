package com.hll.concert.common;

import java.util.ArrayList;
import java.util.List;

public class RowsResp<T> extends BaseResp {

    private List<T> rows = new ArrayList<T>();


    public boolean add(T t) {
        return rows.add(t);
    }
}
