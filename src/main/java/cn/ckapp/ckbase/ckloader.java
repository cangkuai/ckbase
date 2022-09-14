package cn.ckapp.ckbase;

import com.mojang.logging.LogUtils;
import org.slf4j.Logger;

import java.io.*;
import java.util.HashMap;
import java.util.List;

public class ckloader {
    private static final Logger LOGGER = LogUtils.getLogger();
    public static void opening(String dir){
        File file1=new File(dir);
        if(!file1.exists()) {
            file1.mkdir();
            LOGGER.info("create dir successfully");
        }
        File file2 = new File(dir+"/homes.txt");
        if (!file2.exists()){
            try {
                file2.createNewFile();

                LOGGER.info("create home database successfully");
                } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        File file3 = new File(dir+"/warps.txt");
        if (!file3.exists()){
            try {
                file3.createNewFile();

                LOGGER.info("create warp database successfully");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        File file5 = new File(dir+"/backs.txt");
        if (!file5.exists()){
            try {
                file5.createNewFile();

                LOGGER.info("create back database successfully");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        File file6 = new File(dir+"/sets.txt");
        if (!file6.exists()){
            try {
                file6.createNewFile();
                FileWriter writer = new FileWriter(file6);
                writer.write("language=en-us");
                writer.flush();
                writer.close();
                LOGGER.info("create set database successfully");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        File file4=new File(dir+"/language");
        if(!file4.exists()) {
            file4.mkdir();
            LOGGER.info("create language dir successfully");
        }
        File file7 = new File(dir+"/language/en-us.txt");
        if (!file7.exists()){
            try {
                file7.createNewFile();
                FileWriter writer = new FileWriter(file7);
                writer.write(
                        "An unknown error has occurred\n" +
                        "Send successfully, please wait for the other player to agree\n" +
                        " want to tp you , please send /tpaccept to accept.\n" +
                        "Player is not online\n" +
                        "nobody want to tp you\n" +
                        "tp successfully\n" +
                        "this command need op`s permission\n" +
                        "you haven't set up your home.\n" +
                        "set successfully\n" +
                        "unknown warp name\n" +
                        "no records to return\n" +
                        "change successfully"
                );
                writer.flush();
                writer.close();
                LOGGER.info("create en-us successfully");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        File file8 = new File(dir+"/language/zh-cn.txt");
        if (!file8.exists()){
            try {
                file8.createNewFile();
                FileWriter writer = new FileWriter(file8);
                writer.write(
                        "发生了一个未知错误\n" +
                        "邀请发送成功，请等待对方玩家同意\n" +
                        "想要tp你，请输入/tpaccept同意这个邀请\n" +
                        "玩家不在线\n" +
                        "没有玩家想要tp你\n" +
                        "传送成功\n" +
                        "这个指令需要op权限\n" +
                        "你还没有设置家\n" +
                        "设置成功\n" +
                        "未知的坐标名\n" +
                        "没有可以返回的记录\n" +
                        "切换成功"
                );
                writer.flush();
                writer.close();
                LOGGER.info("create zh-cn successfully");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static HashMap<String,String> getsets(String dir){
        HashMap<String,String> sets = new HashMap<>();
        File setfile=new File(dir+"/sets.txt");
        try {
            String ls;
            BufferedReader br = new BufferedReader(new FileReader(setfile));
            while ((ls= br.readLine()) != null) {
                String[] lslist= ls.split("=");
                sets.put(lslist[0],lslist[1]);
            }
            return sets;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static String[] getlanguage(String type,String dir){
        File language=new File(dir+"/language/"+type+".txt");
        try {
            String[] languages=new String[12];
            String ls;
            int i=0;
            BufferedReader br = new BufferedReader(new FileReader(language));
            while ((ls= br.readLine()) != null) {
                languages[i]=ls;
                i++;
            }
            return languages;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
