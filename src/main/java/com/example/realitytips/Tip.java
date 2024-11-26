package com.example.realitytips;

public class Tip {
    double price;
    int tip_percent;
    int number_of_person;

    public Tip(double price, int tip_percent, int number_of_person) {
        this.price = price;
        this.tip_percent = tip_percent;
        this.number_of_person = number_of_person;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getTip_percent() {
        return tip_percent;
    }

    public void setTip_percent(int tip_percent) {
        this.tip_percent = tip_percent;
    }

    public int getNumber_of_person() {
        return number_of_person;
    }

    public void setNumber_of_person(int number_of_person) {
        this.number_of_person = number_of_person;
    }

    @Override
    public String toString() {
        return "Tip{" +
                "price=" + price +
                ", tip_percent=" + tip_percent +
                ", number_of_person=" + number_of_person +
                '}';
    }

    public double get_tip(Tip tip) throws IllegalArgumentException {
        if (tip.getPrice() <= 0) {
            System.out.println(tip.getPrice());
            throw new IllegalArgumentException("Le montant de l'addition ne peut pas être nulle ou négative");
        }
        if (tip.getTip_percent() <= 0) {
            System.out.println(tip.getPrice());
            throw new IllegalArgumentException("Le pourcentage de pourboire ne peut pas être nul ou négatif");
        }
        if (tip.getNumber_of_person() <= 0) {
            System.out.println(tip.getNumber_of_person());
            throw new IllegalArgumentException("Le nombre de personne devant payer l'addition est nul ou négatif");
        }
        return (tip.getPrice() * tip.getTip_percent() / 100) / tip.getNumber_of_person();
    }

    public double get_total(Tip tip, double _tip) throws IllegalArgumentException {
        if (tip.getPrice() <= 0) {
            throw new IllegalArgumentException("L'addition ne peut pas être négative");
        }
        if (tip.getTip_percent() <= 0) {
            System.out.println(tip.getPrice());
            throw new IllegalArgumentException("Le pourcentage de pourboire ne peut pas être nulle ou négative");
        }
        if (tip.getNumber_of_person() <= 0) {
            System.out.println(tip.getNumber_of_person());
            throw new IllegalArgumentException("Le nombre de personne devant payer l'addition ne peut pas être négatif");
        }
        return (tip.getPrice() / tip.getNumber_of_person()) + _tip;
    }
}
