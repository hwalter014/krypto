public class Praktikum {
    public static void main(String[] args) {
        String chiffrat = Helper.readText("chiffrat");
        Helper.writeText(Prak1.decryptChiffrat(chiffrat), "chiffratDECRYPT");

    }
}
