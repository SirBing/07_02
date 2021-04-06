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

    private Konto kontoSuchen(String iban, int pin) {
        for (int i = 0; i < belegteKonten; i++) {
            //Abfangen von iban null objekt
            if(konten[i].getIBAN() == null) {
                continue;
            }
            if(konten[i].getIBAN().equals(iban)){
                if(konten[i].pruefePin(pin)) {
                    return konten[i];
                }
            }
        }
        return null;
    }

    public String getIBAN(String kunde) {
        Konto konto = kontoSuchen(kunde);
        return konto.getIBAN();
    }

    public void sparkontoEroeffnen(String kunde) {
        Konto konto = kontoSuchen(kunde);
        if (konto != null) {
            System.out.println(konto.getInhaber() + " hat schon ein Konto.");
            return;
        }
        if (belegteKonten >= MAX_KONTEN) {
            System.out.println("Keine Konten mehr frei");
            return;
        }
        
        konten[belegteKonten] = new Sparkonto(kunde);
        System.out.println("Das Sparkonto für " + kunde + " wurde angelegt.");
        belegteKonten++;
    }

    public void girokontoEroeffnen(String kunde, int pin) {
        Konto konto = kontoSuchen(kunde);
        if (konto != null) {
            System.out.println(konto.getInhaber() + " hat schon ein Konto.");
            return;
        }
        if (belegteKonten >= MAX_KONTEN) {
            System.out.println("Keine Konten mehr frei");
            return;
        }
        
        konten[belegteKonten] = new Girokonto(kunde, pin);
        System.out.println("Das Girokonto für " + kunde + " wurde angelegt.");
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

    public void einzahlenVonAutomat(String iban, int pin, float betrag) {
        Konto konto = kontoSuchen(iban, pin);
        if (konto == null) {
            System.out.println("Falsche IBAN und/oder PIN");
        } else {
            konto.einzahlen(betrag);
            System.out.println(
                    "Der Betrag über: " + betrag + " € wurde eingezahlt und der Kontostand beträgt nun " + konto.getKontostand() + " €");
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

    public float abhebenVonAutomat(String iban, int pin, float betrag) {
        Konto konto = kontoSuchen(iban, pin);
        if (konto == null) {
            System.out.println("Falsche IBAN und/oder PIN");
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
