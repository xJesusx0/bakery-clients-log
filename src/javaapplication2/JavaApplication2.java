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

            //Total a pagar de cada clientey metodo de pago
            double total[] = new double[number_of_clients];
            String payment_methods[] = new String[number_of_clients];

            //Ciclo que recibe la informacion de los clientes
            for (int i = 0 ; i < number_of_clients ; i++){

                //Registramos el nombre del cliente directamente a el vector
                System.out.println("Ingrese su nombre");
                String name = input.next();
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

                //Si el metodo de pago es visa entonces se aplica el descuento
                if(payment_ == 1){
                    ammount_to_pay = ammount_to_pay - (ammount_to_pay * 0.1);
                    payment_method = "Visa";
                }

                //Despues de recoger la informacion la guardamos en los vectores
                total[i] = ammount_to_pay;
                ammount_of_products[i] = product_quantity;
                payment_methods[i] = payment_method;

                bread_subtotal[i] =bread_category_subtotal;
                cake_subtotal[i] = cake_category_subtotal;
                desserts_subtotal[i] = dessert_category_subtotal;

            }
            //Abrimos el archivo de texto para guardar los resultados
            PrintWriter file = new PrintWriter(new FileWriter("registros_venta.txt",true));

            //Ciclo que va a mostrar los resultados
            for(int i = 0 ; i < number_of_clients ; i++){
                System.out.println("El nombre del cliente es");
                System.out.println(clients_name[i]);
                file.println("El nombre del cliente es");
                file.println(clients_name[i]);

                System.out.println("La cantidad de productos es");
                System.out.println(ammount_of_products[i]);
                file.println("La cantidad de productos es");
                file.println(ammount_of_products[i]);

                System.out.println("El metodo de pago es");
                System.out.println(payment_methods[i]);
                file.println("El metodo de pago es");
                file.println(payment_methods[i]);

                System.out.println("El total a pagar es");
                System.out.println(total[i]);
                file.println("El total a pagar es");
                file.println(total[i]);

                System.out.println("El subtotal de la categoria panes es");
                System.out.println(bread_subtotal[i]);
                file.println("El subtotal de la categoria panes es");
                file.println(bread_subtotal[i]);

                System.out.println("El subtotal de la categoria tortas es");
                System.out.println(cake_subtotal[i]);
                file.println("El subtotal de la categoria tortas es");
                file.println(cake_subtotal[i]);

                System.out.println("El subtotal de la categoria postre es");
                System.out.println(desserts_subtotal[i]);
                file.println("El subtotal de la categoria postre es");
                file.println(desserts_subtotal[i]);

                System.out.println("==============");
                file.println("==============");

            }

            //Despues de guardar los resultados cerramos el archivo
            file.close();
        }    
    }   
}
