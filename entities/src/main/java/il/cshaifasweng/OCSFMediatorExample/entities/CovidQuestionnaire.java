package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.Entity;

@Entity
public class CovidQuestionnaire extends Covid19Test{

    // fields representing Covid19 symptoms
    public boolean metCovid19Patient; // did you meet a covid patient?
    public boolean hasFever;
    public boolean hasCough;
    public boolean tired;
    public boolean taste; // loss of taste
    public boolean smell; // loss of smell

    public CovidQuestionnaire(){
        super();
    }

    public CovidQuestionnaire(boolean met, boolean f, boolean c, boolean tired, boolean taste, boolean s){
        this.metCovid19Patient=met;
        this.hasFever=f;
        this.hasCough=c;
        this.tired=tired;
        this.taste=taste;
        this.smell=s;
    }

    public boolean getMetCovid19Patient() { return metCovid19Patient; }

    public void setMetCovid19Patient(boolean metCovid19Patient) { this.metCovid19Patient = metCovid19Patient; }

    public boolean getHasFever() { return hasFever; }

    public void setHasFever(boolean hasFever) { this.hasFever = hasFever; }

    public boolean getHasCough() { return hasCough; }

    public void setHasCough(boolean hasCough) { this.hasCough = hasCough; }

    public boolean getTired() { return tired; }

    public void setTired(boolean tired) { this.tired = tired; }

    public boolean getTaste() { return taste; }

    public void setTaste(boolean taste) { this.taste = taste; }

    public boolean getSmell() { return smell; }

    public void setSmell(boolean smell) { this.smell = smell; }

    /*
    // this method helps with filling the questionnaire
    public void fillQuestionnaire(){

    }
*/

}
