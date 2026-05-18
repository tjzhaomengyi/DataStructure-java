package com.niuke.BM101.dp;

public class BM85_ValidIPV4V6 {
    public String isValidIp(String IP){
        return isValidV4(IP) ? "IPv4" : (validIPv6(IP) ? "IPv6" : "Neither");
    }

    private boolean isValidV4(String IP){
        String[] strs = IP.split("\\.", -1);
        // 1、分割长度不为4
        if(strs.length != 4){
            return false;
        }
        // 2、分段字符串长度大于1，但是以0开头
        for(String str : strs){
            if(str.length() > 1 && str.startsWith("0")){
                return false;
            }
            try{
                // 3、分段字符串不在255范围内
                int val = Integer.parseInt(str);
                if(!(val >= 0 && val <= 255)){
                    return false;
                }
            } catch (NumberFormatException numberFormatException){ //接一下解析错误的所有错误
                return false;
            }
        }
        return true;
    }

    private boolean validIPv6(String IP){
        String[] strs = IP.split(":", -1);
        // 1、分段长度不为8
        if(strs.length != 8){
            return false;
        }
        for(String str : strs){
            // 2、如果分段字符长度大于4，或者为空，就是非法的
            if(str.length() > 4 || str.length() == 0){
                return false;
            }
            try {
                int val = Integer.parseInt(str, 16); //解析十六禁制地址是否合法
            } catch (NumberFormatException numberFormatException){
                return false;
            }
        }
        return true;
    }
}
