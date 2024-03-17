package com.weeklyproblems.weekly_2022_1_2;

/**
 * @Author: zhaomengyi
 * @Date: 2024/3/11 11:44
 * @Description:
 */
public class Code03_CovertBinaryAndHex {
    public static String convert(String num) {
        if (num == null || num.length() == 0 || num.length() > 6) {
            return "NODATA";
        }
        int n = Integer.valueOf(num);
        if (n < -32768 || n > 32768) {
            return "NODATA";
        }
        int info = (n < 0 ? (1 << 15) : 0 | (n & 65535)); //65535前面15是0，后面15是1
        StringBuilder sb = new StringBuilder();
        for (int i = 15; i >= 0; i--) {
            sb.append((info & (1 << i)) != 0 ? '1' : '0');
        }
        sb.append(",0x");
        for (int i = 12; i >= 0; i -= 4) {
            int cur = (info & (15 << i)) >> i;
            if (cur < 10) {
                sb.append(cur);
            } else {
                switch (cur) {
                    case 10:
                        sb.append('a');
                        break;
                    case 11:
                        sb.append('b');
                        break;
                    case 12:
                        sb.append('c');
                        break;
                    case 13:
                        sb.append('d');
                        break;
                    case 14:
                        sb.append('e');
                        break;
                    default:
                        sb.append('f');
                }
            }
        }
        return sb.toString();
    }
}
