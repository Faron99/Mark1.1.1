/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;


@ManagedBean
@SessionScoped
public class UploaderBB implements Serializable{

 private UploadedFile file;
 private  String imagen;
 private String ruta = ("C:\\Users\\mmercadoco\\Documents\\NetBeansProjects\\MARK3\\web\\imagenes\\");
 
 
 private String name= file.getFileName();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

 
    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }
 
  public void upload() {
        if (file != null) {
            //FacesMessage msg = new FacesMessage("Succesful", file.getFileName() + "is uploaded");
            //FacesContext.getCurrentInstance().addMessage(null, msg);
            try {
                guardarImagen(file.getFileName(), file.getInputstream());
                System.out.println(file.getFileName());
                //  RequestContext.getCurrentInstance().update("formadatos:tbw1");
                if (file.getFileName().equals("")) {
                    System.out.println("no img");
                } else {
                    
                    RequestContext.getCurrentInstance().update("formadatos:tbw1");
                    RequestContext.getCurrentInstance().closeDialog(null);
                }
            } catch (IOException ex) {
                //Logger.getLogger(Image.class.getName()).log(Level.SEVERE,null,ex));
            }
        }
    }

    public void guardarImagen(String nombre, InputStream in) {
    
       
        try {
         
            OutputStream out = new FileOutputStream(new File(ruta + nombre));

            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }

            in.close();
            out.flush();
            out.close();
            System.out.println("Archivo Guardado: "+ruta+nombre);
               System.out.println("Otro nombre: "+name);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

 
public void handleFileUpload(FileUploadEvent event) {

try {

File targetFolder = new File("C:\\Users\\mmercadoco\\Documents\\NetBeansProjects\\MARK3\\web\\imagenes");

InputStream inputStream = event.getFile().getInputstream();
OutputStream out = new FileOutputStream(new File(targetFolder,
event.getFile().getFileName()));

int read = 0;
byte[] bytes = new byte[1024];
while ((read = inputStream.read(bytes)) != -1) {
out.write(bytes, 0, read);
}

inputStream.close();
out.flush();
out.close();

} catch (IOException e) {

e.printStackTrace();

}

}

}