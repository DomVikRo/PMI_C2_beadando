import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.FileOutputStream;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;


public class com {
    public static ArrayList<diakok> dak = new ArrayList<>();
    public static Scanner num = new Scanner(System.in);

    public static void ad(String nev, String jegyek) {
        diakok now = new diakok(nev, jegyek, atlag(jegyek));
        dak.add(now);
        main.valaszt();
    }

    public static String kiír() {
        String ki = new String();
        for (diakok I : dak) {
            ki += I.getName() + " " + I.getJegyek() + " ";
            if (I.getÁtment() == 1) {
                ki += "Átment\r\n";
            } else {
                ki += "Megbukott\r\n";
            }
        }
        return ki;
    }

    public static void update() {
        System.out.println("Kérem a módosítani kívánt diák nevét: ");
        String name=inputname();
        int i=0;
        int k=0;
        for (diakok I : dak) {
            if (I.getName().equals(name)) {
                k=i;
            }
            i++;
        }
        if(k>0){
            System.out.println("Kérem válasza ki mit szeretne módosítani: ");
            System.out.println("1.Diák neve.");
            System.out.println("2.jegy hozzáadás");
            System.out.println("3.jegyek újra írása");
            System.out.println("4.Átmenés státuszának megváltoztatása");
            System.out.print("A kívánt művelet számíát it írja be: ");
            int h= num.nextInt();
            if(h==1){
                System.out.print("\n Ön a diák nevének megváltoztatását választotta kérem az új nevet most írja be: ");
                String v=inputname();
                dak.get(k).setName(v);
            }else if(h==2){
                System.out.println("Kérem adja meg a hozzáadni kívánt jegyeket: ");
                String v=dak.get(k).getJegyek()+inputjegy();
                dak.get(k).setJegyek(v);
            }else if(h==3){
                System.out.println("Kérem adja meg az új jegyeket: ");
                String v=inputjegy();
                dak.get(k).setJegyek(v);
            }else if(h==4){
                System.out.println("Az átment státuszának megváltoztatásához kérem adja meg a kívánt értéket: \n 1 Átment \n 0 döntés alatt álló \n -1 megbukott");
                int v=num.nextInt();
                dak.get(k).setÁtment(v);
            }
        }else{
            System.out.println("Hibás név!");
        }

    }

    public static int atlag(String jegyek) {
        int ösz = 0;
        int szam = 0;
        double atlag = 0;
        String[] split = jegyek.split(",");
        for (int i = 0; i < split.length; i++) {
            ösz += Integer.parseInt(split[i]);
            szam += 1;
            atlag = ösz / szam;
        }


        if (atlag > 1.51) {
            return 1;
        } else {
            return 0;
        }

    }

    public static void delete(String name) {
        int i = 0;
        int k=0;
        for (diakok I : dak) {
            if (I.getName().equals(name)) {
                dak.remove(i);
                System.out.println("Sikeres törlés");
                k++;
            }
            i++;
        }
        if(k==0){
            System.out.println("Sikertelen törlés");
        }
        main.valaszt();
    }

    public static void save() {
        try {
            Document document = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder().newDocument();
            Element rootElement = document.createElement("users");
            document.appendChild(rootElement);

            for (diakok I:dak) {
                Element userElement = document.createElement("user");
                rootElement.appendChild(userElement);
                createChildElement(document, userElement, "name", I.getName());
                createChildElement(document, userElement, "jegyek",
                        String.valueOf(I.getJegyek()));
                String j=I.getÁtment()+"";
                createChildElement(document, userElement, "atment", j);

            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new FileOutputStream("src/dk.xml"));

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void beolvas() {
        try {
            DocumentBuilderFactory documentBuilderFactory
                    = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse("src/dk.xml");

            Element rootElement = document.getDocumentElement();
            NodeList childNodeList = rootElement.getChildNodes();
            Node node;
            for (int i = 0; i < childNodeList.getLength(); i++) {
                node = childNodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    NodeList childNodesOfDiakTag = node.getChildNodes();
                    String name = "", jegyek = "", atment = "";
                    for (int j = 0; j < childNodesOfDiakTag.getLength(); j++) {
                        Node childNodeOfDiakTag = childNodesOfDiakTag.item(j);
                        if (childNodeOfDiakTag.getNodeType() == Node.ELEMENT_NODE) {
                            switch (childNodeOfDiakTag.getNodeName()) {
                                case "name" -> name = childNodeOfDiakTag.getTextContent();
                                case "jegyek" -> jegyek = childNodeOfDiakTag.getTextContent();
                                case "atment" -> atment = childNodeOfDiakTag.getTextContent();
                            }
                        }
                    }
                    dak.add(new diakok(name, jegyek, Integer.parseInt(atment)));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void hozzaad() {
        System.out.println("Ön az elem hozzáadását választotta, kérem agya meg az elemeket a kívánt sorrendben.");
        System.out.print("\nKérem adja meg a tanuló nevét(az írás befejezését az enter lenyomásával teheti meg): ");
        String nev = inputname();
        String j = new String();
        int i = 1;
        System.out.print("\nKérem adja meg a tanuló jegyeit(minden számnak 1 és 5 között kell leni a jegyek beírásának befejezéséhez egy 0-ás adatot kell bevinni): ");
        j = inputjegy();
        ad(nev, j);

    }

    public static String inputname() {
        return num.nextLine();
    }

    public static String inputjegy() {
        String j = "";
        int i = 1;
        do {
            System.out.print("\n jegy: ");
            i = num.nextInt();
            if (i > 0 && i < 6) {
                j += i + ",";
            } else if (i == 0) {
                System.out.println("a jegyek bevitele sikeres volt");
            } else {
                System.out.println("A megadott érték nem megfelelő.");
            }
        } while (i != 0);
        return j;
    }
    private static void createChildElement(Document document, Element parent,
                                           String tagName, String text) {
        Element element = document.createElement(tagName);
        element.setTextContent(text);
        parent.appendChild(element);
    }
}
