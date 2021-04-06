public class Kundeninteraktion {
    public static void main(String[] args) {
        Bank bank = new Bank();
		bank.sparkontoEroeffnen("Erika Mustermann");
		bank.einzahlen("Erika Mustermann", 100);
		
		bank.girokontoEroeffnen("Marcus Deininger", 1100);
		bank.einzahlen("Marcus Deininger", 120);
		bank.abheben("Marcus Deininger", 150);
		bank.einzahlenVonAutomat(bank.getIBAN("Marcus Deininger"), 1100, 100);
        bank.abhebenVonAutomat(bank.getIBAN("Marcus Deininger"), 1100, 300);
		
        for(int i = 0; i < 12; i++) {
            bank.abschlussDurchfuehren();    
        }
        bank.abschlussDurchfuehren();
		bank.bestandAusgeben();
    }
}
