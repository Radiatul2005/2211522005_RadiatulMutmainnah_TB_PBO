import java.util.InputMismatchException;
import java.sql.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashSet;

public class transaksi extends barang
{

    static Connection root;
    String urll = "jdbc:mysql://localhost:3306/kurokaimarket"; //String untuk menyambungkan ke database
    Scanner inputan = new Scanner(System.in); //scanner

    // constructor
    public transaksi(int hargaBarang, int jumlahBarang1)
    {
        super(hargaBarang, jumlahBarang1);
    }

    @Override
    public int subtotal()
    {
        // operasi matematika
        subtotal = hargaBarang * totalBelanja;
        return subtotal;
    }

    @Override
    // percabangan dan proses matematika
    public int diskon()
    {
        if (subtotal > 25000 && subtotal <= 50000)
        {
            diskon1 = 1000;
        }
        else if (subtotal > 50000 && subtotal <= 100000) {
            diskon1 = 3000;
        }
        else if (subtotal > 100000 && subtotal <= 150000) {
            diskon1 = 5000;
        }
        else if (subtotal > 150000) {
            diskon1 = 10 * diskon1 / 100;
        }
        else
        {
            diskon1 = 0;
        }
        return diskon1;
    }

    @Override
    public int totalharga()
    {
        total = subtotal - diskon1;
        return total;
    }

    @Override
    public void tampil() {

        System.out.println("\nSubtotal : " + subtotal);
        System.out.println("Diskon yang didapat : " + diskon1);
        System.out.println("\nTotal harga: " + total);

    }

    // -------------CRUD-------------

    public void view() throws SQLException
    {
        HashSet<String> uniqueProducts = new HashSet<>(); //Collection Framework dengan hashset

        System.out.println("Daftar Produk KuroKai Shop");
        String sql = "SELECT * FROM kurokai";
        root = DriverManager.getConnection(urll, "root", "");
        Statement statement = root.createStatement();
        ResultSet result = statement.executeQuery(sql);

        while (result.next())
        {
            System.out.print("\nNomor Produk\t: ");
            System.out.print(result.getString("kodebarang"));

            System.out.print("\nNama Produk\t: ");
            String namaProduk = result.getString("namabarang");
            System.out.print(namaProduk);

            // Menambahkan nama produk ke HashSet
            uniqueProducts.add(namaProduk);

            System.out.print("\nUkuran Produk\t: ");
            System.out.print(result.getString("ukuranbarang"));

            System.out.print("\nHarga\t\t: Rp.");
            System.out.print(result.getInt("harga"));

            System.out.print("\nJumlah Produk\t: ");
            System.out.println(result.getInt("jumlah"));
        }
            // Menampilkan nama produk unik
            System.out.println("\nDaftar Produk Unik:");
            for (String product : uniqueProducts)
            {
                System.out.println(product);
            }
    }
    public void save() throws SQLException //Menyimpan data
    {
        System.out.println("---SIMPAN DATA---");
        try
        {
            namabarang();
            ukuran();
            hargabarang();
            jumlah();
            kodebarang();

            String sql = "INSERT INTO kurokai (kodebarang, namabarang, ukuranbarang, harga, jumlah) VALUES ('"
                    + kodeProduk + "','" + namaProduk + "','" + ukuranProduk + "','" + hargaBarang + "' ,'" + jumlahBarang1 + "')";

            root = DriverManager.getConnection(urll, "root", "");
            Statement statement = root.createStatement();
            statement.execute(sql);
            System.out.println("Data Tersimpan!!");
        }

        catch (ArrayIndexOutOfBoundsException e)
        {
            System.err.println("Error!! Masukkan Data Dengan Benar");
        }

        catch (InputMismatchException e)
        {
            System.err.println("Input Pilihan Dengan Angka");
        }

    }

    public void delete() throws SQLException
    {
        System.out.println("---HAPUS DATA---");
        view();
        try
        {
            System.out.print("Masukkan nomor produk yang ingin dihapus : ");
            String keyword = inputan.next();

            String sql = "DELETE FROM kurokai WHERE kodebarang = '" + keyword + "'";

            root = DriverManager.getConnection(urll, "root", "");
            Statement statement = root.createStatement();

            if (statement.executeUpdate(sql) > 0)
            {
                System.out.println("Berhasil menghapus data (Nomor " + keyword + ")");
            }
        }
        catch (SQLException e)
        {
            System.out.println("Terjadi kesalahan penghapusan data");
        }
        catch (Exception e)
        {
            System.out.println("Masukan data dengan benar");
        }
    }

