public class Human {
    enum Gender{
        MALE, FEMALE;

        @Override
        public String toString(){
            return this.name().toLowerCase();
        }
    }

    private double heightInInches;
    private double weightInPounds;
    private Gender gender;
    private Gender expectedGender;

    public Human(double heightInInches, double weightInPounds, Gender gender) {
        this.heightInInches = heightInInches;
        this.weightInPounds = weightInPounds;
        this.gender = gender;
    }

    public double getHeightInInches() {
        return heightInInches;
    }

    public void setHeightInInches(double heightInInches) {
        this.heightInInches = heightInInches;
    }

    public double getWeightInPounds() {
        return weightInPounds;
    }

    public void setWeightInPounds(double weightInPounds) {
        this.weightInPounds = weightInPounds;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Gender getExpectedGender() { return expectedGender; }

    public void setExpectedGender(Gender expectedGender) { this.expectedGender = expectedGender; }

    public void setExpectedGender(){
        double y = (5.0/3.0)*heightInInches + 38.33;
        if(weightInPounds >= y){
            expectedGender = Gender.MALE;
        }
        else{
            expectedGender = Gender.FEMALE;
        }
    }

    public boolean calculateGenderPredictionForAccuracy(){
        return expectedGender == gender;
    }

    @Override
    public String toString() {
        return String.format("%.2f,%.2f,%d\n", getHeightInInches(), getWeightInPounds(), getGender() == Gender.MALE ? 0 : 1);
    }
}
