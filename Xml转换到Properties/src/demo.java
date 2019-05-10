
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.Properties;

import org.dom4j.*;
import org.dom4j.io.SAXReader;

/**
 * 
 * @author ����117 2017/6/17/15:17
 *
 */

public class demo {
	public static void main(String[] args) {
		SAXReader reader = new SAXReader();
		try {
			Document doc = reader.read("src/��ҵ1.xml");
			Element root = doc.getRootElement();
			// int count=0;
			// for(Iterator i = root.elementIterator();i.hasNext();){
			// Element element=(Element) i.next();
			// System.out.println(element.getName()+count++);
			//
			// for(Iterator k = element.attributeIterator();k.hasNext();){
			// Attribute att = (Attribute)k.next();
			// System.out.println(att.getText());
			// }
			//
			// for(Iterator j = element.elementIterator();j.hasNext();){
			// Element element2 = (Element) j.next();
			// System.out.println(element2.getName());
			// }
			// }

			// ����properties
			Properties pro = new Properties();
			FileOutputStream out = new FileOutputStream("src/reslut.properties");
			ConverIntoPro(root, "",pro,out);
			pro.store(out, null);
			out.close();
			// Do_travels(root,"");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ģ�������
	private static void Do_travels(Element ele, String fatherName) {
		if (ele != null) {
			// ����ڵ���
			String nodeName = ele.getName();
			// �ȶ�����ڵ�����Ա���һ��
			for (Iterator it = ele.attributeIterator(); it.hasNext();) {
				Attribute att = (Attribute) it.next();
				String info = nodeName + "." + att.getName() + "=" + att.getText();
				System.out.println(fatherName + info);
			}
			// ������ڵ�ֵ
			if (!ele.getTextTrim().equals("")) {
				System.out.println(fatherName + nodeName + "=" + ele.getTextTrim());
			}
			// System.out.println();
			// �ٱ����ӽڵ�����
			fatherName += nodeName + ".";
			for (Iterator it = ele.elementIterator(); it.hasNext();) {
				Do_travels((Element) it.next(), fatherName);
			}
		}
	}

	// ת������

	private static void ConverIntoPro(Element ele, String fatherName,Properties pro,FileOutputStream out) {
		if (ele != null) {
			
			try {
				
				// ����
				// pro.setProperty("test", "hello_World");
				// pro.store(out, "test");
				// out.close();

				// ����ڵ���
				String nodeName = ele.getName();
				
				if(!nodeName.equals("root")){
					// �ȶ�����ڵ�����Ա���һ��
					for (Iterator it = ele.attributeIterator(); it.hasNext();) {
						Attribute att = (Attribute) it.next();
						String info = fatherName+nodeName + "." + att.getName();
						pro.setProperty(info, att.getText());

					}
					// ������ڵ�ֵ
					if (!ele.getTextTrim().equals("")) {
						pro.setProperty(fatherName + nodeName, ele.getTextTrim());
					}
					fatherName += nodeName + ".";
				}
				
				
				// �ٱ����ӽڵ�����
				
				for (Iterator it = ele.elementIterator(); it.hasNext();) {
					ConverIntoPro((Element) it.next(), fatherName,pro,out);
				}


			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

}
