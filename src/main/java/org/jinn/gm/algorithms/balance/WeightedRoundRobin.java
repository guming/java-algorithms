package org.jinn.gm.algorithms.balance;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
/**
 * Created by gumingcn on 16/3/7.
 */
public class WeightedRoundRobin {

    private int currentIndex = -1;// 上一次选择的服务器
    private int currentWeight = 0;// 当前调度的权值
    private int maxWeight = 0; // 最大权重
    private int gcdWeight = 0; //所有服务器权重的最大公约数
    private int serverCount = 0; //服务器数量
    private List<Server> serverList; //服务器集合

    private static int gcd(int a, int b) {
        BigInteger b1 = new BigInteger(String.valueOf(a));
        BigInteger b2 = new BigInteger(String.valueOf(b));
        BigInteger gcd = b1.gcd(b2);
        return gcd.intValue();
    }

    private static int getGCDForServers(List<Server> serverList ) {
        int w = 0;
        for (int i = 0, len = serverList.size(); i < len - 1; i++) {
            if (w == 0) {
                w = gcd(serverList.get(i).weight, serverList.get(i + 1).weight);
            } else {
                w = gcd(w, serverList.get(i + 1).weight);
            }
        }
        return w;
    }

    public static int getMaxWeightForServers(List<Server> serverList) {
        int w = 0;
        for (int i = 0, len = serverList.size(); i < len - 1; i++) {
            if (w == 0) {
                w = Math.max(serverList.get(i).weight, serverList.get(i + 1).weight);
            } else {
                w = Math.max(w, serverList.get(i + 1).weight);
            }
        }
        return w;
    }

    public Server GetServer() {
        while (true) {
            currentIndex = (currentIndex + 1) % serverCount;
            if (currentIndex == 0) {
                currentWeight = currentWeight - gcdWeight;
                if (currentWeight <= 0) {
                    currentWeight = maxWeight;
                    if (currentWeight == 0)
                        return null;
                }
            }
            if (serverList.get(currentIndex).weight >= currentWeight) {
                return serverList.get(currentIndex);
            }
        }
    }


    class Server {
        public String ip;
        public int weight;
        public Server(String ip, int weight) {
            super();
            this.ip = ip;
            this.weight = weight;
        }
        public String getIp() {
            return ip;
        }
        public void setIp(String ip) {
            this.ip = ip;
        }
        public int getWeight() {
            return weight;
        }
        public void setWeight(int weight) {
            this.weight = weight;
        }
    }


    public void init() {
        Server s1 = new Server("192.168.0.100", 3);//3
        Server s2 = new Server("192.168.0.101", 2);//2
        Server s3 = new Server("192.168.0.102", 6);//6
        Server s4 = new Server("192.168.0.103", 4);//4
        Server s5 = new Server("192.168.0.104", 1);//1
        serverList = new ArrayList<Server>();
        serverList.add(s1);
        serverList.add(s2);
        serverList.add(s3);
        serverList.add(s4);
        serverList.add(s5);

        currentIndex = -1;
        currentWeight = 0;
        serverCount = serverList.size();
        maxWeight = getMaxWeightForServers(serverList);
        gcdWeight = getGCDForServers(serverList);
    }


    public static void main(String[] args) {
        WeightedRoundRobin obj = new WeightedRoundRobin();
        obj.init();

        Map<String,Integer> countResult = new HashMap<String,Integer>();

        for (int i = 0; i < 1000; i++) {
            Server s = obj.GetServer();
            String log = "ip:"+s.ip+";weight:"+s.weight;
            if(countResult.containsKey(log)){
                countResult.put(log,countResult.get(log)+1);
            }else{
                countResult.put(log,1);
            }
            System.out.println(log);
        }

        for(Entry<String, Integer> map : countResult.entrySet()){
            System.out.println("server "+map.getKey()+" count： "+map.getValue());
        }
    }

}
