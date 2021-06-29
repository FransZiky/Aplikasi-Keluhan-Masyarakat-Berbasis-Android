

package com.example.lapcov_19.ui.logger;

public interface LogNode {
    void println(int priority, String tag, String msg, Throwable tr);
}
