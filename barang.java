import java.time.LocalDateTime;
// import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class barang implements penjualan {

    String namaProduk, idAdmin, namaAdmin, noFaktur, admin1, ukuranProduk,faktur, namaCustomer, customer;
    String ukuran[] = {"Small", "Medium", "Big"};
    int ukur,jumlahBarang;    //jumlahBarang adalah int untuk memasukkan jumlah barang yang diambil dari database
    int hargaBarang, jumlahBarang1;    //Integer untuk input database
    int totalBelanja; //Total belanja
    String kodeProduk; //kode barang
    int subtotal,diskon1,total; //integer untuk diskon dan total belanja

    //constructor untuk barang
    public barang(int hargaBarang, int jumlahBarang1)
    {
        this.hargaBarang = hargaBarang;
        this.jumlahBarang1 = jumlahBarang1;
    }

    Scanner produk = new Scanner(System.in); //scanner

    public void admin() //method untuk data admin
    {
        System.out.print("\nID Admin : ");
        idAdmin = produk.nextLine();
        System.out.print("Nama Admin : ");
        namaAdmin = produk.nextLine();
    }

    public void customer()
    {
        System.out.print("Nama Customer : ");
        namaCustomer = produk.nextLine();
        namaCustomer = namaCustomer.toUpperCase(); //Method string untuk mengubah nama kasir menjadi huruf kapital
        customer = "Cus" +"-".concat(namaCustomer);
    }
    //method date
    public void notransaksi()
    {
        LocalDateTime noFaktur = LocalDateTime.now();
        faktur = noFaktur.toString();
        // System.out.println("Nomor transaksi : " + notranksaksi + "\n");
    }

    public void namabarang()
    {
        System.out.print("\nNama Produk : ");
        this.namaProduk = produk.nextLine();
        if(namaProduk.isEmpty()){
            System.out.println("Nama Produk Kosong, Inputkan Nama Produk!!");
            System.exit(0);}
    }

    public void ukuran()
    {
        System.out.print("Ukuran(0->Small | 1->Medium | 2-Big) : ");
        this.ukur = produk.nextInt();
        ukuranProduk = ukuran[ukur];
        boolean henti=false;
        //exception
        try
        {
            System.out.println("Ukuran " + ukur + " adalah " + ukuranProduk + "\n");
        }
        catch (Exception e)
        {
            henti=true;
            System.out.println("Index Ukuran Produk Salah!!!");
            if(henti){System.exit(0);};
        }
    }

    public void hargabarang()
    {
        System.out.print("Harga : ");
        this.hargaBarang = produk.nextInt();
        //System.out.println("Harga : " + hargabrg);
    }

    public void jumlah()
    {
        System.out.print("Jumlah : ");
        this.jumlahBarang1 = produk.nextInt();
        //System.out.println("Jumlah : " + jumlahbrg);
    }

    public void kodebarang()
    {
        System.out.println("Masukkan kode Barang : ");
        this.kodeProduk = produk.next();
    }

    public int subtotal()
    {
        return 0;
    }

    public int diskon()
    {
        return 0;
    }

    public int totalharga()
    {
        return 0;
    }
    public void tampil()
    {

    }

}
