/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.swingact6clases.modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

/**
 *
 * @author catax
 */
public class ArrayProductos {
    
    private ArrayList<Producto> productos;
    
    /* Metodo constructor que crea el objeto productos de tipo ArrayList
       y carga el contenido del archivo Productos.txt
    */
    public ArrayProductos()
    {
        productos = new ArrayList<Producto>();
        cargar();
    }
    
    /*Metodo que adiciona un objeto de tipo Producto al ArrayList*/
    public void adicionar(Producto p)
    {
        productos.add(p);
    }
    
    /*Metodo que devuelve los elementos contenidos en el array
        segun una posicion especificada
    */
    public Producto obtener(int posicion)
    {
        return productos.get(posicion);
    }
    
    /*Metodo que busca un producto desde el arreglo y devuelve
        el objeto de tipo producto, sino lo encuentra devolvera NULL*/
    public Producto buscar(String codigo)
    {
        for (int i = 0; i < productos.size(); i++) 
        {
            if(codigo.equals(productos.get(i).getCodigo()))
                {return productos.get(i);}
        }
        return null;
    }
    
    /*Metodo que devuelve el total de productos registrados*/
    public int getTamanio()
    {
        return productos.size();
    }
    
    /*Metodo que permite eliminar un producto del array mediante
        el objeto de tipo Producto*/
    public void eliminar(Producto p)
    {
        productos.remove(p);
    }
    
    
    public void cargar()
    {
        try 
        {
            File archivo = new File("productos.txt");
            if(archivo.exists())
            {
                BufferedReader br = new BufferedReader(new FileReader("productos.txt"));
                String linea;
                while((linea=br.readLine())!=null)
                {
                    //Definición del separador de valores en Productos.txt
                    StringTokenizer st = new StringTokenizer(linea, ",");
                    
                    String codigo = st.nextToken().trim();
                    String descripcion = st.nextToken().trim();
                    String categoria = st.nextToken().trim();
                    int stock = Integer.parseInt(st.nextToken().trim());
                    double precio = Double.parseDouble(st.nextToken().trim());
                    
                    //Crear un nuevo registro de producto obtenido del
                    //archivo Productos.txt
                    Producto p = new Producto(codigo, descripcion, categoria, stock, precio);
                    adicionar(p);
                }
                br.close();
            }
            else 
            {
                JOptionPane.showMessageDialog(null, "El archivo no existe");
            }
        } 
        catch (Exception e) 
        {
            JOptionPane.showMessageDialog(null, "Se  produjo un error!!"+e);
        }
    }
    
    /*Metodo que permite grabar los nuevos valores de un producto*/
    public void grabar()
    {
        try 
        {
            PrintWriter pw = new PrintWriter(new FileWriter("productos.txt"));
            for (int i = 0; i < getTamanio(); i++) 
            {
                pw.println(obtener(i).getCodigo()+","
                        +obtener(i).getDescripcion()+","
                        +obtener(i).getCategoria()+","
                        +obtener(i).getStock()+","
                        +obtener(i).getPrecio());
                
            }
            pw.close();
            JOptionPane.showMessageDialog(null, "Operacion exitosa!!");
        } 
        catch (Exception e) 
        {
           JOptionPane.showMessageDialog(null, "Se  produjo un error!!"+"Aviso: "+JOptionPane.ERROR_MESSAGE); 
        }
    }

    
}
