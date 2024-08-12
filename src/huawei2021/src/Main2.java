package huawei2021.src;



import java.io.*;
import java.util.*;

/**
 * @author zg
 * @create 2021-03-14 14:13
 */
public class Main2 {
    private static Server[] serverArray;//服务器列表
    private static Vm[] vmArray;//虚拟机列表
    private static List[] allRequestArray;//请求列表，[天数][每一天的请求]


    private static Map<String, Vm> vmNameToVmMap;
    private static Map<Integer, Vm> vmIdToVmMap;


    private static double cpuMemoryRateUpperLimit = 19.5; //19
    private static double cpuMemoryRateLowerLimit = 0.14;//0.14
    private static int midBuyServerNumDan = 0;
    private static int minBuyServerNumDan = 0;
    private static int maxBuyServerNumDan = 0;
    private static int midBuyServerNumShuang = 0;
    private static int minBuyServerNumShuang = 0;
    private static int maxBuyServerNumShuang = 0;


    private static void read1(){
        Scanner scanner = new Scanner(System.in);
        int serverNum = Integer.parseInt(scanner.nextLine().trim());
        serverArray = new Server[serverNum];
        // 读取服务器数据
        for (int i = 0; i < serverNum; i++) {
            String line = scanner.nextLine();
            line = line.substring(1, line.length()-1);
            String[] strings = line.split(",");
            Server thisServer = new Server(strings[0].trim(), Integer.parseInt(strings[1].trim()), Integer.parseInt(strings[2].trim()), Integer.parseInt(strings[3].trim()), Integer.parseInt(strings[4].trim()));
            serverArray[i] = thisServer;
        }
        // 读取虚拟机数据
        int vmNum = Integer.parseInt(scanner.nextLine().trim());
        vmArray = new Vm[vmNum];
        for (int i = 0; i < vmNum; i++) {
            String line = scanner.nextLine();
            line = line.substring(1, line.length()-1);
            String[] strings = line.split(",");
            Vm thisSvM = new Vm(strings[0].trim(), Integer.parseInt(strings[1].trim()), Integer.parseInt(strings[2].trim()), Integer.parseInt(strings[3].trim()));
            vmArray[i] = thisSvM;
        }
        // 读取请求
        int dayNum = Integer.parseInt(scanner.nextLine().trim());
        allRequestArray = new List[dayNum];
        for (int i = 0; i < dayNum; i++) {
            int reqNum = Integer.parseInt(scanner.nextLine().trim());
            List<String> reqOfDay = new ArrayList<>();
            for (int j = 0; j < reqNum; j++) {
                String line = scanner.nextLine();
                reqOfDay.add(line.substring(1, line.length()-1));
            }
            allRequestArray[i] = reqOfDay;
        }
    }


