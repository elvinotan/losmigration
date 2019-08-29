package com.btpn.migration.los.constant;

import java.util.HashMap;
import java.util.Map;

public class DataMapping {

	// Cara ambil dari excel
	// Cari sumbernya dari Data.Validity dapat Dati_3
	// Ketik names define Dati_3 akan mengarah ke Sheet Dati_3
	// Lalu pilih Data Consilidate akan muncul copy to $Value.$AX1
	// Buat sheet baru lalu copy $Value.$AX1 copy aja ke bawah
	
	private Map<String, String> dati2Map = new HashMap<String, String>();
	
	private static DataMapping dataMapping = null;
	
	private DataMapping() {		
		//Mapping Dari Dati2 yang ada di excel (InformasiDebitur.H121) ke  CommonService TypeCity
		dati2Map.put("Bekasi, Kab. - 0102", "KAB. BEKASI");
		dati2Map.put("Ambon, Kota. - 8191", "KOTA AMBON");
		dati2Map.put("Badung, Kab. - 7204", "KAB. BANDUNG");
		dati2Map.put("Balikpapan, Kota. - 5492", "KOTA BALIKPAPAN");
		dati2Map.put("Bandar Lampung, Kota. - 3991", "KOTA BANDAR LAMPUNG");
		dati2Map.put("Bandung Barat, Kab - 0122", "KAB. BANDUNG BARAT");
		dati2Map.put("Bandung, Kab. - 0111", "KAB. BANDUNG");
		dati2Map.put("Bandung, Kota. - 0191", "KOTA BANDUNG");
		dati2Map.put("Bandung, Kota. - 0192", "KOTA BANDUNG");
		dati2Map.put("Banggai Kepulauan, Kab - 6005", "KAB. BANGGAI KEPULAUAN");
		dati2Map.put("Bangka Belitung, Kab - 3707", "");
		dati2Map.put("Banjar, Kab. - 5101", "KAB. BANJAR");
		dati2Map.put("Banjarbaru, Kota. - 5192", "KOTA BANJARBARU");
		dati2Map.put("Banjarmasin, Kota. - 5191", "KOTA BANJARMASIN");
		dati2Map.put("Bantul, Kab. - 0501", "KAB. BANTUL");
		dati2Map.put("Banyumas, Kab. - 0914", "KAB. BANYUMAS");
		dati2Map.put("Banyuwangi, Kab. - 1211", "KAB. BANYUWANGI");
		dati2Map.put("Batam, Kota - 3892", "KOTA BATAM");
		dati2Map.put("Batang, Kab. - 0929", "KAB. BATANG");
		dati2Map.put("Batanghari, Kab. - 3101", "KAB. BATANGHARI");
		dati2Map.put("Batu, Kota. - 1271", "KOTA BATU");
		dati2Map.put("Bekasi, Kota. - 0198", "KOTA BEKASI");
		dati2Map.put("Belu, Kab. - 7404", "KAB. BELU");
		dati2Map.put("Bengkalis, Kab. - 3502", "KAB. BENGKALIS");
		dati2Map.put("Binjai, Kota. - 3392", "KOTA BINJAI");
		dati2Map.put("Binjai, Kota. - 3393", "KOTA BINJAI");
		dati2Map.put("Blora, Kab. - 0913", "KAB. BLORA");
		dati2Map.put("Bogor, Kab. - 0108", "KAB. BOGOR");
		dati2Map.put("Bogor, Kota. - 0192", "KOTA BOGOR");
		dati2Map.put("Bogor, Kota. - 0193", "KOTA BOGOR");
		dati2Map.put("Bogor, Kota. - 0194", "KOTA BOGOR");
		dati2Map.put("Bombana, Kab. - 6908", "KAB. BOMBANA");
		dati2Map.put("Bulukumba, Kab. - 6111", "KAB. BULUKUMBA");
		dati2Map.put("Cilacap, Kab. - 0915", "KAB. CILACAP");
		dati2Map.put("Cilegon, Kota. - 0291", "KOTA CILEGON");
		dati2Map.put("Cimahi, Kota. - 0196", "KOTA CIMAHI");
		dati2Map.put("Cirebon, Kab. - 0116", "KAB. CIREBON");
		dati2Map.put("Cirebon, Kota. - 0194", "KOTA CIREBON");
		dati2Map.put("Deli Serdang, Kab. - 3301", "KAB. DELI SERDANG");
		dati2Map.put("Demak, Kab. - 0903", "KAB. DEMAK");
		dati2Map.put("Denpasar, Kota. - 7291", "KOTA DENPASAR");
		dati2Map.put("Depok, Kota. - 0197", "KOTA DEPOK");
		dati2Map.put("Gianyar, Kab. - 7205", "KAB. GIANYAR");
		dati2Map.put("Gorontalo, Kota. - 6391", "KOTA GORONTALO");
		dati2Map.put("Gowa, Kab. - 6102", "KAB. GOWA");
		dati2Map.put("Gresik, Kab. - 1201", "KAB. GRESIK");
		dati2Map.put("Grobogan, Kab. - 0904", "KAB. GROBOGAN");
		dati2Map.put("Jakarta Barat, Wil. Kota - 0393", "KOTA ADM. JAKARTA BARAT");
		dati2Map.put("Jakarta Barat, Wil. Kota - 0394", "KOTA ADM. JAKARTA BARAT");
		dati2Map.put("Jakarta Barat, Wil. Kota - 0395", "KOTA ADM. JAKARTA BARAT");
		dati2Map.put("Jakarta Pusat, Wil. Kota - 0391", "KOTA ADM. JAKARTA PUSAT");
		dati2Map.put("Jakarta Pusat, Wil. Kota - 0392", "KOTA ADM. JAKARTA PUSAT");
		dati2Map.put("Jakarta Pusat, Wil. Kota - 0393", "KOTA ADM. JAKARTA PUSAT");
		dati2Map.put("Jakarta Selatan, Wil. Kota - 0394", "KOTA ADM. JAKARTA SELATAN");
		dati2Map.put("Jakarta Timur, Wil. Kota - 0395", "KOTA ADM. JAKARTA TIMUR");
		dati2Map.put("Jakarta Timur, Wil. Kota - 0396", "KOTA ADM. JAKARTA TIMUR");
		dati2Map.put("Jakarta Utara ., Wil. Kota - 0392", "KOTA ADM. JAKARTA UTARA");
		dati2Map.put("Jambi, Kota. - 3191", "KOTA JAMBI");
		dati2Map.put("Jember, Kab. - 1212", "KAB. JEMBER");
		dati2Map.put("Jembrana, Kab. - 7202", "KAB. JEMBRANA");
		dati2Map.put("Jepara, Kab. - 0911", "KAB. JEPARA");
		dati2Map.put("Kampar, Kab. - 3501", "KAB. KAMPAR");
		dati2Map.put("Karanganyar, Kab. - 0927", "KAB. KARANGANYAR");
		dati2Map.put("Karawang, Kab. - 0106", "KAB. KARAWANG");
		dati2Map.put("Kediri, Kota. - 1297", "KOTA KEDIRI");
		dati2Map.put("Kendal, Kab. - 0902", "KAB. KENDAL");
		dati2Map.put("Kendari, Kota. - 6991", "KOTA KENDARI");
		dati2Map.put("Ketapang, Kab. - 5303", "KAB. KETAPANG");
		dati2Map.put("Konawe, Kab. - 6906", "KAB. KONAWE");
		dati2Map.put("Kota Tangerang Selatan - 0294", "KOTA TANGERANG SELATAN");
		dati2Map.put("Kotawaringin Timur, Kab. - 5803", "KAB. KOTAWARINGIN TIMUR");
		dati2Map.put("Kubu Raya, Kab. - 5312", "KAB. KUBU RAYA");
		dati2Map.put("Kudus, Kab. - 0909", "KAB. KUDUS");
		dati2Map.put("Kuningan, Kab. - 0117", "KAB. KUNINGAN");
		dati2Map.put("Kupang, Kota. - 7491", "KOTA KUPANG");
		dati2Map.put("Labuhan Batu, Kab. - 3305", "KAB. LABUHANBATU");
		dati2Map.put("Lampung Selatan, Kab. - 3901", "KAB. LAMPUNG SELATAN");
		dati2Map.put("Lampung Timur, Kab. - 3907", "KAB. LAMPUNG TIMUR");
		dati2Map.put("Lampung Utara, Kab. - 3903", "KAB. LAMPUNG UTARA");
		dati2Map.put("Langkat, Kab. - 3302", "KAB. LANGKAT");
		dati2Map.put("Lombok Tengah, Kab. - 7102", "KAB. LOMBOK TENGAH");
		dati2Map.put("Luwu, Kab. - 6109", "KAB. LUWU");
		dati2Map.put("Madiun, Kab. - 1222", "KAB. MADIUN");
		dati2Map.put("Madiun, Kota. - 1298", "KOTA MADIUN");
		dati2Map.put("Magelang, Kab. - 0918", "KAB. MAGELANG");
		dati2Map.put("Magelang, Kota. - 0995", "KOTA MAGELANG");
		dati2Map.put("Majalengka, Kab. - 0119", "KAB. MAJALENGKA");
		dati2Map.put("Makassar, Kota. - 6191", "KOTA MAKASSAR");
		dati2Map.put("Malang, Kab. - 1213", "KAB. MALANG");
		dati2Map.put("Malang, Kota. - 1293", "KOTA MALANG");
		dati2Map.put("Malang, Kota. - 1294", "KOTA MALANG");
		dati2Map.put("Malinau, Kab. - 5410", "KAB. MALINAU");
		dati2Map.put("Maros, Kab. - 6107", "KAB. MAROS");
		dati2Map.put("Mataram, Kota. - 7191", "KOTA MATARAM");
		dati2Map.put("Medan, Kota. - 3396", "KOTA MEDAN");
		dati2Map.put("Medan, Kota. - 3397", "KOTA MEDAN");
		dati2Map.put("Menado, Kota. - 6291", "KOTA MANADO");
		dati2Map.put("Metro, Kota. - 3992", "KOTA METRO");
		dati2Map.put("Minahasa, Kab. - 6202", "KAB. MINAHASA");
		dati2Map.put("Mojokerto, Kab. - 1203", "KAB. MOJOKERTO");
		dati2Map.put("Mojokerto, Kota. - 1292", "KOTA MOJOKERTO");
		dati2Map.put("Padang, Kota. - 3492", "KOTA PADANG");
		dati2Map.put("Palembang, Kota. - 3691", "KOTA PALEMBANG");
		dati2Map.put("Palu, Kota. - 6091", "KOTA PALU");
		dati2Map.put("Pangkajene Kepulauan, Kab. - 6118", "KAB. PANGKAJENE KEPULAUAN");
		dati2Map.put("Pasuruan, Kab. - 1214", "KAB. PASURUAN");
		dati2Map.put("Pati, Kab. - 0908", "KAB. PATI");
		dati2Map.put("Pekanbaru, Kota. - 3591", "KOTA PEKANBARU");
		dati2Map.put("Pelalawan, Kab. - 3510", "KAB. PELALAWAN");
		dati2Map.put("Pemalang, Kab. - 0910", "KAB. PEMALANG");
		dati2Map.put("Pematang Siantar, Kota. - 3393", "KOTA PEMATANG SIANTAR");
		dati2Map.put("Ponorogo, Kab. - 1225", "KAB. PONOROGO");
		dati2Map.put("Pontianak, Kab. - 5301", "");
		dati2Map.put("Pontianak, Kota. - 5391", "KOTA PONTIANAK");
		dati2Map.put("Pontianak, Kota. - 5392", "KOTA PONTIANAK");
		dati2Map.put("Pringsewu, Kab - 3910", "KAB. PRINGSEWU");
		dati2Map.put("Probolinggo, Kota. - 1295", "KOTA PROBOLINGGO");
		dati2Map.put("Purbalingga, Kab. - 0916", "KAB. PURBALINGGA");
		dati2Map.put("Purworejo, Kab. - 0921", "KAB. PURWOREJO");
		dati2Map.put("Rembang, Kab. - 0912", "KAB. REMBANG");
		dati2Map.put("Salatiga, Kota. - 0992", "KOTA SALATIGA");
		dati2Map.put("Samarinda, Kota. - 5491", "KOTA SAMARINDA");
		dati2Map.put("Samarinda, Kota. - 5492", "KOTA SAMARINDA");
		dati2Map.put("Sambas, Kab. - 5302", "KAB. SAMBAS");
		dati2Map.put("Selayar, Kab. - 6114", "KAB. KEPULAUAN SELAYAR");
		dati2Map.put("Semarang, Kab. - 0901", "KAB. SEMARANG");
		dati2Map.put("Semarang, Kota. - 0991", "KOTA SEMARANG");
		dati2Map.put("Semarang, Kota. - 0992", "KOTA SEMARANG");
		dati2Map.put("Serang, Kab. - 0203", "KAB. SERANG");
		dati2Map.put("Serang. Kota. - 0293", "KOTA SERANG");
		dati2Map.put("Serdang Bedagai, Kab - 3319", "KAB. SERDANG BEDAGAI");
		dati2Map.put("Siak, Kab. - 3511", "KAB. SIAK");
		dati2Map.put("Sidoarjo, Kab. - 1202", "KAB. SIDOARJO");
		dati2Map.put("Singkawang, Kota. - 5392", "KOTA SINGKAWANG");
		dati2Map.put("Sleman, Kab. - 0502", "KAB. SLEMAN");
		dati2Map.put("Subang, Kab. - 0121", "KAB. SUBANG");
		dati2Map.put("Sukoharjo, Kab. - 0926", "KAB. SUKOHARJO");
		dati2Map.put("Sumbawa, Kab. - 7104", "KAB. SUMBAWA");
		dati2Map.put("Surabaya, Kota. - 1291", "KOTA SURABAYA");
		dati2Map.put("Surabaya, Kota. - 1292", "KOTA SURABAYA");
		dati2Map.put("Surakarta, Kota. - 0996", "KOTA SURAKARTA");
		dati2Map.put("Tabalong, Kab. - 5109", "KAB. TABALONG");
		dati2Map.put("Tabanan, Kab. - 7203", "KAB. TABANAN");
		dati2Map.put("Takalar, Kab. - 6115", "KAB. TAKALAR");
		dati2Map.put("Tangerang, Kab. - 0204", "KAB. TANGERANG");
		dati2Map.put("Tangerang, Kota. - 0292", "KOTA TANGERANG");
		dati2Map.put("Tangerang, Kota. - 0293", "KOTA TANGERANG");
		dati2Map.put("Tangerang, Kota. - 0294", "KOTA TANGERANG");
		dati2Map.put("Tanjungpinang, Kota - 3891", "KOTA TANJUNG PINANG");
		dati2Map.put("Tarakan, Kota. - 5493", "KOTA TARAKAN");
		dati2Map.put("Tasikmalaya, Kota. - 0195", "KOTA TASIKMALAYA");
		dati2Map.put("Tasikmalaya, Kota. - 0196", "KOTA TASIKMALAYA");
		dati2Map.put("Tasikmalaya, Kota. - 0197", "KOTA TASIKMALAYA");
		dati2Map.put("Tegal, Kab. - 0906", "KAB. TEGAL");
		dati2Map.put("Tegal, Kota. - 0994", "KOTA TEGAL");
		dati2Map.put("Temanggung, Kab. - 0919", "KAB. TEMANGGUNG");
		dati2Map.put("Tomohon, Kota - 6294", "KOTA TOMOHON");
		dati2Map.put("Tulang Bawang, Kab. - 3905", "KAB. TULANG BAWANG");
		dati2Map.put("Tulungagung, Kab. - 1219", "KAB. TULUNGAGUNG");
		dati2Map.put("Wonogiri, Kab. - 0928", "KAB. WONOGIRI");
		dati2Map.put("Yapen-Waropen, Kab. - 8210", "KAB. WAROPEN");
		dati2Map.put("Yogyakarta, Kota. - 0591", "KOTA YOGYAKARTA");
	}
	
	public static DataMapping get() {
		if (dataMapping == null) dataMapping = new DataMapping();
		return dataMapping;
	}
	
	public String getDati2Mapping(String dati2) {
		return dati2Map.get(dati2);
	}
}
