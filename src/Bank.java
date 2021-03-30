public class Bank {

    private final int MAX_KONTEN = 100;
    private Konto[] konten = new Konto[MAX_KONTEN];
    private int belegteKonten = 0;
    private int monat = 0;
    
    private Konto kontoSuchen(String kunde) {
        for (int i = 0; i < belegteKonten; i++) {
            if (konten[i].getInhaber().equals(kunde)) {
                return konten[i];
            }
        }
        return null;
    }

    public void kontoEroeffnen(String kunde) {
        Konto konto = kontoSuchen(kunde);
        if (konto != null) {
            System.out.println(konto.getInhaber() + " hat schon ein Konto.");
            return;
        }
        if (belegteKonten >= MAX_KONTEN) {
            System.out.println("Keine Konten mehr frei");
            return;
        }
        
        konten[belegteKonten] = new Konto(kunde);
        System.out.println("Das Konto für " + kunde + " wurde angelegt.");
        belegteKonten++;
    }

    public void einzahlen(String kunde, float betrag) {
        Konto konto = kontoSuchen(kunde);
        if (konto == null) {
            System.out.println(kunde + "besitzt kein Konto.");
        } else {
            konto.einzahlen(betrag);
            System.out.println(
                    kunde + " hat " + betrag + " € eingezahlt und verfuegt nun ueber " + konto.getKontostand() + " €");
        }
    }

    public float abheben(String kunde, float betrag) {
        Konto konto = kontoSuchen(kunde);
        if (konto == null) {
            System.out.println(kunde + " besitzt kein Konto.");
            return 0;
        } else {
            return konto.abheben(betrag);
        }
    }

    public void bestandAusgeben() {
        System.out.println("Nach dem " + monat + ". Monat befinden sich folgende Betraege auf den Konten:");
        float summe = 0;
        for (int i = 0; i < belegteKonten; i++) {
            System.out.println(konten[i].toSring());
            summe = summe + konten[i].getKontostand();
        }
        System.out.println("=> Auf allen Konten in Summe befinden sich: " + summe + " €");
    }

    public void abschlussDurchfuehren() {
        for (int i = 0; i < belegteKonten; i++) {
            konten[i].verzinsen();
        }
        monat++;
    }
}