    private static void read2() throws IOException {
        InputStream is = new FileInputStream("C:\\To_be_faster\\IdeaWorkspace\\TestProject1\\src\\huawei2021\\training-1.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        int serverNum = Integer.parseInt(reader.readLine().trim());
        serverArray = new Server[serverNum];
        // 读取服务器数据
        for (int i = 0; i < serverNum; i++) {
            String line = reader.readLine();
            line = line.substring(1, line.length()-1);
            String[] strings = line.split(",");
            Server thisServer = new Server(strings[0].trim(), Integer.parseInt(strings[1].trim()), Integer.parseInt(strings[2].trim()), Integer.parseInt(strings[3].trim()), Integer.parseInt(strings[4].trim()));
            serverArray[i] = thisServer;
        }
        // 读取虚拟机数据
        int vmNum = Integer.parseInt(reader.readLine().trim());
        vmArray = new Vm[vmNum];
        for (int i = 0; i < vmNum; i++) {
            String line = reader.readLine();
            line = line.substring(1, line.length()-1);
            String[] strings = line.split(",");
            Vm thisSvM = new Vm(strings[0].trim(), Integer.parseInt(strings[1].trim()), Integer.parseInt(strings[2].trim()), Integer.parseInt(strings[3].trim()));
            vmArray[i] = thisSvM;
        }
        // 读取请求
        int dayNum = Integer.parseInt(reader.readLine().trim());
        allRequestArray = new List[dayNum];
        for (int i = 0; i < dayNum; i++) {
            int reqNum = Integer.parseInt(reader.readLine().trim());
            List<String> reqOfDay = new ArrayList<>();
            for (int j = 0; j < reqNum; j++) {
                String line = reader.readLine();
                reqOfDay.add(line.substring(1, line.length()-1));
            }
            allRequestArray[i] = reqOfDay;
        }
    }

    private static void deal()  {
        Server midCpuMemoryRateServer = selectServer();
        Server maxCpuMemoryRateServer = selectServerMaxRate();
        Server minCpuMemoryRateServer = selectServerMinRate();
        List<Server> currentServerDan = new ArrayList<>();
        List<Server> currentServerShuang = new ArrayList<>();
        List<Server> maxRateServerDan = new ArrayList<>();
        List<Server> maxRateServerShuang =new ArrayList<>();
        List<Server> minRateServerDan = new ArrayList<>();
        List<Server> minRateServerShuang =new ArrayList<>();

        int purchaseIndex = -1; // 前一天的最后一个购买的服务器编号，初始为-1

        for (int i = 0; i < allRequestArray.length; i++) {

            // 对双节点虚拟机的剩余容量排序
            sort(currentServerShuang);
            // 对单节点虚拟机的剩余容量排序
            sort2(currentServerDan);
            int vmNum = currentServerShuang.size() + currentServerDan.size()/2;
            // 对双节点虚拟机进行迁移
            List<String> migrationAnswer = move(currentServerShuang, vmNum*5/1000);
            int remainMoveNum = vmNum*5/1000 - (migrationAnswer.size()) ;
            // 对单节点虚拟机进行迁移
            List<String> migrationAnswer2 = move2(currentServerDan, remainMoveNum);

            List<String> requestAnswer = new ArrayList<>(); // 输出信息，先存在这里
            List<String> requestOfDayList = allRequestArray[i]; //这一天的请求
            int purchaseNum = 0;// 购买服务器数量
            minBuyServerNumDan = 0;
            midBuyServerNumDan = 0;
            maxBuyServerNumDan = 0;
            minBuyServerNumShuang = 0;
            midBuyServerNumShuang = 0;
            maxBuyServerNumShuang = 0;

            for (int j = 0; j < requestOfDayList.size(); j++) {
                String[] strings = requestOfDayList.get(j).split(",");
                if (strings[0].equals("add")) {
                    String vmName = strings[1].trim();
                    int vmId = Integer.parseInt(strings[2].trim());
                    Vm tempVm = vmNameToVmMap.get(vmName);
                    Vm currentVm = (Vm) tempVm.clone();
                    currentVm.setVmId(vmId);
                    vmIdToVmMap.put(vmId, currentVm);
                    // 单节点
                    if (currentVm.type == 0) {
                        // 不是极端的服务器
                        if (currentVm.cpuMemoryRate < cpuMemoryRateUpperLimit && currentVm.cpuMemoryRate > cpuMemoryRateLowerLimit) {
                            purchaseNum = addVmToServerListDan(midCpuMemoryRateServer, purchaseIndex, currentServerDan, 1, purchaseNum, currentVm);
                        } else if (currentVm.cpuMemoryRate >= cpuMemoryRateUpperLimit){
                            purchaseNum = addVmToServerListDan(maxCpuMemoryRateServer, purchaseIndex, maxRateServerDan, 2, purchaseNum, currentVm);
                        } else {
                            purchaseNum = addVmToServerListDan(minCpuMemoryRateServer, purchaseIndex, minRateServerDan, 0, purchaseNum, currentVm);
                        }
                    } else { // 双节点情况
                        if (currentVm.cpuMemoryRate < cpuMemoryRateUpperLimit && currentVm.cpuMemoryRate > cpuMemoryRateLowerLimit){
                            purchaseNum = addVmToServerListShuang(midCpuMemoryRateServer, currentServerShuang, purchaseIndex, 1, purchaseNum, currentVm);
                        }else if (currentVm.cpuMemoryRate >= cpuMemoryRateUpperLimit) {
                            purchaseNum = addVmToServerListShuang(maxCpuMemoryRateServer, maxRateServerShuang, purchaseIndex, 2, purchaseNum, currentVm);
                        } else {
                            purchaseNum = addVmToServerListShuang(minCpuMemoryRateServer, minRateServerShuang, purchaseIndex, 0, purchaseNum, currentVm);
                        }
                    }
                }else { // 删除虚拟机逻辑
                    int vmId = Integer.parseInt(strings[1].trim());
                    Vm currentVm = vmIdToVmMap.get(vmId);
                    int index = currentVm.getServerListIndex();
                    if (currentVm.type == 0) {
                        if (currentVm.cpuMemoryRate < cpuMemoryRateUpperLimit && currentVm.cpuMemoryRate > cpuMemoryRateLowerLimit){
                            addSpace(currentServerDan.get(index), currentVm);
                        } else if (currentVm.cpuMemoryRate >= cpuMemoryRateUpperLimit){
                            addSpace(maxRateServerDan.get(index), currentVm);
                        } else {
                            addSpace(minRateServerDan.get(index), currentVm);
                        }
                    } else {
                        if (currentVm.cpuMemoryRate < cpuMemoryRateUpperLimit && currentVm.cpuMemoryRate > cpuMemoryRateLowerLimit){
                            addSpace(currentServerShuang.get(index), currentVm);
                        } else if (currentVm.cpuMemoryRate >= cpuMemoryRateUpperLimit){
                            addSpace(maxRateServerShuang.get(index), currentVm);
                        } else {
                            addSpace(minRateServerShuang.get(index), currentVm);
                        }

                    }
                }
            }

            // 今天买的服务器重新编号
            ReNumber(currentServerDan, currentServerShuang, maxRateServerDan, maxRateServerShuang, minRateServerDan, minRateServerShuang, purchaseIndex);
            // 处理请求序列
            generateAnswer(currentServerDan, currentServerShuang, maxRateServerDan, maxRateServerShuang, minRateServerDan, minRateServerShuang, requestAnswer, requestOfDayList);
            // 输出答案
            printAnswer(midCpuMemoryRateServer, maxCpuMemoryRateServer, minCpuMemoryRateServer, migrationAnswer, migrationAnswer2, requestAnswer);

            purchaseIndex += purchaseNum;
        }
        System.out.println("maxBuyServerNum: " + purchaseIndex);
    }

    private static void printAnswer(Server midCpuMemoryRateServer, Server maxCpuMemoryRateServer, Server minCpuMemoryRateServer, List<String> migrationAnswer, List<String> migrationAnswer2, List<String> requestAnswer) {
        int midBuyServerNum = midBuyServerNumDan+midBuyServerNumShuang; // 先编号
        int minBuyServerNum = minBuyServerNumDan+minBuyServerNumShuang; // 第二个编号
        int maxBuyServerNum = maxBuyServerNumDan+maxBuyServerNumShuang; // 第三个编号

        int buyServerTypeNum = 0;
        if (midBuyServerNum > 0) buyServerTypeNum++;
        if (minBuyServerNum > 0) buyServerTypeNum++;
        if (maxBuyServerNum > 0) buyServerTypeNum++;
        System.out.println("(purchase, " + buyServerTypeNum + ")");
        if (midBuyServerNum>0){
            System.out.println("(" + midCpuMemoryRateServer.model + ", " + midBuyServerNum + ")");
        }
        if (minBuyServerNum>0){
            System.out.println("(" + minCpuMemoryRateServer.model + ", " + minBuyServerNum + ")");
        }
        if (maxBuyServerNum>0){
            System.out.println("(" + maxCpuMemoryRateServer.model + ", " + maxBuyServerNum + ")");
        }

//        System.out.println("(migration, 0)");
        if (migrationAnswer.size() + migrationAnswer2.size() > 0 ){
            System.out.println("(migration, " + (migrationAnswer.size() + migrationAnswer2.size() ) + ")");
            for (int j = 0; j < migrationAnswer.size(); j++) {
                System.out.println(migrationAnswer.get(j));
            }
            for (int j = 0; j < migrationAnswer2.size(); j++) {
                System.out.println(migrationAnswer2.get(j));
            }
        } else {
            System.out.println("(migration, 0)");
        }

        for (int j = 0; j < requestAnswer.size(); j++) {
            System.out.println(requestAnswer.get(j));
        }
    }


    private static void generateAnswer(List<Server> currentServerDan, List<Server> currentServerShuang, List<Server> maxRateServerDan, List<Server> maxRateServerShuang, List<Server> minRateServerDan, List<Server> minRateServerShuang, List<String> requestAnswer, List<String> requestOfDayList) {
        for (int j = 0; j < requestOfDayList.size(); j++) {
            String[] strings = requestOfDayList.get(j).split(",");
            if (strings[0].equals("add")) {
                int vmId = Integer.parseInt(strings[2].trim());
                Vm currentVm = vmIdToVmMap.get(vmId);
                // 单节点
                if (currentVm.type == 0) {
                    // 不是极端的服务器
                    if (currentVm.cpuMemoryRate < cpuMemoryRateUpperLimit && currentVm.cpuMemoryRate > cpuMemoryRateLowerLimit) {
                        generateAnswerDan(currentServerDan, requestAnswer, currentVm);
                    } else if (currentVm.cpuMemoryRate >= cpuMemoryRateUpperLimit){
                        generateAnswerDan(maxRateServerDan, requestAnswer, currentVm);
                    } else {
                        generateAnswerDan(minRateServerDan, requestAnswer, currentVm);
                    }
                } else { // 双节点情况
                    if (currentVm.cpuMemoryRate < cpuMemoryRateUpperLimit && currentVm.cpuMemoryRate > cpuMemoryRateLowerLimit){
                        generateAnswerShuang(currentServerShuang, requestAnswer, currentVm);
                    }else if (currentVm.cpuMemoryRate >= cpuMemoryRateUpperLimit) {
                        generateAnswerShuang(maxRateServerShuang, requestAnswer, currentVm);
                    } else {
                        generateAnswerShuang(minRateServerShuang, requestAnswer, currentVm);
                    }
                }
            }
        }
    }


    private static void ReNumber(List<Server> currentServerDan, List<Server> currentServerShuang, List<Server> maxRateServerDan, List<Server> maxRateServerShuang, List<Server> minRateServerDan, List<Server> minRateServerShuang, int purchaseIndex) {
        int newId = 1;
        for (int j = currentServerShuang.size()-midBuyServerNumShuang; j < currentServerShuang.size(); j++) {
            Server server = currentServerShuang.get(j);
            server.setId(purchaseIndex + newId);
            newId++;
        }
        int temp = 0;
        for (int j = currentServerDan.size()-midBuyServerNumDan*2; j < currentServerDan.size(); j++) {
            Server server = currentServerDan.get(j);
            server.setId(purchaseIndex + newId);
            temp++;
            if (temp==2){
                temp = 0;
                newId++;
            }

        }
        for (int j = minRateServerShuang.size()-minBuyServerNumShuang; j < minRateServerShuang.size(); j++) {
            Server server = minRateServerShuang.get(j);
            server.setId(purchaseIndex + newId);
            newId++;
        }
        temp = 0;
        for (int j = minRateServerDan.size()-minBuyServerNumDan*2; j < minRateServerDan.size(); j++) {
            Server server = minRateServerDan.get(j);
            server.setId(purchaseIndex + newId);
            temp++;
            if (temp==2){
                temp = 0;
                newId++;
            }
        }
        for (int j = maxRateServerShuang.size()-maxBuyServerNumShuang; j < maxRateServerShuang.size(); j++) {
            Server server = maxRateServerShuang.get(j);
            server.setId(purchaseIndex + newId);
            newId++;
        }
        temp = 0;
        for (int j = maxRateServerDan.size()-maxBuyServerNumDan*2; j < maxRateServerDan.size(); j++) {
            Server server = maxRateServerDan.get(j);
            server.setId(purchaseIndex + newId);
            temp++;
            if (temp==2){
                temp = 0;
                newId++;
            }
        }
    }

    private static int addVmToServerListShuang(Server midCpuMemoryRateServer, List<Server> currentServerShuang, int purchaseIndex, int mode, int purchaseNum, Vm currentVm) {
        // 返回要添加服务器的id
        int res = isEnough(currentServerShuang, currentVm);
        // 如果当前服务器没有可以存放虚拟机的情况
        if (res == -1) {
            purchaseNum++;
            if (mode == 0) minBuyServerNumShuang++;
            else if (mode == 1) midBuyServerNumShuang++;
            else maxBuyServerNumShuang++;
            addServerToServerShuangList(currentServerShuang, midCpuMemoryRateServer, purchaseIndex+purchaseNum);
            res = isEnough(currentServerShuang, currentVm);
        }
        currentVm.setServerListIndex(res);
        reduceSpace(currentServerShuang.get(res), currentVm);
        return purchaseNum;
    }

    private static void generateAnswerShuang(List<Server> currentServerShuang, List<String> requestAnswer, Vm currentVm){
        int res = currentVm.getServerListIndex();
        String currentAnswer = "(" + currentServerShuang.get(res).getId() + ")";
        requestAnswer.add(currentAnswer);
    }

    private static int addVmToServerListDan(Server maxCpuAndMemServer, int purchaseIndex, List<Server> currentServerDan, int mode, int purchaseNum, Vm currentVm) {
        int res = isEnough(currentServerDan, currentVm);
        // 如果当前服务器没有可以存放虚拟机的情况
        if (res == -1) {
            purchaseNum++;
            if (mode == 0) minBuyServerNumDan++;
            else if (mode == 1) midBuyServerNumDan++;
            else maxBuyServerNumDan++;
            addServerToServerDanList(currentServerDan, maxCpuAndMemServer, purchaseIndex+purchaseNum);
            res = isEnough(currentServerDan, currentVm);
        }
        currentVm.setServerListIndex(res);
        reduceSpace(currentServerDan.get(res), currentVm);

        return purchaseNum;
    }

    private static void generateAnswerDan(List<Server> currentServerDan, List<String> requestAnswer, Vm currentVm){
        int res = currentVm.getServerListIndex();
        String currentAnswer;
        if (res%2 == 0){
            currentAnswer = "(" + currentServerDan.get(res).getId() + ", A)";
        } else {
            currentAnswer = "(" + currentServerDan.get(res).getId() + ", B)";
        }
        requestAnswer.add(currentAnswer);
    }


    private static int isEnough(List<Server> serverDan, Vm currentVm) {
        for (int i = 0; i < serverDan.size(); i++) {
            if (isEnoughSpace(serverDan.get(i), currentVm)){
                return i;
            }
        }
        return -1;
    }





    private static void addServerToServerDanList(List<Server> serverDan, Server maxCpuAndMemServer, int currentId) {
        Server temp = (Server) maxCpuAndMemServer.clone();
        Server temp2 = (Server) maxCpuAndMemServer.clone();

        temp.remain_cpu = temp.remain_cpu/2;
        temp.remain_memory = temp.remain_memory/2;
        temp2.remain_cpu = temp2.remain_cpu/2;
        temp2.remain_memory = temp2.remain_memory/2;
        temp.setId(currentId);
        temp2.setId(currentId);

        serverDan.add(temp);
        serverDan.add(temp2);
    }

    private static void addServerToServerShuangList(List<Server> serverShuang, Server maxCpuAndMemServer, int currentId) {
        Server temp = (Server) maxCpuAndMemServer.clone();
        temp.setId(currentId);
        serverShuang.add(temp);
    }



    private static boolean isEnoughSpace(Server currentServer, Vm currentVm){
        if (currentServer.remain_cpu >= currentVm.cpu && currentServer.remain_memory >= currentVm.memory){
            return true;
        }
        return false;
    }

    // 增加虚拟机
    private static void reduceSpace(Server currentServer, Vm currentVm){
        if (currentServer.remain_cpu < currentVm.cpu && currentServer.remain_memory < currentVm.memory){
            throw new RuntimeException("---");
        }
        currentServer.remain_cpu -= currentVm.cpu;
        currentServer.remain_memory -= currentVm.memory;
        currentServer.vmList.add(currentVm);
    }

    // 删除虚拟机
    private static void addSpace(Server currentServer, Vm currentVm){
        currentServer.remain_cpu += currentVm.cpu;
        currentServer.remain_memory += currentVm.memory;
        boolean flag = currentServer.vmList.remove(currentVm);
    }


    private static Server selectServer2(){
        // 先选取cpu和内存最大的服务器
        Server res = serverArray[0];
        for (int i = 1; i < serverArray.length; i++) {
            Server temp = serverArray[i];
            if (temp.cpu > res.cpu && temp.memory > res.memory){
                res = temp;
            }
        }
        return res;
    }

    private static Server selectServer(){
        Server res = serverArray[0];
        double costmin=Integer.MAX_VALUE;
        for (int i = 1; i < serverArray.length; i++) {
            Server temp = serverArray[i];
            double cost=0;
            if(temp.memory<256||temp.cpu<256){
                continue;
            }
            double rate_tmp=(double)(temp.cpu)/temp.memory;
            int totalDay = 0;
            if(rate_tmp>=0.863){
                cost= (double)(temp.cost_day* totalDay /2+temp.cost_hardware)/(temp.memory);
                if(cost<costmin){
                    costmin=cost;
                    res = temp;
                }
            }
            else{
                cost= (double)(temp.cost_day* totalDay /2+temp.cost_hardware)/(temp.cpu);
                if(cost<costmin){
                    costmin=cost;
                    res = temp;
                }
            }
        }
        return res;
    }


    private static Server selectServerMaxRate(){
        Server maxRateServer = serverArray[0];
        for (int i = 1; i < serverArray.length; i++) {
            if (maxRateServer.cpuMemoryRate < serverArray[i].cpuMemoryRate) {
                maxRateServer = serverArray[i];
            }
        }
        return maxRateServer;
    }

    private static Server selectServerMinRate(){
        Server minRateServer = serverArray[0];
        for (int i = 1; i < serverArray.length; i++) {
            if (minRateServer.cpuMemoryRate > serverArray[i].cpuMemoryRate) {
                minRateServer = serverArray[i];
            }
        }
        return minRateServer;
    }





    private static List<String> move(List<Server> serverListShuang, int maxMoveNum){
        List<String> migrationAnswer = new ArrayList<>(); // 输出信息，先存在这里
        // 从后往前迁移
        while(maxMoveNum>0) {
            maxMoveNum--;
            Vm currentVm;
            int index = serverListShuang.size()-1;
            Server lastServer;
            while(true){
                lastServer = serverListShuang.get(index);
                if (lastServer.vmList.size()!=0) {
                    currentVm = lastServer.vmList.get(0);
                    break;
                } else {
                    index--;
                }
            }

            int res = isEnough(serverListShuang, currentVm);
            // 如果当前可以将虚拟机迁移到前面的服务器中
            if (res != currentVm.serverListIndex && res != -1) {
                addSpace(lastServer, currentVm);
                currentVm.setServerListIndex(res);
                reduceSpace(serverListShuang.get(res), currentVm);
                String temp = "("+ currentVm.vmId +", "+serverListShuang.get(res).getId()+")";
                migrationAnswer.add(temp);
            } else {
                break;
            }
        }
        return migrationAnswer;
    }

    private static List<String> move2(List<Server> serverListDan, int maxMoveNum){
        List<String> migrationAnswer = new ArrayList<>(); // 输出信息，先存在这里
        // 从后往前迁移
        while(maxMoveNum>0) {
            maxMoveNum--;
            Vm currentVm;
            int index = serverListDan.size()-1;
            Server lastServer;
            while(true){
                lastServer = serverListDan.get(index);
                if (lastServer.vmList.size()!=0) {
                    currentVm = lastServer.vmList.get(0);
                    break;
                } else {
                    index--;
                }
            }

            int res = isEnough(serverListDan, currentVm);
            // 如果当前可以将虚拟机迁移到前面的服务器中
            if (res != currentVm.serverListIndex && res != -1) {
                addSpace(lastServer, currentVm);
                currentVm.setServerListIndex(res);
                reduceSpace(serverListDan.get(res), currentVm);
                String temp = "(" + currentVm.vmId + ", " + serverListDan.get(res).getId() + ", " + (res%2 == 0?"A":"B") + ")";
                migrationAnswer.add(temp);
            } else {
                break;
            }
        }
        return migrationAnswer;
    }


    private static void prepare(){
        // 构造虚拟机型号到虚拟机的map
//        Arrays.stream(vmArray).collect(Collectors.toMap(a-> a.getModel()))
        vmNameToVmMap = new HashMap<>();
        vmIdToVmMap = new HashMap<>();
        for (int i = 0; i < vmArray.length; i++) {
            Vm temp = vmArray[i];
            vmNameToVmMap.put(temp.model, temp);
        }
    }


    private static void sort(List<Server> serverListShuang){

        for (int i = 0; i < serverListShuang.size(); i++) {
            int index = i;
            Server max = serverListShuang.get(i);
            for (int j = i+1; j < serverListShuang.size(); j++) {
                Server temp = serverListShuang.get(j);
                if ((max.remain_memory*0.86 + max.remain_cpu) > (temp.remain_cpu+temp.remain_memory*0.86)) {
                    max = temp;
                    index = j;
                }
            }
            if (index != i){
                serverListShuang.set(index, serverListShuang.get(i));
                serverListShuang.set(i, max);
                List<Vm> vmList = serverListShuang.get(i).vmList;
                for (int j = 0; j < vmList.size(); j++) {
                    vmList.get(j).setServerListIndex(i);
                }
                vmList = serverListShuang.get(index).vmList;
                for (int j = 0; j <vmList.size() ; j++) {
                    vmList.get(j).setServerListIndex(index);
                }
            }
        }
    }


    private static void sort2(List<Server> serverListDan){

        for (int i = 0; i < serverListDan.size(); i+=2) {
            int index = i;
            Server maxA = serverListDan.get(i);
            Server maxB = serverListDan.get(i+1);
            for (int j = i+2; j < serverListDan.size(); j+=2) {
                Server tempA = serverListDan.get(j);
                Server tempB = serverListDan.get(j+1);
                if ((maxA.remain_memory + maxA.remain_cpu + maxB.remain_cpu + maxB.remain_memory)
                        > (tempA.remain_cpu + tempA.remain_memory + tempB.remain_cpu + tempB.remain_memory)) {
                    maxA = tempA;
                    maxB = tempB;
                    index = j;
                }
            }
            if (index != i){
                serverListDan.set(index, serverListDan.get(i));
                serverListDan.set(index+1, serverListDan.get(i+1));
                serverListDan.set(i, maxA);
                serverListDan.set(i+1, maxB);
                List<Vm> vmList = serverListDan.get(i).vmList;
                for (int j = 0; j < vmList.size(); j++) {
                    vmList.get(j).setServerListIndex(i);
                }
                vmList = serverListDan.get(i+1).vmList;
                for (int j = 0; j < vmList.size(); j++) {
                    vmList.get(j).setServerListIndex(i+1);
                }
                vmList = serverListDan.get(index).vmList;
                for (int j = 0; j <vmList.size() ; j++) {
                    vmList.get(j).setServerListIndex(index);
                }
                vmList = serverListDan.get(index+1).vmList;
                for (int j = 0; j <vmList.size() ; j++) {
                    vmList.get(j).setServerListIndex(index+1);
                }
            }
        }
    }



    public static void main(String[] args) throws Exception {
        read2(); // 读取
        prepare(); // 构造数据结构，如相关map
        deal(); // 处理请求

    }
}




