/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author hi
 */
import java.io.IOException;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.Arrays;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

class Rating {

    String rating;
    String ulasan;
    String[] link;
    String[][] dataRating = new String[31][2];
    int[] rowSheet;
    static XSSFWorkbook workbook = new XSSFWorkbook();
    static XSSFSheet sheet = workbook.createSheet("Rating&Ulasan");
    static XSSFRow row;
    static XSSFCell cell;
    static Connection sambung;
    static String filePath = "C:\\Users\\hi\\OneDrive\\Documents\\WebScraping\\RatingTerbaru6.xlsx";

    Rating(String[] link, int[] rowSheet) throws IOException {
        this.link = link;
        this.rowSheet = rowSheet;
        this.getRatingandReview();
    }

    void getRatingandReview() throws IOException {
        for (int i = 0; i < link.length; i++) {
            sambung = Jsoup.connect(link[i]);
            Document doc = sambung.get();
            this.rating = doc.getElementsByClass("css-4rbku5 css-901oao r-t1w4ow r-1x35g6 r-b88u0q r-vrz42v r-fdjqy7").text();
            this.ulasan = doc.getElementsByClass("css-901oao css-cens5h r-1i6uqv8 r-t1w4ow r-1b43r93 r-b88u0q r-rjixqe r-fdjqy7").text();
            //if (ulasan.length() == 0){
              //  continue;
            //}
            System.out.println(this.rating);
            System.out.println(this.ulasan);
            this.dataRating[i][0] = rating;
            this.dataRating[i][1] = ulasan;
        }
        addtoExcel();
    }

    void addtoExcel() throws IOException {
        int cols = this.dataRating[0].length;
        for (int r = 0; r < this.rowSheet.length; r++) {
            row = sheet.createRow(this.rowSheet[r]);
            for (int c = 0; c < cols; c++) {
                cell = row.createCell(c);
                //Object value =  [r][c];
                cell.setCellValue(dataRating[r][c]);
            }
        }

    }

    void writeExcel() throws IOException {
        FileOutputStream outStream = new FileOutputStream(filePath);
        workbook.write(outStream);
        System.out.println("Sukses");
    }

    void display() {
        System.out.println(Arrays.toString(this.dataRating[0]));
    }
}

class Traveloka extends Rating {
 Traveloka(String[] link, int[] rowSheet) throws IOException {
        super(link, rowSheet);
    }

}

class Booking extends Rating {

    Booking(String[] link, int[] rowSheet) throws IOException {
        super(link, rowSheet);
    }

    void getRatingandReview() throws IOException {
        for (int i = 0; i < link.length; i++) {
            sambung = Jsoup.connect(link[i]);
            Document doc = sambung.get();
            try {
                this.rating = doc.getElementsByClass("a3b8729ab1 d86cee9b25").first().text();
                this.ulasan = doc.getElementsByClass("abf093bdfe f45d8e4c32 d935416c47").first().text();
            } catch (NullPointerException e) {
                continue;
            }
            System.out.println(rating);
            System.out.println(ulasan);
            this.dataRating[i][0] = rating;
            this.dataRating[i][1] = ulasan.substring(0, 2);

        }
        addtoExcel();
    }

}

public class Main {

