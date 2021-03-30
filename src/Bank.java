public class Bank {
    private final int MAX_KONTEN = 100;
    private int anzahl = 0;
    private float[] konten = new float[MAX_KONTEN];
    private String[] inhaber = new String[MAX_KONTEN];
    private final float ZINSSATZ = 3.0f;
    private int monat = 0;

    private int kontoSuchen(String kunde) {
        for (int i = 0; i < anzahl; i++) {
            if (inhaber[i].equals(kunde)) {
                return i;
            }
        }
        return -1;
    }

    public void kontoEroeffnen(String kunde) {
        if (kontoSuchen(kunde) != -1) {
            System.out.println(kunde + " hat schon ein Konto.");
            return;
        }
        if (anzahl >= MAX_KONTEN) {
            System.out.println("Keine Konten mehr frei");
            return;
        }
        inhaber[anzahl] = kunde;
        konten[anzahl] = 0;
        System.out.println("Das Konto für " + kunde + " wurde angelegt.");
        anzahl++;
    }

    public void einzahlen(String kunde, float betrag) {
        int kontonr = kontoSuchen(kunde);
        if (kontonr == -1) {
            System.out.println(kunde + "besitzt kein Konto.");
        } else {
            konten[kontonr] = konten[kontonr] + betrag;
            System.out.println(
                    kunde + " hat " + betrag + " € eingezahlt und verfuegt nun ueber " + konten[kontonr] + " €");
        }
    }

    public float abheben(String kunde, float betrag) {
        int kontonr = kontoSuchen(kunde);
        if (kontonr == -1) {
            System.out.println(kunde + " besitzt kein Konto.");
            return 0;
        } else {
            if (konten[kontonr] > betrag) {
                konten[kontonr] = konten[kontonr] - betrag;
                System.out.println(
                        kunde + "hat " + betrag + " € abgehoben und verfuegt nun ueber " + konten[kontonr] + " €");
            } else {
                betrag = konten[kontonr];
                konten[kontonr] = konten[kontonr] - konten[kontonr];
                System.out.println(
                        kunde + "verfuegt nicht über ausreichend Guthaben, deshalb wurde der max. Betrag von " + betrag
                                + " € abgehoben und " + kunde + " verfuegt nun ueber " + konten[kontonr] + " €");
            }
            return betrag;
        }
    }

    public void bestandAusgeben() {
        System.out.println("Nach dem " + monat + ". Monat befinden sich folgende Betraege auf den Konten:");
        float summe = 0;
        for (int i = 0; i < anzahl; i++) {
            System.out.println(inhaber[i] + ": " + konten[i] + " €");
            summe = summe + konten[i];
        }
        System.out.println("=> Auf allen Konten in Summe befinden sich: " + summe + " €");
    }

    public void abschlussDurchfuehren() {
        for (int i = 0; i < anzahl; i++) {
            konten[i] = konten[i] * (1 + ZINSSATZ / 12 / 100);
        }
        monat++;
    }
}
