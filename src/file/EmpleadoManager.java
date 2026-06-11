
package file;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Calendar;
import java.util.Date;

public class EmpleadoManager
{
    private RandomAccessFile rcods, remps; // 1 usage

    /*
    Formato:
    1- Codigo.emp
       int code;

    2- Empleado.emp
       int code;
       String name;
       double salary;
       long hdate
       long idate
    */

    public EmpleadoManager() {
        try {
            File mf = new File("company");
            mf.mkdir();
            rcods = new RandomAccessFile("company/codigo.emp", "rw");
            initCode();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    private void initCode() throws IOException{
        if(rcods.length()==0){
            rcods.seek(0);
            rcods.writeInt(1);
        }
    }
    
    private int getcode() throws IOException{
        rcods.seek(0);
        int codigo = rcods.readInt();
        
        rcods.seek(0);
        rcods.writeInt(codigo+1);
        
        return codigo;
    }
    
    public void addEmployee(String name, double salary) throws IOException{
        remps.seek(remps.length());
        int code = getcode();
        remps.writeInt(code);
        remps.writeUTF(name);
        remps.writeDouble(salary);
        remps.writeLong(Calendar.getInstance().getTimeInMillis());
        remps.writeLong(0);
        
    }
    
    private String employeeFolder(int code){
     return "company/empledao"+code;   
    }
    
    private RandomAccessFile salesFileFor(int code) throws IOException{
        String dirPadre=employeeFolder(code);
        int yearActual = Calendar.getInstance().get(Calendar.YEAR);
        String path = dirPadre="/ventas"+yearActual+".emp";
        return new RandomAccessFile(path,"rw");
        
    }
    
    private void createSalesFileFor(int code) throws IOException{
        RandomAccessFile ryear = salesFileFor(code);
        if(ryear.length()==0){
            for (int mes = 0; mes < 12; mes++) {
                ryear.writeDouble(0);
                ryear.writeBoolean(false);
            }
        }
    }
    
    private void createEmployeeFolder(int code) throws IOException{
        File edir = new File(employeeFolder(code));
        edir.mkdir();
        createSalesFileFor(code);
        
        
    }
    
    public void employeeList() throws IOException{
        remps.seek(0);
        while(remps.getFilePointer()<remps.length()){
            int code = remps.readInt();
            String name = remps.readUTF();
            double salary=remps.readDouble();
            Date fecha = new Date(remps.readLong());
            if(remps.readLong()==0){
                System.out.println(code+"-"+name+"-"+"- Lps. "+salary+" Contratado el: "+fecha);
            }
        }
    }
    
    
    private boolean isEmployeeActive(int code)throws IOException{
        remps.seek(0);
        while(remps.getFilePointer()<remps.length()){
            int codeI=remps.readInt();
            long pos = remps.getFilePointer();
            remps.readUTF();
            remps.skipBytes(16);
           if(remps.readLong()==0 && codeI==code){
            remps.seek(pos);
            return true;
        }
        }
        return false;
    }
    
    public boolean fireEmployee(int code) throws IOException{
        if(isEmployeeActive(code)){
            String name=remps.readUTF();
            remps.skipBytes(16);
            remps.writeLong(new Date().getTime());
            System.out.println("Despidiendo a"+name);
            return true;
        }
        return false;
    }
    
   
    public void addSaleToEmployee(int code, double monto) throws IOException{
        if(!isEmployeeActive(code)){
            System.out.println("Empleado no se encontro o inactivo");
            return;
        }
        int mesActual = Calendar.getInstance().get(Calendar.MONTH);
        RandomAccessFile rventas=salesFileFor(code);
        long pos =(long) mesActual*9;
        rventas.seek(pos);
        double ventasActuales=rventas.readDouble();
        rventas.seek(pos);
        rventas.writeDouble(ventasActuales+monto);
        System.out.println("Venta de lps. "+monto+" agregada al empleado "+code);
        
        
    }
    
    private RandomAccessFile billFileFor(int code) throws IOException{
        String dirPadre = employeeFolder(code);
        String path = dirPadre+"/recibos.emp";
        return new RandomAccessFile(path, "rw");
    }
    
    
    private boolean isEmployeePayed(int code) throws IOException{
        RandomAccessFile rventas = salesFileFor(code);
        int mesActual = Calendar.getInstance().get(Calendar.MONTH);
        long pos =(long)mesActual*9;
        rventas.seek(pos);
        rventas.skipBytes(8);
        boolean pagado = rventas.readBoolean();
        return pagado;
        
    }
    
    public void payEmployee(int code) throws IOException{
        if(!isEmployeeActive(code)|| isEmployeePayed(code)){
            System.out.println("No se pudo pagar");
            return;
        }
        
    }
    
}