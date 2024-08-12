package huawei2021.src;

import java.util.ArrayList;
import java.util.List;

public class Server {
    String model;
    int cpu;
    int memory;
    int cost_hardware;
    int cost_day;

    int remain_cpu;
    int remain_memory;

    boolean isSelect;

    int id; // 服务器id
    double cpuMemoryRate;

    List<Vm> vmList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }




    public Server(String model, int cpu, int memory, int cost_hardware, int cost_day) {
        this.model = model;
        this.cpu = cpu;
        this.memory = memory;
        this.cost_hardware = cost_hardware;
        this.cost_day = cost_day;
        remain_cpu = cpu;
        remain_memory = memory;
        vmList = new ArrayList<>();
        cpuMemoryRate = cpu*1.0/memory;
        isSelect = false;
    }

    @Override
    protected Object clone() {
        Server server = null;
        server = new Server(this.model, this.cpu, this.memory, this.cost_hardware, this.cost_day);
        return server;
    }
}
