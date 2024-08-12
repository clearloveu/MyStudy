package huawei2021.src;

import java.io.*;
import java.util.*;

/**
 * @author zg
 * @create 2021-03-14 14:13
 */
public class Main {
    private static Server[] serverArray;//服务器列表
    private static Vm[] vmArray;//虚拟机列表
    private static List[] allRequestArray;//请求列表，[天数][每一天的请求]


    private static Map<String, Vm> vmNameToVmMap;
    private static Map<Integer, Vm> vmIdToVmMap;
    private static Map<String, Server> vmNameToServer; //完美配对的虚拟机-服务器

    private static double cpuMemoryRateInSort = 1.0;
//    private static double[] limitList = {0.15, 0.25, 0.33, 0.52, 0.8125, 1, 1.45, 2.2, 4.83};
    private static double[] limitList = {100};

    //    private static double[] vmlimitList = {0.14, 0.52, 1, 7, 100.0};
    // key:cpu内存比 ； value： <=此cpu内存比的服务器集群
    private static Map<Double, ServerCluster> serverClusterMap;

    private static Map<String, ServerCluster> pairServerClusterMap;
    private static double maxTolerancePercent = 0.1;
    private static List<String> vmInPair;


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

    private static void deal() throws IOException {
        BufferedWriter out = new BufferedWriter(new FileWriter("answer.txt"));
        int purchaseIndex = -1; // 前一天的最后一个购买的服务器编号，初始为-1
        Server[] selectServerList = new Server[limitList.length];
        for (int i = 0; i < allRequestArray.length; i++) {
            // 重新选服务器列表
            // selectServerList[0] = selectServer(0.9, 1.1, i);
            // selectServerList[1] = selectServer(0.9, 1.1, i);
            selectServerList[0] = selectServer(0.8, 0.9, i);
//            selectServerList[1] = selectServer(0.8, 0.9, i);
//            selectServerList[2] = selectServer(0.8, 0.9, i);

            for (int j = 1; j < selectServerList.length; j++) {
                selectServerList[j] = selectServer(0.8, 0.9, i);
            }


            List<String> requestAnswer = new ArrayList<>(); // 处理请求输出信息，先存在这里
            List<String> requestOfDayList = allRequestArray[i]; //这一天的请求
            int purchaseNum = 0;// 购买服务器数量

            // 迁移
//           List<String> migrationAnswerAll = move();
             List<String> migrationAnswerAll = new ArrayList<>();


            for (int j = 0; j < requestOfDayList.size(); j++) {
                String[] strings = requestOfDayList.get(j).split(",");
                if (strings[0].equals("add")) {
                    String vmName = strings[1].trim();
                    int vmId = Integer.parseInt(strings[2].trim());
                    Vm tempVm = vmNameToVmMap.get(vmName);
                    Vm currentVm = (Vm) tempVm.clone();
                    currentVm.setVmId(vmId);
                    vmIdToVmMap.put(vmId, currentVm);
                    purchaseNum = addVm(purchaseIndex, selectServerList, purchaseNum, currentVm);
                }else { // 删除虚拟机逻辑
                    int vmId = Integer.parseInt(strings[1].trim());
                    Vm currentVm = vmIdToVmMap.get(vmId);
                    deleteVm(currentVm);
                }

            }






            // 今天买的服务器重新编号
            ReNumber(purchaseIndex);
            // 处理请求序列
            generateAnswer(requestAnswer, requestOfDayList);
            // 输出答案
//             printAnswer(selectServerList, migrationAnswerAll,requestAnswer);
            printAnswerToTxt(selectServerList, migrationAnswerAll,requestAnswer, out);

//            System.out.println("+++++++++++++++++" + i + "++++++++++++++");
//            for (int j = 0; j < 1; j++) {
//                ServerCluster serverCluster = serverClusterMap.get(limitList[j]);
//                List<Server> serverDan = serverCluster.serverListDan;
//                List<Server> serverShuang = serverCluster.serverListShuang;
//
//                for (int k = 0; k < serverDan.size(); k++) {
//                    System.out.println(serverDan.get(k).remain_cpu + "-" + serverDan.get(k).remain_memory);
//                }
//                System.out.println("============================");
//                for (int k = 0; k < serverShuang.size(); k++) {
//                    System.out.println(serverShuang.get(k).remain_cpu + "-" +  serverShuang.get(k).remain_memory);
//                }
//
//            }

            // 初始化购买数量
            for (String key: pairServerClusterMap.keySet()) {
                ServerCluster serverCluster = pairServerClusterMap.get(key);
                serverCluster.clear();
            }
            for (int j = 0; j < limitList.length; j++) {
                ServerCluster serverCluster = serverClusterMap.get(limitList[j]);
                serverCluster.clear();
            }

            purchaseIndex += purchaseNum;
        }
         System.out.println("maxBuyServerNum: " + purchaseIndex);
        System.out.println("--------------------");
        int sum = 0;
        for (String key: pairServerClusterMap.keySet()) {
            ServerCluster serverCluster = pairServerClusterMap.get(key);
            sum += serverCluster.serverListDan.size();
            sum += serverCluster.serverListShuang.size();
//            for (int i = 0; i < serverCluster.serverListDan.size(); i++) {
//                Server server = serverCluster.serverListDan.get(i);
//                System.out.println(server.remain_cpu+"-"+server.remain_memory);
//                System.out.println(server.cpu+"="+server.memory);
//
//            }
            for (int i = 0; i < serverCluster.serverListShuang.size(); i++) {
                Server server = serverCluster.serverListShuang.get(i);
                System.out.println(server.remain_cpu+"-"+server.remain_memory);
                System.out.println(server.cpu+"="+server.memory);
                Vm vm = vmNameToVmMap.get(key);
                System.out.println(vm.cpu +"+"+vm.memory);
            }
        }
        System.out.println(sum);
        out.close();
    }

