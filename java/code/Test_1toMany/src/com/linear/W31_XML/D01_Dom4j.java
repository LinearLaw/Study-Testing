package com.linear.W31_XML;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/*

    31、XML，
    有两种解析方式
        - DOM
        - SAX
    常用解析器
        - dom4j
        - Jaxp：可以解析dom和sax
        - jsoup
        - jdom : 和dom4j类似

 */
public class D01_Dom4j {

    public static void main(String[] args){
        run01();


    }

    public static void run01(){
        SAXReader reader = new SAXReader();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("./W31_books.xml");
            Document document = reader.read(fis);

            Element root = document.getRootElement();
            List<Element> elems = root.elements();

            for (Element e : elems){
                Attribute bid = e.attribute("bid");
                System.out.println(bid.getName() + " : " + bid.getValue());

                List<Element> childElements = e.elements();
                for (Element ce: childElements){
                    System.out.println(ce.getName() + " : " + ce.getText());
                }

                System.out.println("------");
            }
        } catch (FileNotFoundException e) {
            System.out.println("【File not found】");
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            if(fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
