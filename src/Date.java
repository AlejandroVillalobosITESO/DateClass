public class Date {
    private int day;
    private int month;
    private int year;

    private final int DIA_INIC;
    private final int MES_INIC;
    private final int AÑO_INIC;

    //CONSTANTES QUE NUNCA SE USAN :)
    private static final byte ENERO     = 1;
    private static final byte FEBRERO   = 2;
    private static final byte MARZO     = 3;
    private static final byte ABRIL     = 4;
    private static final byte MAYO      = 5;
    private static final byte JUNIO     = 6;
    private static final byte JULIO     = 7;
    private static final byte AGOSTO    = 8;
    private static final byte SEPTIEMBRE = 9;
    private static final byte OCTUBRE   = 10;
    private static final byte NOVIEMBRE = 11;
    private static final byte DICIEMBRE = 12;

    private static final byte LUNES        = 1;
    private static final byte MARTES       = 2;
    private static final byte MIERCOLES    = 3;
    private static final byte JUEVES       = 4;
    private static final byte VIERNES      = 5;
    private static final byte SABADO       = 6;
    private static final byte DOMINGO      = 0;

    public Date() {
        setDay(1);
        setMonth(1);
        setYear(1970);
        DIA_INIC = 1;
        MES_INIC = 1;
        AÑO_INIC = 1970;
    }

    public Date(int year, int month, int day) {
        setDay(day);
        setMonth(month);
        setYear(year);
        DIA_INIC = day;
        MES_INIC = month;
        AÑO_INIC = year;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        if(day>31 || day<1){
            setDay(1);
            return;
        }
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        if(month>12 || month<1){
            setMonth(1);
            return;
        }
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        if(year>9999 || year<0){
            setYear(1970);
            return;
        }
        this.year = year;
    }

    @Override
    public String toString() {
        return "Date{" +
                "day=" + day +
                ", month=" + month +
                ", year=" + year +
                ", monthname=" + getMonthStr() +
                '}';
    }

    public Boolean isValid() {
        return !(getDay() > daysInAMonth(this.getMonth()));
    }

    public String getMonthStr(){
        switch (this.month) {
            case 1:
                return "Enero";
            case 2:
                return "Febrero";
            case 3:
                return "Marzo";
            case 4:
                return "Abril";
            case 5:
                return "Mayo";
            case 6:
                return "Junio";
            case 7:
                return "Julio";
            case 8:
                return "Agosto";
            case 9:
                return "Septiembre";
            case 10:
                return "Octubre";
            case 11:
                return "Noviembre";
            case 12:
                return "Diciembre";
            default:
                return "ERROR";
        }
    }

    @Override
    public boolean equals(Object o) {
        // Para usar equals() se hace:
        // boolean equalDates = date1.equals(date2);
        if (this == o) return true;
        if (!(o instanceof Date)) return false;
        Date date = (Date) o;
        return this.getDay() == date.getDay() && this.getMonth() == date.getMonth() && this.getYear() == date.getYear();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        // Para hacer clone() se hace:
        // Date dateClone = (Date) date1.clone();
        return super.clone();
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("Se destruye: "+ this.toString());
        super.finalize();
    }

    public void nextDay(){
        if(this.getDay()+1 > daysInAMonth(this.getMonth())){
            this.setDay(1);
            if(this.getMonth() == 12){
                this.setMonth(1);
                this.setYear(this.getYear()+1);
            }
            else{
                this.setMonth(this.getMonth()+1);
            }
        }
        else{
            this.setDay(this.getDay()+1);
        }
    }

    public void prevDay(){
        if(this.getDay() == 1){
            if(this.getMonth() == 1){
                this.setYear(this.getYear()-1);
                this.setMonth(12);
                this.setDay(31);
                return;
            }
            this.setDay(daysInAMonth(this.getMonth()-1));
            this.setMonth(this.getMonth()-1);
            return;
        }
        this.setDay(this.getDay()-1);
    }

    private int daysInAMonth(int month){
        switch (month){
            //meses de 31
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            //meses de 30
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            //febrero
            case 2:
                if(isLeapYear(this.year)){
                    return 29;
                }
                else{
                    return 28;
                }
            default:
                System.out.println("ERROR");
                return 0;
        }
    }

    public boolean isLeapYear(int year){
        return((year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0));
    }
}