    private static void deleteVm(Vm currentVm) {
        int index = currentVm.getServerListIndex();

        // 如果是完美配对的情况
        if (pairServerClusterMap.containsKey(currentVm.model)) {
            ServerCluster cluster = pairServerClusterMap.get(currentVm.model);
            List<Server> currentServer;
            if (currentVm.type == 0) {
                currentServer = cluster.serverListDan;
            } else {
                currentServer = cluster.serverListShuang;
            }
            addSpace(currentServer.get(index), currentVm);
            return;
        }


        int temp = 0;
        while (true){
            if (currentVm.cpuMemoryRate < limitList[temp]||currentVm.cpuMemoryRate > limitList[limitList.length-temp-1]) {
                ServerCluster cluster = serverClusterMap.get(limitList[temp]);
                List<Server> currentServer;
                if (currentVm.type == 0) {
                    currentServer = cluster.serverListDan;
                } else {
                    currentServer = cluster.serverListShuang;
                }
                addSpace(currentServer.get(index), currentVm);
                break;
            }
            temp++;
        }
    }

    private static int addVm(int purchaseIndex, Server[] selectServerList, int purchaseNum, Vm currentVm) {
        // 单节点
        if (currentVm.type == 0) {

            // 完美配对
            if (pairServerClusterMap.containsKey(currentVm.model)) {
                Server selectServer = vmNameToServer.get(currentVm.model);
                purchaseNum = addVmToServerListDanPair(selectServer, purchaseIndex, purchaseNum, currentVm);
            }
            else {
                int temp = 0;
                while (true){

                    if (currentVm.cpuMemoryRate < limitList[temp]||currentVm.cpuMemoryRate > limitList[limitList.length-temp-1]) {
                        purchaseNum = addVmToServerListDan(selectServerList[temp], purchaseIndex, temp, purchaseNum, currentVm);
                        break;
                    }
                    temp++;
                }
            }
        } else { // 双节点情况


            // 完美配对
            if (pairServerClusterMap.containsKey(currentVm.model)) {
                Server selectServer = vmNameToServer.get(currentVm.model);
                purchaseNum = addVmToServerListShuangPair(selectServer, purchaseIndex, purchaseNum, currentVm);
            }else {
                int temp = 0;
                while (true){
                    if (currentVm.cpuMemoryRate < limitList[temp]||currentVm.cpuMemoryRate > limitList[limitList.length-temp-1]) {
                        purchaseNum = addVmToServerListShuang(selectServerList[temp], purchaseIndex, temp, purchaseNum, currentVm);
                        break;
                    }
                    temp++;
                }
            }

        }
        return purchaseNum;
    }


