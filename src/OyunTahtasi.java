import java.util.Scanner;

public class OyunTahtasi {
    private char[][] tahta;
    private boolean[][] atisYapildi;
    public void atisGoster() {//cozemedim bunu hala
        System.out.println("Atış yapılan noktaların gösterildiği tahta:");
        for (int i = 0; i < tahta.length; i++) {
            for (int j = 0; j < tahta[i].length; j++) {
                if (atisYapildi[i][j]) {
                    System.out.print("X ");
                } else {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
    }


    public OyunTahtasi(char[][] tahta, boolean[][] atisYapildi) {
        this.tahta = tahta;
        this.atisYapildi = atisYapildi;
    }

    public void tahtayiGoster() {
        System.out.print("  ");
        for (int i = 0; i < tahta.length; i++) {
            System.out.print((char) ('A' + i) + " ");
        }
        System.out.println();
        for (int i = 0; i < tahta.length; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < tahta[i].length; j++) {
                System.out.print(tahta[i][j] + " ");
            }
            System.out.println();
        }
    }
    public OyunTahtasi(int boyut) {
        this.tahta = new char[boyut][boyut];
        initializeTahta();
    }
    private void initializeTahta() {
        for (int i = 0; i < tahta.length; i++) {
            for (int j = 0; j < tahta[i].length; j++) {
                tahta[i][j] = '-';
            }
        }
    }

    public void gemiYerlestir(int gemiBoyutu) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Başlangıç koordinatlarını girin (örn. A1):");
            String baslangicKoordinat = scanner.next().toUpperCase();
            char baslangicHarf = baslangicKoordinat.charAt(0);
            int baslangicRakam = Integer.parseInt(baslangicKoordinat.substring(1));
            if (baslangicHarf < 'A' || baslangicHarf >= (char) ('A' + tahta.length) || baslangicRakam < 1 || baslangicRakam > tahta.length) {
                System.out.println("Geçersiz koordinatlar. Lütfen tekrar deneyin.");
                continue;
            }
            int baslangicSatir = baslangicRakam - 1;
            int baslangicSutun = baslangicHarf - 'A';
            System.out.println("Yönü belirleyin (yatay için H, dikey için V):");
            char yon = scanner.next().toUpperCase().charAt(0);
            if (yon != 'H' && yon != 'V') {
                System.out.println("Geçersiz yön. Lütfen tekrar deneyin.");
                continue;
            }
            if ((yon == 'H' && baslangicSutun + gemiBoyutu > tahta.length) || (yon == 'V' && baslangicSatir + gemiBoyutu > tahta.length)) {
                System.out.println("Gemi tahtanın dışına taşıyor. Lütfen yeni bir koordinat girin.");
                continue;
            }
            boolean yerleştirmeBaşarılı = true;
            if (yon == 'H') {
                for (int i = 0; i < gemiBoyutu; i++) {
                    if (tahta[baslangicSatir][baslangicSutun + i] != '-') {
                        System.out.println("Gemi mevcut bir konumun üzerine yerleştirilemez. Lütfen yeni bir koordinat girin.");
                        yerleştirmeBaşarılı = false;
                        break;
                    }
                }
                if (yerleştirmeBaşarılı) {
                    for (int i = 0; i < gemiBoyutu; i++) {
                        tahta[baslangicSatir][baslangicSutun + i] = 'O';
                    }
                }
            } else {
                for (int i = 0; i < gemiBoyutu; i++) {
                    if (tahta[baslangicSatir + i][baslangicSutun] != '-') {
                        System.out.println("Gemi mevcut bir konumun üzerine yerleştirilemez. Lütfen yeni bir koordinat girin.");
                        yerleştirmeBaşarılı = false;
                        break;
                    }
                }
                if (yerleştirmeBaşarılı) {
                    for (int i = 0; i < gemiBoyutu; i++) {
                        tahta[baslangicSatir + i][baslangicSutun] = 'O';
                    }
                }
            }

            if (yerleştirmeBaşarılı) {
                break;
            }
        }
        tahtayiGoster();
    }
    private boolean gemiKaldiMi() {
        for (int i = 0; i < tahta.length; i++) {
            for (int j = 0; j < tahta[i].length; j++) {
                if (tahta[i][j] == 'O') {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean oyunBittiMi() {
        return  !gemiKaldiMi();
    }


    public boolean isabetliAtis(int satir, int sutun) {
        if (tahta[satir][sutun] == 'X') {
            System.out.println("Bu hedef zaten vurulmuş!");

            return false;
        } else if (tahta[satir][sutun] == 'O') {
            System.out.println("Tebrikler! İsabet ettiniz.");
            tahta[satir][sutun] = 'X';

            return true;
        } else {
            System.out.println("Üzgünüm, isabet etmediniz.");

            return false;
        }
    }

}
