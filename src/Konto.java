public class Konto {
    private String inhaber;
    private String IBAN;
    private float kontostand;
    private final float ZINSSATZ = 3.0f;

    public Konto(String inhaber) {
        this.inhaber = inhaber;
        IBAN = "DE";
        for (int i = 0; i < 14; i++) {
            IBAN += Integer.toString((int)(Math.random() * 9));
        }
        System.out.println("Die IBAN für " + inhaber + " lautet: " + IBAN);
    }

    public String getInhaber() {
        return inhaber;
    }

    public float getKontostand() {
        return kontostand;
    }

    public void einzahlen (float betrag) {
        kontostand += betrag;
    }

    public float abheben (float betrag) {
        if (kontostand > betrag) {
            kontostand -= betrag;
            System.out.println(
                        inhaber + "hat " + betrag + " € abgehoben und verfuegt nun ueber " + kontostand + " €");
        } else {
            betrag = kontostand;
            kontostand -= betrag;
            System.out.println(
                        inhaber + "verfuegt nicht über ausreichend Guthaben, deshalb wurde der max. Betrag von " + betrag
                                + " € abgehoben und " + inhaber + " verfuegt nun ueber " + kontostand + " €");
        }
        return betrag;
    }

    public String toSring() {
        return inhaber + ": " + kontostand;
    }

    public float verzinsen() {
        kontostand = kontostand * (1 + ZINSSATZ / 12 / 100);
        return kontostand;
    }


}
