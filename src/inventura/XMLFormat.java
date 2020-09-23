package inventura;

import javafx.scene.transform.Transform;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class XMLFormat {
    private ArrayList<Proizvod> proizvodi = new ArrayList<>();
    private ArrayList<Mjesto> mjesta = new ArrayList<>();
    private ArrayList<Narudzba> narudzbe = new ArrayList<Narudzba>();
    private Proizvod noviProizvod = new Proizvod();
    public Proizvod proizvod = new Proizvod();


    public void ucitaj(File file) throws Exception {
        Document dom;

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        if(file!=null)
        try {
        //Ucitavanje xml fajla u program

            DocumentBuilder db = dbf.newDocumentBuilder();

            try {
                dom = db.parse(file);
            }catch(SAXException | IOException e){
                throw new Exception("Pokuso");
            }
            Proizvod pomocni=new Proizvod();
            dom.getDocumentElement().normalize();
            Element doc = dom.getDocumentElement();
            Element root = dom.getDocumentElement();
            //citanje fajla i smjestanje u proizvod
            if(!root.getTagName().equals("inventura")) System.out.print("Malo ti je falilo");
            NodeList nodeList = dom.getElementsByTagName("proizvod");
            for(int i=0;i<nodeList.getLength();i++) {
                Node node = nodeList.item(i);
                Element element = (Element) node;
                pomocni.setId(Integer.parseInt(getTextValue(String.valueOf(pomocni.id), element, "id")));
                pomocni.setNaziv(getTextValue(pomocni.naziv, element, "naziv"));
                pomocni.setKategorija(getTextValue(pomocni.kategorija, element, "kategorija"));
                pomocni.setDatum(getTextValue(pomocni.datum, element, "datum"));
                pomocni.setMjesto(getTextValue(pomocni.mjesto, element, "mjesto"));
                pomocni.setMjesto_id(Integer.parseInt(getTextValue(String.valueOf(pomocni.mjesto_id), element, "mjesto_id")));
                pomocni.setKolicina_proizvoda(Integer.parseInt(getTextValue(String.valueOf(pomocni.kolicina_proizvoda), element, "kolicina_proizvoda")));
                proizvodi.add(pomocni);
                pomocni= new Proizvod();
            }
            //citanje mjesta i smjestanje u mjesto
            Mjesto pomocnoMjesto = new Mjesto();
            nodeList=dom.getElementsByTagName("mjesto");
            for(int i=0;i<nodeList.getLength();i++){
                Node node = nodeList.item(i);
                Element element = (Element) node;
                pomocnoMjesto.setId(Integer.parseInt(getTextValue(String.valueOf(pomocni.id),element,"id")));
                pomocnoMjesto.setNaziv(getTextValue(pomocnoMjesto.naziv,element,"naziv"));
                pomocnoMjesto.setLokacija(getTextValue(pomocnoMjesto.lokacija,element,"lokacija"));
                pomocnoMjesto.setOpis(getTextValue(pomocnoMjesto.opis,element,"opis"));
                mjesta.add(pomocnoMjesto);
                pomocnoMjesto=new Mjesto();
            }
            //citanje narudzbe i smjestanje u narudzbe
            Narudzba pomocnaNarudzba = new Narudzba();
            nodeList=dom.getElementsByTagName("narudzba");
            for(int i=0;i<nodeList.getLength();i++){
                Node node = nodeList.item(i);
                Element element = (Element) node;
                pomocnaNarudzba.setId(Integer.parseInt(getTextValue(String.valueOf(pomocnaNarudzba.id),element,"id")));
                pomocnaNarudzba.setProizvod(getTextValue(pomocnaNarudzba.proizvod,element,"proizvod"));
                pomocnaNarudzba.setVrsta(getTextValue(pomocnaNarudzba.vrsta,element,"vrsta"));
                pomocnaNarudzba.setOpis(getTextValue(pomocnaNarudzba.opis,element,"opis"));
                pomocnaNarudzba.setDatum(getTextValue(pomocnaNarudzba.datum,element,"datum"));
                pomocnaNarudzba.setProizvod_id(Integer.parseInt(getTextValue(String.valueOf(pomocnaNarudzba.proizvod_id),element,"proizvod_id")));
                pomocnaNarudzba.setMjesto_id(Integer.parseInt(getTextValue(String.valueOf(pomocnaNarudzba.mjesto_id),element,"mjesto_id")));
                narudzbe.add(pomocnaNarudzba);
                pomocnaNarudzba = new Narudzba();
            }
        } catch (ParserConfigurationException pce) {
            System.out.println(pce.getMessage());
        } catch (SAXException se) {
            System.out.println(se.getMessage());
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }




    }

    public void zapisi(File file)  {
        Document dom;
        Element e = null;

//zapisivanje xml fajla
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {

            DocumentBuilder db = dbf.newDocumentBuilder();

            dom = db.newDocument();
            Element rootEle = dom.createElement("inventura");
            //zapisivanje svih proizvoda
            for(int i=0;i<proizvodi.size();i++) {
                Element eEproizvod = dom.createElement("proizvod");

                e = dom.createElement("id");
                e.appendChild(dom.createTextNode(String.valueOf(proizvodi.get(i).getId())));
                eEproizvod.appendChild(e);

                e = dom.createElement("naziv");
                e.appendChild(dom.createTextNode(proizvodi.get(i).getNaziv()));
                eEproizvod.appendChild(e);

                e = dom.createElement("kategorija");
                e.appendChild(dom.createTextNode(proizvodi.get(i).getKategorija()));
                eEproizvod.appendChild(e);

                e = dom.createElement("datum");
                e.appendChild(dom.createTextNode(proizvodi.get(i).getDatum()));
                eEproizvod.appendChild(e);

                e = dom.createElement("mjesto");
                e.appendChild(dom.createTextNode(proizvodi.get(i).getMjesto()));
                eEproizvod.appendChild(e);

                e = dom.createElement("mjesto_id");
                e.appendChild(dom.createTextNode(String.valueOf(proizvodi.get(i).getMjesto_id())));
                eEproizvod.appendChild(e);

                e = dom.createElement("kolicina_proizvoda");
                e.appendChild(dom.createTextNode(String.valueOf(proizvodi.get(i).getKolicina_proizvoda())));
                eEproizvod.appendChild(e);
                rootEle.appendChild(eEproizvod);
            }
            //zapisivanje svih mjesta
            for(int i=0;i<mjesta.size();i++){
                Element eMjesto = dom.createElement("mjesto");

                e = dom.createElement("id");
                e.appendChild(dom.createTextNode(String.valueOf(mjesta.get(i).getId())));
                eMjesto.appendChild(e);

                e = dom.createElement("naziv");
                e.appendChild(dom.createTextNode(mjesta.get(i).getNaziv()));
                eMjesto.appendChild(e);

                e = dom.createElement("lokacija");
                e.appendChild(dom.createTextNode(mjesta.get(i).getLokacija()));
                eMjesto.appendChild(e);

                e = dom.createElement("opis");
                e.appendChild(dom.createTextNode(mjesta.get(i).getOpis()));
                eMjesto.appendChild(e);

                rootEle.appendChild(eMjesto);
            }
            //zapisivanje svih narudzbi
            for(int i=0;i<narudzbe.size();i++){
                Element eNarudzba = dom.createElement("narudzba");

                e = dom.createElement("id");
                e.appendChild(dom.createTextNode(String.valueOf(narudzbe.get(i).getId())));
                eNarudzba.appendChild(e);

                e = dom.createElement("proizvod");
                e.appendChild(dom.createTextNode(narudzbe.get(i).getProizvod()));
                eNarudzba.appendChild(e);

                e = dom.createElement("vrsta");
                e.appendChild(dom.createTextNode(narudzbe.get(i).getVrsta()));
                eNarudzba.appendChild(e);

                e = dom.createElement("opis");
                e.appendChild(dom.createTextNode(narudzbe.get(i).getOpis()));
                eNarudzba.appendChild(e);

                e = dom.createElement("datum");
                e.appendChild(dom.createTextNode(narudzbe.get(i).getDatum()));
                eNarudzba.appendChild(e);

                e = dom.createElement("proizvod_id");
                e.appendChild(dom.createTextNode(String.valueOf(narudzbe.get(i).getProizvod_id())));
                eNarudzba.appendChild(e);

                e = dom.createElement("mjesto_id");
                e.appendChild(dom.createTextNode(String.valueOf(narudzbe.get(i).getMjesto_id())));
                eNarudzba.appendChild(e);
                rootEle.appendChild(eNarudzba);
            }
            dom.appendChild(rootEle);
            try {
                //zapisivanje navedenog u fajl
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer tr = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(dom);
                StreamResult streamResult = new StreamResult(file);
                tr.transform(source,streamResult);

            } catch (TransformerException te) {
                System.out.println(te.getMessage());
            }
        } catch (ParserConfigurationException pce) {
           pce.printStackTrace();
        }
    }
    private String getTextValue(String def, Element doc, String tag) {
        String value = def;
        NodeList nl;
        nl = doc.getElementsByTagName(tag);
        if (nl.getLength() > 0 && nl.item(0).hasChildNodes()) {
            value = nl.item(0).getFirstChild().getNodeValue();
        }
        return value;
    }

    public ArrayList<Mjesto> getMjesta() {
        return mjesta;
    }

    public void setMjesta(ArrayList<Mjesto> mjesta) {
        this.mjesta = mjesta;
    }

    public ArrayList<Narudzba> getNarudzbe() {
        return narudzbe;
    }

    public void setNarudzbe(ArrayList<Narudzba> narudzbe) {
        this.narudzbe = narudzbe;
    }

    public ArrayList<Proizvod> getProizvodi() {
        return proizvodi;
    }

    public void setProizvodi(ArrayList<Proizvod> proizvodi) {
        this.proizvodi = proizvodi;
    }
    public void setProizvod(Proizvod proizvod){
        this.proizvod=proizvod;
    }

    public Proizvod getProizvod(){ return proizvod; }
}
