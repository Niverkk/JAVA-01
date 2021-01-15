package info.niverkk.jvm;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * 自定义一个 Classloader，加载一个 Hello.xlass 文件，执行 hello 方法，
 * 此文件内容是一个 Hello.class 文件所有字节（x=255-x）处理后的文件。
 *
 * @author JKXAING on 2021/1/15
 */
public class MyClassLoader extends ClassLoader {

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] bytes = new byte[0];
        try {
            bytes = loadFile(name);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ClassNotFoundException("加载出错...");
        }

        return defineClass(name, bytes, 0, bytes.length);
    }

    private byte[] loadFile(String name) throws IOException {
        File file = new File("week01/resources/" + name + ".xlass");
        FileInputStream fis = new FileInputStream(file);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        int b = -1;
        while ((b = fis.read()) != -1) {
            bos.write(b);
        }
        return decode(bos);
    }

    private byte[] decode(ByteArrayOutputStream bos) {
        byte[] bytes = bos.toByteArray();
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) (255 - bytes[i]);
        }
        return bytes;
    }

    public static void main(String[] args) throws Exception {
        Class<?> helloClass = new MyClassLoader().loadClass("Hello");
        Method helloMethod = helloClass.getMethod("hello");
        Object o = helloClass.newInstance();

        //执行hello方法
        helloMethod.invoke(o);
    }
}
