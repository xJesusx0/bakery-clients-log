package javaapplication2;

import java.util.Scanner;
import java.io.*;

public class JavaApplication2 {
    public static void main(String[] args) throws IOException {
                         
        Scanner input = new Scanner(System.in);
        System.out.println("Ingrese que desea hacer");
        System.out.println("1 - Ver el registro");
        System.out.println("2 - Registrar");
        
        int option = input.nextInt();
        if (option == 1){
        
            BufferedReader file = new BufferedReader(new FileReader("registros_venta.txt"));
            String line = file.readLine();
            
            while(line != null){
                System.out.println(line);
                line = file.readLine();
            }
            file.close(); 
        }else if (option == 2){
            
            //En estos vectores declaramos la informacion de cada producto
            String[] products = {"Pan arabe","Torta de zanahoria","Napoleon","Tres leches","Torta chocolate","Pan de espinaca"};
            int[] prices = {5000,25000,10000,15000,30000,4000};
            String[] type = {"Panes","Tortas","Postres","Postres","Tortas","Panes"};
            
            input.nextLine();
            System.out.println("Ingrese el nombre de la panaderia");
            String bakery_name = input.nextLine();

            System.out.println("Ingrese la cantidad de clientes");
            int number_of_clients = input.nextInt();

            //En estos vectores va la informacion de los clientes
            String clients_name[] = new String[number_of_clients];
            int ammount_of_products[] = new int[number_of_clients];

            //Subtotal por categoria de cada cliente
            double bread_subtotal[] = new double [number_of_clients];
            double cake_subtotal[] = new double [number_of_clients];
            double desserts_subtotal[] = new double [number_of_clients];

            //Total a pagar de cada cliente, metodo de pago y descuento aplicado
            double total[] = new double[number_of_clients];
            String payment_methods[] = new String[number_of_clients];
            String discounts[] = new String[number_of_clients];
            
            //Ciclo que recibe la informacion de los clientes
            for (int i = 0 ; i < number_of_clients ; i++){

                //Registramos el nombre del cliente directamente a el vector
                System.out.println("Ingrese su nombre");
                input.nextLine();
                String name = input.nextLine();
                clients_name[i] = name;
                //Variables que contienen toda la informacion de el usuario actual
                int product_quantity = 0;
                double ammount_to_pay = 0;
                double bread_category_subtotal = 0;
                double cake_category_subtotal = 0;
                double dessert_category_subtotal = 0;

                //Ciclo que va a recoger la informacion de cada producto, cantidad,precio total, etc..
                //La cantidad de veces que itere el ciclo sera la cantidad de productos que haiga
                for(int k = 0 ; k < products.length; k++ ){

                    //Y aca accedemos a la informacion de cada producto que esta contenida en los vectores del inicio           
                    System.out.println("Ingrese la cantidad de " + products[k] + " que va a llevar");
                    int ammount = input.nextInt();
                    double product_total_price = prices[k] * ammount;

                    //Le sumamos el total a pagar de el producto a el subtotal de su categoria
                    if(type[k].equals("Panes")){
                        bread_category_subtotal = bread_category_subtotal + product_total_price;   
                    }
                    if(type[k].equals("Tortas")){
                        cake_category_subtotal = cake_category_subtotal + product_total_price;
                    }
                    if(type[k].equals("Postres")){
                        dessert_category_subtotal = dessert_category_subtotal + product_total_price;
                    }
                    product_quantity = product_quantity + ammount;
                    ammount_to_pay = ammount_to_pay + product_total_price;
                } 
                //Menu para el metodo de pago
                System.out.println("Ingrese el metodo de pago");
                System.out.println("1 - Visa");
                System.out.println("2 - Otro");

                int payment_ = input.nextInt();
                String payment_method = "Otro";
                String discount = "0%";
                        
                //Si el metodo de pago es visa entonces se aplica el descuento
                if(payment_ == 1){
                    ammount_to_pay = ammount_to_pay - (ammount_to_pay * 0.1);
                    payment_method = "Visa";
                    discount = "10%";
                }

                //Despues de recoger la informacion la guardamos en los vectores
                total[i] = ammount_to_pay;
                ammount_of_products[i] = product_quantity;
                payment_methods[i] = payment_method;

                bread_subtotal[i] =bread_category_subtotal;
                cake_subtotal[i] = cake_category_subtotal;
                desserts_subtotal[i] = dessert_category_subtotal;
                discounts[i] = discount;
                
                //Abrimos el archivo de texto para guardar los datos
                PrintWriter file = new PrintWriter(new FileWriter("registros_venta.txt",true));
                
                file.println("Nombre del cliente: " + clients_name[i]);
                file.println("Cantidad de productos: " + ammount_of_products[i]);
                file.println("Metodo de pago: " + payment_methods[i]);
                file.println("Total a pagar es: " + total[i]);
                file.println("Descuento: " + discounts[i]);
                file.println("Subtotal de la categoria panes: " + bread_subtotal[i]);
                file.println("Subtotal de la categoria tortas es: " + cake_subtotal[i]);
                file.println("Subtotal de la categoria postre: " + desserts_subtotal[i]);
                file.println("==============");
                
                //Despues de guardar los datos cerramos el archivo
                file.close();
                System.out.println("Informacion guardada correctamente");
            }
            

            //Ciclo que va a mostrar los resultados
            for(int i = 0 ; i < number_of_clients ; i++){
                System.out.println("Nombre del cliente: " + clients_name[i]);
                System.out.println("Cantidad de productos: " + ammount_of_products[i]);
                System.out.println("Metodo de pago: " + payment_methods[i]);           
                System.out.println("Total a pagar es: " + total[i]);
                System.out.println("Descuento: " + discounts[i]);
                System.out.println("Subtotal de la categoria panes: " + bread_subtotal[i]);
                System.out.println("Subtotal de la categoria tortas: " + cake_subtotal[i]);
                System.out.println("Subtotal de la categoria postre: " + desserts_subtotal[i]);
                System.out.println("==============");
            }
            
        }    
    }   
}
