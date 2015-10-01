package com.example.designsupportlibraryuseing.otto;

import com.squareup.otto.Bus;

/**
 * Created by 晋伟 on 2015/9/22 0022.
 */
public final class BusProvider {
    private static final Bus BUS = new Bus();

    public static Bus getInstance() {
        return BUS;
    }

    private BusProvider() {
        // No instances.
    }
}
