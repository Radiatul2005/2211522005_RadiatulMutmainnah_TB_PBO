import java.util.Scanner;
import java.io.IOException;
import java.sql.*;

public class program
{
    public static void main(String[] args) throws SQLException, IOException
    {
        //string koneksi
        Connection root;
        String url = "jdbc:mysql://localhost:3306/kurokaimarket";

        int pilihann;
        //try catch exception
        try (Scanner masukkanInput = new Scanner (System.in))
        {
            boolean menu=true;
            //constructor
            transaksi trs = new transaksi(0, 0);
            System.out.println("Selamat Datang di KuroKai Shop");
            System.out.println("--------------------------------");
            System.out.println("Login Sebagai");
            System.out.println("1. Admin");
            System.out.println("2. Customer");
            System.out.print("Login Sebagai : ");
            int loginSebagai;
            Scanner scanner = new Scanner(System.in);
            loginSebagai = scanner.nextInt();
            if (loginSebagai == 1)
            {
                System.out.println("--------------------------------");
                LoginAdmin loginadmin = new LoginAdmin();
                loginadmin.masukkanDetails();
                try {
                        root = DriverManager.getConnection(url,"root","");
                        System.out.println("Class Driver ditemukan");
                        //kasir
                        trs.admin();
                        //Perulangan dengan method while
                        while (menu)
                        {
                            System.out.println("\n------------------");
                            System.out.println("    KuroKai Shop    ");
                            System.out.println("----------------------");
                            System.out.println("1. View Data");
                            System.out.println("2. Add Product");
                            System.out.println("3. Update Data");
                            System.out.println("4. Delete Data");
                            System.out.println("5. Search Product");
                            System.out.println("6. Selesai\n");

                            System.out.print("\nMasukkan Pilihan Menu (1/2/3/4/5/6): ");
                            pilihann = masukkanInput.nextInt();

                            //percabangan
                            switch (pilihann)
                            {
                                case 1:
                                    System.out.print("\033[H\033[2J");
                                    System.out.flush();
                                    System.out.println("---VIEW DATA---");
                                    trs.view();
                                    break;
                                case 2:
                                    System.out.print("\033[H\033[2J");
                                    System.out.flush();
                                    trs.save();
                                    break;
                                case 3:
                                    System.out.print("\033[H\033[2J");
                                    System.out.flush();
                                    trs.update();
                                    break;
                                case 4:
                                    System.out.print("\033[H\033[2J");
                                    System.out.flush();
                                    trs.delete();
                                    break;
                                case 5:
                                    System.out.print("\033[H\033[2J");
                                    System.out.flush();
                                    trs.search();
                                    break;
                                case 6:
                                    menu = false;
                                    break;
                                default:
                                    System.err.println("\nInput Salah!! Masukkan Ulang");
                                    break;
                            }
                        }

                }
                //exception
                catch(SQLException e)
                {
                    System.err.println("Koneksi Gagal");
                    System.err.println(e.getMessage());
                }
            }
            else if (loginSebagai == 2)
            {
                System.out.println("--------------------------------");
                LoginCustomer logincustomer = new LoginCustomer();
                logincustomer.masukkanDetails();
                try {
                        root = DriverManager.getConnection(url,"root","");
                        System.out.println("Class Driver ditemukan");
                        trs.customer();
                        while (menu)
                        {
                            System.out.println("\n------------------");
                            System.out.println("    KuroKai Shop    ");
                            System.out.println("----------------------");
                            System.out.println("1. Buy Product");
                            System.out.println("2. Search Product");
                            System.out.println("3. Selesai\n");

                            System.out.print("\nMasukkan Pilihan Menu (1/2/3): ");
                            pilihann = masukkanInput.nextInt();

                            //percabangan
                            switch (pilihann)
                            {
                                case 1:
                                    System.out.print("\033[H\033[2J");
                                    System.out.flush();
                                    trs.belibarang();
                                    break;
                                case 2:
                                    System.out.print("\033[H\033[2J");
                                    System.out.flush();
                                    trs.search();
                                    break;
                                case 3:
                                    menu = false;
                                    break;
                                default:
                                    System.err.println("\nInput Salah!! Masukkan Ulang");
                                    break;
                            }
                        }

                }
                //exception
                catch(SQLException e)
                {
                    System.err.println("Koneksi Gagal");
                    System.err.println(e.getMessage());
                }
            }
        }

        System.out.println("\nSelesai\n");

    }
}
