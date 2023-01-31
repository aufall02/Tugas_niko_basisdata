
import java.sql.*;
import java.util.Scanner;

public class Main {
    static  final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static  final String DB_URL = "jdbc:mysql://localhost/pt_niko";
    static  final String USER = "root";
    static  final String PASS = "";

    static  Connection conn;
    static  Statement stmt;
    static  ResultSet rs;


    static Scanner masuk = new Scanner(System.in);

        //update
        public static String update() {
            System.out.println("persiapan merubah data");
            System.out.printf("masukkan id yang mau diubah: ");
            String idp = masuk.next();
            System.out.println();
            System.out.println("pilih kolom yang ingin di update >>>>>>");
            System.out.println(" 1 = Nama");
            System.out.println(" 2 = Barang");
            System.out.println(" 3 = Jumlah");
            System.out.println(" 4 = batal");
            System.out.print("pilih menu: ");
            int menu=masuk.nextInt();
            String has = null;
            switch (menu){
                case 1:
                    System.out.printf("masukkan nama yang mau diubah: ");
                    String namap = masuk.next();
                    has = "update transaksi set nama='" + namap + "' where id='" + idp + "'";
                    break;
                case 2:
                    System.out.printf("masukkan barang yang mau diubah: ");
                    String barang = masuk.next();
                    has = "update transaksi set barang='" + barang + "' where id='" + idp + "'";
                    break;
                case 3:
                    System.out.printf("masukkan jumlah yang mau diubah: ");
                    String jmlh = masuk.next();
                    has = "update transaksi set jumlah='" + jmlh + "' where id='" + idp + "'";
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println("menu yang anda masukkan salah, silahkan kembali masukkan menu");
            }


            return has;

        }



        // menghapus data berdasarkan id
        public static String delete(){

            System.out.printf("masukkan id yang mau didelete: ");
            String idp=masuk.next();
            String sql = "delete from transaksi where id='" +idp+"'" ;

            return sql;
        }

        //menapilkan data
        public static void datas(){
        try {

            String sql = "select * from transaksi";
            rs = stmt.executeQuery(sql);
            while (rs.next()){
                System.out.println("ID pelanggan: " + rs.getInt("id")
                        + "| Nama: " + rs.getString("nama")
                        + "| Barang: " + rs.getString("barang")
                        + "| Jumlah: " + rs.getInt("jumlah"));

            }
            return ;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        }

    // menambahkan data
    public static String insert(){
        System.out.println("persiapan input");
        System.out.print("masukkan id: ");

        int idI=masuk.nextInt();
        System.out.print("masukkan nama_pelanggan:");
        String nama=masuk.next();
        System.out.print("masukkan nama_barang :");
        String brng=masuk.next();
        System.out.print("masukkan jumlah_barang:");
        int jml=masuk.nextInt();
        String sql = "insert into transaksi(id,nama,barang,jumlah)" + "values('"+idI+"','"+nama+"','"+brng+"','"+jml+"')";
        return sql;
    }

    public static int menu(){
        System.out.println("=================pilih menuuu ================");
        System.out.println("menu 1: lihat data");
        System.out.println("menu 2: insert data");
        System.out.println("menu 3: update data");
        System.out.println("menu 4: delete data");
        System.out.println("menu 5: exit");
        System.out.print("pilih menu: ");
        int menu=masuk.nextInt();

        return menu;
    }




    public static void main(String[] args) {
        System.out.println("Hello world!");

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();

            while(true){
                System.out.println("=================pilih menuuu ================");
                System.out.println("menu 1: lihat data");
                System.out.println("menu 2: insert data");
                System.out.println("menu 3: update data");
                System.out.println("menu 4: delete data");
                System.out.println("menu 5: exit");
                System.out.print("pilih menu: ");
                int menu=masuk.nextInt();

                switch (menu){
                    case 1:
                        datas();
                        break;
                    case 2:
                        stmt.execute(insert());
                        System.out.print("data berhasil di masukkan");
                        break;
                    case 3:
                        stmt.execute(update());
                        System.out.println("data berhasil di update");
                        break;
                    case 4:
                        stmt.execute(delete());
                        System.out.println("data berhasil di delete");
                        break;

                    case 5:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("menu yang anda masukkan salah, silahkan kembali masukkan menu");
                }
            }


        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}