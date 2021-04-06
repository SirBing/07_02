public class Girokonto extends Konto {

    final float SOLLZINSSATZ = 2.0f;
    int pin;

    private String IBAN;

    public Girokonto(String inhaber, int pin) {
        super(inhaber);
        this.pin = pin;
        IBAN = "DE";
        for (int i = 0; i < 14; i++) {
            IBAN += Integer.toString((int)(Math.random() * 9));
        }
        System.out.println("Die IBAN für " + inhaber + " lautet: " + IBAN);
    }

    public String getIBAN() {
        return IBAN;
    }

    @Override
    public float abheben(float betrag) {
        if (kontostand >= betrag) {
            return super.abheben(betrag);
        } else {
            kontostand -= betrag;
            System.out.println(
                        getInhaber() + "hat " + betrag + " € abgehoben hat nun einen Dispositionskredit in Höhe von: " + kontostand + " €");
            return kontostand;
        }
    }

    @Override
    public float verzinsen() {
        if(kontostand >= 0){
            return super.verzinsen();
        } else {
            kontostand = kontostand * (1 + SOLLZINSSATZ / 12 / 100);
            return kontostand;
        }
    }

    @Override
    public String toString() {
        return "Girokonto: " + super.toSring();
    }

    @Override
    public boolean pruefePin(int pin) {
        if (this.pin == pin) {
            return true;
        } else {
            return false;
        }
    }






    
}
