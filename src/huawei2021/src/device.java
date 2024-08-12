package huawei2021.src;

public class device {
    Server buy_ser;

    int num;

    int use_cpu;
    int use_memory;

    int remain_cpu;
    int remain_memory;

    public device(Server buy_ser, int num, int remain_cpu, int remain_memory) {
        this.buy_ser = buy_ser;
        this.num = num;
        this.remain_cpu = remain_cpu;
        this.remain_memory = remain_memory;
    }

    double rate_cpu;
    double rate_memory;
}
