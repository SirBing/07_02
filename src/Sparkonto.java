public class Sparkonto extends Konto {

    public Sparkonto(String inhaber) {
        super(inhaber);
    }

    @Override
    public String toString() {
        return "Sparkonto: " + super.toSring();
    }
    
}