    public static void main(String[] args) throws IOException {
        //link traveloka
        String hillcrest = "https://www.traveloka.com/en-id/hotel/detail?spec=05-06-2024.06-06-2024.1.1.HOTEL.9000000987471.Cove%20Hillcrest%2C%20Karawaci.1";
        String t63 = "https://www.traveloka.com/en-id/hotel/detail?spec=05-06-2024.06-06-2024.1.1.HOTEL.9000001071619.Cove%20T63.1";
        String theday = "https://www.traveloka.com/en-id/hotel/detail?spec=05-06-2024.06-06-2024.1.1.HOTEL.9000001071622.Cove%20The%20Day.1";
        String taman = "https://www.traveloka.com/en-id/hotel/detail?spec=05-06-2024.06-06-2024.1.1.HOTEL.9000001109608.Cove%20Taman.1";
        String west_inn = "https://www.traveloka.com/en-id/hotel/detail?spec=05-06-2024.06-06-2024.1.1.HOTEL.9000001109610.Cove%20West%20Inn%20Cihampelas.1";
        String arimbi = "https://www.traveloka.com/en-id/hotel/detail?spec=05-06-2024.06-06-2024.1.1.HOTEL.9000001136296.Cove%20Arimbi.1";
        String accordia = "https://www.traveloka.com/en-id/hotel/detail?spec=05-06-2024.06-06-2024.1.1.HOTEL.9000001136647.Cove%20Accordia%20Residence.1";
        String birah = "https://www.traveloka.com/en-id/hotel/detail?spec=05-06-2024.06-06-2024.1.1.HOTEL.9000000987472.Cove%20Birah%20at%20Senopati.1";
        String renata = "https://www.traveloka.com/en-id/hotel/detail?spec=05-06-2024.06-06-2024.1.1.HOTEL.9000001152181.Cove%20Renata%20Seminyak.1";
        String tevana = "https://www.traveloka.com/en-id/hotel/detail?spec=05-06-2024.06-06-2024.1.1.HOTEL.9000001152821.Cove%20Tevana.1";
        String ransha = "https://www.traveloka.com/en-id/hotel/detail?spec=05-06-2024.06-06-2024.1.1.HOTEL.9000001153016.Cove%20Ransha%20Stay%20Bali.1";
        String w_suite = "https://www.traveloka.com/en-id/hotel/detail?spec=05-06-2024.06-06-2024.1.1.HOTEL.9000000987470.Cove%20W%20Suites%2C%20Tebet.1";
        String isvara = "https://www.traveloka.com/en-id/hotel/detail?spec=05-06-2024.06-06-2024.1.1.HOTEL.9000001202196.The%20Isvara%20By%20Cove%20.1";
        String guntur = "https://www.traveloka.com/en-id/hotel/detail?spec=05-06-2024.06-06-2024.1.1.HOTEL.9000002249761.Cove%20Guntur%20Heritage.1";
        String kanaya = "https://www.traveloka.com/en-id/hotel/detail?spec=05-06-2024.06-06-2024.1.1.HOTEL.9000001207976.Cove%20Kanaya.1";
        String horizone = "https://www.traveloka.com/en-id/hotel/detail?spec=05-06-2024.06-06-2024.1.1.HOTEL.9000002376611.Cove%20Horizone.1";
        String oekude = "https://www.traveloka.com/en-id/hotel/detail?spec=05-06-2024.06-06-2024.1.1.HOTEL.9000002374442.Cove%20Oekude%20Residence.1";
        String bonaSort = "https://www.traveloka.com/en-id/hotel/detail?spec=05-06-2024.06-06-2024.1.1.HOTEL.9000002377031.Cove%20Bona%20Sort%20Pasar%20Baru.1";
        String skyCitiHome = "https://www.traveloka.com/en-id/hotel/detail?spec=05-06-2024.06-06-2024.1.1.HOTEL.9000000971872.Cove%20Sky%20City%20Home.1";
        String senja = "https://www.traveloka.com/en-id/hotel/detail?spec=05-06-2024.06-06-2024.1.1.HOTEL.9000002450817.Cove%20Senja.1";
        String griya_elite = "https://www.traveloka.com/en-id/hotel/detail?spec=05-06-2024.06-06-2024.1.1.HOTEL.9000001106367.Cove%20Griya%20Elite.1";
        String matahari = "https://www.traveloka.com/en-id/hotel/detail?spec=05-06-2024.06-06-2024.1.1.HOTEL.9000000973887.Cove%20Matahari%20Guesthouse.1";
        String depavilla = "https://www.traveloka.com/en-id/hotel/detail?spec=05-06-2024.06-06-2024.1.1.HOTEL.9000002624460.Cove%20De%20Pavilla.1";
        String detinaya = "https://www.traveloka.com/en-id/hotel/detail?spec=05-06-2024.06-06-2024.1.1.HOTEL.9000001282302.Cove%20De%20Tinaya.1";
        String raddeyapa = "https://www.traveloka.com/en-id/hotel/detail?spec=05-06-2024.06-06-2024.1.1.HOTEL.9000002763320.Cove%20Raddeyapa.1";
   //veranda not real
        String veranda = "https://www.traveloka.com/en-id/hotel/detail?spec=05-06-2024.06-06-2024.1.1.HOTEL.9000003268929.Cove%20Veranda.1";
        String riverhaus = "https://www.traveloka.com/en-id/hotel/detail?spec=05-06-2024.06-06-2024.1.1.HOTEL.9000002920800.Cove%20River%20Haus.1";
        String nawaprita = "https://www.traveloka.com/en-id/hotel/detail?spec=05-06-2024.06-06-2024.1.1.HOTEL.9000003250689.Cove%20Nawaprita.1";
        String urbanier = "https://www.traveloka.com/en-id/hotel/detail?spec=05-06-2024.06-06-2024.1.1.HOTEL.9000003250690.Cove%20Urbanier.1";
        String casagio = "https://www.traveloka.com/en-id/hotel/detail?spec=05-06-2024.06-06-2024.1.1.HOTEL.9000003252556.Cove%20Casagio.1";
        String tripurihouse = "https://www.traveloka.com/en-id/hotel/detail?spec=05-06-2024.06-06-2024.1.1.HOTEL.9000003253714.Cove%20Tripuri%20House%20Bali.1";
//link booking.com
        String a_Hillcrest = "https://www.booking.com/hotel/id/cove-hillcrest.id.html?aid=304142&label=gen173nr-1FCAEoggI46AdIM1gEaGiIAQGYARK4ARfIAQzYAQHoAQH4AQyIAgGoAgO4AonLiakGwAIB0gIkZDYxYTRiMjYtYzQwOC00YWYxLWE1OGUtZTdhODZjMzljYTQ12AIG4AIB&sid=ae5ed9ff67052a78281a8b47cfa86da1&all_sr_blocks=845462903_361662890_2_0_0;checkin=2023-12-30;checkout=2023-12-31;dest_id=8454629;dest_type=hotel;dist=0;group_adults=2;group_children=0;hapos=1;highlighted_blocks=845462903_361662890_2_0_0;hpos=1;matching_block_id=845462903_361662890_2_0_0;no_rooms=1;req_adults=2;req_children=0;room1=A%2CA;sb_price_type=total;sr_order=popularity;sr_pri_blocks=845462903_361662890_2_0_0__38888900;srepoch=1696753140;srpvid=76b63a51e699000a;type=total;ucfs=1&#hotelTmpl";
        String a_t63 = "https://www.booking.com/hotel/id/cove-t63.id.html?aid=304142&label=gen173nr-1FCAEoggI46AdIM1gEaGiIAQGYARK4ARfIAQzYAQHoAQH4AQyIAgGoAgO4AquqkKkGwAIB0gIkYjM2MmQ0NjItYTQ5My00Y2Q2LWEwNjgtMDlkYTA1YmJmZDNj2AIG4AIB&sid=ae5ed9ff67052a78281a8b47cfa86da1&all_sr_blocks=898075001_360460292_2_0_0;checkin=2023-12-30;checkout=2023-12-31;dest_id=8980750;dest_type=hotel;dist=0;group_adults=2;group_children=0;hapos=1;highlighted_blocks=898075001_360460292_2_0_0;hpos=1;matching_block_id=898075001_360460292_2_0_0;no_rooms=1;req_adults=2;req_children=0;room1=A%2CA;sb_price_type=total;sr_order=popularity;sr_pri_blocks=898075001_360460292_2_0_0__59850000;srepoch=1696863548;srpvid=e52b695d48490329;type=total;ucfs=1&#hotelTmpl";
        String a_theday = "https://www.booking.com/hotel/id/cove-the-day.id.html?aid=304142&label=gen173nr-1FCAEoggI46AdIM1gEaGiIAQGYARK4ARfIAQzYAQHoAQH4AQyIAgGoAgO4AquqkKkGwAIB0gIkYjM2MmQ0NjItYTQ5My00Y2Q2LWEwNjgtMDlkYTA1YmJmZDNj2AIG4AIB&sid=ae5ed9ff67052a78281a8b47cfa86da1&all_sr_blocks=898910001_361564240_0_0_0;checkin=2023-12-30;checkout=2023-12-31;dest_id=8989100;dest_type=hotel;dist=0;group_adults=2;group_children=0;hapos=1;highlighted_blocks=898910001_361564240_0_0_0;hpos=1;matching_block_id=898910001_361564240_0_0_0;no_rooms=1;req_adults=2;req_children=0;room1=A%2CA;sb_price_type=total;sr_order=popularity;sr_pri_blocks=898910001_361564240_0_0_0__81249900;srepoch=1696863591;srpvid=8c4f69743c260064;type=total;ucfs=1&#hotelTmpl";
        String a_taman = "https://www.booking.com/hotel/id/cove-taman.id.html?aid=304142&label=gen173nr-1FCAEoggI46AdIM1gEaGiIAQGYARK4ARfIAQzYAQHoAQH4AQyIAgGoAgO4AquqkKkGwAIB0gIkYjM2MmQ0NjItYTQ5My00Y2Q2LWEwNjgtMDlkYTA1YmJmZDNj2AIG4AIB&sid=ae5ed9ff67052a78281a8b47cfa86da1&all_sr_blocks=941133003_366358535_0_0_0;checkin=2023-12-30;checkout=2023-12-31;dest_id=9411330;dest_type=hotel;dist=0;group_adults=2;group_children=0;hapos=1;highlighted_blocks=941133003_366358535_0_0_0;hpos=1;matching_block_id=941133003_366358535_0_0_0;no_rooms=1;req_adults=2;req_children=0;room1=A%2CA;sb_price_type=total;sr_order=popularity;sr_pri_blocks=941133003_366358535_0_0_0__41666700;srepoch=1696863627;srpvid=96f3698437a9000d;type=total;ucfs=1&#hotelTmpl";
        String a_west_inn = "https://www.booking.com/hotel/id/west-inn.id.html?aid=304142&label=gen173nr-1FCAEoggI46AdIM1gEaGiIAQGYARK4ARfIAQzYAQHoAQH4AQyIAgGoAgO4AquqkKkGwAIB0gIkYjM2MmQ0NjItYTQ5My00Y2Q2LWEwNjgtMDlkYTA1YmJmZDNj2AIG4AIB&sid=ae5ed9ff67052a78281a8b47cfa86da1&all_sr_blocks=940700402_373401574_2_0_0;checkin=2023-12-30;checkout=2023-12-31;dest_id=9407004;dest_type=hotel;dist=0;group_adults=2;group_children=0;hapos=1;highlighted_blocks=940700402_373401574_2_0_0;hpos=1;matching_block_id=940700402_373401574_2_0_0;no_rooms=1;req_adults=2;req_children=0;room1=A%2CA;sb_price_type=total;sr_order=popularity;sr_pri_blocks=940700402_373401574_2_0_0__60000000;srepoch=1696863674;srpvid=42a3699d3e2300bf;type=total;ucfs=1&#hotelTmpl";
        String a_arimbi = "https://www.booking.com/hotel/id/cove-arimbi.id.html?aid=304142&label=gen173nr-1FCAEoggI46AdIM1gEaGiIAQGYARK4ARfIAQzYAQHoAQH4AQyIAgGoAgO4AquqkKkGwAIB0gIkYjM2MmQ0NjItYTQ5My00Y2Q2LWEwNjgtMDlkYTA1YmJmZDNj2AIG4AIB&sid=ae5ed9ff67052a78281a8b47cfa86da1&all_sr_blocks=969703901_369376982_2_0_0;checkin=2023-12-30;checkout=2023-12-31;dest_id=9697039;dest_type=hotel;dist=0;group_adults=2;group_children=0;hapos=1;highlighted_blocks=969703901_369376982_2_0_0;hpos=1;matching_block_id=969703901_369376982_2_0_0;no_rooms=1;req_adults=2;req_children=0;room1=A%2CA;sb_price_type=total;sr_order=popularity;sr_pri_blocks=969703901_369376982_2_0_0__46875000;srepoch=1696863789;srpvid=27a369d5eeb80261;type=total;ucfs=1&#hotelTmpl";
        String a_accordia = "https://www.booking.com/hotel/id/cove-accordia-residence.id.html?aid=304142&label=gen173nr-1FCAEoggI46AdIM1gEaGiIAQGYARK4ARfIAQzYAQHoAQH4AQyIAgGoAgO4AquqkKkGwAIB0gIkYjM2MmQ0NjItYTQ5My00Y2Q2LWEwNjgtMDlkYTA1YmJmZDNj2AIG4AIB&sid=ae5ed9ff67052a78281a8b47cfa86da1&checkin=2023-12-30;checkout=2023-12-31;dest_id=9696963;dest_type=hotel;dist=0;group_adults=2;group_children=0;hapos=1;hpos=1;no_rooms=1;req_adults=2;req_children=0;room1=A%2CA;sb_price_type=total;soh=1;sr_order=popularity;srepoch=1696863866;srpvid=ca7969fd76bf009b;type=total;ucfs=1&#no_availability_msg";
        String a_birah = "https://www.booking.com/hotel/id/cove-birah-senopati.id.html?aid=304142&label=gen173nr-1FCAEoggI46AdIM1gEaGiIAQGYARK4ARfIAQzYAQHoAQH4AQyIAgGoAgO4AquqkKkGwAIB0gIkYjM2MmQ0NjItYTQ5My00Y2Q2LWEwNjgtMDlkYTA1YmJmZDNj2AIG4AIB&sid=ae5ed9ff67052a78281a8b47cfa86da1&all_sr_blocks=1043083401_376700429_0_0_0;checkin=2023-12-30;checkout=2023-12-31;dest_id=10430834;dest_type=hotel;dist=0;group_adults=2;group_children=0;hapos=1;highlighted_blocks=1043083401_376700429_0_0_0;hpos=1;matching_block_id=1043083401_376700429_0_0_0;no_rooms=1;req_adults=2;req_children=0;room1=A%2CA;sb_price_type=total;sr_order=popularity;sr_pri_blocks=1043083401_376700429_0_0_0__66666600;srepoch=1696863907;srpvid=af2b6a12272601fb;type=total;ucfs=1&#hotelTmpl";
        String a_renata = "https://www.booking.com/hotel/id/cove-renata-seminyak.id.html?aid=304142&label=gen173nr-1FCAEoggI46AdIM1gEaGiIAQGYARK4ARfIAQzYAQHoAQH4AQyIAgGoAgO4AquqkKkGwAIB0gIkYjM2MmQ0NjItYTQ5My00Y2Q2LWEwNjgtMDlkYTA1YmJmZDNj2AIG4AIB&sid=ae5ed9ff67052a78281a8b47cfa86da1&all_sr_blocks=1026465701_375407201_2_0_0;checkin=2023-12-30;checkout=2023-12-31;dest_id=10264657;dest_type=hotel;dist=0;group_adults=2;group_children=0;hapos=1;highlighted_blocks=1026465701_375407201_2_0_0;hpos=1;matching_block_id=1026465701_375407201_2_0_0;no_rooms=1;req_adults=2;req_children=0;room1=A%2CA;sb_price_type=total;sr_order=popularity;sr_pri_blocks=1026465701_375407201_2_0_0__81247000;srepoch=1696863923;srpvid=92006a1a783e014c;type=total;ucfs=1&#hotelTmpl";
        String a_tevana = "https://www.booking.com/hotel/id/cove-tevana.id.html?aid=304142&label=gen173nr-1FCAEoggI46AdIM1gEaGiIAQGYARK4ARfIAQzYAQHoAQH4AQyIAgGoAgO4AquqkKkGwAIB0gIkYjM2MmQ0NjItYTQ5My00Y2Q2LWEwNjgtMDlkYTA1YmJmZDNj2AIG4AIB&sid=ae5ed9ff67052a78281a8b47cfa86da1&checkin=2023-12-30;checkout=2023-12-31;dest_id=9854911;dest_type=hotel;dist=0;group_adults=2;group_children=0;hapos=1;hpos=1;no_rooms=1;req_adults=2;req_children=0;room1=A%2CA;sb_price_type=total;soh=1;sr_order=popularity;srepoch=1696863968;srpvid=9a506a30c70500df;type=total;ucfs=1&#no_availability_msg";
        String a_ransha = "https://www.booking.com/hotel/id/cove-ransha-stay.id.html?aid=304142&label=gen173nr-1FCAEoggI46AdIM1gEaGiIAQGYARK4ARfIAQzYAQHoAQH4AQyIAgGoAgO4AquqkKkGwAIB0gIkYjM2MmQ0NjItYTQ5My00Y2Q2LWEwNjgtMDlkYTA1YmJmZDNj2AIG4AIB&sid=ae5ed9ff67052a78281a8b47cfa86da1&all_sr_blocks=1025127502_375237172_2_0_0;checkin=2023-12-30;checkout=2023-12-31;dest_id=10251275;dest_type=hotel;dist=0;group_adults=2;group_children=0;hapos=1;highlighted_blocks=1025127502_375237172_2_0_0;hpos=1;matching_block_id=1025127502_375237172_2_0_0;no_rooms=1;req_adults=2;req_children=0;room1=A%2CA;sb_price_type=total;sr_order=popularity;sr_pri_blocks=1025127502_375237172_2_0_0__58497800;srepoch=1696863988;srpvid=4dfa6a3a712300e0;type=total;ucfs=1&#hotelTmpl";
        String a_w_suite = "https://www.booking.com/hotel/id/cove-w-suites.id.html?aid=304142&label=gen173nr-1FCAEoggI46AdIM1gEaGiIAQGYARK4ARfIAQzYAQHoAQH4AQyIAgGoAgO4AquqkKkGwAIB0gIkYjM2MmQ0NjItYTQ5My00Y2Q2LWEwNjgtMDlkYTA1YmJmZDNj2AIG4AIB&sid=ae5ed9ff67052a78281a8b47cfa86da1&all_sr_blocks=845403602_351507078_2_0_0;checkin=2023-12-30;checkout=2023-12-31;dest_id=8454036;dest_type=hotel;dist=0;group_adults=2;group_children=0;hapos=1;highlighted_blocks=845403602_351507078_2_0_0;hpos=1;matching_block_id=845403602_351507078_2_0_0;no_rooms=1;req_adults=2;req_children=0;room1=A%2CA;sb_price_type=total;sr_order=popularity;sr_pri_blocks=845403602_351507078_2_0_0__56250000;srepoch=1696864015;srpvid=f9f86a46b653000c;type=total;ucfs=1&#hotelTmpl";
        String a_isvara = "https://www.booking.com/hotel/id/the-isvara-by-cove.id.html?aid=304142&label=gen173nr-1FCAEoggI46AdIM1gEaGiIAQGYARK4ARfIAQzYAQHoAQH4AQyIAgGoAgO4AquqkKkGwAIB0gIkYjM2MmQ0NjItYTQ5My00Y2Q2LWEwNjgtMDlkYTA1YmJmZDNj2AIG4AIB&sid=ae5ed9ff67052a78281a8b47cfa86da1&all_sr_blocks=1012728201_374790113_0_0_0;checkin=2023-12-30;checkout=2023-12-31;dest_id=10127282;dest_type=hotel;dist=0;group_adults=2;group_children=0;hapos=1;highlighted_blocks=1012728201_374790113_0_0_0;hpos=1;matching_block_id=1012728201_374790113_0_0_0;no_rooms=1;req_adults=2;req_children=0;room1=A%2CA;sb_price_type=total;sr_order=popularity;sr_pri_blocks=1012728201_374790113_0_0_0__217388930;srepoch=1696864044;srpvid=6c386a5614db0036;type=total;ucfs=1&#hotelTmpl";
        String a_guntur = "https://www.booking.com/hotel/id/cove-guntur-heritage.id.html?aid=304142&label=gen173nr-1FCAEoggI46AdIM1gEaGiIAQGYARK4ARfIAQzYAQHoAQH4AQyIAgGoAgO4AquqkKkGwAIB0gIkYjM2MmQ0NjItYTQ5My00Y2Q2LWEwNjgtMDlkYTA1YmJmZDNj2AIG4AIB&sid=ae5ed9ff67052a78281a8b47cfa86da1&checkin=2023-12-30;checkout=2023-12-31;dest_id=10673310;dest_type=hotel;dist=0;group_adults=2;group_children=0;hapos=1;hpos=1;no_rooms=1;req_adults=2;req_children=0;room1=A%2CA;sb_price_type=total;soh=1;sr_order=popularity;srepoch=1696864073;srpvid=dfbe6a64926200cb;type=total;ucfs=1&#no_availability_msg";
        String a_kanaya = "https://www.booking.com/hotel/id/cove-kanaya.id.html?aid=304142&label=gen173nr-1FCAEoggI46AdIM1gEaGiIAQGYARK4ARfIAQzYAQHoAQH4AQyIAgGoAgO4AquqkKkGwAIB0gIkYjM2MmQ0NjItYTQ5My00Y2Q2LWEwNjgtMDlkYTA1YmJmZDNj2AIG4AIB&sid=ae5ed9ff67052a78281a8b47cfa86da1&all_sr_blocks=1042960701_376691205_0_0_0;checkin=2023-12-30;checkout=2023-12-31;dest_id=10429607;dest_type=hotel;dist=0;group_adults=2;group_children=0;hapos=1;highlighted_blocks=1042960701_376691205_0_0_0;hpos=1;matching_block_id=1042960701_376691205_0_0_0;no_rooms=1;req_adults=2;req_children=0;room1=A%2CA;sb_price_type=total;sr_order=popularity;sr_pri_blocks=1042960701_376691205_0_0_0__48611100;srepoch=1696864214;srpvid=99916aab95580169;type=total;ucfs=1&#hotelTmpl";
        String a_horizone = "https://www.booking.com/hotel/id/cove-horizone.id.html?aid=304142&label=gen173nr-1FCAEoggI46AdIM1gEaGiIAQGYARK4ARjIAQzYAQHoAQH4AQyIAgGoAgS4AoGLgqoGwAIB0gIkOGY2NWMzMGQtMmMyYS00MDEyLWFhMTgtYTRiNjk3ZDg3MzE12AIG4AIB&sid=eee42981c49d1ad47e59fb4368713338&checkin=2023-12-30;checkout=2023-12-31;dest_id=10787920;dest_type=hotel;dist=0;group_adults=2;group_children=0;hapos=1;hpos=1;no_rooms=1;req_adults=2;req_children=0;room1=A%2CA;sb_price_type=total;soh=1;sr_order=popularity;srepoch=1698727316;srpvid=f7f92106875e006b;type=total;ucfs=1&#no_availability_msg";
        String a_oekude = "https://www.booking.com/hotel/id/cove-oekude-residence.id.html?aid=304142&label=gen173nr-1FCAEoggI46AdIM1gEaGiIAQGYARK4ARjIAQzYAQHoAQH4AQyIAgGoAgS4AoGLgqoGwAIB0gIkOGY2NWMzMGQtMmMyYS00MDEyLWFhMTgtYTRiNjk3ZDg3MzE12AIG4AIB&sid=eee42981c49d1ad47e59fb4368713338&all_sr_blocks=1075497501_0_2_0_0;checkin=2023-12-30;checkout=2023-12-31;dest_id=10754975;dest_type=hotel;dist=0;group_adults=2;group_children=0;hapos=1;highlighted_blocks=1075497501_0_2_0_0;hpos=1;matching_block_id=1075497501_0_2_0_0;no_rooms=1;req_adults=2;req_children=0;room1=A%2CA;sb_price_type=total;sr_order=popularity;sr_pri_blocks=1075497501_0_2_0_0__19798044;srepoch=1698727345;srpvid=ca84211767460291;type=total;ucfs=1&#hotelTmpl";
        String a_bonaSort = "https://www.booking.com/hotel/id/cove-bona-sort.id.html?aid=304142&label=gen173nr-1FCAEoggI46AdIM1gEaGiIAQGYARK4ARjIAQzYAQHoAQH4AQyIAgGoAgS4AoGLgqoGwAIB0gIkOGY2NWMzMGQtMmMyYS00MDEyLWFhMTgtYTRiNjk3ZDg3MzE12AIG4AIB&sid=eee42981c49d1ad47e59fb4368713338&all_sr_blocks=1064317401_380532257_0_0_0;checkin=2023-12-30;checkout=2023-12-31;dest_id=10643174;dest_type=hotel;dist=0;group_adults=2;group_children=0;hapos=1;highlighted_blocks=1064317401_380532257_0_0_0;hpos=1;matching_block_id=1064317401_380532257_0_0_0;no_rooms=1;req_adults=2;req_children=0;room1=A%2CA;sb_price_type=total;sr_order=popularity;sr_pri_blocks=1064317401_380532257_0_0_0__40000000;srepoch=1698727385;srpvid=6c96212b81ff006d;type=total;ucfs=1&#hotelTmpl";
        String a_skyCitiHome = "https://www.booking.com/hotel/id/cove-sky-city-home.id.html?aid=304142&label=gen173nr-1FCAEoggI46AdIM1gEaGiIAQGYARK4ARjIAQzYAQHoAQH4AQyIAgGoAgS4AoGLgqoGwAIB0gIkOGY2NWMzMGQtMmMyYS00MDEyLWFhMTgtYTRiNjk3ZDg3MzE12AIG4AIB&sid=eee42981c49d1ad47e59fb4368713338&all_sr_blocks=0_0_2_0_0;checkin=2023-12-30;checkout=2023-12-31;dest_id=10843351;dest_type=hotel;dist=0;group_adults=2;group_children=0;hapos=1;highlighted_blocks=0_0_2_0_0;hpos=1;matching_block_id=0_0_2_0_0;no_rooms=1;req_adults=2;req_children=0;room1=A%2CA;sb_price_type=total;sr_order=popularity;sr_pri_blocks=0_0_2_0_0__32231598;srepoch=1698727412;srpvid=192b2138e3e00107;type=total;ucfs=1&#hotelTmpl";
        String a_senja = "https://www.booking.com/hotel/id/cove-senja.id.html?aid=304142&label=gen173nr-1FCAEoggI46AdIM1gEaGiIAQGYARK4ARjIAQzYAQHoAQH4AQyIAgGoAgS4Ao7SqqwGwAIB0gIkMWFlMjZjOWYtMWIyOC00YTk3LWFkNDUtY2UyZGY5NmRjMzll2AIG4AIB&sid=56b41b34d1f88d691fbb1f0662c1e8d4&all_sr_blocks=0_0_2_0_0;checkin=2023-12-26;checkout=2023-12-27;dest_id=10888374;dest_type=hotel;dist=0;group_adults=2;group_children=0;hapos=1;highlighted_blocks=0_0_2_0_0;hpos=1;matching_block_id=0_0_2_0_0;no_rooms=1;req_adults=2;req_children=0;room1=A%2CA;sb_price_type=total;sr_order=popularity;sr_pri_blocks=0_0_2_0_0__33442583;srepoch=1703586302;srpvid=e79b493fb02b05be;type=total;ucfs=1&#hotelTmpl";
        String a_griya_elite = "https://www.booking.com/hotel/id/griya-elite-i.id.html?aid=304142&label=gen173nr-1FCAEoggI46AdIM1gEaGiIAQGYARK4ARjIAQzYAQHoAQH4AQyIAgGoAgS4Ao7SqqwGwAIB0gIkMWFlMjZjOWYtMWIyOC00YTk3LWFkNDUtY2UyZGY5NmRjMzll2AIG4AIB&sid=56b41b34d1f88d691fbb1f0662c1e8d4&checkin=2023-12-26;checkout=2023-12-27;dest_id=8478255;dest_type=hotel;dist=0;group_adults=2;group_children=0;hapos=1;hpos=1;no_rooms=1;req_adults=2;req_children=0;room1=A%2CA;sb_price_type=total;soh=1;sr_order=popularity;srepoch=1703586488;srpvid=df40499bce9e0674;type=total;ucfs=1&#no_availability_msg";
        String a_matahari ="https://www.booking.com/hotel/id/cove-matahari-guesthouse-sunset.id.html?aid=304142&label=gen173nr-1FCAEoggI46AdIM1gEaGiIAQGYARK4ARjIAQzYAQHoAQH4AQyIAgGoAgS4Ao7SqqwGwAIB0gIkMWFlMjZjOWYtMWIyOC00YTk3LWFkNDUtY2UyZGY5NmRjMzll2AIG4AIB&sid=56b41b34d1f88d691fbb1f0662c1e8d4&checkin=2023-12-26;checkout=2023-12-27;dest_id=9273985;dest_type=hotel;dist=0;group_adults=2;group_children=0;hapos=1;hpos=1;no_rooms=1;req_adults=2;req_children=0;room1=A%2CA;sb_price_type=total;soh=1;sr_order=popularity;srepoch=1703586545;srpvid=a9d549ae6efe0062;type=total;ucfs=1&#no_availability_msg" ;
        String a_depavilla = "https://www.booking.com/hotel/id/cove-de-pavila.id.html?aid=304142&label=gen173nr-1FCAEoggI46AdIM1gEaGiIAQGYARK4ARjIAQzYAQHoAQH4AQyIAgGoAgS4Ao7SqqwGwAIB0gIkMWFlMjZjOWYtMWIyOC00YTk3LWFkNDUtY2UyZGY5NmRjMzll2AIG4AIB&sid=56b41b34d1f88d691fbb1f0662c1e8d4&checkin=2023-12-26;checkout=2023-12-27;dest_id=11032060;dest_type=hotel;dist=0;group_adults=2;group_children=0;hapos=1;hpos=1;no_rooms=1;req_adults=2;req_children=0;room1=A%2CA;sb_price_type=total;soh=1;sr_order=popularity;srepoch=1703586592;srpvid=d5bb49cf4ebc040c;type=total;ucfs=1&#no_availability_msg";
        //de tinaya not real
        String a_detinaya = "https://www.booking.com/hotel/id/cove-de-pavila.id.html?aid=304142&label=gen173nr-1FCAEoggI46AdIM1gEaGiIAQGYARK4ARjIAQzYAQHoAQH4AQyIAgGoAgS4Ao7SqqwGwAIB0gIkMWFlMjZjOWYtMWIyOC00YTk3LWFkNDUtY2UyZGY5NmRjMzll2AIG4AIB&sid=56b41b34d1f88d691fbb1f0662c1e8d4&checkin=2023-12-26;checkout=2023-12-27;dest_id=11032060;dest_type=hotel;dist=0;group_adults=2;group_children=0;hapos=1;hpos=1;no_rooms=1;req_adults=2;req_children=0;room1=A%2CA;sb_price_type=total;soh=1;sr_order=popularity;srepoch=1703586592;srpvid=d5bb49cf4ebc040c;type=total;ucfs=1&#no_availability_msg";
        String a_raddeyapa = "https://www.booking.com/hotel/id/rade-guesthouse.id.html?aid=304142&label=gen173nr-1FCAEoggI46AdIM1gEaGiIAQGYARK4ARjIAQzYAQHoAQH4AQyIAgGoAgS4Avuf9q4GwAIB0gIkZmU1MGJlNGItY2U5Zi00Y2MwLWE3OWItNTZhYmM2YWZkOTAy2AIG4AIB&sid=56b41b34d1f88d691fbb1f0662c1e8d4&all_sr_blocks=191244906_0_2_0_0;checkin=2024-05-23;checkout=2024-05-24;dest_id=1912449;dest_type=hotel;dist=0;group_adults=2;group_children=0;hapos=1;highlighted_blocks=191244906_0_2_0_0;hpos=1;matching_block_id=191244906_0_2_0_0;no_rooms=1;req_adults=2;req_children=0;room1=A%2CA;sb_price_type=total;sr_order=popularity;sr_pri_blocks=191244906_0_2_0_0__24632527;srepoch=1709019346;srpvid=3cfb355e37c30046;type=total;ucfs=1&#hotelTmpl";
        String a_veranda = "https://www.booking.com/hotel/id/cove-veranda.id.html?aid=304142&label=gen173nr-1FCAEoggI46AdIM1gEaGiIAQGYARK4ARjIAQzYAQHoAQH4AQyIAgGoAgS4Avuf9q4GwAIB0gIkZmU1MGJlNGItY2U5Zi00Y2MwLWE3OWItNTZhYmM2YWZkOTAy2AIG4AIB&sid=56b41b34d1f88d691fbb1f0662c1e8d4&all_sr_blocks=1138704902_386582668_2_0_0;checkin=2024-05-23;checkout=2024-05-24;dest_id=11387049;dest_type=hotel;dist=0;group_adults=2;group_children=0;hapos=1;highlighted_blocks=1138704902_386582668_2_0_0;hpos=1;matching_block_id=1138704902_386582668_2_0_0;no_rooms=1;req_adults=2;req_children=0;room1=A%2CA;sb_price_type=total;sr_order=popularity;sr_pri_blocks=1138704902_386582668_2_0_0__20520000;srepoch=1709019479;srpvid=03ec359eafef0098;type=total;ucfs=1&#hotelTmpl";
        String a_riverhaus ="https://www.booking.com/hotel/id/cove-river-haus.id.html?aid=304142&label=gen173nr-1FCAEoggI46AdIM1gEaGiIAQGYARK4ARjIAQzYAQHoAQH4AQyIAgGoAgS4Avuf9q4GwAIB0gIkZmU1MGJlNGItY2U5Zi00Y2MwLWE3OWItNTZhYmM2YWZkOTAy2AIG4AIB&sid=56b41b34d1f88d691fbb1f0662c1e8d4&all_sr_blocks=1130829801_385738430_0_0_0;checkin=2024-05-23;checkout=2024-05-24;dest_id=11308298;dest_type=hotel;dist=0;group_adults=2;group_children=0;hapos=1;highlighted_blocks=1130829801_385738430_0_0_0;hpos=1;matching_block_id=1130829801_385738430_0_0_0;no_rooms=1;req_adults=2;req_children=0;room1=A%2CA;sb_price_type=total;sr_order=popularity;sr_pri_blocks=1130829801_385738430_0_0_0__30000000;srepoch=1709019568;srpvid=b87835d63a09017e;type=total;ucfs=1&#hotelTmpl";
        String a_nawaprita = "https://www.booking.com/hotel/id/cove-nawaprita.id.html?aid=304142&label=gen173nr-1FCAEoggI46AdIM1gEaGiIAQGYARK4ARjIAQzYAQHoAQH4AQyIAgGoAgS4Avuf9q4GwAIB0gIkZmU1MGJlNGItY2U5Zi00Y2MwLWE3OWItNTZhYmM2YWZkOTAy2AIG4AIB&sid=56b41b34d1f88d691fbb1f0662c1e8d4&all_sr_blocks=1143919501_0_2_0_0;checkin=2024-05-23;checkout=2024-05-24;dest_id=11439195;dest_type=hotel;dist=0;group_adults=2;group_children=0;hapos=1;highlighted_blocks=1143919501_0_2_0_0;hpos=1;matching_block_id=1143919501_0_2_0_0;no_rooms=1;req_adults=2;req_children=0;room1=A%2CA;sb_price_type=total;sr_order=popularity;sr_pri_blocks=1143919501_0_2_0_0__33979133;srepoch=1709019801;srpvid=0f3b3647c1870285;type=total;ucfs=1&#hotelTmpl";
        String a_urbanier = "https://www.booking.com/hotel/id/cove-urbanier.id.html?aid=304142&label=gen173nr-1FCAEoggI46AdIM1gEaGiIAQGYARK4ARjIAQzYAQHoAQH4AQyIAgGoAgS4Avuf9q4GwAIB0gIkZmU1MGJlNGItY2U5Zi00Y2MwLWE3OWItNTZhYmM2YWZkOTAy2AIG4AIB&sid=56b41b34d1f88d691fbb1f0662c1e8d4&all_sr_blocks=1143924701_0_2_0_0;checkin=2024-05-23;checkout=2024-05-24;dest_id=11439247;dest_type=hotel;dist=0;group_adults=2;group_children=0;hapos=1;highlighted_blocks=1143924701_0_2_0_0;hpos=1;matching_block_id=1143924701_0_2_0_0;no_rooms=1;req_adults=2;req_children=0;room1=A%2CA;sb_price_type=total;sr_order=popularity;sr_pri_blocks=1143924701_0_2_0_0__29118273;srepoch=1709019873;srpvid=bc45366bc5a9002e;type=total;ucfs=1&#hotelTmpl";
        String a_casagio = "https://www.booking.com/hotel/id/cove-casagio.id.html?aid=304142&label=gen173nr-1FCAEoggI46AdIM1gEaGiIAQGYARK4ARjIAQzYAQHoAQH4AQuIAgGoAgS4Ao_iwbEGwAIB0gIkOGUxNjU1ZTAtMTMzNi00OTg3LWIwYjEtNWM5NWZmNjEzZDQ32AIG4AIB&sid=56b41b34d1f88d691fbb1f0662c1e8d4&all_sr_blocks=1155113201_0_2_0_0;checkin=2024-04-30;checkout=2024-05-01;dest_id=11551132;dest_type=hotel;dist=0;group_adults=2;group_children=0;hapos=1;highlighted_blocks=1155113201_0_2_0_0;hpos=1;matching_block_id=1155113201_0_2_0_0;no_rooms=1;req_adults=2;req_children=0;room1=A%2CA;sb_price_type=total;sr_order=popularity;sr_pri_blocks=1155113201_0_2_0_0__49928932;srepoch=1714450750;srpvid=0b981e4d869d008b;type=total;ucfs=1&#hotelTmpl";
        String a_tripurihouse = "https://www.booking.com/hotel/id/cove-tripuri-house-bali.id.html?aid=304142&label=gen173nr-1FCAEoggI46AdIM1gEaGiIAQGYARK4ARjIAQzYAQHoAQH4AQuIAgGoAgS4Ao_iwbEGwAIB0gIkOGUxNjU1ZTAtMTMzNi00OTg3LWIwYjEtNWM5NWZmNjEzZDQ32AIG4AIB&sid=56b41b34d1f88d691fbb1f0662c1e8d4&checkin=2024-04-30;checkout=2024-05-01;dest_id=11587290;dest_type=hotel;dist=0;group_adults=2;group_children=0;hapos=1;hpos=1;no_rooms=1;req_adults=2;req_children=0;room1=A%2CA;sb_price_type=total;soh=1;sr_order=popularity;srepoch=1714450804;srpvid=9b941e77399f0167;type=total;ucfs=1&#no_availability_msg";
        String[] linkBooking = {a_Hillcrest, a_t63, a_theday, a_taman, a_west_inn, a_arimbi, a_accordia, a_birah, a_renata, a_tevana, a_ransha, a_w_suite, a_isvara, a_guntur, a_kanaya, a_horizone, a_oekude, a_bonaSort, a_skyCitiHome,a_senja,a_griya_elite,a_matahari,a_depavilla,a_detinaya,a_raddeyapa,a_veranda,a_riverhaus,a_nawaprita,a_urbanier,a_casagio,a_tripurihouse};
        int[] row_Booking = {3, 7, 11, 15, 19, 23, 27, 31, 35, 39, 43, 47, 51, 55, 59, 63, 67, 71, 75,79,83,87,91,95,99,103,107,111,115,119,123};
        int[] row_Traveloka = {5, 9, 13, 17, 21, 25, 29, 33, 37, 41, 45, 49, 53, 57, 61, 65, 69, 73, 77,81,85,89,93,97,101,105,109,113,117,121,125};

       //Booking booking_com = new Booking(linkBooking, row_Booking);
       //booking_com.writeExcel();

        String[] linkTraveloka = {hillcrest, t63, theday, taman, west_inn, arimbi, accordia, birah, renata, tevana, ransha, w_suite, isvara, guntur, kanaya, horizone, oekude, bonaSort, skyCitiHome,senja,griya_elite,matahari,depavilla,detinaya,raddeyapa,veranda,riverhaus,nawaprita,urbanier,casagio,tripurihouse};
        Traveloka traveloka = new Traveloka(linkTraveloka, row_Traveloka);
        traveloka.writeExcel();

    }
}
