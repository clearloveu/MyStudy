package huawei2021.src;

public class Vm {
    public String getModel() {
        return model;
    }

    String model;
    int cpu;
    int memory;
    int type; //0单节点 1双节点

    int vmId;//虚拟机id
    int serverListIndex; // 服务器List下对应的索引
    double cpuMemoryRate;

    public int getVmId() {
        return vmId;
    }

    public void setVmId(int vmId) {
        this.vmId = vmId;
    }

    public int getServerListIndex() {
        return serverListIndex;
    }

    public void setServerListIndex(int serverListIndex) {
        this.serverListIndex = serverListIndex;
    }

    public Vm(String model, int cpu, int memory, int type) {
        this.model = model;
        this.cpu = cpu;
        this.memory = memory;
        this.type = type;
        cpuMemoryRate = cpu*1.0/memory;
    }

    @Override
    protected Object clone() {
        Vm vm = null;
        vm = new Vm(this.model, this.cpu, this.memory, this.type);
        return vm;
    }

}