    public void search() throws SQLException
    {
        System.out.println("---CARI DATA---");
        System.out.print("Masukkan nama produk yang : ");
        String keyword = inputan.nextLine();

        String sql = "SELECT * FROM kurokai WHERE namabarang LIKE '%" + keyword + "%'";
        root = DriverManager.getConnection(urll, "root", "");
        Statement statement = root.createStatement();
        ResultSet result = statement.executeQuery(sql);

        while (result.next())
        {
            System.out.print("\nKode barang\t: ");
            System.out.print(result.getString("kodebarang"));
            System.out.print("\nNama barang\t: ");
            System.out.print(result.getString("namabarang"));
            System.out.print("\nUkuran barang\t: ");
            System.out.print(result.getString("ukuranbarang"));
            System.out.print("\nHarga\t\t: ");
            System.out.print(result.getInt("harga"));
            System.out.print("\nJumlah\t\t: ");
            System.out.println(result.getInt("jumlah"));
        }

    }

    public void update() throws SQLException
    {
        System.out.println("---UPDATE DATA---");
        view();
        try
        {
            System.out.print("\nMasukkan nomor produk yang ingin diubah: ");
            String keywordd = inputan.next();

            String sql = "SELECT * FROM kurokai WHERE kodebarang = '" + keywordd + "'";
            Statement statement = root.createStatement();
            ResultSet result = statement.executeQuery(sql);

            if (result.next())
            {
                // tambah stok
                System.out.print("\nJumlah Baru \t: ");
                Integer tambahan = inputan.nextInt();
                int tambahan2;
                int jumlahlama;
                jumlahlama = result.getInt("jumlah");
                tambahan2 = jumlahlama + tambahan;
                // Integer tambahanbrg = jumlahbrg
                System.out.print("\n");
                sql = "UPDATE kurokai SET jumlah='" + tambahan2 + "' WHERE kodebarang='" + keywordd + "'";


                if (statement.executeUpdate(sql) > 0)
                {
                    System.out.println("Berhasil memperbaharui data (Nomor " + keywordd + ")");
                }
            }
            statement.close();
        }

        catch (SQLException e)
        {
            System.err.println("Terjadi kesalahan dalam mengupdate data");
            System.err.println(e.getMessage());
        }

    }

    public void belibarang() throws SQLException  // beli barang menggunakan konsep array list
    {
        view();
        notransaksi();
        String pilihanProduk;

        try
        {
            System.out.print("\nPilih nomor barang yang akan di beli : ");
            pilihanProduk = inputan.next();
            String sql = "SELECT * FROM kurokai WHERE kodebarang = '" + pilihanProduk + "'";
            root = DriverManager.getConnection(urll, "root", "");
            Statement statement = root.createStatement();
            ResultSet result = statement.executeQuery(sql);

            System.out.println("\n----------------");
            System.out.println("   KONFIRMASI");
            System.out.println("----------------\n");
            System.out.println("Customer : " + customer);
            System.out.println("\nAnda akan membeli barang berikut");

            // collection framework
            ArrayList<String> barang = new ArrayList<String>();

            while (result.next())
            {
                barang.add(" Nomor transaksi: " + faktur);
                barang.add(" Nomor barang   : " + (result.getString("kodebarang")));
                barang.add(" Nama barang    : " + (result.getString("namabarang")));
                barang.add(" ukuran barang  : " + (result.getString("ukuranbarang")));
                // method string
                barang.add(" Harga          : " + Integer.toString(result.getInt("harga")));
                ukuranProduk = result.getString("ukuranbarang");
                hargaBarang = result.getInt("harga");
                barang.add(" Stok tersedia  : " + Integer.toString(result.getInt("jumlah")));
                jumlahBarang = result.getInt("jumlah");
            }

            // pengulangan
            for (String i : barang)
            {
                System.out.println(i);
            }

            System.out.print("\nMasukkan banyak barang yang anda akan dibeli: ");
            totalBelanja = inputan.nextInt();

            if (totalBelanja > jumlahBarang)
            {
                System.out.println("/nJumlah yang anda masukkan melebihi stok!");
            }
            else
            {
                // System.out.println("Konfirmasi ulang");
                subtotal();
                diskon();
                totalharga();
                tampil();

                System.out.print("\nYakin ingin membeli barang ini?? (yes/n) : ");
                String konfir = inputan.next();
                // System.out.println(konfir);
                if (konfir.equals("yes"))
                {
                    jumlahBarang = jumlahBarang - totalBelanja;
                    sql = "UPDATE kurokai SET jumlah='" + jumlahBarang + "' WHERE kodebarang='" + pilihanProduk + "'";

                    if (statement.executeUpdate(sql) > 0)
                    {
                        System.out.println("\nPembelian barang berhasil (Nomor " + pilihanProduk + ")");
                    }
                }
                else
                {
                    System.out.println("Pembelian dibatalkan!!");
                }
            }

            barang.clear();

        }
        catch (Exception e)
        {
            System.err.println("Terjadi kesalahan");
            System.err.println(e.getMessage());
        }

    }

}
