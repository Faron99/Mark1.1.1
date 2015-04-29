/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upload;

import java.awt.Rectangle;
import java.awt.print.PageFormat;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.util.PDFTextStripperByArea;

/**
 *
 * @author mmercadoco
 */

@ManagedBean
@SessionScoped




public class LeerPdf {
    private String contenido;

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
    
    
    
    public void inicia(int a) {
       
        //poner if o switch
       if(a==1){
        LeerPdf leerPDF =new LeerPdf();
       leerPDF.lecturaPDF();
    } 
       else {
           contenido=null;
    }}
    
    public void lecturaPDF(){
        
        
        String ln = System.getProperty("line.separator"); 
        File dir = new File("C:\\Users\\mmercadoco\\Desktop\\imagenes");//CREO UN OBJETO CON TODOS LOS ARCHIVOS QUE CONTIENE LA CARPETA QUE CONTIENE LOS PDFS.
        String[] ficheros = dir.list();//ARREGLO QUE ALMACENARÁ TODOS LOS NOMBRES DE LOS ARCHIVOS QUE ESTAN DENTRO DEL OBJETO.
        
        if (ficheros == null)//EXCEPCION
              System.out.println("No hay archivos en la carpeta especificada");
        else { 
          for (int x=0;x<ficheros.length;x++){//RECORREMOS EL ARREGLO CON LOS NOMBRES DE ARCHIVO
            String ruta=new String();//VARIABLE QUE DETERMINARA LA RUTA DEL ARCHIVO A LEER.
            ruta=("C:\\Users\\mmercadoco\\Desktop\\imagenes\\"+ficheros[x]); //SE ALMACENA LA RUTA DEL ARCHIVO A LEER. 
            System.out.println("ruta"+ ruta);
              try {
                  PDDocument pd = PDDocument.load(ruta); //CARGAR EL PDF
                  List l = pd.getDocumentCatalog().getAllPages();//NUMERO LAS PAGINAS DEL ARCHIVO
                  System.out.println("Paginas: " +l);
                  Object[] obj = l.toArray();//METO EN UN OBJETO LA LISTA DE PAGINAS PARA MANIPULARLA
                  
                  PDPage page = (PDPage) obj[9];//PAGE ES LA PAGINA 1 DE LA QUE CONSTA EL ARCHIVO
                  PageFormat pageFormat = pd.getPageFormat(0);//PROPIEDADES DE LA PAGINA (FORMATO)
                  Double d1 = new Double(pageFormat.getHeight());//ALTO
                  Double d2 = new Double(pageFormat.getWidth());//ANCHO
                  int width = d1.intValue();//ANCHO
                  int eigth=1024;//ALTO
                  
                  PDFTextStripperByArea stripper = new PDFTextStripperByArea();//COMPONENTE PARA ACCESO AL TEXTO
                  Rectangle rect = new Rectangle(0, 0, width, eigth);//DEFNIR AREA DONDE SE BUSCARA EL TEXTO
                  stripper.addRegion("area1", rect);//REGISTRAMOS LA REGION CON UN NOMBRE
                  stripper.extractRegions(page);//EXTRAE TEXTO DEL AREA
                  System.out.println("Texto del area: " +page);
                  contenido = new String();//CONTENIDO = A LO QUE CONTENGA EL AREA O REGION
                  contenido=(stripper.getTextForRegion("area1"));
                      System.out.println("rect: " +rect);         
                      System.out.println("stripper: " +stripper.getTextForRegion("area1"));   
                  File archivo=new File(ruta+".txt");//CREAMOS ARCHIVO CON NOMBRE ORIGINAL PERO EN TXT
                  System.out.println("Nuevo nombre de txt: " +ficheros[x]);
                  BufferedWriter writer = new BufferedWriter(new FileWriter(archivo));//CREAMOS EL ESCRITOR
                  //writer.write(ruta);//IMPRIMIMOS LA RUTA
                  writer.write(contenido);//IMPRIMIMOS EL CONTENIDO
                  System.out.println("Contenido: " +contenido);
                  writer.close();//CERRAMOS EL ESCRITOR
                                   
                  pd.close();//CERRAMOS OBJETO ACROBAT
              } catch (IOException e) {
                  if(e.toString()!=null){
                    File archivo=new File("dañado_"+ficheros[x]+".txt");//SEPARA LOS DAÑADOS
                  }
                  System.out.println("Archivo dañado "+ficheros[x]);// INDICA EN CONSOLA CUALES SON LOS DAÑADOS
                  e.printStackTrace();
              }//CATCH
          }//FOR
        }//ELSE
    }//LECTURAPDF()
}//CLASS