    private static int findMaxCpuServerIndex(List<Server> serverList, Vm currentVm){
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < serverList.size(); i++) {
            if (currentVm.cpu > serverList.get(i).remain_cpu || currentVm.memory > serverList.get(i).remain_memory){
                set.add(i);
            }
        }
        int index = -1;
        double   maxRemainCpuMemoryRate = -1;
        for (int i = 0; i < serverList.size(); i++) {
            if (!set.contains(i)) {
                Server temp = serverList.get(i);
                if ((temp.remain_cpu+1)*1.0/(temp.remain_memory+1) > maxRemainCpuMemoryRate){
                    maxRemainCpuMemoryRate = (temp.remain_cpu+1)*1.0/(temp.remain_memory+1);
                    index = i;
                }
            }
        }
        if (index == -1) throw new RuntimeException("error, can not find element");
        return index;
    }

    private static int findMaxMemoryServerIndex(List<Server> serverList, Vm currentVm){
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < serverList.size(); i++) {
            if (currentVm.cpu > serverList.get(i).remain_cpu || currentVm.memory > serverList.get(i).remain_memory){
                set.add(i);
            }
        }
        int index = -1;
        double   minRemainCpuMemoryRate = 10000;
        for (int i = 0; i < serverList.size(); i++) {
            if (!set.contains(i)) {
                Server temp = serverList.get(i);
                if ((temp.remain_cpu+1)*1.0/(temp.remain_memory+1) < minRemainCpuMemoryRate){
                    minRemainCpuMemoryRate = (temp.remain_cpu+1)*1.0/(temp.remain_memory+1);
                    index = i;
                }
            }
        }
        if (index == -1) throw new RuntimeException("error, can not find element");

        return index;
    }



    private static int findMaxCpuServerIndex_back(List<Server> serverList, Vm currentVm){
        Set<Integer> set = new HashSet<>();
        int index = -1;
        int maxRemainCpu = -1;
        while (true){
            maxRemainCpu = -1;
            for (int i = 0; i < serverList.size(); i++) {
                if (!set.contains(i)) {
                    Server temp = serverList.get(i);
                    if (temp.remain_cpu > maxRemainCpu){
                        maxRemainCpu = temp.remain_cpu;
                        index = i;
                    }
                }
            }
            if (currentVm.cpu > serverList.get(index).remain_cpu || currentVm.memory > serverList.get(index).remain_memory){
                set.add(index);
            }else {
                break;
            }
        }

        return index;
    }

    private static int findMaxMemoryServerIndex_back(List<Server> serverList, Vm currentVm){
        Set<Integer> set = new HashSet<>();
        int index = -1;
        int maxRemainMemory = -1;
        while(true){
            maxRemainMemory = -1;
            for (int i = 0; i < serverList.size(); i++) {
                if (!set.contains(i)) {
                    Server temp = serverList.get(i);
                    if (temp.remain_memory > maxRemainMemory){
                        maxRemainMemory = temp.remain_memory;
                        index = i;
                    }
                }
            }
            if (currentVm.cpu > serverList.get(index).remain_cpu || currentVm.memory > serverList.get(index).remain_memory){
                set.add(index);
            }else {
                break;
            }
        }

        return index;
    }




    private static void printAnswer(Server[] selectServerList, List<String> migrationAnswerAll, List<String> requestAnswer) {
        // 所有种类购买服务器的数量
        int[] bugServerNum = new int[limitList.length];
        for (int i = 0; i < limitList.length; i++) {
            ServerCluster serverCluster = serverClusterMap.get(limitList[i]);
            bugServerNum[i] = serverCluster.buyServerNumDan + serverCluster.buyServerNumShuang;
        }
        int buyServerTypeNum = 0;
        for (int i = 0; i < limitList.length; i++) {
            if (bugServerNum[i] > 0) buyServerTypeNum++;
        }
        System.out.println("(purchase, " + buyServerTypeNum + ")");
        for (int i = 0; i < limitList.length; i++) {
            if (bugServerNum[i] > 0) {
                System.out.println("(" + selectServerList[i].model + ", " + bugServerNum[i] + ")");
            }
        }
//        System.out.println("(migration, 0)");
        if (migrationAnswerAll.size()>0){
            System.out.println("(migration, " + migrationAnswerAll.size() + ")");
            for (int j = 0; j < migrationAnswerAll.size(); j++) {
                System.out.println(migrationAnswerAll.get(j));
            }
        } else {
            System.out.println("(migration, 0)");
        }
        for (int j = 0; j < requestAnswer.size(); j++) {
            System.out.println(requestAnswer.get(j));
        }
    }


    private static void printAnswerToTxt(Server[] selectServerList, List<String> migrationAnswerAll,
                                         List<String> requestAnswer, BufferedWriter out) throws IOException {

        // 完美配对
        List<Integer> buyServerNumInPair = new ArrayList<>();
        for (int i = 0; i < vmInPair.size(); i++) {
            ServerCluster serverCluster = pairServerClusterMap.get(vmInPair.get(i));
            buyServerNumInPair.add(serverCluster.buyServerNumDan + serverCluster.buyServerNumShuang);
        }


        // 所有种类购买服务器的数量
        int[] bugServerNum = new int[limitList.length];
        for (int i = 0; i < limitList.length; i++) {
            ServerCluster serverCluster = serverClusterMap.get(limitList[i]);
            bugServerNum[i] = serverCluster.buyServerNumDan + serverCluster.buyServerNumShuang;
        }
        int buyServerTypeNum = 0;


        for (int i = 0; i < buyServerNumInPair.size(); i++) {
            if (buyServerNumInPair.get(i) > 0) buyServerTypeNum++;
        }
        for (int i = 0; i < limitList.length; i++) {
            if (bugServerNum[i] > 0) buyServerTypeNum++;
        }
        out.write("(purchase, " + buyServerTypeNum + ")\n");

        for (int i = 0; i < vmInPair.size(); i++) {
            if (buyServerNumInPair.get(i) > 0) {
                out.write("(" + vmNameToServer.get(vmInPair.get(i)).model + ", " + buyServerNumInPair.get(i) + ")\n");
            }
        }


        for (int i = 0; i < limitList.length; i++) {
            if (bugServerNum[i] > 0) {
                out.write("(" + selectServerList[i].model + ", " + bugServerNum[i] + ")\n");
            }
        }


        if (migrationAnswerAll.size()>0){
            out.write("(migration, " + migrationAnswerAll.size() + ")\n");
            for (int j = 0; j < migrationAnswerAll.size(); j++) {
                out.write(migrationAnswerAll.get(j)+"\n");
            }
        } else {
            out.write("(migration, 0)\n");
        }
        for (int j = 0; j < requestAnswer.size(); j++) {
            out.write(requestAnswer.get(j)+"\n");
        }
    }


    private static void generateAnswer(List<String> requestAnswer, List<String> requestOfDayList) {
        for (int j = 0; j < requestOfDayList.size(); j++) {
            String[] strings = requestOfDayList.get(j).split(",");
            if (strings[0].equals("add")) {
                int vmId = Integer.parseInt(strings[2].trim());
                Vm currentVm = vmIdToVmMap.get(vmId);

                // 处理完美请求
                if (pairServerClusterMap.containsKey(currentVm.model)) {
                    ServerCluster currentServerCluster = pairServerClusterMap.get(currentVm.model);
                    if (currentVm.type == 0) { //单节点
                        List<Server> currentServerDan = currentServerCluster.serverListDan;
                        generateAnswerDan(currentServerDan, requestAnswer, currentVm);
                    } else { // 双节点
                        List<Server> currentServerShuang = currentServerCluster.serverListShuang;
                        generateAnswerShuang(currentServerShuang, requestAnswer, currentVm);
                    }
                } else {
                    int temp = 0;
                    while (true) {

                        if (currentVm.cpuMemoryRate < limitList[temp]||currentVm.cpuMemoryRate > limitList[limitList.length-temp-1]) {
                            ServerCluster currentServerCluster = serverClusterMap.get(limitList[temp]);
                            if (currentVm.type == 0) { //单节点
                                List<Server> currentServerDan = currentServerCluster.serverListDan;
                                generateAnswerDan(currentServerDan, requestAnswer, currentVm);
                                break;
                            } else { // 双节点
                                List<Server> currentServerShuang = currentServerCluster.serverListShuang;
                                generateAnswerShuang(currentServerShuang, requestAnswer, currentVm);
                                break;
                            }
                        }
                        temp++;
                    }
                }
            }
        }
    }


    private static void ReNumber(int purchaseIndex) {
        int newId = 1;

        // 处理完美配对
        for (int i = 0; i < vmInPair.size(); i++) {
            ServerCluster serverCluster = pairServerClusterMap.get(vmInPair.get(i));
            List<Server> currentServerShuang = serverCluster.serverListShuang;
            List<Server> currentServerDan = serverCluster.serverListDan;
            int buyServerNumShuang = serverCluster.buyServerNumShuang;
            int buyServerNumDan = serverCluster.buyServerNumDan;
            for (int j = currentServerShuang.size()-buyServerNumShuang; j < currentServerShuang.size(); j++) {
                Server server = currentServerShuang.get(j);
                server.setId(purchaseIndex + newId);
                newId++;
            }
            int temp = 0;
            for (int j = currentServerDan.size()-buyServerNumDan*2; j < currentServerDan.size(); j++) {
                Server server = currentServerDan.get(j);
                server.setId(purchaseIndex + newId);
                temp++;
                if (temp==2){
                    temp = 0;
                    newId++;
                }
            }
        }


        for (int i = 0; i < limitList.length; i++) {
            ServerCluster serverCluster = serverClusterMap.get(limitList[i]);
            List<Server> currentServerShuang = serverCluster.serverListShuang;
            List<Server> currentServerDan = serverCluster.serverListDan;
            int buyServerNumShuang = serverCluster.buyServerNumShuang;
            int buyServerNumDan = serverCluster.buyServerNumDan;
            for (int j = currentServerShuang.size()-buyServerNumShuang; j < currentServerShuang.size(); j++) {
                Server server = currentServerShuang.get(j);
                server.setId(purchaseIndex + newId);
                newId++;
            }
            int temp = 0;
            for (int j = currentServerDan.size()-buyServerNumDan*2; j < currentServerDan.size(); j++) {
                Server server = currentServerDan.get(j);
                server.setId(purchaseIndex + newId);
                temp++;
                if (temp==2){
                    temp = 0;
                    newId++;
                }
            }
        }
    }

    private static int addVmToServerListShuang(Server selectServer, int purchaseIndex, int index, int purchaseNum, Vm currentVm) {
        ServerCluster currentServerCluster = serverClusterMap.get(limitList[index]);
        List<Server> currentServerShuang = currentServerCluster.serverListShuang;
        // 返回要添加服务器的id
        int res = isEnough(currentServerShuang, currentVm);
        // 如果当前服务器没有可以存放虚拟机的情况
        if (res == -1) {
            purchaseNum++;
            currentServerCluster.buyServerNumShuang++;
            addServerToServerShuangList(currentServerShuang, selectServer, purchaseIndex + purchaseNum);
//            res = isEnough(currentServerShuang, currentVm);
        }

        if (currentVm.cpu > currentVm.memory){
            res = findMaxCpuServerIndex(currentServerShuang, currentVm);
        }else {
            res = findMaxMemoryServerIndex(currentServerShuang, currentVm);
        }



        currentVm.setServerListIndex(res);
        reduceSpace(currentServerShuang.get(res), currentVm);
        return purchaseNum;
    }


    private static int addVmToServerListShuangPair(Server selectServer, int purchaseIndex, int purchaseNum, Vm currentVm) {
        ServerCluster currentServerCluster = pairServerClusterMap.get(currentVm.model);
        List<Server> currentServerShuang = currentServerCluster.serverListShuang;
        // 返回要添加服务器的id
        int res = isEnough(currentServerShuang, currentVm);
        // 如果当前服务器没有可以存放虚拟机的情况
        if (res == -1) {
            purchaseNum++;
            currentServerCluster.buyServerNumShuang++;
            addServerToServerShuangList(currentServerShuang, selectServer, purchaseIndex + purchaseNum);
//            res = isEnough(currentServerShuang, currentVm);
        }

        if (currentVm.cpu > currentVm.memory){
            res = findMaxCpuServerIndex(currentServerShuang, currentVm);
        }else {
            res = findMaxMemoryServerIndex(currentServerShuang, currentVm);
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

    private static int addVmToServerListDan(Server selectServer, int purchaseIndex, int index, int purchaseNum, Vm currentVm) {
        ServerCluster currentServerCluster = serverClusterMap.get(limitList[index]);
        List<Server> currentServerDan = currentServerCluster.serverListDan;
        int res = isEnough(currentServerDan, currentVm);
        // 如果当前服务器没有可以存放虚拟机的情况
        if (res == -1) {
            purchaseNum++;
            currentServerCluster.buyServerNumDan++;
            addServerToServerDanList(currentServerDan, selectServer, purchaseIndex + purchaseNum);
//             res = isEnough(currentServerDan, currentVm);
        }
        // 更新所存放的服务器位置
        if (currentVm.memory > currentVm.cpu){
            res = findMaxMemoryServerIndex(currentServerDan, currentVm);
        }else {
            res = findMaxCpuServerIndex(currentServerDan, currentVm);
        }


        currentVm.setServerListIndex(res);
        reduceSpace(currentServerDan.get(res), currentVm);

        return purchaseNum;
    }

    private static int addVmToServerListDanPair(Server selectServer, int purchaseIndex, int purchaseNum, Vm currentVm) {
        ServerCluster currentServerCluster = pairServerClusterMap.get(currentVm.model);
        List<Server> currentServerDan = currentServerCluster.serverListDan;
        int res = isEnough(currentServerDan, currentVm);
        // 如果当前服务器没有可以存放虚拟机的情况
        if (res == -1) {
            purchaseNum++;
            currentServerCluster.buyServerNumDan++;
            addServerToServerDanList(currentServerDan, selectServer, purchaseIndex + purchaseNum);
//             res = isEnough(currentServerDan, currentVm);
        }
        // 更新所存放的服务器位置
        if (currentVm.memory > currentVm.cpu){
            res = findMaxMemoryServerIndex(currentServerDan, currentVm);
        }else {
            res = findMaxCpuServerIndex(currentServerDan, currentVm);
        }


        currentVm.setServerListIndex(res);
        reduceSpace(currentServerDan.get(res), currentVm);

        return purchaseNum;
    }

    private static int addVmToServerListDan_back(Server selectServer, int purchaseIndex, int index, int purchaseNum, Vm currentVm) {
        ServerCluster currentServerCluster = serverClusterMap.get(limitList[index]);
        List<Server> currentServerDan = currentServerCluster.serverListDan;
        int res = isEnough(currentServerDan, currentVm);
        // 如果当前服务器没有可以存放虚拟机的情况
        if (res == -1) {
            purchaseNum++;
            currentServerCluster.buyServerNumDan++;
            addServerToServerDanList(currentServerDan, selectServer, purchaseIndex + purchaseNum);
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
        if (!flag) {
            throw new RuntimeException("can not remove");
        }
    }


    private static Server selectServer2(double lowerRate, double upperRate, int day){
        // 先选取cpu和内存最大的服务器
        Server res = serverArray[0];
        double totalcost=0;
        double costmin=Integer.MAX_VALUE;
        for (int i = 1; i < serverArray.length; i++) {
            Server temp = serverArray[i];
            double rate = temp.cpu*1.0/temp.memory;
            if(rate>upperRate||rate<lowerRate){
                continue;
            }
            if(temp.memory<128*2||temp.cpu<128*2){
                continue;
            }
            totalcost=(double)((allRequestArray.length-day)*temp.cost_day+temp.cost_hardware)/(temp.cpu+temp.memory);
            if(totalcost<costmin){
                res=temp;
                costmin=totalcost;
            }
        }

        return res;
    }

    private static Server selectServer(double lowerRate, double upperRate, int day){
        //lowerRate = 0.8;
        //upperRate = 0.9;
        int totalday = allRequestArray.length;
        // 先选取cpu和内存最大的服务器
        Server res = serverArray[0];
        Server maxser=serverArray[0];
        Server minser=serverArray[0];
        double totalcost=0;
        double costmin=Integer.MAX_VALUE;
        boolean find=false;
        List<Server> serverratelist=new ArrayList<Server>();

        for(int i = 1; i < serverArray.length; i++){
            Server temp = serverArray[i];
//            if(temp.cpu>256&&temp.memory>256){
            serverratelist.add(temp);
//            }
        }
        Collections.sort(serverratelist, new Comparator<Server>() {
            @Override
            public int compare(Server o1, Server o2) {
                if(o1.cpu*0.1/o1.memory-o2.cpu*0.1/o2.memory>0){
                    return -1;
                }
                else {
                    return 1;
                }
            }
        });
        for(int i=0;i<5;i++){
            Server temp = serverratelist.get(i);
            totalcost=(double)((totalday-day)*temp.cost_day+temp.cost_hardware)/(temp.cpu+temp.memory);
            if(totalcost<costmin){
                maxser=temp;
                costmin=totalcost;
            }
        }

        totalcost=0;
        costmin=Integer.MAX_VALUE;

        for(int i=serverratelist.size()-1;i>serverratelist.size()-6;i--){
            Server temp = serverratelist.get(i);

            totalcost=(double)((totalday-day)*temp.cost_day+temp.cost_hardware)/(temp.cpu+temp.memory);
            if(totalcost<costmin){
                minser=temp;
                costmin=totalcost;
            }
        }

        totalcost=0;
        costmin=Integer.MAX_VALUE;

        for (int i = 1; i < serverArray.length; i++) {
            Server temp = serverArray[i];
            double rate=temp.cpu*1.0/temp.memory;
            if(rate>upperRate||rate<lowerRate){
                continue;
            }
            if(temp.memory<256||temp.cpu<256){
                continue;
            }
            totalcost=(double)((totalday-day)*temp.cost_day+temp.cost_hardware)/(temp.cpu+temp.memory);
            if(totalcost<costmin){
                res=temp;
                costmin=totalcost;
                find=true;
            }
        }
        if(!find){
            if(lowerRate>1){
                res=maxser;
            }
            else {
                res=minser;
            }
        }

        return res;
    }



    private static List<String> move(){
        List<String> migrationAnswerAll = new ArrayList<>();
        int vmSum = getVmSum(); //获得虚拟机总量
        int maxMoveSum = vmSum*5/1000;
        sortVm(); // 在每个服务器内，对其中的虚拟机按照（cpu+memory）排序，使用率高的在前面

        int index = 0;
        while (maxMoveSum>0 && index < limitList.length){
            ServerCluster serverCluster = serverClusterMap.get(limitList[index]);
            List<Server> serverListShuang = serverCluster.serverListShuang;
            List<Server> serverListDan = serverCluster.serverListDan;

            List<String> migrationAnswer; // 迁移信息

            if (serverListShuang.size()>0){
                migrationAnswer = moveShuang(serverListShuang, maxMoveSum/2);
                maxMoveSum -= migrationAnswer.size();
                migrationAnswerAll.addAll(migrationAnswer);
            }
            if (serverListDan.size()>0){
                migrationAnswer = moveDan(serverListDan, maxMoveSum/2);
                maxMoveSum -= migrationAnswer.size();
                migrationAnswerAll.addAll(migrationAnswer);
            }
            index++;
        }
        return migrationAnswerAll;
    }

//    private static List<String> move2(){
//        List<String> migrationAnswerAll = new ArrayList<>();
//        int vmSum = getVmSum(); //获得虚拟机总量
//        sortVm(); // 在每个服务器内，对其中的虚拟机按照（cpu+memory）排序，使用率高的在前面
//
//        while (vmSum>0){
//            List<Server> maxRemainServerList = new ArrayList<>();
//            boolean flag = findMaxRemainServerList(maxRemainServerList); // 确定当前服务器中剩余量最大的服务器
//            if (maxRemainServerList.size() == 0) {
////                break;
//                throw new RuntimeException("can not find server");
//            }
//            List<String> migrationAnswer;
//            // true:单;false:双
//            if (flag){
//                migrationAnswer = moveDan(maxRemainServerList, vmSum);
//            } else {
//                migrationAnswer = moveShuang(maxRemainServerList, vmSum);
//            }
//            vmSum -= migrationAnswer.size();
//            migrationAnswerAll.addAll(migrationAnswer);
//
//        }
//        return migrationAnswerAll;
//    }

//    private static boolean findMaxRemainServerList(List<Server> maxRemainServerList) {
//        double maxRemainSpace = 0;
//        boolean flag = true; // true:单;false:双
//
//        // 确定当前服务器中剩余量最大的服务器
//        for (int i = 0; i < limitList.length; i++) {
//            ServerCluster serverCluster = serverClusterMap.get(limitList[i]);
//            List<Server> serverListShuang = serverCluster.serverListShuang;
//            Server maxRemainServerShuang = serverListShuang.get(serverListShuang.size()-1);
//            if (!maxRemainServerShuang.isSelect) {
//                if (maxRemainSpace < (maxRemainServerShuang.remain_memory*cpuMemoryRateInSort
//                        + maxRemainServerShuang.remain_cpu)){
//                    maxRemainSpace = (maxRemainServerShuang.remain_memory*cpuMemoryRateInSort
//                            + maxRemainServerShuang.remain_cpu);
//                    maxRemainServerList = serverListShuang;
//                    maxRemainServerShuang.isSelect = true;
//                    flag = false;
//                }
//            }
//
//            List<Server> serverListDan = serverCluster.serverListDan;
//            Server maxRemainServerDan = serverListDan.get(serverListDan.size()-1);
//            if (!maxRemainServerDan.isSelect){
//                if (maxRemainSpace < (maxRemainServerDan.remain_memory*cpuMemoryRateInSort
//                        + maxRemainServerDan.remain_cpu)){
//                    maxRemainSpace = (maxRemainServerDan.remain_memory*cpuMemoryRateInSort
//                            + maxRemainServerDan.remain_cpu);
//                    maxRemainServerList = serverListDan;
//                    maxRemainServerDan.isSelect = true;
//                    flag = true;
//                }
//            }
//
//
//        }
//        return flag;
//    }

    private static void sortVm() {
//        System.out.println("111");
        for (int i = 0; i < limitList.length; i++) {
            ServerCluster serverCluster = serverClusterMap.get(limitList[i]);
            List<Server> serverListShuang = serverCluster.serverListShuang;
            List<Server> serverListDan = serverCluster.serverListDan;
            // 对双节点虚拟机的剩余容量排序
            sortShuang(serverListShuang);
            // 对单节点虚拟机的剩余容量排序
            sortDan(serverListDan);
//            System.out.println("222");
        }
    }

    private static int getVmSum() {
        int vmSum = 0; // 虚拟机总量
        for (int i = 0; i < limitList.length; i++) {
            ServerCluster serverCluster = serverClusterMap.get(limitList[i]);
            List<Server> serverListShuang = serverCluster.serverListShuang;
            List<Server> serverListDan = serverCluster.serverListDan;
            for (int j = 0; j < serverListShuang.size(); j++) {
                List<Vm> vmList = serverListShuang.get(j).vmList;
                vmSum += vmList.size();
            }
            for (int j = 0; j < serverListDan.size(); j++) {
                vmSum += serverListDan.get(j).vmList.size();
            }
        }
        return vmSum;
    }


    private static List<String> moveShuang(List<Server> serverListShuang, int maxMoveNum){
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

    private static List<String> moveDan(List<Server> serverListDan, int maxMoveNum){
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
        // 初始化serverClusterMap
        serverClusterMap = new HashMap<>();
        for (int i = 0; i < limitList.length; i++) {
            ServerCluster cluster = new ServerCluster(limitList[i]);
            serverClusterMap.put(limitList[i], cluster);
        }
        pairServerClusterMap = new HashMap<>();
        vmNameToServer = new HashMap<>();
        vmInPair = new ArrayList<>();
        List<Vm> vmList = Arrays.asList(vmArray);
        vmList = new ArrayList<>(vmList);
        Set<String> findedVmSet = new HashSet<>();
        // 找完美的服务器装载这个虚拟机
        for (int i = 0; i < vmList.size(); i++) {
            Vm currentVm = vmList.get(i);
            double vmCpuMemoryRate = currentVm.cpu*1.0/currentVm.memory;
            double tolerancePercent = 0.0;
            boolean flag = false;
            while(tolerancePercent <  maxTolerancePercent && !flag){
                tolerancePercent += 0.01;
                for (int j = 0; j < serverArray.length; j++) {
                    Server currentServer = serverArray[j];

                    if (currentVm.type == 0) {
                        if(currentServer.cpu/2 < currentVm.cpu || currentServer.memory/2 < currentVm.memory) continue;
                    }

                    double serverCpuMemoryRate = currentServer.cpu*1.0/currentServer.memory;
                    if (Math.abs(vmCpuMemoryRate-serverCpuMemoryRate) < tolerancePercent ){
                        if ((currentServer.cpu%currentVm.cpu)*1.0/currentVm.cpu < tolerancePercent &&
                                (currentServer.memory%currentVm.memory)*1.0/currentVm.memory < tolerancePercent) {
                            ServerCluster cluster = new ServerCluster(serverCpuMemoryRate);
                            pairServerClusterMap.put(currentVm.model, cluster);
                            findedVmSet.add(currentVm.model);
                            vmNameToServer.put(currentVm.model, currentServer);
                            vmInPair.add(currentVm.model);
                            flag = true;
                            break;
                        }
                    }
                }
            }
        }


        System.out.println(1);

//        for (int i = 0; i < allRequestArray.length; i++) {
//            List<Server> requestInDay = allRequestArray[i];
//            for (int j = 0; j < requestInDay.size(); j++) {
//
//            }
//        }



    }


    private static void sortShuang(List<Server> serverListShuang){

        for (int i = 0; i < serverListShuang.size(); i++) {
            int index = i;
            Server max = serverListShuang.get(i);
            for (int j = i+1; j < serverListShuang.size(); j++) {
                Server temp = serverListShuang.get(j);
                if ((max.remain_memory*cpuMemoryRateInSort + max.remain_cpu) > (temp.remain_cpu+temp.remain_memory*cpuMemoryRateInSort)) {
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


    private static void sortDan(List<Server> serverListDan){

        for (int i = 0; i < serverListDan.size(); i+=2) {
            int index = i;
            Server maxA = serverListDan.get(i);
            Server maxB = serverListDan.get(i+1);
            for (int j = i+2; j < serverListDan.size(); j+=2) {
                Server tempA = serverListDan.get(j);
                Server tempB = serverListDan.get(j+1);
                if ((maxA.remain_memory*cpuMemoryRateInSort + maxA.remain_cpu + maxB.remain_cpu + maxB.remain_memory*cpuMemoryRateInSort)
                        > (tempA.remain_cpu + tempA.remain_memory*cpuMemoryRateInSort + tempB.remain_cpu + tempB.remain_memory*cpuMemoryRateInSort)) {
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


