package com.geek.study.jvm;

import java.lang.reflect.Method;
import java.util.Base64;

/**
 * @Author: siyan.liu
 * @Date: 2021/9/19
 */
public class CustomClassloader extends ClassLoader {
    public static void main(String[] args) {
        try {
            /**
             * hello.java
             *
             package com.geek.study.jvm;

             public class Hello {

                 public void hello(){
                    System.out.println("Hello, classLoader!");
                 }

             }
             */
            Object helloObj = new CustomClassloader().findClass("com.geek.study.jvm.Hello").newInstance();
            Method method = helloObj.getClass().getMethod("hello");
            method.invoke(helloObj);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException{
        String base64 = "yv66vgAAADQAHAoABgAOCQAPABAIABEKABIAEwcAFAcAFQEABjxpbml0PgEAAygpVgEABENvZGUBAA9MaW5lTnVtYmVyVGFibGUBAAVoZWxsbwEAClNvdXJjZUZpbGUBAApIZWxsby5qYXZhDAAHAAgHABYMABcAGAEAE0hlbGxvLCBjbGFzc0xvYWRlciEHABkMABoAGwEAGGNvbS9nZWVrL3N0dWR5L2p2bS9IZWxsbwEAEGphdmEvbGFuZy9PYmplY3QBABBqYXZhL2xhbmcvU3lzdGVtAQADb3V0AQAVTGphdmEvaW8vUHJpbnRTdHJlYW07AQATamF2YS9pby9QcmludFN0cmVhbQEAB3ByaW50bG4BABUoTGphdmEvbGFuZy9TdHJpbmc7KVYAIQAFAAYAAAAAAAIAAQAHAAgAAQAJAAAAHQABAAEAAAAFKrcAAbEAAAABAAoAAAAGAAEAAAAHAAEACwAIAAEACQAAACUAAgABAAAACbIAAhIDtgAEsQAAAAEACgAAAAoAAgAAAAkACAAKAAEADAAAAAIADQ==";
        byte[] helloByte = Base64.getDecoder().decode(base64);
        try {
           return defineClass(name,helloByte,0,helloByte.length);
        } catch (ClassFormatError classFormatError) {
            classFormatError.printStackTrace();
        }
        return null;
    }

}
