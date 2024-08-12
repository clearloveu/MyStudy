package huawei2021.src;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zg
 * @create 2021-03-20 10:12
 */
public class ServerCluster {

    public double cpuMemoryRateLimit;
    public List<Server> serverListDan;
    public List<Server> serverListShuang;
    public int buyServerNumDan;
    public int buyServerNumShuang;


    public ServerCluster(double cpuMemoryRateLimit) {
        this.cpuMemoryRateLimit = cpuMemoryRateLimit;
        serverListShuang = new ArrayList<>();
        serverListDan = new ArrayList<>();
        clear();
    }

    public void clear(){
        buyServerNumDan = 0;
        buyServerNumShuang = 0;
        for (int i = 0; i < serverListDan.size(); i++) {
            serverListDan.get(i).isSelect = false;
        }
        for (int i = 0; i < serverListShuang.size(); i++) {
            serverListShuang.get(i).isSelect = false;
        }
    }

}
