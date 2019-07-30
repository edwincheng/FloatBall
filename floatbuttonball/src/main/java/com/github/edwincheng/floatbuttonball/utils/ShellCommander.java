package com.github.edwincheng.floatbuttonball.utils;

import java.io.OutputStream;

/**
 * @ file name:    :
 * @ author        : edwincheng
 * @ e-mail        : zwp_edwincheng@163.com
 * @ date          : 19-7-29 17:32
 * @ description   : 命令行执行工具
 * @ modify author :
 * @ modify date   :
 */
public class ShellCommander {
    private OutputStream os;

    public final void exec(String cmd){
        try {
            if (os == null){
                os = Runtime.getRuntime().exec("su").getOutputStream();
            }
            os.write(cmd.getBytes());
            os.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 模拟按键
     * @param keyCode
     */
    public final void simulateKey(int keyCode) {
        exec("input keyevent " + keyCode + "\n");
    }
}
