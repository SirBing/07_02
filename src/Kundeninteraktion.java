public class Kundeninteraktion {
    public static void main(String[] args) {
        Bank bank = new Bank();
		bank.kontoEroeffnen("Erika Mustermann");
		bank.einzahlen("Erika Mustermann", 100);
		
		bank.kontoEroeffnen("Marcus Deininger");
		bank.einzahlen("Marcus Deininger", 120);
		bank.abheben("Marcus Deininger", 150);
		bank.einzahlen("Marcus Deininger", 20);
		
        for(int i = 0; i < 12; i++) {
            bank.abschlussDurchfuehren();    
        }
        bank.abschlussDurchfuehren();
		bank.bestandAusgeben();
    }
}
