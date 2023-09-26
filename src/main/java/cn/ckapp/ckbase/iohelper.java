package cn.ckapp.ckbase;

import com.mojang.logging.LogUtils;
import org.slf4j.Logger;

import java.io.*;
import java.util.HashMap;
import java.util.List;

public class iohelper   {
    public static String dir;
    private static HashMap<String, Double[]>  homes=new HashMap<String, Double[]>();
    private static HashMap<String, Double[]>  warps=new HashMap<String, Double[]>();
    private static HashMap<String, Double[]>  backs=new HashMap<String, Double[]>();
    private static final Logger LOGGER = LogUtils.getLogger();
    public void setdir(String dirs){
        dir=dirs;
    }
    public void read(){
        File homefile=new File(dir+"/homes.txt");
        try {
            String ls;
            BufferedReader br = new BufferedReader(new FileReader(homefile));
            while ((ls= br.readLine()) != null) {
                String[] lslist= ls.split(",");
                homes.put(lslist[0], new Double[]{Double.valueOf(lslist[1]),Double.valueOf(lslist[2]),Double.valueOf(lslist[3])});
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        File warpfile=new File(dir+"/warps.txt");
        try {
            String ls;
            BufferedReader br = new BufferedReader(new FileReader(warpfile));
            while ((ls= br.readLine()) != null) {
                String[] lslist= ls.split(",");
                warps.put(lslist[0], new Double[]{Double.valueOf(lslist[1]),Double.valueOf(lslist[2]),Double.valueOf(lslist[3])});
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        File backfile=new File(dir+"/backs.txt");
        try {
            String ls;
            BufferedReader br = new BufferedReader(new FileReader(backfile));
            while ((ls= br.readLine()) != null) {
                String[] lslist= ls.split(",");
                backs.put(lslist[0], new Double[]{Double.valueOf(lslist[1]),Double.valueOf(lslist[2]),Double.valueOf(lslist[3])});
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public Double[] getdata(String type,String names){
        if(type.equals("home")){
            if(homes.size()==0){
                read();
            }
            return homes.get(names);
        }else if (type.equals("warp")) {
            if (warps.size()==0){
                read();
            }
            return warps.get(names);
        } else if (type.equals("back")) {
            if (backs.size()==0){
                read();
            }
            return backs.get(names);
        }
        return null;
    }
    public void setdata(String type,String name,Double[] data){
        if(type.equals("home")){
            homes.put(name,data);
        } else if (type.equals("warp")) {
            warps.put(name,data);
        }else if(type.equals("back")){
            backs.put(name,data);
        }
    }
    public void deldata(String type,String name){
        if(type.equals("home")){
            homes.remove(name);
        } else if (type.equals("warp")) {
            warps.remove(name);
        }else if(type.equals("back")){
            backs.remove(name);
        }
    }
    public void savedata() {
        try {
            File file=new File(dir+"/homes.txt");
            FileWriter writer = new FileWriter(file);
            homes.forEach((key, value) -> {
                try {
                    writer.write(key+","+String.valueOf(value[0])+","+String.valueOf(value[1])+","+String.valueOf(value[2])+"\n");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            writer.flush();
            writer.close();
            File file1=new File(dir+"/warps.txt");
            FileWriter writer1 = new FileWriter(file1);
            warps.forEach((key, value) -> {
                try {
                    writer1.write(key+","+String.valueOf(value[0])+","+String.valueOf(value[1])+","+String.valueOf(value[2])+"\n");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            writer1.flush();
            writer1.close();
            File file2=new File(dir+"/backs.txt");
            FileWriter writer2 = new FileWriter(file2);
            backs.forEach((key, value) -> {
                try {
                    writer2.write(key+","+String.valueOf(value[0])+","+String.valueOf(value[1])+","+String.valueOf(value[2])+"\n");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            writer2.flush();
            writer2.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}