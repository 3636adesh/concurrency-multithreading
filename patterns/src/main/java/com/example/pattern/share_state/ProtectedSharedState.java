package com.example.pattern.share_state;

import com.example.pattern.GuardedBy;
import com.example.pattern.ThreadSafe;

@ThreadSafe
public class ProtectedSharedState {

    @GuardedBy("this")
    private Object state;

    @GuardedBy("this")
    private Object state2;

    public synchronized Object getState() {
        return state;
    }

    public synchronized void setState(Object state) {
        this.state = state;
    }

    public synchronized Object getState2() {
        return state2;
    }

    public synchronized void setState2(Object state2) {
        this.state2 = state2;
    }

}
