

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashSet;
import java.util.Set;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.yc.utils.files.PropertiesUtil;
  
public class ZipCompressor {     
    static final int BUFFER = 8192;     
    
    private File zipFile;     
      
    public ZipCompressor(String pathName) {     
        zipFile = new File(pathName);     
    }     
    public void compress(String zipRootPath,String... pathName) {   
        ZipOutputStream out = null;     
        try {    
            FileOutputStream fileOutputStream = new FileOutputStream(zipFile);     
            CheckedOutputStream cos = new CheckedOutputStream(fileOutputStream,     
                    new CRC32());     
            out = new ZipOutputStream(cos);     
            
            Set<String> sets = new HashSet<String>();
            String except = PropertiesUtil.getPropertiesKey(zipRootPath+"jarCompiler.properties", "except");
            String[] excepts = except.split(",");
            for(String e : excepts){
            	sets.add(e);
            }
            
            String basedir = "";   
            for (int i=0;i<pathName.length;i++){  
                compress(new File(pathName[i]), out, basedir,sets);     
            }  
            out.close();    
        } catch (Exception e) {     
            throw new RuntimeException(e);     
        }   
    }     
    public void compress(String zipRootPath,String srcPathName) {     
        File file = new File(srcPathName);     
        if (!file.exists())     
            throw new RuntimeException(srcPathName + "不存在！");     
        try {     
            FileOutputStream fileOutputStream = new FileOutputStream(zipFile);     
            CheckedOutputStream cos = new CheckedOutputStream(fileOutputStream,     
                    new CRC32());     
            ZipOutputStream out = new ZipOutputStream(cos);     
            String basedir = "";     
            
            Set<String> sets = new HashSet<String>();
            String except = PropertiesUtil.getPropertiesKey(zipRootPath+"jarCompiler.properties", "except");
            String[] excepts = except.split(",");
            for(String e : excepts){
            	sets.add(e);
            }
            
            compress(file, out, basedir,sets);     
            out.close();     
        } catch (Exception e) {     
            throw new RuntimeException(e);     
        }     
    }     
    
    private void compress(File file, ZipOutputStream out, String basedir,Set<String> excepts) {     
        /* 判断是目录还是文件 */    
        if (file.isDirectory()) {     
            System.out.println("压缩：" + basedir + file.getName());     
            this.compressDirectory(file, out, basedir,excepts);     
        } else {     
            System.out.println("压缩：" + basedir + file.getName());     
            this.compressFile(file, out, basedir,excepts);     
        }     
    }     
    
    /** 压缩一个目录 */    
    private void compressDirectory(File dir, ZipOutputStream out, String basedir,Set<String> excepts) {     
        if (!dir.exists())     
            return;     
    
        File[] files = dir.listFiles();     
        for (int i = 0; i < files.length; i++) {     
            /* 递归 */    
            compress(files[i], out, basedir + dir.getName() + "/",excepts);     
        }     
    }     
    
    /** 压缩一个文件 */    
    private void compressFile(File file, ZipOutputStream out, String basedir,Set<String> excepts) {     
        if (!file.exists()) {     
            return;     
        }     
        if(excepts.contains(file.getName())){
        	System.out.println("except jar : "+file.getName());
        	return;
        }
        try {     
            BufferedInputStream bis = new BufferedInputStream(     
                    new FileInputStream(file));     
            ZipEntry entry = new ZipEntry(basedir + file.getName());     
            out.putNextEntry(entry);     
            int count;     
            byte data[] = new byte[BUFFER];     
            while ((count = bis.read(data, 0, BUFFER)) != -1) {     
                out.write(data, 0, count);     
            }     
            bis.close();     
        } catch (Exception e) {     
            throw new RuntimeException(e);     
        }     
    }     
   public static void main(String[] args) {     
//        ZipCompressor zc = new ZipCompressor("E:/resource/resource.zip");     
//        zc.compress("E:/resource/js","E:/resource/css","E:/resource/images"); //指定几个文件夹   
	   ZipCompressor zc = new ZipCompressor("D:/itms/zipdemo.zip"); 
	   zc.compress("D:/itms/","D:/itms/zipdemo");
    }  
}